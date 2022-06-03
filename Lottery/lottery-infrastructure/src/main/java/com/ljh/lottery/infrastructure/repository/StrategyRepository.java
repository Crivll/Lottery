package com.ljh.lottery.infrastructure.repository;

import com.ljh.lottery.domain.strategy.model.aggregates.StrategyRich;
import com.ljh.lottery.domain.strategy.model.vo.AwardBriefVO;
import com.ljh.lottery.domain.strategy.model.vo.StrategyBriefVO;
import com.ljh.lottery.domain.strategy.model.vo.StrategyDetailBriefVO;
import com.ljh.lottery.domain.strategy.repository.IStrategyRepository;
import com.ljh.lottery.infrastructure.dao.IAwardDao;
import com.ljh.lottery.infrastructure.dao.IStrategyDao;
import com.ljh.lottery.infrastructure.dao.IStrategyDetailDao;
import com.ljh.lottery.infrastructure.po.Award;
import com.ljh.lottery.infrastructure.po.Strategy;
import com.ljh.lottery.infrastructure.po.StrategyDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        List<StrategyDetail> strategyDetailList= strategyDetailDao.queryStrategyDetailList(strategyId);

        StrategyBriefVO strategyBriefVO = new StrategyBriefVO();
        BeanUtils.copyProperties(strategy, strategyBriefVO);

        List<StrategyDetailBriefVO> strategyDetailBriefVOList = strategyDetailList.stream().map(strategyDetail -> {
            StrategyDetailBriefVO strategyDetailBriefVO = new StrategyDetailBriefVO();
            BeanUtils.copyProperties(strategyDetail, strategyDetailBriefVO);
            return strategyDetailBriefVO;
        }).collect(Collectors.toList());
        return StrategyRich.builder()
                .strategyId(strategyId)
                .strategy(strategyBriefVO)
                .strategyDetailList(strategyDetailBriefVOList).build();
    }

    @Override
    public AwardBriefVO queryAwardInfo(String awardId) {
        Award award = awardDao.queryAwardInfo(awardId);

        // 可以使用 BeanUtils.copyProperties(award, awardBriefVO)、或者基于ASM实现的Bean-Mapping，但在效率上最好的依旧是硬编码
        AwardBriefVO awardBriefVO = new AwardBriefVO();
        BeanUtils.copyProperties(award, awardBriefVO);
        return awardBriefVO;
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
