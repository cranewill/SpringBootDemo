package com.tsuru.springboot_demo;

import com.spring4all.mongodb.EnableMongoPlus;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.tsuru.springboot_demo.dao.mybatis") // 扫描mybatis的mapper文件
@EnableMongoPlus // 激活第三方mongo插件，用于配置mongo连接池
@EnableScheduling // 激活定时任务
@SpringBootApplication
public class SpringbootDemoApplication extends SpringBootServletInitializer {

	/**
	 * 在本地运行的时候会以main方法为入口
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringbootDemoApplication.class, args);
	}

	/**
	 * 打包为jar或者war都需要继承SpringBootServletInitializer并重写该方法
	 * 而且原本main方法的逻辑都不会执行，改为执行该方法
	 * @param application
	 * @return
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringbootDemoApplication.class);
	}

}
