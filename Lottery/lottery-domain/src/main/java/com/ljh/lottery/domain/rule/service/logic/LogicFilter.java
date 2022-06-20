package com.ljh.lottery.domain.rule.service.logic;

import com.ljh.lottery.domain.rule.model.req.DecisionMatterReq;
import com.ljh.lottery.domain.rule.model.vo.TreeNodeLineVO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 规则过滤器接口
 *
 * @Author: ljh
 * DateTime: 2022-06-20 14:17
 */
public interface LogicFilter {

    /**
     * 逻辑决策器
     * @param matterValue           决策值
     * @param treeNodeLineInfoList  决策节点
     * @return                      下一个节点ID
     */
    Long filter(String matterValue, List<TreeNodeLineVO> treeNodeLineInfoList);

    /**
     * 获取决策值
     * @param decisionMatterReq 决策物料
     * @return                  决策值
     */
    String matterValue(DecisionMatterReq decisionMatterReq);

}
