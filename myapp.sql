/*
 Navicat Premium Data Transfer

 Source Server         : hjw
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : localhost:3306
 Source Schema         : myapp

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 25/07/2022 18:36:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_aricle
-- ----------------------------
DROP TABLE IF EXISTS `t_aricle`;
CREATE TABLE `t_aricle`  (
  `id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `l_id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '语言id',
  `t_id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专题id',
  `u_id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发布者id',
  `title` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章标题',
  `sub_title` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章副标题',
  `content` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章标题',
  `keyword` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '关键字',
  `read_count` int(11) NOT NULL DEFAULT 0 COMMENT '阅读量',
  `delete_flag` int(11) NOT NULL DEFAULT 0 COMMENT '0:未删除 1:逻辑删除',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_aricle
-- ----------------------------

-- ----------------------------
-- Table structure for t_launage
-- ----------------------------
DROP TABLE IF EXISTS `t_launage`;
CREATE TABLE `t_launage`  (
  `id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `desc` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '0:暂存 1:发布',
  `delete_flag` int(11) NOT NULL DEFAULT 0 COMMENT '0:未删除 1:逻辑删除',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_launage
-- ----------------------------

-- ----------------------------
-- Table structure for t_topic
-- ----------------------------
DROP TABLE IF EXISTS `t_topic`;
CREATE TABLE `t_topic`  (
  `id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `l_id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `u_id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发布者id',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专题名称',
  `desc` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专题描述',
  `delete_flag` int(11) NOT NULL DEFAULT 0 COMMENT '0:未删除 1:逻辑删除',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_topic
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录名',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `photo` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `intro` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签名介绍',
  `birthday` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生日(年月日) yyyy-MM-dd',
  `sex` int(11) NOT NULL DEFAULT 0 COMMENT '阅读量',
  `delete_flag` int(11) NOT NULL DEFAULT 0 COMMENT '0:未删除 1:逻辑删除',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1551502630400090112', 'hjw', 'e10adc3949ba59abbe56e057f20f883e', 'zijing', NULL, '我是一个比较懒的人~~', '1989-11-22', 0, 0, 0, '2022-07-25 17:40:27', '2022-07-25 17:40:27');
INSERT INTO `t_user` VALUES ('1551505145518678016', 'hermit', 'e10adc3949ba59abbe56e057f20f883e', 'hermit', NULL, '我是一个比较勤快的人~~', '1979-11-22', 0, 0, 0, '2022-07-25 17:50:27', '2022-07-25 17:50:27');
INSERT INTO `t_user` VALUES ('1551505312384933888', 'xiaoliu', 'e10adc3949ba59abbe56e057f20f883e', 'xiaoliu', NULL, '我喜欢阅读和运毒~~', '1988-09-22', 0, 0, 0, '2022-07-25 17:51:07', '2022-07-25 17:51:07');
INSERT INTO `t_user` VALUES ('1551505604476194816', 'lishutong', 'e10adc3949ba59abbe56e057f20f883e', '李姝潼', NULL, '我最近不喜欢吃饭了~~', '2017-09-22', 0, 0, 0, '2022-07-25 17:52:17', '2022-07-25 17:52:17');
INSERT INTO `t_user` VALUES ('1551505752069607424', 'houjiale', 'e10adc3949ba59abbe56e057f20f883e', '侯嘉乐', NULL, '我是一个活泼爱动的活力宝宝~~', '2016-12-12', 0, 0, 0, '2022-07-25 17:52:51', '2022-07-25 17:52:51');

SET FOREIGN_KEY_CHECKS = 1;
