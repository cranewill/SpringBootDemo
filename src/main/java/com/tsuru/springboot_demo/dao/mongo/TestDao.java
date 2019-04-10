package com.tsuru.springboot_demo.dao.mongo;

import com.tsuru.springboot_demo.bean.mongo.BaseBean;
import com.tsuru.springboot_demo.bean.mongo.BonusBean;
import com.tsuru.springboot_demo.bean.mongo.TestBean;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Log4j2
public class TestDao extends BaseDao {

	@Override
	public TestBean select(Object id) {
		Query query = new Query(Criteria.where("_id").is(id));
		return mongo.findOne(query, TestBean.class);
	}

	public List<BonusBean> selectAll() {
		return mongo.findAll(BonusBean.class);
	}

	@Override
	public int insert(BaseBean bean) {
		try {
			mongo.insert(bean);
			return 1;
		} catch (Exception e) {
			log.error(e);
		}
		return 0;
	}

	@Override
	public int update(BaseBean bean) {
		return 0;
	}

	@Override
	public int delete(Object id) {
		return 0;
	}

	public int save(TestBean bean) {
		try {
			mongo.save(bean);
		} catch (Exception e) {
			log.error(e);
		}
		return 0;
	}
}

