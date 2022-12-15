package dev.noemontes.chat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.noemontes.chat.converter.UserConverter;
import dev.noemontes.chat.dto.UserDto;
import dev.noemontes.chat.entity.UserEntity;
import dev.noemontes.chat.repository.UserRepository;
import dev.noemontes.chat.service.UserService;

@Service
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
