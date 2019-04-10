package com.tsuru.springboot_demo.runner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Order(value=1) // 多个runner通过Order注解设置顺序
@Component
// 用于启动服务器后开启线程执行指定操作
public class MyApplicationRunner implements ApplicationRunner {
	Logger log = LogManager.getLogger(MyApplicationRunner.class);

	@Override
	public void run(ApplicationArguments var1) throws Exception{
		log.info("启动runner——1!");
	}
}
