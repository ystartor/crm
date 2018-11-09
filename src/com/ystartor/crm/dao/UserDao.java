package com.ystartor.crm.dao;

import com.ystartor.crm.domain.User;

public interface UserDao extends BaseDao<User>{

    User login(User user);
}
