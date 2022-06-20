package com.ljh.lottery.domain.rule.model.vo;

/**
 * Created with IntelliJ IDEA.
 * Description: 规则树线信息（链路）
 *
 * @Author: ljh
 * DateTime: 2022-06-20 14:27
 */
public class TreeNodeLineVO {
    /**
     * from节点id
     */
    private Long nodeIdFrom;
    /**
     * to节点id
     */
    private Long nodeIdTo;
    /**
     * 限定类型：1 : =; 2 : >; 3 : <; 4 : >=; 5 : <=; 6 : enum[枚举范围]
     */
    private Integer ruleLimitType;

    private String ruleLimitValue;

    public Long getNodeIdFrom() {
        return nodeIdFrom;
    }

    public void setNodeIdFrom(Long nodeIdFrom) {
        this.nodeIdFrom = nodeIdFrom;
    }

    public Long getNodeIdTo() {
        return nodeIdTo;
    }

    public void setNodeIdTo(Long nodeIdTo) {
        this.nodeIdTo = nodeIdTo;
    }

    public Integer getRuleLimitType() {
        return ruleLimitType;
    }

    public void setRuleLimitType(Integer ruleLimitType) {
        this.ruleLimitType = ruleLimitType;
    }

    public String getRuleLimitValue() {
        return ruleLimitValue;
    }

    public void setRuleLimitValue(String ruleLimitValue) {
        this.ruleLimitValue = ruleLimitValue;
    }

    @Override
    public String toString() {
        return "TreeNodeLineVO{" +
                "nodeIdFrom=" + nodeIdFrom +
                ", nodeIdTo=" + nodeIdTo +
                ", ruleLimitType=" + ruleLimitType +
                ", ruleLimitValue='" + ruleLimitValue + '\'' +
                '}';
    }
}
