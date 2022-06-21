package com.ljh.lottery.domain.rule.service.logic.impl;

import com.ljh.lottery.domain.rule.model.req.DecisionMatterReq;
import com.ljh.lottery.domain.rule.service.logic.BaseLogic;

/**
 * Created with IntelliJ IDEA.
 * Description: 年龄过滤规则
 *
 * @Author: ljh
 * DateTime: 2022-06-21 12:21
 */
public class UserAgeFilter extends BaseLogic {

    @Override
    public String matterValue(DecisionMatterReq decisionMatterReq) {
        return decisionMatterReq.getValMap().get("age").toString();
    }
}
