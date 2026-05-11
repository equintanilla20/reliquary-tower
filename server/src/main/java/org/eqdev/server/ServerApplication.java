package org.eqdev.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ServerApplication {

	public static void main(String[] args) {
        System.out.println("Starting server...");
		SpringApplication.run(ServerApplication.class, args);
	}

}
