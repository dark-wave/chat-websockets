package dev.noemontes.server.chat.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebFlux
@EnableMongoAuditing
@EnableWebSocketMessageBroker
public class ChatServerConfiguration implements WebSocketMessageBrokerConfigurer{	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/user","/queue","/topic","/events");
		registry.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws-message").setAllowedOrigins("*").withSockJS();
	}
}