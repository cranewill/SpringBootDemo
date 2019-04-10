package com.tsuru.springboot_demo.interceptor;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname MyInterceptor
 * @Description 自定义拦截器
 * @Author Tsuru
 **/
@Log4j2
public class MyInterceptor implements HandlerInterceptor {
	/**
	 * 请求处理之前调用
	 * @param request
	 * @param response
	 * @param handler
	 * @return true:继续往下执行 false:拦截该请求
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.debug("preHandle...");
		return true;
	}

	/**
	 * 请求处理之后调用，但是在视图渲染之前
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @throws Exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		log.debug("postHandle...");
	}

	/**
	 * 请求处理完成之后调用，一般主要用于资源清理
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @throws Exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		log.debug("afterCompletion...");
	}
}
