package com.ljh.lottery.domain.strategy.service.draw;

import com.ljh.lottery.domain.strategy.model.req.DrawReq;
import com.ljh.lottery.domain.strategy.model.res.DrawResult;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: ljh
 * DateTime: 2022-05-22 18:46
 */
public interface IDrawExec {

    DrawResult doDrawExec(DrawReq req);
}
