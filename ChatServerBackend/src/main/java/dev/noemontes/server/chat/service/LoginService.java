package dev.noemontes.server.chat.service;

import dev.noemontes.server.chat.dto.UserDto;

public interface LoginService {
	public UserDto login(UserDto userDto);
}
