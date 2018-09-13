package com.srini.albumservice.controllers;

import com.srini.albumservice.exception.DefaultExceptionHandler;
import com.srini.albumservice.services.PhotoStorageService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class ImagesControllerTest {

    @Mock
    private PhotoStorageService photoStorageService;

    private ImagesController imagesController;

    private MockMvc mockMvc;

    @Before
    public  void setUp() throws Exception {
        initMocks(this);

        imagesController = new ImagesController(photoStorageService);
    }

    @Test
    public void shouldAskStorageForImage() {
        imagesController.getImage("Photo.png");

        verify(photoStorageService).loadFileAsResource("Photo.png");
    }

    @Test
    public void shouldTakeDotAsPathVariable() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new ImagesController(photoStorageService))
                .setControllerAdvice(new DefaultExceptionHandler())
                .build();

        mockMvc.perform(MockMvcRequestBuilders.get("/images/someImage.png"))
                .andReturn();

        verify(photoStorageService).loadFileAsResource("someImage.png");
    }
}