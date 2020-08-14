package com.login.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.login.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author 吕凤祥
 * @since 2020-05-06
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {



    /** 分页查询User
     * @param map.page  页码
     * @param map.limit  查几条
     * @param map.user  前端传进的参数
     * @return 返回统一分页信息
     */
    List<User> selectMyPage(@Param("map") Map map);
    Integer countMyPage(@Param("map") Map map);

}
