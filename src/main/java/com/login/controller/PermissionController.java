package com.login.controller;


import com.login.bean.Permission;
import com.login.bean.User;
import com.login.common.helper.ApiResultModel;
import com.login.common.helper.ResultPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 菜单资源表 前端控制器
 * </p>
 *
 * @author 张子艺
 * @since 2020-05-08
 */
@Api(tags = "获取路由")
@RestController
@RequestMapping("/admin/Permission")
public class PermissionController extends BaseController {

    @ApiOperation(value = "获取路由表信息")
    @GetMapping("/getRouteInfo")
    public ApiResultModel getRouteInfo(){
        User user=(User) getRequest().getAttribute("user");
        return permissionService.getRouteInfo(user.getId().toString());
    }

    @ApiOperation(value = "分页查询路由表")
    @GetMapping("/findPermissionByPage")
    public ResultPage findPermissionByPage(@RequestParam Integer page, @RequestParam Integer limit, Permission permission){
        return permissionService.findPermissionByPage(page, limit, permission);
    }

    @ApiOperation(value = "添加路由表信息")
    @PostMapping("/savePermission")
    public ApiResultModel savePermission(@RequestBody Permission permission){
        return permissionService.addPermission(permission);
    }

    @ApiOperation(value = "删除路由表信息")
    @GetMapping("/delPermission")
    public ApiResultModel delPermission(@RequestParam String ids){
        return permissionService.delPermission(ids);
    }
}
