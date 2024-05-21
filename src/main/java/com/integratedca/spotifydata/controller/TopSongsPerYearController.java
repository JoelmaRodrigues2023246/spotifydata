package com.integratedca.spotifydata.controller;

import com.integratedca.spotifydata.model.TopSongsPerYear;
import com.integratedca.spotifydata.repository.TopSongsPerYearRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/top_songs_per_year")
public class TopSongsPerYearController {

    @Autowired
    private TopSongsPerYearRepository repository;

    @GetMapping
    public List<TopSongsPerYear> getAll() {
        return repository.findAll();
    }
}
