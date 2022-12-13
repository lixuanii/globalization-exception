package com.lixuan.globalization.exception.exception;

import com.lixuan.globalization.exception.enums.ResultStatusEnum;

/**
 * @author lixuan
 * @date 2022-12-13 10:49
 */
public class MsgException extends RuntimeException {

    private final ResultStatusEnum resultStatus;

    public MsgException(String msg) {
        super(msg);
        this.resultStatus = ResultStatusEnum.ERROR;
    }

    public ResultStatusEnum getResultStatus() {
        return resultStatus;
    }
}
