package com.sicmed.ehis.registration.mapper.slaver;

import com.sicmed.ehis.registration.base.BaseReadMapper;
import com.sicmed.ehis.registration.entity.SicmedRegisteredType;

import java.util.List;

public interface SicmedRegisteredTypeReadMapper extends BaseReadMapper<SicmedRegisteredType>{

    @Override
    SicmedRegisteredType selectById(String id);

    @Override
    List<SicmedRegisteredType> selectByParams(SicmedRegisteredType sicmedRegisteredType);
}