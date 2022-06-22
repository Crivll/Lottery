package com.ljh.lottery.interfaces.facade;

import com.ljh.lottery.rpc.ILotteryActivityBooth;
import com.ljh.lottery.rpc.req.DrawReq;
import com.ljh.lottery.rpc.req.QuantificationDrawReq;
import com.ljh.lottery.rpc.res.DrawRes;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: ljh
 * DateTime: 2022-06-22 10:45
 */
public class LotteryActivityBooth implements ILotteryActivityBooth {
    @Override
    public DrawRes doDraw(DrawReq drawReq) {
        return null;
    }

    @Override
    public DrawRes doQuantificationDraw(QuantificationDrawReq quantificationDrawReq) {
        return null;
    }
}
