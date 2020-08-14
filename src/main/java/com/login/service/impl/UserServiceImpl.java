package com.login.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.login.bean.User;
import com.login.bean.UserRole;
import com.login.common.helper.$;
import com.login.common.helper.ApiCode;
import com.login.common.helper.ApiResultModel;
import com.login.common.helper.ResultPage;
import com.login.mapper.UserMapper;
import com.login.service.UserRoleService;
import com.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.xingan.teacity.injection.commen.annotation.RedisCache;
//import org.springframework.data.redis.core.ValueOperations;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 吕凤祥
 * @since 2020-05-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

//    @Autowired
//    protected ValueOperations valueOperations;

    @Autowired
    UserRoleService userRoleService;

    /**
     * 添加User
     *
     * @param user 前端传进的参数
     * @return 返回统一信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResultModel addUser(User user) {
        user.setPassWord(SecureUtil.md5(user.getPassWord()));
        if (this.save(user)) {
            UserRole userRole=new UserRole();
            userRole.setRoleId(Integer.parseInt(user.getRoleid()));
            userRole.setUserId(user.getId());
            if(userRoleService.save(userRole)){
                return $.success();
            }else{
                return $.error("添加失败");
            }
        } else {
            return $.error("添加失败");
        }
    }

    /**
     * 编辑跳转页面的回显数据User
     *
     * @param id 前端传进的参数
     * @return 返回实体类
     */
    @Override
    public ApiResultModel selectUserByIdUser(Integer id) {
        try {
            User user = this.getById(id);
            return $.success(user);
        } catch (Exception e) {
            return $.error("查询失败");
        }
//        return this.getById(id);
    }


    /**
     * 编辑User
     *
     * @param user 前端传进的参数
     * @return 返回统一信息
     */
    @Override
    @Transactional
    public ApiResultModel editUser(User user) {
        if (this.updateById(user)) {
            UserRole userRole=userRoleService.getOne(new QueryWrapper<UserRole>().eq("user_id",user.getId()));
            userRole.setRoleId(Integer.parseInt(user.getRoleid()));
            if(userRoleService.updateById(userRole)){
                return $.success();
            }else{
                return $.error("编辑失败");
            }
        } else {
            return $.error("编辑失败");
        }
    }

    /**
     * 删除一条User
     *
     * @param id 前端传进的ID
     * @return 返回统一信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResultModel delUser(Integer id) {
        if (this.removeById(id)) {
            return $.success();
        } else {
            return $.error("删除失败");
        }
    }

    @Override
    public ApiResultModel findidByPwd(Integer id) {
        try {
            User user = getOne(new QueryWrapper<User>().eq("id", id));
            return $.success(user.getPassWord());
        } catch (Exception e) {
            return $.error("查询失败");
        }
    }

    /**
     * 分页查询User
     *
     * @param page  页码
     * @param limit 查几条
     * @param user  前端传进的参数
     * @return 返回统一分页信息
     */
    @Override
    public ResultPage findUserByPage(Integer page, Integer limit, User user) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("page", (page - 1) * limit);
        paramMap.put("limit", limit);
        paramMap.put("user", user);
        List<User> list = baseMapper.selectMyPage(paramMap);
        Integer count = baseMapper.countMyPage(paramMap);
        ResultPage<User> resultPage = new ResultPage<>();
        resultPage.setCode(ApiCode.success);
        resultPage.setCount(count);
        resultPage.setMsg("成功");
        resultPage.setData(list);
        return resultPage;
    }

}
