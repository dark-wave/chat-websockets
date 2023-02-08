package dev.noemontes.server.chat.service;

import java.util.List;

import dev.noemontes.server.chat.dto.UserRegisterDto;

public interface UserService {
	public UserRegisterDto saveUser(UserRegisterDto userDto);
	public List<UserRegisterDto> listUsers();
	public UserRegisterDto getUserById(Long id);
	public List<UserRegisterDto> getUserContacts(String useruuid);
}
