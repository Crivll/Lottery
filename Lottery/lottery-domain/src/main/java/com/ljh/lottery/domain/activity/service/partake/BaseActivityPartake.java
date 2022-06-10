package com.ljh.lottery.domain.activity.service.partake;

import com.ljh.lottery.common.Constants;
import com.ljh.lottery.common.Result;
import com.ljh.lottery.domain.activity.model.req.PartakeReq;
import com.ljh.lottery.domain.activity.model.res.PartakeResult;
import com.ljh.lottery.domain.activity.model.vo.ActivityBillVO;

/**
 * Created with IntelliJ IDEA.
 * Description: 活动领取模板抽象类
 *
 * @Author: ljh
 * DateTime: 2022-06-10 14:25
 */
public abstract class BaseActivityPartake extends ActivityPartakeSupport implements IActivityPartake{
    @Override
    public Result<PartakeResult> doPartake(PartakeReq req) {
        // 查询活动账单
        ActivityBillVO activityBillVO = super.queryActivityBill(req);

        // 活动信息校验处理【活动库存、状态、日期、个人参与次数】
        Result checkResult = this.checkActivityBill(req, activityBillVO);
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(checkResult.getCode())) {
            return checkResult;
        }

        // 扣减活动库存【目前为直接对配置库中的lottery.activity进行扣减库存，后续优化为redis扣减】
        Result subtractionActivityResult = this.subtractionActivityStock(req);
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(subtractionActivityResult.getCode())) {
            return subtractionActivityResult;
        }

        // 领取活动信息【个人用户把活动信息写入到用户表】
        Result grabResult = this.grabActivity(req, activityBillVO);
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(grabResult.getCode())) {
            return grabResult;
        }

        // 封装结果【返回的策略ID，用于继续完成抽奖步骤】
        return Result.success(PartakeResult.builder().strategyId(activityBillVO.getStrategyId()).build());
    }

    /**
     * 活动信息校验处理，活动库存、状态、日期、个人参与次数
     * @param partake
     * @param bill
     * @return
     */
    protected abstract Result checkActivityBill(PartakeReq partake, ActivityBillVO bill);

    /**
     * 扣减活动库存
     * @param req
     * @return
     */
    protected abstract Result subtractionActivityStock(PartakeReq req);

    /**
     * 领取活动
     * @param partake
     * @param bill
     * @return
     */
    protected abstract Result grabActivity(PartakeReq partake, ActivityBillVO bill);
}
