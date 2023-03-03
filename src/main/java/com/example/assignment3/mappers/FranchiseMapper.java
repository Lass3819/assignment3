package com.example.assignment3.mappers;

import com.example.assignment3.dto.character.CharacterGetDTO;
import com.example.assignment3.dto.franchise.FranchiseGetDTO;
import com.example.assignment3.dto.movie.MovieGetDTO;
import com.example.assignment3.model.Franchise;
import com.example.assignment3.model.Movie;
import com.example.assignment3.model.Character;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface FranchiseMapper {


    @Mapping(target = "movies", source = "movies", qualifiedByName = "moviesToIds")
    FranchiseGetDTO toFranchiseDto(Franchise franchise);


    @Named("moviesToIds")
    default Set<Integer> map(Set<Movie> source) {
        if(source == null)
            return null;
        return source.stream()
                .map(s -> s.getId()).collect(Collectors.toSet());
    }
}