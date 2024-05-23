package com.integratedca.spotifydata.controller;

import com.integratedca.spotifydata.model.Top10Artists;
import com.integratedca.spotifydata.repository.Top10ArtistsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class Top10ArtistsController {

    @Autowired
    private Top10ArtistsRepository top10ArtistsRepository;

    @GetMapping("/view_top_10_artists")
    public String viewTop10Artists(
            @RequestParam(value = "region", defaultValue = "Ireland") String region,
            @RequestParam(value = "year", defaultValue = "2021") int year,
            Model model) {
        List<Top10Artists> top10Artists = top10ArtistsRepository.findByRegionAndYear(region, year);
        System.out.println("Retrieved Top 10 Artists: " + top10Artists);
        model.addAttribute("top10Artists", top10Artists);
        model.addAttribute("defaultRegion", region);
        model.addAttribute("defaultYear", year);
        return "top_10_artists";
    }
}