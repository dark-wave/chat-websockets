package dev.noemontes.server.chat.exceptions;

public class UserNotCreateException extends RuntimeException{

    /** Default Serial Version UID*/
    private static final long serialVersionUID = 1L;

    public UserNotCreateException(String message) {
        super(message);
    }
}
