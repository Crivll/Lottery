package com.ljh.lottery.domain.rule.repository;

import com.ljh.lottery.domain.rule.model.aggregates.TreeRuleRich;

/**
 * Created with IntelliJ IDEA.
 * Description: 规则信息仓储服务接口
 *
 * @Author: ljh
 * DateTime: 2022-06-21 13:32
 */
public interface IRuleRepository {

    /**
     * 查询决策树配置聚合对象
     * @param treeId    决策树ID
     * @return          决策树聚合对象
     */
    TreeRuleRich queryTreeRuleRich(Long treeId);
}
