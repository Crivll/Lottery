package com.ljh.lottery.domain.activity.model.req;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description: 参与活动请求
 *
 * @Author: ljh
 * DateTime: 2022-06-10 14:09
 */
public class PartakeReq {

    /**
     * 用户Id
     */
    private String uId;

    /**
     * 活动Id
     */
    private Long activityId;

    /**
     * 活动领取时间
     */
    private Date partakeDate;

    public PartakeReq() {
    }

    public PartakeReq(String uId, Long activityId) {
        this.uId = uId;
        this.activityId = activityId;
        this.partakeDate = new Date();
    }

    public PartakeReq(String uId, Long activityId, Date partakeDate) {
        this.uId = uId;
        this.activityId = activityId;
        this.partakeDate = partakeDate;
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

    public Date getPartakeDate() {
        return partakeDate;
    }

    public void setPartakeDate(Date partakeDate) {
        this.partakeDate = partakeDate;
    }
}
