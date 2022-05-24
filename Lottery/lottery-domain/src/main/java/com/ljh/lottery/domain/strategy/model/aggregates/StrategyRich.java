package com.ljh.lottery.domain.strategy.model.aggregates;


import com.ljh.lottery.infrastructure.po.Strategy;
import com.ljh.lottery.infrastructure.po.StrategyDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: ljh
 * DateTime: 2022-05-25 2:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StrategyRich {

    /**
     * 策略ID
     */
    private Long strategyId;

    /**
     * 策略配置
     */
    private Strategy strategy;

    /**
     * 策略明细
     */
    private List<StrategyDetail> strategyDetailList;
}
