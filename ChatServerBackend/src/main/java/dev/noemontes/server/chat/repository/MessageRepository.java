package dev.noemontes.server.chat.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dev.noemontes.server.chat.model.MessageModel;

@Repository
public interface MessageRepository extends MongoRepository<MessageModel, String>{
	List<MessageModel> findFirst10ByUserUuidSenderAndUserUuidReveiverOrderByCreationDateDesc(String userUuidSender, String userUuidReceiver);

	List<MessageModel> findFirst10ByUserUuidSenderInOrUserUuidReveiverInOrderByCreationDateDesc(List<String> userUuidSender, List<String> userUuidReceiver);
}
