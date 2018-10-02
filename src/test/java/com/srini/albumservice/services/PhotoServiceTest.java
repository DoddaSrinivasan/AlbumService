package com.srini.albumservice.services;

import com.srini.albumservice.model.Photo;
import com.srini.albumservice.model.Photos;
import com.srini.albumservice.repositories.PhotosRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PhotoServiceTest {

    @Mock
    private PhotosRepository photosRepository;

    @Mock
    private PhotoStorageService photoStorageService;

    @Mock
    private BufferedImage bufferedImage;

    private PhotoService photoService;

    @Before
    public void setUp() {
        initMocks(this);
        photoService = new PhotoService(photosRepository, photoStorageService);
    }

    @Test
    public void shouldAddPhotoWithNewId()throws Exception {
        when(bufferedImage.getWidth()).thenReturn(100);
        when(bufferedImage.getHeight()).thenReturn(200);

        Photo savedPhoto = photoService.addPhoto(bufferedImage);

        assertNotNull(savedPhoto.getPhotoId());
        assertThat(savedPhoto.getWidth(), is(100));
        assertThat(savedPhoto.getHeight(), is(200));
        verify(photosRepository).save(savedPhoto);
    }

    @Test
    public void shouldSaveBufferedImage() throws Exception {
        Photo savedPhoto = photoService.addPhoto(bufferedImage);

        verify(photoStorageService).storeFile(bufferedImage, savedPhoto.getPhotoId());
    }

    @Test
    public void shouldGetAllPhotosFromRepository() {
        Photo photo = new Photo("PhotoId", 100, 200);
        when(photosRepository.findAll()).thenReturn(Collections.singletonList(photo));

        Photos photos = photoService.allPhotos();

        assertEquals(photos.size(),1);
        verify(photosRepository).findAll();
    }

    @Test
    public void shouldDeletePhotosFromRepository() {
        List<String> photoIds = Arrays.asList("1", "2");
        photoService.delete(photoIds);

        verify(photosRepository).deleteByPhotoIdIn(photoIds);
    }
}
