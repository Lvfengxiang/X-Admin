package com.login.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.login.bean.RolePermission;
import com.login.common.helper.$;
import com.login.common.helper.ApiResultModel;
import com.login.common.helper.ResultPage;
import com.login.mapper.RolePermissionMapper;
import com.login.service.RolePermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色菜单关联表 服务实现类
 * </p>
 *
 * @author 张子艺
 * @since 2020-05-08
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    /** 添加RolePermission
     * @param rolePermission  前端传进的参数
     * @return 返回统一信息
     */
    @Override
    public ApiResultModel addRolePermission(RolePermission rolePermission  ) {
           if(this.save(rolePermission )){
               return $.success();
           }else{
                return $.error("添加失败");
           }
    }
    /** 编辑跳转页面的回显数据RolePermission
     * @param id  前端传进的参数
     * @return 返回实体类
     */
    @Override
    public RolePermission selectRolePermissionByIdRolePermission(Integer id ){
            return this.getById(id);
    }


    /** 编辑RolePermission
     * @param rolePermission  前端传进的参数
     * @return 返回统一信息
     */
    @Override
    public ApiResultModel editRolePermission(RolePermission rolePermission  ) {
           if(this.updateById(rolePermission )){
               return $.success();
           }else{
                return $.error("编辑失败");
           }
    }
    /** 删除一条RolePermission
     * @param id  前端传进的ID
     * @return 返回统一信息
     */
    @Override
    public   ApiResultModel delRolePermission( Integer id  ){
           if(this.removeById(id)){
               return $.success();
           }else{
                return $.error("删除失败");
           }
    }

   /** 分页查询RolePermission
     * @param page  页码
     * @param limit  查几条
     * @param rolePermission  前端传进的参数
     * @return 返回统一分页信息
     */
    @Override
    public ResultPage findRolePermissionByPage(Integer page, Integer limit, RolePermission rolePermission){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("page", (page - 1) * limit);
        paramMap.put("limit", limit);
        paramMap.put("rolePermission", rolePermission);
        List<RolePermission> list = baseMapper.selectMyPage(paramMap);
        Integer count = baseMapper.countMyPage(paramMap);
        ResultPage<RolePermission> resultPage = new ResultPage<>();
        resultPage.setCode(0);
        resultPage.setCount(count);
        resultPage.setMsg("成功");
        resultPage.setData(list);
        return resultPage;
    }

}
