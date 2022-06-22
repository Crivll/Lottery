package com.ljh.lottery.rpc;

import com.ljh.lottery.rpc.req.DrawReq;
import com.ljh.lottery.rpc.req.QuantificationDrawReq;
import com.ljh.lottery.rpc.res.DrawRes;

/**
 * Created with IntelliJ IDEA.
 * Description: 抽奖活动展台接口
 *
 * @Author: ljh
 * DateTime: 2022-06-22 10:46
 */
public interface ILotteryActivityBooth {

    /**
     * 指定活动抽奖
     * @param drawReq
     * @return
     */
    DrawRes doDraw(DrawReq drawReq);

    /**
     * 量化人群抽奖
     * @param quantificationDrawReq
     * @return
     */
    DrawRes doQuantificationDraw(QuantificationDrawReq quantificationDrawReq);
}
