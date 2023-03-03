package com.example.assignment3.dto.character;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Data
@Setter
@Getter
public class CharacterGetDTO {
    private int id;
    private String name;
    private String alias;
    private String gender;
    private String pictureUrl;
    private Set<Integer> movies;
}
