/*
 Navicat Premium Data Transfer

 Source Server         : test1
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : jingdongdb

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 21/12/2018 22:37:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `addressID` int(11) NOT NULL AUTO_INCREMENT COMMENT '地址ID',
  `userID` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' 用户ID--外键',
  `province` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '省',
  `city` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' 市',
  `block` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' 区',
  `street` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' 街道',
  `address` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT ' 详细地址',
  `receiver` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' 收件人',
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收件人手机',
  PRIMARY KEY (`addressID`) USING BTREE,
  INDEX `addressID`(`addressID`) USING BTREE,
  INDEX `addressID_2`(`addressID`) USING BTREE,
  INDEX `au_fk`(`userID`) USING BTREE,
  INDEX `addressID_3`(`addressID`) USING BTREE,
  INDEX `addressID_4`(`addressID`) USING BTREE,
  CONSTRAINT `au_fk` FOREIGN KEY (`userID`) REFERENCES `user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (2, 'cwj', '北京', '北京', '海淀', '学院路', '清华东路35号', 'lxk', '17801122933');
INSERT INTO `address` VALUES (3, 'smh', '北京', '北京', '海淀', '学院路', '清华东路35号', 'lxk', '17801122933');
INSERT INTO `address` VALUES (4, 'lxk', '北京', '北京', '海淀', '学院路', '清华东路35号北京林业大学', 'lxk', '17801122933');
INSERT INTO `address` VALUES (5, 'hyl', '北京', '北京', '海淀', '学院路', '清华东路35号北京林业大学', 'lxk', '17801122933');
INSERT INTO `address` VALUES (6, 'hyl', '北京', '北京', '海淀', '学院路', '清华东路35号北京林业大学', 'cwj', '13121862811');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `categoryID` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别ID',
  `categoryName` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' 类别名--unique',
  `parentID` int(11) NULL DEFAULT NULL COMMENT '父类别ID--外键',
  PRIMARY KEY (`categoryID`) USING BTREE,
  UNIQUE INDEX `categoryName`(`categoryName`) USING BTREE,
  INDEX `categoryID`(`categoryID`) USING BTREE,
  INDEX `categoryID_2`(`categoryID`) USING BTREE,
  INDEX `categoryID_3`(`categoryID`) USING BTREE,
  INDEX `categoryID_4`(`categoryID`) USING BTREE,
  INDEX `parentID_fk`(`parentID`) USING BTREE,
  INDEX `categoryID_5`(`categoryID`) USING BTREE,
  INDEX `categoryID_6`(`categoryID`) USING BTREE,
  INDEX `categoryID_7`(`categoryID`) USING BTREE,
  INDEX `categoryID_8`(`categoryID`) USING BTREE,
  INDEX `categoryID_9`(`categoryID`) USING BTREE,
  INDEX `categoryID_10`(`categoryID`) USING BTREE,
  CONSTRAINT `parentID_fk` FOREIGN KEY (`parentID`) REFERENCES `category` (`categoryid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '家用电器', NULL);
INSERT INTO `category` VALUES (2, '服装', NULL);
INSERT INTO `category` VALUES (3, '母婴', NULL);
INSERT INTO `category` VALUES (4, '食品', NULL);
INSERT INTO `category` VALUES (5, '箱包', NULL);
INSERT INTO `category` VALUES (6, '电视机', 1);
INSERT INTO `category` VALUES (7, '冰箱', 1);
INSERT INTO `category` VALUES (8, '男装', 2);
INSERT INTO `category` VALUES (9, '女装', 2);
INSERT INTO `category` VALUES (10, '奶粉', 3);
INSERT INTO `category` VALUES (11, '营养辅食', 3);
INSERT INTO `category` VALUES (12, '零食饮料', 4);
INSERT INTO `category` VALUES (13, '水果蔬菜', 4);
INSERT INTO `category` VALUES (14, '潮流女包', 5);
INSERT INTO `category` VALUES (15, '功能箱包', 5);
INSERT INTO `category` VALUES (16, '液晶电视', 6);
INSERT INTO `category` VALUES (17, '4k超清电视', 6);
INSERT INTO `category` VALUES (18, '苹果', 13);
INSERT INTO `category` VALUES (19, '西红柿', 13);
INSERT INTO `category` VALUES (20, '橘子', 13);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `commentID` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `commentTime` datetime(0) NULL DEFAULT NULL COMMENT '评价时间',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `score` int(11) NULL DEFAULT NULL COMMENT '评分',
  `orderID` int(11) NULL DEFAULT NULL COMMENT '订单ID--外键',
  `productID` int(11) NULL DEFAULT NULL COMMENT '产品ID--外键',
  `ispassed` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'N' COMMENT '是否审核通过',
  PRIMARY KEY (`commentID`) USING BTREE,
  INDEX `cop_fk`(`orderID`, `productID`) USING BTREE,
  CONSTRAINT `cop_fk` FOREIGN KEY (`orderID`, `productID`) REFERENCES `orderproduct` (`orderid`, `productid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (2, '2018-12-19 21:33:39', '66666666666666666', 5, 2, 1, 'Y');
INSERT INTO `comment` VALUES (3, '2018-12-19 21:34:27', '666666666', 5, 3, 1, 'Y');
INSERT INTO `comment` VALUES (4, '2018-12-19 21:55:28', '666666666', 5, 3, 1, 'N');
INSERT INTO `comment` VALUES (5, '2018-12-20 21:17:28', '嗯，商品很不错，给个赞！', 5, 2, 2, 'Y');
INSERT INTO `comment` VALUES (6, '2018-12-20 21:27:24', '嗯，商品很不错，给个赞！', 5, 2, 2, 'N');

-- ----------------------------
-- Table structure for detailed
-- ----------------------------
DROP TABLE IF EXISTS `detailed`;
CREATE TABLE `detailed`  (
  `userID` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户登录名--外键',
  `nickName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `gender` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '保密' COMMENT '性别--默认保密',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `trueName` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `idNumber` char(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `jdBean` int(11) NOT NULL DEFAULT 0 COMMENT '京豆数--默认0',
  PRIMARY KEY (`userID`) USING BTREE,
  CONSTRAINT `rd_fk` FOREIGN KEY (`userID`) REFERENCES `user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of detailed
-- ----------------------------
INSERT INTO `detailed` VALUES ('cwj', 'dau', '女', '1998-07-20', '陈婉菁', '', 0);
INSERT INTO `detailed` VALUES ('hyl', 'yolanhe', '女', '1998-04-01', '何钰霖', '', 0);
INSERT INTO `detailed` VALUES ('lxk', 'kezi', '女', '1998-05-10', '梁晓珂', '410185199904283522', 0);
INSERT INTO `detailed` VALUES ('smh', 'sunshine', '女', '1998-10-03', '宋明惠', '', 0);

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news`  (
  `newsID` int(11) NOT NULL AUTO_INCREMENT COMMENT '资讯ID',
  `storeID` int(11) NOT NULL COMMENT '发布店铺ID--外键',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' 咨询内容',
  `publishTime` datetime(0) NOT NULL COMMENT ' 发布时间',
  `visitVolume` int(11) NULL DEFAULT NULL COMMENT ' 阅读量',
  PRIMARY KEY (`newsID`) USING BTREE,
  INDEX `ns_fk`(`storeID`) USING BTREE,
  INDEX `newsID`(`newsID`) USING BTREE,
  CONSTRAINT `ns_fk` FOREIGN KEY (`storeID`) REFERENCES `store` (`storeid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES (1, 1, '小米官方旗舰店4k超清电视机新品首发', '2018-12-20 14:34:28', 19000);
INSERT INTO `news` VALUES (2, 2, '对你爱爱爱不完，海尔君忍不住唱出来，感谢对咱家宝贝的认可', '2018-12-20 14:37:22', 55);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `orderID` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `userID` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' 用户ID--外键',
  `orderTime` datetime(0) NOT NULL COMMENT '下单时间',
  `addressID` int(11) NOT NULL COMMENT ' 地址ID--外键',
  PRIMARY KEY (`orderID`) USING BTREE,
  INDEX `orderID`(`orderID`) USING BTREE,
  INDEX `orderID_2`(`orderID`) USING BTREE,
  INDEX `orderID_3`(`orderID`) USING BTREE,
  INDEX `oa_fk`(`addressID`) USING BTREE,
  INDEX `ou_fk`(`userID`) USING BTREE,
  INDEX `orderID_4`(`orderID`) USING BTREE,
  CONSTRAINT `oa_fk` FOREIGN KEY (`addressID`) REFERENCES `address` (`addressid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ou_fk` FOREIGN KEY (`userID`) REFERENCES `user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (2, 'cwj', '2018-12-19 21:31:45', 2);
INSERT INTO `order` VALUES (3, 'smh', '2018-12-19 21:32:07', 3);

-- ----------------------------
-- Table structure for orderproduct
-- ----------------------------
DROP TABLE IF EXISTS `orderproduct`;
CREATE TABLE `orderproduct`  (
  `orderID` int(11) NOT NULL,
  `productID` int(11) NOT NULL,
  PRIMARY KEY (`orderID`, `productID`) USING BTREE,
  INDEX `orderID`(`orderID`, `productID`) USING BTREE,
  INDEX `opp_fk`(`productID`) USING BTREE,
  INDEX `orderID_2`(`orderID`, `productID`) USING BTREE,
  CONSTRAINT `opo_fk` FOREIGN KEY (`orderID`) REFERENCES `order` (`orderid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `opp_fk` FOREIGN KEY (`productID`) REFERENCES `product` (`productID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderproduct
-- ----------------------------
INSERT INTO `orderproduct` VALUES (2, 1);
INSERT INTO `orderproduct` VALUES (3, 1);
INSERT INTO `orderproduct` VALUES (2, 2);
INSERT INTO `orderproduct` VALUES (3, 2);
INSERT INTO `orderproduct` VALUES (2, 3);
INSERT INTO `orderproduct` VALUES (3, 4);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `productID` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `productName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名',
  `weight` float(6, 3) NOT NULL COMMENT '重量',
  `visitVolume` int(11) NOT NULL DEFAULT 0 COMMENT '访问量--默认0',
  `model` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' 型号',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' 描述',
  `categoryID` int(11) NOT NULL COMMENT '类别--外键',
  `jdBean` int(11) NULL DEFAULT 0 COMMENT '京豆数--默认0',
  `status` bit(1) NULL DEFAULT b'1' COMMENT '状态',
  `price` float(10, 2) NOT NULL COMMENT '产品价格',
  `storeID` int(11) NOT NULL COMMENT '所属店铺ID',
  PRIMARY KEY (`productID`) USING BTREE,
  INDEX `productID`(`productID`) USING BTREE,
  INDEX `productID_2`(`productID`) USING BTREE,
  INDEX `productID_3`(`productID`) USING BTREE,
  INDEX `productID_4`(`productID`) USING BTREE,
  INDEX `productID_5`(`productID`) USING BTREE,
  INDEX `productID_6`(`productID`) USING BTREE,
  INDEX `productID_7`(`productID`) USING BTREE,
  INDEX `productID_8`(`productID`) USING BTREE,
  INDEX `pc_fk`(`categoryID`) USING BTREE,
  INDEX `ps_fk2`(`storeID`) USING BTREE,
  CONSTRAINT `pc_fk` FOREIGN KEY (`categoryID`) REFERENCES `category` (`categoryid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ps_fk2` FOREIGN KEY (`storeID`) REFERENCES `store` (`storeid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '液晶电视', 0.000, 256, '普通版', '索尼（SONY）KD-65X9000F 65英寸 大屏4K超清 智能液晶平板电视 精锐光控Pro增强版（黑色）', 16, 0, b'1', 11999.00, 1);
INSERT INTO `product` VALUES (2, '曲面电视', 0.000, 222, '55英寸曲面', '小米（MI）小米电视4S 55英寸 曲面 L55M5-AQ 2GB+8GB HDR 4K超高清 蓝牙语音 人工智能语音网络液晶平板电视 ', 6, 0, b'1', 3299.00, 1);
INSERT INTO `product` VALUES (3, '全高清电视', 0.000, 3434, '40英寸1GB+4GB', '小米（MI）小米电视4C 40英寸 L40M5-4C 1GB+4GB 人工智能网络液晶平板电视', 6, 0, b'1', 1239.00, 1);
INSERT INTO `product` VALUES (4, '4k超清电视', 0.000, 2342, '55英寸人工智能4K新品', '海信（Hisense）H55E3A 55英寸 超高清4K HDR 金属背板 人工智能电视 丰富影视教育资源 ', 17, 0, b'1', 2399.00, 1);

-- ----------------------------
-- Table structure for productcollect
-- ----------------------------
DROP TABLE IF EXISTS `productcollect`;
CREATE TABLE `productcollect`  (
  `pcollectID` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品收藏ID',
  `userID` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' 用户ID--外键--unique',
  PRIMARY KEY (`pcollectID`) USING BTREE,
  UNIQUE INDEX `userID`(`userID`) USING BTREE,
  INDEX `pcollectID`(`pcollectID`) USING BTREE,
  INDEX `pcollectID_2`(`pcollectID`) USING BTREE,
  CONSTRAINT `pr_fk` FOREIGN KEY (`userID`) REFERENCES `user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of productcollect
-- ----------------------------
INSERT INTO `productcollect` VALUES (1, 'cwj');
INSERT INTO `productcollect` VALUES (2, 'hyl');
INSERT INTO `productcollect` VALUES (10, 'lxk');
INSERT INTO `productcollect` VALUES (3, 'smh');

-- ----------------------------
-- Table structure for productcollectproduct
-- ----------------------------
DROP TABLE IF EXISTS `productcollectproduct`;
CREATE TABLE `productcollectproduct`  (
  `pcollectID` int(11) NOT NULL COMMENT '产品收藏ID',
  `productID` int(255) NOT NULL COMMENT '产品ID',
  PRIMARY KEY (`pcollectID`, `productID`) USING BTREE,
  INDEX `pcpp_fk`(`productID`) USING BTREE,
  CONSTRAINT `pcpc_fk` FOREIGN KEY (`pcollectID`) REFERENCES `productcollect` (`pcollectid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pcpp_fk` FOREIGN KEY (`productID`) REFERENCES `product` (`productID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of productcollectproduct
-- ----------------------------
INSERT INTO `productcollectproduct` VALUES (1, 1);
INSERT INTO `productcollectproduct` VALUES (2, 1);
INSERT INTO `productcollectproduct` VALUES (3, 1);
INSERT INTO `productcollectproduct` VALUES (10, 1);
INSERT INTO `productcollectproduct` VALUES (2, 2);
INSERT INTO `productcollectproduct` VALUES (3, 2);
INSERT INTO `productcollectproduct` VALUES (10, 2);
INSERT INTO `productcollectproduct` VALUES (1, 3);
INSERT INTO `productcollectproduct` VALUES (2, 3);
INSERT INTO `productcollectproduct` VALUES (3, 3);
INSERT INTO `productcollectproduct` VALUES (1, 4);
INSERT INTO `productcollectproduct` VALUES (2, 4);
INSERT INTO `productcollectproduct` VALUES (3, 4);

-- ----------------------------
-- Table structure for shoppingcart
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcart`;
CREATE TABLE `shoppingcart`  (
  `shoppingcartID` int(11) NOT NULL AUTO_INCREMENT COMMENT '购物车ID',
  `userID` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID--外键',
  PRIMARY KEY (`shoppingcartID`) USING BTREE,
  UNIQUE INDEX `userID`(`userID`) USING BTREE,
  INDEX `shoppingcartID`(`shoppingcartID`) USING BTREE,
  CONSTRAINT `shu_fk` FOREIGN KEY (`userID`) REFERENCES `user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shoppingcart
-- ----------------------------
INSERT INTO `shoppingcart` VALUES (4, 'cwj');
INSERT INTO `shoppingcart` VALUES (2, 'hyl');
INSERT INTO `shoppingcart` VALUES (1, 'lxk');
INSERT INTO `shoppingcart` VALUES (3, 'smh');

-- ----------------------------
-- Table structure for shoppingcartproduct
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcartproduct`;
CREATE TABLE `shoppingcartproduct`  (
  `shoppingcartID` int(11) NOT NULL COMMENT '购物车ID',
  `productID` int(11) NOT NULL COMMENT '产品ID',
  PRIMARY KEY (`shoppingcartID`, `productID`) USING BTREE,
  INDEX `spp_fk`(`productID`) USING BTREE,
  CONSTRAINT `spp_fk` FOREIGN KEY (`productID`) REFERENCES `product` (`productID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sps_fk` FOREIGN KEY (`shoppingcartID`) REFERENCES `shoppingcart` (`shoppingcartid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shoppingcartproduct
-- ----------------------------
INSERT INTO `shoppingcartproduct` VALUES (1, 1);
INSERT INTO `shoppingcartproduct` VALUES (3, 1);
INSERT INTO `shoppingcartproduct` VALUES (1, 2);
INSERT INTO `shoppingcartproduct` VALUES (3, 2);
INSERT INTO `shoppingcartproduct` VALUES (4, 2);
INSERT INTO `shoppingcartproduct` VALUES (2, 3);
INSERT INTO `shoppingcartproduct` VALUES (3, 3);
INSERT INTO `shoppingcartproduct` VALUES (4, 3);
INSERT INTO `shoppingcartproduct` VALUES (2, 4);
INSERT INTO `shoppingcartproduct` VALUES (4, 4);

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store`  (
  `storeID` int(11) NOT NULL AUTO_INCREMENT COMMENT '店铺ID',
  `userID` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' 用户登录名--外键--unique',
  `storeName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' 店铺名--unique',
  `score` float(2, 1) NOT NULL COMMENT ' 店铺评分',
  PRIMARY KEY (`storeID`) USING BTREE,
  UNIQUE INDEX `storeName`(`storeName`) USING BTREE,
  UNIQUE INDEX `userID`(`userID`) USING BTREE,
  INDEX `storeID`(`storeID`) USING BTREE,
  INDEX `storeID_2`(`storeID`) USING BTREE,
  INDEX `storeID_3`(`storeID`) USING BTREE,
  INDEX `storeID_4`(`storeID`) USING BTREE,
  INDEX `storeID_5`(`storeID`) USING BTREE,
  INDEX `storeID_6`(`storeID`) USING BTREE,
  INDEX `storeID_7`(`storeID`) USING BTREE,
  INDEX `storeID_8`(`storeID`) USING BTREE,
  INDEX `storeID_9`(`storeID`) USING BTREE,
  INDEX `storeID_10`(`storeID`) USING BTREE,
  INDEX `storeID_11`(`storeID`) USING BTREE,
  INDEX `storeID_12`(`storeID`) USING BTREE,
  INDEX `storeID_13`(`storeID`) USING BTREE,
  INDEX `storeID_14`(`storeID`) USING BTREE,
  INDEX `storeID_15`(`storeID`) USING BTREE,
  INDEX `storeID_16`(`storeID`) USING BTREE,
  INDEX `storeID_17`(`storeID`) USING BTREE,
  INDEX `storeID_18`(`storeID`) USING BTREE,
  INDEX `storeID_19`(`storeID`) USING BTREE,
  INDEX `storeID_20`(`storeID`) USING BTREE,
  INDEX `storeID_21`(`storeID`) USING BTREE,
  INDEX `storeID_22`(`storeID`) USING BTREE,
  CONSTRAINT `sr_fk` FOREIGN KEY (`userID`) REFERENCES `user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of store
-- ----------------------------
INSERT INTO `store` VALUES (1, 'lxk', 'lxk\'s store', 5.0);
INSERT INTO `store` VALUES (2, 'smh', 'smh\'s store', 5.0);
INSERT INTO `store` VALUES (3, 'hyl', 'hyl\'s store', 5.0);
INSERT INTO `store` VALUES (4, 'cwj', 'cwj\'s store', 5.0);

-- ----------------------------
-- Table structure for storecollect
-- ----------------------------
DROP TABLE IF EXISTS `storecollect`;
CREATE TABLE `storecollect`  (
  `scollectID` int(11) NOT NULL AUTO_INCREMENT COMMENT '店铺收藏ID',
  `userID` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' 用户ID--外键--unique',
  PRIMARY KEY (`scollectID`) USING BTREE,
  UNIQUE INDEX `userID`(`userID`) USING BTREE,
  INDEX `scollectID`(`scollectID`) USING BTREE,
  INDEX `scollectID_2`(`scollectID`) USING BTREE,
  CONSTRAINT `scr_fk` FOREIGN KEY (`userID`) REFERENCES `user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of storecollect
-- ----------------------------
INSERT INTO `storecollect` VALUES (1, 'cwj');
INSERT INTO `storecollect` VALUES (2, 'hyl');
INSERT INTO `storecollect` VALUES (3, 'lxk');
INSERT INTO `storecollect` VALUES (4, 'smh');

-- ----------------------------
-- Table structure for storecollectstore
-- ----------------------------
DROP TABLE IF EXISTS `storecollectstore`;
CREATE TABLE `storecollectstore`  (
  `scollectID` int(11) NOT NULL COMMENT '店铺收藏ID',
  `storeID` int(11) NOT NULL COMMENT '店铺ID',
  PRIMARY KEY (`scollectID`, `storeID`) USING BTREE,
  INDEX `scss_fk`(`storeID`) USING BTREE,
  CONSTRAINT `scsc_fk` FOREIGN KEY (`scollectID`) REFERENCES `storecollect` (`scollectid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `scss_fk` FOREIGN KEY (`storeID`) REFERENCES `store` (`storeid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of storecollectstore
-- ----------------------------
INSERT INTO `storecollectstore` VALUES (1, 1);
INSERT INTO `storecollectstore` VALUES (2, 1);
INSERT INTO `storecollectstore` VALUES (3, 1);
INSERT INTO `storecollectstore` VALUES (4, 1);
INSERT INTO `storecollectstore` VALUES (2, 2);
INSERT INTO `storecollectstore` VALUES (3, 2);
INSERT INTO `storecollectstore` VALUES (1, 3);
INSERT INTO `storecollectstore` VALUES (2, 3);
INSERT INTO `storecollectstore` VALUES (3, 3);
INSERT INTO `storecollectstore` VALUES (4, 3);
INSERT INTO `storecollectstore` VALUES (1, 4);
INSERT INTO `storecollectstore` VALUES (2, 4);
INSERT INTO `storecollectstore` VALUES (3, 4);

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic`  (
  `topicID` int(11) NOT NULL AUTO_INCREMENT COMMENT '话题ID',
  `topicName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT ' 话题名称',
  PRIMARY KEY (`topicID`) USING BTREE,
  UNIQUE INDEX `topicName`(`topicName`) USING BTREE,
  INDEX `topicID`(`topicID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES (2, '买家秀');
INSERT INTO `topic` VALUES (4, '天使穿搭志');
INSERT INTO `topic` VALUES (3, '新品剧透');
INSERT INTO `topic` VALUES (1, '深夜互动');

-- ----------------------------
-- Table structure for topicnews
-- ----------------------------
DROP TABLE IF EXISTS `topicnews`;
CREATE TABLE `topicnews`  (
  `topicID` int(11) NOT NULL COMMENT '话题ID',
  `newsID` int(11) NOT NULL COMMENT ' 资讯ID',
  PRIMARY KEY (`topicID`, `newsID`) USING BTREE,
  INDEX `tnn_fk`(`newsID`) USING BTREE,
  CONSTRAINT `tnn_fk` FOREIGN KEY (`newsID`) REFERENCES `news` (`newsid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tnt_fk` FOREIGN KEY (`topicID`) REFERENCES `topic` (`topicID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of topicnews
-- ----------------------------
INSERT INTO `topicnews` VALUES (3, 1);
INSERT INTO `topicnews` VALUES (1, 2);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userID` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户登录名',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机--unique',
  `mail` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱--unique',
  PRIMARY KEY (`userID`) USING BTREE,
  UNIQUE INDEX `phone`(`phone`) USING BTREE,
  UNIQUE INDEX `mail`(`mail`) USING BTREE,
  INDEX `userID`(`userID`) USING BTREE,
  INDEX `userID_2`(`userID`) USING BTREE,
  INDEX `userID_3`(`userID`) USING BTREE,
  INDEX `userID_4`(`userID`) USING BTREE,
  INDEX `userID_5`(`userID`) USING BTREE,
  INDEX `userID_6`(`userID`) USING BTREE,
  INDEX `userID_7`(`userID`) USING BTREE,
  INDEX `userID_8`(`userID`) USING BTREE,
  INDEX `userID_9`(`userID`) USING BTREE,
  INDEX `userID_10`(`userID`) USING BTREE,
  INDEX `userID_11`(`userID`) USING BTREE,
  INDEX `userID_12`(`userID`) USING BTREE,
  INDEX `userID_13`(`userID`) USING BTREE,
  INDEX `userID_14`(`userID`) USING BTREE,
  INDEX `userID_15`(`userID`) USING BTREE,
  INDEX `userID_16`(`userID`) USING BTREE,
  INDEX `userID_17`(`userID`) USING BTREE,
  INDEX `userID_18`(`userID`) USING BTREE,
  INDEX `userID_19`(`userID`) USING BTREE,
  INDEX `userID_20`(`userID`) USING BTREE,
  INDEX `userID_21`(`userID`) USING BTREE,
  INDEX `userID_22`(`userID`) USING BTREE,
  INDEX `userID_23`(`userID`) USING BTREE,
  INDEX `userID_24`(`userID`) USING BTREE,
  INDEX `userID_25`(`userID`) USING BTREE,
  INDEX `userID_26`(`userID`) USING BTREE,
  INDEX `userID_27`(`userID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('cwj', '123', '13121862811', 'chenwanjing@126.com');
INSERT INTO `user` VALUES ('hyl', '123', '18926922280', 'yolanehe@126.com');
INSERT INTO `user` VALUES ('lxk', '123', '17801122933', '1607155178@qq.com');
INSERT INTO `user` VALUES ('smh', '123', '13269016600', 'songminghui@126.com');

SET FOREIGN_KEY_CHECKS = 1;
