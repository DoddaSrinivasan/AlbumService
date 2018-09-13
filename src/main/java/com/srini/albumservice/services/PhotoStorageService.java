package com.srini.albumservice.services;

import com.srini.albumservice.configurations.FileStorageProperties;
import com.srini.albumservice.exception.FileNotFoundException;
import com.srini.albumservice.exception.ValidationException;
import com.srini.albumservice.response.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
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

    void storeFile(BufferedImage bufferedImage, String photoId) {
        String fileName = photoId+".png";
        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        try {
            ImageIO.write(bufferedImage, "png", targetLocation.toFile());
        } catch (IOException e) {
            throw new ValidationException(new Error("Could not save image. Try again later."));
        }
    }

    public Resource loadFileAsResource(String imageName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(imageName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + imageName);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found " + imageName);
        }
    }

}
