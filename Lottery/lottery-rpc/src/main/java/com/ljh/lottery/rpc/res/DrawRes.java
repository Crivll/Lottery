package com.ljh.lottery.rpc.res;

import com.ljh.lottery.common.Result;
import com.ljh.lottery.rpc.dto.AwardDTO;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description: 抽奖结果
 *
 * @Author: ljh
 * DateTime: 2022-06-22 10:48
 */
public class DrawRes extends Result implements Serializable {

    private AwardDTO awardDTO;

    public DrawRes(String code, String info) {
        super(code, info);
    }

    public AwardDTO getAwardDTO() {
        return awardDTO;
    }

    public void setAwardDTO(AwardDTO awardDTO) {
        this.awardDTO = awardDTO;
    }
}
