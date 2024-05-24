package com.integratedca.spotifydata.controller;

import com.integratedca.spotifydata.model.Top50Songs;
import com.integratedca.spotifydata.repository.Top50SongsRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/top_songs_per_year")
public class Top50SongsController {

    @Autowired
    private Top50SongsRepository repository;

    @GetMapping
    public List<Top50Songs> getAll() {
        return repository.findAll();
    }
}
