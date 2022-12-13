package com.lixuan.globalization.exception.filter;

import com.lixuan.globalization.exception.base.GlobalResult;
import com.lixuan.globalization.exception.enums.ExceptionEnum;
import com.lixuan.globalization.exception.exception.MsgException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 全局异常处理
 *
 * @author lixuan
 * @date 2022-12-13 10:54
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public GlobalResult error(Exception exception) {
        if (exception instanceof MsgException msgException) {
            return GlobalResult.error(msgException.getMessage(), msgException.getResultStatus());
        }
        return GlobalResult.error(getExceptionMsg(exception));
    }

    private String getExceptionMsg(Exception exception) {
        if (exception instanceof final MethodArgumentNotValidException methodArgumentNotValidException) {
            List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
            String STRING_SEPARATOR = ",";
            return fieldErrors.stream().map(fieldError -> {
                String defaultMessage = fieldError.getDefaultMessage();
                ExceptionEnum exceptionEnum = ExceptionEnum.MSG_MAP_STR.get(defaultMessage);
                if (exceptionEnum != null) {
                    return exceptionEnum.msg();
                }
                return defaultMessage;
            }).collect(Collectors.joining(STRING_SEPARATOR));
        }
        log.error("服务器异常:", exception);
        return ExceptionEnum.SYSTEM_EXCEPTION.msg();
    }
}
