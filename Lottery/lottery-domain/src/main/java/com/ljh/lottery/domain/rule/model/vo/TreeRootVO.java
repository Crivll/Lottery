package com.ljh.lottery.domain.rule.model.vo;

/**
 * Created with IntelliJ IDEA.
 * Description: 规则树根配置
 *
 * @Author: ljh
 * DateTime: 2022-06-20 14:19
 */
public class TreeRootVO {

    /**
     * 规则树ID
     */
    private Long treeId;
    /**
     * 规则树根节点ID
     */
    private Long treeRootNodeId;
    /**
     * 规则树名
     */
    private String treeName;

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public Long getTreeRootNodeId() {
        return treeRootNodeId;
    }

    public void setTreeRootNodeId(Long treeRootNodeId) {
        this.treeRootNodeId = treeRootNodeId;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    @Override
    public String toString() {
        return "TreeRootVO: {" +
                "treeId = " + treeId +
                ", treeRootNodeId = " + treeRootNodeId +
                ", treeName = '" + treeName + "'" +
                "}";
    }
}
