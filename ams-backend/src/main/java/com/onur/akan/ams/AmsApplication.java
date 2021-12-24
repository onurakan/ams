package com.onur.akan.ams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @Author Onur Akan
 */
@SpringBootApplication
@EnableJpaRepositories("com.onur.akan.ams.repositories")
public class AmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmsApplication.class, args);
    }

//    @Bean
//    CommandLineRunner initDatabase() {
//
//        return args -> {
//            repository.save(new Book("A Guide to the Bodhisattva Way of Life", "Santideva", new BigDecimal("15.41")));
//            repository.save(new Book("The Life-Changing Magic of Tidying Up", "Marie Kondo", new BigDecimal("9.69")));
//            repository.save(new Book("Refactoring: Improving the Design of Existing Code", "Martin Fowler", new BigDecimal("47.99")));
//        };
//    }
}
