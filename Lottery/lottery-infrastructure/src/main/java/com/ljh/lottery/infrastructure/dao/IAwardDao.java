package com.ljh.lottery.infrastructure.dao;

import com.ljh.lottery.infrastructure.po.Award;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 奖品dao接口
 *
 * @Author: ljh
 * DateTime: 2022-05-25 23:09
 */
@Mapper
public interface IAwardDao {
    /**
     * 查询奖品信息
     * @param awardId
     * @return
     */
    Award queryAwardInfo(String awardId);

    /**
     * 插入奖品配置
     *
     * @param list 奖品配置
     */
    void insertList(List<Award> list);
}
