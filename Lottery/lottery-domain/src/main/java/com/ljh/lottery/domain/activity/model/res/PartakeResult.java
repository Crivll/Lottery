package com.ljh.lottery.domain.activity.model.res;

import com.ljh.lottery.common.Result;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * Description: 活动参与结果
 *
 * @Author: ljh
 * DateTime: 2022-06-10 14:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartakeResult {

    /**
     * 策略Id
     */
    private Long strategyId;

    /**
     * 活动领取ID
     */
    private Long takeId;

}
