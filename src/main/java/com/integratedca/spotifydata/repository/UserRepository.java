package com.integratedca.spotifydata.repository;

import com.integratedca.spotifydata.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * Interface that extends MongoRepository to interact with the user collection in MongoDB.
 * Allows perform CRUD operations on the users collection. 
 * Defines method findByUsername
 * to search -user by username - > used by the authentication service (CustomUserDetailsService).
 */

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}
