package com.srini.albumservice.controllers;

import com.jayway.jsonpath.JsonPath;
import com.srini.albumservice.exception.DefaultExceptionHandler;
import com.srini.albumservice.services.PhotoService;
import com.srini.albumservice.validators.ImageFileValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class PhotoControllerIntTest {

    @Autowired
    private ImageFileValidator imageFileValidator;

    @Mock
    private PhotoService photoService;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(new PhotoController(photoService, imageFileValidator))
                .setControllerAdvice(new DefaultExceptionHandler())
                .build();
    }

    @Test
    public void shouldValidateFileContentType() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "filename.txt", "text/plain", "some xml".getBytes());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.multipart("/photos")
                .file(file))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        String message = JsonPath.read(responseBody, "$.error.message");
        assertThat(message, is("Invalid File Type. Only Image is valid"));
    }

    @Test
    public void shouldDeletePhotos() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/photos/delete?photoIds=photo1,photo2"))
                .andExpect(status().isOk())
                .andReturn();


        verify(photoService).delete(Arrays.asList("photo1", "photo2"));
    }
}
