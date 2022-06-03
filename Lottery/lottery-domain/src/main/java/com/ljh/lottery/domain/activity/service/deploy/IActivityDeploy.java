package com.ljh.lottery.domain.activity.service.deploy;

import com.ljh.lottery.domain.activity.model.req.ActivityConfigReq;

/**
 * Created with IntelliJ IDEA.
 * Description: 部署活动配置接口
 *
 * @Author: ljh
 * DateTime: 2022-06-03 16:17
 */
public interface IActivityDeploy {

    /**
     * 创建活动信息
     * @param req
     */
    void createActivity(ActivityConfigReq req);

    /**
     * 修改活动信息
     * @param req
     */
    void updateActivity(ActivityConfigReq req);
}
