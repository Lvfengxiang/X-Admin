package com.login.common.helper;

/**
 * @author 张子艺
 * @packge com.api.result.plus.entity
 * @data 2019-12-16 16:19
 * @project ApiResultPlus
 */
public class ApiCode {
    //成功
    public  static  final  int success=200;
    public  static  final  String success_msg="成功";
    //失败
    public  static  final  int error=500;
    public  static  final  String error_msg="失败";

    public  static  final  int auth=403;
    public  static  final  String auth_msg="登陆失效";


    public  static  final  int notfound=404;

    //参数错误
    public  static  final  int parameter_error =20001;
    public  static  final  int login_time_out=20002;
}