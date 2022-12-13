package dev.noemontes.chat.repository;

import org.springframework.data.repository.CrudRepository;

import dev.noemontes.chat.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long>{}
