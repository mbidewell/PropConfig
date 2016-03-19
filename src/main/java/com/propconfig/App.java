package com.propconfig;

/**
 * Hello world!
 *
 */
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableAutoConfiguration
public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(App.class, args);

        System.out.println("Let's inspect the beans provided by Spring Boot:");

        
    }

}
