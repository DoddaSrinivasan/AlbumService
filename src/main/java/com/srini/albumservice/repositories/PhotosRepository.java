package com.srini.albumservice.repositories;

import com.srini.albumservice.model.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PhotosRepository extends MongoRepository<Photo, String> {

    List <Photo> deleteByPhotoIdIn(List<String> photoIds);

}
