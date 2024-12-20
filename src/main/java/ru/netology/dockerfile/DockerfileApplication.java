package ru.netology.dockerfile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class DockerfileApplication {

    public static void main(String[] args) {
        SpringApplication.run(DockerfileApplication.class, args);
    }

}
