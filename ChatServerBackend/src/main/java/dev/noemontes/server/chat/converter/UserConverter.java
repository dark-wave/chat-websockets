package dev.noemontes.server.chat.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import dev.noemontes.server.chat.dto.UserDto;
import dev.noemontes.server.chat.model.UserModel;

@Component
public class UserConverter {
	public UserModel convertDtoToModel(UserDto userDto) {
		UserModel userModel = new UserModel();
		
		userModel.setUuid(userDto.getUuid());
		userModel.setName(userDto.getEmail());
		userModel.setLastName(userDto.getLastName());
		userModel.setEmail(userDto.getEmail());
		userModel.setPassword(userDto.getPassword());
		userModel.setConnected(userDto.getConnected());
		
		return userModel;
	}
	
	public UserDto convertModelToDto(UserModel userModel) {
		UserDto userDto = new UserDto();
		
		userDto.setUuid(userModel.getUuid());
		userDto.setName(userModel.getName());
		userDto.setLastName(userModel.getLastName());
		userDto.setEmail(userModel.getEmail());
		userDto.setPassword(userModel.getPassword());
		userDto.setConnected(userModel.getConnected());
		userDto.setCreatedAt(userModel.getCreatedAt());
		
		if(userModel.getContacts()!=null && userModel.getContacts().size() > 0) {
			for(UserModel contactModel : userModel.getContacts()) {
				UserDto contactDto = new UserDto();
				contactDto.setUuid(contactModel.getUuid());
				contactDto.setName(contactModel.getName());
				contactDto.setLastName(contactModel.getLastName());
				contactDto.setEmail(contactModel.getEmail());
				contactDto.setConnected(contactModel.getConnected());
				
				userDto.addContact(contactDto);
			}
		}
		
		return userDto;
	}
	
	
	public List<UserDto> convertModelListToDtoList(List<UserModel> userModelList){
		List<UserDto> userDtoList = new ArrayList<UserDto>();
		
		for (UserModel userModel : userModelList) {
			userDtoList.add(convertModelToDto(userModel));
		}
		return userDtoList;
	}
}
