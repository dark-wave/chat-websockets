package dev.noemontes.server.chat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class ChatServerConfig implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketHandler(), "/websocket");
	}

	@Bean
	public WebSocketHandler webSocketHandler() {
		return new ServerWebSocketHandler();
	}

	/*
	 * Comentado para pruebas
	 * 
	 * @Autowired
	 * 
	 * @Qualifier("ReactiveWebSocketHandler") private WebSocketHandler
	 * webSocketHandler;
	 * 
	 * @Bean public HandlerMapping webSocketHandlerMapping() { Map<String,
	 * WebSocketHandler> map = new HashMap<>(); map.put("/event-emitter",
	 * webSocketHandler);
	 * 
	 * SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
	 * handlerMapping.setOrder(1); handlerMapping.setUrlMap(map); return
	 * handlerMapping; }
	 * 
	 * @Bean public WebSocketHandlerAdapter handlerAdapter() { return new
	 * WebSocketHandlerAdapter(); }
	 */
}
