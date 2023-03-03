package com.example.assignment3.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String genre;
    private int release_year;
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
    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", movieName='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", releaseYear=" + release_year +
                ", director='" + director + '\'' +
                ", picture='" + picture + '\'' +
                ", trailer='" + trailer + '\'' +
                ", franchise=" + franchise +
                ", characters=" + characters +
                '}';
    }

}
