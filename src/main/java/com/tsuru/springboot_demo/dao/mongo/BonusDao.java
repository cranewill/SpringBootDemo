package com.tsuru.springboot_demo.dao.mongo;

import com.tsuru.springboot_demo.bean.mongo.BaseBean;
import com.tsuru.springboot_demo.bean.mongo.BonusBean;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Log4j2
public class BonusDao extends BaseDao {

	@Override
	public BonusBean select(Object id) {
		try {
			Query query = new Query(Criteria.where("_id").is(id));
			return mongo.findOne(query, BonusBean.class);
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}

	public List<BonusBean> selectAll() {
		return mongo.findAll(BonusBean.class);
	}

	@Override
	public int insert(BaseBean bean) {
		try {
			mongo.insert(bean);
		} catch (Exception e) {
			log.error(e);
		}
		return 0;
	}

	@Override
	public int update(BaseBean baseBean) {
		try {
			BonusBean bean = (BonusBean) baseBean;
			mongo.updateFirst(new Query(Criteria.where("_id").is(bean.getId())),
					new Update().set("bonusTime", System.currentTimeMillis()), BonusBean.class);
		} catch (Exception e) {
			log.error(e);
		}
		return 0;
	}

	@Override
	public int delete(Object id) {
		try {
			Query query = new Query(Criteria.where("_id").is(id));
			mongo.remove(query, BonusBean.class);
		} catch (Exception e) {
			log.error(e);
		}
		return 0;
	}

	public int save(BonusBean bean) {
		try {
			mongo.save(bean);
		} catch (Exception e) {
			log.error(e);
		}
		return 0;
	}
}

