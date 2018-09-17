package com.sicmed.ehis.registration.mapper.master;

import com.sicmed.ehis.registration.entity.SicmedRegisteredPrice;

public interface SicmedRegisteredPriceWriteMapper {
    int deleteByPrimaryKey(String id);

    int insert(SicmedRegisteredPrice record);

    int insertSelective(SicmedRegisteredPrice record);

    SicmedRegisteredPrice selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SicmedRegisteredPrice record);

    int updateByPrimaryKey(SicmedRegisteredPrice record);
}