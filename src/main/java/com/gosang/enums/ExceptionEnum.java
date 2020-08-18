package com.gosang.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @author gosang
 * @version 1.0
 * @date 2020/8/18 23:24
 */
@Getter
public enum ExceptionEnum {
    USER_NOT_EXISt(1,"用户为空"),
    REGISTER_FAIL(2,"注册失败");

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @EnumValue
    private Integer code;
    private String msg;
}
