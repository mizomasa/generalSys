package com.general.demo.domain.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.general.demo.domain.repos.entity.T_UserRole;
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
        }

        T_User regitUser = new T_User();
        BeanUtils.copyProperties(user,regitUser);
        user.encodePassword(PasswordEncoder);

        regitUser.setUpdateUser("TODO:ログインユーザにする");


        userRepository.save(regitUser);
        BeanUtils.copyProperties(regitUser, user);
        List<T_UserRole> entities = new ArrayList<>();

        Arrays.asList(user.getRoles())
            .parallelStream()
            .forEach(e-> entities.add(new T_UserRole(user.getUserId(),e)));

        List<T_UserRole> list = userRoleRepository.findAllByUserId(user.getUserId());
        userRoleRepository.deleteInBatch(list);
        userRoleRepository.saveAll(entities);
        userRoleRepository.flush();
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
        User user= tUser
                .map(u -> createUser(u, username))
                .orElseThrow(()->new UsernameNotFoundException("user not found"));
        return user;
    }

    private User createUser(T_User tUser, String username) {
        List<T_UserRole> userRoles = userRoleRepository.findAllByUserId(username);

        String[] roles = new String[userRoles.size()];
        for(int i=0; i<roles.length; i++)
            roles[i] = userRoles.get(i).getRoleId();
        User user = new User(roles);
        BeanUtils.copyProperties(tUser,user);
        return user;
    }

}
