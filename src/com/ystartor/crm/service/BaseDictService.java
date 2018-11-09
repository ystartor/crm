package com.ystartor.crm.service;

import com.ystartor.crm.domain.BaseDict;

import java.util.List;

public interface BaseDictService {
    List<BaseDict> findByTypeCode(String dict_type_code);
}
