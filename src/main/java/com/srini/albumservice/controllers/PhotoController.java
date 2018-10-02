package com.srini.albumservice.controllers;

import com.srini.albumservice.exception.ValidationException;
import com.srini.albumservice.model.Photo;
import com.srini.albumservice.model.Photos;
import com.srini.albumservice.response.Error;
import com.srini.albumservice.response.Response;
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
import java.util.Arrays;
import java.util.List;

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
            Photo photo = photoService.addPhoto(bufferedImage);
            return Response.withContent(photo);
        } catch (IOException e) {
            throw new ValidationException(new Error("Could not read the Image"));
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    Response<Photos> getPhotos() {
        Photos photos = photoService.allPhotos();
        return Response.withContent(photos);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    Response<Boolean> deletePhotos(@RequestParam("photoIds") List<String> photoIds) {
        photoService.delete(photoIds);
        return Response.withContent(true);
    }
}
