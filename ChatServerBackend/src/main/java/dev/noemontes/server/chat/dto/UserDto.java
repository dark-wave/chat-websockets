package dev.noemontes.server.chat.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	@JsonInclude(Include.NON_NULL)
	private String uuid;
	private String name;
	private String lastName;
	private String email;
	@JsonInclude(Include.NON_NULL)
	private String password;
	private Boolean connected;
	@JsonInclude(Include.NON_NULL)
	private List<UserDto> contacts;
	@JsonInclude(Include.NON_NULL)
	private Date createdAt;
	
	public void addContact(UserDto contact) {
		if(contacts == null) {
			contacts = new ArrayList<UserDto>();
		}
		
		contacts.add(contact);
	}
}
