package com.ljh.lottery.domain.award.service.goods.impl;

import com.ljh.lottery.common.Constants;
import com.ljh.lottery.common.Result;
import com.ljh.lottery.domain.award.model.req.GoodsReq;
import com.ljh.lottery.domain.award.model.res.DistributionRes;
import com.ljh.lottery.domain.award.service.DistributionBase;
import com.ljh.lottery.domain.award.service.goods.IDistributionGoods;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: ljh
 * DateTime: 2022-05-27 20:48
 */
@Component
public class CouponGoods extends DistributionBase implements IDistributionGoods {
    @Override
    public DistributionRes doDistribution(GoodsReq req) {

        // 模拟调用优惠券发放接口
        logger.info("模拟调用优惠券发放接口 uId：{} awardContent：{}", req.getuId(), req.getAwardContent());

        // 更新用户领奖结果
        super.updateUserAwardState(req.getuId(),
                req.getOrderId(), req.getAwardId(),
                Constants.GrantState.COMPLETE.getCode());
        return DistributionRes.builder().uId(req.getuId())
                .code(Constants.AwardState.SUCCESS.getCode())
                .info(Constants.AwardState.SUCCESS.getInfo()).build();
    }

    @Override
    public Integer getDistributionGoodsName() {
        return Constants.AwardType.CouponGoods.getCode();
    }
}
