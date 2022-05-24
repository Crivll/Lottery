package com.ljh.lottery.domain.strategy.repository;

import com.ljh.lottery.domain.strategy.model.aggregates.StrategyRich;
import com.ljh.lottery.infrastructure.po.Award;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: ljh
 * DateTime: 2022-05-25 2:26
 */
public interface IStrategyRepository {
    /**
     * 查询strategyRich聚合对象
     * @param strategyId
     * @return
     */
    StrategyRich queryStrategyRich(Long strategyId);

    /**
     * 查询奖品信息
     * @param awardId
     * @return
     */
    Award queryAwardInfo(String awardId);

    /**
     * 查询指定策略下无库存的奖品清单
     * @param strategyId
     * @return
     */
    List<String> queryNoStockStrategyAwardList(Long strategyId);

    /**
     * 扣减库存
     * @param strategyId 策略ID
     * @param awardId    奖品ID
     * @return           扣减结果
     */
    boolean deductStock(Long strategyId, String awardId);
}
