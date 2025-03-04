package com.example.dio.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class AppDocumentation {

    @Bean
    OpenAPI getOpenAPI(){
        return new OpenAPI().info(info());
    }

    private Info info(){
        return new Info().title("Dine In Order Api").description("""
                ## Description
                
                Dine in Order is an API built using spring boot under rest architecture.
                The API is designed to serve as a backend to a application that deals in ordering food online.
                
                ## Tech-Stack
                - Java 8
                - Spring Boot
                - Spring Data JPA
                - MySQl DB
                - Spring Security
                
                """)
                .version("v1")
                .contact(contact());
    }

    private Contact contact(){
        return new Contact().email("yashvardhan16200@gmail.com").name("abc").url("https://www.instagram.com");
    }

}

