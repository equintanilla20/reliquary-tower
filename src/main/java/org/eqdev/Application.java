package org.eqdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    @Bean
    public graphql.schema.GraphQLScalarType extendedScalarType() {
        return graphql.scalars.ExtendedScalars.GraphQLLong;
    }
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}