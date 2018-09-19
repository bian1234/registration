package com.sicmed.ehis.registration.controller;

import com.sicmed.ehis.registration.base.BaseController;
import com.sicmed.ehis.registration.base.Constant;
import com.sicmed.ehis.registration.base.util.AppParameterSimulation;
import com.sicmed.ehis.registration.bean.RegisteredBean;
import com.sicmed.ehis.registration.entity.SicmedNum;
import com.sicmed.ehis.registration.entity.SicmedPatient;
import com.sicmed.ehis.registration.entity.SicmedRegistered;
import com.sicmed.ehis.registration.service.SicmedNumService;
import com.sicmed.ehis.registration.service.SicmedPatientService;
import com.sicmed.ehis.registration.service.SicmedRegisteredService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sicmed.ehis.registration.base.util.DateTimeUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/9/17 18:57
 * @Todo:
 */
@Controller
@RequestMapping("/ehis/sicmedRegistered")
public class SicmedRegisteredController  extends BaseController {


    @Autowired
    private SicmedRegisteredService sicmedRegisteredService;

    @Autowired
    private SicmedPatientService  sicmedPatientService;

    @Autowired
    private SicmedNumService sicmedNumService;

    /**
     *  这是假数据，以后从缓存中获取
     */
    private static  int pediatricResidue = 5;   //儿科
    private static  int surgicalResidue = 5;   //外科

    /**
     *@Author:      ykbian
     *@date_time:   2018/9/17 18:59
     *@Description: 挂号
     *@param:
    */
    @ResponseBody
    @PostMapping("insert")
    public Map insert(RegisteredBean registeredBean){

        /**
         *  根据科室和医生信息查询号源余量，没号的话直接返回
         */
        //根据身份证号码查询患者信息，没有的话创建患者
        /**
         *   此处有bug  关于身份证号的
         */
        SicmedPatient sicmedPatient = sicmedPatientService.selectByCard(registeredBean.getPatientCard());
        String patientId = null;
        if (sicmedPatient != null){
            patientId = sicmedPatient.getId();
        }else {
            SicmedPatient patient = new SicmedPatient();
            patient.setPatientTel(registeredBean.getPatientTel());
            patient.setPatientCard(registeredBean.getPatientCard());
//            ===========================患者编号生成开始================
//            当前系统时间+每日的挂号顺序
//            患者编号 getDateStr
            StringBuffer patientNumber = new StringBuffer() ;
            String str = DateTimeUtil.getDateStr();
            patientNumber.append(str);
            SicmedNum sno = sicmedNumService.selectById(Constant.PATIENT_NUM_ID);

            Date date = sno.getUpdateDate();
            //如果是当日第一个患者，则编号为0001，否则直接加一
            if(DateTimeUtil.getDayBeginDate(date)){
                sno.setNumber("1");
            }
            String s = sno.getNumber();
            // 不够四位数的话前面加"0"
            int i = 4 - s.length();
            for (int j = 0; j < i; j++) {
                patientNumber.append(0);
            }
            patientNumber.append(s);
            Integer integer = Integer.parseInt(s);
            integer = integer +1;
            sno.setNumber(integer.toString());
            sno.setUpdateDate(new Date());
            sicmedNumService.updateSelective(sno);
//            ===========================患者编号生成结束================

            patient.setPatientNumber(patientNumber.toString());
            patient.setPatientName(registeredBean.getPatientName());
            patient.setPatientAddress(registeredBean.getPatientAddress());
            patient.setPatientSex(registeredBean.getPatientSex());
            patient.setPatientAge(registeredBean.getPatientAge());
            patient.setMedicareNo(registeredBean.getMedicareNo());
            patient.setMedicareType(registeredBean.getMedicareType());
            patient.setDelFlag(Constant.DEL_FLAG_USABLE);
            patient.setCreateDate(new Date());
            patient.setCreateUser(getToken());
            if (sicmedPatientService.insertSelective(patient) < 1){
                return insertFailedResponse();
            }
            SicmedPatient sp = sicmedPatientService.selectByCard(registeredBean.getPatientCard());
            patientId = sp.getId();
        }
        SicmedRegistered sicmedRegistered = new SicmedRegistered();
        sicmedRegistered.setPatientId(patientId);
        sicmedRegistered.setBranchId(registeredBean.getBranchId());
        sicmedRegistered.setDoctorId(registeredBean.getDoctorId());
        sicmedRegistered.setRegisteredPrice(registeredBean.getRegisteredPrice());
        sicmedRegistered.setRegistrationType(registeredBean.getRegisteredTypeId());
        //删除状态
        sicmedRegistered.setDelFlag(Constant.DEL_FLAG_USABLE);
        // 获取创建者信息
        sicmedRegistered.setCreateDate(new Date());
        sicmedRegistered.setCreateUser(getToken());
        //未缴费
        sicmedRegistered.setPriceStatus(Constant.PRICE_NOT_PAY);
        //正常挂号
        sicmedRegistered.setRegisteredStatus(Constant.PATIENT_REGISTERED_CURING);
        //就诊时间-----当前时间到今日24时

        sicmedRegistered.setRegisteredBeginDate(new Date());
        sicmedRegistered.setRegisteredEndDate(DateTimeUtil.getCureEndDate());
        if (sicmedRegisteredService.insertSelective(sicmedRegistered) > 0){
            /**
             *  相对应的科室号院减一
             */
            return insertSuccseeResponse();
        }
        return insertFailedResponse();
    }


    /**
     *@Author:      ykbian
     *@date_time:   2018/9/18 9:30
     *@Description: 患者改号
     *@param:
    */
    @ResponseBody
    @PostMapping("registeredChange")
    public Map registeredChange(SicmedRegistered sicmedRegistered){
        /**
         *  从缓存中查询剩余号源数量 ，如果号源数量不足，则改号失败
         */

       // 改变挂号信息中的科室信息
        sicmedRegistered.setUpdateDate(new Date());
        sicmedRegistered.setUpdateUser(getToken());
        if (sicmedRegisteredService.updateSelective(sicmedRegistered) > 0){
            /**
             *  相应的号源信息加一 和 减一
             */
            return updateSuccseeResponse();
        }
        return updateFailedResponse();
    }



    /**
     *@Author:      ykbian
     *@date_time:   2018/9/18 9:35
     *@Description:  患者退号
     *@param:
    */
    @ResponseBody
    @PostMapping("registeredRefund")
    public Map registeredRefund(String registeredId){
        // 改变挂号信息中的挂号状态，
        SicmedRegistered sicmedRegistered = sicmedRegisteredService.selectById(registeredId);
        //找不到这个挂号信息的话直接返回
        if (sicmedRegistered == null){
           return deleteFailedResponse();
        }
        sicmedRegistered.setDelFlag(Constant.DEL_FLAG_DISUSABLE);
        sicmedRegistered.setRegisteredStatus(Constant.PATIENT_REGISTERED_RETURN);
        sicmedRegistered.setDeleteUser(getToken());
        sicmedRegistered.setDeleteDate(new Date());
        if (sicmedRegisteredService.updateSelective(sicmedRegistered) > 0){
            /**
             *   退出的号源信息加一
             */
            return deleteSuccseeResponse();
        }
        return deleteFailedResponse();
    }



    /**
     *@Author:      ykbian
     *@date_time:   2018/9/18 10:28
     *@Description: 可以改号的患者列表(正常挂号的患者)
     *@param:
    */
    @ResponseBody
    @GetMapping("allChangePatientes")
    public Map allChangePatientes(){
        List<RegisteredBean> registeredBeans = new ArrayList<>();
        //创建查询条件
        SicmedRegistered sicmedRegistered = new SicmedRegistered();
        sicmedRegistered.setDelFlag(Constant.DEL_FLAG_USABLE);
        sicmedRegistered.setRegisteredStatus(Constant.PATIENT_REGISTERED_CURING);
        List<SicmedRegistered> registereds = sicmedRegisteredService.selectByParams2(sicmedRegistered);
        if (registereds == null){
            return queryEmptyResponse();
        }
        for (SicmedRegistered registered:registereds) {
            RegisteredBean bean = new RegisteredBean();
            SicmedPatient sicmedPatient = sicmedPatientService.selectById(registered.getPatientId());
            bean.setPatientName(sicmedPatient.getPatientName());
            bean.setPatientNumber(sicmedPatient.getPatientNumber());
            bean.setPatientSex(sicmedPatient.getPatientSex());
            bean.setPatientAge(sicmedPatient.getPatientAge());
            bean.setBranchId(registered.getBranchId());
            bean.setDoctorId(registered.getDoctorId());
            bean.setRegisteredStatus(registered.getRegisteredStatus());
            registeredBeans.add(bean);
        }
        return querySuccessResponse(registeredBeans);
    }


    /**
     *@Author:      ykbian
     *@date_time:   2018/9/18 10:31
     *@Description: 可以退号的患者列表（正常挂号和改号的患者）
     *@param:
    */
    @ResponseBody
    @GetMapping("allRefundPatientes")
    public Map allRefundPatientes(){
        List<RegisteredBean> registeredBeans = new ArrayList<>();
        //创建查询条件
        SicmedRegistered sicmedRegistered = new SicmedRegistered();
        sicmedRegistered.setDelFlag(Constant.DEL_FLAG_USABLE);
        sicmedRegistered.setRegisteredStatus(Constant.PATIENT_REGISTERED_CURING);
        List<SicmedRegistered> registereds = sicmedRegisteredService.selectByParams2(sicmedRegistered);
        if (registereds == null){
            return queryEmptyResponse();
        }
        for (SicmedRegistered registered:registereds) {
            RegisteredBean bean = new RegisteredBean();
            SicmedPatient sicmedPatient = sicmedPatientService.selectById(registered.getPatientId());
            bean.setPatientName(sicmedPatient.getPatientName());
            bean.setPatientNumber(sicmedPatient.getPatientNumber());
            bean.setPatientSex(sicmedPatient.getPatientSex());
            bean.setPatientAge(sicmedPatient.getPatientAge());
            bean.setBranchId(registered.getBranchId());
            bean.setDoctorId(registered.getDoctorId());
            bean.setRegisteredStatus(registered.getRegisteredStatus());
            registeredBeans.add(bean);
        }
        return querySuccessResponse(registeredBeans);
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/9/19 13:11
     *@Description: 按照患者编号查询患者的挂号信息
     *@param:
    */
    @ResponseBody
    @GetMapping("selectByPatientNO")
    public Map selectByPatientNO(String patientNumber){
        //创建返回对象
        List<RegisteredBean> registeredBeans = new ArrayList<>();
        // 根据患者编号查询患者信息
        SicmedPatient sicmedPatient = sicmedPatientService.selectByNumber(patientNumber);
        //患者信息为空
        if (sicmedPatient == null){
            return queryEmptyResponse();
        }
        // 根据患者id查询挂号信息
        SicmedRegistered sicmedRegistered = new SicmedRegistered();
        sicmedRegistered.setPatientId(sicmedPatient.getId());
        sicmedRegistered.setDelFlag(Constant.DEL_FLAG_USABLE);
        List<SicmedRegistered> sicmedRegistereds = sicmedRegisteredService.selectByParams(sicmedRegistered);
        // 患者的挂号信息为空
        if (sicmedRegistereds.isEmpty()){
            return queryEmptyResponse();
        }
        for (SicmedRegistered registered:sicmedRegistereds) {
            RegisteredBean bean = new RegisteredBean();
            //挂号部分
            bean.setRegisteredStatus(registered.getRegisteredStatus());
            bean.setDoctorId(registered.getDoctorId());
            bean.setBranchId(registered.getBranchId());
            bean.setRegisteredPrice(registered.getRegisteredPrice());
            bean.setRegisteredTypeId(registered.getRegistrationType());
            bean.setId(registered.getId());
            // 患者信息部分
            bean.setPatientName(sicmedPatient.getPatientName());
            bean.setPatientNumber(sicmedPatient.getPatientNumber());
            bean.setPatientAge(sicmedPatient.getPatientAge());
            bean.setPatientCard(sicmedPatient.getPatientCard());
            bean.setPatientAddress(sicmedPatient.getPatientAddress());
            bean.setPatientTel(sicmedPatient.getPatientTel());
            bean.setPatientAddress(sicmedPatient.getPatientAddress());
            registeredBeans.add(bean);
        }
        return querySuccessResponse(registeredBeans);
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/9/19 13:43
     *@Description: 查询挂号记录(0 正常 1改号   2退号   3正常结束)
     *@param:
    */
    @ResponseBody
    @GetMapping("registeredRecord")
    public Map registeredRecord(String status,String beginDate,String endDate){
        List<RegisteredBean> registeredBeans  = new ArrayList<>();
        // 默认查询的时间范围是当天0时到现在
        Date startDate = DateTimeUtil.getDayBeginDate();
        Date endDate1 = DateTimeUtil.getTime();
        // 如果前端的起始时间不为空，则按照前端的起始时间查询
        if(beginDate != "" && beginDate !=null){
            startDate = DateTimeUtil.getDate(beginDate);
        }
        // 截止时间不为空的话  按照前端的时间进行查询
        if(!AppParameterSimulation.isEmpty(endDate)){
            endDate1 = DateTimeUtil.getDate(endDate);
        }
        SicmedRegistered sicmedRegistered = new SicmedRegistered();
        // 给查询条件赋值
        sicmedRegistered.setRegisteredBeginDate(startDate);
        sicmedRegistered.setRegisteredEndDate(endDate1);
        List<SicmedRegistered> sicmedRegistereds = null;
        switch (Integer.parseInt(status)){
            // 挂号
            case 0:
                sicmedRegistered.setCreateUser(getToken());
                sicmedRegistered.setRegisteredStatus(Constant.PATIENT_REGISTERED_CURING);
                sicmedRegistereds = sicmedRegisteredService.registerRecord(sicmedRegistered);
                break;
            // 改号
            case 1:
                sicmedRegistered.setUpdateUser(getToken());
                sicmedRegistered.setRegisteredStatus(Constant.PATIENT_REGISTERED_EXCHANGE);
                sicmedRegistereds = sicmedRegisteredService.exchangeRecord(sicmedRegistered);
                break;
            // 退号
            case 2:
                sicmedRegistered.setDeleteUser(getToken());
                sicmedRegistered.setRegisteredStatus(Constant.PATIENT_REGISTERED_RETURN);
                sicmedRegistereds = sicmedRegisteredService.returnRecord(sicmedRegistered);
                break;
            // 正常结束====
//            case 3:
//                sicmedRegistered.setRegisteredStatus(Constant.PATIENT_REGISTERED_OVER);
            default:
                break;
        }
        if (sicmedRegistereds.isEmpty()){
            return queryEmptyResponse();
        }
        //遍历挂号信息
        for (SicmedRegistered registered:sicmedRegistereds) {
            RegisteredBean bean = new RegisteredBean();
            // 根据挂号信息查询出患者信息
            SicmedPatient sicmedPatient = sicmedPatientService.selectById(registered.getPatientId());
            //患者信息部分
            bean.setPatientNumber(sicmedPatient.getPatientNumber());
            bean.setPatientName(sicmedPatient.getPatientName());
            bean.setPatientTel(sicmedPatient.getPatientTel());
            bean.setPatientAddress(sicmedPatient.getPatientAddress());
            bean.setPatientAge(sicmedPatient.getPatientAge());
            bean.setPatientSex(sicmedPatient.getPatientSex());
            bean.setPatientCard(sicmedPatient.getPatientCard());
            //挂号信息部分
            bean.setRegisteredStatus(registered.getRegisteredStatus());
            bean.setRegisteredTypeId(registered.getRegistrationType());
            bean.setRegisteredPrice(registered.getRegisteredPrice());
            bean.setBranchId(registered.getBranchId());
            bean.setDoctorId(registered.getDoctorId());
            bean.setId(registered.getId());
            registeredBeans.add(bean);
        }
        return querySuccessResponse(registeredBeans);
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/9/19 15:52
     *@Description:  统计挂号，改号和退号数据（人数和金额）
     *@param:
    */



    /**
     *@Author:      ykbian
     *@date_time:   2018/9/19 15:49
     *@Description:  医生查询今日安排的患者列表
     *@param:
    */
    @ResponseBody
    @GetMapping("myPatients")
    public Map myPatients(String branch,String doctor,String beginDate,String endDate){
       //查询条件   已经缴费  科室和医生信息相符
        List<RegisteredBean> registeredBeans = new ArrayList<>();
        SicmedRegistered sicmedRegistered = new SicmedRegistered();
        sicmedRegistered.setPriceStatus(Constant.PRICE_EXCHARGE);
        Date startDate = DateTimeUtil.getDayBeginDate();
        Date endDate1 = DateTimeUtil.getDayEndDate();
        // 如果前端的起始时间不为空，则按照前端的起始时间查询
        if(beginDate != "" && beginDate !=null){
            startDate = DateTimeUtil.getDate(beginDate);
        }
        // 截止时间不为空的话  按照前端的时间进行查询
        if(!AppParameterSimulation.isEmpty(endDate)){
            endDate1 = DateTimeUtil.getDate(endDate);
        }
        /**
         *   这里的科室和医生信息应该根据当前登录信息进行获取
         */
        sicmedRegistered.setDoctorId(doctor);
        sicmedRegistered.setBranchId(branch);
        sicmedRegistered.setRegisteredBeginDate(startDate);
        sicmedRegistered.setRegisteredEndDate(endDate1);
        List<SicmedRegistered> sicmedRegistereds = sicmedRegisteredService.selectByParams(sicmedRegistered);
        if (sicmedRegistereds.isEmpty()){
            return queryEmptyResponse();
        }
        //遍历挂号信息
        for (SicmedRegistered registered:sicmedRegistereds) {
            RegisteredBean bean = new RegisteredBean();
            // 根据挂号信息查询出患者信息
            SicmedPatient sicmedPatient = sicmedPatientService.selectById(registered.getPatientId());
            //患者信息部分
            bean.setPatientNumber(sicmedPatient.getPatientNumber());
            bean.setPatientName(sicmedPatient.getPatientName());
            bean.setPatientTel(sicmedPatient.getPatientTel());
            bean.setPatientAddress(sicmedPatient.getPatientAddress());
            bean.setPatientAge(sicmedPatient.getPatientAge());
            bean.setPatientSex(sicmedPatient.getPatientSex());
            bean.setPatientCard(sicmedPatient.getPatientCard());
            //挂号信息部分
            bean.setRegisteredStatus(registered.getRegisteredStatus());
            bean.setRegisteredTypeId(registered.getRegistrationType());
            bean.setRegisteredPrice(registered.getRegisteredPrice());
            bean.setBranchId(registered.getBranchId());
            bean.setDoctorId(registered.getDoctorId());
            bean.setId(registered.getId());
            registeredBeans.add(bean);
        }
        return querySuccessResponse(registeredBeans);
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/9/19 15:50
     *@Description:  统计医生今日的诊疗数据（人数）
     *@param:
    */


    /**
     *@Author:      ykbian
     *@date_time:   2018/9/19 15:51
     *@Description: 医生看完病以后结束挂号流程
     *@param:
    */
    @ResponseBody
    @PostMapping("finishCure")
    public Map finishCure(String registeredID){
        //获取医生信息
        SicmedRegistered sicmedRegistered = new SicmedRegistered();
        //只有派给本科室的订单，医生才有权限结束治疗
        sicmedRegistered.setDoctorId(getToken());
        sicmedRegistered.setRegisteredStatus(Constant.PATIENT_REGISTERED_OVER);
        sicmedRegistered.setId(registeredID);
        if (sicmedRegisteredService.updateSelective(sicmedRegistered) > 0) {
            return updateSuccseeResponse();
        }
        return updateFailedResponse();
    }
}
