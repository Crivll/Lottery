package com.ljh.lottery.domain.activity.model.req;

import com.ljh.lottery.domain.activity.model.aggregates.ActivityConfigRich;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * Description: 活动配置请求对象
 *
 * @Author: ljh
 * DateTime: 2022-06-03 15:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityConfigReq {

    /** 活动ID */
    private Long activityId;

    /** 活动配置信息 */
    private ActivityConfigRich activityConfigRich;
}
