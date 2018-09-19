package com.sicmed.ehis.registration.mapper.master;

import com.sicmed.ehis.registration.base.BaseWriteMapper;
import com.sicmed.ehis.registration.entity.SicmedRegisteredType;

public interface SicmedRegisteredTypeWriteMapper  extends BaseWriteMapper<SicmedRegisteredType>{
    @Override
    int insertSelective(SicmedRegisteredType sicmedRegisteredType);

    @Override
    int updateSelective(SicmedRegisteredType sicmedRegisteredType);

    @Override
    int deleteById(String id);
}