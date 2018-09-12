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
        Photo photo = new Photo("id", "photoId");
        photosRepository.save(photo);

        List<Photo> photoList = photosRepository.findAll();
        assertThat(photoList.size(), is(1));
    }

    @After
    public void teardown() throws Exception {
        photosRepository.deleteAll();
    }
}
