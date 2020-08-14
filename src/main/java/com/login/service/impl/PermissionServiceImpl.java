package com.login.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.login.bean.Permission;
import com.login.bean.RolePermission;
import com.login.common.helper.*;
import com.login.mapper.PermissionMapper;
import com.login.service.PermissionService;
import com.login.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * <p>
 * 菜单资源表 服务实现类
 * </p>
 *
 * @author 张子艺
 * @since 2020-05-08
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    /**
     * 添加Permission
     *
     * @param permission 前端传进的参数
     * @return 返回统一信息
     */
    @Autowired
    RolePermissionService  rolePermissionService;

    @Override
    public ApiResultModel addPermission(Permission permission) {
        if (this.save(permission)) {
            return $.success();
        } else {
            return $.error("添加失败");
        }
    }

    /**
     * 编辑跳转页面的回显数据Permission
     *
     * @param id 前端传进的参数
     * @return 返回实体类
     */
    @Override
    public Permission selectPermissionByIdPermission(Integer id) {
        return this.getById(id);
    }


    /**
     * 编辑Permission
     *
     * @param permission 前端传进的参数
     * @return 返回统一信息
     */
    @Override
    public ApiResultModel editPermission(Permission permission) {
        if (this.updateById(permission)) {
            return $.success();
        } else {
            return $.error("编辑失败");
        }
    }

    /**
     * 删除一条Permission
     *
     * @param id 前端传进的ID
     * @return 返回统一信息
     */
    @Override
    @Transactional
    public ApiResultModel delPermission(String id) {
        try{
            if(StrUtil.isBlank(id)){
                throw new CustomException("参数值为空");
            }
            List<Permission> list=baseMapper.permissionInfo(id);
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).getMenuType()==1){
                    return $.error("该删除中存在基础路由，无法删除");
                }
            }
            Integer[] ids=Convert.toIntArray(id);
            for (int i = 0; i < ids.length; i++) {
                this.removeById(ids[i]);
                rolePermissionService.remove(new QueryWrapper<RolePermission>().eq("m_id",ids[i]));
            }

            return $.success();
        }catch (Exception e){
            e.printStackTrace();
            return $.error("删除失败");
        }
    }

    /**
     * 分页查询Permission
     *
     * @param page       页码
     * @param limit      查几条
     * @param permission 前端传进的参数
     * @return 返回统一分页信息
     */
    @Override
    public ResultPage findPermissionByPage(Integer page, Integer limit, Permission permission) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("page", (page - 1) * limit);
        paramMap.put("limit", limit);
        paramMap.put("permission", permission);
        List<Permission> list = baseMapper.selectMyPage(paramMap);
        Integer count = baseMapper.countMyPage(paramMap);
        list2TreeConverter(list,0);
        ResultPage<Permission> resultPage = new ResultPage<>();
        resultPage.setCode(ApiCode.success);
        resultPage.setCount(count);
        resultPage.setMsg("成功");
        resultPage.setData(list);
        return resultPage;
    }

    @Override
    public ApiResultModel getRouteInfo(String userId) {
        List<Permission> list = baseMapper.getRouteInfo(userId);
//        return $.success(list2TreeConverter(list,0));
        return $.success(list);
    }

    /**
     * 获得指定节点下所有归档
     * @param list
     * @param parentId
     * @return
     */
    public static List<Permission> list2TreeConverter(List<Permission> list, Integer parentId) {
        List<Permission> returnList = new ArrayList<>();
        for (Permission res : list) {
            //判断对象是否为根节点
            if (res.getParentId().equals(parentId)) {
                //该节点为根节点,开始递归
                recursionFn(list, res); //通过递归为节点设置childList
                returnList.add(res);
            }
        }

        return returnList;
    }

    /**
     * 递归列表
     * 通过递归,给指定t节点设置childList
     * @param list
     * @param t
     */
    public static void recursionFn(List<Permission> list, Permission t) {
        //只能获取当前t节点的子节点集,并不是所有子节点集
        List<Permission> childsList = getChildList(list, t);
        //设置他的子集对象集
        t.setChildren(childsList);

        //迭代子集对象集
        for (Permission nextChild : childsList) { //遍历完,则退出递归

            //判断子集对象是否还有子节点
            if (!CollectionUtils.isEmpty(childsList)) {
                //有下一个子节点,继续递归
                recursionFn(list, nextChild);
            }
        }
    }   /**
     * 获得指定节点下的所有子节点
     * @param list
     * @param t
     * @return
     */
    public static List<Permission> getChildList(List<Permission> list, Permission t) {
        List<Permission> childsList = new ArrayList<Permission>();
        //遍历集合元素,如果元素的Parentid==指定元素的id,则说明是该元素的子节点
        for (Permission t1 : list) {
            if (t1.getParentId()==t.getId()) {
                childsList.add(t1);
            }
        }
        return childsList;
    }

}
