package com.general.demo.domain.model.system;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SystemSetting {

    @NotBlank
    private String id;

    @NotBlank
    private String subId;

    @NotBlank
    private String name;

    @NotBlank
    private String value;

    private String remark;

    private LocalDateTime lastUpdateDate;
    private String lastUpdateUser;

    public boolean isNew() {
        return  lastUpdateDate == null;
    }

}
