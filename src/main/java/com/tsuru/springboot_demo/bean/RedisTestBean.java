package com.tsuru.springboot_demo.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: RedisTestBean
 * @Description: redis测试bean
 * @author: Tsuru
 */
@Data
public class RedisTestBean implements Serializable {
    private String v_str;

    private int v_int;

    private long v_long;

    /**
     * 进行反序列化时需要该类的无参构造函数
     */
    public RedisTestBean() {
    }

    public RedisTestBean(String v_str, int v_int, long v_long) {
        this.v_str = v_str;
        this.v_int = v_int;
        this.v_long = v_long;
    }
}
