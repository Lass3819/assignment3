package com.example.assignment3.dto.franchise;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Data
@Getter
@Setter
public class FranchiseGetDTO {
    private int id;
    private String name;
    private Set<Integer> movies;
}
