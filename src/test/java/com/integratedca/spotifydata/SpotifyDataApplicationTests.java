package com.integratedca.spotifydata;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpotifyDataApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "CCT1234", password = "54321")
    void contextLoads() {
        // Application context loads successfully
    }

    @Test
    @WithMockUser(username = "CCT1234", password = "54321")
    void testHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk()); // Verifies: home page is accessible
    }

    @Test
    @WithMockUser(username = "CCT1234", password = "54321")
    void testViewTop10ArtistsPage() throws Exception {
        mockMvc.perform(get("/view_top_10_artists")
                .param("region", "Ireland")
                .param("year", "2021"))
                .andExpect(status().isOk()); // Verifies: Top 10 Artists page is accessible with parameters
    }
}