package com.wzp.king.common.exception;

/**
 * 自定义异常
 *
 * @author wengzhipeng
 * @version v1.0, 2019/5/16
 */

public class CustomException extends RuntimeException {
    private static final long serialVersionUID = -6739408881567472269L;

    public CustomException() {
        super();
    }

    public CustomException(String s) {
        super(s);
    }
}
