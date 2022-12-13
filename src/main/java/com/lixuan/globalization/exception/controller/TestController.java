package com.lixuan.globalization.exception.controller;

import com.lixuan.globalization.exception.asserts.Assert;
import com.lixuan.globalization.exception.dto.TestDTO;
import com.lixuan.globalization.exception.enums.ExceptionEnum;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lixuan
 * @date 2022-12-13 11:01
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("")
    public void test(@RequestBody @Validated TestDTO dto) {
        Assert.nonBlank(dto.getDescription(), ExceptionEnum.DESC_IS_NULL);
        // do something
    }
}
