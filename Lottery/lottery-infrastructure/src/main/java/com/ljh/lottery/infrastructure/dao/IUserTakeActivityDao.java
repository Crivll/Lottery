package com.ljh.lottery.infrastructure.dao;

import com.ljh.lottery.infrastructure.po.UserTakeActivity;
import com.ljh.middleware.db.router.annotation.DBRouter;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: ljh
 * DateTime: 2022-06-08 16:29
 */
@Mapper
public interface IUserTakeActivityDao {

    /**
     * 插入用户领取活动信息
     * @param userTakeActivity
     */
    @DBRouter(key = "uId")
    void insert(UserTakeActivity userTakeActivity);

}
