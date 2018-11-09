package com.ystartor.crm.service.impl;

import com.ystartor.crm.dao.CustomerDao;
import com.ystartor.crm.domain.Customer;
import com.ystartor.crm.domain.PageBean;
import com.ystartor.crm.service.CustomerService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class CustomerServiceImpl implements CustomerService {
    private CustomerDao customerDao;

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public void save(Customer customer) {
        customerDao.save(customer);
    }

    @Override
    public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage,Integer pageSize) {
        PageBean<Customer> pageBean = new PageBean<Customer>();
        //封装当前页
        pageBean.setCurrPage(currPage);
        pageBean.setPageSize(pageSize);
        Integer totalCount =  customerDao.findCount(detachedCriteria);
        pageBean.setTotalCount(totalCount);
        Double tc = totalCount.doubleValue();
        Double num = Math.ceil(tc/pageSize);
        pageBean.setTotalPage(num.intValue());
        Integer begin = (currPage-1)*pageSize;
        List<Customer> list = customerDao.findAll(detachedCriteria,begin,pageSize);
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public Customer findById(Long cust_id) {
        return customerDao.findById(cust_id);
    }

    @Override
    public void delete(Customer customer) {
        customerDao.delete(customer);
    }

    @Override
    public void update(Customer customer) {
        customerDao.update(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }
}
