package com.sicmed.ehis.registration.mapper.master;

import com.sicmed.ehis.registration.entity.SicmedRegistered;

public interface SicmedRegisteredWriteMapper {
    int deleteByPrimaryKey(String id);

    int insert(SicmedRegistered record);

    int insertSelective(SicmedRegistered record);

    SicmedRegistered selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SicmedRegistered record);

    int updateByPrimaryKey(SicmedRegistered record);
}