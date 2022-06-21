package com.ljh.lottery.domain.rule.service.engine;

import com.ljh.lottery.domain.rule.model.req.DecisionMatterReq;
import com.ljh.lottery.domain.rule.model.res.EngineResult;

/**
 * Created with IntelliJ IDEA.
 * Description: 规则过滤器引擎
 *
 * @Author: ljh
 * DateTime: 2022-06-21 12:24
 */
public interface EngineFilter {

    /**
     * 规则过滤器入口
     * @param matter    规则决策物料
     * @return          规则决策结果
     */
    EngineResult process(final DecisionMatterReq matter);
}
