package dev.noemontes.chat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.noemontes.chat.dto.UserDto;
import dev.noemontes.chat.repository.UserRepository;
import dev.noemontes.chat.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDto saveUser(UserDto userDto) {
		return null;
	}
}
