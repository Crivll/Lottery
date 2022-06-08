package com.ljh.lottery.infrastructure.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description: 用户领取活动表
 *
 * @Author: ljh
 * DateTime: 2022-06-08 16:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTakeActivity {
    /**
     * 自增ID
     */
    private Long id;
    /**
     * 用户ID
     */
    private String uId;
    /**
     * 活动领取ID
     */
    private Long takeId;
    /**
     * 活动ID
     */
    private Long activityId;
    /**
     * 活动名称
     */
    private String activityName;
    /**
     * 活动领取时间
     */
    private Date takeDate;
    /**
     * 领取次数
     */
    private Integer takeCount;
    /**
     * 防重ID
     */
    private String uuid;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
