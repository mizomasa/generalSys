package com.general.demo.domain.repos.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class T_UserRolePK implements Serializable{
    public String userId;
    public String roleId;
}
