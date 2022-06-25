package com.ljh.lottery.domain.award.repository;

/**
 * Created with IntelliJ IDEA.
 * Description: 奖品表仓储服务接口
 *
 * @Author: ljh
 * DateTime: 2022-05-27 20:58
 */
public interface IOrderRepository {

    /**
     * 更新奖品发放状态
     * @param uId
     * @param orderId
     * @param awardId
     * @param grantState
     */
    void updateUserAwardState(String uId, Long orderId, String awardId, Integer grantState);
}
