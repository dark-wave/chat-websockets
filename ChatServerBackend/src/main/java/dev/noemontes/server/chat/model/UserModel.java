package dev.noemontes.server.chat.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

@Document(collection = "users")
@Data
public class UserModel {
    @Id
    private String userId;
    private String uuid;
    private String name;
    private String lastName;

    @Indexed(unique = true)
    private String email;
    private String password;

    @CreatedBy
    private Date createdAt;
}
