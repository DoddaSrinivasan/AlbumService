package com.srini.albumservice.validators;


import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ImageFileValidatorTest {

    @Test
    public void shouldValidateContentTypePng() {
        ImageFileValidator validator = new ImageFileValidator();
        MockMultipartFile file = new MockMultipartFile("data", "filename.png", "image/png", "some xml".getBytes());
        try{
            validator.validate(file);
            assertTrue(true);
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void shouldValidateContentTypeJpeg() {
        ImageFileValidator validator = new ImageFileValidator();
        MockMultipartFile file = new MockMultipartFile("data", "filename.jpeg", "image/jpeg", "some xml".getBytes());
        try{
            validator.validate(file);
            assertTrue(true);
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void shouldInValidateContentTypeNonImage() {
        ImageFileValidator validator = new ImageFileValidator();
        MockMultipartFile file = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());
        try{
            validator.validate(file);
            fail();
        } catch (RuntimeException ex) {
            assertTrue(true);
        }
    }
}