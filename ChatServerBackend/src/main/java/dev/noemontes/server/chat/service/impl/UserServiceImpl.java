package dev.noemontes.server.chat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import dev.noemontes.server.chat.converter.UserConverter;
import dev.noemontes.server.chat.dto.UserDto;
import dev.noemontes.server.chat.entity.UserEntity;
import dev.noemontes.server.chat.repository.UserRepository;
import dev.noemontes.server.chat.service.UserService;

public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserConverter userConverter;

	@Override
	public UserDto saveUser(UserDto userDto) {
		UserEntity userEntity = userConverter.convertDtoToEntity(userDto);
		
		UserEntity userEntityDbResponse = userRepository.save(userEntity);
		
		return userConverter.convertEntityToDto(userEntityDbResponse);
	}
}
