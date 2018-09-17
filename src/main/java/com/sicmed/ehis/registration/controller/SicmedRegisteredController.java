package com.sicmed.ehis.registration.controller;

import com.sicmed.ehis.registration.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/9/17 18:57
 * @Todo:
 */
@Controller
@RequestMapping("/ehis/sicmedRegistered")
public class SicmedRegisteredController  extends BaseController {

    /**
     *@Author:      ykbian
     *@date_time:   2018/9/17 18:59
     *@Description: 挂号
     *@param:
    */
    @PostMapping("insert")
    public Map insert(){
        return null;
    }
}
