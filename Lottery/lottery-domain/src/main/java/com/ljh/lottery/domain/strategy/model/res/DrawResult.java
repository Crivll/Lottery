package com.ljh.lottery.domain.strategy.model.res;

import com.ljh.lottery.common.Constants;
import com.ljh.lottery.domain.strategy.model.vo.DrawAwardInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * Description: 抽奖结果
 *
 * @Author: ljh
 * DateTime: 2022-05-22 18:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrawResult {

    /**
     * 用户Id
     */
    private String uId;

    /**
     * 策略Id
     */
    private Long strategyId;

    /**
     * 中奖状态：0未中奖、1已中奖、2兜底奖 Constants.DrawState
     */
    private Integer drawState = Constants.DrawState.FAIL.getCode();

    /**
     * 中奖奖品信息
     */
    private DrawAwardInfo drawAwardInfo;

    /**
     * 奖品名称
     */
    private String awardName;

}
