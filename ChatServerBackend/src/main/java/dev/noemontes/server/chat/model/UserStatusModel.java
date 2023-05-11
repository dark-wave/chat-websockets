package dev.noemontes.server.chat.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "userstatus")
@Data
public class UserStatusModel {
	
	@Id	
	private String uuid;
	private Boolean connected;
	private Date lastConnection;
}
