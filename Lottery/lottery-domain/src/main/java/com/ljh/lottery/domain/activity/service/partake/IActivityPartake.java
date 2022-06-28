package com.ljh.lottery.domain.activity.service.partake;

import com.ljh.lottery.common.Result;
import com.ljh.lottery.domain.activity.model.req.PartakeReq;
import com.ljh.lottery.domain.activity.model.res.PartakeResult;
import com.ljh.lottery.domain.activity.model.vo.DrawOrderVO;
import com.ljh.lottery.domain.activity.model.vo.InvoiceVO;

import java.util.List;

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

    /**
     * 更新发货单MQ状态
     * @param uId
     * @param orderId
     * @param mqState   MQ发送状态
     */
    void updateInvoiceMqState(String uId, Long orderId, Integer mqState);

    /**
     * 扫描发货单MQ状态，把未发送的MQ单子扫描出来做补偿
     * @param dbCount   指定分库
     * @param tbCount   指定分表
     * @return          发货单
     */
    List<InvoiceVO> scanInvoiceMqState(int dbCount, int tbCount);
}
