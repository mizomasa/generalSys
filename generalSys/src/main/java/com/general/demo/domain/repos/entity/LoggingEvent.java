package com.general.demo.domain.repos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name="LOGGING_EVENT")
public class LoggingEvent {
    @Id
    @Column(name="event_id")
    private Long eventId;

    @Column(name="timestmp")
    private Long timestmp;

    @Column(name="formattedMessage")
    private String message;

    @Column(name="levelString")
    private String level;

    @Column(name="callerClass")
    private String className;

    @Column(name="callerMethod")
    private String methodName;

    @Column(name="callerLine")
    private char line;
}
