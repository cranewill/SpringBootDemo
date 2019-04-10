package com.tsuru.springboot_demo.dao.mongo;

import com.tsuru.springboot_demo.bean.mongo.BaseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public abstract class BaseDao {
	@Autowired
	MongoTemplate mongo;

	public abstract BaseBean select(Object id);

	public abstract int insert(BaseBean bean);

	public abstract int update(BaseBean bean);

	public abstract int delete(Object id);
}
