package com.ljh.lottery.domain.rule.service.engine;

import com.ljh.lottery.domain.rule.service.logic.LogicFilter;
import com.ljh.lottery.domain.rule.service.logic.impl.UserAgeFilter;
import com.ljh.lottery.domain.rule.service.logic.impl.UserGenderFilter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * Description: 规则配置
 *
 * @Author: ljh
 * DateTime: 2022-06-21 12:24
 */
public class EngineConfig {

    protected static Map<String, LogicFilter> logicFilterMap = new ConcurrentHashMap<>();

    @Resource
    private UserAgeFilter userAgeFilter;
    @Resource
    private UserGenderFilter userGenderFilter;

    @PostConstruct
    public void init() {
        logicFilterMap.put("userAge", userAgeFilter);
        logicFilterMap.put("userGender", userGenderFilter);
    }
}
