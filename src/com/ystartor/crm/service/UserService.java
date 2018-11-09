package com.ystartor.crm.service;

import com.ystartor.crm.domain.User;

import java.util.List;

public interface UserService {
    void regist(User user);

    User login(User user);

    List<User> findAllUser();
}
