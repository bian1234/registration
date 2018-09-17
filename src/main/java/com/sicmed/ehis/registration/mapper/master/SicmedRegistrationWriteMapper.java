package com.sicmed.ehis.registration.mapper.master;

import com.sicmed.ehis.registration.entity.SicmedRegistration;

public interface SicmedRegistrationWriteMapper {
    int deleteByPrimaryKey(String id);

    int insert(SicmedRegistration record);

    int insertSelective(SicmedRegistration record);

    SicmedRegistration selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SicmedRegistration record);

    int updateByPrimaryKey(SicmedRegistration record);
}