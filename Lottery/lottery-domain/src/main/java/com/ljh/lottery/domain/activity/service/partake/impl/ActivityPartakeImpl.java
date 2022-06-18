package com.ljh.lottery.domain.activity.service.partake.impl;

import com.ljh.lottery.common.Constants;
import com.ljh.lottery.common.Result;
import com.ljh.lottery.domain.activity.model.req.PartakeReq;
import com.ljh.lottery.domain.activity.model.vo.ActivityBillVO;
import com.ljh.lottery.domain.activity.model.vo.UserTakeActivityVO;
import com.ljh.lottery.domain.activity.repository.IUserTakeActivityRepository;
import com.ljh.lottery.domain.activity.service.partake.BaseActivityPartake;
import com.ljh.lottery.domain.support.ids.IIdGenerator;
import com.ljh.middleware.db.router.strategy.IDBRouterStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: ljh
 * DateTime: 2022-06-10 18:37
 */
@Service
public class ActivityPartakeImpl extends BaseActivityPartake {

    private Logger logger = LoggerFactory.getLogger(ActivityPartakeImpl.class);

    @Resource
    private IUserTakeActivityRepository userTakeActivityRepository;
    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;
    @Resource
    private TransactionTemplate transactionTemplate;
    @Resource
    private IDBRouterStrategy dbRouter;

    @Override
    protected UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId, String uId) {
        return userTakeActivityRepository.queryNoConsumedTakeActivityOrder(activityId, uId);
    }

    @Override
    protected Result checkActivityBill(PartakeReq partake, ActivityBillVO bill) {
        // 校验活动状态
        if (!Constants.ActivityState.DOING.getCode().equals(bill.getState())) {
            logger.warn("活动当前状态非可用 state: {}", bill.getState());
            return Result.buildResult(Constants.ResponseCode.UN_ERROR, "活动当前状态非可用");
        }

        // 校验活动日期
        if (bill.getBeginDateTime().after(partake.getPartakeDate()) || bill.getEndDateTime().before(partake.getPartakeDate())) {
            logger.warn("活动时间范围非可用 beginDateTime: {}, endDateTime: {}", bill.getBeginDateTime(), bill.getEndDateTime());
            return Result.buildResult(Constants.ResponseCode.UN_ERROR, "活动时间范围非可用");
        }
        // 校验活动库存
        if (bill.getStockSurplusCount() <= 0) {
            logger.warn("活动剩余库存非可用 stockSurplusCount: {}", bill.getStockSurplusCount());
            return Result.buildResult(Constants.ResponseCode.UN_ERROR, "活动剩余库存非可用");
        }

        // 校验个人库存 - 个人活动剩余可领取次数
        if (bill.getUserTakeLeftCount() <= 0) {
            logger.warn("个人领取次数非可用 userTakeLeftCount: {}", bill.getUserTakeLeftCount());
            return Result.buildResult(Constants.ResponseCode.UN_ERROR, "个人领取次数非可用");
        }

        return Result.success(null);
    }

    @Override
    protected Result subtractionActivityStock(PartakeReq req) {
        int count = activityRepository.subtractionActivityStock(req.getActivityId());
        if (0 == count) {
            logger.error("扣减活动库存失败 activityId: {}", req.getActivityId());
            return Result.buildResult(Constants.ResponseCode.NO_UPDATE);
        }
        return Result.success(null);
    }

    @Override
    protected Result grabActivity(PartakeReq partake, ActivityBillVO bill, Long takeId) {
        try {
            dbRouter.doRouter(partake.getuId());
            return transactionTemplate.execute(status -> {
                try {
                    // 扣减个人已参与次数
                    int updateCount = userTakeActivityRepository.subtractionLeftCount(bill.getActivityId()
                            , bill.getActivityName()
                            , bill.getTakeCount()
                            , bill.getUserTakeLeftCount()
                            , bill.getuId()
                            , partake.getPartakeDate());
                    if (0 == updateCount) {
                        status.setRollbackOnly();
                        logger.error("领取活动，扣减个人已参与次数失败 activityId: {}, uId: {}", partake.getActivityId(), partake.getuId());
                        return Result.buildResult(Constants.ResponseCode.NO_UPDATE);
                    }

                    // 插入领取活动信息
                    // Long takeId = idGeneratorMap.get(Constants.Ids.SnowFlake).nextId();
                    // 为避免重复领取，不在此处生成领取编号，在外层判断并生成编号
                    userTakeActivityRepository.takeActivity(bill.getActivityId()
                            , bill.getActivityName()
                            , bill.getTakeCount()
                            , bill.getUserTakeLeftCount()
                            , bill.getuId()
                            , partake.getPartakeDate()
                            , takeId);
                } catch (DuplicateKeyException e) {
                    status.setRollbackOnly();
                    logger.error("领取活动，唯一索引冲突 activityId: {}, uId: {}", partake.getActivityId(), partake.getuId());
                    return Result.buildResult(Constants.ResponseCode.INDEX_DUP);
                }
                return Result.success(null);
            });
        } finally {
            dbRouter.clear();
        }
    }
}
