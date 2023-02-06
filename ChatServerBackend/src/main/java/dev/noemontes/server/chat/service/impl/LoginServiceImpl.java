package dev.noemontes.server.chat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.noemontes.server.chat.converter.UserConverter;
import dev.noemontes.server.chat.dto.LoginRequestDto;
import dev.noemontes.server.chat.dto.UserLoginResponseDto;
import dev.noemontes.server.chat.dto.UserRegisterDto;
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
	public UserLoginResponseDto login(LoginRequestDto userDto) {
		List<UserEntity> userListFromDb = userRepository.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
		
		if(!userListFromDb.isEmpty() && userListFromDb.size()==1) {
			UserEntity uniqueUserFromDb = userListFromDb.get(0);
			return userConverter.convertUserEntityToUserLoginResponse(uniqueUserFromDb);
		}else {
			//TODO: Usuario no encontrado o usuario duplicado
			return null;
		}
	}
}
