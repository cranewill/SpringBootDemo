package com.tsuru.springboot_demo.config;

import com.tsuru.springboot_demo.interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName InterceptorConfig
 * @Description 拦截器配置
 * @Author Tsuru
 **/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	/**
	 * 注册拦截器
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(handlerInterceptor()).addPathPatterns("/**");
	}

	@Bean
	public HandlerInterceptor handlerInterceptor() {
		return new MyInterceptor();
	}
}
