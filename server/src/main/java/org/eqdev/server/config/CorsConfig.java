package org.eqdev.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/apis/graphql") // Or your GraphQL endpoint
                        .allowedOrigins("http://localhost:3000") // Replace with your Next.js origin
                        .allowedMethods("GET", "POST", "OPTIONS") // Allowed HTTP methods
                        .allowedHeaders("*"); // Allowed headers
                        // .allowCredentials(false); // Allow credentials (if needed)
            }
        };
    }
}