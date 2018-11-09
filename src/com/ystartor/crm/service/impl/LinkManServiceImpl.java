package com.ystartor.crm.service.impl;

import com.ystartor.crm.dao.LinkManDao;
import com.ystartor.crm.domain.LinkMan;
import com.ystartor.crm.domain.PageBean;
import com.ystartor.crm.service.LinkManService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class LinkManServiceImpl implements LinkManService {
    private LinkManDao linkManDao;

    public void setLinkManDao(LinkManDao linkManDao) {
        this.linkManDao = linkManDao;
    }

    @Override
    public PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
        PageBean<LinkMan> pageBean = new PageBean<LinkMan>();
        pageBean.setCurrPage(currPage);
        pageBean.setPageSize(pageSize);
        Integer totalCount = linkManDao.findCount(detachedCriteria);
        pageBean.setTotalCount(totalCount);
        Double tc = totalCount.doubleValue();
        Double num = Math.ceil(tc/pageSize);
        pageBean.setTotalPage(num.intValue());
        Integer begin = (currPage - 1)*pageSize;
        List<LinkMan> linkManList = linkManDao.findAll(detachedCriteria,begin,pageSize);
        pageBean.setList(linkManList);
        return pageBean;
    }

    @Override
    public void save(LinkMan linkMan) {
        linkManDao.save(linkMan);
    }

    @Override
    public LinkMan findById(Long lkm_id) {
        return linkManDao.findById(lkm_id);
    }

    @Override
    public void update(LinkMan linkMan) {
        linkManDao.update(linkMan);
    }

    @Override
    public void delete(LinkMan linkMan) {
        linkManDao.delete(linkMan);
    }
}
