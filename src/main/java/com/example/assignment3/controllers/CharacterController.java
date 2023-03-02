package com.example.assignment3.controllers;

import com.example.assignment3.services.CharacterService;
import org.apache.coyote.Request;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.assignment3.model.Character;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/characters")
public class CharacterController {
    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Character>> getAll(){
        return ResponseEntity.ok(characterService.findAll());
    }


    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<Character> getById(@PathVariable int id){
        return ResponseEntity.ok(characterService.findById(id));
    }
    @RequestMapping(method = RequestMethod.POST) //POST: localhost:8080/api/v1/characters
    public ResponseEntity<Character> add(@RequestBody Character character){
        Character characterToAdd = characterService.add(character);
        URI location = URI.create("characters/" + characterToAdd.getId());
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}") // PUT: localhost:8080/api/v1/characters/{id}
    public ResponseEntity Update(@RequestBody Character character, @PathVariable int id){
        if(id != character.getId()){
            return ResponseEntity.badRequest().build();
        }
        characterService.update(character);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
        public ResponseEntity<Character> delete(@PathVariable int id){
            characterService.deleteById(id);
            return ResponseEntity.noContent().build();
    }



}
