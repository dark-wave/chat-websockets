package dev.noemontes.server.chat.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "users")
@Data
public class UserModel {
    
	@Id
    private String  uuid;

    private String name;
    private String lastName;

    @Indexed(unique = true, sparse = true)
    private String email;
    private String password;
	private Boolean connected = false;
	
	private String sessionId;

	@DBRef
    private List<UserModel> contacts;

    @CreatedDate
    private Date createdAt;
    
    
    public void addContact(UserModel contact) {
		if(contacts == null) {
			contacts = new ArrayList<UserModel>();
		}
		
		contacts.add(contact);
	}
    
    public Boolean existsContact(String contactUuid) {
    	if(contacts!=null && contacts.size()>0) {
    		for(UserModel contact : contacts) {
    			if(contact.getUuid().equals(contactUuid)) {
    				return true;
    			}
    		}
    	}
    	
    	return false;
    }
}
