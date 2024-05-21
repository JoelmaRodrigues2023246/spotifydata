package com.integratedca.spotifydata.repository;

import com.integratedca.spotifydata.model.TopSongsPerYear;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TopSongsPerYearRepository extends MongoRepository<TopSongsPerYear, String> {
}
