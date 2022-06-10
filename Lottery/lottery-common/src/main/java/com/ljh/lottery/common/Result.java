package com.ljh.lottery.common;

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
 * DateTime: 2022-05-21 11:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -3826891916021780628L;
    private String code;
    private String info;
    private T data;

    public Result(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public static Result buildResult(Constants.ResponseCode code, String info) {
        return new Result(code.getCode(), info);
    }

    public static Result buildResult(Constants.ResponseCode code) {
        return new Result(code.getCode(), code.getInfo());
    }

    public static Result buildSuccessResult() {
        return new Result(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
    }

    public static Result buildErrorResult() {
        return new Result(Constants.ResponseCode.UN_ERROR.getCode(), Constants.ResponseCode.UN_ERROR.getInfo());
    }

    public static Result buildErrorResult(String info) {
        return new Result(Constants.ResponseCode.UN_ERROR.getCode(), info);
    }

    public static <T> Result success(T data) {
        Result<T> result = new Result<>();
        result.setCode(Constants.ResponseCode.SUCCESS.getCode());
        result.setData(data);
        result.setInfo(Constants.ResponseCode.SUCCESS.getInfo());
        return result;
    }

    public static <T> Result success(T data, String info) {
        Result<T> result = new Result<>();
        result.setCode(Constants.ResponseCode.SUCCESS.getCode());
        result.setData(data);
        result.setInfo(info);
        return result;
    }
}
