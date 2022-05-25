package com.ljh.lottery.test;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.ljh.lottery.domain.strategy.model.req.DrawReq;
import com.ljh.lottery.domain.strategy.model.vo.AwardRateInfo;
import com.ljh.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import com.ljh.lottery.domain.strategy.service.draw.IDrawExec;
import com.ljh.lottery.infrastructure.dao.IActivityDao;
import com.ljh.lottery.infrastructure.dao.IStrategyDao;
import com.ljh.lottery.infrastructure.dao.IStrategyDetailDao;
import com.ljh.lottery.infrastructure.po.Activity;
import com.ljh.lottery.infrastructure.po.StrategyDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: ljh
 * DateTime: 2022-05-25 23:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringRunnerTest {

    private Logger logger = LoggerFactory.getLogger(SpringRunnerTest.class);

    @Resource
    private IActivityDao activityDao;

    @Resource
    private IDrawExec drawExec;

    @Resource
    private IStrategyDao strategyDao;

    @Resource
    private IStrategyDetailDao strategyDetailDao;

    @Resource(name = "singleRateRandomDrawAlgorithm")
    private IDrawAlgorithm randomDrawAlgorithm;

    @Test
    public void test_drawExec() {
//        List<StrategyDetail> strategyDetails = strategyDetailDao.queryStrategyDetailList(10001L);
//        List<AwardRateInfo> awardRateInfoList = new ArrayList<>();
//        for (StrategyDetail strategyDetail : strategyDetails) {
//            awardRateInfoList.add(new AwardRateInfo(strategyDetail.getAwardId(), strategyDetail.getAwardRate()));
//        }
//        randomDrawAlgorithm.initRateTuple(10001L, awardRateInfoList);

        drawExec.doDrawExec(new DrawReq("小傅哥", 10001L));
        drawExec.doDrawExec(new DrawReq("小佳佳", 10001L));
        drawExec.doDrawExec(new DrawReq("小蜗牛", 10001L));
        drawExec.doDrawExec(new DrawReq("八杯水", 10001L));
    }

    @Test
    public void test_insert() {
        Activity activity = new Activity();
        activity.setActivityId(100001L);
        activity.setActivityName("测试活动");
        activity.setActivityDesc("仅用于插入数据测试");
        activity.setBeginDateTime(new Date());
        activity.setEndDateTime(new Date());
        activity.setStockCount(100);
        activity.setTakeCount(10);
        activity.setState(0);
        activity.setCreator("xiaofuge");
        activityDao.insert(activity);
    }

    @Test
    public void test_select() {
        Activity activity = activityDao.queryActivityById(100001L);
        logger.info("测试结果：{}", JSON.toJSONString(activity));
    }
}
