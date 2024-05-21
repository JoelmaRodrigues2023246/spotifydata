package com.integratedca.spotifydata.repository;

import com.integratedca.spotifydata.model.TopArtistsPerYear;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TopArtistsPerYearRepository extends MongoRepository<TopArtistsPerYear, String> {
}
