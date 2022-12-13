package dev.noemontes.chat.converter;

import org.springframework.stereotype.Component;

import dev.noemontes.chat.dto.UserDto;
import dev.noemontes.chat.entity.UserEntity;

@Component
public class UserConverter {
	
	public UserDto convertEntityToDto(UserEntity userEntity) {
		UserDto userDto = new UserDto();
		
		return userDto;
	}
	
	
	
	public UserEntity convertDtoToEntity(UserDto userDto) {
		UserEntity userEntity = new UserEntity();
		
		return userEntity;
	}
}
