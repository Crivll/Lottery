package com.ljh.lottery.domain.strategy.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description: 奖品概率信息，奖品编号、库存、概率
 *
 * @Author: ljh
 * DateTime: 2022-05-22 13:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AwardRateInfo {

    // 奖品ID
    private String awardId;

    // 中奖概率
    private BigDecimal awardRate;
}
