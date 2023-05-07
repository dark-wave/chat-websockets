package dev.noemontes.server.chat.service;

import dev.noemontes.server.chat.dto.LoginRequestDto;
import dev.noemontes.server.chat.dto.LogoutRequestDto;
import dev.noemontes.server.chat.dto.UserDto;
import dev.noemontes.server.chat.exceptions.LoginException;

public interface LoginService {
	public UserDto login(LoginRequestDto userDto) throws LoginException;
	public UserDto logout(LogoutRequestDto logoutDto);
}
