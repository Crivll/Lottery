package com.ljh.lottery.domain.support.ids;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: ljh
 * DateTime: 2022-06-04 19:08
 */
public interface IIdGenerator {

    /**
     * 获取Id，目前有两种实现方式
     * 1. 雪花算法，用于生成单号
     * 2. 日期算法，用于生成活动编号类，特性是生成数字串较短，但指定时间内不能生成太多
     * 3. 随机算法， 用于生成策略Id
     *
     * @return
     */
    long nextId();
}
