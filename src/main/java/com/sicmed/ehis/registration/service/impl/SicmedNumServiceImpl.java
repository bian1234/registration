package com.sicmed.ehis.registration.service.impl;

import com.sicmed.ehis.registration.entity.SicmedNum;
import com.sicmed.ehis.registration.mapper.master.SicmedNumWriteMapper;
import com.sicmed.ehis.registration.mapper.slaver.SicmedNumReadMapper;
import com.sicmed.ehis.registration.service.SicmedNumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: ykbian
 * @Date: 2018/9/19 10:34
 * @Todo:
 */
@Service
public class SicmedNumServiceImpl  implements SicmedNumService {


    @Autowired
    private SicmedNumReadMapper sicmedNumReadMapper;
    @Autowired
    private SicmedNumWriteMapper sicmedNumWriteMapper;
    @Override
    public int insertSelective(SicmedNum sicmedNum) {
        return 0;
    }

    @Override
    public int updateSelective(SicmedNum sicmedNum) {
        return sicmedNumWriteMapper.updateSelective(sicmedNum);
    }

    @Override
    public SicmedNum selectById(String id) {
        return sicmedNumReadMapper.selectById(id);
    }

    @Override
    public List<SicmedNum> selectByParams(SicmedNum sicmedNum) {
        return null;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }
}
