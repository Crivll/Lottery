package com.ljh.lottery.application.process;

import com.ljh.lottery.application.process.req.DrawProcessReq;
import com.ljh.lottery.application.process.res.RuleQuantificationResult;
import com.ljh.lottery.common.Result;
import com.ljh.lottery.domain.rule.model.req.DecisionMatterReq;

/**
 * Created with IntelliJ IDEA.
 * Description: 抽奖流程编排接口
 *
 * @Author: ljh
 * DateTime: 2022-06-19 10:31
 */
public interface IActivityProcess {

    /**
     * 执行抽奖流程
     * @param req
     * @return
     */
    Result<Object> doDrawProcess(DrawProcessReq req);

    /**
     * 量化规则人群，返回
     * @param req
     * @return
     */
    RuleQuantificationResult doRuleQuantification(DecisionMatterReq req);
}
