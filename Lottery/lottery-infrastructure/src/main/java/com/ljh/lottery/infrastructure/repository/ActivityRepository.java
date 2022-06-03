package com.ljh.lottery.infrastructure.repository;

import com.ljh.lottery.common.Constants;
import com.ljh.lottery.domain.activity.model.vo.*;
import com.ljh.lottery.domain.activity.repository.IActivityRepository;
import com.ljh.lottery.infrastructure.dao.IActivityDao;
import com.ljh.lottery.infrastructure.dao.IAwardDao;
import com.ljh.lottery.infrastructure.dao.IStrategyDao;
import com.ljh.lottery.infrastructure.dao.IStrategyDetailDao;
import com.ljh.lottery.infrastructure.po.Activity;
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
 * DateTime: 2022-06-03 18:14
 */
@Component
public class ActivityRepository implements IActivityRepository {

    @Resource
    private IActivityDao activityDao;
    @Resource
    private IAwardDao awardDao;
    @Resource
    private IStrategyDao strategyDao;
    @Resource
    private IStrategyDetailDao strategyDetailDao;

    @Override
    public void addActivity(ActivityVO activity) {
        Activity req = new Activity();
        BeanUtils.copyProperties(activity, req);
        activityDao.insert(req);
    }

    @Override
    public void addAward(List<AwardVO> awardList) {
        List<Award> req = awardList.stream().map(awardVO -> {
            Award award = new Award();
            BeanUtils.copyProperties(awardVO, award);
            return award;
        }).collect(Collectors.toList());
        awardDao.insertList(req);
    }

    @Override
    public void addStrategy(StrategyVO strategy) {
        Strategy req = new Strategy();
        BeanUtils.copyProperties(strategy, req);
        strategyDao.insert(req);
    }

    @Override
    public void addStrategyDetailList(List<StrategyDetailVO> strategyDetailList) {
        List<StrategyDetail> req = strategyDetailList.stream().map(strategyDetailVO -> {
            StrategyDetail strategyDetail = new StrategyDetail();
            BeanUtils.copyProperties(strategyDetailVO, strategyDetail);
            return strategyDetail;
        }).collect(Collectors.toList());
        strategyDetailDao.insertList(req);
    }

    @Override
    public boolean alterStatus(Long activityId, Enum<Constants.ActivityState> beforeState, Enum<Constants.ActivityState> afterState) {
        AlterStateVO alterStateVO = new AlterStateVO(activityId
                , ((Constants.ActivityState) beforeState).getCode()
                , ((Constants.ActivityState) afterState).getCode());
        int count = activityDao.alterState(alterStateVO);
        return 1 == count;
    }
}
