package com.ljh.lottery.infrastructure.po;

/**
 * Created with IntelliJ IDEA.
 * Description: 规则树节点
 *
 * @Author: ljh
 * DateTime: 2022-06-21 13:42
 */
public class RuleTreeNode {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 规则树id
     */
    private Long treeId;
    /**
     * 节点类型：1.非叶节点，2.叶节点
     */
    private Integer nodeType;
    /**
     * 节点值[nodeType = 2，叶节点值]
     */
    private String nodeValue;
    /**
     * 规则key
     */
    private String ruleKey;
    /**
     * 规则描述
     */
    private String ruleDesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public Integer getNodeType() {
        return nodeType;
    }

    public void setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }

    public String getRuleKey() {
        return ruleKey;
    }

    public void setRuleKey(String ruleKey) {
        this.ruleKey = ruleKey;
    }

    public String getRuleDesc() {
        return ruleDesc;
    }

    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc;
    }
}
