package com.srini.albumservice.services;

import com.srini.albumservice.generators.IdGenerator;
import com.srini.albumservice.model.Photo;
import com.srini.albumservice.model.Photos;
import com.srini.albumservice.repositories.PhotosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.List;

@Service
public class PhotoService {

    private PhotosRepository photosRepository;
    private PhotoStorageService photoStorageService;

    @Autowired
    PhotoService(PhotosRepository photosRepository, PhotoStorageService photoStorageService) {
        this.photosRepository = photosRepository;
        this.photoStorageService = photoStorageService;
    }

    public Photo addPhoto(BufferedImage image) {
        String photoId = IdGenerator.generateNewId();
        int width = image.getWidth();
        int height = image.getHeight();
        Photo photo = new Photo(photoId, width, height);

        photoStorageService.storeFile(image, photoId);
        photosRepository.save(photo);

        return photo;
    }

    public Photos allPhotos() {
        return new Photos(photosRepository.findAll());
    }

    public void delete(List<String> photoIds) {
        photosRepository.deleteByPhotoIdIn(photoIds);
    }
}
