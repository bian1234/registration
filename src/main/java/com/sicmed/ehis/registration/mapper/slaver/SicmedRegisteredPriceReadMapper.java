package com.sicmed.ehis.registration.mapper.slaver;

import com.sicmed.ehis.registration.entity.SicmedRegisteredPrice;

public interface SicmedRegisteredPriceReadMapper {
    int deleteByPrimaryKey(String id);

    int insert(SicmedRegisteredPrice record);

    int insertSelective(SicmedRegisteredPrice record);

    SicmedRegisteredPrice selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SicmedRegisteredPrice record);

    int updateByPrimaryKey(SicmedRegisteredPrice record);
}