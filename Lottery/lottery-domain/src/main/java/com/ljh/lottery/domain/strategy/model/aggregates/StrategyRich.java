package com.ljh.lottery.domain.strategy.model.aggregates;


import com.ljh.lottery.domain.strategy.model.vo.StrategyBriefVO;
import com.ljh.lottery.domain.strategy.model.vo.StrategyDetailBriefVO;
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
    private StrategyBriefVO strategy;

    /**
     * 策略明细
     */
    private List<StrategyDetailBriefVO> strategyDetailList;
}
