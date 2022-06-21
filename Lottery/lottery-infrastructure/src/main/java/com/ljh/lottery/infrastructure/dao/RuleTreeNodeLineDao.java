package com.ljh.lottery.infrastructure.dao;

import com.ljh.lottery.infrastructure.po.RuleTreeNodeLine;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 规则树节点连接Dao
 *
 * @Author: ljh
 * DateTime: 2022-06-21 13:37
 */
@Mapper
public interface RuleTreeNodeLineDao {

    /**
     * 查询规则树节点连线集合
     * @param req
     * @return
     */
    List<RuleTreeNodeLine> queryRuleTreeNodeLineList(RuleTreeNodeLine req);

    /**
     * 查询规则树连线数量
     * @param treeId
     * @return
     */
    int queryTreeNodeLineCount(Long treeId);
}
