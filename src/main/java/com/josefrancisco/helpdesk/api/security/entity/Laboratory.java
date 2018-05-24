package com.josefrancisco.helpdesk.api.security.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Laboratory {

    @Id
    @Setter
    @Getter
    private String id;

    @Setter
    @Getter
    private String name;

}
