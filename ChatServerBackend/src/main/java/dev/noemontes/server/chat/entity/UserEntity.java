package dev.noemontes.server.chat.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="uuid")
	private String uuid;
	
	@Column(name="name")
	private String name;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email", unique = true)
	private String email; 
	
	@Column(name="password")
	private String password;
	
	@Column(name="created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@OneToMany(mappedBy = "userSender")
	private List<MessageEntity> messageList;
	
	@ManyToMany
	@JoinTable(name = "users_contacts", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "contact_id"))
	private List<UserEntity> contacts;
	
	/*
	 @ManyToMany
	    @JoinTable(name = "user_contacts",
	            joinColumns = @JoinColumn(name = "user_id"),
	            inverseJoinColumns = @JoinColumn(name = "contact_id"))
	    private List<User> contacts;
	   */
	
	@PrePersist
	private void prePersist() {
		uuid = UUID.randomUUID().toString();
		createdAt = new Date();
	}
}
