package com.sicmed.ehis.registration.mapper.master;

import com.sicmed.ehis.registration.base.BaseWriteMapper;
import com.sicmed.ehis.registration.entity.SicmedRegistration;

public interface SicmedRegistrationWriteMapper   extends BaseWriteMapper<SicmedRegistration>{
    @Override
    int insertSelective(SicmedRegistration sicmedRegistration);

    @Override
    int updateSelective(SicmedRegistration sicmedRegistration);

    @Override
    int deleteById(String id);
}