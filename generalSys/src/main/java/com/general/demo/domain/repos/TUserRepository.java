package com.general.demo.domain.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.general.demo.domain.repos.entity.T_User;

@Repository
public interface TUserRepository extends JpaRepository<T_User,String> {
    T_User findByUserId(String id);
}