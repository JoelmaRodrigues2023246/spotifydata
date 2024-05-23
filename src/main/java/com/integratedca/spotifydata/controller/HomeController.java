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
        return "artist_yearly_streams"; // Ensure the template exists
    }

    @GetMapping("/view_top_songs_per_year")
    public String viewTopSongsPerYear() {
        return "top_songs_per_year"; // Ensure the template exists
    }
}