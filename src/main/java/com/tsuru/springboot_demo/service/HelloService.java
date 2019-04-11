package com.tsuru.springboot_demo.service;

import com.tsuru.springboot_demo.bean.RedisTestBean;
import com.tsuru.springboot_demo.bean.mybatis.ServiceWelcomeBean;
import com.tsuru.springboot_demo.dao.mongo.TestDao;
import com.tsuru.springboot_demo.bean.mongo.TestBean;
import com.tsuru.springboot_demo.dao.mybatis.ServiceWelcomeDao;
import com.tsuru.springboot_demo.dao.redis.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * service demo
 */
@Service
public class HelloService {
	@Autowired
	TestDao dao;
	@Autowired
	ServiceWelcomeDao sdao;
	@Autowired
	RedisDao rdao;

	public List<String> list = new ArrayList<>();

	public void add() {
		list.add("a");
	}

	public void print() {
		list.stream().forEach(a -> System.out.println(a));
	}

	public void insert() {
		TestBean bean = new TestBean();
		bean.setId(11111111l);
		bean.setGameId(527);
		bean.setOpenId("test_openId");
		bean.setTime(System.currentTimeMillis());
		bean.setParam(999);
		dao.insert(bean);
	}

	public void save() {
		TestBean bean = new TestBean();
		bean.setId(222222222L);
		bean.setGameId(999);
		bean.setOpenId("test_save1_openId");
		bean.setTime(System.currentTimeMillis());
		bean.setParam(111);
		dao.save(bean);
	}

	public List<ServiceWelcomeBean> mybatisSelect() {
		return sdao.selectAll();
	}

	public String mybatisText() {
		return sdao.selectText("test", "1");
	}

	/**
	 * mybatis批量插入
	 */
	public void mybatisBatchInsert() {
		List<ServiceWelcomeBean> list = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			ServiceWelcomeBean bean = new ServiceWelcomeBean();
			bean.setOriginId("id" + i);
			bean.setText("text" + i);
			bean.setMediaId("media" + i);
			list.add(bean);
		}
		sdao.batchInsert(list);
	}

	/**
	 * mybatis批量合并更新
	 */
	public void mybatisBatchMerge() {
		List<ServiceWelcomeBean> list = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			ServiceWelcomeBean bean = new ServiceWelcomeBean();
			bean.setOriginId("id" + i);
			bean.setText("text" + i);
			bean.setMediaId("media" + i);
			list.add(bean);
		}
		sdao.batchMerge(list);
	}

	public void redisTestSet() {
		rdao.set("test", new RedisTestBean("haha", 1, 2L));
	}

	public Object redisTestGet() {
		return rdao.get("test");
	}
}
