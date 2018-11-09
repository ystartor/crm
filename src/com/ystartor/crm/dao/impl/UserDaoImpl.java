package com.ystartor.crm.dao.impl;

import com.ystartor.crm.dao.UserDao;
import com.ystartor.crm.domain.User;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    public User login(User user) {
        List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code = ? and user_password=?",user.getUser_code(),user.getUser_password());
        if(list.isEmpty()){
           return null;
        }
        return list.get(0);
    }
}
