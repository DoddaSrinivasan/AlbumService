package com.srini.albumservice.repositories;

import com.srini.albumservice.model.Photo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class PhotosRepositoryIntTest {

    @Autowired
    PhotosRepository photosRepository;

    @Before
    public void setUp() throws Exception {
        photosRepository.deleteAll();
    }

    @Test
    public void shouldSavePhoto() {
        Photo photo = new Photo("photoId", 10, 10);
        photosRepository.save(photo);

        List<Photo> photoList = photosRepository.findAll();
        assertThat(photoList.size(), is(1));
    }

    @Test
    public void testShouldDeletePhotos() {
        Photo photo1 = new Photo("photoId1", 10, 10);
        Photo photo2 = new Photo("photoId2", 10, 10);
        Photo photo3 = new Photo("photoId3", 10, 10);
        photosRepository.save(photo1);
        photosRepository.save(photo2);
        photosRepository.save(photo3);
        photosRepository.deleteByPhotoIdIn(Arrays.asList("photoId1", "photoId2"));

        List<Photo> photoList = photosRepository.findAll();
        assertThat(photoList.size(), is(1));
    }

    @After
    public void teardown() throws Exception {
        photosRepository.deleteAll();
    }
}
