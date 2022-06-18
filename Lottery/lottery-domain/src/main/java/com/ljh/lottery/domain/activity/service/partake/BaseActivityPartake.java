package com.ljh.lottery.domain.activity.service.partake;

import com.ljh.lottery.common.Constants;
import com.ljh.lottery.common.Result;
import com.ljh.lottery.domain.activity.model.req.PartakeReq;
import com.ljh.lottery.domain.activity.model.res.PartakeResult;
import com.ljh.lottery.domain.activity.model.vo.ActivityBillVO;
import com.ljh.lottery.domain.activity.model.vo.UserTakeActivityVO;
import com.ljh.lottery.domain.support.ids.IIdGenerator;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 活动领取模板抽象类
 *
 * @Author: ljh
 * DateTime: 2022-06-10 14:25
 */
public abstract class BaseActivityPartake extends ActivityPartakeSupport implements IActivityPartake{

    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;

    @Override
    public Result<PartakeResult> doPartake(PartakeReq req) {

        // 1. 查询是否存在未执行抽奖领取活动单【user_take_activity 存在 state = 0，领取了但抽奖过程失败的，可以直接返回领取结果继续抽奖】
        UserTakeActivityVO userTakeActivityVO = this.queryNoConsumedTakeActivityOrder(req.getActivityId(), req.getuId());
        if (null != userTakeActivityVO) {
            return Result.success(PartakeResult.builder()
                    .strategyId(userTakeActivityVO.getStrategyId())
                    .takeId(userTakeActivityVO.getTakeId()).build());
        }

        // 2. 查询活动账单
        ActivityBillVO activityBillVO = super.queryActivityBill(req);

        // 3. 活动信息校验处理【活动库存、状态、日期、个人参与次数】
        Result checkResult = this.checkActivityBill(req, activityBillVO);
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(checkResult.getCode())) {
            return checkResult;
        }

        // 4. 扣减活动库存【目前为直接对配置库中的lottery.activity进行扣减库存，后续优化为redis扣减】
        Result subtractionActivityResult = this.subtractionActivityStock(req);
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(subtractionActivityResult.getCode())) {
            return subtractionActivityResult;
        }

        // 5. 插入领取活动信息【个人用户把活动信息写入到用户表】
        Long takeId = idGeneratorMap.get(Constants.Ids.SnowFlake).nextId();
        Result grabResult = this.grabActivity(req, activityBillVO, takeId);
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(grabResult.getCode())) {
            return grabResult;
        }

        // 封装结果【返回的策略ID，用于继续完成抽奖步骤】
        return Result.success(PartakeResult.builder().strategyId(activityBillVO.getStrategyId()).build());
    }

    /**
     * 查询是否存在未执行的抽奖活动领取单
     * @param activityId
     * @param uId
     * @return
     */
    protected abstract UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId, String uId);

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
    protected abstract Result grabActivity(PartakeReq partake, ActivityBillVO bill, Long takeId);
}
