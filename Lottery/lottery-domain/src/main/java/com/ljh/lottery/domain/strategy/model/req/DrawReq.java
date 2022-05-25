package com.ljh.lottery.domain.strategy.model.req;

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
public class DrawReq {

    /**
     * 用户Id
     */
    private String uId;

    /**
     * 策略Id
     */
    private Long strategyId;
}
