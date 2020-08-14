package com.login.common.helper;


/**
 * @author 张子艺
 * @packge com.xbxkj.commen.controller.helper
 * @data 2019-12-26 16:46
 * @project HuaZhouDelivery
 */
public class $ {

    /**
     * 成功，携带返回码和描述信息
     * @param message 描述信息
     * @param <T> 数据类型
     * @return 返回结果封装 {@link ApiResultModel}
     */
    public static <  T> ApiResultModel< T> success(String message) {
        return new ApiResultModel< T>(ApiCode.success, message, null);
    }
    /**
     * 成功，携带返回码和描述信息
     * @param <T> 数据类型
     * @return 返回结果封装 {@link ApiResultModel}
     */
    public static <  T> ApiResultModel< T> success() {
        return new ApiResultModel< T>(ApiCode.success, "成功", null);
    }
    /**
     * 成功，携带返回码和描述信息
     * @param <T> 数据类型
     * @return 返回结果封装 {@link ApiResultModel}
     */
    public static <  T> ApiResultModel< T> success(T data) {
        return new ApiResultModel< T>(ApiCode.success, ApiCode.success_msg, data);
    }

    /**
     * 成功，携带返回码、描述信息和数据
     * @param code 返回码
     * @param message 描述信息
     * @param data 数据
     * @param <T> 数据类型
     * @return 返回结果封装 {@link ApiResultModel}
     */
    public static < T> ApiResultModel< T> success(int code, String message, T data) {
        return new ApiResultModel< T>(code, message, data);
    }
    /**
     * 错误，携带错误码和详细描述信息
     * @param code 返回码
     * @param message 描述信息
     * @param <T> 数据类型
     * @return 返回结果封装 {@link ApiResultModel}
     */
    public static < T> ApiResultModel< T> error(int code, String message) {
        return new ApiResultModel<T>(code, message);
    }

    /**
     * 错误，携带错误码和详细描述信息
     * @param message 描述信息
     * @param <T> 数据类型
     * @return 返回结果封装 {@link ApiResultModel}
     */
    public static < T> ApiResultModel< T> error(String message) {
        return new ApiResultModel<T>(ApiCode.error, message);
    }

    /**
     * 错误，携带错误码和详细描述信息
     * @param <T> 数据类型
     * @return 返回结果封装 {@link ApiResultModel}
     */
    public static < T> ApiResultModel< T> error( ) {
        return new ApiResultModel<T>(ApiCode.error, ApiCode.error_msg);
    }

}
