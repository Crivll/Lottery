package com.ljh.lottery.domain.activity.repository;

import com.ljh.lottery.domain.activity.model.vo.DrawOrderVO;
import com.ljh.lottery.domain.activity.model.vo.UserTakeActivityVO;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description: 用户参与活动仓储接口
 *
 * @Author: ljh
 * DateTime: 2022-06-10 18:46
 */
public interface IUserTakeActivityRepository {

    /**
     * 扣减个人活动参与次数
     * @param activityId        活动ID
     * @param activityName      活动名称
     * @param takeCount         活动个人可领取次数
     * @param userTakeLeftCount 活动个人剩余领取次数
     * @param uId               用户ID
     * @param partakeDate       领取时间
     * @return
     */
    int subtractionLeftCount(Long activityId, String activityName, Integer takeCount, Integer userTakeLeftCount, String uId, Date partakeDate);

    /**
     * 领取活动
     * @param activityId        活动ID
     * @param activityName      活动名称
     * @param takeCount         活动个人可领取次数
     * @param userTakeLeftCount 活动个人剩余领取次数
     * @param uId               用户ID
     * @param partakeDate       领取时间
     * @param takeId            领取ID
     */
    void takeActivity(Long activityId, String activityName, Long strategyId, Integer takeCount, Integer userTakeLeftCount, String uId, Date partakeDate, Long takeId);

    /**
     * 锁定活动领取记录
     * @param uId
     * @param activityId
     * @param takeId
     * @return
     */
    int lockTackActivity(String uId, Long activityId, Long takeId);

    /**
     * 保存抽奖信息
     * @param drawOrder 中奖单
     */
    void saveUserStrategyExport(DrawOrderVO drawOrder);

    /**
     * 查询是否存在未执行的抽奖领取单
     * @param activityId
     * @param uId
     * @return
     */
    UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId, String uId);
}
