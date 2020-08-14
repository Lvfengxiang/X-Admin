package com.login.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.login.bean.User;
import com.login.common.helper.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Api(tags = "用户登录")
@RestController
@RequestMapping("/admin/user")
public class UserController extends BaseController{
    /**
     * 后台用户登录
     *
     * @param username
     * @param passwords
     * @return
     */
    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public ApiResultModel<String> login(@RequestParam String username, @RequestParam String passwords) {
        if (StrUtil.isBlank(username) || StrUtil.isBlank(passwords)) {
            throw new CustomException("请输入账号或者密码");
        }
        //根据账号获取数据库信息
        User user = userService.getOne(new QueryWrapper<User>().eq("user_name", username));
        if (user == null) {
            return $.error("账号不存在");
        }
        //判断密码是否正确
        passwords = SecureUtil.md5(passwords);
        if (user.getPassWord().equals(passwords)) {
            //生成token
            String token = "";
            try {
                token = AuthSign.sign(user.getUserName(), user.getId(), "");
                redisUtil.set(  "user:" + token, JSONObject.toJSONString(user), 172800);
//                redisUtil.set(  "user:" + token, JSONObject.toJSONString(user), 60);
                return $.success(token);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return $.error("登录失败");
            }
        } else {
            return $.error("密码错误");
        }
    }

    /**
     * 查询用户信息
     * @param page
     * @param limit
     * @param user
     * @return
     */
    @GetMapping("/findUserByPage")
    public ResultPage findUserByPage(@RequestParam Integer page,@RequestParam Integer limit,User user){
        return userService.findUserByPage(page, limit, user);
    }

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    @PostMapping("/insertUser")
    public ApiResultModel insertUser(@RequestBody User user){
        return userService.addUser(user);
    }

    /**
     * 详情接口
     * @param id
     * @return
     */
    @GetMapping("/detailsUserInfo")
    public ApiResultModel detailsUserInfo(@RequestParam Integer id){
        return $.success(userService.getOne(new QueryWrapper<User>().eq("id",id)));
    }

    /**
     * 编辑用户信息
     * @param user
     * @return
     */
    @PostMapping("/editUserInfo")
    public ApiResultModel editUserInfo(@RequestBody User user){
        return userService.editUser(user);
    }

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    @PostMapping("/delUserInfo")
    public ApiResultModel delUserInfo(@RequestParam Integer id){
        return userService.delUser(id);
    }


}
