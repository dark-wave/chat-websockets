package dev.noemontes.server.chat.service;

import dev.noemontes.server.chat.dto.LoginRequestDto;
import dev.noemontes.server.chat.dto.UserDto;

public interface LoginService {
	public UserDto login(LoginRequestDto userDto);
	public UserDto logout(LoginRequestDto userDto);
}
