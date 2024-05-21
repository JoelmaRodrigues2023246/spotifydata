package com.integratedca.spotifydata.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }
    
    @GetMapping("/view_artists_yearly_streams")
    public String viewArtistYearlyStreams() {
        return "artist_yearly_streams"; 
    }

    @GetMapping("/view_top_artists_per_year")
    public String viewTopArtistsPerYear() {
        return "top_artists_per_year"; // takes to template "top_artists_per_year.html"
    }

    @GetMapping("/view_top_songs_per_year")
    public String viewTopSongsPerYear() {
        return "top_songs_per_year"; // takes to "top_songs_per_year.html"
    }
}
