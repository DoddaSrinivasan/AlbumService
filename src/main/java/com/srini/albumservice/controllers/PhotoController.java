package com.srini.albumservice.controllers;

import com.srini.albumservice.exception.ValidationException;
import com.srini.albumservice.model.Photo;
import com.srini.albumservice.response.Error;
import com.srini.albumservice.response.Response;
import com.srini.albumservice.services.PhotoService;
import com.srini.albumservice.validators.ImageFileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    Response<Photo> createPhoto(@RequestParam("file") MultipartFile file) {
        imageFileValidator.validate(file);
        try {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            Photo photo =photoService.addPhoto(bufferedImage);
            return Response.withContent(photo);
        } catch (IOException e) {
            throw new ValidationException(new Error("Could not read the Image"));
        }
    }
}
