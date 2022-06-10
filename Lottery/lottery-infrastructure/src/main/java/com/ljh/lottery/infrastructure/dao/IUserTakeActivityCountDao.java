package com.ljh.lottery.infrastructure.dao;

import com.ljh.lottery.infrastructure.po.UserTakeActivityCount;
import com.ljh.middleware.db.router.annotation.DBRouter;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: ljh
 * DateTime: 2022-06-10 20:29
 */
@Mapper
public interface IUserTakeActivityCountDao {

    /**
     * 查询用户领取次数信息
     * @param req 请求入参【活动号，用户ID】
     * @return    领取结果
     */
    @DBRouter
    UserTakeActivityCount queryUserTakeActivityCount(UserTakeActivityCount req);

    /**
     * 插入领取次数信息
     * @param userTakeActivityCount
     */
    void insert(UserTakeActivityCount userTakeActivityCount);

    /**
     * 更新领取次数信息
     * @param userTakeActivityCount
     * @return
     */
    int updateLeftCount(UserTakeActivityCount userTakeActivityCount);
}
