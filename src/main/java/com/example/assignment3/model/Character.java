package com.example.assignment3.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@Entity
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String alias;
    private String gender;
    @Column(name = "picture_url")
    private String pictureUrl;


    @ManyToMany(mappedBy = "characters")
    private Set<Movie> movies;





}
