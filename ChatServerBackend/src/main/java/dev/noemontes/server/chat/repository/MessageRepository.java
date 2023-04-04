package dev.noemontes.server.chat.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dev.noemontes.server.chat.model.MessageModel;

@Repository
public interface MessageRepository extends MongoRepository<MessageModel, String>{}
