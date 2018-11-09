package com.ystartor.crm.dao;

import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
    void delete(T t);
    void update(T t);
    void save(T t);

    T findById(Serializable id);

    List<T> findAll();

    Integer findCount(DetachedCriteria detachedCriteria);

    List<T> findAll(DetachedCriteria detachedCriteria,Integer begin,Integer pageSize);

}
