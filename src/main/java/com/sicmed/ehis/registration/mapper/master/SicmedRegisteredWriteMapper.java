package com.sicmed.ehis.registration.mapper.master;

import com.sicmed.ehis.registration.base.BaseWriteMapper;
import com.sicmed.ehis.registration.entity.SicmedRegistered;
import org.springframework.stereotype.Repository;

@Repository
public interface SicmedRegisteredWriteMapper extends BaseWriteMapper<SicmedRegistered>{
    @Override
    int insertSelective(SicmedRegistered sicmedRegistered);

    @Override
    int updateSelective(SicmedRegistered sicmedRegistered);

    @Override
    int deleteById(String id);
}