package com.general.demo.domain.repos.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.Data;

@Entity(name="t_user_role")
@IdClass(T_UserRolePK.class)
@Data
public class T_UserRole {

    @Id
    public String userId;
    @Id
    public String roleId;
}
