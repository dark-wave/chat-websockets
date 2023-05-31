package dev.noemontes.server.chat.model;

import java.util.Date;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class MessageModel {
	@Id
	private String messageId;
	private String userUuidSender;
	private String userUuidReveiver;
	private String message;
	private Date creationDate = new Date();
	private Date readingDate;
}
