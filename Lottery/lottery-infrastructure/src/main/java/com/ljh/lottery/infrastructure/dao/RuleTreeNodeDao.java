package com.ljh.lottery.infrastructure.dao;

import com.ljh.lottery.infrastructure.po.RuleTreeNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 规则树节点Dao
 *
 * @Author: ljh
 * DateTime: 2022-06-21 13:36
 */
@Mapper
public interface RuleTreeNodeDao {

    /**
     * 查询规则树节点
     * @param treeId
     * @return
     */
    List<RuleTreeNode> queryRuleTreeNodeList(Long treeId);

    /**
     * 查询规则树节点数量
     * @param treeId
     * @return
     */
    int queryTreeNodeCount(Long treeId);

    /**
     * 查询规则树节点
     * @param treeId
     * @return
     */
    List<RuleTreeNode> queryTreeRulePoint(Long treeId);
}
