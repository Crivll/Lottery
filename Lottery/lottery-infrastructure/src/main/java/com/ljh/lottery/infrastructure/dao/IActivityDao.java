package com.ljh.lottery.infrastructure.dao;

import com.ljh.lottery.infrastructure.po.Activity;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 * Description: 活动dao接口
 *
 * @Author: ljh
 * DateTime: 2022-05-21 12:07
 */
@Mapper
public interface IActivityDao {

    void insert(Activity req);

    Activity queryActivityById(Long activityId);
}
