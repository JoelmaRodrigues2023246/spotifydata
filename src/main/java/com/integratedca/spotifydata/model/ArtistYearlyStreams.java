package com.integratedca.spotifydata.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "artists_yearly_streams")
public class ArtistYearlyStreams {
    @Id
    private String id;
    private String artist;
    private int year;
    private String region;
    private int totalStreams;

    // Getters and setters
    
    
}
