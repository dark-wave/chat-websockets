package dev.noemontes.chat.converter;

import org.springframework.stereotype.Component;

import dev.noemontes.chat.dto.UserDto;
import dev.noemontes.chat.entity.UserEntity;

@Component
public class UserConverter {
	
	public UserDto convertEntityToDto(UserEntity userEntity) {
		UserDto userDto = new UserDto();
		
		userDto.setUuid(userEntity.getUuid());
		userDto.setName(userEntity.getName());
		userDto.setLastName(userEntity.getLastName());
		userDto.setEmail(userEntity.getEmail());
		userDto.setPassword(userEntity.getPassword());
		
		return userDto;
	}
	
	
	public UserEntity convertDtoToEntity(UserDto userDto) {
		UserEntity userEntity = new UserEntity();
		
		userEntity.setUuid(userDto.getUuid());
		userEntity.setName(userDto.getName());
		userEntity.setLastName(userDto.getLastName());
		userEntity.setEmail(userDto.getEmail());
		userEntity.setPassword(userDto.getPassword());
		
		return userEntity;
	}
}
