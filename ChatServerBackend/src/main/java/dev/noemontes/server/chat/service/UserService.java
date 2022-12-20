package dev.noemontes.server.chat.service;

import java.util.List;

import dev.noemontes.server.chat.dto.UserDto;

public interface UserService {
	public UserDto saveUser(UserDto userDto);
	public List<UserDto> listUsers();
	public UserDto getUserById(Long id);
}
