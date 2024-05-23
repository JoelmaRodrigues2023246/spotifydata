package com.integratedca.spotifydata.service;

import com.integratedca.spotifydata.model.Top10Artists;
import com.integratedca.spotifydata.repository.Top10ArtistsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class Top10ArtistsService {

    @Autowired
    private Top10ArtistsRepository repository;

    public List<Top10Artists> getTop10ArtistsByRegionAndYear(String region, int year) {
        return repository.findByRegionAndYear(region, year);
    }
}