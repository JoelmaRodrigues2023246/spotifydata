package com.integratedca.spotifydata.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "top_artists_per_year")
public class TopArtistsPerYear {
    @Id
    private String id;
    private String artist;
    private int year;
    private String region;
    private int rank;

    // Getters and setters
}
