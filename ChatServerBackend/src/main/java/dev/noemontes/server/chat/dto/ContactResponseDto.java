package dev.noemontes.server.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContactResponseDto {
	private ContactRequestDto contactRequest;
	
	private String userUuidResponse;
	private Boolean contactAccepted;
}
