package com.ljh.lottery.domain.rule.service.engine.impl;

import com.ljh.lottery.domain.rule.model.aggregates.TreeRuleRich;
import com.ljh.lottery.domain.rule.model.req.DecisionMatterReq;
import com.ljh.lottery.domain.rule.model.res.EngineResult;
import com.ljh.lottery.domain.rule.model.vo.TreeNodeVO;
import com.ljh.lottery.domain.rule.repository.IRuleRepository;
import com.ljh.lottery.domain.rule.service.engine.EngineBase;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * Description: 规则引擎处理器
 *
 * @Author: ljh
 * DateTime: 2022-06-21 13:30
 */
@Service("ruleEngineHandler")
public class RuleEngineHandler extends EngineBase {

    @Resource
    private IRuleRepository ruleRepository;
    @Override
    public EngineResult process(DecisionMatterReq matter) {
        // 决策规则树
        TreeRuleRich treeRuleRich = ruleRepository.queryTreeRuleRich(matter.getTreeId());
        if (null == treeRuleRich) {
            throw new RuntimeException("Tree rule is null");
        }

        // 决策节点
        TreeNodeVO treeNodeInfo = engineDecisionMaker(treeRuleRich, matter);

        // 决策结果
        return new EngineResult(matter.getUserId(), matter.getTreeId(), treeNodeInfo.getTreeNodeId(), treeNodeInfo.getNodeValue());
    }
}
