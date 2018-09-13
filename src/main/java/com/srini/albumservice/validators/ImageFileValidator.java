package com.srini.albumservice.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageFileValidator {

    @Autowired
    ImageFileValidator() { }

    public void validate(MultipartFile multipartFile) {
        String contentType = multipartFile.getContentType();
        if (contentType != null && !contentType.contains("image")) {
            throw new RuntimeException("Invalid Image. Only Image is valid");
        }
    }
}
