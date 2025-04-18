package org.eqdev.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.eqdev.server.repository")
public class Server {

	public static void main(String[] args) {
		SpringApplication.run(Server.class, args);
	}
}