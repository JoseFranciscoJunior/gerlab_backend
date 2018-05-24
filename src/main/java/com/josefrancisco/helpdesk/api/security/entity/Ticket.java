package com.josefrancisco.helpdesk.api.security.entity;

import com.josefrancisco.helpdesk.api.security.enums.PriorityEnum;
import com.josefrancisco.helpdesk.api.security.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
public class Ticket {

    @Id
    @Setter
    @Getter
    private String id;

    @DBRef(lazy = true)
    @Setter
    @Getter
    private User user;

    @Setter
    @Getter
    private Date date;

    @Setter
    @Getter
    private String title;

    @Setter
    @Getter
    private Integer number;

    @Setter
    @Getter
    private StatusEnum status;

    @Setter
    @Getter
    private PriorityEnum priority;

    @DBRef(lazy = true)
    @Setter
    @Getter
    private User assignedUser;

    @Setter
    @Getter
    private String description;

    @Setter
    @Getter
    private String image;

    @Transient
    @Setter
    @Getter
    private List<ChangeStatus> changes;

}
