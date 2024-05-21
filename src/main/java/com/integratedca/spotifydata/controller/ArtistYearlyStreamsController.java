package com.integratedca.spotifydata.controller;

import com.integratedca.spotifydata.model.ArtistYearlyStreams;
import com.integratedca.spotifydata.repository.ArtistYearlyStreamsRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/artists_yearly_streams")
public class ArtistYearlyStreamsController {

    @Autowired
    private ArtistYearlyStreamsRepository repository;

    @GetMapping
    public List<ArtistYearlyStreams> getAll() {
        return repository.findAll();
    }
}
