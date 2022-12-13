package dev.noemontes.chat.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MessageDto {
	private String uidSender;
	private String uidReceiver;
	private String message;
	private Date creationDate;
	private Boolean read;
}
