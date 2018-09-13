package com.srini.albumservice.controllers;


import com.srini.albumservice.services.PhotoService;
import com.srini.albumservice.validators.ImageFileValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.mock.web.MockMultipartFile;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;


public class PhotoControllerTest {

    @Mock
    private PhotoService photoService;

    @Mock
    private ImageFileValidator imageFileValidator;

    private PhotoController photoController;

    @Before
    public void setUp() {
        initMocks(this);
        photoController = new PhotoController(photoService, imageFileValidator);
    }

    @Test
    public void shouldValidateAndCreatePhoto() {
        MockMultipartFile file = new MockMultipartFile("file", "filename.png", "image/png", "some xml".getBytes());
        photoController.createPhoto(file);

        verify(imageFileValidator).validate(file);
        verify(photoService).addPhoto(any());
    }
}