package com.lixuan.globalization.exception.enums;

/**
 * 返回值
 *
 * @author lixuan
 * @date 2022-12-13 10:50
 */
public enum ResultStatusEnum {
    // Result
    OK(200),
    ERROR(500),
    ;
    private final int code;

    ResultStatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
