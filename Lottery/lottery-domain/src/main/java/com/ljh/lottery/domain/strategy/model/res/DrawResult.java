package com.ljh.lottery.domain.strategy.model.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: ljh
 * DateTime: 2022-05-22 18:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrawResult {

    /**
     * 用户Id
     */
    private String uId;

    /**
     * 策略Id
     */
    private Long strategyId;

    /**
     * 奖品Id
     */
    private String rewardId;

    /**
     * 奖品名称
     */
    private String awardName;
}
