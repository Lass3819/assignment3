package com.example.assignment3.dto.movie;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
@Setter
@Getter
public class MovieGetDTO {
    private int id;
    private String name;
    private String genre;
    private int release_year;
    private String director;
    private String picture;
    private String trailer;

    private Integer franchise;
    private Set<Integer> characters;

}
