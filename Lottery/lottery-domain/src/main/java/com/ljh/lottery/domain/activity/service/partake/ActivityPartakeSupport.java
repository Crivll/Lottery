package com.ljh.lottery.domain.activity.service.partake;

import com.ljh.lottery.domain.activity.model.req.PartakeReq;
import com.ljh.lottery.domain.activity.model.vo.ActivityBillVO;
import com.ljh.lottery.domain.activity.repository.IActivityRepository;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * Description: 活动领取模板操作，一些通用的数据服务
 *
 * @Author: ljh
 * DateTime: 2022-06-10 14:20
 */
public class ActivityPartakeSupport {

    @Resource
    protected IActivityRepository activityRepository;

    protected ActivityBillVO queryActivityBill(PartakeReq req) {
        return activityRepository.queryActivityBill(req);
    }
}
