package com.general.demo.domain.repos.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class M_User {
    @Id
    private String userId;
    private String password;
    private String firstName;
    private String lastName;
    private boolean deleted;
    private boolean locked;
    private String remark;
    private LocalDateTime lastUpdateDate;
    private String lastUpdateUser;

    public void setUpdateUser(String id) {
        this.lastUpdateDate = LocalDateTime.now();
        this.lastUpdateUser= id;
    }
}
