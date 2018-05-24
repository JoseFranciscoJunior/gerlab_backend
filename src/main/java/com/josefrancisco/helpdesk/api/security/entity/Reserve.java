package com.josefrancisco.helpdesk.api.security.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Reserve {

    @Id
    @Setter
    @Getter
    private String id;

    @DBRef(lazy = true)
    @Setter
    @Getter
    private User user;

    @DBRef(lazy = true)
    @Setter
    @Getter
    private User assignedUser;

    @Setter
    @Getter
    private String description;

    @Transient
    @Setter
    @Getter
    private List<ChangeStatus> changes;

}
