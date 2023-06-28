package com.fastcamp.getinline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class FastcampusGetinlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastcampusGetinlineApplication.class, args);
    }

}
