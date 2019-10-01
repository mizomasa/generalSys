package com.general.demo.domain.model.system;

import lombok.Data;

@Data
public class Logging {

    private Long eventId;
    private Long timestmp;
    private String message;
    private String level;
    private String className;
    private String methodName;
    private char line;
}
