package dev.noemontes.server.chat.model;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * 
 * @author dark-wave
 * @since 1.0.0
 *
 * Clase de modelo que define un evento de contacto, cuando un usuario
 * solicita a otro ser un contacto y almacena la respuesta.
 */
@Document(collection = "contactevent")
@Data
public class ContactEventModel {
	@Id
	private String id;
	
	@DBRef
	private UserModel user;
	@DBRef
	private UserModel contact;
	
	private Boolean contactNotified = false;
	private Boolean contactAccepted = false;
	
	@CreatedDate
	private Date createdAt;
	
	private Date acceptedRejectDate;
}
