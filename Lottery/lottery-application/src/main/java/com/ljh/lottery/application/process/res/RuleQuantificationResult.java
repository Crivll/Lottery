package com.ljh.lottery.application.process.res;

import com.ljh.lottery.common.Result;

/**
 * Created with IntelliJ IDEA.
 * Description: 量化人群结果
 *
 * @Author: ljh
 * DateTime: 2022-06-22 12:48
 */
public class RuleQuantificationResult extends Result {

    /**
     * 活动id
     */
    private Long activityId;

    public RuleQuantificationResult(String code, String info) {
        super(code, info);
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
