package com.ljh.lottery.infrastructure.dao;

import com.ljh.lottery.infrastructure.po.Strategy;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: ljh
 * DateTime: 2022-05-25 23:06
 */
@Mapper
public interface IStrategyDao {

    Strategy queryStrategy(Long strategyId);
}
