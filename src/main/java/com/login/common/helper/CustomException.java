package com.login.common.helper;

/**
 * @项目名称: tea-city-online
 * @包名称: com.xingan.teacity.shop.core.exception
 * @创建人员: Mr.Administrator  张子艺
 * @创建时间: 2020-04-25 14:15
 * @版本:
 */
public class CustomException extends RuntimeException {
    public CustomException() {
        super();
    }
    public CustomException(String message, Throwable cause, boolean enableSuppression,
                           boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
    public CustomException(String message) {
        super(message);
    }
    public CustomException(Throwable cause) {
        super(cause);
    }
}
