package com.springDataProject.springDataProject.controllers;

import com.springDataProject.springDataProject.entities.Officer;
import com.springDataProject.springDataProject.entities.Rank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class OfficerControllerTest {
    // Mock the controller
    @Autowired
    MockMvc mvc;
    String url = "/api/officers/";
    @Test
    void saveOfficer() throws Exception {
        String officer = "{\n" +
                "\t\"rank\": \"ENSIGN\",\n" +
                "\t\"first\": \"lol\",\n" +
                "\t\"last\": \"lol\"\n" +
                "}";
        mvc.perform(post(url).content(officer).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllOfficers() throws Exception {
        mvc.perform(get(url)).andExpect(status().isOk());
    }

    @Test
    void getOfficerById() throws Exception {
        String id = "15";
        String getByIdurl = url + id;
        mvc.perform(get(getByIdurl)).andExpect(status().isOk());
    }

    @Test
    void updateOfficer() throws Exception {
        String id = "14";
        String rank =  "/" + String.valueOf(Rank.CAPTAIN);
        String putByIdurl = url + id + rank;
        mvc.perform(put(putByIdurl)).andExpect(status().isOk());
    }

    @Test
    void deleteOfficer() throws Exception {
        String id = "13";
        String deleteByIdurl = url + id;
        mvc.perform(delete(deleteByIdurl)).andExpect(status().isOk());
    }
}