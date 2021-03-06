package com.srini.albumservice.controllers;


import com.srini.albumservice.model.Photo;
import com.srini.albumservice.model.Photos;
import com.srini.albumservice.response.Response;
import com.srini.albumservice.services.PhotoService;
import com.srini.albumservice.validators.ImageFileValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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

    @Test
    public void shouldGetAllPhotosFromService() {
        Photo photo = new Photo("PhotoId", 100, 200);
        Photos photos = new Photos(Collections.singletonList(photo));
        when(photoService.allPhotos()).thenReturn(photos);
        Response<Photos> photosResponse = photoController.getPhotos();

        assertEquals(photosResponse.getContent().size(),1);
        verify(photoService).allPhotos();
    }

    @Test
    public void shouldDeletePhotos() {
        Photo photo = new Photo("PhotoId", 100, 200);
        List<String> photoIds = Arrays.asList("1", "2");
        when(photoService.delete(photoIds)).thenReturn(Collections.singletonList(photo));
        List<Photo> deletedPhotos = photoController.deletePhotos(photoIds).getContent();

        assertThat(deletedPhotos.size() , is(1));
        verify(photoService).delete(photoIds);
    }
}