package com.general.demo.domain.repos.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="m_system")
@IdClass(M_SystemPK.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class M_System {
    @Id
    private String id;
    @Id
    private String subId;
    private String name;
    private String code;
    private String remark;
    private LocalDateTime last_update_date;
    private String last_update_user ;

}
