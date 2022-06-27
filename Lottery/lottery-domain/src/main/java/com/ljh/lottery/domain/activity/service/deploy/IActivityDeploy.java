package com.ljh.lottery.domain.activity.service.deploy;

import com.ljh.lottery.domain.activity.model.req.ActivityConfigReq;
import com.ljh.lottery.domain.activity.model.vo.ActivityVO;

import java.util.List;

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

    /**
     * 扫描待处理的活动集合，状态为：通过、活动中
     * 通过 -> 时间符合时 -> 活动中
     * 活动中 -> 时间到期时 -> 关闭
     * @param id
     * @return
     */
    List<ActivityVO> scanToDoActivityList(Long id);
}
