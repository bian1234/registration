package com.sicmed.ehis.registration.cache.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * @author MaxCoder
 *
 * @date 2017.04.09
 *
 * Redis 服务
 */
@Service
public class RedisSerive {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
//
//    @Autowired
//    private JsonUtil<Doctor> jsonUtil;

    public void set(String key,int val) {
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        String val0 = val+"";
        valueOperations.set(key,val0);
    }



    public int get(String key) {
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        int value = Integer.parseInt(valueOperations.get(key));
        return value;
    }

//    public Doctor getDoctor(String key) {
//        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
//        return jsonUtil.jsonStrToObject(valueOperations.get(key));
//    }

    public boolean remove(String key) {
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        return stringRedisTemplate.delete(key);
    }
//    public WechatUser getWechatUser(String key) {
//        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
//        return jsonUtil.jsonStrToWechatUser(valueOperations.get(key));
//    }
}
