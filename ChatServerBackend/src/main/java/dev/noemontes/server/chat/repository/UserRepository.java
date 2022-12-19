package dev.noemontes.server.chat.repository;

import org.springframework.data.repository.CrudRepository;

import dev.noemontes.server.chat.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long>{}
