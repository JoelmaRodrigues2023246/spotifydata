package com.integratedca.spotifydata.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "top_songs_per_year")
public class Top50Songs {
    @Id
    private String id;
    private String song;
    private String artist;
    private int year;
    private String region;
    private int rank;
    private String spotifyLink; // For providing a link to the song

    // Getters and setters
}
