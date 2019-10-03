package com.general.demo.domain.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.general.demo.domain.repos.entity.T_UserRole;

@Repository
public interface TUserRoleRepository extends JpaRepository<T_UserRole,String> {


    @Query("select o.roleId from t_user_role o where o.userId=:userId ")
    List<String> findAllByUserId(@Param("userId") String userId);
}
