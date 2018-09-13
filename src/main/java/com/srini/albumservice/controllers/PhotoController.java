package com.srini.albumservice.controllers;

import com.srini.albumservice.model.Photo;
import com.srini.albumservice.services.PhotoService;
import com.srini.albumservice.validators.ImageFileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping(value = "/photos")
class PhotoController {

    private PhotoService photoService;
    private ImageFileValidator imageFileValidator;

    @Autowired
    PhotoController(PhotoService photoService, ImageFileValidator imageFileValidator) {
        this.photoService = photoService;
        this.imageFileValidator = imageFileValidator;
    }

    @RequestMapping(method = RequestMethod.POST)
    Photo createPhoto(@RequestParam("file") MultipartFile file) {
        imageFileValidator.validate(file);
        try {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            return photoService.addPhoto(bufferedImage);
        } catch (IOException e) {
            throw new RuntimeException("Could not read the Image");
        }
    }
}
