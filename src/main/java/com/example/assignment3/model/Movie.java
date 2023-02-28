package com.example.assignment3.model;

import jakarta.persistence.*;

import java.util.Set;
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "movie_title")
    private String movieName;
    private String genre;
    @Column(name = "release_year")
    private int releaseYear;
    private String director;
    private String picture;
    private String trailer;
    @ManyToOne
    private Franchise franchise;
    @ManyToMany
    @JoinTable(
            name = "movie_character",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "character_id")}
    )
    private Set<Character> characters;
}
