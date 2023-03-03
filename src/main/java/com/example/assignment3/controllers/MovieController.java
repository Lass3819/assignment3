package com.example.assignment3.controllers;

import com.example.assignment3.dto.movie.MovieGetDTO;
import com.example.assignment3.mappers.MovieMapper;
import com.example.assignment3.model.Character;
import com.example.assignment3.model.Movie;
import com.example.assignment3.services.CharacterService;
import com.example.assignment3.services.FranchiseService;
import com.example.assignment3.services.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/movies")
public class MovieController {
    private final MovieService movieService;
    private final CharacterService characterService;
    private final MovieMapper movieMapper;

    private final FranchiseService franchiseService;

    public MovieController(MovieService movieService, CharacterService characterService, MovieMapper movieMapper, FranchiseService franchiseService) {
        this.movieService = movieService;
        this.characterService = characterService;
        this.movieMapper = movieMapper;
        this.franchiseService = franchiseService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<MovieGetDTO>> getAll(){
        Collection<Movie> movies = movieService.findAll();
        List<MovieGetDTO> toReturn = new ArrayList<>();
        for(Movie movie: movies){
            toReturn.add(movieMapper.toMovieDto(movie));
        }
        return ResponseEntity.ok(toReturn);
    }


    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<MovieGetDTO> getById(@PathVariable int id){
        MovieGetDTO movieGetDTO = movieMapper.toMovieDto(movieService.findById(id));
        return ResponseEntity.ok(movieGetDTO);
    }

    @RequestMapping(method = RequestMethod.POST) //POST: localhost:8080/api/v1/franchises
    public ResponseEntity<Movie> add(@RequestBody Movie movie){
        Movie movieToAdd = movieService.add(movie);
        URI location = URI.create("characters/" + movieToAdd.getId());
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}") // PUT: localhost:8080/api/v1/movies/{id}
    public ResponseEntity update(@RequestBody Movie movie, @PathVariable int id){
        if(id != movie.getId()){
            return ResponseEntity.badRequest().build();
        }
        movieService.update(movie);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public ResponseEntity<Character> delete(@PathVariable int id){
        movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}/characters")
    public ResponseEntity<Set<Character>> getAllCharactersInMovie(@PathVariable int id){
        Set characters = movieService.findById(id).getCharacters();
        return ResponseEntity.ok(characters);
    }


    @RequestMapping(method = RequestMethod.PATCH, path = "{id}") // PATCH: localhost:8080/api/v1/movies/{id}
    public ResponseEntity updateRelations(@RequestBody int[] arr, @PathVariable int id){
        Movie movie = movieService.findById(id);
        if(movie == null){
            return ResponseEntity.badRequest().build();
        }
        Set<Character> characters = movie.getCharacters();
        for(int i: arr){
            Character character = characterService.findById(i);
            characters.add(character);
        }
        movie.setCharacters(characters);
        movieService.update(movie);
        return ResponseEntity.noContent().build();
    }



}
