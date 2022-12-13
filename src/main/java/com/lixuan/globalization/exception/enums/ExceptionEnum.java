package com.lixuan.globalization.exception.enums;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 异常枚举
 *
 * @author lixuan
 * @date 2022-12-13 10:42
 */
@Slf4j
public enum ExceptionEnum {
    // exception
    OPERATION_FAILED("操作失败", "operation failed", "Falha na operação"),
    OPERATION_SUCCESS("操作成功", "operation success", "Operação com sucesso"),
    SYSTEM_EXCEPTION("系统异常,请联系管理员", "system exception,contact administrator", "O sistema está anormal, entre em contato com o administrador"),


    NAME_IS_NULL("名称为空", "name is null", "O nome está vazio"),
    DESC_IS_NULL("描述为空", "Describe as empty", "Descrição está vazio");

    /**
     * 中文描述
     */
    private final String ZH;
    /**
     * 英文描述
     */
    private final String EN;
    /**
     * 葡萄牙语描述
     */
    private final String PU;


    /**
     * key:异常名称  value:异常枚举
     */
    public static final Map<String, ExceptionEnum> MSG_MAP_STR = new HashMap<>();

    static {
        // 初始化赋值
        Arrays.stream(ExceptionEnum.values()).filter(Objects::nonNull).forEach(item -> MSG_MAP_STR.put(item.name(), item));
    }

    private static final String PLACEHOLDER = "{0}";
    private static final String LANGUAGE = "Accept-Language";
    private static final String LANGUAGE_EN = "en";
    private static final String LANGUAGE_PU = "pu";

    /**
     * 根据枚举获取错误返回值
     *
     * @param values 需要拼接提示的数组
     * @return String msg
     * @author lixuan
     * @date 2022/6/30 17:26
     **/
    public String msg(String... values) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            log.error("Spring 模式获取国际化提示失败,未获取到上下文:ServletRequestAttributes");
        }
        // 默认为中文提示
        String language = attributes != null ? attributes.getRequest().getHeader(LANGUAGE) : null;
        return buildMsg(values, MSG_MAP_STR.get(this.name()), language);
    }

    /**
     * 构建错误消息
     *
     * @param values        插入参数
     * @param enumStringMap 消息枚举
     * @param languageType  语言类型
     * @return String 错误消息
     * @author lixuan
     * @date 2022/6/30 17:27
     **/
    private static String buildMsg(String[] values, ExceptionEnum enumStringMap, String languageType) {
        if (enumStringMap == null) {
            return null;
        }
        String msg = getLanguageType(enumStringMap, languageType);
        String format = null;
        if (values != null && msg != null && msg.contains(PLACEHOLDER)) {
            String[] split = msg.split("}");
            if (values.length != split.length - 1) {
                log.warn("提示语:" + msg + ";参数:" + Arrays.toString(values) + " ->参数需要:" + split.length + "个,但实际:" + values.length + "个!");
            }
            format = MessageFormat.format(msg, (Object[]) values);
        }
        if (StrUtil.isNotBlank(format)) {
            return format;
        }
        if (StrUtil.isNotBlank(msg)) {
            return msg;
        }
        return enumStringMap.ZH;
    }

    /**
     * 判断语言类型  默认展示英文
     *
     * @param enumStringMap 语言枚举
     * @param languageParam 语言类型
     * @return String 提示语
     */
    private static String getLanguageType(ExceptionEnum enumStringMap, String languageParam) {
        if (null == languageParam) {
            return enumStringMap.getZH();
        }
        return switch (languageParam) {
            case LANGUAGE_EN -> enumStringMap.getEN();
            case LANGUAGE_PU -> enumStringMap.getPU();
            default -> enumStringMap.getZH();
        };
    }


    ExceptionEnum(String zh, String en, String pu) {
        ZH = zh;
        EN = en;
        PU = pu;
    }

    public String getZH() {
        return ZH;
    }

    public String getEN() {
        return EN;
    }

    public String getPU() {
        return PU;
    }
}
