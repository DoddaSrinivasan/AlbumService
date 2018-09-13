package com.srini.albumservice.services;

import com.srini.albumservice.configurations.FileStorageProperties;
import com.srini.albumservice.exception.FileNotFoundException;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class PhotoStorageServiceIntTest {

    private PhotoStorageService photoStorageService;
    private Path fileStorageLocation;

    @Autowired
    private FileStorageProperties fileStorageProperties;

    @Before
    public void setUp() throws Exception {
        fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        deleteTestPhotos();
        photoStorageService = new PhotoStorageService(fileStorageProperties);
    }

    @Test
    public void shouldSaveImage() throws Exception {
        photoStorageService.storeFile(getBufferedImage(), "PhotoId");

        boolean fileExists = fileStorageLocation.resolve("PhotoId.png").toFile().exists();

        assertTrue(fileExists);
    }

    @Test
    public void shouldRetrieveSavedImage() {
        photoStorageService.storeFile(getBufferedImage(), "PhotoId");

        Resource resource = photoStorageService.loadFileAsResource("PhotoId.png");

        assertNotNull(resource);
    }

    @Test(expected = FileNotFoundException.class)
    public void shouldThrowFileNotFound() {
        photoStorageService.loadFileAsResource("PhotoId.png");
    }

    @After
    public void teardown() throws Exception{
        deleteTestPhotos();
    }

    private void deleteTestPhotos() throws Exception {
        FileUtils.cleanDirectory(fileStorageLocation.toFile());
    }

    private BufferedImage getBufferedImage() {
        BufferedImage img = new BufferedImage(
                100, 200, BufferedImage.TYPE_INT_RGB );
        int r = 5;
        int g = 25;
        int b = 255;
        int col = (r << 16) | (g << 8) | b;
        for(int x = 0; x < 100; x++){
            for(int y = 20; y < 100; y++){
                img.setRGB(x, y, col);
            }
        }
        return img;
    }
}