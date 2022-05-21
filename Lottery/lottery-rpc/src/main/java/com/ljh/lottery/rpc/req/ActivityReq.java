package com.ljh.lottery.rpc.req;

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
 * DateTime: 2022-05-21 12:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityReq implements Serializable {

    private Long activityId;
}
