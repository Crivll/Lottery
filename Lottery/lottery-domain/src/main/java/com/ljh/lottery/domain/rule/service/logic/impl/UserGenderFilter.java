package com.ljh.lottery.domain.rule.service.logic.impl;

import com.ljh.lottery.domain.rule.model.req.DecisionMatterReq;
import com.ljh.lottery.domain.rule.service.logic.BaseLogic;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description: 性别过滤规则
 *
 * @Author: ljh
 * DateTime: 2022-06-21 12:21
 */
@Component
public class UserGenderFilter extends BaseLogic {

    @Override
    public String matterValue(DecisionMatterReq decisionMatterReq) {
        return decisionMatterReq.getValMap().get("gender").toString();
    }
}
