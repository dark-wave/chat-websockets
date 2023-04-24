package dev.noemontes.server.chat.dto;

import java.util.Date;

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
	private Date createdAt;
}
