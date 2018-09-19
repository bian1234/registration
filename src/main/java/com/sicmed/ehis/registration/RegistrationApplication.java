package com.sicmed.ehis.registration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;


//@EnableHystrix  //@EnableCircuitBreaker  ==  @EnableHystrix
//@EnableFeignClients
//@EnableEurekaServer
@EnableCaching      // redis缓存
@SpringBootApplication
@EnableScheduling   //定时器
public class RegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistrationApplication.class, args);
	}
}
