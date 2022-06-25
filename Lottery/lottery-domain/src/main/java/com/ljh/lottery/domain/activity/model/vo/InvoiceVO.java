package com.ljh.lottery.domain.activity.model.vo;

import com.ljh.lottery.domain.award.model.vo.ShippingAddress;

/**
 * Created with IntelliJ IDEA.
 * Description: 中奖物品发货单，用于发送MQ消息，异步触达发货物品给用户
 *
 * @Author: ljh
 * DateTime: 2022-06-25 14:31
 */
public class InvoiceVO {

    /**
     * 用户id
     */
    private String uId;
    /**
     * 抽奖单号id
     */
    private Long orderId;
    /**
     * 奖品id
     */
    private String awardId;
    /**
     * 奖品类型
     */
    private Integer awardType;
    /**
     * 奖品名称
     */
    private String awardName;
    /**
     * 奖品内容【描述、奖品码、sku】
     */
    private String awardContent;
    /**
     * 四级送货地址(只有实物需要地址)
     */
    private ShippingAddress shippingAddress;
    /**
     * 扩展信息，用于一些个性化商品发放所需要透传字段内容
     */
    private String extInfo;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getAwardId() {
        return awardId;
    }

    public void setAwardId(String awardId) {
        this.awardId = awardId;
    }

    public Integer getAwardType() {
        return awardType;
    }

    public void setAwardType(Integer awardType) {
        this.awardType = awardType;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public String getAwardContent() {
        return awardContent;
    }

    public void setAwardContent(String awardContent) {
        this.awardContent = awardContent;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

    @Override
    public String toString() {
        return "InvoiceVO{" +
                "uId='" + uId + '\'' +
                ", orderId=" + orderId +
                ", awardId='" + awardId + '\'' +
                ", awardType=" + awardType +
                ", awardName='" + awardName + '\'' +
                ", awardContent='" + awardContent + '\'' +
                ", shippingAddress=" + shippingAddress +
                ", extInfo='" + extInfo + '\'' +
                '}';
    }
}
