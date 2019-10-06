package com.general.demo.domain.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.general.demo.domain.repos.entity.M_Role;

@Repository
public interface MRoleRepository extends JpaRepository<M_Role,String> {
}