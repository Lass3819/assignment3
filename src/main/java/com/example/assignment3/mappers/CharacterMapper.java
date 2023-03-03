package com.example.assignment3.mappers;

import com.example.assignment3.dto.character.CharacterGetDTO;
import com.example.assignment3.model.Character;
import com.example.assignment3.model.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CharacterMapper {

    @Mapping(target = "movies", source="movies", qualifiedByName = "moviesToIds")
    CharacterGetDTO toCharacterDto(Character character);

    @Named("moviesToIds")
    default Set<Integer> map(Set<Movie> source) {
        if(source == null)
            return null;
        return source.stream()
                .map(s -> s.getId()).collect(Collectors.toSet());
    }
}
