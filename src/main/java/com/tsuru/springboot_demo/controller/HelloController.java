package com.tsuru.springboot_demo.controller;

import com.alibaba.fastjson.JSON;
import com.tsuru.springboot_demo.bean.RedisTestBean;
import com.tsuru.springboot_demo.bean.mybatis.ServiceWelcomeBean;
import com.tsuru.springboot_demo.bean.mongo.BonusBean;
import com.tsuru.springboot_demo.dao.mongo.BonusDao;
import com.tsuru.springboot_demo.service.HelloService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

@Log4j2
@RestController // == @Controller + @ResponseBody
public class HelloController {

	@Value("${global.url}")
	String url;
	@Autowired
	HelloService service;
	@Autowired
	BonusDao dao;

	@RequestMapping(value = "post", method = RequestMethod.POST)
	public HashMap<String, Object> post(@RequestBody String body) {
		HashMap<String, Object> result = JSON.parseObject(body, HashMap.class);
		return result;
	}

	@RequestMapping(value = "hello", method = RequestMethod.GET)
	// 如果get参数不是必须的，则必须指定required为false。不会像struts2一样自动设为空值
	public ModelAndView hello(int num, @RequestParam(required = false) String st) {
		// 测试自定义配置
		String str = String.format(url, num, st);
		// 测试日志
		log.info("info:" + str);
		log.error("error:" + str);
		log.warn("warn:" + str);
		log.debug("debug:" + str);
		// 测试thymeleaf
		ModelAndView mav = new ModelAndView();
		mav.setViewName("hello");
		List<BonusBean> list = dao.selectAll();
		mav.addObject("list", list);
		mav.addObject("hello", "Hello~!~");
		return mav;
	}

	// add和print方法验证service的唯一性
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public HashMap<String, Object> addTest() {
		service.add();

		HashMap<String, Object> result = new HashMap<>();
		result.put("message", "success");
		return result;
	}

	@RequestMapping(value = "print", method = RequestMethod.GET)
	public HashMap<String, Object> restTest() {
		service.print();

		HashMap<String, Object> result = new HashMap<>();
		result.put("code", 1);
		return result;
	}

	@RequestMapping(value = "select", method = RequestMethod.GET)
	public HashMap<String, Object> select() {
		HashMap<String, Object> result = new HashMap<>();
		try {
			BonusBean bean = dao.select("5292_test");
			log.info(bean.getId());
			log.info(bean.getRecordTime());
			result.put("code", bean.getRecordTime());
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	// 测试mongodb自定义bean注解
	@RequestMapping(value = "insert", method = RequestMethod.GET)
	public HashMap<String, Object> insert() {
		HashMap<String, Object> result = new HashMap<>();
		try {
			service.insert();
			result.put("code", 1);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	@RequestMapping(value = "save", method = RequestMethod.GET)
	public HashMap<String, Object> save() {
		HashMap<String, Object> result = new HashMap<>();
		try {
			service.save();
			result.put("code", 1);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	@RequestMapping(value = "mbselect", method = RequestMethod.GET)
	public HashMap<String, Object> mybatisSelect() {
		HashMap<String, Object> result = new HashMap<>();
		try {
			List<ServiceWelcomeBean> list = service.mybatisSelect();
			result.put("list", list);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	@RequestMapping(value = "mbText", method = RequestMethod.GET)
	public HashMap<String, Object> mybatisText() {
		HashMap<String, Object> result = new HashMap<>();
		try {
			String text = service.mybatisText();
			result.put("text", text);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

    /**
     * 用于测试redis
     * @return
     */
    @RequestMapping(value = "redisSet", method = RequestMethod.GET)
    public HashMap<String, Object> redisSet() {
        HashMap<String, Object> result = new HashMap<>();
        try {
            service.redisTestSet();
            result.put("result", 0);
        } catch (Exception e) {
            log.error(e);
        }
        return result;
    }

    @RequestMapping(value = "redisGet", method = RequestMethod.GET)
    public HashMap<String, Object> redisGet() {
        HashMap<String, Object> result = new HashMap<>();
        try {
            result.put("result", service.redisTestGet());
        } catch (Exception e) {
            log.error(e);
        }
        return result;
    }

}
