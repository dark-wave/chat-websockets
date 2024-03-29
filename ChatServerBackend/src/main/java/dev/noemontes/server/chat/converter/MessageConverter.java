package dev.noemontes.server.chat.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import dev.noemontes.server.chat.dto.MessageRequestDto;
import dev.noemontes.server.chat.dto.MessageResponseDto;
import dev.noemontes.server.chat.model.MessageModel;

@Component
public class MessageConverter {
	public MessageModel convertDtoToModel(MessageRequestDto messageDto) {
		MessageModel messageModel = new MessageModel();

		messageModel.setUserUuidSender(messageDto.getUuidSender());
		messageModel.setUserUuidReveiver(messageDto.getUuidReceiver());
		messageModel.setMessage(messageDto.getMessage());
		
		return messageModel;
	}
	
	public MessageResponseDto convertModelToDto(MessageModel messageModel) {
		MessageResponseDto messageResponseDto = new MessageResponseDto();
		
		if(messageModel != null) {
			messageResponseDto.setUuidSender(messageModel.getUserUuidSender());
			messageResponseDto.setUuidReceiver(messageModel.getUserUuidReveiver());
			messageResponseDto.setMessage(messageModel.getMessage());
			messageResponseDto.setCreationDate(messageModel.getCreationDate());
		}
		
		return messageResponseDto;
	}
	
	public List<MessageResponseDto> convertModelListToResponseDtoList(List<MessageModel> messageModelList){
		List<MessageResponseDto> messageResponseDtoList = new ArrayList<MessageResponseDto>();
		MessageResponseDto messageResponseDto;
		
		for(MessageModel messageModel : messageModelList) {
			messageResponseDto = new MessageResponseDto();
			messageResponseDto.setUuidSender(messageModel.getUserUuidSender());
			messageResponseDto.setUuidReceiver(messageModel.getUserUuidReveiver());
			messageResponseDto.setMessage(messageModel.getMessage());
			messageResponseDto.setCreationDate(messageModel.getCreationDate());
			
			messageResponseDtoList.add(messageResponseDto);
		}
		
		return messageResponseDtoList;
	}
}
