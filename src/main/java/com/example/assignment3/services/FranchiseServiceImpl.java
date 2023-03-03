package com.example.assignment3.services;

import com.example.assignment3.model.Franchise;
import com.example.assignment3.repositories.FranchiseRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FranchiseServiceImpl implements FranchiseService{
    private final FranchiseRepository franchiseRepository;

    public FranchiseServiceImpl(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    @Override
    public Franchise findById(Integer id) {
        if(franchiseRepository.existsById(id)){
            return franchiseRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public Collection<Franchise> findAll() {
        return franchiseRepository.findAll();
    }

    @Override
    public Franchise add(Franchise entity) {
        return franchiseRepository.save(entity);
    }

    @Override
    public Franchise update(Franchise entity) {
        return franchiseRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        Franchise franchise = findById(id);
        franchise.getMovies().forEach(s -> s.setFranchise(null));
        franchiseRepository.delete(franchise);

    }
}
