package com.example.assignment3.controllers;

import com.example.assignment3.model.Character;
import com.example.assignment3.model.Franchise;
import com.example.assignment3.model.Movie;
import com.example.assignment3.services.CharacterService;
import com.example.assignment3.services.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Movie>> getAll(){
        return ResponseEntity.ok(movieService.findAll());
    }


    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<Movie> getById(@PathVariable int id){
        return ResponseEntity.ok(movieService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST) //POST: localhost:8080/api/v1/franchises
    public ResponseEntity<Movie> add(@RequestBody Movie movie){
        Movie movieToAdd = movieService.add(movie);
        URI location = URI.create("characters/" + movieToAdd.getId());
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}") // PUT: localhost:8080/api/v1/movies/{id}
    public ResponseEntity Update(@RequestBody Movie movie, @PathVariable int id){
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



}
