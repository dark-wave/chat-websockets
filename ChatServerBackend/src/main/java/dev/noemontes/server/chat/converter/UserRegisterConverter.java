package dev.noemontes.server.chat.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import dev.noemontes.server.chat.dto.UserLoginResponseDto;
import dev.noemontes.server.chat.dto.UserRegisterDto;
import dev.noemontes.server.chat.entity.UserEntity;
import dev.noemontes.server.chat.model.UserModel;

/**
 * @author dark-wave
 * @since 1.0.0
 *
 * Clase encargada de la conversión de tipos de DTO a Model para el objeto usuario.
 */
@Component
public class UserRegisterConverter {
	
	public UserModel convertDtoToModel(UserRegisterDto userDto) {
		UserModel userModel = new UserModel();
		
		userModel.setUuid(userDto.getUuid());
		userModel.setName(userDto.getName());
		userModel.setLastName(userDto.getLastName());
		userModel.setEmail(userDto.getEmail());
		userModel.setPassword(userDto.getPassword());
		
		return userModel;
	}
	
	public UserRegisterDto convertModelToDto(UserModel userModel) {
		UserRegisterDto userDto = new UserRegisterDto();
		
		userDto.setUuid(userModel.getUuid());
		userDto.setName(userModel.getName());
		userDto.setLastName(userModel.getLastName());
		userDto.setEmail(userModel.getEmail());
		userDto.setPassword(userModel.getPassword());
		userDto.setCreatedAt(userModel.getCreatedAt());
		userDto.setConnected(userModel.getConnected());
		
		if(userModel.getContacts()!=null && userModel.getContacts().size() > 0) {
			for(UserModel contactModel : userModel.getContacts()) {
				UserRegisterDto contactDto = new UserRegisterDto();
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
	
	
	public List<UserRegisterDto> convertModelListToDtoList(List<UserModel> userModelList){
		List<UserRegisterDto> userDtoList = new ArrayList<UserRegisterDto>();
		
		for (UserModel userModel : userModelList) {
			userDtoList.add(convertModelToDto(userModel));
		}
		return userDtoList;
	}

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