package com.ystartor.crm.service.impl;

import com.ystartor.crm.dao.BaseDictDao;
import com.ystartor.crm.domain.BaseDict;
import com.ystartor.crm.service.BaseDictService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class BaseDictServiceImpl implements BaseDictService {
    private BaseDictDao baseDictDao;

    public void setBaseDictDao(BaseDictDao baseDictDao) {
        this.baseDictDao = baseDictDao;
    }

    @Override
    public List<BaseDict> findByTypeCode(String dict_type_code) {
        return baseDictDao.findByTypeCode(dict_type_code);
    }
}
