/*
Navicat MySQL Data Transfer

Source Server         : 121.42.57.149
Source Server Version : 50547
Source Host           : 121.42.57.149:3306
Source Database       : logistics_system

Target Server Type    : MYSQL
Target Server Version : 50547
File Encoding         : 65001

Date: 2016-05-04 20:34:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` bigint(20) DEFAULT NULL,
  `IsDeleted` tinyint(1) DEFAULT NULL,
  `modify_time` bigint(20) DEFAULT NULL,
  `AreaName` varchar(64) DEFAULT NULL,
  `Level` int(11) DEFAULT NULL,
  `Pinyin` varchar(32) DEFAULT NULL,
  `parent` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKBB979BF450F36A7B` (`parent`) USING BTREE,
  CONSTRAINT `address_ibfk_1` FOREIGN KEY (`parent`) REFERENCES `address` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('1', '1461246317974', '0', '1461554285837', '青岛市', '1', 'qingdaoshi', null);

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` bigint(20) DEFAULT NULL,
  `IsDeleted` tinyint(1) DEFAULT NULL,
  `modify_time` bigint(20) DEFAULT NULL,
  `adminName` varchar(255) DEFAULT NULL,
  `password` tinyblob,
  `salt` tinyblob,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('2', '1461202916785', '0', '1461202916785', 'admin', 0x5DC7E80959C7A54C61DBB13C233AAC24, 0x524E7D57, '1');
INSERT INTO `admin` VALUES ('3', '1461327332194', '0', '1461327332194', '李家沽', 0x250620AAAA4FB587C6EC4AD07F625BE0, 0xEA84E9D7, '3');

-- ----------------------------
-- Table structure for carbusiness
-- ----------------------------
DROP TABLE IF EXISTS `carbusiness`;
CREATE TABLE `carbusiness` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` bigint(20) DEFAULT NULL,
  `IsDeleted` tinyint(1) DEFAULT NULL,
  `modify_time` bigint(20) DEFAULT NULL,
  `DateOfFinishing` bigint(20) DEFAULT NULL,
  `DateOfStarting` bigint(20) DEFAULT NULL,
  `taskId` int(11) DEFAULT NULL,
  `vehicle` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKEA3D8FB4D76205CB` (`taskId`) USING BTREE,
  KEY `FKEA3D8FB4F977E435` (`vehicle`) USING BTREE,
  CONSTRAINT `carbusiness_ibfk_1` FOREIGN KEY (`taskId`) REFERENCES `transporttask` (`ID`),
  CONSTRAINT `carbusiness_ibfk_2` FOREIGN KEY (`vehicle`) REFERENCES `vehicle` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of carbusiness
-- ----------------------------

-- ----------------------------
-- Table structure for carBusiness
-- ----------------------------
DROP TABLE IF EXISTS `carBusiness`;
CREATE TABLE `carBusiness` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` bigint(20) DEFAULT NULL,
  `IsDeleted` tinyint(1) DEFAULT NULL,
  `modify_time` bigint(20) DEFAULT NULL,
  `DateOfFinishing` bigint(20) DEFAULT NULL,
  `DateOfStarting` bigint(20) DEFAULT NULL,
  `taskId` int(11) DEFAULT NULL,
  `vehicle` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKEA3D8FB4D76205CB` (`taskId`),
  KEY `FKEA3D8FB4F977E435` (`vehicle`),
  CONSTRAINT `FKEA3D8FB4F977E435` FOREIGN KEY (`vehicle`) REFERENCES `vehicle` (`ID`),
  CONSTRAINT `FKEA3D8FB4D76205CB` FOREIGN KEY (`taskId`) REFERENCES `transportTask` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of carBusiness
-- ----------------------------
INSERT INTO `carBusiness` VALUES ('1', '1461554322418', '0', '1461554322418', '1464232721465', '1464146321465', null, '1');

-- ----------------------------
-- Table structure for cargo
-- ----------------------------
DROP TABLE IF EXISTS `cargo`;
CREATE TABLE `cargo` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` bigint(20) DEFAULT NULL,
  `IsDeleted` tinyint(1) DEFAULT NULL,
  `modify_time` bigint(20) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `price` int(11) DEFAULT NULL,
  `weight` int(11) NOT NULL,
  `taskId` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK5A0E7BCD76205CB` (`taskId`) USING BTREE,
  CONSTRAINT `cargo_ibfk_1` FOREIGN KEY (`taskId`) REFERENCES `transporttask` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cargo
-- ----------------------------

-- ----------------------------
-- Table structure for carrierbusiness
-- ----------------------------
DROP TABLE IF EXISTS `carrierbusiness`;
CREATE TABLE `carrierbusiness` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` bigint(20) DEFAULT NULL,
  `IsDeleted` tinyint(1) DEFAULT NULL,
  `modify_time` bigint(20) DEFAULT NULL,
  `isPayed` tinyint(1) DEFAULT NULL,
  `companyId` int(11) DEFAULT NULL,
  `taskId` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK31178AD8B52220B2` (`companyId`) USING BTREE,
  KEY `FK31178AD8D76205CB` (`taskId`) USING BTREE,
  CONSTRAINT `carrierbusiness_ibfk_1` FOREIGN KEY (`companyId`) REFERENCES `company` (`ID`),
  CONSTRAINT `carrierbusiness_ibfk_2` FOREIGN KEY (`taskId`) REFERENCES `transporttask` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of carrierbusiness
-- ----------------------------

-- ----------------------------
-- Table structure for carrierBusiness
-- ----------------------------
DROP TABLE IF EXISTS `carrierBusiness`;
CREATE TABLE `carrierBusiness` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` bigint(20) DEFAULT NULL,
  `IsDeleted` tinyint(1) DEFAULT NULL,
  `modify_time` bigint(20) DEFAULT NULL,
  `isPayed` tinyint(1) DEFAULT NULL,
  `companyId` int(11) DEFAULT NULL,
  `taskId` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK31178AD8B52220B2` (`companyId`),
  KEY `FK31178AD8D76205CB` (`taskId`),
  CONSTRAINT `FK31178AD8D76205CB` FOREIGN KEY (`taskId`) REFERENCES `transportTask` (`ID`),
  CONSTRAINT `FK31178AD8B52220B2` FOREIGN KEY (`companyId`) REFERENCES `company` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of carrierBusiness
-- ----------------------------

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` bigint(20) DEFAULT NULL,
  `IsDeleted` tinyint(1) DEFAULT NULL,
  `modify_time` bigint(20) DEFAULT NULL,
  `companyName` varchar(255) DEFAULT NULL,
  `corporation` varchar(255) DEFAULT NULL,
  `introduction` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('2', '1461246269618', '0', '1461246269618', '顺丰', '王卫', '同城快递', '15345320123');

-- ----------------------------
-- Table structure for con_test
-- ----------------------------
DROP TABLE IF EXISTS `con_test`;
CREATE TABLE `con_test` (
  `a` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of con_test
-- ----------------------------

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` bigint(20) DEFAULT NULL,
  `IsDeleted` tinyint(1) DEFAULT NULL,
  `modify_time` bigint(20) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `goodsName` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `sumPrice` int(11) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `weight` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '1461246433696', '0', '1461554244830', '10', '快递车辆', '卡车', '1000000', '0', '1', '4000');
INSERT INTO `goods` VALUES ('2', '1461327600412', '0', '1461327600412', '20', '上午公车', '汽车', '10000', '0', '2', '40000');

-- ----------------------------
-- Table structure for goodsgroup
-- ----------------------------
DROP TABLE IF EXISTS `goodsgroup`;
CREATE TABLE `goodsgroup` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` bigint(20) DEFAULT NULL,
  `IsDeleted` tinyint(1) DEFAULT NULL,
  `modify_time` bigint(20) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `goods_ID` int(11) DEFAULT NULL,
  `goodsRecord_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK102594C9475FC317` (`goods_ID`) USING BTREE,
  KEY `FK102594C9A624C7D7` (`goodsRecord_ID`) USING BTREE,
  CONSTRAINT `goodsgroup_ibfk_1` FOREIGN KEY (`goods_ID`) REFERENCES `goods` (`ID`),
  CONSTRAINT `goodsgroup_ibfk_2` FOREIGN KEY (`goodsRecord_ID`) REFERENCES `goodsrecord` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goodsgroup
-- ----------------------------

-- ----------------------------
-- Table structure for goodsGroup
-- ----------------------------
DROP TABLE IF EXISTS `goodsGroup`;
CREATE TABLE `goodsGroup` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` bigint(20) DEFAULT NULL,
  `IsDeleted` tinyint(1) DEFAULT NULL,
  `modify_time` bigint(20) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `goods_ID` int(11) DEFAULT NULL,
  `goodsRecord_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK102594C9475FC317` (`goods_ID`),
  KEY `FK102594C9A624C7D7` (`goodsRecord_ID`),
  CONSTRAINT `FK102594C9A624C7D7` FOREIGN KEY (`goodsRecord_ID`) REFERENCES `goodsRecord` (`ID`),
  CONSTRAINT `FK102594C9475FC317` FOREIGN KEY (`goods_ID`) REFERENCES `goods` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goodsGroup
-- ----------------------------

-- ----------------------------
-- Table structure for goodsrecord
-- ----------------------------
DROP TABLE IF EXISTS `goodsrecord`;
CREATE TABLE `goodsrecord` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` bigint(20) DEFAULT NULL,
  `IsDeleted` tinyint(1) DEFAULT NULL,
  `modify_time` bigint(20) DEFAULT NULL,
  `purpose` varchar(255) DEFAULT NULL,
  `sumPrice` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `charger` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK6959747F58A903B` (`charger`) USING BTREE,
  CONSTRAINT `goodsrecord_ibfk_1` FOREIGN KEY (`charger`) REFERENCES `staff` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goodsrecord
-- ----------------------------

-- ----------------------------
-- Table structure for goodsRecord
-- ----------------------------
DROP TABLE IF EXISTS `goodsRecord`;
CREATE TABLE `goodsRecord` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` bigint(20) DEFAULT NULL,
  `IsDeleted` tinyint(1) DEFAULT NULL,
  `modify_time` bigint(20) DEFAULT NULL,
  `purpose` varchar(255) DEFAULT NULL,
  `sumPrice` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `charger` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK6959747F58A903B` (`charger`),
  CONSTRAINT `FK6959747F58A903B` FOREIGN KEY (`charger`) REFERENCES `staff` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goodsRecord
-- ----------------------------

-- ----------------------------
-- Table structure for oilbuy
-- ----------------------------
DROP TABLE IF EXISTS `oilbuy`;
CREATE TABLE `oilbuy` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` bigint(20) DEFAULT NULL,
  `IsDeleted` tinyint(1) DEFAULT NULL,
  `modify_time` bigint(20) DEFAULT NULL,
  `litre` double DEFAULT NULL,
  `price` double DEFAULT NULL,
  `staff_ID` int(11) DEFAULT NULL,
  `vehicle_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKC363AF54174A9ED7` (`staff_ID`) USING BTREE,
  KEY `FKC363AF5492E7D817` (`vehicle_ID`) USING BTREE,
  CONSTRAINT `oilbuy_ibfk_1` FOREIGN KEY (`staff_ID`) REFERENCES `staff` (`ID`),
  CONSTRAINT `oilbuy_ibfk_2` FOREIGN KEY (`vehicle_ID`) REFERENCES `vehicle` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oilbuy
-- ----------------------------

-- ----------------------------
-- Table structure for oilBuy
-- ----------------------------
DROP TABLE IF EXISTS `oilBuy`;
CREATE TABLE `oilBuy` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` bigint(20) DEFAULT NULL,
  `IsDeleted` tinyint(1) DEFAULT NULL,
  `modify_time` bigint(20) DEFAULT NULL,
  `litre` double DEFAULT NULL,
  `price` double DEFAULT NULL,
  `staff_ID` int(11) DEFAULT NULL,
  `vehicle_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKC363AF54174A9ED7` (`staff_ID`),
  KEY `FKC363AF5492E7D817` (`vehicle_ID`),
  CONSTRAINT `FKC363AF5492E7D817` FOREIGN KEY (`vehicle_ID`) REFERENCES `vehicle` (`ID`),
  CONSTRAINT `FKC363AF54174A9ED7` FOREIGN KEY (`staff_ID`) REFERENCES `staff` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oilBuy
-- ----------------------------
INSERT INTO `oilBuy` VALUES ('1', '1461246375873', '0', '1461246375873', '30', '210', '1', '1');

-- ----------------------------
-- Table structure for repair
-- ----------------------------
DROP TABLE IF EXISTS `repair`;
CREATE TABLE `repair` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` bigint(20) DEFAULT NULL,
  `IsDeleted` tinyint(1) DEFAULT NULL,
  `modify_time` bigint(20) DEFAULT NULL,
  `reasonDesc` varchar(255) DEFAULT NULL,
  `goodsRecord_id` int(11) DEFAULT NULL,
  `repairman` int(11) DEFAULT NULL,
  `vehicle_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKC84C1F8DA624C7D7` (`goodsRecord_id`) USING BTREE,
  KEY `FKC84C1F8D9C0A9AAA` (`repairman`) USING BTREE,
  KEY `FKC84C1F8D92E7D817` (`vehicle_id`) USING BTREE,
  CONSTRAINT `repair_ibfk_1` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`ID`),
  CONSTRAINT `repair_ibfk_2` FOREIGN KEY (`repairman`) REFERENCES `staff` (`ID`),
  CONSTRAINT `repair_ibfk_3` FOREIGN KEY (`goodsRecord_id`) REFERENCES `goodsrecord` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repair
-- ----------------------------

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` bigint(20) DEFAULT NULL,
  `IsDeleted` tinyint(1) DEFAULT NULL,
  `modify_time` bigint(20) DEFAULT NULL,
  `dateOfHire` bigint(20) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `idCardNumber` varchar(255) NOT NULL,
  `job` varchar(255) DEFAULT NULL,
  `levelOfEducation` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `politicalGroup` varchar(255) DEFAULT NULL,
  `realName` varchar(255) NOT NULL,
  `salary` int(11) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `typeOfEmployment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES ('1', '1461210956366', '0', '1461554216105', '-56615748184278', '男', '370785198911246514', '经理', '本科', '15335320821', '', 'thankful', '10000', '普通', null);
INSERT INTO `staff` VALUES ('2', '1461211073536', '0', '1461211073536', '1423367873480', '男', '370785198911246514', '经理', '本科', '15335320821', '', 'thankful2', '10000', '普通', null);
INSERT INTO `staff` VALUES ('3', '1461327397991', '0', '1461327397991', '1464005640957', '女', '370785198911146547', '2', '本科', '', '', '葵花', '2', '普通', null);

-- ----------------------------
-- Table structure for transporttask
-- ----------------------------
DROP TABLE IF EXISTS `transporttask`;
CREATE TABLE `transporttask` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` bigint(20) DEFAULT NULL,
  `IsDeleted` tinyint(1) DEFAULT NULL,
  `modify_time` bigint(20) DEFAULT NULL,
  `endDate` bigint(20) DEFAULT NULL,
  `endPlace` varchar(255) NOT NULL,
  `queryId` varchar(255) NOT NULL,
  `rate` double DEFAULT NULL,
  `startDate` bigint(20) DEFAULT NULL,
  `startPlace` varchar(255) NOT NULL,
  `state` int(11) DEFAULT NULL,
  `sumPrice` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `customer` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKA8D8E44EE04FBAEC` (`customer`) USING BTREE,
  CONSTRAINT `transporttask_ibfk_1` FOREIGN KEY (`customer`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of transporttask
-- ----------------------------

-- ----------------------------
-- Table structure for transportTask
-- ----------------------------
DROP TABLE IF EXISTS `transportTask`;
CREATE TABLE `transportTask` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` bigint(20) DEFAULT NULL,
  `IsDeleted` tinyint(1) DEFAULT NULL,
  `modify_time` bigint(20) DEFAULT NULL,
  `endDate` bigint(20) DEFAULT NULL,
  `endPlace` varchar(255) NOT NULL,
  `queryId` varchar(255) NOT NULL,
  `rate` double DEFAULT NULL,
  `startDate` bigint(20) DEFAULT NULL,
  `startPlace` varchar(255) NOT NULL,
  `state` int(11) DEFAULT NULL,
  `sumPrice` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `customer` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKA8D8E44EE04FBAEC` (`customer`),
  CONSTRAINT `FKA8D8E44EE04FBAEC` FOREIGN KEY (`customer`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of transportTask
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` bigint(20) DEFAULT NULL,
  `IsDeleted` tinyint(1) DEFAULT NULL,
  `modify_time` bigint(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1461134768217', '0', '1461249083843', '13-1301-130123-', '1@1.com', 'admin', '123456', '15335320821');

-- ----------------------------
-- Table structure for vehicle
-- ----------------------------
DROP TABLE IF EXISTS `vehicle`;
CREATE TABLE `vehicle` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` bigint(20) DEFAULT NULL,
  `IsDeleted` tinyint(1) DEFAULT NULL,
  `modify_time` bigint(20) DEFAULT NULL,
  `carNumber` varchar(255) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `identity` varchar(255) NOT NULL,
  `driver` int(11) DEFAULT NULL,
  `vehicleType` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK14638F2C7A99F425` (`driver`) USING BTREE,
  KEY `FK14638F2CF62F5449` (`vehicleType`) USING BTREE,
  CONSTRAINT `vehicle_ibfk_1` FOREIGN KEY (`driver`) REFERENCES `staff` (`ID`),
  CONSTRAINT `vehicle_ibfk_2` FOREIGN KEY (`vehicleType`) REFERENCES `vehicletype` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vehicle
-- ----------------------------
INSERT INTO `vehicle` VALUES ('1', '1461210261208', '0', '1461554254773', '法拉利', '黄色', 'a', null, null);

-- ----------------------------
-- Table structure for vehicletype
-- ----------------------------
DROP TABLE IF EXISTS `vehicletype`;
CREATE TABLE `vehicletype` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` bigint(20) DEFAULT NULL,
  `IsDeleted` tinyint(1) DEFAULT NULL,
  `modify_time` bigint(20) DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `height` int(11) DEFAULT NULL,
  `length` int(11) DEFAULT NULL,
  `oilType` int(11) DEFAULT NULL,
  `seat` int(11) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vehicletype
-- ----------------------------

-- ----------------------------
-- Table structure for VehicleType
-- ----------------------------
DROP TABLE IF EXISTS `VehicleType`;
CREATE TABLE `VehicleType` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` bigint(20) DEFAULT NULL,
  `IsDeleted` tinyint(1) DEFAULT NULL,
  `modify_time` bigint(20) DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `height` int(11) DEFAULT NULL,
  `length` int(11) DEFAULT NULL,
  `oilType` int(11) DEFAULT NULL,
  `seat` int(11) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of VehicleType
-- ----------------------------
INSERT INTO `VehicleType` VALUES ('1', '1461246358156', '0', '1461554264251', '20', '卡车', '3', '10', '3', '30', '4');
