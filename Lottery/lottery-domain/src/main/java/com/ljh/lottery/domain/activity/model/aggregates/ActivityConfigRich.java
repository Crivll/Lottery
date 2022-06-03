package com.ljh.lottery.domain.activity.model.aggregates;

import com.ljh.lottery.domain.activity.model.vo.ActivityVO;
import com.ljh.lottery.domain.activity.model.vo.AwardVO;
import com.ljh.lottery.domain.activity.model.vo.StrategyVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 活动配置聚合信息
 *
 * @Author: ljh
 * DateTime: 2022-06-03 15:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityConfigRich {

    /** 活动配置 */
    private ActivityVO activity;

    /** 策略配置(含策略明细) */
    private StrategyVO strategy;

    /** 奖品配置 */
    private List<AwardVO> awardList;
}
