package com.josefrancisco.helpdesk.api.security.entity;

import com.josefrancisco.helpdesk.api.security.enums.ProfileEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;

@Document
public class User {

    @Id
    @Setter
    @Getter
    private String id;

    @NotBlank(message = "Name is required")
    @Setter
    @Getter
    private String name;

    @Indexed(unique = true)
    @NotBlank(message = "Email required")
    @Email(message = "Email invalid")
    @Setter
    @Getter
    private String email;

    @NotBlank(message = "Password required")
    @Size(min = 6)
    @Setter
    @Getter
    private String password;

    @Setter
    @Getter
    private ProfileEnum profile;

    @Setter
    @Getter
    private String image;

}
