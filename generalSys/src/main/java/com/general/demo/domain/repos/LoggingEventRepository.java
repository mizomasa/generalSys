package com.general.demo.domain.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.general.demo.domain.repos.entity.LoggingEvent;

public interface LoggingEventRepository extends JpaRepository<LoggingEvent,Long>{
    @Query("select o from LOGGING_EVENT o where o.level = :level ")
    List<LoggingEvent> findAllByLevel(@Param("level") String level);

}
