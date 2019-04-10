package com.tsuru.springboot_demo.bean.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer_bonus")
@CompoundIndexes({ // 联合索引 1为正序 -1为倒序
		@CompoundIndex(name = "only_id", def = "{'gameId':1, 'openId':-1}")
})
public class BonusBean extends BaseBean {
	@Id // 唯一id
	private String id;
	private long bonusTime;
	private int gameId;
	private String openId;
	@Indexed // 单索引
	private long recordTime;
	@Transient // 不序列化
	private int param;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getBonusTime() {
		return bonusTime;
	}

	public void setBonusTime(long bonusTime) {
		this.bonusTime = bonusTime;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public long getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(long recordTime) {
		this.recordTime = recordTime;
	}

	public int getParam() {
		return param;
	}

	public void setParam(int param) {
		this.param = param;
	}

}
