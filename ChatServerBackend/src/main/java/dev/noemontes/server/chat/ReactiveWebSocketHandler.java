package dev.noemontes.server.chat;

import java.time.Duration;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.time.LocalDateTime.now;
import static java.util.UUID.randomUUID;

@Component("ReactiveWebSocketHandler")
public class ReactiveWebSocketHandler implements WebSocketHandler{

	@Override
	public Mono<Void> handle(WebSocketSession webSocketSession) {
		return webSocketSession.send(intervalFlux
		          .map(webSocketSession::textMessage))
		          .and(webSocketSession.receive()
		            .map(WebSocketMessage::getPayloadAsText).log());
	}
	
	private static final ObjectMapper json = new ObjectMapper();

    private Flux<String> eventFlux = Flux.generate(sink -> {
        Event event = new Event(randomUUID().toString(), now().toString());
        try {
            sink.next(json.writeValueAsString(event));
        } catch (JsonProcessingException e) {
            sink.error(e);
        }
    });

    private Flux<String> intervalFlux = Flux.interval(Duration.ofMillis(1000L))
      .zipWith(eventFlux, (time, event) -> event);
}
