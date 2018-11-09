package com.ystartor.crm.service.impl;

import com.ystartor.crm.dao.UserDao;
import com.ystartor.crm.domain.User;
import com.ystartor.crm.service.UserService;
import com.ystartor.crm.util.MD5Utils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void regist(User user) {
        //密码加密
        user.setUser_password(MD5Utils.md5(user.getUser_password()));
        user.setUser_state("1");
        //调用dao
        userDao.save(user);
    }

    @Override
    public User login(User user) {
        user.setUser_password(MD5Utils.md5(user.getUser_password()));
        //
        return userDao.login(user);
    }

    @Override
    public List<User> findAllUser() {
        return userDao.findAll();
    }
}
