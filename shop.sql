/*
 Navicat Premium Data Transfer

 Source Server         : 47.99.178.93
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : 47.99.178.93:3306
 Source Schema         : shop

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 05/06/2021 15:00:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cartinfo
-- ----------------------------
DROP TABLE IF EXISTS `cartinfo`;
CREATE TABLE `cartinfo`  (
  `goodsid` int(6) NULL DEFAULT NULL,
  `userid` int(6) NULL DEFAULT NULL,
  `count` int(6) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of cartinfo
-- ----------------------------

-- ----------------------------
-- Table structure for goodsinfo
-- ----------------------------
DROP TABLE IF EXISTS `goodsinfo`;
CREATE TABLE `goodsinfo`  (
  `goodsid` int(6) NOT NULL AUTO_INCREMENT,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` int(10) NULL DEFAULT NULL,
  `reserve` int(6) NULL DEFAULT NULL,
  PRIMARY KEY (`goodsid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100025 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of goodsinfo
-- ----------------------------
INSERT INTO `goodsinfo` VALUES (100000, 'image/floor/floor1-1.jpg', '双开门冰箱', 5999, 14);
INSERT INTO `goodsinfo` VALUES (100001, 'image/floor/floor1-2.jpg', '电视', 8765, 19);
INSERT INTO `goodsinfo` VALUES (100002, 'image/floor/floor1-3.jpg', '洗衣机', 4566, 10);
INSERT INTO `goodsinfo` VALUES (100003, 'image/floor/floor1-4.jpg', '空调', 7686, 10);
INSERT INTO `goodsinfo` VALUES (100004, 'image/floor/floor1-5.jpg', '热水器', 3453, 8);
INSERT INTO `goodsinfo` VALUES (100005, 'image/floor/floor2-1.jpg', '笔记本电脑', 10989, 23);
INSERT INTO `goodsinfo` VALUES (100006, 'image/floor/floor2-2.jpg', '手机', 3999, 234);
INSERT INTO `goodsinfo` VALUES (100007, 'image/floor/floor2-3.jpg', '平板电脑', 6899, 78);
INSERT INTO `goodsinfo` VALUES (100008, 'image/floor/floor2-4.jpg', '数码相机', 9888, 67);
INSERT INTO `goodsinfo` VALUES (100009, 'image/floor/floor2-5.jpg', '3C配件', 67, 6);
INSERT INTO `goodsinfo` VALUES (100010, 'image/floor/floor3-1.jpg', '女装', 123, 34);
INSERT INTO `goodsinfo` VALUES (100011, 'image/floor/floor3-2.jpg', '帽子专区', 40, 67);
INSERT INTO `goodsinfo` VALUES (100012, 'image/floor/floor3-3.jpg', '旅行箱', 500, 56);
INSERT INTO `goodsinfo` VALUES (100013, 'image/floor/floor3-4.jpg', '手提包', 300, 345);
INSERT INTO `goodsinfo` VALUES (100014, 'image/floor/floor3-5.jpg', '保暖内衣', 99, 434);
INSERT INTO `goodsinfo` VALUES (100015, 'image/floor/floor4-1.jpg', '最爱零食', 50, 65);
INSERT INTO `goodsinfo` VALUES (100016, 'image/floor/floor4-2.jpg', '生鲜', 98, 453);
INSERT INTO `goodsinfo` VALUES (100017, 'image/floor/floor4-3.jpg', '半成品菜', 56, 754);
INSERT INTO `goodsinfo` VALUES (100018, 'image/floor/floor4-4.jpg', '速冻专区', 50, 132);
INSERT INTO `goodsinfo` VALUES (100019, 'image/floor/floor4-5.jpg', '进口牛奶', 80, 884);
INSERT INTO `goodsinfo` VALUES (100020, 'image/floor/floor5-1.jpg', '白酒', 198, 435);
INSERT INTO `goodsinfo` VALUES (100021, 'image/floor/floor5-2.jpg', '红酒', 178, 645);
INSERT INTO `goodsinfo` VALUES (100022, 'image/floor/floor5-3.jpg', '饮料', 25, 675);
INSERT INTO `goodsinfo` VALUES (100023, 'image/floor/floor5-4.jpg', '调制鸡尾酒', 40, 100);
INSERT INTO `goodsinfo` VALUES (100024, 'image/floor/floor5-5.jpg', '进口洋酒', 6000, 324);

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo`  (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES (9, 'root', '123456');

SET FOREIGN_KEY_CHECKS = 1;
