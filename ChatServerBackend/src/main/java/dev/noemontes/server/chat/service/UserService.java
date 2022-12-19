package dev.noemontes.server.chat.service;

import dev.noemontes.server.chat.dto.UserDto;

public interface UserService {
	public UserDto saveUser(UserDto userDto);
}
