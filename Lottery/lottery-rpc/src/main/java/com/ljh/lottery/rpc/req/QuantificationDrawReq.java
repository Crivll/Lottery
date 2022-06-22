package com.ljh.lottery.rpc.req;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 量化人群抽奖请求
 *
 * @Author: ljh
 * DateTime: 2022-06-22 10:48
 */
public class QuantificationDrawReq {
    /**
     * 用户id
     */
    private String uId;
    /**
     * 规则树id
     */
    private Long treeId;
    /**
     * 决策值
     */
    private Map<String, Object> valMap;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public Map<String, Object> getValMap() {
        return valMap;
    }

    public void setValMap(Map<String, Object> valMap) {
        this.valMap = valMap;
    }
}
