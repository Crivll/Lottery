package com.ljh.lottery.infrastructure.repository;

import com.ljh.lottery.common.Constants;
import com.ljh.lottery.domain.rule.model.aggregates.TreeRuleRich;
import com.ljh.lottery.domain.rule.model.vo.TreeNodeLineVO;
import com.ljh.lottery.domain.rule.model.vo.TreeNodeVO;
import com.ljh.lottery.domain.rule.model.vo.TreeRootVO;
import com.ljh.lottery.domain.rule.repository.IRuleRepository;
import com.ljh.lottery.infrastructure.dao.RuleTreeDao;
import com.ljh.lottery.infrastructure.dao.RuleTreeNodeDao;
import com.ljh.lottery.infrastructure.dao.RuleTreeNodeLineDao;
import com.ljh.lottery.infrastructure.po.RuleTree;
import com.ljh.lottery.infrastructure.po.RuleTreeNode;
import com.ljh.lottery.infrastructure.po.RuleTreeNodeLine;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 规则信息仓储服务
 *
 * @Author: ljh
 * DateTime: 2022-06-21 13:35
 */
@Repository
public class RuleRepository implements IRuleRepository {

    @Resource
    private RuleTreeDao ruleTreeDao;
    @Resource
    private RuleTreeNodeDao ruleTreeNodeDao;
    @Resource
    private RuleTreeNodeLineDao ruleTreeNodeLineDao;

    @Override
    public TreeRuleRich queryTreeRuleRich(Long treeId) {
        // 规则树
        RuleTree ruleTree = ruleTreeDao.queryRuleTreeById(treeId);
        TreeRootVO treeRoot = new TreeRootVO();
        treeRoot.setTreeId(ruleTree.getId());
        treeRoot.setTreeRootNodeId(ruleTree.getTreeRootNodeId());
        treeRoot.setTreeName(ruleTree.getTreeName());

        // 树节点 -> 树连接线
        Map<Long, TreeNodeVO> treeNodeMap = new HashMap<>();
        List<RuleTreeNode> ruleTreeNodeList = ruleTreeNodeDao.queryRuleTreeNodeList(treeId);
        // 对于当前规则树的每一个节点
        for (RuleTreeNode treeNode: ruleTreeNodeList) {

            List<TreeNodeLineVO> treeNodeLineInfoList = new ArrayList<>();
            if (Constants.NodeType.STEM.equals(treeNode.getNodeType())) {
                RuleTreeNodeLine ruleTreeNodeLineReq = new RuleTreeNodeLine();
                ruleTreeNodeLineReq.setTreeId(treeId);
                ruleTreeNodeLineReq.setNodeIdFrom(treeNode.getId());
                List<RuleTreeNodeLine> ruleTreeNodeLineList = ruleTreeNodeLineDao.queryRuleTreeNodeLineList(ruleTreeNodeLineReq);

                for (RuleTreeNodeLine nodeLine : ruleTreeNodeLineList) {
                    TreeNodeLineVO treeNodeInfo = new TreeNodeLineVO();
                    // 暂时使用BeanUtils自动注入属性
                    BeanUtils.copyProperties(nodeLine, treeNodeInfo);
//                    treeNodeInfo.setNodeIdFrom(nodeLine.getNodeIdFrom());
//                    treeNodeInfo.setNodeIdTo(nodeLine.getNodeIdTo());
//                    treeNodeInfo.setRuleLimitType(nodeLine.getRuleLimitType());
//                    treeNodeInfo.setRuleLimitValue(nodeLine.getRuleLimitValue());
                    treeNodeLineInfoList.add(treeNodeInfo);
                }
            }
            TreeNodeVO treeNodeInfo = new TreeNodeVO();
            BeanUtils.copyProperties(treeNode, treeNodeInfo);
            treeNodeInfo.setTreeNodeLineInfoList(treeNodeLineInfoList);
            treeNodeInfo.setTreeNodeId(treeNode.getId());

            treeNodeMap.put(treeNode.getId(), treeNodeInfo);
        }

        TreeRuleRich treeRuleRich = new TreeRuleRich();
        treeRuleRich.setTreeRoot(treeRoot);
        treeRuleRich.setTreeNodeMap(treeNodeMap);
        return treeRuleRich;
    }
}
