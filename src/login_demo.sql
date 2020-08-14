/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50711
 Source Host           : localhost:3306
 Source Schema         : login_demo

 Target Server Type    : MySQL
 Target Server Version : 50711
 File Encoding         : 65001

 Date: 14/08/2020 11:35:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tr_permission
-- ----------------------------
DROP TABLE IF EXISTS `tr_permission`;
CREATE TABLE `tr_permission`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '菜单或按钮名字(menu_title)',
  `menu_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '菜单URL',
  `menu_type` int(4) NULL DEFAULT NULL COMMENT '菜单类型  1 基础路由  2自定义路由',
  `parent_id` int(10) NULL DEFAULT NULL COMMENT '上级菜单',
  `menu_order` int(10) NULL DEFAULT NULL COMMENT '排序',
  `menu_icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '菜单图标',
  `menu_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '菜单标识',
  `is_del` int(10) NULL DEFAULT NULL COMMENT '是否删除1.是2.否(用于：有些路由是无法删除的)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '菜单资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tr_permission
-- ----------------------------
INSERT INTO `tr_permission` VALUES (3, '路由管理', 'Router', 1, 0, NULL, 'el-icon-s-platform', 'router', 1);
INSERT INTO `tr_permission` VALUES (4, '首页', 'Home', 2, 0, NULL, 'el-icon-s-marketing', 'home', NULL);
INSERT INTO `tr_permission` VALUES (5, '角色管理', 'Role', 1, 0, NULL, 'el-icon-s-tools', 'role', NULL);
INSERT INTO `tr_permission` VALUES (7, '账号管理', 'Account', 1, 0, NULL, 'el-icon-user-solid', 'account', NULL);

-- ----------------------------
-- Table structure for tr_role
-- ----------------------------
DROP TABLE IF EXISTS `tr_role`;
CREATE TABLE `tr_role`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色名',
  `role_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色编码',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `details` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tr_role
-- ----------------------------
INSERT INTO `tr_role` VALUES (1, '超级管理员', 'admin', 1583573392414, '超级管理员');

-- ----------------------------
-- Table structure for tr_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `tr_role_permission`;
CREATE TABLE `tr_role_permission`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `r_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色ID',
  `m_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tr_role_permission
-- ----------------------------
INSERT INTO `tr_role_permission` VALUES (3, '1', '4');
INSERT INTO `tr_role_permission` VALUES (4, '1', '3');
INSERT INTO `tr_role_permission` VALUES (5, '1', '5');
INSERT INTO `tr_role_permission` VALUES (7, '1', '7');

-- ----------------------------
-- Table structure for tr_user
-- ----------------------------
DROP TABLE IF EXISTS `tr_user`;
CREATE TABLE `tr_user`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '账户名称',
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单位名称',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '手机号',
  `pass_word` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tr_user
-- ----------------------------
INSERT INTO `tr_user` VALUES (1, 'admin', NULL, NULL, NULL, '21232f297a57a5a743894a0e4a801fc3');

-- ----------------------------
-- Table structure for tr_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tr_user_role`;
CREATE TABLE `tr_user_role`  (
  `role_id` int(10) NULL DEFAULT NULL COMMENT '角色id',
  `user_id` int(10) NULL DEFAULT NULL COMMENT '用户id',
  `id` int(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色用户关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tr_user_role
-- ----------------------------
INSERT INTO `tr_user_role` VALUES (1, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
