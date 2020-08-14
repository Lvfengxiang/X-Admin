package com.login.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.login.bean.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色菜单关联表 Mapper 接口
 * </p>
 *
 * @author 张子艺
 * @since 2020-05-08
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {



    /** 分页查询RolePermission
     * @param map.page  页码
     * @param map.limit  查几条
     * @param map.rolePermission  前端传进的参数
     * @return 返回统一分页信息
     */
    List<RolePermission> selectMyPage(@Param("map") Map map);
    Integer countMyPage(@Param("map") Map map);

}
