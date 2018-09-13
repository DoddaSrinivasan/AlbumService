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

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

        MvcResult restlt = mockMvc.perform(MockMvcRequestBuilders.multipart("/photos")
                .file(file))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = restlt.getResponse().getContentAsString();
        String message = JsonPath.read(responseBody, "$.error.message");
        assertThat(message, is("Invalid File Type. Only Image is valid"));

    }
}
