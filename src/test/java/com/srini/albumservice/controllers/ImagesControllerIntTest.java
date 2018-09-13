package com.srini.albumservice.controllers;

import com.srini.albumservice.exception.DefaultExceptionHandler;
import com.srini.albumservice.services.PhotoStorageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class ImagesControllerIntTest {

    @Autowired
    PhotoStorageService photoStorageService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ImagesController(photoStorageService))
                .setControllerAdvice(new DefaultExceptionHandler())
                .build();
    }

    @Test
    public void shouldGetNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/images/someImage.png"))
                .andExpect(status().isNotFound())
                .andReturn();
    }
}
