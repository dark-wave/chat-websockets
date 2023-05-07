package dev.noemontes.server.chat.exceptions;

public class UserNotFoundException extends RuntimeException{

        /** Default Serial Version UID*/
        private static final long serialVersionUID = 1L;

        public UserNotFoundException(String message) {
            super(message);
        }
}
