package com.sicmed.ehis.registration.mapper.master;

import com.sicmed.ehis.registration.base.BaseWriteMapper;
import com.sicmed.ehis.registration.entity.SicmedNum;
import org.springframework.stereotype.Repository;

@Repository
public interface SicmedNumWriteMapper extends BaseWriteMapper<SicmedNum>{
    @Override
    int insertSelective(SicmedNum sicmedNum);

    @Override
    int updateSelective(SicmedNum sicmedNum);

    @Override
    int deleteById(String id);
}