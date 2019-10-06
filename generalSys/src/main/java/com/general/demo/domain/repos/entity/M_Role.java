package com.general.demo.domain.repos.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class M_Role {

    @Id
    private String roleId;
    private String name;
    private String description;
    private LocalDateTime lastUpdateDate;
    private String lastUpdateUser;

}
