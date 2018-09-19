package com.sicmed.ehis.registration.mapper.master;

import com.sicmed.ehis.registration.base.BaseWriteMapper;
import com.sicmed.ehis.registration.entity.SicmedRegisteredPrice;
import org.springframework.stereotype.Repository;

@Repository
public interface SicmedRegisteredPriceWriteMapper extends BaseWriteMapper<SicmedRegisteredPrice>{

    @Override
    int insertSelective(SicmedRegisteredPrice sicmedRegisteredPrice);

    @Override
    int updateSelective(SicmedRegisteredPrice sicmedRegisteredPrice);

    @Override
    int deleteById(String id);
}