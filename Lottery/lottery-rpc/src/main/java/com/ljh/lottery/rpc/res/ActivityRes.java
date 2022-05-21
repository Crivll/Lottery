package com.ljh.lottery.rpc.res;

import com.ljh.lottery.common.Result;
import com.ljh.lottery.rpc.dto.ActivityDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: ljh
 * DateTime: 2022-05-21 11:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityRes implements Serializable {

    private Result result;
    private ActivityDto activity;

    public ActivityRes(Result result) {
        this.result = result;
    }
}
