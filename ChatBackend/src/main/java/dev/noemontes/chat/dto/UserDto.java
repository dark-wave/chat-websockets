package dev.noemontes.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private String uid;
	private String name;
	private String lastName;
	private String email;
	private String password;
	private Boolean online;
}
