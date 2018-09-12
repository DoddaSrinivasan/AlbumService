package com.srini.albumservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = Photo.COLLECTION_NAME)
public class Photo {

    static final String COLLECTION_NAME = "as.photo";

    private static final String PHOTO_ID = "photoId";
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";

    @Id
    private String id;

    @JsonProperty
    @Field(PHOTO_ID)
    String photoId;

    @JsonProperty
    @Field(WIDTH)
    int width;

    @JsonProperty
    @Field(HEIGHT)
    int height;

    public Photo(String photoId, int width, int height) {
        this.photoId = photoId;
        this.width = width;
        this.height = height;
    }

    public String getPhotoId() {
        return photoId;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
