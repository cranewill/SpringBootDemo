package com.tsuru.springboot_demo.bean.mybatis;


public class ServiceWelcomeBean extends MSBaseBean {
	
	private String originId;

	private String text;

	private String mediaId;

	public String getOriginId() {
		return originId;
	}

	public void setOriginId(String originId) {
		this.originId = originId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}


}

