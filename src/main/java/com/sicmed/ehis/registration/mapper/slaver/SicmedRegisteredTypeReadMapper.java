package com.sicmed.ehis.registration.mapper.slaver;

import com.sicmed.ehis.registration.entity.SicmedRegisteredType;

public interface SicmedRegisteredTypeReadMapper {
    int deleteByPrimaryKey(String id);

    int insert(SicmedRegisteredType record);

    int insertSelective(SicmedRegisteredType record);

    SicmedRegisteredType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SicmedRegisteredType record);

    int updateByPrimaryKey(SicmedRegisteredType record);
}