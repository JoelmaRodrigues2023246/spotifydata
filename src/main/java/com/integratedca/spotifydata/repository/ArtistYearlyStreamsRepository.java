package com.integratedca.spotifydata.repository;

import com.integratedca.spotifydata.model.ArtistYearlyStreams;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArtistYearlyStreamsRepository extends MongoRepository<ArtistYearlyStreams, String> {
}
