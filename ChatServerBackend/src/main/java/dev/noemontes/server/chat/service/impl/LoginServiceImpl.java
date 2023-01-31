package dev.noemontes.server.chat.service.impl;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.noemontes.server.chat.converter.UserConverter;
import dev.noemontes.server.chat.dto.UserDto;
import dev.noemontes.server.chat.entity.UserEntity;
import dev.noemontes.server.chat.repository.UserRepository;
import dev.noemontes.server.chat.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserConverter userConverter;
	
	@Override
	public UserDto login(UserDto userDto) {
		UserEntity userEntity;
		
		try {
			userEntity = userConverter.convertDtoToEntity(userDto);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
			
			return null;
		}
		List<UserEntity> userListFromDb = userRepository.findByEmailAndPassword(userEntity.getEmail(), userEntity.getPassword());
		
		if(!userListFromDb.isEmpty() && userListFromDb.size()==1) {
			UserEntity uniqueUserFromDb = userListFromDb.get(0);
			return userConverter.convertEntityToDto(uniqueUserFromDb);
		}else {
			//TODO: Usuario no encontrado o usuario duplicado
			return null;
		}
	}
}
