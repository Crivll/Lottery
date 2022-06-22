package com.ljh.lottery.test.test;

import com.alibaba.fastjson.JSON;
import com.ljh.lottery.rpc.ILotteryActivityBooth;
import com.ljh.lottery.rpc.req.DrawReq;
import com.ljh.lottery.rpc.req.QuantificationDrawReq;
import com.ljh.lottery.rpc.res.DrawRes;
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
 * DateTime: 2022-06-23 0:43
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class LotteryActivityBoothTest {

    private Logger logger = LoggerFactory.getLogger(LotteryActivityBoothTest.class);

    @Resource
    private ILotteryActivityBooth lotteryActivityBooth;

    @Test
    public void test_doDraw() {
        DrawReq drawReq = new DrawReq();
        drawReq.setuId("xiaofuge");
        drawReq.setActivityId(100001L);
        DrawRes drawRes = lotteryActivityBooth.doDraw(drawReq);
        logger.info("请求参数：{}", JSON.toJSONString(drawReq));
        logger.info("测试结果：{}", JSON.toJSONString(drawRes));
    }

    @Test
    public void test_doQuantificationDraw() {
        QuantificationDrawReq quantificationDrawReq = new QuantificationDrawReq();
        quantificationDrawReq.setuId("xiaofuge");
        quantificationDrawReq.setTreeId(2110081902L);
        quantificationDrawReq.setValMap(new HashMap<String, Object>() {{
            put("gender", "man");
            put("age", "18");
        }});

        DrawRes drawRes = lotteryActivityBooth.doQuantificationDraw(quantificationDrawReq);
        logger.info("请求参数：{}", JSON.toJSONString(quantificationDrawReq));
        logger.info("测试结果：{}", JSON.toJSONString(drawRes));
    }


}
