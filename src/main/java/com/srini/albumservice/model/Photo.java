package com.srini.albumservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = Photo.COLLECTION_NAME)
public class Photo {

    public static final String COLLECTION_NAME = "as.photo";

    public static final String ID = "id";
    public static final String PHOTO_ID = "photoId";


    @Id
    @JsonIgnore
    @Field(ID)
    String id;

    @JsonProperty
    @Field(PHOTO_ID)
    String photoId;

    public Photo(String id, String photoId) {
        this.id = id;
        this.photoId = photoId;
    }
}
