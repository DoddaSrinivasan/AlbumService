package com.srini.albumservice.services;

import com.srini.albumservice.configurations.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PhotoStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public PhotoStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.");
        }
    }

    public void storeFile(BufferedImage bufferedImage, String photoId) throws Exception {
        String fileName = photoId+".png";
        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        ImageIO.write(bufferedImage, "png", targetLocation.toFile());
    }

}
