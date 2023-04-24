package dev.noemontes.server.chat.model;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "users")
@Data
public class UserModel {
    
	@Id
    private String  uuid;

    private String name;
    private String lastName;

    @Indexed(unique = true)
    private String email;
    private String password;
    
    private Boolean connected;

    @CreatedDate
    private Date createdAt;
}
