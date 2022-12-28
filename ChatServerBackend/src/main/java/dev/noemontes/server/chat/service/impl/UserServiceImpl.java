package dev.noemontes.server.chat.service.impl;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.noemontes.server.chat.converter.UserConverter;
import dev.noemontes.server.chat.dto.UserDto;
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
	public UserDto saveUser(UserDto userDto) {
		UserEntity userEntity;
		try {
			userEntity = userConverter.convertDtoToEntity(userDto);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
			return null;
		}
		
		UserEntity userEntityDbResponse = userRepository.save(userEntity);
		
		return userConverter.convertEntityToDto(userEntityDbResponse);
	}

	@Override
	public List<UserDto> listUsers() {
		List<UserEntity> listDbUsers = StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
		
		return userConverter.convertEntityListToDtoList(listDbUsers);
	}

	@Override
	public UserDto getUserById(Long id) {
		Optional<UserEntity> opUser = userRepository.findById(id);
		UserDto userDto = null;
		
		if(opUser.isPresent()) {
			userDto = userConverter.convertEntityToDto(opUser.get());
			return userDto;
		}else {
			return null;
		}
	}
}
