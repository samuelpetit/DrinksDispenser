package com.lombardodier.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * The main class of application.
 * 
 * @author Samuel Petit
 *
 */
@SpringBootApplication
@EnableWebMvc
@ComponentScan(basePackages = { "com.lombardodier" })
public class DrinksDispenserApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(DrinksDispenserApplication.class, args);
    }
}
