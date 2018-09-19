package com.sicmed.ehis.registration.mapper.master;

import com.sicmed.ehis.registration.entity.SicmedRegisteredSource;

public interface SicmedRegisteredSourceWriteMapper {
    int deleteByPrimaryKey(String id);

    int insert(SicmedRegisteredSource record);

    int insertSelective(SicmedRegisteredSource record);

    SicmedRegisteredSource selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SicmedRegisteredSource record);

    int updateByPrimaryKey(SicmedRegisteredSource record);
}