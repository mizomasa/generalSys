package com.general.demo.domain.service;

import java.util.List;

import com.general.demo.domain.model.User;
import com.general.demo.ex.BusinessException;

public interface UserService {

    List<User> findAll();
    User findByUserId(String id) throws Exception;
    User saveFor(User user) throws BusinessException;
    void deleteByUserId(String id) throws BusinessException;

}
