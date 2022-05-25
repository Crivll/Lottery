package com.ljh.lottery.domain.strategy.repository.impl;

import com.ljh.lottery.domain.strategy.model.aggregates.StrategyRich;
import com.ljh.lottery.domain.strategy.repository.IStrategyRepository;
import com.ljh.lottery.infrastructure.dao.IAwardDao;
import com.ljh.lottery.infrastructure.dao.IStrategyDao;
import com.ljh.lottery.infrastructure.dao.IStrategyDetailDao;
import com.ljh.lottery.infrastructure.po.Award;
import com.ljh.lottery.infrastructure.po.Strategy;
import com.ljh.lottery.infrastructure.po.StrategyDetail;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: ljh
 * DateTime: 2022-05-25 23:11
 */
@Component
public class StrategyRepository implements IStrategyRepository {

    @Resource
    private IStrategyDao strategyDao;

    @Resource
    private IStrategyDetailDao strategyDetailDao;

    @Resource
    private IAwardDao awardDao;

    @Override
    public StrategyRich queryStrategyRich(Long strategyId) {
        Strategy strategy = strategyDao.queryStrategy(strategyId);
        List<StrategyDetail> strategyDetails = strategyDetailDao.queryStrategyDetailList(strategyId);
        return StrategyRich.builder()
                .strategyId(strategyId)
                .strategy(strategy)
                .strategyDetailList(strategyDetails).build();
    }

    @Override
    public Award queryAwardInfo(String awardId) {
        return awardDao.queryAwardInfo(awardId);
    }

    @Override
    public List<String> queryNoStockStrategyAwardList(Long strategyId) {
        return strategyDetailDao.queryNoStockStrategyAwardList(strategyId);
    }

    @Override
    public boolean deductStock(Long strategyId, String awardId) {
        StrategyDetail strategyDetail = new StrategyDetail();
        strategyDetail.setStrategyId(strategyId);
        strategyDetail.setAwardId(awardId);
        int count = strategyDetailDao.deductStock(strategyDetail);
        return count == 1;
    }
}
