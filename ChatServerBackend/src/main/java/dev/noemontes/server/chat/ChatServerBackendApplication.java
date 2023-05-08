package dev.noemontes.server.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
public class ChatServerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatServerBackendApplication.class, args);
	}
}
