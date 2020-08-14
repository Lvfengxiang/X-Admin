package com.login.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.login.bean.Role;
import com.login.common.helper.ApiResultModel;
import com.login.common.helper.ResultPage;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author 张子艺
 * @since 2020-05-08
 */
public interface RoleService extends IService<Role> {

     /** 添加Role
     * @param role  前端传进的参数
     * @return 返回统一信息
     */
   ApiResultModel addRole(Role role);

    /** 编辑跳转页面的回显数据Role
     * @param id  前端传进的参数
     * @return 返回实体类
     */
    ApiResultModel selectRoleByIdRole(Integer id);

     /** 编辑Role
     * @param role  前端传进的参数
     * @return 返回统一信息
     */
   ApiResultModel editRole(Role role);


     /** 删除一条Role
     * @param id  前端传进的ID
     * @return 返回统一信息
     */
      ApiResultModel delRole(Integer id);


   /** 分页查询Role
     * @param page  页码
     * @param limit  查几条
     * @param role  前端传进的参数
     * @return 返回统一分页信息
     */
   ResultPage findRoleByPage(Integer page, Integer limit, Role role);

}
