package com.ljh.lottery.rpc;

import com.ljh.lottery.rpc.req.ActivityReq;
import com.ljh.lottery.rpc.res.ActivityRes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 活动展台
 * 1. 创建活动
 * 2. 更新活动
 * 3. 查询活动
 * @Author: ljh
 * DateTime: 2022-05-21 11:42
 */
public interface IActivityBooth {

    ActivityRes queryActivityById(ActivityReq req);
}
