package com.general.demo.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.general.demo.domain.model.User;
import com.general.demo.domain.repos.TUserRepository;
import com.general.demo.domain.repos.entity.T_User;
import com.general.demo.domain.service.UserService;
import com.general.demo.ex.BusinessException;
import com.general.demo.util.Utils;

@Service
public class UserServiceImpl  implements UserService,UserDetailsService {

    @Autowired
    private TUserRepository userRepository;


    @Override
    public List<User> findAll() {
        List<T_User> tUsers = userRepository.findAll();
        List<User> users = new ArrayList<>();
        for(T_User tUser : tUsers) {
            User user = new User();
            users.add(Utils.copy(tUser,User.class));
        }
        return users;
    }

    @Override
    public User findByUserId(String id) throws BusinessException {
        T_User tUser = userRepository.findById(id).orElse(null);
        if(tUser == null)  throw new BusinessException("ユーザが見つかりません。");
        User user = new User();
        BeanUtils.copyProperties(tUser, user);
        return user;
    }

    @Override
    @Transactional
    public User saveFor(User user) throws BusinessException {
        T_User tUser = userRepository.findById(user.getUserId()).orElse(null);

        if(user.isNew()) {
            if(tUser != null)throw new BusinessException("既にそのユーザIDは、存在します。");
        }else {
            if(tUser == null )throw new BusinessException("ユーザが削除されています。");
            //TODO:楽観ロックを実装
        }
        tUser = new T_User();
        BeanUtils.copyProperties(user,tUser);

        tUser.setUpdateUser("test");
        userRepository.save(tUser);
        BeanUtils.copyProperties(tUser,user);
        return user;
    }

    @Override
    public void deleteByUserId(String id) throws BusinessException {
        T_User tUser = userRepository.findById(id).orElse(null);
        if(tUser == null)throw new BusinessException("既にそのユーザIDは、削除されています。");
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UsernameNotFoundException("未実装");
    }
}
