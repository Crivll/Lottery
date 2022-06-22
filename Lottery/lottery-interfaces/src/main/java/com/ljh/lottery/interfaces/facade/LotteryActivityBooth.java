package com.ljh.lottery.interfaces.facade;

import com.alibaba.fastjson.JSON;
import com.ljh.lottery.application.process.IActivityProcess;
import com.ljh.lottery.application.process.req.DrawProcessReq;
import com.ljh.lottery.application.process.res.RuleQuantificationResult;
import com.ljh.lottery.common.Constants;
import com.ljh.lottery.common.Result;
import com.ljh.lottery.domain.rule.model.req.DecisionMatterReq;
import com.ljh.lottery.domain.strategy.model.vo.DrawAwardVO;
import com.ljh.lottery.interfaces.assembler.IMapping;
import com.ljh.lottery.rpc.ILotteryActivityBooth;
import com.ljh.lottery.rpc.dto.AwardDTO;
import com.ljh.lottery.rpc.req.DrawReq;
import com.ljh.lottery.rpc.req.QuantificationDrawReq;
import com.ljh.lottery.rpc.res.DrawRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * Description: 抽奖活动展台
 *
 * @Author: ljh
 * DateTime: 2022-06-22 10:45
 */
@Controller
public class LotteryActivityBooth implements ILotteryActivityBooth {

    private Logger logger = LoggerFactory.getLogger(LotteryActivityBooth.class);

    @Resource
    private IActivityProcess activityProcess;
    @Resource
    private IMapping<DrawAwardVO, AwardDTO> awardMapping;

    @Override
    public DrawRes doDraw(DrawReq drawReq) {
        try {
            logger.info("抽奖，开始 uId：{} activityId：{}", drawReq.getuId(), drawReq.getActivityId());

            // 1. 执行抽奖
            Result<Object> drawProcessResult = activityProcess.doDrawProcess(new DrawProcessReq(drawReq.getuId(), drawReq.getActivityId()));
            if (!Constants.ResponseCode.SUCCESS.getCode().equals(drawProcessResult.getCode())) {
                logger.error("抽奖过程失败， uid: {}, activityId: {}", drawReq.getuId(), drawReq.getActivityId());
                return new DrawRes(drawProcessResult.getCode(), drawProcessResult.getInfo());
            }
            // 2. 数据转换
            DrawAwardVO drawAwardVO = (DrawAwardVO) drawProcessResult.getData();
            AwardDTO awardDTO = awardMapping.sourceToTarget(drawAwardVO);
            awardDTO.setActivityId(drawReq.getActivityId());

            // 3. 封装数据
            DrawRes drawRes = new DrawRes(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
            drawRes.setAwardDTO(awardDTO);

            logger.info("抽奖完成，uId: {}, activityId: {}, drawRes: {}", drawReq.getuId(), drawReq.getActivityId(), JSON.toJSONString(drawRes));

            return drawRes;
        } catch (Exception e) {
            logger.error("抽奖失败, uId: {}, activityId: {}, reqJson: {}", drawReq.getuId(), drawReq.getActivityId(), JSON.toJSONString(drawReq), e);
            return new DrawRes(Constants.ResponseCode.UN_ERROR.getCode(), Constants.ResponseCode.UN_ERROR.getInfo());
        }
    }

    @Override
    public DrawRes doQuantificationDraw(QuantificationDrawReq quantificationDrawReq) {
        try {
            logger.info("量化人群抽奖，开始 uId：{} treeId：{}", quantificationDrawReq.getuId(), quantificationDrawReq.getTreeId());

            // 1. 执行规则引擎，获取用户能参与的活动号
            RuleQuantificationResult ruleQuantificationResult = activityProcess.doRuleQuantification(new DecisionMatterReq(quantificationDrawReq.getuId(), quantificationDrawReq.getTreeId(), quantificationDrawReq.getValMap()));
            if (!Constants.ResponseCode.SUCCESS.getCode().equals(ruleQuantificationResult.getCode())) {
                logger.error("量化人群抽奖，失败(规则引擎执行异常) uId：{} treeId：{}", quantificationDrawReq.getuId(), quantificationDrawReq.getTreeId());
                return new DrawRes(ruleQuantificationResult.getCode(), ruleQuantificationResult.getInfo());
            }

            // 2. 执行抽奖
            Long activityId = ruleQuantificationResult.getActivityId();
            Result<Object> drawProcessResult = activityProcess.doDrawProcess(new DrawProcessReq(quantificationDrawReq.getuId(), activityId));
            if (!Constants.ResponseCode.SUCCESS.getCode().equals(drawProcessResult.getCode())) {
                logger.error("抽奖过程失败， uid: {}, activityId: {}", quantificationDrawReq.getuId(), activityId);
                return new DrawRes(drawProcessResult.getCode(), drawProcessResult.getInfo());
            }

            // 2. 数据转换
            DrawAwardVO drawAwardVO = (DrawAwardVO) drawProcessResult.getData();
            AwardDTO awardDTO = awardMapping.sourceToTarget(drawAwardVO);
            awardDTO.setActivityId(activityId);

            // 3. 封装数据
            DrawRes drawRes = new DrawRes(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
            drawRes.setAwardDTO(awardDTO);

            logger.info("量化人群抽奖完成，uId: {}, activityId: {}, drawRes: {}", quantificationDrawReq.getuId(), activityId, JSON.toJSONString(drawRes));

            return drawRes;

        } catch (Exception e) {
            logger.error("量化人群抽奖，失败 uId：{} treeId：{} reqJson：{}", quantificationDrawReq.getuId(), quantificationDrawReq.getTreeId(), JSON.toJSONString(quantificationDrawReq), e);
            return new DrawRes(Constants.ResponseCode.UN_ERROR.getCode(), Constants.ResponseCode.UN_ERROR.getInfo());
        }
    }
}
