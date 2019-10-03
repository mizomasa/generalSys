package com.general.demo.domain.repos.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="t_user_role")
@IdClass(T_UserRolePK.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class T_UserRole {

    @Id
    private String userId;
    @Id
    private String roleId;
}
