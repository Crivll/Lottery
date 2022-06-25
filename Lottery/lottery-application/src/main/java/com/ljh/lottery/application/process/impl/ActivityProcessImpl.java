package com.ljh.lottery.application.process.impl;

import com.ljh.lottery.application.mq.producer.KafkaProducer;
import com.ljh.lottery.application.process.IActivityProcess;
import com.ljh.lottery.application.process.req.DrawProcessReq;
import com.ljh.lottery.application.process.res.DrawProcessResult;
import com.ljh.lottery.application.process.res.RuleQuantificationResult;
import com.ljh.lottery.common.Constants;
import com.ljh.lottery.common.Result;
import com.ljh.lottery.domain.activity.model.req.PartakeReq;
import com.ljh.lottery.domain.activity.model.res.PartakeResult;
import com.ljh.lottery.domain.activity.model.vo.DrawOrderVO;
import com.ljh.lottery.domain.activity.model.vo.InvoiceVO;
import com.ljh.lottery.domain.activity.service.partake.IActivityPartake;
import com.ljh.lottery.domain.rule.model.req.DecisionMatterReq;
import com.ljh.lottery.domain.rule.model.res.EngineResult;
import com.ljh.lottery.domain.rule.service.engine.EngineFilter;
import com.ljh.lottery.domain.strategy.model.req.DrawReq;
import com.ljh.lottery.domain.strategy.model.res.DrawResult;
import com.ljh.lottery.domain.strategy.model.vo.DrawAwardVO;
import com.ljh.lottery.domain.strategy.service.draw.IDrawExec;
import com.ljh.lottery.domain.support.ids.IIdGenerator;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

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
    @Resource(name = "ruleEngineHandler")
    private EngineFilter engineFilter;
    @Resource
    private KafkaProducer kafkaProducer;


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
        DrawOrderVO drawOrderVO = buildDrawOrderVO(req, strategyId, takeId, drawAwardInfo);
        Result recordResult = activityPartake.recordDrawOrder(drawOrderVO);
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(recordResult.getCode())) {
            return  new Result(recordResult.getCode(), recordResult.getInfo());
        }

        // 4. 发送MQ，触发后续流程
        InvoiceVO invoiceVO = buildInvoiceVO(drawOrderVO);
        ListenableFuture<SendResult<String, Object>> future = kafkaProducer.sendLotteryInvoice(invoiceVO);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                // MQ 消息发送完成，更新数据库表 user_strategy_export.mq_state = 1
                activityPartake.updateInvoiceMqState(invoiceVO.getuId(), invoiceVO.getOrderId(), Constants.MQState.COMPLETE.getCode());
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                // MQ 消息发送失败，更新数据库表 user_strategy_export.mq_state = 2 【等待定时任务扫码补偿MQ消息】
                activityPartake.updateInvoiceMqState(invoiceVO.getuId(), invoiceVO.getOrderId(), Constants.MQState.FAIL.getCode());
            }
        });

        // 5. 返回结果
        return Result.success(drawAwardInfo);
    }

    @Override
    public RuleQuantificationResult doRuleQuantification(DecisionMatterReq req) {
        // 1. 量化决策
        EngineResult engineResult = engineFilter.process(req);

        if (!engineResult.isSuccess()) {
            return new RuleQuantificationResult(Constants.ResponseCode.RULE_ERR.getCode(), Constants.ResponseCode.RULE_ERR.getInfo());
        }
        // 2. 封装结果
        RuleQuantificationResult ruleQuantificationResult = new RuleQuantificationResult(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
        ruleQuantificationResult.setActivityId(Long.valueOf(engineResult.getNodeValue()));

        return ruleQuantificationResult;
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

    private InvoiceVO buildInvoiceVO(DrawOrderVO drawOrderVO) {
        InvoiceVO invoiceVO = new InvoiceVO();
        invoiceVO.setuId(drawOrderVO.getuId());
        invoiceVO.setOrderId(drawOrderVO.getOrderId());
        invoiceVO.setAwardId(drawOrderVO.getAwardId());
        invoiceVO.setAwardType(drawOrderVO.getAwardType());
        invoiceVO.setAwardName(drawOrderVO.getAwardName());
        invoiceVO.setAwardContent(drawOrderVO.getAwardContent());
        invoiceVO.setShippingAddress(null);
        invoiceVO.setExtInfo(null);
        return invoiceVO;
    }
}
