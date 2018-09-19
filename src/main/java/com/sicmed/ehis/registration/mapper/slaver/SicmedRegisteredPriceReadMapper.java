package com.sicmed.ehis.registration.mapper.slaver;

import com.sicmed.ehis.registration.base.BaseReadMapper;
import com.sicmed.ehis.registration.entity.SicmedRegisteredPrice;

import java.util.List;

public interface SicmedRegisteredPriceReadMapper extends BaseReadMapper<SicmedRegisteredPrice>{
    @Override
    SicmedRegisteredPrice selectById(String id);

    @Override
    List<SicmedRegisteredPrice> selectByParams(SicmedRegisteredPrice sicmedRegisteredPrice);
}