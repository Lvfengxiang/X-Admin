package com.login.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.login.bean.UserRole;
import com.login.common.helper.$;
import com.login.common.helper.ApiResultModel;
import com.login.common.helper.ResultPage;
import com.login.mapper.UserRoleMapper;
import com.login.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色用户关联表 服务实现类
 * </p>
 *
 * @author 张子艺
 * @since 2020-05-08
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
    /** 添加UserRole
     * @param userRole  前端传进的参数
     * @return 返回统一信息
     */
    @Override
    public ApiResultModel addUserRole(UserRole userRole  ) {
           if(this.save(userRole )){
               return $.success();
           }else{
                return $.error("添加失败");
           }
    }
    /** 编辑跳转页面的回显数据UserRole
     * @param id  前端传进的参数
     * @return 返回实体类
     */
    @Override
    public UserRole selectUserRoleByIdUserRole(Integer id ){
            return this.getById(id);
    }


    /** 编辑UserRole
     * @param userRole  前端传进的参数
     * @return 返回统一信息
     */
    @Override
    public ApiResultModel editUserRole(UserRole userRole  ) {
           if(this.updateById(userRole )){
               return $.success();
           }else{
                return $.error("编辑失败");
           }
    }
    /** 删除一条UserRole
     * @param id  前端传进的ID
     * @return 返回统一信息
     */
    @Override
    public   ApiResultModel delUserRole( Integer id  ){
           if(this.removeById(id)){
               return $.success();
           }else{
                return $.error("删除失败");
           }
    }

   /** 分页查询UserRole
     * @param page  页码
     * @param limit  查几条
     * @param userRole  前端传进的参数
     * @return 返回统一分页信息
     */
    @Override
    public ResultPage findUserRoleByPage(Integer page, Integer limit, UserRole userRole){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("page", (page - 1) * limit);
        paramMap.put("limit", limit);
        paramMap.put("userRole", userRole);
        List<UserRole> list = baseMapper.selectMyPage(paramMap);
        Integer count = baseMapper.countMyPage(paramMap);
        ResultPage<UserRole> resultPage = new ResultPage<>();
        resultPage.setCode(0);
        resultPage.setCount(count);
        resultPage.setMsg("成功");
        resultPage.setData(list);
        return resultPage;
    }

}
