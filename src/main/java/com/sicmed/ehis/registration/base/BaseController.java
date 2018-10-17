package com.sicmed.ehis.registration.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/9/17 18:12
 * @Todo:   基础类
 */

public class BaseController {


    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    public Map<String,Object> resultMap ;

    /**
     *   当前登录信息
     */
    protected String getToken() {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        String token = request.getHeader("token");
        return token;
    }

    /**
     * 参数为空返回值
     * @return
     */
    public Map emptyParamResponse(){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","20001");
        resultMap.put("msg","EMPTY_PARAM");
        resultMap.put("result",null);
        return resultMap;
    }

    /**
     * 请求成功返回值
     * @param result
     * @return
     */
    public Map querySuccessResponse(Object result){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","20000");
        resultMap.put("msg","SUCCESS");
        resultMap.put("result",result);
        return resultMap;
    }

    /**
     * 分页查询请求成功返回值
     * @param result
     * @return
     */
    public Map querySuccessResponse(Object result,String count){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","20000");
        resultMap.put("msg","SUCCESS");
        resultMap.put("result",result);
        resultMap.put("count",count);
        return resultMap;
    }
    /**
     * 查询请求结果为空返回值
     * @return
     */
    public Map queryEmptyResponse(){
        resultMap = new LinkedHashMap<>();

        resultMap.put("code","40004");
        resultMap.put("msg","RESULT_EMPTY");
        resultMap.put("result",null);
        return resultMap;
    }
    /**
     * 请求失败返回值
     * @return
     */
    public Map failedResponse(){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","50002");
        resultMap.put("msg","FAILED_EMPTY");
        resultMap.put("result",null);
        return resultMap;
    }

    /**
     * 请求失败返回值
     * @return
     */
    public Map insertFailedResponse(){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","50003");
        resultMap.put("msg","INSERT_FAILED");
        resultMap.put("result",null);
        return resultMap;
    }

    /**
     * 插入请求成功返回值
     * @return
     */
    public Map insertSuccseeResponse(){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","20000");
        resultMap.put("msg","INSERT_SUCCESS");
        resultMap.put("result",null);
        return resultMap;
    }
    /**
     * 插入请求成功返回值
     * @return
     */

    public Map insertSuccseeResponse(Object result){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","20000");
        resultMap.put("msg","INSERT_SUCCESS");
        resultMap.put("result",result);
        return resultMap;
    }

    /**
     * UPDATE成功返回值
     * @return
     */
    public Map updateSuccseeResponse(){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","20000");
        resultMap.put("msg","UPDATE_SUCCESS");
        resultMap.put("result",null);
        return resultMap;
    }

    /**
     * UPDATE成功返回值
     * @param result
     * @return
     */
    public Map updateSuccseeResponse(Object result){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","20000");
        resultMap.put("msg","UPDATE_SUCCESS");
        resultMap.put("result",result);
        return resultMap;
    }

    /**
     * UPDATE请求失败返回值
     * @return
     */
    public Map updateFailedResponse(){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","50004");
        resultMap.put("msg","UPDATE_FAILED");
        resultMap.put("result",null);
        return resultMap;
    }

    /**
     * DELETE成功返回值
     * @return
     */
    public Map deleteSuccseeResponse(){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","20000");
        resultMap.put("msg","DELETE_SUCCESS");
        resultMap.put("result",null);
        return resultMap;
    }

    /**
     * DELETE请求失败返回值
     * @return
     */
    public Map deleteFailedResponse(){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","50005");
        resultMap.put("msg","DELETE_FAILED");
        resultMap.put("result",null);
        return resultMap;
    }

    /**
     *  打印成功
     */
    public Map printSuccess(){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","20000");
        resultMap.put("msg","PRINT_SUCCESS");
        resultMap.put("result",null);
        return resultMap;
    }
}



