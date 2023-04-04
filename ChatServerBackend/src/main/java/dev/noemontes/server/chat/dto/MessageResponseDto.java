package dev.noemontes.server.chat.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MessageResponseDto {
	private String uidSender;
	private String uidReceiver;
	private String message;
	private Date creationDate;
}
