package dev.noemontes.server.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MessageDto {
	/* Campos finales comentados para las pruebas
		private String uuidSender;
		private String uuidReceiver;
		private String message;
		private Date creationDate;
		private Boolean read;
	*/
	
	private String senderName;
    private String receiverName;
    private String message;
    private String date;
}
