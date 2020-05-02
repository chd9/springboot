/*
Navicat MySQL Data Transfer

Source Server         : dbcms
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : manage

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-05-02 23:54:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `parent_id` int(11) DEFAULT NULL COMMENT '父ID',
  `name` varchar(20) DEFAULT NULL COMMENT '菜单名',
  `type` tinyint(4) DEFAULT NULL COMMENT '0：菜单',
  `sort_num` int(11) DEFAULT NULL COMMENT '排序',
  `url` varchar(100) DEFAULT NULL COMMENT 'url',
  `flag` tinyint(4) DEFAULT '1' COMMENT '状态',
  `create_by` varchar(30) NOT NULL DEFAULT 'system' COMMENT '创建人',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(30) NOT NULL DEFAULT 'system' COMMENT '更新人',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('12', null, '用户管理', '0', '1', null, '1', 'system', '2020-05-01 21:57:06', 'system', '2020-05-01 21:57:06');
INSERT INTO `t_menu` VALUES ('13', '12', '用户管理', '0', '1', null, '1', 'system', '2020-05-01 21:57:44', 'system', '2020-05-01 21:57:44');
INSERT INTO `t_menu` VALUES ('14', '12', '角色管理', '0', '2', null, '1', 'system', '2020-05-01 21:58:23', 'system', '2020-05-01 21:58:23');
INSERT INTO `t_menu` VALUES ('15', '12', '菜单管理', '0', '3', null, '1', 'system', '2020-05-01 21:58:41', 'system', '2020-05-01 21:58:41');
INSERT INTO `t_menu` VALUES ('16', null, '设置', '0', '2', null, '1', 'system', '2020-05-01 23:23:09', 'system', '2020-05-01 23:23:09');

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role` varchar(30) NOT NULL COMMENT '角色',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `flag` tinyint(4) DEFAULT '1' COMMENT '状态',
  `create_by` varchar(30) NOT NULL DEFAULT 'system' COMMENT '创建人',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(30) NOT NULL DEFAULT 'system' COMMENT '更新人',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('12', '超级管理员', '系统最高权限', '1', 'system', '2020-05-01 18:33:26', 'system', '2020-05-01 18:33:26');
INSERT INTO `t_role` VALUES ('13', '北京分公司管理员', '单个公司的最高管理权限', '1', 'system', '2020-05-01 18:44:00', 'system', '2020-05-01 18:44:00');
INSERT INTO `t_role` VALUES ('14', '上海分公司管理员', '上海分公司最高权限', '1', 'system', '2020-05-01 18:43:17', 'system', '2020-05-01 18:43:17');
INSERT INTO `t_role` VALUES ('16', '湖北分公司管理员', '分公司最高权限', '1', 'system', '2020-05-02 21:07:48', 'system', '2020-05-02 21:07:48');

-- ----------------------------
-- Table structure for `t_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  `flag` tinyint(4) DEFAULT '1' COMMENT '状态',
  `create_by` varchar(30) NOT NULL DEFAULT 'system' COMMENT '创建人',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(30) NOT NULL DEFAULT 'system' COMMENT '更新人',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES ('30', '12', '12', '1', 'system', '2020-05-02 23:05:32', 'system', '2020-05-02 23:05:32');
INSERT INTO `t_role_menu` VALUES ('31', '12', '13', '1', 'system', '2020-05-02 23:05:32', 'system', '2020-05-02 23:05:32');
INSERT INTO `t_role_menu` VALUES ('32', '12', '14', '1', 'system', '2020-05-02 23:05:32', 'system', '2020-05-02 23:05:32');
INSERT INTO `t_role_menu` VALUES ('33', '12', '15', '1', 'system', '2020-05-02 23:05:32', 'system', '2020-05-02 23:05:32');
INSERT INTO `t_role_menu` VALUES ('34', '12', '16', '1', 'system', '2020-05-02 23:05:32', 'system', '2020-05-02 23:05:32');

-- ----------------------------
-- Table structure for `t_setting`
-- ----------------------------
DROP TABLE IF EXISTS `t_setting`;
CREATE TABLE `t_setting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '配置项名称',
  `title` varchar(100) NOT NULL DEFAULT '' COMMENT '标题',
  `value` varchar(255) NOT NULL DEFAULT '' COMMENT '配置项值',
  `group` tinyint(1) NOT NULL COMMENT '分组 1-系统设置 2-日志设置',
  `type` tinyint(1) NOT NULL COMMENT '类型：1-text 2-textarea 3-select',
  `remark` varchar(100) NOT NULL DEFAULT '' COMMENT '备注',
  `options` varchar(255) NOT NULL DEFAULT '' COMMENT 'type为3时的选项，格式为:\r\n1:选项1\r\n2:选项2',
  `sort` smallint(3) NOT NULL DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_setting
-- ----------------------------
INSERT INTO `t_setting` VALUES ('1', 'show_validate_code', '登录验证码', '0', '1', '3', '登录时是否启用验证码', '1:启用\r\n0:不启用', '0');
INSERT INTO `t_setting` VALUES ('2', 'password_safe_times', '密码修改周期', '60', '2', '1', '设置的时间周期内必须修改密码，否则将无法登陆', '', '0');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(30) NOT NULL COMMENT '用户名',
  `sex` varchar(10) DEFAULT '男' COMMENT '性别',
  `phone` varchar(11) DEFAULT '13124567891' COMMENT '手机号',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `salt` varchar(10) DEFAULT NULL COMMENT '秘钥',
  `flag` tinyint(4) DEFAULT '1' COMMENT '状态',
  `create_by` varchar(30) NOT NULL DEFAULT 'system' COMMENT '创建人',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(30) NOT NULL DEFAULT 'system' COMMENT '更新人',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '男', '13124567891', '9836c6e9ea4b0140a0a6a6ccdfb141ca', 'dfh38h', '1', 'system', '2020-05-02 11:19:08', 'system', '2020-05-02 11:19:09');
INSERT INTO `t_user` VALUES ('3', 'zhangsan', '男', '13124567891', '70a8d988973ddac76425ab9320584ee8', 'dsafsd', '1', 'system', '2020-05-01 15:43:00', 'system', '2020-05-01 15:43:01');
INSERT INTO `t_user` VALUES ('4', 'lisi', '男', '13125467777', 'd4643023a1be125393945e5c777fbae4', 'dsfsfa', '1', 'system', '2020-05-01 17:01:03', 'system', '2020-05-01 17:01:04');
INSERT INTO `t_user` VALUES ('5', 'wangwu', '男', '13124567891', '7e0fcc4a74c5335fe8d325839e2ebcc7', 'wangwu', '1', 'system', '2020-05-01 15:43:34', 'system', '2020-05-01 15:43:34');
INSERT INTO `t_user` VALUES ('6', 'zhaoyi', '男', '13124567891', 'ad024d10ded930fe387231d202e2e479', 'zhaoyi', '1', 'system', '2020-05-01 15:44:50', 'system', '2020-05-01 15:44:50');
INSERT INTO `t_user` VALUES ('7', 'qianer', '男', '13124567891', '8299fbd3ad33cd77b1d3c8c9ff02ce3d', 'qianer', '1', 'system', '2020-05-01 15:45:10', 'system', '2020-05-01 15:45:11');
INSERT INTO `t_user` VALUES ('8', 'sunsan', '男', '13124567891', '15055ba92231df87080bd3f479328378', 'dssfff', '1', 'system', '2020-05-01 15:45:29', 'system', '2020-05-01 15:45:29');
INSERT INTO `t_user` VALUES ('9', 'liba', '男', '13124567891', 'e6e4120290b6d52f4c7995fefd397187', 'dsasda', '1', 'system', '2020-05-01 15:45:45', 'system', '2020-05-01 15:45:45');
INSERT INTO `t_user` VALUES ('10', 'zhoujiu', '男', '13124567891', 'aa20fa3ddcc9ec9f8a268ed49404d12f', 'vdfsd', '1', 'system', '2020-05-01 15:46:10', 'system', '2020-05-01 15:46:10');
INSERT INTO `t_user` VALUES ('11', 'wushi', '男', '13124567891', 'f338b1306a747a3162a39ee56e3e12e1', 'wushid', '1', 'system', '2020-05-01 15:46:30', 'system', '2020-05-01 15:46:30');
INSERT INTO `t_user` VALUES ('12', 'admin1', '男', '12342432423', '9bba66ac2606773eb74ab7ec097f1bb6', 'wqwef', '1', 'system', '2020-05-01 18:39:02', 'system', '2020-05-01 18:39:02');

-- ----------------------------
-- Table structure for `t_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `flag` tinyint(4) DEFAULT '1' COMMENT '状态',
  `create_by` varchar(30) NOT NULL DEFAULT 'system' COMMENT '创建人',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(30) NOT NULL DEFAULT 'system' COMMENT '更新人',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('27', '1', '12', '1', 'system', '2020-05-02 23:00:45', 'system', '2020-05-02 23:00:45');
INSERT INTO `t_user_role` VALUES ('28', '1', '13', '1', 'system', '2020-05-02 23:00:45', 'system', '2020-05-02 23:00:45');
INSERT INTO `t_user_role` VALUES ('29', '1', '14', '1', 'system', '2020-05-02 23:00:45', 'system', '2020-05-02 23:00:45');
