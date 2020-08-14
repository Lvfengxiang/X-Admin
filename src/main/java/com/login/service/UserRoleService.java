package com.login.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.login.bean.UserRole;
import com.login.common.helper.ApiResultModel;
import com.login.common.helper.ResultPage;

/**
 * <p>
 * 角色用户关联表 服务类
 * </p>
 *
 * @author 张子艺
 * @since 2020-05-08
 */
public interface UserRoleService extends IService<UserRole> {

     /** 添加UserRole
     * @param userRole  前端传进的参数
     * @return 返回统一信息
     */
   ApiResultModel addUserRole(UserRole userRole);

    /** 编辑跳转页面的回显数据UserRole
     * @param id  前端传进的参数
     * @return 返回实体类
     */
    UserRole selectUserRoleByIdUserRole(Integer id);

     /** 编辑UserRole
     * @param userRole  前端传进的参数
     * @return 返回统一信息
     */
   ApiResultModel editUserRole(UserRole userRole);


     /** 删除一条UserRole
     * @param id  前端传进的ID
     * @return 返回统一信息
     */
      ApiResultModel delUserRole(Integer id);


   /** 分页查询UserRole
     * @param page  页码
     * @param limit  查几条
     * @param userRole  前端传进的参数
     * @return 返回统一分页信息
     */
   ResultPage findUserRoleByPage(Integer page, Integer limit, UserRole userRole);
}
