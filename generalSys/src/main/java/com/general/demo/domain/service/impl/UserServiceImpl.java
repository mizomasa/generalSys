package com.general.demo.domain.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.general.demo.domain.model.User;
import com.general.demo.domain.repos.TUserRepository;
import com.general.demo.domain.repos.TUserRoleRepository;
import com.general.demo.domain.repos.entity.T_User;
import com.general.demo.domain.service.UserService;
import com.general.demo.ex.BusinessException;

@Service
public class UserServiceImpl  implements UserService,UserDetailsService {

    @Autowired
    private TUserRepository userRepository;

    @Autowired
    private TUserRoleRepository userRoleRepository;

    @Autowired
    PasswordEncoder PasswordEncoder;

    @Override
    public List<User> findAll() throws BusinessException {
        List<T_User> tUsers = userRepository.findAll();
        List<User> users = new ArrayList<>();
        for(T_User tUser : tUsers) {
            User user = findByUserId(tUser.getUserId());
            users.add(user);
        }
        return users;
    }

    @Override
    public User findByUserId(String id) throws BusinessException {
        return (User) loadUserByUsername(id);
    }

    @Override
    @Transactional
    public User saveFor(User user) throws BusinessException {
        Optional<T_User> tUser = userRepository.findById(user.getUserId());
        if(user.isNew()) {
            if (tUser.isPresent())throw new BusinessException("既にそのユーザIDは、存在します。");
        }else {
            tUser.orElseThrow(()->new BusinessException("ユーザが削除されています。"));
            //TODO:楽観ロックを実装
        }
        T_User regitUser = new T_User();
        BeanUtils.copyProperties(user,regitUser);
        user.encodePassword(PasswordEncoder);
        
        regitUser.setUpdateUser("test");
        userRepository.save(regitUser);
        BeanUtils.copyProperties(regitUser, user);
        return user;
    }

    @Override
    @Transactional
    public void deleteByUserId(String id) throws BusinessException {
        Optional<T_User> tUser = userRepository.findById(id);
        tUser.orElseThrow(()->new BusinessException("既にそのユーザIDは、削除されています。"));
        tUser.ifPresent(t->userRepository.deleteById(t.getUserId()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<T_User> tUser = userRepository.findById(username);
        return tUser
                .map(u -> createUser(u, username))
                .orElseThrow(()->new UsernameNotFoundException("user not found"));
    }

    private User createUser(T_User tUser, String username) {
        List<String> userRoles = userRoleRepository.findAllByUserId(username);
        String[] roles = userRoles.toArray(new String[userRoles.size()]);
        List<GrantedAuthority> authorities  =AuthorityUtils.createAuthorityList(roles);
        User user = new User(authorities);
        BeanUtils.copyProperties(tUser,user);
        return user;
    }

}
