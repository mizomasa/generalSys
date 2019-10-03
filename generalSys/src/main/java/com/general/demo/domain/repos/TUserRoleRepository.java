package com.general.demo.domain.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.general.demo.domain.repos.entity.T_UserRole;
import com.general.demo.domain.repos.entity.T_UserRolePK;

@Repository
public interface TUserRoleRepository extends JpaRepository<T_UserRole,T_UserRolePK> {

    @Query("select o from t_user_role o where o.userId=:userId order by o.roleId")
    List<T_UserRole> findAllByUserId(@Param("userId") String userId);


    @Query("delete from t_user_role o where o.userId=:userId ")
    void deleteByUserId(@Param("userId") String userId);
}