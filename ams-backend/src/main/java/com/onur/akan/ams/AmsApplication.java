package com.onur.akan.ams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @Author Onur Akan
 */
@SpringBootApplication
@EnableJpaRepositories("com.onur.akan.ams.database.jpa")
public class AmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmsApplication.class, args);
    }
}
