package com.gosang.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @author gosang
 * @version 1.0
 * @date 2020/8/18 23:37
 */
@Getter
public enum GenderEnum {

    FEMALE(0,"女"),
    MALE(1,"男");

    GenderEnum(Integer code, String sex) {
        this.code = code;
        this.sex = sex;
    }

    @EnumValue
    private Integer code;
    private String sex;
}
