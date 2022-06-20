package com.ljh.lottery.domain.rule.model.req;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 决策物料
 *
 * @Author: ljh
 * DateTime: 2022-06-20 14:35
 */
public class DecisionMatterReq {
    /**
     * 规则树ID
     */
    private Long treeId;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 决策值
     */
    private Map<String, Object> valMap;

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<String, Object> getValMap() {
        return valMap;
    }

    public void setValMap(Map<String, Object> valMap) {
        this.valMap = valMap;
    }
}