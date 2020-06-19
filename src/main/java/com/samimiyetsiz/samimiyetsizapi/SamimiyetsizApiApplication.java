package com.samimiyetsiz.samimiyetsizapi;

import com.samimiyetsiz.samimiyetsizapi.service.uploader.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class SamimiyetsizApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SamimiyetsizApiApplication.class, args);
    }

}
