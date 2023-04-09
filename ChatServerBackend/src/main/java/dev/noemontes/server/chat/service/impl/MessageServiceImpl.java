package dev.noemontes.server.chat.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.noemontes.server.chat.converter.MessageConverter;
import dev.noemontes.server.chat.dto.MessageRequestDto;
import dev.noemontes.server.chat.dto.MessageResponseDto;
import dev.noemontes.server.chat.model.MessageModel;
import dev.noemontes.server.chat.repository.MessageRepository;
import dev.noemontes.server.chat.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	MessageRepository messageRepository;
	
	@Autowired
	MessageConverter messageConverter;

	@Override
	public MessageResponseDto saveMessage(MessageRequestDto message) {
		MessageModel messageModelDbResponse = messageRepository.insert(messageConverter.convertDtoToModel(message));
		return messageConverter.convertModelToDto(messageModelDbResponse);
	}

	@Override
	public List<MessageResponseDto> getMessages() {
		List<MessageModel> messageDbList = messageRepository.findAll();
		
		return messageConverter.convertModelListToResponseDtoList(messageDbList);
	}
	
	@Override
	public List<MessageResponseDto> getLastMessages(String userUuidSender, String userUuidReceiver) {
		List<MessageModel> messageList = messageRepository.findFirst10ByUserUuidSenderInOrUserUuidReveiverInOrderByCreationDateDesc(Arrays.asList(userUuidSender, userUuidReceiver), Arrays.asList(userUuidSender, userUuidReceiver));
		
		return messageConverter.convertModelListToResponseDtoList(messageList);
	}

	@Override
	public void deleteAllMessages() {
		messageRepository.deleteAll();
	}
}
