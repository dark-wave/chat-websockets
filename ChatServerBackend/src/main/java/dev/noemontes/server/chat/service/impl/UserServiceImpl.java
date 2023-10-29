package dev.noemontes.server.chat.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import dev.noemontes.server.chat.exceptions.UserExistsException;
import dev.noemontes.server.chat.exceptions.UserNotCreateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.noemontes.server.chat.converter.UserRegisterConverter;
import dev.noemontes.server.chat.dto.UserRegisterDto;
import dev.noemontes.server.chat.model.UserModel;
import dev.noemontes.server.chat.repository.UserMongoRepository;
import dev.noemontes.server.chat.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMongoRepository userMongoRepository;
	
	@Autowired
	private UserRegisterConverter userConverter;

	@Override
	public UserRegisterDto saveUser(UserRegisterDto userDto) throws UserNotCreateException, UserExistsException {
		UserModel userModel;
		userModel = userConverter.convertDtoToModel(userDto);

		try{
			Optional<UserModel> userDbTempModel = userMongoRepository.findByEmail(userModel.getEmail());
			if (userDbTempModel.isPresent()) {
				throw new UserExistsException("El usuario ya se encuentra registrado");
			}

			UserModel userDbModel = userMongoRepository.save(userModel);

			return userConverter.convertModelToDto(userDbModel);
		}catch (Exception e) {
			throw new UserNotCreateException("Error al crear el usuario");
		}
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
		Optional<UserModel> opUser = userMongoRepository.findByUuid(useruuid);
		
		if(opUser.isPresent() && opUser.get().getContacts() != null && opUser.get().getContacts().size() > 0) {
			List<UserModel> listDbUsers = StreamSupport.stream(opUser.get().getContacts().spliterator(), false).collect(Collectors.toList());
			
			return userConverter.convertModelListToDtoList(listDbUsers);
		}
		
		return null;
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
	public void updateUserSessionId(String userUuid, String sessionId) {
		Optional<UserModel> opUser = userMongoRepository.findByUuid(userUuid);
		
		if(opUser.isPresent()) {
			UserModel userModel = opUser.get();
			userModel.setSessionId(sessionId);
			
			userMongoRepository.save(userModel);
		}
	}

	@Override
	public void removeUserSessionId(String sessionId) {
		Optional<UserModel> opUser = userMongoRepository.findBySessionId(sessionId);
		
		if(opUser.isPresent()) {
			UserModel userModel = opUser.get();
			userModel.setSessionId(null);
			userModel.setConnected(false);
			
			userMongoRepository.save(userModel);
		}
	}

	@Override
	public void removeContact(String userUuid, String contactUuid) {
		Optional <UserModel> opUser = userMongoRepository.findByUuid(userUuid);
		Optional <UserModel> opContact = userMongoRepository.findByUuid(contactUuid);

		if(opUser.isPresent() &&  opContact.isPresent()){
			UserModel userModel = opUser.get();
			UserModel contactModel = opContact.get();
			userModel.removeContact(contactModel);
			contactModel.removeContact(userModel);

			userMongoRepository.save(userModel);
			userMongoRepository.save(contactModel);
		}
	}
}
