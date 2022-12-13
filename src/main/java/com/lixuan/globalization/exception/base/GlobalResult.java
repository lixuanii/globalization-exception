package com.lixuan.globalization.exception.base;

import com.lixuan.globalization.exception.enums.ResultStatusEnum;
import lombok.Data;

/**
 * @author lixuan
 * @date 2022-12-13 10:55
 */
@Data
public class GlobalResult {

    private int state;

    private String msg;

    private Object data;

    private Object extra;


    public GlobalResult(ResultStatusEnum status, String msg, Object data, Object extra) {
        this.state = status.getCode();
        this.msg = msg;
        this.data = data;
        this.extra = extra;
    }

    public static GlobalResult error(String msg) {
        return new GlobalResult(ResultStatusEnum.ERROR, msg, "", "");
    }

    public static GlobalResult error(String msg, Object expand) {
        return new GlobalResult(ResultStatusEnum.ERROR, msg, "操作失败", expand);
    }
}
