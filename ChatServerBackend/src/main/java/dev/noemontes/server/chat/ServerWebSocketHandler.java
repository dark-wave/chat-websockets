package dev.noemontes.server.chat;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.HtmlUtils;

public class ServerWebSocketHandler extends TextWebSocketHandler{
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
	    String request = message.getPayload();
	    System.out.println("Server received: {}" + request);
	        
	    String response = String.format("response from server to '%s'", HtmlUtils.htmlEscape(request));
	    System.out.println("Server sends: {}" + response);
	    session.sendMessage(new TextMessage(response));
	}
}
