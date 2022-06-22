package com.ljh.lottery.rpc.req;

/**
 * Created with IntelliJ IDEA.
 * Description: 指定活动抽奖请求
 *
 * @Author: ljh
 * DateTime: 2022-06-22 10:47
 */
public class DrawReq {
    /**
     * 用户id
     */
    private String uId;
    /**
     * 活动id
     */
    private Long activityId;

    public DrawReq() {
    }

    public DrawReq(String uId, Long activityId) {
        this.uId = uId;
        this.activityId = activityId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
