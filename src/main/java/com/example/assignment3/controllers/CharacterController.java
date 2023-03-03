package com.example.assignment3.controllers;

import com.example.assignment3.dto.character.CharacterGetDTO;
import com.example.assignment3.mappers.CharacterMapper;

import com.example.assignment3.services.CharacterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.assignment3.model.Character;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/characters")
public class CharacterController {
    private final CharacterService characterService;
    private final CharacterMapper characterMapper;

    public CharacterController(CharacterService characterService, CharacterMapper characterMapper) {
        this.characterService = characterService;
        this.characterMapper = characterMapper;
    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<CharacterGetDTO>> getAll(){
        Collection<Character> characters = characterService.findAll();
        List<CharacterGetDTO> toReturn = new ArrayList<>();
        for(Character character: characters){
            toReturn.add(characterMapper.toCharacterDto(character));
        }
        return ResponseEntity.ok(toReturn);
    }


    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<CharacterGetDTO> getById(@PathVariable int id){
        CharacterGetDTO characterGetDTO = characterMapper.toCharacterDto(characterService.findById(id));
        return ResponseEntity.ok(characterGetDTO);
    }
    @RequestMapping(method = RequestMethod.POST) //POST: localhost:8080/api/v1/characters
    public ResponseEntity<Character> add(@RequestBody Character character){
        Character characterToAdd = characterService.add(character);
        URI location = URI.create("characters/" + characterToAdd.getId());
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}") // PUT: localhost:8080/api/v1/characters/{id}
    public ResponseEntity update(@RequestBody Character character, @PathVariable int id){
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
