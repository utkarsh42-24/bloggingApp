package com.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

atu

@EnableJpaRepositories("com.blog.repository")     /** to scan the specified package for repositories */
@EntityScan("com.blog.models")                    /** to pick up our JPA entities */
@SpringBootApplication
public class BlogSystem {

    public static void main(String[] args){
        SpringApplication.run(BlogSystem.class);
    }

}
