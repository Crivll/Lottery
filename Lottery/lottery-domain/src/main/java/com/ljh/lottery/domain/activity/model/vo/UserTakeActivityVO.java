package com.ljh.lottery.domain.activity.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: ljh
 * DateTime: 2022-06-18 21:59
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTakeActivityVO {

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 活动领取ID
     */
    private Long takeId;

    /**
     * 策略ID
     */
    private Long strategyId;

    /**
     * 活动单使用状态 0未使用、1已使用
     * Constants.TaskState
     */
    private Integer state;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getTakeId() {
        return takeId;
    }

    public void setTakeId(Long takeId) {
        this.takeId = takeId;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "UserTakeActivityVO{" +
                "activityId=" + activityId +
                ", takeId=" + takeId +
                ", strategyId=" + strategyId +
                ", state=" + state +
                '}';
    }
}
