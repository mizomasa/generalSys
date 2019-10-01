package com.general.demo.domain.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.general.demo.domain.repos.entity.M_User;

@Repository
public interface MUserRepository extends JpaRepository<M_User,String> {
    M_User findByUserId(String id);
}