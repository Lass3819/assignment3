package com.example.assignment3.controllers;

import com.example.assignment3.dto.franchise.FranchiseGetDTO;
import com.example.assignment3.dto.movie.MovieGetDTO;
import com.example.assignment3.mappers.FranchiseMapper;
import com.example.assignment3.mappers.MovieMapper;
import com.example.assignment3.model.Character;
import com.example.assignment3.model.Franchise;
import com.example.assignment3.model.Movie;
import com.example.assignment3.services.FranchiseService;
import com.example.assignment3.services.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/franchises")
public class FranchiseController {
    private final FranchiseService franchiseService;
    private final MovieService movieService;
    private final FranchiseMapper franchiseMapper;


    public FranchiseController(FranchiseService franchiseService, MovieService movieService, FranchiseMapper franchiseMapper) {
        this.franchiseService = franchiseService;
        this.movieService = movieService;
        this.franchiseMapper = franchiseMapper;
    }


    @RequestMapping(method = RequestMethod.GET) //GET: localhost:8080/api/v1/franchises
    public ResponseEntity<Collection<FranchiseGetDTO>> getAll(){
        Collection<Franchise> franchises = franchiseService.findAll();
        List<FranchiseGetDTO> toReturn = new ArrayList<>();
        for(Franchise franchise: franchises){
            toReturn.add(franchiseMapper.toFranchiseDto(franchise));
        }
        return ResponseEntity.ok(toReturn);
    }


    @RequestMapping(method = RequestMethod.GET, path = "{id}") //GET: localhost:8080/api/v1/franchises/{id}
    public ResponseEntity<FranchiseGetDTO> getById(@PathVariable int id){
        FranchiseGetDTO franchiseGetDTO = franchiseMapper.toFranchiseDto(franchiseService.findById(id));

        return ResponseEntity.ok(franchiseGetDTO);
    }


    @RequestMapping(method = RequestMethod.POST) //POST: localhost:8080/api/v1/franchises
    public ResponseEntity<Franchise> add(@RequestBody Franchise franchise){
        Franchise franchiseToAdd = franchiseService.add(franchise);
        URI location = URI.create("franchises/" + franchiseToAdd.getId());
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}") // PUT: localhost:8080/api/v1/franchises/{id}
    public ResponseEntity update(@RequestBody Franchise franchise, @PathVariable int id){
        if(id != franchise.getId()){
            return ResponseEntity.badRequest().build();
        }
        franchiseService.update(franchise);
        return ResponseEntity.noContent().build();
    }
    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public ResponseEntity delete(@PathVariable int id){
        franchiseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}/movies")
    public ResponseEntity<Set<Movie>> getAllMoviesInFranchise(@PathVariable int id){
        Set movies = franchiseService.findById(id).getMovies();
        return ResponseEntity.ok(movies);
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}/characters")
    public ResponseEntity<Set<Character>> getAllCharactersInFranchise(@PathVariable int id){
        Set<Character> characters = franchiseService.findById(id).getMovies()
                .stream().flatMap(x-> x.getCharacters().stream()).collect(Collectors.toSet());
        return ResponseEntity.ok(characters);
    }

    @RequestMapping(method = RequestMethod.PATCH, path="{id}")
    public ResponseEntity updateRelations(@RequestBody int[] arr, @PathVariable int id){
        Franchise franchise = franchiseService.findById(id);
        if(franchise == null){
            return ResponseEntity.badRequest().build();
        }
        Set<Movie> movies = franchise.getMovies();
        for(int i: arr){
            Movie movie = movieService.findById(i);
            movie.setFranchise(franchise);
            movieService.update(movie);
        }
        return ResponseEntity.noContent().build();
    }








}
