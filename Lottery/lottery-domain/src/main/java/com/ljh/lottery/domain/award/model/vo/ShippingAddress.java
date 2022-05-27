package com.ljh.lottery.domain.award.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: ljh
 * DateTime: 2022-05-27 20:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShippingAddress {

    /**
     * 收获人
     */
    private String name;

    /**
     * 一级地址ID
     */
    private String provinceId;

    /**
     * 一级地址名称
     */
    private String provinceName;

    /**
     * 二级地址ID
     */
    private String cityId;

    /**
     * 二级地址名称
     */
    private String cityName;

    /**
     * 三级地址ID
     */
    private String countyId;

    /**
     * 三级地址名称
     */
    private String countyName;

    /**
     * 四级地址ID
     */
    private String townId;

    /**
     * 四级地址名称
     */
    private String townName;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 备注
     */
    private String remark;
}
