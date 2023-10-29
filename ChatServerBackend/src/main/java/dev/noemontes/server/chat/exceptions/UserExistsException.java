package dev.noemontes.server.chat.exceptions;

public class UserExistsException extends RuntimeException{

    /** Default Serial Version UID*/
    private static final long serialVersionUID = 1L;

    public UserExistsException(String message) {
        super(message);
    }
}
