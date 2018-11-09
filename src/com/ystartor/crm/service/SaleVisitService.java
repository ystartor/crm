package com.ystartor.crm.service;

import com.ystartor.crm.domain.PageBean;
import com.ystartor.crm.domain.SaleVisit;
import org.hibernate.criterion.DetachedCriteria;

public interface SaleVisitService {
    PageBean<SaleVisit> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

    void save(SaleVisit saleVisit);
}
