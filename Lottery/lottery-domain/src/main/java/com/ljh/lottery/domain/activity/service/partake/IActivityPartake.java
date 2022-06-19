package com.ljh.lottery.domain.activity.service.partake;

import com.ljh.lottery.common.Result;
import com.ljh.lottery.domain.activity.model.req.PartakeReq;
import com.ljh.lottery.domain.activity.model.res.PartakeResult;
import com.ljh.lottery.domain.activity.model.vo.DrawOrderVO;

/**
 * Created with IntelliJ IDEA.
 * Description: 抽奖活动参与接口
 *
 * @Author: ljh
 * DateTime: 2022-06-03 16:16
 */
public interface IActivityPartake {

    /**
     * 参与活动
     * @param req
     * @return
     */
    Result<PartakeResult> doPartake(PartakeReq req);

    /**
     * 保存奖品单
     * @param drawOrder
     * @return
     */
    Result recordDrawOrder(DrawOrderVO drawOrder);
}
