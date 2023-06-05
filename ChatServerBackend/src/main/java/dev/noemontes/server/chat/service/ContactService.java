package dev.noemontes.server.chat.service;

import dev.noemontes.server.chat.dto.ContactRequestDto;
import dev.noemontes.server.chat.exceptions.ContactExistsException;
import dev.noemontes.server.chat.exceptions.UserNotFoundException;

public interface ContactService {
	public void contactRequest(ContactRequestDto contactRequestDto) throws UserNotFoundException, ContactExistsException;
}
