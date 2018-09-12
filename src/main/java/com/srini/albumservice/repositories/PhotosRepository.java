package com.srini.albumservice.repositories;

import com.srini.albumservice.model.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhotosRepository extends MongoRepository<Photo, String> {
}
