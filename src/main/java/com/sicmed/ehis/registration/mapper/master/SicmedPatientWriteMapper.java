package com.sicmed.ehis.registration.mapper.master;

import com.sicmed.ehis.registration.base.BaseWriteMapper;
import com.sicmed.ehis.registration.entity.SicmedPatient;
import org.springframework.stereotype.Repository;

@Repository
public interface SicmedPatientWriteMapper extends BaseWriteMapper<SicmedPatient>{
    @Override
    int insertSelective(SicmedPatient sicmedPatient);

    @Override
    int updateSelective(SicmedPatient sicmedPatient);

    @Override
    int deleteById(String id);
}