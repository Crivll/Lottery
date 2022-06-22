package com.ljh.lottery.application.process.impl;

import com.ljh.lottery.application.process.IActivityProcess;
import com.ljh.lottery.application.process.req.DrawProcessReq;
import com.ljh.lottery.common.Constants;
import com.ljh.lottery.common.Result;
import com.ljh.lottery.domain.activity.model.req.PartakeReq;
import com.ljh.lottery.domain.activity.model.res.PartakeResult;
import com.ljh.lottery.domain.activity.model.vo.DrawOrderVO;
import com.ljh.lottery.domain.activity.service.partake.IActivityPartake;
import com.ljh.lottery.domain.strategy.model.req.DrawReq;
import com.ljh.lottery.domain.strategy.model.res.DrawResult;
import com.ljh.lottery.domain.strategy.model.vo.DrawAwardVO;
import com.ljh.lottery.domain.strategy.service.draw.IDrawExec;
import com.ljh.lottery.domain.support.ids.IIdGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 活动抽奖流程编排
 *
 * @Author: ljh
 * DateTime: 2022-06-19 10:40
 */
@Service
public class ActivityProcessImpl implements IActivityProcess {

    @Resource
    private IActivityPartake activityPartake;
    @Resource
    private IDrawExec drawExec;
    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;


    @Override
    public Result<Object> doDrawProcess(DrawProcessReq req) {
        //1. 领取活动
        Result<PartakeResult> partakeResult = activityPartake.doPartake(new PartakeReq(req.getuId(), req.getActivityId()));
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(partakeResult.getCode())) {
            return Result.builder()
                    .code(partakeResult.getCode())
                    .info(partakeResult.getInfo())
                    .build();
        }
        Long strategyId = partakeResult.getData().getStrategyId();
        Long takeId = partakeResult.getData().getTakeId();

        // 2. 执行抽奖
        DrawResult drawResult = drawExec.doDrawExec(new DrawReq(req.getuId(), strategyId, String.valueOf(takeId)));
        if (Constants.DrawState.FAIL.getCode().equals(drawResult.getDrawState())) {
            return Result.builder()
                    .code(Constants.ResponseCode.LOSING_DRAW.getCode())
                    .info(Constants.ResponseCode.LOSING_DRAW.getInfo())
                    .build();
        }
        DrawAwardVO drawAwardInfo = drawResult.getDrawAwardInfo();

        // 3. 抽奖结果落库
        activityPartake.recordDrawOrder(buildDrawOrderVO(req, strategyId, takeId, drawAwardInfo));

        // todo 4. 发送MQ，触发后续流程

        // 5. 返回结果
        return Result.success(drawAwardInfo);
    }

    private DrawOrderVO buildDrawOrderVO(DrawProcessReq req, Long strategyId, Long takeId, DrawAwardVO drawAwardInfo) {
        long orderId = idGeneratorMap.get(Constants.Ids.SnowFlake).nextId();
        DrawOrderVO drawOrderVO = new DrawOrderVO();
        drawOrderVO.setuId(req.getuId());
        drawOrderVO.setTakeId(takeId);
        drawOrderVO.setActivityId(req.getActivityId());
        drawOrderVO.setOrderId(orderId);
        drawOrderVO.setStrategyId(strategyId);
        drawOrderVO.setStrategyMode(drawAwardInfo.getStrategyMode());
        drawOrderVO.setGrantType(drawAwardInfo.getGrantType());
        drawOrderVO.setGrantDate(drawAwardInfo.getGrantDate());
        drawOrderVO.setGrantState(Constants.GrantState.INIT.getCode());
        drawOrderVO.setAwardId(drawAwardInfo.getAwardId());
        drawOrderVO.setAwardType(drawAwardInfo.getAwardType());
        drawOrderVO.setAwardName(drawAwardInfo.getAwardName());
        drawOrderVO.setAwardContent(drawAwardInfo.getAwardContent());
        return drawOrderVO;
    }
}
