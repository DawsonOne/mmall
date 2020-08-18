package com.gosang.Exception;

import com.gosang.enums.ExceptionEnum;

/**
 * @author gosang
 * @version 1.0
 * @date 2020/8/18 22:50
 */
public class MMallException extends RuntimeException{

    public MMallException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
    }
}
