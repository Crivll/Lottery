package com.ljh.lottery.application.mq.consumer;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSON;
import com.ljh.lottery.common.Constants;
import com.ljh.lottery.domain.activity.model.vo.InvoiceVO;
import com.ljh.lottery.domain.award.model.req.GoodsReq;
import com.ljh.lottery.domain.award.model.res.DistributionRes;
import com.ljh.lottery.domain.award.service.factory.DistributionGoodsFactory;
import com.ljh.lottery.domain.award.service.goods.IDistributionGoods;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description: 中奖发货及监听消息
 *
 * @Author: ljh
 * DateTime: 2022-06-25 15:35
 */
public class LotteryInvoiceListener {

    private Logger logger = LoggerFactory.getLogger(LotteryInvoiceListener.class);

    @Resource
    private DistributionGoodsFactory distributionGoodsFactory;

    public void onMessage(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional<?> message = Optional.ofNullable(record.value());

        // 1. 判断消息是否存在
        if (!message.isPresent()) {
            return;
        }

        // 2. 处理MQ消息
        try {
            // 1. 转化对象(可自行重写Serializer<T>)
            InvoiceVO invoiceVO = JSON.parseObject((String) message.get(), InvoiceVO.class);

            // 2. 获取发送奖品工厂，执行发奖
            IDistributionGoods distributionGoodsService = distributionGoodsFactory.getDistributionGoodsService(invoiceVO.getAwardType());
            DistributionRes distributionRes = distributionGoodsService.doDistribution(new GoodsReq(invoiceVO.getuId(), invoiceVO.getOrderId(), invoiceVO.getAwardId(), invoiceVO.getAwardName(), invoiceVO.getAwardContent()));
            Assert.isTrue(Constants.ResponseCode.SUCCESS.getCode().equals(distributionRes.getCode()), distributionRes.getInfo());

            // 3. 打印日志
            logger.info("消费MQ消息， 完成 topic: {}, bizId: {}, 发奖结果: {}", topic, invoiceVO.getuId(), JSON.toJSONString(distributionRes));

            // 4. 消息消费完成
            ack.acknowledge();
        } catch (Exception e) {
            // 发奖环节失败，消息重试。发奖所有环节，发货、更新库等都需要保证幂等
            logger.error("消费MQ消息， 失败 topic: {}, message: {}", topic, message.get());
            throw e;
        }
    }
}
