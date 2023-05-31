package dev.noemontes.server.chat.service;

import java.util.List;

import dev.noemontes.server.chat.dto.UserRegisterDto;

public interface UserService {
	public UserRegisterDto saveUser(UserRegisterDto userDto);
	public List<UserRegisterDto> listUsers();
	public UserRegisterDto addContactToUser(String userId, String contactId);
	public UserRegisterDto getUserById(String id);
	public List<UserRegisterDto> getUserContacts(String useruuid);
	public void deleteAllUsers();
}
