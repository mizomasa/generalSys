package com.general.demo.domain.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.general.demo.domain.repos.entity.M_System;
import com.general.demo.domain.repos.entity.M_SystemPK;

public interface MSystemRepository extends JpaRepository<M_System,M_SystemPK> {

    @Query("select o from m_system o where o.id = :id ")
    List<M_System> findById(@Param("id") String id);

}

