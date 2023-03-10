package com.example.assignment3.services;

import com.example.assignment3.model.Movie;
import com.example.assignment3.repositories.MovieRepository;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService{
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    @Override
    public Movie findById(Integer id) {
        return movieRepository.findById(id).get();
    }

    @Override
    public Collection<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie add(Movie entity) {
        return movieRepository.save(entity);
    }

    @Override
    public Movie update(Movie entity) {
        return movieRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        Movie movie = findById(id);
        movie.getCharacters().forEach(s -> {
            Set tempSet = s.getMovies();
            tempSet.remove(movie);
            s.setMovies(tempSet);
        });
        movieRepository.delete(movie);

    }
}
