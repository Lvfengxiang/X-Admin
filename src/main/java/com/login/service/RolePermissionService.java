package com.login.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.login.bean.RolePermission;
import com.login.common.helper.ApiResultModel;
import com.login.common.helper.ResultPage;

/**
 * <p>
 * 角色菜单关联表 服务类
 * </p>
 *
 * @author 张子艺
 * @since 2020-05-08
 */
public interface RolePermissionService extends IService<RolePermission> {

     /** 添加RolePermission
     * @param rolePermission  前端传进的参数
     * @return 返回统一信息
     */
   ApiResultModel addRolePermission(RolePermission rolePermission);

    /** 编辑跳转页面的回显数据RolePermission
     * @param id  前端传进的参数
     * @return 返回实体类
     */
    RolePermission selectRolePermissionByIdRolePermission(Integer id);

     /** 编辑RolePermission
     * @param rolePermission  前端传进的参数
     * @return 返回统一信息
     */
   ApiResultModel editRolePermission(RolePermission rolePermission);


     /** 删除一条RolePermission
     * @param id  前端传进的ID
     * @return 返回统一信息
     */
      ApiResultModel delRolePermission(Integer id);


   /** 分页查询RolePermission
     * @param page  页码
     * @param limit  查几条
     * @param rolePermission  前端传进的参数
     * @return 返回统一分页信息
     */
   ResultPage findRolePermissionByPage(Integer page, Integer limit, RolePermission rolePermission);
}
