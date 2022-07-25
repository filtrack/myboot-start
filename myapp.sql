/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : myapp

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 26/07/2022 00:10:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_aricle
-- ----------------------------
DROP TABLE IF EXISTS `t_aricle`;
CREATE TABLE `t_aricle` (
  `id` bigint NOT NULL,
  `l_id` bigint NOT NULL COMMENT '语言id',
  `t_id` bigint NOT NULL COMMENT '专题id',
  `u_id` bigint NOT NULL COMMENT '发布者id',
  `title` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文章标题',
  `sub_title` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文章副标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '文章标题',
  `keyword` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '关键字',
  `read_count` int NOT NULL DEFAULT '0' COMMENT '阅读量',
  `status` int NOT NULL DEFAULT '0' COMMENT '0:暂存 1:发布',
  `delete_flag` int NOT NULL DEFAULT '0' COMMENT '0:未删除 1:逻辑删除',
  `version` int NOT NULL DEFAULT '0' COMMENT '乐观锁',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_aricle
-- ----------------------------
BEGIN;
INSERT INTO `t_aricle` (`id`, `l_id`, `t_id`, `u_id`, `title`, `sub_title`, `content`, `keyword`, `read_count`, `status`, `delete_flag`, `version`, `create_time`, `update_time`) VALUES (1551598363747471362, 1551583015916720130, 1551584687699202050, 1551502630400090112, 'Spring Boot简介', 'Spring Boot 有哪些优点？主要给我们解决了哪些问题呢？我们以下图来说明：', '我们知道，从 2002 年开始，Spring 一直在飞速的发展，如今已经成为了在Java EE（Java Enterprise Edition）开发中真正意义上的标准，但是随着技术的发展，Java EE使用 Spring 逐渐变得笨重起来，大量的 XML 文件存在于项目之中。繁琐的配置，整合第三方框架的配置问题，导致了开发和部署效率的降低。\n', 'Spring Spring-Boot', 0, 1, 0, 0, '2022-07-26 00:00:51', '2022-07-26 00:05:27');
INSERT INTO `t_aricle` (`id`, `l_id`, `t_id`, `u_id`, `title`, `sub_title`, `content`, `keyword`, `read_count`, `status`, `delete_flag`, `version`, `create_time`, `update_time`) VALUES (1551599673322463233, 1551583015916720130, 1551584687699202050, 1551502630400090112, 'Spring Boot 是什么', 'Spring Boot 有哪些优点？主要给我们解决了哪些问题呢？我们以下图来说明：', '我们知道，从 2002 年开始，Spring 一直在飞速的发展，如今已经成为了在Java EE（Java Enterprise Edition）开发中真正意义上的标准，但是随着技术的发展，Java EE使用 Spring 逐渐变得笨重起来，大量的 XML 文件存在于项目之中。繁琐的配置，整合第三方框架的配置问题，导致了开发和部署效率的降低。\n', 'Spring Spring-Boot', 0, 1, 0, 0, '2022-07-26 00:06:04', '2022-07-26 00:06:47');
INSERT INTO `t_aricle` (`id`, `l_id`, `t_id`, `u_id`, `title`, `sub_title`, `content`, `keyword`, `read_count`, `status`, `delete_flag`, `version`, `create_time`, `update_time`) VALUES (1551599703706001410, 1551583015916720130, 1551584687699202050, 1551502630400090112, 'Spring Boot 能做什么', 'Spring Boot 有哪些优点？主要给我们解决了哪些问题呢？我们以下图来说明：', '我们知道，从 2002 年开始，Spring 一直在飞速的发展，如今已经成为了在Java EE（Java Enterprise Edition）开发中真正意义上的标准，但是随着技术的发展，Java EE使用 Spring 逐渐变得笨重起来，大量的 XML 文件存在于项目之中。繁琐的配置，整合第三方框架的配置问题，导致了开发和部署效率的降低。\n', 'Spring Spring-Boot', 0, 1, 0, 0, '2022-07-26 00:06:11', '2022-07-26 00:06:54');
INSERT INTO `t_aricle` (`id`, `l_id`, `t_id`, `u_id`, `title`, `sub_title`, `content`, `keyword`, `read_count`, `status`, `delete_flag`, `version`, `create_time`, `update_time`) VALUES (1551599735020675074, 1551583015916720130, 1551584687699202050, 1551502630400090112, 'Spring Boot 可以做到什么', 'Spring Boot 有哪些优点？主要给我们解决了哪些问题呢？我们以下图来说明：', '我们知道，从 2002 年开始，Spring 一直在飞速的发展，如今已经成为了在Java EE（Java Enterprise Edition）开发中真正意义上的标准，但是随着技术的发展，Java EE使用 Spring 逐渐变得笨重起来，大量的 XML 文件存在于项目之中。繁琐的配置，整合第三方框架的配置问题，导致了开发和部署效率的降低。\n', 'Spring Spring-Boot', 0, 1, 0, 0, '2022-07-26 00:06:18', '2022-07-26 00:07:02');
INSERT INTO `t_aricle` (`id`, `l_id`, `t_id`, `u_id`, `title`, `sub_title`, `content`, `keyword`, `read_count`, `status`, `delete_flag`, `version`, `create_time`, `update_time`) VALUES (1551599769728540674, 1551583015916720130, 1551584687699202050, 1551502630400090112, 'Spring Boot 真的那么优秀吗', 'Spring Boot 有哪些优点？主要给我们解决了哪些问题呢？我们以下图来说明：', '我们知道，从 2002 年开始，Spring 一直在飞速的发展，如今已经成为了在Java EE（Java Enterprise Edition）开发中真正意义上的标准，但是随着技术的发展，Java EE使用 Spring 逐渐变得笨重起来，大量的 XML 文件存在于项目之中。繁琐的配置，整合第三方框架的配置问题，导致了开发和部署效率的降低。\n', 'Spring Spring-Boot', 0, 1, 0, 0, '2022-07-26 00:06:27', '2022-07-26 00:07:10');
INSERT INTO `t_aricle` (`id`, `l_id`, `t_id`, `u_id`, `title`, `sub_title`, `content`, `keyword`, `read_count`, `status`, `delete_flag`, `version`, `create_time`, `update_time`) VALUES (1551600020753440769, 1551583015916720130, 1551584687699202050, 1551502630400090112, 'Spring Boot 我的最爱', 'Spring Boot 有哪些优点？主要给我们解决了哪些问题呢？我们以下图来说明：', '我们知道，从 2002 年开始，Spring 一直在飞速的发展，如今已经成为了在Java EE（Java Enterprise Edition）开发中真正意义上的标准，但是随着技术的发展，Java EE使用 Spring 逐渐变得笨重起来，大量的 XML 文件存在于项目之中。繁琐的配置，整合第三方框架的配置问题，导致了开发和部署效率的降低。\n', 'Spring Spring-Boot', 0, 0, 0, 0, '2022-07-26 00:07:26', '2022-07-26 00:07:26');
COMMIT;

-- ----------------------------
-- Table structure for t_launage
-- ----------------------------
DROP TABLE IF EXISTS `t_launage`;
CREATE TABLE `t_launage` (
  `id` bigint NOT NULL,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `descs` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  `delete_flag` int NOT NULL DEFAULT '0' COMMENT '0:未删除 1:逻辑删除',
  `version` int NOT NULL DEFAULT '0' COMMENT '乐观锁',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_launage
-- ----------------------------
BEGIN;
INSERT INTO `t_launage` (`id`, `name`, `descs`, `delete_flag`, `version`, `create_time`, `update_time`) VALUES (1551583015916720130, 'java', '面向对象开发语言', 0, 0, '2022-07-25 22:59:53', '2022-07-25 22:59:53');
INSERT INTO `t_launage` (`id`, `name`, `descs`, `delete_flag`, `version`, `create_time`, `update_time`) VALUES (1551583017351172097, 'golang', '高并发服务端开发', 0, 0, '2022-07-25 22:59:53', '2022-07-25 22:59:53');
COMMIT;

-- ----------------------------
-- Table structure for t_topic
-- ----------------------------
DROP TABLE IF EXISTS `t_topic`;
CREATE TABLE `t_topic` (
  `id` bigint NOT NULL,
  `l_id` bigint NOT NULL,
  `u_id` bigint NOT NULL COMMENT '发布者id',
  `title` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '专题名称',
  `sub_title` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '专题描述',
  `delete_flag` int NOT NULL DEFAULT '0' COMMENT '0:未删除 1:逻辑删除',
  `version` int NOT NULL DEFAULT '0' COMMENT '乐观锁',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_topic
-- ----------------------------
BEGIN;
INSERT INTO `t_topic` (`id`, `l_id`, `u_id`, `title`, `sub_title`, `delete_flag`, `version`, `create_time`, `update_time`) VALUES (1551584687699202050, 1551583015916720130, 1551502630400090112, 'SpringBoot 入门到精通', '适合新手学习的SpringBoot教程', 0, 0, '2022-07-25 23:06:31', '2022-07-25 23:06:31');
INSERT INTO `t_topic` (`id`, `l_id`, `u_id`, `title`, `sub_title`, `delete_flag`, `version`, `create_time`, `update_time`) VALUES (1551587183272366082, 1551583015916720130, 1551502630400090112, 'SpringBoot 深入学习', '学习SpringBoot精髓', 0, 0, '2022-07-25 23:16:26', '2022-07-25 23:16:26');
INSERT INTO `t_topic` (`id`, `l_id`, `u_id`, `title`, `sub_title`, `delete_flag`, `version`, `create_time`, `update_time`) VALUES (1551587184778121217, 1551583015916720130, 1551502630400090112, 'SpringCloud 教程', '适合新手学习的SpringCloud课程', 0, 0, '2022-07-25 23:16:26', '2022-07-25 23:16:26');
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint NOT NULL,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登录名',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `nickname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '昵称',
  `photo` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  `intro` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '签名介绍',
  `birthday` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '生日(年月日) yyyy-MM-dd',
  `sex` int NOT NULL DEFAULT '0' COMMENT '阅读量',
  `delete_flag` int NOT NULL DEFAULT '0' COMMENT '0:未删除 1:逻辑删除',
  `version` int NOT NULL DEFAULT '0' COMMENT '乐观锁',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` (`id`, `username`, `password`, `nickname`, `photo`, `intro`, `birthday`, `sex`, `delete_flag`, `version`, `create_time`, `update_time`) VALUES (1551502630400090112, 'hjw', 'e10adc3949ba59abbe56e057f20f883e', 'zijing', NULL, '我是一个比较懒的人~~', '1989-11-22', 0, 0, 0, '2022-07-25 17:40:27', '2022-07-25 17:40:27');
INSERT INTO `t_user` (`id`, `username`, `password`, `nickname`, `photo`, `intro`, `birthday`, `sex`, `delete_flag`, `version`, `create_time`, `update_time`) VALUES (1551505145518678016, 'hermit', 'e10adc3949ba59abbe56e057f20f883e', 'hermit', NULL, '我是一个比较勤快的人~~', '1979-11-22', 0, 0, 0, '2022-07-25 17:50:27', '2022-07-25 17:50:27');
INSERT INTO `t_user` (`id`, `username`, `password`, `nickname`, `photo`, `intro`, `birthday`, `sex`, `delete_flag`, `version`, `create_time`, `update_time`) VALUES (1551505312384933888, 'xiaoliu', 'e10adc3949ba59abbe56e057f20f883e', 'xiaoliu', NULL, '我喜欢阅读和运毒~~', '1988-09-22', 0, 0, 0, '2022-07-25 17:51:07', '2022-07-25 17:51:07');
INSERT INTO `t_user` (`id`, `username`, `password`, `nickname`, `photo`, `intro`, `birthday`, `sex`, `delete_flag`, `version`, `create_time`, `update_time`) VALUES (1551505604476194816, 'lishutong', 'e10adc3949ba59abbe56e057f20f883e', '李姝潼', NULL, '我最近不喜欢吃饭了~~', '2017-09-22', 0, 0, 0, '2022-07-25 17:52:17', '2022-07-25 17:52:17');
INSERT INTO `t_user` (`id`, `username`, `password`, `nickname`, `photo`, `intro`, `birthday`, `sex`, `delete_flag`, `version`, `create_time`, `update_time`) VALUES (1551505752069607424, 'houjiale', 'e10adc3949ba59abbe56e057f20f883e', '侯嘉乐', NULL, '我是一个活泼爱动的活力宝宝~~', '2016-12-12', 0, 0, 0, '2022-07-25 17:52:51', '2022-07-25 17:52:51');
INSERT INTO `t_user` (`id`, `username`, `password`, `nickname`, `photo`, `intro`, `birthday`, `sex`, `delete_flag`, `version`, `create_time`, `update_time`) VALUES (1551579417736589313, 'houjiale_123', 'e10adc3949ba59abbe56e057f20f883e', '侯嘉乐', NULL, '我是一个活泼爱动的活力宝宝~~', '2016-12-12', 0, 0, 0, '2022-07-25 22:45:35', '2022-07-25 22:45:35');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
