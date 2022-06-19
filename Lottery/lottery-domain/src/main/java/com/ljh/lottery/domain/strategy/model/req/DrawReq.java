package com.ljh.lottery.domain.strategy.model.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: ljh
 * DateTime: 2022-05-22 18:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrawReq {

    /**
     * 用户Id
     */
    private String uId;

    /**
     * 策略Id
     */
    private Long strategyId;

    /**
     * 防重ID
     */
    private String uuid;

    public DrawReq(String uId, Long strategyId) {
        this.uId = uId;
        this.strategyId = strategyId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
