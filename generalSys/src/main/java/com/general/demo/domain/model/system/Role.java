package com.general.demo.domain.model.system;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Role {
    private String roleId;
    private String name;
    private String description;
    private LocalDateTime lastUpdateDate;
    private String lastUpdateUser;
}
