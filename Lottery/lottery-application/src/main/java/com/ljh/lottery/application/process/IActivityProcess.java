package com.ljh.lottery.application.process;

import com.ljh.lottery.application.process.req.DrawProcessReq;
import com.ljh.lottery.common.Result;

/**
 * Created with IntelliJ IDEA.
 * Description: 抽奖流程编排接口
 *
 * @Author: ljh
 * DateTime: 2022-06-19 10:31
 */
public interface IActivityProcess {

    /**
     * 执行抽奖流程
     * @param req
     * @return
     */
    Result<Object> doDrawProcess(DrawProcessReq req);
}
