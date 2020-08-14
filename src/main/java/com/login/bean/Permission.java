package com.login.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 菜单资源表
 * </p>
 *
 * @author 张子艺
 * @since 2020-05-08
 */
@TableName("tr_permission")
@ApiModel(value="Permission对象", description="菜单资源表")
public class Permission extends Model<Permission> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "菜单或按钮名字")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "菜单URL")
    @TableField("menu_url")
    private String menuUrl;

//    @ApiModelProperty(value = "菜单级别  1 一级菜单  2 二级菜单")
//    @TableField("menu_lev")
//    private Integer menuLev;

//    @ApiModelProperty(value = "菜单样式")
//    @TableField("menu_class")
//    private String menuClass;

    @ApiModelProperty(value = "菜单类型  1 菜单  2按钮")
    @TableField("menu_type")
    private Integer menuType;

    @ApiModelProperty(value = "上级菜单")
    @TableField("parent_id")
    private Integer parentId;

    @ApiModelProperty(value = "排序")
    @TableField("menu_order")
    private Integer menuOrder;

//    @ApiModelProperty(value = "菜单跳转(用于跳转)")
//    @TableField("menu_path")
//    private String menuPath;
//
//    @ApiModelProperty(value = "菜单名称(用于跳转)")
//    @TableField("menu_name")
//    private String menuName;

    @ApiModelProperty(value = "菜单图标")
    @TableField("menu_icon")
    private String menuIcon;

//    @ApiModelProperty(value = "默认子路由")
//    @TableField("menu_redirect")
//    private String menuReirect;

    @ApiModelProperty(value = "默认子路由")
    @TableField(exist = false)
    private List<Permission> children;

    @ApiModelProperty(value = "菜单标识")
    @TableField("menu_code")
    private String  menuCode;

    @ApiModelProperty(value = "是否删除1.是2.否(用于：有些路由是无法删除的)")
    @TableField("is_del")
    private Integer isDel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }
//    public Integer getMenuLev() {
//        return menuLev;
//    }
//
//    public void setMenuLev(Integer menuLev) {
//        this.menuLev = menuLev;
//    }
//    public String getMenuClass() {
//        return menuClass;
//    }
//
//    public void setMenuClass(String menuClass) {
//        this.menuClass = menuClass;
//    }
    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }
//    public String getMenuPath() {
//        return menuPath;
//    }
//
//    public void setMenuPath(String menuPath) {
//        this.menuPath = menuPath;
//    }
//    public String getMenuName() {
//        return menuName;
//    }
//
//    public void setMenuName(String menuName) {
//        this.menuName = menuName;
//    }
    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }
//    public String getMenuReirect() {
//        return menuReirect;
//    }
//
//    public void setMenuReirect(String menuReirect) {
//        this.menuReirect = menuReirect;
//    }
    public List<Permission> getChildren() {
        return children;
    }

    public void setChildren(List<Permission> children) {
        this.children = children;
    }
    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }
    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Permission{" +
            "id=" + id +
            ", name=" + name +
            ", menuUrl=" + menuUrl +
//            ", menuLev=" + menuLev +
//            ", menuClass=" + menuClass +
            ", menuType=" + menuType +
            ", parentId=" + parentId +
            ", menuOrder=" + menuOrder +
        "}";
    }
}
