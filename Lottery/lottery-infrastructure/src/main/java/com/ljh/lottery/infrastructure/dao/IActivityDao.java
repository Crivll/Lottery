package com.ljh.lottery.infrastructure.dao;

import com.ljh.lottery.infrastructure.po.Activity;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: ljh
 * DateTime: 2022-05-21 12:07
 */
public interface IActivityDao {

    void insert(Activity req);

    Activity queryActivityById(Long activityId);
}
