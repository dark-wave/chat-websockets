package dev.noemontes.server.chat.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.noemontes.server.chat.model.UserModel;

public interface UserMongoRepository extends MongoRepository<UserModel, String>{
	public Optional<UserModel> findByEmailAndPassword(String email);
	
	public Optional<UserModel> findByEmail(String email);
	
	public Optional<UserModel> findByEmailAndPassword(String emal, String password);
	
	public Optional<UserModel> findByUuid(String uuid);
	
	public Optional<UserModel> findBySessionId(String sessionId);
}
