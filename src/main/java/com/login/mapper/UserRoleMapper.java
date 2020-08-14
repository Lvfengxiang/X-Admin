package com.login.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.login.bean.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色用户关联表 Mapper 接口
 * </p>
 *
 * @author 张子艺
 * @since 2020-05-08
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {



    /** 分页查询UserRole
     * @param map
     * @return 返回统一分页信息
     */
    List<UserRole> selectMyPage(@Param("map") Map map);
    Integer countMyPage(@Param("map") Map map);

}
