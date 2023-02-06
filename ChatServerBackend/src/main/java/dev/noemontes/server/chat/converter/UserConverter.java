package dev.noemontes.server.chat.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.Contained;
import org.springframework.stereotype.Component;

import dev.noemontes.server.chat.dto.UserLoginResponseDto;
import dev.noemontes.server.chat.dto.UserRegisterDto;
import dev.noemontes.server.chat.entity.UserEntity;

@Component
public class UserConverter {
	public UserRegisterDto convertEntityToDto(UserEntity userEntity) {
		UserRegisterDto userDto = new UserRegisterDto();
		
		userDto.setUuid(userEntity.getUuid());
		userDto.setName(userEntity.getName());
		userDto.setLastName(userEntity.getLastName());
		userDto.setEmail(userEntity.getEmail());
		userDto.setPassword(userEntity.getPassword());
		
		
		return userDto;
	}
	
	
	public UserEntity convertDtoToEntity(UserRegisterDto userDto) {
		UserEntity userEntity = new UserEntity();
		
		userEntity.setUuid(userDto.getUuid());
		userEntity.setName(userDto.getName());
		userEntity.setLastName(userDto.getLastName());
		userEntity.setEmail(userDto.getEmail());
		userEntity.setPassword(userDto.getPassword());
		
		return userEntity;
	}
	
	public UserLoginResponseDto convertUserEntityToUserLoginResponse(UserEntity logedUserFromDb) {
		UserLoginResponseDto loginResponseDto = new UserLoginResponseDto();
		
		loginResponseDto.setUuid(logedUserFromDb.getUuid());
		loginResponseDto.setName(logedUserFromDb.getName());
		loginResponseDto.setLastName(logedUserFromDb.getLastName());
		loginResponseDto.setEmail(logedUserFromDb.getEmail());
		
		if(logedUserFromDb.getContacts().size() > 0) {
			loginResponseDto.setContacts(new ArrayList<UserLoginResponseDto>());
			
			for(UserEntity contact : logedUserFromDb.getContacts()) {
				UserLoginResponseDto contactDto = new UserLoginResponseDto();
				
				contactDto.setUuid(contact.getUuid());
				contactDto.setName(contact.getName());
				contactDto.setLastName(contact.getLastName());
				contactDto.setEmail(contact.getEmail());
				
				loginResponseDto.getContacts().add(contactDto);
			}
		}
		
		return loginResponseDto;
	}
	
	public List<UserRegisterDto> convertEntityListToDtoList(List<UserEntity> userEntityList){
		List<UserRegisterDto> userDtoList = new ArrayList<UserRegisterDto>();
		
		for (UserEntity userEntity : userEntityList) {
			UserRegisterDto userDto = new UserRegisterDto();
			userDto.setUuid(userEntity.getUuid());
			userDto.setName(userEntity.getName());
			userDto.setLastName(userEntity.getLastName());
			userDto.setEmail(userEntity.getEmail());
			userDto.setPassword(userEntity.getPassword());
			
			userDtoList.add(userDto);
		}
		
		return userDtoList;
	}
}
