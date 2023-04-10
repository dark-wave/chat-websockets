package dev.noemontes.server.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventResponseDto {
	private String codEvent;
	private String descEvent;
	private String msgRequestEvent;
	private String userResponseEvent;
}

