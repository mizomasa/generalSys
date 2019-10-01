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
import com.general.demo.domain.repos.MUserRepository;
import com.general.demo.domain.repos.entity.M_User;
import com.general.demo.domain.service.UserService;
import com.general.demo.ex.BusinessException;
import com.general.demo.util.Utils;

@Service
public class UserServiceImpl  implements UserService,UserDetailsService {

    @Autowired
    private MUserRepository mUserRepository;

    @Override
    public List<User> findAll() {
        List<M_User> mUsers = mUserRepository.findAll();
        List<User> users = new ArrayList<>();
        for(M_User mUser : mUsers) {
            User user = new User();
            users.add(Utils.copy(mUser,User.class));
        }
        return users;
    }

    @Override
    public User findByUserId(String id) throws BusinessException {
        M_User mUser = mUserRepository.findById(id).orElse(null);
        if(mUser == null)  throw new BusinessException("ユーザが見つかりません。");
        User user = new User();
        BeanUtils.copyProperties(mUser, user);
        return user;
    }

    @Override
    @Transactional
    public User saveFor(User user) throws BusinessException {
        M_User mUser = mUserRepository.findById(user.getUserId()).orElse(null);

        if(user.isNew()) {
            if(mUser != null)throw new BusinessException("既にそのユーザIDは、存在します。");
        }else {
            if(mUser == null )throw new BusinessException("ユーザが削除されています。");
            //TODO:楽観ロックを実装
        }
        mUser = new M_User();
        BeanUtils.copyProperties(user,mUser);

        mUser.setUpdateUser("test");
        mUserRepository.save(mUser);
        BeanUtils.copyProperties(mUser,user);
        return user;
    }

    @Override
    public void deleteByUserId(String id) throws BusinessException {
        M_User mUser = mUserRepository.findById(id).orElse(null);
        if(mUser == null)throw new BusinessException("既にそのユーザIDは、削除されています。");
        mUserRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username == null || "".equals(username)) {
            throw new UsernameNotFoundException("ユーザーIDが未入力です");
        }
        try {
            return findByUserId(username);
        } catch (BusinessException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }
}
