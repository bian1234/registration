package com.sicmed.ehis.registration.mapper.slaver;

import com.sicmed.ehis.registration.base.BaseReadMapper;
import com.sicmed.ehis.registration.entity.SicmedNum;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SicmedNumReadMapper   extends BaseReadMapper<SicmedNum>{
    @Override
    SicmedNum selectById(String id);

    @Override
    List<SicmedNum> selectByParams(SicmedNum sicmedNum);
}