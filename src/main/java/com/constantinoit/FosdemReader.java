package com.constantinoit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class FosdemReader {

    public static void main(String[] args) {
        SpringApplication.run(com.constantinoit.FosdemReader.class, args);
    }

}