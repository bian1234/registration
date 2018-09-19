package com.sicmed.ehis.registration.service;

import com.sicmed.ehis.registration.base.BaseService;
import com.sicmed.ehis.registration.entity.SicmedNum;

import java.util.List;

/**
 * @Author: ykbian
 * @Date: 2018/9/19 10:33
 * @Todo:
 */

public interface SicmedNumService  extends BaseService<SicmedNum> {

    @Override
    int insertSelective(SicmedNum sicmedNum);

    @Override
    int updateSelective(SicmedNum sicmedNum);

    @Override
    SicmedNum selectById(String id);

    @Override
    List<SicmedNum> selectByParams(SicmedNum sicmedNum);

    @Override
    int deleteById(String id);
}
