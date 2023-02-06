package dev.noemontes.server.chat.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserLoginResponseDto {
	private String uuid;
	private String name;
	private String lastName;
	private String email;
	private List<UserLoginResponseDto> contacts;
}
