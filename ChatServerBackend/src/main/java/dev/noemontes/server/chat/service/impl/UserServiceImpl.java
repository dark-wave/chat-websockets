package dev.noemontes.server.chat.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.noemontes.server.chat.converter.UserConverter;
import dev.noemontes.server.chat.dto.UserRegisterDto;
import dev.noemontes.server.chat.entity.UserEntity;
import dev.noemontes.server.chat.repository.UserRepository;
import dev.noemontes.server.chat.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserConverter userConverter;

	@Override
	public UserRegisterDto saveUser(UserRegisterDto userDto) {
		UserEntity userEntity;
		userEntity = userConverter.convertDtoToEntity(userDto);
		
		UserEntity userEntityDbResponse = userRepository.save(userEntity);
		
		return userConverter.convertEntityToDto(userEntityDbResponse);
	}

	@Override
	public List<UserRegisterDto> listUsers() {
		List<UserEntity> listDbUsers = StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
		
		return userConverter.convertEntityListToDtoList(listDbUsers);
	}

	@Override
	public UserRegisterDto getUserById(Long id) {
		Optional<UserEntity> opUser = userRepository.findById(id);
		UserRegisterDto userDto = null;
		
		if(opUser.isPresent()) {
			userDto = userConverter.convertEntityToDto(opUser.get());
			return userDto;
		}else {
			return null;
		}
	}
}
