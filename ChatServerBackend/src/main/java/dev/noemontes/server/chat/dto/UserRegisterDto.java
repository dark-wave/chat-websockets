package dev.noemontes.server.chat.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegisterDto {
	private String uuid;
	private String name;
	private String lastName;
	private String email;
	private String password;
	private Boolean connected;
	private List<UserRegisterDto> contacts;
	private Date createdAt;
	
	public void addContact(UserRegisterDto contact) {
		if(contacts == null) {
			contacts = new ArrayList<UserRegisterDto>();
		}
		
		contacts.add(contact);
	}
}
