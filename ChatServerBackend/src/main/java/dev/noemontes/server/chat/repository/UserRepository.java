package dev.noemontes.server.chat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import dev.noemontes.server.chat.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long>{
	
	@Query("SELECT U FROM UserEntity U WHERE U.email = ?1 AND U.password = ?2")
	public List<UserEntity> findByEmailAndPassword(String email, String password);
	
	public Optional<UserEntity> findByEmail(String email);
}
