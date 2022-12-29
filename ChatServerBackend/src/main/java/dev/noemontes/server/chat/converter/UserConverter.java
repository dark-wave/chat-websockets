package dev.noemontes.server.chat.converter;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.stereotype.Component;

import dev.noemontes.server.chat.dto.UserDto;
import dev.noemontes.server.chat.encrypt.EncryptData;
import dev.noemontes.server.chat.entity.UserEntity;

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
	
	
	public UserEntity convertDtoToEntity(UserDto userDto) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		UserEntity userEntity = new UserEntity();
		
		userEntity.setUuid(userDto.getUuid());
		userEntity.setName(userDto.getName());
		userEntity.setLastName(userDto.getLastName());
		userEntity.setEmail(userDto.getEmail());
		userEntity.setPassword(userDto.getPassword());
		
		return userEntity;
	}
	
	public List<UserDto> convertEntityListToDtoList(List<UserEntity> userEntityList){
		List<UserDto> userDtoList = new ArrayList<UserDto>();
		
		for (UserEntity userEntity : userEntityList) {
			UserDto userDto = new UserDto();
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
