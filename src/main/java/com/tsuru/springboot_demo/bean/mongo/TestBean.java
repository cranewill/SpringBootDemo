package com.tsuru.springboot_demo.bean.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "springboot_test") // 若为加该注解，则默认写入到类型对应的collection中
@CompoundIndexes({ // 联合索引 1为正序 -1为倒序
		@CompoundIndex(name = "test_key", def = "{'gameId':1, 'time':-1}")
})
public class TestBean extends BaseBean {
	@Id
	private long id;

	private int gameId;

	@Indexed(unique = true) // 默认为false
	private String openId;

	@Field(value = "tTime") // 指定存到collection中的字段
	private long time;

	@Transient // 不序列化
	private int param;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public int getParam() {
		return param;
	}

	public void setParam(int param) {
		this.param = param;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
}
