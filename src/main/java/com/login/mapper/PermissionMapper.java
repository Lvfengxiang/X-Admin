package com.login.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.login.bean.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单资源表 Mapper 接口
 * </p>
 *
 * @author 张子艺
 * @since 2020-05-08
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {



    /** 分页查询Permission
     * @param map.page  页码
     * @param map.limit  查几条
     * @param map.permission  前端传进的参数
     * @return 返回统一分页信息
     */
    List<Permission> selectMyPage(@Param("map") Map map);
    Integer countMyPage(@Param("map") Map map);

    /**
     *
     */
    List<Permission> getRouteInfo(@Param("userId") String userId);

    List<Permission> permissionInfo(@RequestParam("ids") String ids);
}
