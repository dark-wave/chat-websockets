package dev.noemontes.server.chat.service;

import java.util.List;
import java.util.Optional;

import dev.noemontes.server.chat.dto.UserRegisterDto;
import dev.noemontes.server.chat.entity.UserEntity;

public interface UserService {
	public UserRegisterDto saveUser(UserRegisterDto userDto);
	public UserRegisterDto saveUser(UserEntity userEntity);
	public List<UserRegisterDto> listUsers();
	public UserRegisterDto getUserById(String id);
	public List<UserRegisterDto> getUserContacts(String useruuid);
	public Optional<UserEntity> getUserByEmail(String email);
	public Optional<UserEntity> getUserByUuid(String uuid);
	public void deleteAllUsers();
}
