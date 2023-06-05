package dev.noemontes.server.chat.exceptions;

public class ContactExistsException extends RuntimeException{

	/** Default Serial Version UID*/
	private static final long serialVersionUID = 1L;
	
	
	public ContactExistsException(String message) {
        super(message);
    }
}
