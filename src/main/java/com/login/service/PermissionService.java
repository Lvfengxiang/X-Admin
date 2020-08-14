package com.login.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.login.bean.Permission;
import com.login.common.helper.ApiResultModel;
import com.login.common.helper.ResultPage;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 * 菜单资源表 服务类
 * </p>
 *
 * @author 张子艺
 * @since 2020-05-08
 */
public interface PermissionService extends IService<Permission> {

     /** 添加Permission
     * @param permission  前端传进的参数
     * @return 返回统一信息
     */
   ApiResultModel addPermission(Permission permission);

    /** 编辑跳转页面的回显数据Permission
     * @param id  前端传进的参数
     * @return 返回实体类
     */
    Permission selectPermissionByIdPermission(Integer id);

     /** 编辑Permission
     * @param permission  前端传进的参数
     * @return 返回统一信息
     */
   ApiResultModel editPermission(Permission permission);


     /** 删除一条Permission
     * @param id  前端传进的ID
     * @return 返回统一信息
     */
      ApiResultModel delPermission(String id);


   /** 分页查询Permission
     * @param page  页码
     * @param limit  查几条
     * @param permission  前端传进的参数
     * @return 返回统一分页信息
     */
   ResultPage findPermissionByPage(Integer page, Integer limit, Permission permission);

    /**
     * 获取路由
     * @param userId
     * @return
     */
   ApiResultModel getRouteInfo(String userId);
}
