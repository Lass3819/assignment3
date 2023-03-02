package com.example.assignment3.controllers;

import com.example.assignment3.model.Character;
import com.example.assignment3.model.Franchise;
import com.example.assignment3.model.Movie;
import com.example.assignment3.services.CharacterService;
import com.example.assignment3.services.FranchiseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/franchises")
public class FranchiseController {
    private final FranchiseService franchiseService;

    public FranchiseController(FranchiseService franchiseService) {
        this.franchiseService = franchiseService;
    }


    @RequestMapping(method = RequestMethod.GET) //GET: localhost:8080/api/v1/franchises
    public ResponseEntity<Collection<Franchise>> getAll(){
        return ResponseEntity.ok(franchiseService.findAll());
    }


    @RequestMapping(method = RequestMethod.GET, path = "{id}") //GET: localhost:8080/api/v1/franchises/{id}
    public ResponseEntity<Franchise> getById(@PathVariable int id){
        return ResponseEntity.ok(franchiseService.findById(id));
    }


    @RequestMapping(method = RequestMethod.POST) //POST: localhost:8080/api/v1/franchises
    public ResponseEntity<Franchise> add(@RequestBody Franchise franchise){
        Franchise franchiseToAdd = franchiseService.add(franchise);
        URI location = URI.create("characters/" + franchiseToAdd.getId());
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}") // PUT: localhost:8080/api/v1/franchises/{id}
    public ResponseEntity Update(@RequestBody Franchise franchise, @PathVariable int id){
        if(id != franchise.getId()){
            return ResponseEntity.badRequest().build();
        }
        franchiseService.update(franchise);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public ResponseEntity<Character> delete(@PathVariable int id){
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
        List<Movie> movies = franchiseService.findById(id).getMovies().stream().toList();
        Set<Character> character = movies.get(0).getCharacters();
        return ResponseEntity.ok(character);
    }



}
