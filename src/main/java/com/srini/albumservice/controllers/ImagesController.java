package com.srini.albumservice.controllers;

import com.srini.albumservice.services.PhotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/images")
public class ImagesController {

    private PhotoStorageService photoStorageService;

    @Autowired
    public ImagesController(PhotoStorageService photoStorageService) {
        this.photoStorageService = photoStorageService;
    }

    @RequestMapping(value = "/{imageName:.+}", method = GET)
    public Resource getImage(@PathVariable("imageName") String imageName) {
        return photoStorageService.loadFileAsResource(imageName);
    }
}
