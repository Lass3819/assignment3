package com.example.assignment3.runners;

import com.example.assignment3.services.CharacterService;
import com.example.assignment3.services.FranchiseService;
import com.example.assignment3.services.MovieService;
import jakarta.transaction.Transactional;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {
    private final MovieService movieService;
    private final FranchiseService franchiseService;

    public AppRunner(MovieService movieService, FranchiseService franchiseService) {
        this.movieService = movieService;
        this.franchiseService = franchiseService;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(franchiseService.findById(1).getName());
    }

}
