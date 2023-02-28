package com.example.assignment3.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@Entity
public class Franchise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "franchise")
    private Set<Movie> movies;


}
