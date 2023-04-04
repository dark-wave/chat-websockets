package dev.noemontes.server.chat.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "messages")
@Data
public class MessageEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="userSenderId")
	private UserEntity userSender;
	
	@ManyToOne
	@JoinColumn(name="userReceviverId")
	private UserEntity userReceiver;
	
	@Column(name="created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@Column(name = "message")
	private String message;

	@PrePersist
	private void prePersist() {
		createAt = new Date();
	}
}
