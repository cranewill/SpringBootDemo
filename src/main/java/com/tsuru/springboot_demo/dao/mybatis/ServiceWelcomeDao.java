package com.tsuru.springboot_demo.dao.mybatis;

import com.tsuru.springboot_demo.bean.mybatis.ServiceWelcomeBean;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceWelcomeDao {

    /***************************************** 注解方式 *****************************************/

    /**
     * 根据originId查找一条
     * 参数只有一个，可以不写@Param注解
     *
     * @param id
     * @return
     */
    @Select("select * from servicewelcome_data where originId = #{id}")
    @Results({
            @Result(property = "originId", column = "originId"),
            @Result(property = "text", column = "text"),
            @Result(property = "media_id", column = "mediaId")
    })
    public ServiceWelcomeBean select(String id);

    /**
     * 查找全部
     *
     * @return
     */
    @Select("select * from servicewelcome_data")
    @Results({
            @Result(property = "originId", column = "originId"),
            @Result(property = "text", column = "text"),
            @Result(property = "mediaId", column = "media_id")
    })
    public List<ServiceWelcomeBean> selectAll();

    /**
     * 根据originId和media_id查找text字段
     * 参数有两个及以上，要增加@Param注解
     *
     * @param originId
     * @param mediaId
     * @return
     */
    @Select("select `text` from servicewelcome_data where `originId` = #{id} and `media_id` = #{media}")
    @Result(property = "text", column = "text")
    public String selectText(@Param("id") String originId, @Param("media") String mediaId);

    /***************************************** xml方式 *****************************************/

    public void insert(ServiceWelcomeBean bean);

    public void update(ServiceWelcomeBean bean);

    public void delete(String id);

}
