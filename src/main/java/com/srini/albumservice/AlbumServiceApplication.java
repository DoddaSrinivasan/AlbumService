package com.srini.albumservice;

import com.srini.albumservice.configurations.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class AlbumServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlbumServiceApplication.class, args);
    }

}
