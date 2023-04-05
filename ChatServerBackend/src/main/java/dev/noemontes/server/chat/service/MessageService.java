package dev.noemontes.server.chat.service;

import java.util.List;

import dev.noemontes.server.chat.dto.MessageRequestDto;
import dev.noemontes.server.chat.dto.MessageResponseDto;

public interface MessageService {
	public MessageResponseDto saveMessage(MessageRequestDto message);
	public List<MessageResponseDto> getMessages();
	public List<MessageResponseDto> getLastMessages(String userUuidSender, String userUuidReceiver);
	public void deleteAllMessages();
}
