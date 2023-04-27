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
import dev.noemontes.server.chat.model.UserModel;
import dev.noemontes.server.chat.repository.UserMongoRepository;
import dev.noemontes.server.chat.repository.UserRepository;
import dev.noemontes.server.chat.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMongoRepository userMongoRepository;
	
	@Autowired
	private UserConverter userConverter;

	@Override
	public UserRegisterDto saveUser(UserRegisterDto userDto) {
		UserModel userModel;
		userModel = userConverter.convertDtoToModel(userDto);
		
		UserModel userDbModel = userMongoRepository.save(userModel);
		
		return userConverter.convertModelToDto(userDbModel);
	}
	
	@Override
	public UserRegisterDto saveUser(UserEntity userEntity) {
		UserEntity userEntityDbResponse = userRepository.save(userEntity);
		return userConverter.convertEntityToDto(userEntityDbResponse);
	}
	
	@Override
	public UserRegisterDto addContactToUser(String userId, String contactId) {
		//Comprobamos que existen ambos usuarios
		Optional<UserModel> opUserModel = userMongoRepository.findByUuid(userId);
		Optional<UserModel> opContactModel = userMongoRepository.findByUuid(contactId);
		
		if(!opUserModel.isPresent() || !opContactModel.isPresent()) {
			return null;
		}
		
		UserModel userModel = opUserModel.get();
		UserModel contactModel = opContactModel.get();
		
		//Comprobamos si ya existe el usuario como contacto
		if(userModel.existsContact(contactModel.getUuid())) {
			return userConverter.convertModelToDto(userModel);
		}
		
		userModel.addContact(contactModel);
		userMongoRepository.save(userModel);
		
		return userConverter.convertModelToDto(userModel);
	}

	@Override
	public List<UserRegisterDto> listUsers() {
		List<UserModel> listDbUsers = userMongoRepository.findAll();
		
		return userConverter.convertModelListToDtoList(listDbUsers);
	}
	
	@Override
	public List<UserRegisterDto> getUserContacts(String useruuid) {
		List<UserEntity> listDbUsers = StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
		
		return userConverter.convertEntityListToDtoList(listDbUsers);
	}

	@Override
	public UserRegisterDto getUserById(String id) {
		Optional<UserModel> opUser = userMongoRepository.findById(id);
		UserRegisterDto userDto = null;
		
		if(opUser.isPresent()) {
			userDto = userConverter.convertModelToDto(opUser.get());
			return userDto;
		}else {
			return null;
		}
	}

	@Override
	public Optional<UserEntity> getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public Optional<UserEntity> getUserByUuid(String uuid) {
		return userRepository.findByUuid(uuid);
	}
	
	@Override
	public void deleteAllUsers() {
		userMongoRepository.deleteAll();
	}
}
