package com.ljh.lottery.infrastructure.dao;


import com.ljh.lottery.infrastructure.po.UserStrategyExport;
import com.ljh.middleware.db.router.annotation.DBRouter;
import com.ljh.middleware.db.router.annotation.DBRouterStrategy;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: ljh
 * DateTime: 2022-06-08 16:10
 */
@Mapper
@DBRouterStrategy(splitTable = true)
public interface IUserStrategyExportDao {

    /**
     * 新增数据
     * @param userStrategyExport
     */
    @DBRouter(key = "uId")
    void insert(UserStrategyExport userStrategyExport);

    /**
     * 查询数据
     * @param uId
     * @return
     */
    @DBRouter
    UserStrategyExport queryUserStrategyExportByUId(String uId);
}
