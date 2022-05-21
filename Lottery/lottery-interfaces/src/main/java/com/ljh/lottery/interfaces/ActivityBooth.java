package com.ljh.lottery.interfaces;

import com.ljh.lottery.common.Constants;
import com.ljh.lottery.common.Result;
import com.ljh.lottery.infrastructure.dao.IActivityDao;
import com.ljh.lottery.infrastructure.po.Activity;
import com.ljh.lottery.rpc.IActivityBooth;
import com.ljh.lottery.rpc.dto.ActivityDto;
import com.ljh.lottery.rpc.req.ActivityReq;
import com.ljh.lottery.rpc.res.ActivityRes;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: ljh
 * DateTime: 2022-05-21 12:12
 */
@Service
public class ActivityBooth implements IActivityBooth {

    @Resource
    private IActivityDao activityDao;


    @Override
    public ActivityRes queryActivityById(ActivityReq req) {

        Activity activity = activityDao.queryActivityById(req.getActivityId());

        ActivityDto activityDto = ActivityDto.builder()
                .activityId(activity.getActivityId())
                .activityName(activity.getActivityName())
                .activityDesc(activity.getActivityDesc())
                .beginDateTime(activity.getBeginDateTime())
                .endDateTime(activity.getEndDateTime()).stockCount(activity.getStockCount())
                .takeCount(activity.getTakeCount()).build();
        return new ActivityRes(new Result(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo()), activityDto);
    }
}
