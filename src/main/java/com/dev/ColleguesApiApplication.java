package com.dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ColleguesApiApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ColleguesApiApplication.class, args);

    }
}
