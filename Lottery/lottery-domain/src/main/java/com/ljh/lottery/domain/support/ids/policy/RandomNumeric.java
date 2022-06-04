package com.ljh.lottery.domain.support.ids.policy;

import com.ljh.lottery.domain.support.ids.IIdGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description: 工具类生成 org.apache.commons.lang3.RandomStringUtils
 *
 * @Author: ljh
 * DateTime: 2022-06-04 19:18
 */
@Component
public class RandomNumeric implements IIdGenerator {
    @Override
    public long nextId() {
        // 生成11位随机字符串转long
        return Long.parseLong(RandomStringUtils.randomNumeric(11));
    }
}
