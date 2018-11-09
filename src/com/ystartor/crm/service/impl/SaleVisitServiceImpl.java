package com.ystartor.crm.service.impl;

import com.ystartor.crm.dao.SaleVisitDao;
import com.ystartor.crm.domain.PageBean;
import com.ystartor.crm.domain.SaleVisit;
import com.ystartor.crm.service.SaleVisitService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
public class SaleVisitServiceImpl implements SaleVisitService {
   @Resource(name = "saleVisitDao")
   private SaleVisitDao saleVisitDao;

    @Override
    public PageBean<SaleVisit> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
        PageBean<SaleVisit> pageBean = new PageBean<SaleVisit>();
        pageBean.setCurrPage(currPage);
        pageBean.setPageSize(pageSize);
        Integer totalCount = saleVisitDao.findCount(detachedCriteria);
        pageBean.setTotalCount(totalCount);
        Double tc = totalCount.doubleValue();
        Double num = Math.ceil(tc/pageSize);
        pageBean.setTotalPage(num.intValue());
        Integer begin = (currPage - 1)*pageSize;
        List<SaleVisit> saleVisitList = saleVisitDao.findAll(detachedCriteria,begin,pageSize);
        pageBean.setList(saleVisitList);
        return pageBean;

    }

    @Override
    public void save(SaleVisit saleVisit) {
        saleVisitDao.save(saleVisit);
    }
}
