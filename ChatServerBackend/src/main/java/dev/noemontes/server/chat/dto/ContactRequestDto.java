package dev.noemontes.server.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContactRequestDto {
	private String requestUserUuid;
	private String requestUserName;
	private String requestUserEmail;
	private String responseUserUuid;
	private String responseUserName;
	private String responseUserEmail;
	private Boolean contactAccepted;
}
