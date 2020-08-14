package com.login.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.login.bean.Role;
import com.login.common.helper.$;
import com.login.common.helper.ApiCode;
import com.login.common.helper.ApiResultModel;
import com.login.common.helper.ResultPage;
import com.login.mapper.RoleMapper;
import com.login.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author 张子艺
 * @since 2020-05-08
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    /** 添加Role
     * @param role  前端传进的参数
     * @return 返回统一信息
     */
    @Override
    public ApiResultModel addRole(Role role  ) {
           if(this.save(role )){
               return $.success();
           }else{
                return $.error("添加失败");
           }
    }
    /** 编辑跳转页面的回显数据Role
     * @param id  前端传进的参数
     * @return 返回实体类
     */
    @Override
    public ApiResultModel selectRoleByIdRole(Integer id ){
        try{
            return $.success(this.getById(id));
        }catch (Exception e){
            return $.error("查询失败");
        }
    }


    /** 编辑Role
     * @param role  前端传进的参数
     * @return 返回统一信息
     */
    @Override
    public ApiResultModel editRole(Role role  ) {
           if(this.updateById(role )){
               return $.success();
           }else{
                return $.error("编辑失败");
           }
    }
    /** 删除一条Role
     * @param id  前端传进的ID
     * @return 返回统一信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public  ApiResultModel delRole( Integer id  ){
        try {
            if (this.removeById(id)) {
                return $.success();
            } else {
                return $.error("删除失败");
            }
        }catch (Exception e){
            return $.error("删除失败");
        }
    }

   /** 分页查询Role
     * @param page  页码
     * @param limit  查几条
     * @param role  前端传进的参数
     * @return 返回统一分页信息
     */
    @Override
    public ResultPage findRoleByPage(Integer page, Integer limit, Role role){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("page", (page - 1) * limit);
        paramMap.put("limit", limit);
        paramMap.put("role", role);
        List<Role> list = baseMapper.selectMyPage(paramMap);
        Integer count = baseMapper.countMyPage(paramMap);
        ResultPage<Role> resultPage = new ResultPage<>();
        resultPage.setCode(ApiCode.success);
        resultPage.setCount(count);
        resultPage.setMsg("成功");
        resultPage.setData(list);
        return resultPage;
    }
}
