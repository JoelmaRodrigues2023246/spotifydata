package com.integratedca.spotifydata.repository;

import com.integratedca.spotifydata.model.Top50Songs;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Top50SongsRepository extends MongoRepository<Top50Songs, String> {
}
