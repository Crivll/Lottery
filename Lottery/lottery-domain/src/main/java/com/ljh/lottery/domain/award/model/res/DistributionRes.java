package com.ljh.lottery.domain.award.model.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: ljh
 * DateTime: 2022-05-27 20:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DistributionRes {

    /**
     * 用户ID
     */
    private String uId;

    /**
     * 编码
     */
    private Integer code;

    /**
     * 描述
     */
    private String info;

    /**
     * 结算单ID，如：发券后有券码、发货后有单号等，用于存根查询
     */
    private String statementId;
}
