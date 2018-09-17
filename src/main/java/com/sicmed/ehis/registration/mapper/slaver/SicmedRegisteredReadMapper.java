package com.sicmed.ehis.registration.mapper.slaver;

import com.sicmed.ehis.registration.entity.SicmedRegistered;

public interface SicmedRegisteredReadMapper {
    int deleteByPrimaryKey(String id);

    int insert(SicmedRegistered record);

    int insertSelective(SicmedRegistered record);

    SicmedRegistered selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SicmedRegistered record);

    int updateByPrimaryKey(SicmedRegistered record);
}