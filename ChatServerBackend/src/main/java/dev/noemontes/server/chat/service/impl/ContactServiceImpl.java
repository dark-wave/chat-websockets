package dev.noemontes.server.chat.service.impl;

import java.util.List;
import java.util.Optional;

import ch.qos.logback.core.net.SyslogOutputStream;
import dev.noemontes.server.chat.converter.UserConverter;
import dev.noemontes.server.chat.dto.ContactDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import dev.noemontes.server.chat.dto.ContactRequestDto;
import dev.noemontes.server.chat.exceptions.ContactExistsException;
import dev.noemontes.server.chat.exceptions.UserNotFoundException;
import dev.noemontes.server.chat.model.UserModel;
import dev.noemontes.server.chat.repository.UserMongoRepository;
import dev.noemontes.server.chat.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService{
	
	@Autowired
	private UserMongoRepository userMongoRepository;

	@Autowired
	private UserConverter userConverter;
	
	@Autowired
	SimpMessagingTemplate messagingTemplate;

	@Override
	public void addContact(ContactRequestDto contactRequestDto) throws UserNotFoundException, ContactExistsException{
		Optional<UserModel> opUserModel = userMongoRepository.findByUuid(contactRequestDto.getRequestUserUuid());
		
		if(opUserModel.isPresent()) {
			UserModel userModel = opUserModel.get();
			
			//Comprobamos que el email de la persona que se quiere agregar como contacto, se encuentra registrado en el sistema
			Optional<UserModel> opContactModel = userMongoRepository.findByEmail(contactRequestDto.getUserEmail());
			if(opContactModel.isPresent()) {
				UserModel contactModel = opContactModel.get();
				
				//Comprobamos que no sean ya contactos
				if(userModel.getContacts() != null && userModel.getContacts().contains(contactModel)) {
					throw new ContactExistsException("El usuario con el email: " + contactModel.getEmail() + ", ya es contacto del usuario: " + userModel.getEmail());
				}
				
				//Se agrega como contacto en ambos sentidos
				userModel.addContact(contactModel);
				contactModel.addContact(userModel);

				userMongoRepository.save(userModel);
				userMongoRepository.save(contactModel);

				messagingTemplate.convertAndSendToUser(contactModel.getUuid(), "/queue/requestcontact", "El usuario " + userModel.getEmail() + " te ha agregado como contacto");
				notifyContacts(userModel.getUuid());
				notifyContacts(contactModel.getUuid());
			}else {
				throw new UserNotFoundException("El usuario con el email: " + contactRequestDto.getUserEmail() + ", no está registrado en el sistema");
			}
		}else {
			throw new UserNotFoundException("El usuario con el uuid: " + contactRequestDto.getRequestUserUuid() + ", no está registrado en el sistema");
		}
	}

	@Override
	public void notifyContacts(String userUuid) {
		Optional<UserModel> opUser = userMongoRepository.findByUuid(userUuid);

		if(opUser.isPresent()){
			UserModel user = opUser.get();

			List<UserModel> contacts = user.getContacts();
			for(UserModel contact : contacts) {
				ContactDto contactDto = userConverter.convertUserModelToContactDto(contact);
				if(contact.getConnected()) {
					messagingTemplate.convertAndSendToUser(contactDto.getUuid(), "/queue/contacts", user);
				}
			}
		}
	}

	@Override
	public void loadContacts(String userUuid) {
		Optional<UserModel> opUser = userMongoRepository.findByUuid(userUuid);

		if(opUser.isPresent()){
			UserModel user = opUser.get();

			List<UserModel> contacts = user.getContacts();
			for(UserModel contact : contacts) {
				ContactDto contactDto = userConverter.convertUserModelToContactDto(contact);
				messagingTemplate.convertAndSendToUser(user.getUuid(), "/queue/contacts", contactDto);
			}
		}
	}
}
