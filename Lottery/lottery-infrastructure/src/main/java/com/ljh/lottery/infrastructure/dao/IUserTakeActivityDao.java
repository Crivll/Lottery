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
    void insert(UserTakeActivity userTakeActivity);

    /**
     * 锁定活动领取记录
     * @param userTakeActivity
     * @return
     */
    int lockTackActivity(UserTakeActivity userTakeActivity);

    /**
     * 查询是否存在未执行抽奖活动领取活动单
     * 【user_take_activity 中以state字段表示是否已执行，如果未执行，那就返回结果继续执行，不用重复领取】
     * 查询此活动ID，用户最早领取但未消费的一条记录【该情形下一般有业务限制，比如是否处理最先或者最新领取单，根据实际业务调整】
     * @param userTakeActivity
     * @return
     */
    @DBRouter
    UserTakeActivity queryNoConsumedTakeActivityOrder(UserTakeActivity userTakeActivity);

}
