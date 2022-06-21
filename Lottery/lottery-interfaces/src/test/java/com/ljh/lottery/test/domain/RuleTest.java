package com.ljh.lottery.test.domain;

import com.alibaba.fastjson.JSON;
import com.ljh.lottery.domain.rule.model.req.DecisionMatterReq;
import com.ljh.lottery.domain.rule.model.res.EngineResult;
import com.ljh.lottery.domain.rule.service.engine.EngineFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: ljh
 * DateTime: 2022-06-21 21:15
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RuleTest {

    private Logger logger = LoggerFactory.getLogger(RuleTest.class);

    @Resource
    private EngineFilter engineFilter;

    @Test
    public void test_process() {
        DecisionMatterReq req = new DecisionMatterReq();
        req.setTreeId(2110081902L);
        req.setUserId("fustack");
        req.setValMap(new HashMap<String, Object>() {{
            put("gender", "man");
            put("age", "25");
        }});

        EngineResult engineResult = engineFilter.process(req);

        logger.info("请求参数：{}", JSON.toJSONString(req));
        logger.info("测试结果：{}", JSON.toJSONString(engineResult));
    }
}
