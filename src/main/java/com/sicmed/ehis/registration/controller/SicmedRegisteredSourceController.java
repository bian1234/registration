package com.sicmed.ehis.registration.controller;

import com.sicmed.ehis.registration.base.BaseController;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

/**
 * @Author: ykbian
 * @Date: 2018/9/19 11:35
 * @Todo:  号源信息控制
 */
@Controller
public class SicmedRegisteredSourceController  extends BaseController {


    /**
     *@Author:      ykbian
     *@date_time:   2018/9/19 11:36
     *@Description:  每日凌晨4时更新号源信息
     *@param:
    */
    @Scheduled(cron="0 0 4 * * ? ")
    public void initialize(){
        //读取每个科室每个类型的号源信息，存储在缓存中

    }



}
