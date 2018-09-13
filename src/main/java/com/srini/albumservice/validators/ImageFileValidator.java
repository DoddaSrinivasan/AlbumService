package com.srini.albumservice.validators;

import com.srini.albumservice.exception.ValidationException;
import com.srini.albumservice.response.Error;
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
            throw new ValidationException(new Error("Invalid File Type. Only Image is valid"));
        }
    }
}
