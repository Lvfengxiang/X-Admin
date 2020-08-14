package com.login.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.login.bean.User;
import com.login.common.helper.ApiResultModel;
import com.login.common.helper.ResultPage;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 吕凤祥
 * @since 2020-05-06
 */
public interface UserService extends IService<User> {

    /**
     * 添加User
     *
     * @param user 前端传进的参数
     * @return 返回统一信息
     */
    ApiResultModel addUser(User user);

    /**
     * 编辑跳转页面的回显数据User
     *
     * @param id 前端传进的参数
     * @return 返回实体类
     */
    ApiResultModel selectUserByIdUser(Integer id);

    /**
     * 编辑User
     *
     * @param user 前端传进的参数
     * @return 返回统一信息
     */
    ApiResultModel editUser(User user);


    /**
     * 删除一条User
     *
     * @param id 前端传进的ID
     * @return 返回统一信息
     */
    ApiResultModel delUser(Integer id);

    /**
     * 修改密码，获取密码
     */
    ApiResultModel findidByPwd(Integer id);


    /**
     * 分页查询User
     *
     * @param page  页码
     * @param limit 查几条
     * @param user  前端传进的参数
     * @return 返回统一分页信息
     */
    ResultPage findUserByPage(Integer page, Integer limit, User user);


}
