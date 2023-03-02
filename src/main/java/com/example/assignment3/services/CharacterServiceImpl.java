package com.example.assignment3.services;

import com.example.assignment3.model.Character;
import com.example.assignment3.repositories.CharacterRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class CharacterServiceImpl implements CharacterService{
    private final CharacterRepository characterRepository;

    public CharacterServiceImpl(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public Character findById(Integer id) {
        return characterRepository.findById(id).get();
    }

    @Override
    public Collection<Character> findAll() {
        return characterRepository.findAll();
    }

    @Override
    public Character add(Character entity) {
        return characterRepository.save(entity);
    }

    @Override
    public Character update(Character entity) {
        return characterRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        Character character = findById(id);
        character.getMovies().forEach(s ->{
            Set tempSet = s.getCharacters();
            tempSet.remove(character);
            s.setCharacters(tempSet);
                }
        );
        characterRepository.delete(character);

    }
}
