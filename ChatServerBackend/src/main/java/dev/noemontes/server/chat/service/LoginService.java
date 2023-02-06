package dev.noemontes.server.chat.service;

import dev.noemontes.server.chat.dto.LoginRequestDto;
import dev.noemontes.server.chat.dto.UserLoginResponseDto;

public interface LoginService {
	public UserLoginResponseDto login(LoginRequestDto loginRequest);
}
