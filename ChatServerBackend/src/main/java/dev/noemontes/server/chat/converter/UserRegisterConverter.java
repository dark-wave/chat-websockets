package dev.noemontes.server.chat.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import dev.noemontes.server.chat.dto.UserRegisterDto;
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
}
