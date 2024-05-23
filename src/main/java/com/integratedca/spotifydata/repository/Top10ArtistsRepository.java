package com.integratedca.spotifydata.repository;

import com.integratedca.spotifydata.model.Top10Artists;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Top10ArtistsRepository extends MongoRepository<Top10Artists, String> {
    List<Top10Artists> findByRegionAndYear(String region, int year);
}