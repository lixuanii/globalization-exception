package com.lixuan.globalization.exception.asserts;

import cn.hutool.core.util.StrUtil;
import com.lixuan.globalization.exception.enums.ExceptionEnum;
import com.lixuan.globalization.exception.exception.MsgException;

/**
 * @author lixuan
 * @date 2022-12-13 11:04
 */
public class Assert {


    public static void nonBlank(String str, ExceptionEnum exceptionEnum) {
        if (StrUtil.isBlank(str)) {
            throwException(exceptionEnum.msg());
        }
    }


    /**
     * 抛出带返回信息的指定异常
     */
    private static void throwException(String msg) {
        throw new MsgException(msg);
    }
}
