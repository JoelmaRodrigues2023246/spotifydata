package com.integratedca.spotifydata.controller;

import com.integratedca.spotifydata.model.TopArtistsPerYear;
import com.integratedca.spotifydata.repository.TopArtistsPerYearRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/top_artists_per_year")
public class TopArtistsPerYearController {

    @Autowired
    private TopArtistsPerYearRepository repository;

    @GetMapping
    public List<TopArtistsPerYear> getAll() {
        return repository.findAll();
    }
}
