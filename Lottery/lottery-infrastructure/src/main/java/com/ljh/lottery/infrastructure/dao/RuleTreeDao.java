package com.ljh.lottery.infrastructure.dao;

import com.ljh.lottery.infrastructure.po.RuleTree;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 * Description: 规则树Dao
 *
 * @Author: ljh
 * DateTime: 2022-06-21 13:36
 */
@Mapper
public interface RuleTreeDao {

    /**
     * 规则树查询
     * @param id
     * @return
     */
    RuleTree queryRuleTreeById(Long id);

    /**
     * 规则树简要信息查询
     * @param treeId
     * @return
     */
    RuleTree queryTreeSummaryInfo(Long treeId);
}
