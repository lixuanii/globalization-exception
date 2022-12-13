package com.lixuan.globalization.exception.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


/**
 * @author lixuan
 * @date 2022-12-13 11:01
 */
@Data
public class TestDTO {

    @NotBlank(message = "NAME_IS_NULL")
    private String name;

    private String description;
}
