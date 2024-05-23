package com.integratedca.spotifydata.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "top10artists")
public class Top10Artists {

    @Id
    private String id;
    private String artist;
    private int year;
    private String region;
    private long total_streams;
    private int rank;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public long getTotal_streams() { 
        return total_streams;
    }

    public void setTotal_streams(long total_streams) {  
        this.total_streams = total_streams;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}