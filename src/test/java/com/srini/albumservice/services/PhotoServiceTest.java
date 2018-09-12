package com.srini.albumservice.services;

import com.srini.albumservice.model.Photo;
import com.srini.albumservice.repositories.PhotosRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.awt.image.BufferedImage;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PhotoServiceTest {

    @Mock
    private PhotosRepository photosRepository;

    @Mock
    private BufferedImage bufferedImage;

    private PhotoService photoService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        photoService = new PhotoService(photosRepository);
    }

    @Test
    public void shouldAddPhotoWithNewId() {
        when(bufferedImage.getWidth()).thenReturn(100);
        when(bufferedImage.getHeight()).thenReturn(200);

        Photo savedPhoto = photoService.addPhoto(bufferedImage);

        assertNotNull(savedPhoto.getPhotoId());
        assertThat(savedPhoto.getWidth(), is(100));
        assertThat(savedPhoto.getHeight(), is(200));
        verify(photosRepository).save(savedPhoto);
    }
}