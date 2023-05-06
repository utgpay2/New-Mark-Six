/*
 Navicat Premium Data Transfer

 Source Server         : 色站167.179.15.36
 Source Server Type    : MySQL
 Source Server Version : 50735 (5.7.35)
 Source Host           : 167.179.15.36:6501
 Source Schema         : kk_porn

 Target Server Type    : MySQL
 Target Server Version : 50735 (5.7.35)
 File Encoding         : 65001

 Date: 28/03/2023 17:07:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for i18n_info
-- ----------------------------
DROP TABLE IF EXISTS `i18n_info`;
CREATE TABLE `i18n_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `from_of` int(11) NOT NULL DEFAULT 1 COMMENT '所属 0=前台PC，1=后台 2=前台移动端 3=前台错误消息 4=后台错误消息',
  `zh` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL COMMENT '中文',
  `en` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL COMMENT '英语',
  `kh` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL COMMENT '高棉语',
  `th` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL COMMENT '泰语',
  `vi` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL COMMENT '越南语',
  `my` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL COMMENT '马来语',
  `operator` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL COMMENT '操作人',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `fromOf_ZhCn_Index`(`from_of`, `zh`) USING BTREE COMMENT '中文名唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '国际化字典表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of i18n_info
-- ----------------------------
INSERT INTO `i18n_info` VALUES (1, 1, '后台系统', 'Back admin system', 'ប្រព័ន្ធគ្រប់គ្រងខាងក្រោយ', NULL, NULL, NULL, NULL, '2023-03-10 22:52:58', '2023-03-10 22:52:58', NULL, NULL);
INSERT INTO `i18n_info` VALUES (2, 2, '网站维护中', 'Website is under maintenance', 'គេហទំព័រកំពុងស្ថិតនៅក្រោមការថែទាំ', NULL, NULL, NULL, NULL, '2023-03-10 22:52:58', '2023-03-10 22:52:58', NULL, NULL);
INSERT INTO `i18n_info` VALUES (3, 2, '本公司对服务器进行维护，期间相关的网页无法登陆,不便之处敬请谅解', 'We are maintaining the server.  During this period all pages are not able to access, sorry for the inconveniences caused.', 'យើងកំពុងថែរក្សាម៉ាស៊ីនមេ។ ក្នុងអំឡុងពេលនេះទំព័រទាំងអស់មិនអាចចូលដំណើរការបានទេសូមអភ័យទោសចំពោះការរអាក់រអួលដែលបានបង្កឡើង។', NULL, NULL, NULL, NULL, '2023-03-10 22:52:58', '2023-03-10 22:52:58', NULL, NULL);
INSERT INTO `i18n_info` VALUES (4, 0, '女优', 'Actress', 'តារាសម្តែង', NULL, NULL, NULL, NULL, '2023-03-10 22:55:56', '2023-03-10 22:55:56', NULL, NULL);
INSERT INTO `i18n_info` VALUES (5, 3, '未知错误', 'Unknown Error', 'កំហុសមិនស្គាល់', NULL, NULL, NULL, NULL, '2023-03-10 22:58:13', '2023-03-10 22:58:13', NULL, NULL);
INSERT INTO `i18n_info` VALUES (6, 0, '国家', 'Country', 'ជាតិ', NULL, NULL, NULL, NULL, '2023-03-10 23:11:42', '2023-03-10 23:11:42', NULL, NULL);
INSERT INTO `i18n_info` VALUES (7, 0, '字幕类型', 'subtitle type', 'ប្រភេទចំណងជើងរង', NULL, NULL, NULL, NULL, '2023-03-10 23:13:18', '2023-03-10 23:13:18', NULL, NULL);
INSERT INTO `i18n_info` VALUES (8, 0, '影片类型', 'movie type', 'ប្រភេទវីដេអូ', NULL, NULL, NULL, NULL, '2023-03-10 23:14:36', '2023-03-10 23:14:36', NULL, NULL);
INSERT INTO `i18n_info` VALUES (9, 0, '拍摄类型', 'shooting type', 'ប្រភេទបាញ់', NULL, NULL, NULL, NULL, '2023-03-10 23:15:14', '2023-03-10 23:15:18', NULL, NULL);
INSERT INTO `i18n_info` VALUES (10, 0, '付费类型', 'payment type', 'ប្រភេទការទូទាត់', NULL, NULL, NULL, NULL, '2023-03-10 23:15:53', '2023-03-10 23:15:53', NULL, NULL);
INSERT INTO `i18n_info` VALUES (12, 2, '女优', 'Actress', 'តារាសម្តែង', NULL, NULL, NULL, NULL, '2023-03-10 23:22:08', '2023-03-10 23:22:08', NULL, NULL);
INSERT INTO `i18n_info` VALUES (13, 2, '字幕类型', 'subtitle type', 'ប្រភេទចំណងជើងរង', NULL, NULL, NULL, NULL, '2023-03-10 23:22:09', '2023-03-10 23:22:09', NULL, NULL);
INSERT INTO `i18n_info` VALUES (14, 2, '国家', 'Country', 'ជាតិ', NULL, NULL, NULL, NULL, '2023-03-10 23:22:09', '2023-03-10 23:22:09', NULL, NULL);
INSERT INTO `i18n_info` VALUES (15, 2, '影片类型', 'movie type', 'ប្រភេទវីដេអូ', NULL, NULL, NULL, NULL, '2023-03-10 23:22:09', '2023-03-10 23:22:09', NULL, NULL);
INSERT INTO `i18n_info` VALUES (16, 2, '拍摄类型', 'shooting type', 'ប្រភេទបាញ់', NULL, NULL, NULL, NULL, '2023-03-10 23:22:09', '2023-03-10 23:22:09', NULL, NULL);
INSERT INTO `i18n_info` VALUES (17, 2, '付费类型', 'payment type', 'ប្រភេទការទូទាត់', NULL, NULL, NULL, NULL, '2023-03-10 23:28:24', '2023-03-10 23:28:24', NULL, NULL);
INSERT INTO `i18n_info` VALUES (18, 2, '全部', 'All', 'ទាំងអស់។', NULL, NULL, NULL, NULL, '2023-03-15 21:51:51', '2023-03-15 21:51:51', NULL, NULL);

-- ----------------------------
-- Table structure for ip_switch_button
-- ----------------------------
DROP TABLE IF EXISTS `ip_switch_button`;
CREATE TABLE `ip_switch_button`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `whiteip_swithc_button` int(11) NOT NULL DEFAULT 1 COMMENT '白名单开关1打开0关闭',
  `blackip_swithc_button` int(11) NOT NULL DEFAULT 1 COMMENT '黑名单开关1打开0关闭',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'IP控制开关' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ip_switch_button
-- ----------------------------
INSERT INTO `ip_switch_button` VALUES (1, 0, 0);

-- ----------------------------
-- Table structure for kpn_actor
-- ----------------------------
DROP TABLE IF EXISTS `kpn_actor`;
CREATE TABLE `kpn_actor`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name_zh` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '中文名',
  `name_en` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '英文名',
  `name_kh` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '柬文名',
  `sex` tinyint(255) NOT NULL DEFAULT 0 COMMENT '性别 0女 1男',
  `birthday` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生日 年月日',
  `country_zh` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '国籍(中文)',
  `country_en` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '国籍(英文)',
  `country_kh` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '国籍(柬文)',
  `height` decimal(5, 2) NULL DEFAULT NULL COMMENT '身高',
  `bwh` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '三围',
  `weight` decimal(5, 2) NULL DEFAULT NULL COMMENT '体重(KG)',
  `cup` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '罩杯',
  `avatar_url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像url',
  `portrait_url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '写真照url',
  `interest_zh` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '兴趣(中文)',
  `interest_en` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '兴趣(英文)',
  `interest_kh` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '兴趣(柬文)',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简介',
  `favorites` bigint(20) NOT NULL DEFAULT 0 COMMENT '总收藏量(所有站点只和)',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `actor_id_index`(`id`) USING BTREE COMMENT '主键索引',
  INDEX `actor_createtime`(`create_time`, `name_zh`, `name_en`, `name_kh`) USING BTREE,
  FULLTEXT INDEX `actor_name_fulltext`(`name_zh`, `name_en`, `name_kh`)
) ENGINE = InnoDB AUTO_INCREMENT = 97 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '演员列表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kpn_actor
-- ----------------------------
INSERT INTO `kpn_actor` VALUES (0, '未知', 'unknown', 'unknown', 0, 'unknown', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '未知', 0, '2023-02-01 23:07:24', '2023-03-07 17:23:33', 'year', 'year');
INSERT INTO `kpn_actor` VALUES (1, '春天', 'spring', 'spring', 0, '1990-01-01', '日本', 'Japan', 'ជប៉ុន', 176.00, '88-88-88', 88.00, NULL, 'http://www.baidu.com', NULL, '吃饭', NULL, NULL, NULL, 0, '2023-02-03 23:06:37', '2023-03-07 17:23:33', 'year', 'year');
INSERT INTO `kpn_actor` VALUES (2, '夏天', 'summer', 'summer', 0, '1992-02-02', '日本', 'Japan', 'ជប៉ុន', 175.00, '90-90-90', 70.00, NULL, 'http://www.sumer.com', NULL, '打游戏', NULL, NULL, NULL, 2, '2023-02-06 20:06:47', '2023-03-07 17:23:33', 'year', 'year');
INSERT INTO `kpn_actor` VALUES (4, '新山枫', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:14:24', '2023-03-21 21:06:45', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (5, '星野千纱', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:20', '2023-03-21 21:06:41', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (6, '水木里沙', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:20', '2023-03-21 21:06:39', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (7, '岛崎结衣', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:21', '2023-03-21 21:06:40', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (8, '星优奈', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:21', '2023-03-21 21:06:38', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (9, '川村真矢', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:21', '2023-03-21 21:06:37', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (10, '浅井奈央', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:21', '2023-03-21 21:06:36', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (11, 'misslexa', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:21', '2023-03-21 21:06:35', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (12, '月野霞', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:21', '2023-03-21 21:06:34', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (13, '北岛玲', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:21', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (14, '爱宝铃', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:21', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (15, '结川悠', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:21', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (16, 'Morgpie', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:21', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (17, '舞衣', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:21', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (18, '甲斐美晴', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:21', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (19, '爱梨南', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:21', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (20, '百合川纱良', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:21', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (21, '今井菜那', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:21', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (22, '渚', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:21', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (23, '三尾惠', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:21', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (24, '阳菜', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:21', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (25, '宍户里帆', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, 'kkporn/actor/25.jpg', 'kkporn/actor/25_photo.jpg', '趣味は読書・映画、料理、ギター、フランス語', NULL, NULL, NULL, 0, '2023-03-07 16:20:22', '2023-03-11 20:10:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (26, '舞坂仁美', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:22', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (27, '由纪诚', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:22', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (28, '黑羽美里', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:22', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (29, '美月瑠奈', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:22', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (30, '时田亞美', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:22', '2023-03-25 14:13:56', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (31, '西冈奈央', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:22', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (32, '美神彩', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:22', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (33, '铃村伊吕波', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:22', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (34, '一之濑铃', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:22', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (35, '香澄莉子', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:22', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (36, '水泽莉乃', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:22', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (37, '户田麻美', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:22', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (38, '岸谷灯', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:22', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (39, '杉浦花音', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:22', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (40, '泷川惠里菜', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:22', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (41, '心爱', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:22', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (42, '美波桃', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:23', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (43, '铃羽美羽', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:23', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (44, '北山柑菜', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:23', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (45, '安西美纱', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:23', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (46, '小高里保', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:23', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (47, '稻场流花', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:23', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (48, '三浦凛', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:23', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (49, '杏', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:23', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (50, '广濑奈奈美', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:23', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (51, '原田志保', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:23', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (52, '热辣主播', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:23', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (53, '云母', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:23', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (54, '音羽蕾恩', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:23', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (55, '初音露莉亚', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:23', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (56, '前原沙良', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:23', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (57, '樱木桃香', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:23', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (58, '藤井夏树', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:23', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (59, '七海', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:24', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (60, '清水理纱', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:24', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (61, '井口惠美', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:24', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (62, '偷情王哥', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:24', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (63, '白濑飞鸟', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:24', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (64, '星崎雏', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:24', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (65, '松田安娜', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:24', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (66, '铃木里绪奈', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:24', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (67, '羽月希', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:24', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (68, '小仓七海', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, 'kkporn/actor/68.jpg', 'kkporn/actor/68_photo.jpg', NULL, NULL, NULL, NULL, 1, '2023-03-07 16:20:24', '2023-03-24 14:48:05', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (69, '源美衣奈', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:24', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (70, '郑艾莉', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2023-03-07 16:20:24', '2023-03-27 19:39:34', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (71, '秋元里奈', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:24', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (72, '本山茉莉', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:24', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (73, '井上绫子', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:24', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (74, '白川沙耶', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:24', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (75, '真野优莉亚', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:25', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (76, '杉崎绘里奈', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:25', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (77, '北原真奈', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:25', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (78, '望月绚香', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:25', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (79, '千叶可怜', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 16:20:25', '2023-03-07 17:23:33', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (80, '上原正树', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 22:05:58', '2023-03-07 22:06:05', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (81, '京野七夏', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 22:06:44', '2023-03-07 22:06:48', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (82, '木村美羽', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 22:08:25', '2023-03-07 22:08:29', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (83, '椎名柚子', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 22:09:06', '2023-03-08 16:16:49', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (84, '椎名美优', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 22:09:32', '2023-03-08 16:16:48', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (85, '樱井梨花', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 22:09:55', '2023-03-08 16:16:48', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (86, '泷川索菲亚', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 22:10:18', '2023-03-08 16:16:49', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (87, '渡边美羽', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 22:10:39', '2023-03-08 16:16:48', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (88, '爱乃娜美', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 22:11:16', '2023-03-08 16:16:48', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (89, '田中美春', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 22:12:09', '2023-03-08 16:16:48', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (90, '真木今日子', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 22:12:34', '2023-03-08 16:16:48', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (91, '绫森市香', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 22:12:58', '2023-03-08 16:16:48', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (92, '羽奈美鈴', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 22:13:19', '2023-03-08 16:16:48', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (93, '荒木梨奈', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 22:13:40', '2023-03-08 16:16:48', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (94, '西川千寻', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 22:14:01', '2023-03-08 16:16:48', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (95, '长谷川美裸', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 22:15:17', '2023-03-08 16:16:48', NULL, NULL);
INSERT INTO `kpn_actor` VALUES (96, '露娜', NULL, NULL, 0, '1999-01-01', '日本', 'Japan', 'ជប៉ុន', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-03-07 22:15:48', '2023-03-08 16:16:48', NULL, NULL);

-- ----------------------------
-- Table structure for kpn_black_ip
-- ----------------------------
DROP TABLE IF EXISTS `kpn_black_ip`;
CREATE TABLE `kpn_black_ip`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ip_section` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会员黑名单ip段',
  `remark` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `site_id` bigint(11) NULL DEFAULT NULL COMMENT '站点id',
  `site_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点编码',
  `site_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kpn_black_ip
-- ----------------------------
INSERT INTO `kpn_black_ip` VALUES (2, '192.168.1.49', '测试2', '2023-03-02 21:37:31', '2023-03-03 12:50:04', '测试2', 'ces', NULL, NULL, NULL);
INSERT INTO `kpn_black_ip` VALUES (3, '192.168.1.49', '测试2', '2023-03-03 12:49:40', '2023-03-03 12:49:40', '测试2', 'ces', NULL, NULL, NULL);
INSERT INTO `kpn_black_ip` VALUES (4, '192.168.1.49', '测试2', '2023-03-03 13:56:49', '2023-03-03 13:56:49', '测试2', 'ces', NULL, NULL, NULL);
INSERT INTO `kpn_black_ip` VALUES (5, '192.168.1.49', '', '2023-03-04 19:54:09', '2023-03-04 19:54:09', '', '', 0, '', '');
INSERT INTO `kpn_black_ip` VALUES (6, '192.168.1.49', '', '2023-03-04 18:59:03', '2023-03-04 18:59:03', '', '', 0, '', '');

-- ----------------------------
-- Table structure for kpn_line
-- ----------------------------
DROP TABLE IF EXISTS `kpn_line`;
CREATE TABLE `kpn_line`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `line` tinyint(3) NULL DEFAULT NULL COMMENT '1:线路一,2:线路二,3:线路三',
  `domain` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '域名',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态 0:禁用,1:启用',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '线路配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kpn_line
-- ----------------------------
INSERT INTO `kpn_line` VALUES (1, 1, 'http://movie.kk88824.net', 1, '2023-02-04 21:51:08', '2023-03-08 22:20:15', 'year', 'year');
INSERT INTO `kpn_line` VALUES (3, 2, 'http://movie.kk88824.net', 1, '2023-02-04 21:52:01', '2023-03-08 22:20:15', 'year', 'year');
INSERT INTO `kpn_line` VALUES (4, 3, 'http://movie.kk88824.net', 1, '2023-02-04 21:58:17', '2023-03-08 22:20:15', 'year', 'year');

-- ----------------------------
-- Table structure for kpn_money_log
-- ----------------------------
DROP TABLE IF EXISTS `kpn_money_log`;
CREATE TABLE `kpn_money_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日期',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `order_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内部订单号',
  `order_type` tinyint(8) NOT NULL COMMENT '账变类型',
  `date` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '日期',
  `before_money` decimal(20, 2) NULL DEFAULT NULL COMMENT '账变前金额',
  `money` decimal(20, 2) NULL DEFAULT NULL COMMENT '账变金额',
  `after_money` decimal(20, 2) NULL DEFAULT NULL COMMENT '账变后金额',
  `product_id` bigint(20) NULL DEFAULT NULL COMMENT '产品id',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `currency` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'K币' COMMENT '币种',
  `transfer_status` tinyint(8) NOT NULL DEFAULT 1 COMMENT '0失败,1成功',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_userid_date_ordertype`(`user_id`, `date`, `order_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'K币账变记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of kpn_money_log
-- ----------------------------
INSERT INTO `kpn_money_log` VALUES (29, 2, 'kk-porn01_year01', '96018460592671693076', 4, '2023-02-24', 0.00, 10.00, 10.00, NULL, '推广会员id:94,获取奖励kb: 10.00', 'kb', 1, '2023-02-24 00:05:49', '2023-02-27 13:33:22', NULL, NULL);
INSERT INTO `kpn_money_log` VALUES (31, 100, 'kk-porn01_year002', '29629296058903649248', 3, '2023-02-24', 0.00, 14.00, 14.00, NULL, '填写邀请码:abcedf,获取奖励kb:14.00', 'kb', 1, '2023-02-24 00:10:15', '2023-02-27 13:33:21', NULL, NULL);
INSERT INTO `kpn_money_log` VALUES (32, 2, 'kk-porn01_year01', '37393930959102978842', 4, '2023-02-24', 10.00, 10.00, 20.00, NULL, '推广会员id:100,获取奖励kb: 10.00', 'kb', 1, '2023-02-24 00:10:16', '2023-02-27 13:33:21', NULL, NULL);
INSERT INTO `kpn_money_log` VALUES (33, 102, 'kk-porn01_year004', '71791667706650577318', 3, '2023-02-24', 0.00, 14.00, 14.00, NULL, '填写邀请码:DRlLPf,获取奖励kb:14.00', 'kb', 1, '2023-02-24 00:13:53', '2023-02-27 13:33:21', NULL, NULL);
INSERT INTO `kpn_money_log` VALUES (35, 100, 'kk-porn01_year002', '50438681270384701847', 2, '2023-02-24', 14.00, 30.00, 44.00, NULL, '签到奖励kb:30.00', 'kb', 1, '2023-02-24 00:15:34', '2023-02-27 13:33:21', NULL, NULL);
INSERT INTO `kpn_money_log` VALUES (36, 103, 'kk-porn01_year005', '39322399645361621923', 3, '2023-02-24', 0.00, 14.00, 14.00, NULL, '填写邀请码:drBfmW,获取奖励kb:14.00', 'kb', 1, '2023-02-24 00:22:31', '2023-02-27 13:33:21', NULL, NULL);
INSERT INTO `kpn_money_log` VALUES (37, 100, 'kk-porn01_year002', '82964148493138543062', 4, '2023-02-24', 44.00, 10.00, 54.00, NULL, '推广会员id:103,获取奖励kb: 10.00', 'kb', 1, '2023-02-24 00:22:32', '2023-02-27 13:33:21', NULL, NULL);
INSERT INTO `kpn_money_log` VALUES (38, 94, 'kk-porn01_year001', '31500013868772788181', 1, '2023-02-24', 1000000.00, -100000.00, 900000.00, 2, '购买vip产品id:2,消费K币: -100000.00', 'kb', 1, '2023-02-24 17:21:21', '2023-02-27 14:18:54', NULL, NULL);
INSERT INTO `kpn_money_log` VALUES (39, 104, 'kk-porn01_year006', '03491670314906709370', 3, '2023-02-24', 0.00, 14.00, 14.00, NULL, '填写邀请码:DRlLPf,获取奖励K币:14.00', 'kb', 1, '2023-02-24 17:25:43', '2023-02-27 13:33:21', NULL, NULL);
INSERT INTO `kpn_money_log` VALUES (40, 94, 'kk-porn01_year001', '85095726242282310456', 4, '2023-02-24', 900000.00, 10.00, 900010.00, NULL, '推广会员id:104,获取奖励K币: 10.00', 'kb', 1, '2023-02-24 17:25:43', '2023-02-27 13:33:21', NULL, NULL);
INSERT INTO `kpn_money_log` VALUES (41, 94, 'kk-porn01_year001', '94932712660847917786', 1, '2023-02-24', 900010.00, -200000.00, 700010.00, 3, '购买vip产品id:3,消费K币: -200000.00', 'kb', 1, '2023-02-24 17:28:26', '2023-02-27 14:18:54', NULL, NULL);
INSERT INTO `kpn_money_log` VALUES (42, 111, 'kk-porn01_year008', '03233133312480466310', 3, '2023-02-28', 0.00, 14.00, 14.00, NULL, '填写邀请码:DRlLPf,获取奖励K币:14.00', 'K币', 1, '2023-02-28 15:41:33', '2023-02-28 15:41:33', NULL, NULL);
INSERT INTO `kpn_money_log` VALUES (43, 94, 'kk-porn01_year001', '95731246796970039815', 4, '2023-02-28', 700010.00, 10.00, 700020.00, NULL, '推广会员id:111,获取奖励K币: 10.00', 'K币', 1, '2023-02-28 15:41:35', '2023-02-28 15:41:35', NULL, NULL);
INSERT INTO `kpn_money_log` VALUES (44, 2, 'kk-porn01_year01', '10100737363783395379', 1, '2023-02-28', 200000.00, -200000.00, 0.00, 3, '购买vip产品id:3,消费K币: -200000.00', 'K币', 1, '2023-02-28 16:07:06', '2023-02-28 16:07:06', NULL, NULL);
INSERT INTO `kpn_money_log` VALUES (45, 2, 'kk-porn01_year01', '73782562170180075237', 1, '2023-02-28', 1000000.00, -200000.00, 800000.00, 3, '购买vip产品id:3,消费K币: -200000.00', 'K币', 1, '2023-02-28 16:16:18', '2023-02-28 16:16:18', NULL, NULL);
INSERT INTO `kpn_money_log` VALUES (46, 2, 'kk-porn01_year01', '43415806607646439515', 1, '2023-02-28', 800000.00, -200000.00, 600000.00, 3, '购买vip产品id:3,消费K币: -200000.00', 'K币', 1, '2023-02-28 16:18:09', '2023-02-28 16:18:09', NULL, NULL);
INSERT INTO `kpn_money_log` VALUES (47, 2, 'kk-porn01_year01', '25932591216123268234', 1, '2023-02-28', 600000.00, -200000.00, 400000.00, 3, '购买vip产品id:3,消费K币: -200000.00', 'K币', 1, '2023-02-28 16:18:41', '2023-02-28 16:18:41', NULL, NULL);
INSERT INTO `kpn_money_log` VALUES (48, 140, 'kk-porn01_qwe11233', '83369619843716697729', 3, '2023-03-25', 0.00, 14.00, 14.00, NULL, '填写邀请码:xOusz6,获取奖励K币:14.00', 'K币', 1, '2023-03-25 17:49:45', '2023-03-25 17:49:45', NULL, NULL);
INSERT INTO `kpn_money_log` VALUES (49, 136, 'kk-porn01_qwe112233', '09746949731755548987', 4, '2023-03-25', 0.00, 10.00, 10.00, NULL, '推广会员id:140,获取奖励K币: 10.00', 'K币', 1, '2023-03-25 17:49:45', '2023-03-25 17:49:45', NULL, NULL);
INSERT INTO `kpn_money_log` VALUES (50, 136, 'kk-porn01_qwe112233', '57100317156115757154', 2, '2023-03-25', 10.00, 30.00, 40.00, NULL, '签到奖励kb:30.00', 'K币', 1, '2023-03-25 20:49:44', '2023-03-25 20:49:44', NULL, NULL);
INSERT INTO `kpn_money_log` VALUES (51, 141, 'kk-porn01_qwe1122333', '63251909602396065585', 2, '2023-03-26', 0.00, 30.00, 30.00, NULL, '签到奖励kb:30.00', 'K币', 1, '2023-03-26 17:02:55', '2023-03-26 17:02:55', NULL, NULL);
INSERT INTO `kpn_money_log` VALUES (52, 142, 'kk-porn01_chezai01', '77930928116444226887', 2, '2023-03-26', 0.00, 30.00, 30.00, NULL, '签到奖励kb:30.00', 'K币', 1, '2023-03-26 17:11:28', '2023-03-26 17:11:28', NULL, NULL);
INSERT INTO `kpn_money_log` VALUES (53, 136, 'kk-porn01_qwe112233', '56206558925175784110', 2, '2023-03-27', 40.00, 50.00, 90.00, NULL, '签到奖励kb:50.00', 'K币', 1, '2023-03-27 12:25:16', '2023-03-27 12:25:16', NULL, NULL);
INSERT INTO `kpn_money_log` VALUES (54, 143, 'kk-porn01_qwe112333', '22029636878933994083', 3, '2023-03-27', 0.00, 14.00, 14.00, NULL, '填写邀请码:xOusz6,获取奖励K币:14.00', 'K币', 1, '2023-03-27 20:07:59', '2023-03-27 20:07:59', NULL, NULL);
INSERT INTO `kpn_money_log` VALUES (55, 136, 'kk-porn01_qwe112233', '02482653592643417323', 4, '2023-03-27', 90.00, 10.00, 100.00, NULL, '推广会员id:143,获取奖励K币: 10.00', 'K币', 1, '2023-03-27 20:07:59', '2023-03-27 20:07:59', NULL, NULL);

-- ----------------------------
-- Table structure for kpn_movie
-- ----------------------------
DROP TABLE IF EXISTS `kpn_movie`;
CREATE TABLE `kpn_movie`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '唯一编码',
  `url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '播放地址',
  `cover_url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '封页图地址',
  `name_zh` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '中文名称',
  `name_en` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '英文名称',
  `name_kh` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '柬文名称',
  `country` tinyint(2) NULL DEFAULT NULL COMMENT '影片所属国家 0:日本,1:中国大陆,2:中国台湾,3:韩国,4:欧美,5:东南亚,6:其他地区',
  `type` tinyint(2) NOT NULL DEFAULT 0 COMMENT '影片类型 0无码 1有码',
  `shooting_type` tinyint(2) NOT NULL DEFAULT 0 COMMENT '拍摄性质 0:专业拍摄,1:偷拍,2:自拍,3:其他',
  `subtitle_type` tinyint(2) NOT NULL DEFAULT 0 COMMENT '字幕类型 0:无字幕,1:中文,2:英文,3:中英,4:其他',
  `duration` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '影片时长(HH:mm:ss 如00:10:02)',
  `serial_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '番号',
  `company` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '制作商',
  `publish_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发行时间(处理时间)',
  `actor_id` bigint(255) NOT NULL DEFAULT 0 COMMENT '演员id',
  `actor_name_zh` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '演员中文名',
  `actor_name_en` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '演员英文名',
  `actor_name_kh` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '演员柬文名',
  `status` tinyint(255) NOT NULL DEFAULT 0 COMMENT '影片状态 1:上架,2:下架',
  `handle_status` tinyint(255) NOT NULL DEFAULT 0 COMMENT '影片处理状态 0未处理 , 1处理完成',
  `delete_status` tinyint(255) NOT NULL DEFAULT 0 COMMENT '删除状态 0:未删除,1:已删除',
  `remark` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简介',
  `vv` bigint(255) NOT NULL DEFAULT 0 COMMENT '影片播放量(所有站点播放量之和)',
  `favorites` bigint(255) NOT NULL DEFAULT 0 COMMENT '影片收藏量(所有站点收藏量之和)',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_code`(`code`) USING BTREE COMMENT '唯一编码',
  UNIQUE INDEX `uni_namezh`(`name_zh`) USING BTREE COMMENT '影片名唯一',
  UNIQUE INDEX `uni_nameen`(`name_en`) USING BTREE COMMENT '影片名唯一',
  UNIQUE INDEX `uni_namekh`(`name_kh`) USING BTREE COMMENT '影片名唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 101233 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kpn_movie
-- ----------------------------
INSERT INTO `kpn_movie` VALUES (100000, 'nVjse4zW', '/nVjse4zW/index.m3u8', '/nVjse4zW/1.jpg', '淫母 3', '淫母 3-en', '淫母 3-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, 'unknown', 'unknown', NULL, 1, 1, 0, '日本动画', 137, 1, '2023-03-07 15:40:31', '2023-03-27 21:35:36', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100001, 'sMEInRSU', '/sMEInRSU/index.m3u8', '/sMEInRSU/1.jpg', '双胞胎的母性本能 1', '双胞胎的母性本能 1-en', '双胞胎的母性本能 1-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, 'unknown', 'unknown', NULL, 1, 1, 0, '日本动画', 22, 0, '2023-03-07 15:40:31', '2023-03-27 21:57:58', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100002, 'nEG5TBOQ', '/nEG5TBOQ/index.m3u8', '/nEG5TBOQ/1.jpg', '集团痴汉电车 1', '集团痴汉电车 1-en', '集团痴汉电车 1-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, 'unknown', 'unknown', NULL, 1, 1, 0, '日本动画', 35, 1, '2023-03-07 15:40:31', '2023-03-28 15:26:55', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100003, '2rbe3TNe', '/2rbe3TNe/index.m3u8', '/2rbe3TNe/1.jpg', '大失禁海倫娜 2', '大失禁海倫娜 2-en', '大失禁海倫娜 2-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, 'unknown', 'unknown', NULL, 1, 1, 0, '日本动画', 142, 1, '2023-03-07 15:40:31', '2023-03-28 15:31:55', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100004, '989ncNPQ', '/989ncNPQ/index.m3u8', '/989ncNPQ/1.jpg', '双胞胎的母性本能 2', '双胞胎的母性本能 2-en', '双胞胎的母性本能 2-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, 'unknown', 'unknown', NULL, 1, 1, 0, '日本动画', 219, 1, '2023-03-07 15:40:31', '2023-03-28 15:37:24', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100005, 'Y6gJX0DZ', '/Y6gJX0DZ/index.m3u8', '/Y6gJX0DZ/1.jpg', '人妻×人妻 1', '人妻×人妻 1-en', '人妻×人妻 1-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, 'unknown', 'unknown', NULL, 1, 1, 0, '日本动画', 225, 1, '2023-03-07 15:40:31', '2023-03-27 21:49:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100006, 'qdXQukrJ', '/qdXQukrJ/index.m3u8', '/qdXQukrJ/1.jpg', '毛茸茸大正电动娘ARISA 1', '毛茸茸大正电动娘ARISA 1-en', '毛茸茸大正电动娘ARISA 1-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, 'unknown', 'unknown', NULL, 1, 1, 0, '日本动画', 1, 0, '2023-03-07 15:40:31', '2023-03-16 20:54:56', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100007, 'ssqs08Pg', '/ssqs08Pg/index.m3u8', '/ssqs08Pg/1.jpg', '魔法少女梅露露 1', '魔法少女梅露露 1-en', '魔法少女梅露露 1-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, 'unknown', 'unknown', NULL, 1, 1, 0, '日本动画', 1, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100008, 'rwutFZJe', '/rwutFZJe/index.m3u8', '/rwutFZJe/1.jpg', '淫母 2', '淫母 2-en', '淫母 2-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, 'unknown', 'unknown', NULL, 1, 1, 0, '日本动画', 8, 0, '2023-03-07 15:40:31', '2023-03-23 22:08:30', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100009, 'uS6yHIll', '/uS6yHIll/index.m3u8', '/uS6yHIll/1.jpg', '安魂曲 Requiem 2', '安魂曲 Requiem 2-en', '安魂曲 Requiem 2-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, 'unknown', 'unknown', NULL, 1, 1, 0, '日本动画', 1, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100010, 'GRbaGWgT', '/GRbaGWgT/index.m3u8', '/GRbaGWgT/1.jpg', '集团痴汉电车 2', '集团痴汉电车 2-en', '集团痴汉电车 2-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, 'unknown', 'unknown', NULL, 1, 1, 0, '日本动画', 1, 0, '2023-03-07 15:40:31', '2023-03-23 14:49:57', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100011, 'nE8jqIi5', '/nE8jqIi5/index.m3u8', '/nE8jqIi5/1.jpg', '大失禁海倫娜 1', '大失禁海倫娜 1-en', '大失禁海倫娜 1-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, 'unknown', 'unknown', NULL, 1, 1, 0, '日本动画', 1, 0, '2023-03-07 15:40:31', '2023-03-13 20:57:18', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100012, '8G2srVsN', '/8G2srVsN/index.m3u8', '/8G2srVsN/1.jpg', '毛茸茸大正电动娘ARISA 2', '毛茸茸大正电动娘ARISA 2-en', '毛茸茸大正电动娘ARISA 2-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, 'unknown', 'unknown', NULL, 1, 1, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100013, 'UGUXmBS3', '/UGUXmBS3/index.m3u8', '/UGUXmBS3/1.jpg', '安魂曲 Requiem 1', '安魂曲 Requiem 1-en', '安魂曲 Requiem 1-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, 'unknown', 'unknown', NULL, 1, 1, 0, '日本动画', 3, 0, '2023-03-07 15:40:31', '2023-03-27 19:50:51', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100014, 'OlM5fkCt', '/OlM5fkCt/index.m3u8', '/OlM5fkCt/1.jpg', '淫娘 3', '淫娘 3-en', '淫娘 3-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, 'unknown', 'unknown', NULL, 1, 1, 0, '日本动画', 1, 0, '2023-03-07 15:40:31', '2023-03-24 14:09:05', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100015, 'i1fLpNn5', '/i1fLpNn5/index.m3u8', '/i1fLpNn5/1.jpg', '满淫电车 调书 3', '满淫电车 调书 3-en', '满淫电车 调书 3-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, 'unknown', 'unknown', NULL, 1, 1, 0, '日本动画', 62, 1, '2023-03-07 15:40:31', '2023-03-28 15:31:57', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100016, 'uGNP5Puw', '/uGNP5Puw/index.m3u8', '/uGNP5Puw/1.jpg', '魔法少女梅露露 2', '魔法少女梅露露 2-en', '魔法少女梅露露 2-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, 'unknown', 'unknown', NULL, 1, 1, 0, '日本动画', 2, 1, '2023-03-07 15:40:31', '2023-03-27 13:57:42', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100017, 'YlqY5IxC', '/YlqY5IxC/index.m3u8', '/YlqY5IxC/1.jpg', '人妻×人妻 2', '人妻×人妻 2-en', '人妻×人妻 2-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, 'unknown', 'unknown', NULL, 1, 1, 0, '日本动画', 11, 1, '2023-03-07 15:40:31', '2023-03-28 16:36:58', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100018, 'iwGh27BE', '/iwGh27BE/index.m3u8', '/iwGh27BE/1.jpg', '满淫电车 调书 2', '满淫电车 调书 2-en', '满淫电车 调书 2-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, 'unknown', 'unknown', NULL, 1, 1, 0, '日本动画', 6, 1, '2023-03-07 15:40:31', '2023-03-27 14:41:22', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100019, 'r2Ojbcve', '/r2Ojbcve/index.m3u8', '/r2Ojbcve/1.jpg', '满淫电车 调书 1', '满淫电车 调书 1-en', '满淫电车 调书 1-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, 'unknown', 'unknown', NULL, 1, 1, 0, '日本动画', 94, 1, '2023-03-07 15:40:31', '2023-03-28 16:15:32', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100020, 'lvJkNZIb', '/lvJkNZIb/index.m3u8', '/lvJkNZIb/1.jpg', '淫娘 2', '淫娘 2-en', '淫娘 2-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, 'unknown', 'unknown', NULL, 1, 1, 0, '日本动画', 2, 1, '2023-03-07 15:40:31', '2023-03-27 13:57:56', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100021, 'Fj8bmsYk', '/Fj8bmsYk/index.m3u8', '/Fj8bmsYk/1.jpg', '轮奸学园 1', '轮奸学园 1-en', '轮奸学园 1-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, 'unknown', 'unknown', NULL, 1, 1, 0, '日本动画', 4, 0, '2023-03-07 15:40:31', '2023-03-16 21:46:16', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100022, 'qRxYRmca', '/qRxYRmca/index.m3u8', '/qRxYRmca/1.jpg', '淫娘 1', '淫娘 1-en', '淫娘 1-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, 'unknown', 'unknown', NULL, 1, 1, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100023, 'PcPFaCS8', '/PcPFaCS8/index.m3u8', '/PcPFaCS8/1.jpg', '淫母 1', '淫母 1-en', '淫母 1-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, 'unknown', 'unknown', NULL, 1, 1, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100024, 'SKiJLd6N', '/SKiJLd6N/index.m3u8', '/SKiJLd6N/1.jpg', '巨乳兔女郎满足客人的所有性癖要求 MIAA-580', '巨乳兔女郎满足客人的所有性癖要求 MIAA-580-en', '巨乳兔女郎满足客人的所有性癖要求 MIAA-580-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 42, '美波桃', NULL, NULL, 1, 1, 0, '美波桃', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100025, '5JFixdNW', '/5JFixdNW/index.m3u8', '/5JFixdNW/1.jpg', '与美女大学生女友同居的性福生活 SSIS-401', '与美女大学生女友同居的性福生活 SSIS-401-en', '与美女大学生女友同居的性福生活 SSIS-401-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 68, '小仓七海', NULL, NULL, 1, 1, 0, '小仓七海', 5, 0, '2023-03-07 15:40:31', '2023-03-24 12:42:33', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100026, 'uWq1Vcpv', '/uWq1Vcpv/index.m3u8', '/uWq1Vcpv/1.jpg', '随着肉棒的剧烈抽插两只雪白的大奶子上下晃动 BOBB-330', '随着肉棒的剧烈抽插两只雪白的大奶子上下晃动 BOBB-330-en', '随着肉棒的剧烈抽插两只雪白的大奶子上下晃动 BOBB-330-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 42, '美波桃', NULL, NULL, 1, 1, 0, '美波桃', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100027, 'KjQOmYnn', '/KjQOmYnn/index.m3u8', '/KjQOmYnn/1.jpg', '两根巨屌连续抽插骚穴爽的骚货尖叫不止 SSIS-311', '两根巨屌连续抽插骚穴爽的骚货尖叫不止 SSIS-311-en', '两根巨屌连续抽插骚穴爽的骚货尖叫不止 SSIS-311-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 35, '香澄莉子', NULL, NULL, 1, 1, 0, '香澄莉子', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100028, 'SxjvHRih', '/SxjvHRih/index.m3u8', '/SxjvHRih/1.jpg', '学生妹子翘课与大叔宾馆激情干炮 JKSR-518', '学生妹子翘课与大叔宾馆激情干炮 JKSR-518-en', '学生妹子翘课与大叔宾馆激情干炮 JKSR-518-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 1, 1, 0, '白濑飞鸟', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100029, 'emLiiYlx', '/emLiiYlx/index.m3u8', '/emLiiYlx/1.jpg', '美女出差被好色上司灌醉后强奸 SSIS-363', '美女出差被好色上司灌醉后强奸 SSIS-363-en', '美女出差被好色上司灌醉后强奸 SSIS-363-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 35, '香澄莉子', NULL, NULL, 1, 1, 0, '香澄莉子', 1, 0, '2023-03-07 15:40:31', '2023-03-23 20:23:31', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100030, 'sScKf88K', '/sScKf88K/index.m3u8', '/sScKf88K/1.jpg', '强行进去女友妹妹的嫩穴把她变成专属性工具 SSIS-348', '强行进去女友妹妹的嫩穴把她变成专属性工具 SSIS-348-en', '强行进去女友妹妹的嫩穴把她变成专属性工具 SSIS-348-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 68, '小仓七海', NULL, NULL, 1, 1, 0, '小仓七海', 5, 0, '2023-03-07 15:40:31', '2023-03-24 12:42:22', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100031, 'e3lh8Waq', '/e3lh8Waq/index.m3u8', '/e3lh8Waq/1.jpg', '小恶魔外遇专家调教M男爽翻天 HMN-100', '小恶魔外遇专家调教M男爽翻天 HMN-100-en', '小恶魔外遇专家调教M男爽翻天 HMN-100-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 1, 1, 0, '白濑飞鸟', 38, 0, '2023-03-07 15:40:31', '2023-03-27 22:04:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100032, 'oQsBLvcg', '/oQsBLvcg/index.m3u8', '/oQsBLvcg/1.jpg', '当着男友的面被陌生肉棒爆操骚穴 MKON-066', '当着男友的面被陌生肉棒爆操骚穴 MKON-066-en', '当着男友的面被陌生肉棒爆操骚穴 MKON-066-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 1, 1, 0, '白濑飞鸟', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100033, 'qPnEafF4', '/qPnEafF4/index.m3u8', '/qPnEafF4/1.jpg', '第一次玩3P就被干到敏感肉体高潮不断 SSIS-255', '第一次玩3P就被干到敏感肉体高潮不断 SSIS-255-en', '第一次玩3P就被干到敏感肉体高潮不断 SSIS-255-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 35, '香澄莉子', NULL, NULL, 1, 1, 0, '香澄莉子', 1, 0, '2023-03-07 15:40:31', '2023-03-16 21:18:46', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100034, 'zEDDQPtP', '/zEDDQPtP/index.m3u8', '/zEDDQPtP/1.jpg', '眼睁睁看着自己的女友被壮汉强奸 SSIS-389', '眼睁睁看着自己的女友被壮汉强奸 SSIS-389-en', '眼睁睁看着自己的女友被壮汉强奸 SSIS-389-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 35, '香澄莉子', NULL, NULL, 1, 1, 0, '香澄莉子', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100035, 'GLdnSUP4', '/GLdnSUP4/index.m3u8', '/GLdnSUP4/1.jpg', '美少女被变态叔叔调教成性奴隶 MUDR-170', '美少女被变态叔叔调教成性奴隶 MUDR-170-en', '美少女被变态叔叔调教成性奴隶 MUDR-170-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 1, 1, 0, '白濑飞鸟', 1, 0, '2023-03-07 15:40:31', '2023-03-25 13:27:35', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100036, 'OGTC3V5d', '/OGTC3V5d/index.m3u8', '/OGTC3V5d/1.jpg', '泳装美少女被蒙面男的巨根操到瘫软在地 OKS-118', '泳装美少女被蒙面男的巨根操到瘫软在地 OKS-118-en', '泳装美少女被蒙面男的巨根操到瘫软在地 OKS-118-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 1, 1, 0, '白濑飞鸟', 1, 0, '2023-03-07 15:40:31', '2023-03-25 19:24:01', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100037, 'CkbUrcfQ', '/CkbUrcfQ/index.m3u8', '/CkbUrcfQ/1.jpg', '游泳部的巨乳老师被我的肉棒抽插成了淫荡的性宠物 MVSD-489', '游泳部的巨乳老师被我的肉棒抽插成了淫荡的性宠物 MVSD-489-en', '游泳部的巨乳老师被我的肉棒抽插成了淫荡的性宠物 MVSD-489-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 42, '美波桃', NULL, NULL, 1, 1, 0, '美波桃', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100038, 'VaJ0fKT1', '/VaJ0fKT1/index.m3u8', '/VaJ0fKT1/1.jpg', '约会长腿妹子温泉宾馆激情性交 SSIS-416', '约会长腿妹子温泉宾馆激情性交 SSIS-416-en', '约会长腿妹子温泉宾馆激情性交 SSIS-416-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 35, '香澄莉子', NULL, NULL, 1, 1, 0, '香澄莉子', 1, 0, '2023-03-07 15:40:31', '2023-03-23 20:23:47', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100039, 'n4dfBpRa', '/n4dfBpRa/index.m3u8', '/n4dfBpRa/1.jpg', '外表清纯的女大学生首次拍片露出淫荡的痴态 SSIS-180', '外表清纯的女大学生首次拍片露出淫荡的痴态 SSIS-180-en', '外表清纯的女大学生首次拍片露出淫荡的痴态 SSIS-180-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 68, '小仓七海', NULL, NULL, 1, 1, 0, '小仓七海', 7, 0, '2023-03-07 15:40:31', '2023-03-24 12:42:34', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100040, 'fBFVdgLP', '/fBFVdgLP/index.m3u8', '/fBFVdgLP/1.jpg', '第一次拍片的美女在肉棒的抽插下仅有的紧张感也消失了 FSDSS-358', '第一次拍片的美女在肉棒的抽插下仅有的紧张感也消失了 FSDSS-358-en', '第一次拍片的美女在肉棒的抽插下仅有的紧张感也消失了 FSDSS-358-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 30, '时田亞美', NULL, NULL, 1, 1, 0, '时田亞美', 2, 0, '2023-03-07 15:40:31', '2023-03-25 14:13:59', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100041, 'cX3wXbdi', '/cX3wXbdi/index.m3u8', '/cX3wXbdi/1.jpg', '痴女学生妹网上约来10名男人一起爽歪歪 HMN-162', '痴女学生妹网上约来10名男人一起爽歪歪 HMN-162-en', '痴女学生妹网上约来10名男人一起爽歪歪 HMN-162-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 1, 1, 0, '白濑飞鸟', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100042, 'a46sewUQ', '/a46sewUQ/index.m3u8', '/a46sewUQ/1.jpg', '猛男操持着巨根急速抽插骚货的肉穴 SSIS-284', '猛男操持着巨根急速抽插骚货的肉穴 SSIS-284-en', '猛男操持着巨根急速抽插骚货的肉穴 SSIS-284-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 35, '香澄莉子', NULL, NULL, 1, 1, 0, '香澄莉子', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100043, 'yMpS5bPI', '/yMpS5bPI/index.m3u8', '/yMpS5bPI/1.jpg', '痴女被大叔的肉棒干到高潮连连 FSDSS-359', '痴女被大叔的肉棒干到高潮连连 FSDSS-359-en', '痴女被大叔的肉棒干到高潮连连 FSDSS-359-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 30, '时田亞美', NULL, NULL, 1, 1, 0, '时田亞美', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100044, '9IVCdMLj', '/9IVCdMLj/index.m3u8', '/9IVCdMLj/1.jpg', '巨乳美女从中学时代开始的性交妄想初体验 MIDV-076（下）', '巨乳美女从中学时代开始的性交妄想初体验 MIDV-076（下）-en', '巨乳美女从中学时代开始的性交妄想初体验 MIDV-076（下）-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 25, '宍户里帆', NULL, NULL, 1, 1, 0, '宍户里帆', 2, 0, '2023-03-07 15:40:31', '2023-03-27 19:47:00', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100045, 'CIGxBQ2m', '/CIGxBQ2m/index.m3u8', '/CIGxBQ2m/1.jpg', '饥渴难耐的哥哥忍不住强奸了自己的继妹 ROYD-063', '饥渴难耐的哥哥忍不住强奸了自己的继妹 ROYD-063-en', '饥渴难耐的哥哥忍不住强奸了自己的继妹 ROYD-063-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 1, 1, 0, '白濑飞鸟', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100046, 'Xy5GlreQ', '/Xy5GlreQ/index.m3u8', '/Xy5GlreQ/1.jpg', '用肉棒对女友的妹妹进行性高潮指导 FSDSS-400', '用肉棒对女友的妹妹进行性高潮指导 FSDSS-400-en', '用肉棒对女友的妹妹进行性高潮指导 FSDSS-400-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 30, '时田亞美', NULL, NULL, 1, 1, 0, '时田亞美', 1, 0, '2023-03-07 15:40:31', '2023-03-17 15:20:25', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100047, 'pXqlOpm3', '/pXqlOpm3/index.m3u8', '/pXqlOpm3/1.jpg', '美少女被摄影师挑逗的内裤湿了一大片 MMND-202', '美少女被摄影师挑逗的内裤湿了一大片 MMND-202-en', '美少女被摄影师挑逗的内裤湿了一大片 MMND-202-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 68, '小仓七海', NULL, NULL, 1, 1, 0, '小仓七海', 4, 0, '2023-03-07 15:40:31', '2023-03-24 14:27:39', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100048, 'tsev3wuK', '/tsev3wuK/index.m3u8', '/tsev3wuK/1.jpg', '极品女优性感裸体写真集 SXAR-010', '极品女优性感裸体写真集 SXAR-010-en', '极品女优性感裸体写真集 SXAR-010-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 1, 1, 0, '白濑飞鸟', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100049, '3E4YB9N7', '/3E4YB9N7/index.m3u8', '/3E4YB9N7/1.jpg', '痴女学生妹子诱惑老师抽插自己的骚穴 SSIS-297', '痴女学生妹子诱惑老师抽插自己的骚穴 SSIS-297-en', '痴女学生妹子诱惑老师抽插自己的骚穴 SSIS-297-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 68, '小仓七海', NULL, NULL, 1, 1, 0, '小仓七海', 4, 0, '2023-03-07 15:40:31', '2023-03-24 12:42:29', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100050, 'niVuUYtd', '/niVuUYtd/index.m3u8', '/niVuUYtd/1.jpg', '与客人温泉酒店激情性交大玩3P BAB-054', '与客人温泉酒店激情性交大玩3P BAB-054-en', '与客人温泉酒店激情性交大玩3P BAB-054-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 42, '美波桃', NULL, NULL, 1, 1, 0, '美波桃', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100051, 'uqpVMVZu', '/uqpVMVZu/index.m3u8', '/uqpVMVZu/1.jpg', '电话叫来巨乳妓女兄弟俩一起爆操骚穴 EBOD-864', '电话叫来巨乳妓女兄弟俩一起爆操骚穴 EBOD-864-en', '电话叫来巨乳妓女兄弟俩一起爆操骚穴 EBOD-864-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 42, '美波桃', NULL, NULL, 1, 1, 0, '美波桃', 1, 0, '2023-03-07 15:40:31', '2023-03-27 17:19:21', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100052, 's02ncK79', '/s02ncK79/index.m3u8', '/s02ncK79/1.jpg', '为了救弟弟，姐姐成了黑社会的性宠物 MVSD-510', '为了救弟弟，姐姐成了黑社会的性宠物 MVSD-510-en', '为了救弟弟，姐姐成了黑社会的性宠物 MVSD-510-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 1, 1, 0, '白濑飞鸟', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100053, 'av6utpx6', '/av6utpx6/index.m3u8', '/av6utpx6/1.jpg', '巨乳美女从中学时代开始的性交妄想初体验 MIDV-076（上）', '巨乳美女从中学时代开始的性交妄想初体验 MIDV-076（上）-en', '巨乳美女从中学时代开始的性交妄想初体验 MIDV-076（上）-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 25, '宍户里帆', NULL, NULL, 1, 1, 0, '宍户里帆', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100054, 'y4DECOAF', '/y4DECOAF/index.m3u8', '/y4DECOAF/1.jpg', '童颜巨乳的女大学生下海拍片 MIDV-056', '童颜巨乳的女大学生下海拍片 MIDV-056-en', '童颜巨乳的女大学生下海拍片 MIDV-056-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 25, '宍户里帆', NULL, NULL, 1, 1, 0, '宍户里帆', 1, 0, '2023-03-07 15:40:31', '2023-03-27 21:23:04', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100055, 'w1Gm1ej8', '/w1Gm1ej8/index.m3u8', '/w1Gm1ej8/1.jpg', '随时随地可以让你爆操嫩穴的极品女仆 SSIS-374', '随时随地可以让你爆操嫩穴的极品女仆 SSIS-374-en', '随时随地可以让你爆操嫩穴的极品女仆 SSIS-374-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 68, '小仓七海', NULL, NULL, 1, 1, 0, '小仓七海', 3, 0, '2023-03-07 15:40:31', '2023-03-27 19:47:07', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100056, 'fVgRQ99T', '/fVgRQ99T/index.m3u8', '/fVgRQ99T/1.jpg', '美少女恳求肉棒插入自己的骚穴只求高潮 FSDSS-360', '美少女恳求肉棒插入自己的骚穴只求高潮 FSDSS-360-en', '美少女恳求肉棒插入自己的骚穴只求高潮 FSDSS-360-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 30, '时田亞美', NULL, NULL, 1, 1, 0, '时田亞美', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100057, 'RYd9vQMz', '/RYd9vQMz/index.m3u8', '/RYd9vQMz/1.jpg', '现役美女大学生按摩店体验被无套中出 MIDV-097', '现役美女大学生按摩店体验被无套中出 MIDV-097-en', '现役美女大学生按摩店体验被无套中出 MIDV-097-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 25, '宍户里帆', NULL, NULL, 1, 1, 0, '宍户里帆', 1, 0, '2023-03-07 15:40:31', '2023-03-24 14:34:23', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100058, 'QBn1MYHb', '/QBn1MYHb/index.m3u8', '/QBn1MYHb/1.jpg', '巨乳学生妹子恳求肉棒无套中出爽翻天 WAAA-130', '巨乳学生妹子恳求肉棒无套中出爽翻天 WAAA-130-en', '巨乳学生妹子恳求肉棒无套中出爽翻天 WAAA-130-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 42, '美波桃', NULL, NULL, 1, 1, 0, '美波桃', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100059, '7zBlGYAD', '/7zBlGYAD/index.m3u8', '/7zBlGYAD/1.jpg', '众多巨屌男优对痴女进行子宫开放让她浑身抽搐爽歪歪 SSIS-336', '众多巨屌男优对痴女进行子宫开放让她浑身抽搐爽歪歪 SSIS-336-en', '众多巨屌男优对痴女进行子宫开放让她浑身抽搐爽歪歪 SSIS-336-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 35, '香澄莉子', NULL, NULL, 1, 1, 0, '香澄莉子', 1, 0, '2023-03-07 15:40:31', '2023-03-27 20:16:13', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100060, 'CFGW5EJb', '/CFGW5EJb/index.m3u8', '/CFGW5EJb/1.jpg', '性欲旺盛的痴女与炮友酒店激情干炮 SSIS-239', '性欲旺盛的痴女与炮友酒店激情干炮 SSIS-239-en', '性欲旺盛的痴女与炮友酒店激情干炮 SSIS-239-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 68, '小仓七海', NULL, NULL, 1, 1, 0, '小仓七海', 4, 0, '2023-03-07 15:40:31', '2023-03-24 12:42:32', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100061, 'jYy2Kw7t', '/jYy2Kw7t/index.m3u8', '/jYy2Kw7t/1.jpg', '巨屌狠狠插入子宫深处让痴女爽到腰部弓起痉挛不止 SSIS-209', '巨屌狠狠插入子宫深处让痴女爽到腰部弓起痉挛不止 SSIS-209-en', '巨屌狠狠插入子宫深处让痴女爽到腰部弓起痉挛不止 SSIS-209-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 68, '小仓七海', NULL, NULL, 1, 1, 0, '小仓七海', 5, 1, '2023-03-07 15:40:31', '2023-03-24 14:48:07', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100062, 'jqc5zLrS', '/jqc5zLrS/index.m3u8', '/jqc5zLrS/1.jpg', '七海性欲觉醒被巨根抽插到浑身抽搐 SSIS-267', '七海性欲觉醒被巨根抽插到浑身抽搐 SSIS-267-en', '七海性欲觉醒被巨根抽插到浑身抽搐 SSIS-267-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 68, '小仓七海', NULL, NULL, 1, 1, 0, '小仓七海', 2, 0, '2023-03-07 15:40:31', '2023-03-27 19:38:08', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100063, 'LLqIFMpM', '/LLqIFMpM/index.m3u8', '/LLqIFMpM/1.jpg', '小情侣在家换上泳装激情性交 OKK-036', '小情侣在家换上泳装激情性交 OKK-036-en', '小情侣在家换上泳装激情性交 OKK-036-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 1, 1, 0, '白濑飞鸟', 1, 0, '2023-03-07 15:40:31', '2023-03-28 14:01:00', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100064, '4FsSsnAH', '/4FsSsnAH/index.m3u8', '/4FsSsnAH/1.jpg', '一进房间就看两个穿泳装的美女', '一进房间就看两个穿泳装的美女-en', '一进房间就看两个穿泳装的美女-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100065, '4NDKSrsI', '/4NDKSrsI/index.m3u8', '/4NDKSrsI/1.jpg', '私密展示自己的黑色丛林', '私密展示自己的黑色丛林-en', '私密展示自己的黑色丛林-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100066, 'GNy40lDR', '/GNy40lDR/index.m3u8', '/GNy40lDR/1.jpg', '火辣辣的小学妹勾引学长', '火辣辣的小学妹勾引学长-en', '火辣辣的小学妹勾引学长-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100067, 'jsbb660D', '/jsbb660D/index.m3u8', '/jsbb660D/1.jpg', '透明情趣制服小骚货', '透明情趣制服小骚货-en', '透明情趣制服小骚货-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100068, 'J3Rag74n', '/J3Rag74n/index.m3u8', '/J3Rag74n/1.jpg', '看脸也就养养眼，看逼才是王道', '看脸也就养养眼，看逼才是王道-en', '看脸也就养养眼，看逼才是王道-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 2, 0, '2023-03-07 15:40:31', '2023-03-23 15:18:07', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100069, 'JceWlVam', '/JceWlVam/index.m3u8', '/JceWlVam/1.jpg', '就喜欢这样低俗的妹子', '就喜欢这样低俗的妹子-en', '就喜欢这样低俗的妹子-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100070, 'j2yeQHF8', '/j2yeQHF8/index.m3u8', '/j2yeQHF8/1.jpg', '练习老汉推车的韩国骚主播', '练习老汉推车的韩国骚主播-en', '练习老汉推车的韩国骚主播-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 6, 0, '2023-03-07 15:40:31', '2023-03-28 14:00:55', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100071, 'vFmvv1d2', '/vFmvv1d2/index.m3u8', '/vFmvv1d2/1.jpg', '可爱真的比不上黑丝诱惑', '可爱真的比不上黑丝诱惑-en', '可爱真的比不上黑丝诱惑-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 132, 1, '2023-03-07 15:40:31', '2023-03-27 22:03:32', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100072, '2QCIWixa', '/2QCIWixa/index.m3u8', '/2QCIWixa/1.jpg', '在房间练习扭臀骚舞的韩国小婊子', '在房间练习扭臀骚舞的韩国小婊子-en', '在房间练习扭臀骚舞的韩国小婊子-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100073, 'mudj1Ruo', '/mudj1Ruo/index.m3u8', '/mudj1Ruo/1.jpg', '爱在哪里？在女神逼逼里', '爱在哪里？在女神逼逼里-en', '爱在哪里？在女神逼逼里-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100074, 'Q7JUZVic', '/Q7JUZVic/index.m3u8', '/Q7JUZVic/1.jpg', '妹子屁股真够大的完美炮架', '妹子屁股真够大的完美炮架-en', '妹子屁股真够大的完美炮架-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100075, 'a4gAe9G1', '/a4gAe9G1/index.m3u8', '/a4gAe9G1/1.jpg', '有这么个老婆两个腰子都不够用', '有这么个老婆两个腰子都不够用-en', '有这么个老婆两个腰子都不够用-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 1, 0, '2023-03-07 15:40:31', '2023-03-27 19:46:24', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100076, 'WS31a4JK', '/WS31a4JK/index.m3u8', '/WS31a4JK/1.jpg', '肉感美腿女神黑丝诱惑', '肉感美腿女神黑丝诱惑-en', '肉感美腿女神黑丝诱惑-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 1, 0, '2023-03-07 15:40:31', '2023-03-23 22:06:33', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100077, 'TiSMPqNl', '/TiSMPqNl/index.m3u8', '/TiSMPqNl/1.jpg', '主播的丰满的美臀还不点开看', '主播的丰满的美臀还不点开看-en', '主播的丰满的美臀还不点开看-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100078, 'gSzhViTz', '/gSzhViTz/index.m3u8', '/gSzhViTz/1.jpg', '柔软白嫩的大咪咪想摸摸吗', '柔软白嫩的大咪咪想摸摸吗-en', '柔软白嫩的大咪咪想摸摸吗-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100079, 'rcQ171Il', '/rcQ171Il/index.m3u8', '/rcQ171Il/1.jpg', '小哥肉棒安慰女闺蜜', '小哥肉棒安慰女闺蜜-en', '小哥肉棒安慰女闺蜜-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100080, 'PgXs8bWX', '/PgXs8bWX/index.m3u8', '/PgXs8bWX/1.jpg', '微胖萌妹子肥肥馒头逼好粉嫩', '微胖萌妹子肥肥馒头逼好粉嫩-en', '微胖萌妹子肥肥馒头逼好粉嫩-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100081, 'SvBlOmGj', '/SvBlOmGj/index.m3u8', '/SvBlOmGj/1.jpg', '嫂子穿上性感睡衣给我倒茶时咪咪若隐若现', '嫂子穿上性感睡衣给我倒茶时咪咪若隐若现-en', '嫂子穿上性感睡衣给我倒茶时咪咪若隐若现-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100082, '71ELH8Yl', '/71ELH8Yl/index.m3u8', '/71ELH8Yl/1.jpg', '直播间用按摩棒将自己搞到潮吹', '直播间用按摩棒将自己搞到潮吹-en', '直播间用按摩棒将自己搞到潮吹-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100083, 'DqEylERS', '/DqEylERS/index.m3u8', '/DqEylERS/1.jpg', '浑身赤裸的萌妹子发骚', '浑身赤裸的萌妹子发骚-en', '浑身赤裸的萌妹子发骚-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100084, 'yGW6kxnh', '/yGW6kxnh/index.m3u8', '/yGW6kxnh/1.jpg', '韩国妹妹的自摸诱惑', '韩国妹妹的自摸诱惑-en', '韩国妹妹的自摸诱惑-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100085, '9SOQW0hf', '/9SOQW0hf/index.m3u8', '/9SOQW0hf/1.jpg', '大奶子的骚主播使劲抠逼', '大奶子的骚主播使劲抠逼-en', '大奶子的骚主播使劲抠逼-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100086, 'R0nTM0TV', '/R0nTM0TV/index.m3u8', '/R0nTM0TV/1.jpg', '圆润的奶子的主播露出奶子摸逼', '圆润的奶子的主播露出奶子摸逼-en', '圆润的奶子的主播露出奶子摸逼-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100087, 'cm3vstmK', '/cm3vstmK/index.m3u8', '/cm3vstmK/1.jpg', '性感车模寂寞居家自慰', '性感车模寂寞居家自慰-en', '性感车模寂寞居家自慰-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100088, 'EWnOVTkA', '/EWnOVTkA/index.m3u8', '/EWnOVTkA/1.jpg', '妹子打赌输了鼓起勇气当众自慰', '妹子打赌输了鼓起勇气当众自慰-en', '妹子打赌输了鼓起勇气当众自慰-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100089, 'fEHE2an9', '/fEHE2an9/index.m3u8', '/fEHE2an9/1.jpg', '白色的液体使劲揉搓大奶子', '白色的液体使劲揉搓大奶子-en', '白色的液体使劲揉搓大奶子-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100090, 'ZjDskx3o', '/ZjDskx3o/index.m3u8', '/ZjDskx3o/1.jpg', '手指不够长就用道具抽插骚穴', '手指不够长就用道具抽插骚穴-en', '手指不够长就用道具抽插骚穴-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100091, 'JRDl0NS4', '/JRDl0NS4/index.m3u8', '/JRDl0NS4/1.jpg', '韩国婊子自慰到高潮', '韩国婊子自慰到高潮-en', '韩国婊子自慰到高潮-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100092, '7i1cdCs6', '/7i1cdCs6/index.m3u8', '/7i1cdCs6/1.jpg', '女主播每晚屋子开激情视频', '女主播每晚屋子开激情视频-en', '女主播每晚屋子开激情视频-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100093, 'cXpBJlPP', '/cXpBJlPP/index.m3u8', '/cXpBJlPP/1.jpg', '不穿衣服的主播撅着腚发出浪叫', '不穿衣服的主播撅着腚发出浪叫-en', '不穿衣服的主播撅着腚发出浪叫-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100094, 'eSfJ7sPK', '/eSfJ7sPK/index.m3u8', '/eSfJ7sPK/1.jpg', '韩国不良少女展示热辣身材', '韩国不良少女展示热辣身材-en', '韩国不良少女展示热辣身材-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100095, 'v6ADnNR4', '/v6ADnNR4/index.m3u8', '/v6ADnNR4/1.jpg', '气质熟女诱人脱衣舞', '气质熟女诱人脱衣舞-en', '气质熟女诱人脱衣舞-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100096, 'eW9Q87G9', '/eW9Q87G9/index.m3u8', '/eW9Q87G9/1.jpg', '一对奶子重十斤的可爱妹子', '一对奶子重十斤的可爱妹子-en', '一对奶子重十斤的可爱妹子-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100097, 'VS6j8GX2', '/VS6j8GX2/index.m3u8', '/VS6j8GX2/1.jpg', '今天到女神家里给她做深喉运动', '今天到女神家里给她做深喉运动-en', '今天到女神家里给她做深喉运动-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100098, 'INcdn1lo', '/INcdn1lo/index.m3u8', '/INcdn1lo/1.jpg', '淫荡女孩想当男人专用肉便器', '淫荡女孩想当男人专用肉便器-en', '淫荡女孩想当男人专用肉便器-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100099, '8HMwugQD', '/8HMwugQD/index.m3u8', '/8HMwugQD/1.jpg', '上不了台面的美女台下玩的够淫荡', '上不了台面的美女台下玩的够淫荡-en', '上不了台面的美女台下玩的够淫荡-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100100, 'M5qzrMZQ', '/M5qzrMZQ/index.m3u8', '/M5qzrMZQ/1.jpg', '打开视频看到女主播只穿内裤在床上', '打开视频看到女主播只穿内裤在床上-en', '打开视频看到女主播只穿内裤在床上-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100101, 'ENPLnba0', '/ENPLnba0/index.m3u8', '/ENPLnba0/1.jpg', '韩国女主播人前文静床上骚气', '韩国女主播人前文静床上骚气-en', '韩国女主播人前文静床上骚气-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 6, 1, '2023-03-07 15:40:31', '2023-03-27 20:12:53', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100102, 'iiHXlJPS', '/iiHXlJPS/index.m3u8', '/iiHXlJPS/1.jpg', '出来偷腥没想到约了个大美女', '出来偷腥没想到约了个大美女-en', '出来偷腥没想到约了个大美女-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100103, 'q1rroMXq', '/q1rroMXq/index.m3u8', '/q1rroMXq/1.jpg', '大奶少女跳起舞来奶子都快甩到脸上了', '大奶少女跳起舞来奶子都快甩到脸上了-en', '大奶少女跳起舞来奶子都快甩到脸上了-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100104, 'mIdBJctg', '/mIdBJctg/index.m3u8', '/mIdBJctg/1.jpg', '翘臀就在我的眼睛前面来回抖动', '翘臀就在我的眼睛前面来回抖动-en', '翘臀就在我的眼睛前面来回抖动-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100105, 'EzupkJBq', '/EzupkJBq/index.m3u8', '/EzupkJBq/1.jpg', '鲜嫩多汁的风骚表姐', '鲜嫩多汁的风骚表姐-en', '鲜嫩多汁的风骚表姐-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 3, 0, '2023-03-07 15:40:31', '2023-03-23 16:11:43', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100106, '1FPWnGzy', '/1FPWnGzy/index.m3u8', '/1FPWnGzy/1.jpg', '我的淫荡女同事', '我的淫荡女同事-en', '我的淫荡女同事-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100107, 'OwE8IOFi', '/OwE8IOFi/index.m3u8', '/OwE8IOFi/1.jpg', '约了个妹妹见面之后一发不可收拾', '约了个妹妹见面之后一发不可收拾-en', '约了个妹妹见面之后一发不可收拾-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 5, 1, '2023-03-07 15:40:31', '2023-03-27 19:47:21', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100108, 'RTqxdfy4', '/RTqxdfy4/index.m3u8', '/RTqxdfy4/1.jpg', '小骚货隔着内裤水都要流出来了', '小骚货隔着内裤水都要流出来了-en', '小骚货隔着内裤水都要流出来了-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 8, 1, '2023-03-07 15:40:31', '2023-03-27 13:58:20', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100109, 'QDsfcEi3', '/QDsfcEi3/index.m3u8', '/QDsfcEi3/1.jpg', '上门服务的臭婊子', '上门服务的臭婊子-en', '上门服务的臭婊子-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 2, 0, '2023-03-07 15:40:31', '2023-03-28 13:52:49', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100110, '5clf0Rk5', '/5clf0Rk5/index.m3u8', '/5clf0Rk5/1.jpg', '妹妹两个大奶子双手都抓不住', '妹妹两个大奶子双手都抓不住-en', '妹妹两个大奶子双手都抓不住-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 87, 1, '2023-03-07 15:40:31', '2023-03-28 13:45:45', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100111, 'xoW6slTF', '/xoW6slTF/index.m3u8', '/xoW6slTF/1.jpg', '蕾丝胸罩里面是波涛汹涌的大奶子', '蕾丝胸罩里面是波涛汹涌的大奶子-en', '蕾丝胸罩里面是波涛汹涌的大奶子-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100112, 'i3Y78J1S', '/i3Y78J1S/index.m3u8', '/i3Y78J1S/1.jpg', '阿姨撅起大屁股等待我的进入', '阿姨撅起大屁股等待我的进入-en', '阿姨撅起大屁股等待我的进入-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 3, 0, '2023-03-07 15:40:31', '2023-03-23 16:11:44', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100113, 'ajcIg3pO', '/ajcIg3pO/index.m3u8', '/ajcIg3pO/1.jpg', '女大学生喜欢买自己的原味丝袜', '女大学生喜欢买自己的原味丝袜-en', '女大学生喜欢买自己的原味丝袜-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100114, 'oX0vyOMA', '/oX0vyOMA/index.m3u8', '/oX0vyOMA/1.jpg', '被干到失禁的小淫娃', '被干到失禁的小淫娃-en', '被干到失禁的小淫娃-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100115, '9RtOJ6pa', '/9RtOJ6pa/index.m3u8', '/9RtOJ6pa/1.jpg', '淫荡少妇喜欢年轻的肉体', '淫荡少妇喜欢年轻的肉体-en', '淫荡少妇喜欢年轻的肉体-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 7, 1, '2023-03-07 15:40:31', '2023-03-27 13:58:23', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100116, 'NkYXwCLB', '/NkYXwCLB/index.m3u8', '/NkYXwCLB/1.jpg', '萝莉装扮御姐的气场', '萝莉装扮御姐的气场-en', '萝莉装扮御姐的气场-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100117, 'HkXpNZE4', '/HkXpNZE4/index.m3u8', '/HkXpNZE4/1.jpg', '张开双腿等待肉棒进入', '张开双腿等待肉棒进入-en', '张开双腿等待肉棒进入-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100118, 'ZeXajU1W', '/ZeXajU1W/index.m3u8', '/ZeXajU1W/1.jpg', '韩国主播喜欢不穿衣服出来直播', '韩国主播喜欢不穿衣服出来直播-en', '韩国主播喜欢不穿衣服出来直播-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100119, 'lGpMkT4B', '/lGpMkT4B/index.m3u8', '/lGpMkT4B/1.jpg', '异地恋的小情侣刚见面就开操', '异地恋的小情侣刚见面就开操-en', '异地恋的小情侣刚见面就开操-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100120, 'uVzmfPm7', '/uVzmfPm7/index.m3u8', '/uVzmfPm7/1.jpg', '隔壁的姐姐一直在勾引我', '隔壁的姐姐一直在勾引我-en', '隔壁的姐姐一直在勾引我-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100121, 'l1mEqoI3', '/l1mEqoI3/index.m3u8', '/l1mEqoI3/1.jpg', '跪着被疯狂后入的小们嫩模', '跪着被疯狂后入的小们嫩模-en', '跪着被疯狂后入的小们嫩模-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 1, 0, '2023-03-07 15:40:31', '2023-03-27 19:39:37', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100122, 'Yc3ED8Xn', '/Yc3ED8Xn/index.m3u8', '/Yc3ED8Xn/1.jpg', '女舍友的胸部发育的真不错', '女舍友的胸部发育的真不错-en', '女舍友的胸部发育的真不错-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100123, 'T5Cn7EKs', '/T5Cn7EKs/index.m3u8', '/T5Cn7EKs/1.jpg', '调教我的萌妹女友', '调教我的萌妹女友-en', '调教我的萌妹女友-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100124, 'p8bQyU9m', '/p8bQyU9m/index.m3u8', '/p8bQyU9m/1.jpg', '马尾少女丝袜诱惑', '马尾少女丝袜诱惑-en', '马尾少女丝袜诱惑-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100125, 'BLTVL6EH', '/BLTVL6EH/index.m3u8', '/BLTVL6EH/1.jpg', '法式舌吻把妹子给征服了', '法式舌吻把妹子给征服了-en', '法式舌吻把妹子给征服了-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100126, 'PTaEYQdM', '/PTaEYQdM/index.m3u8', '/PTaEYQdM/1.jpg', '美少女的真空诱惑', '美少女的真空诱惑-en', '美少女的真空诱惑-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100127, 'i8efQmH2', '/i8efQmH2/index.m3u8', '/i8efQmH2/1.jpg', '哥哥过来陪陪我', '哥哥过来陪陪我-en', '哥哥过来陪陪我-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100128, 'TNtap7Li', '/TNtap7Li/index.m3u8', '/TNtap7Li/1.jpg', '猛男把妹子干的淫水直流', '猛男把妹子干的淫水直流-en', '猛男把妹子干的淫水直流-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100129, 'OieZo5ZL', '/OieZo5ZL/index.m3u8', '/OieZo5ZL/1.jpg', '奶子好翘的韩国女主播', '奶子好翘的韩国女主播-en', '奶子好翘的韩国女主播-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100130, 'MvlEr699', '/MvlEr699/index.m3u8', '/MvlEr699/1.jpg', '小骚货希望找到一个大鸡巴哥哥', '小骚货希望找到一个大鸡巴哥哥-en', '小骚货希望找到一个大鸡巴哥哥-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100131, 'ENM3o3sR', '/ENM3o3sR/index.m3u8', '/ENM3o3sR/1.jpg', '透明的外套衬托出完美的身材', '透明的外套衬托出完美的身材-en', '透明的外套衬托出完美的身材-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100132, 'AQRbqNzj', '/AQRbqNzj/index.m3u8', '/AQRbqNzj/1.jpg', '妹妹的骚逼只能被哥哥填满', '妹妹的骚逼只能被哥哥填满-en', '妹妹的骚逼只能被哥哥填满-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100133, 'ynWZI6du', '/ynWZI6du/index.m3u8', '/ynWZI6du/1.jpg', '猛操刚在一起的女朋友', '猛操刚在一起的女朋友-en', '猛操刚在一起的女朋友-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100134, '8wAiZlpR', '/8wAiZlpR/index.m3u8', '/8wAiZlpR/1.jpg', '被掐住脖子疯狂抽插', '被掐住脖子疯狂抽插-en', '被掐住脖子疯狂抽插-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100135, 'x9wZg3uq', '/x9wZg3uq/index.m3u8', '/x9wZg3uq/1.jpg', '包臀裙尽显风骚韵味', '包臀裙尽显风骚韵味-en', '包臀裙尽显风骚韵味-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100136, 'QC261uuG', '/QC261uuG/index.m3u8', '/QC261uuG/1.jpg', '喝醉的漂亮女孩喜欢追着我亲亲', '喝醉的漂亮女孩喜欢追着我亲亲-en', '喝醉的漂亮女孩喜欢追着我亲亲-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100137, 'BDD7aV3U', '/BDD7aV3U/index.m3u8', '/BDD7aV3U/1.jpg', '表姐在家偷偷自慰被我发现了', '表姐在家偷偷自慰被我发现了-en', '表姐在家偷偷自慰被我发现了-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100138, 'c2m8Lq7r', '/c2m8Lq7r/index.m3u8', '/c2m8Lq7r/1.jpg', '黑丝女秘书被老板慢慢调教', '黑丝女秘书被老板慢慢调教-en', '黑丝女秘书被老板慢慢调教-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100139, 'dx7Apm1Q', '/dx7Apm1Q/index.m3u8', '/dx7Apm1Q/1.jpg', '纯欲学姐带给我无尽的性交', '纯欲学姐带给我无尽的性交-en', '纯欲学姐带给我无尽的性交-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100140, 'q7JdOsjO', '/q7JdOsjO/index.m3u8', '/q7JdOsjO/1.jpg', '被惩罚的女仆小妹妹', '被惩罚的女仆小妹妹-en', '被惩罚的女仆小妹妹-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100141, 'DTTh7P9h', '/DTTh7P9h/index.m3u8', '/DTTh7P9h/1.jpg', '文静的少女脱了衣服诱惑我', '文静的少女脱了衣服诱惑我-en', '文静的少女脱了衣服诱惑我-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100142, 'sb7Xeeox', '/sb7Xeeox/index.m3u8', '/sb7Xeeox/1.jpg', '发情的女老师看上了自己的学生', '发情的女老师看上了自己的学生-en', '发情的女老师看上了自己的学生-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100143, '5SLCtUvi', '/5SLCtUvi/index.m3u8', '/5SLCtUvi/1.jpg', '淫荡小母狗喜欢含着我的肉棒', '淫荡小母狗喜欢含着我的肉棒-en', '淫荡小母狗喜欢含着我的肉棒-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 1, 1, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100144, 'UQ3mWl7h', '/UQ3mWl7h/index.m3u8', '/UQ3mWl7h/1.jpg', '风情万种32岁少妇提前开好情趣酒店等待操逼', '风情万种32岁少妇提前开好情趣酒店等待操逼-en', '风情万种32岁少妇提前开好情趣酒店等待操逼-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '偷情王哥', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100145, 'AxMp1QuC', '/AxMp1QuC/index.m3u8', '/AxMp1QuC/1.jpg', '魔都极品身材美少女被男朋友操到无力还击，扒开内裤无套直插无毛浪穴', '魔都极品身材美少女被男朋友操到无力还击，扒开内裤无套直插无毛浪穴-en', '魔都极品身材美少女被男朋友操到无力还击，扒开内裤无套直插无毛浪穴-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '一手自拍', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100146, 'Z1WS5oPI', '/Z1WS5oPI/index.m3u8', '/Z1WS5oPI/1.jpg', '1500网约漂亮小少妇，网红脸极品美乳', '1500网约漂亮小少妇，网红脸极品美乳-en', '1500网约漂亮小少妇，网红脸极品美乳-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '偷情王哥', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100147, 'qLVybPzY', '/qLVybPzY/index.m3u8', '/qLVybPzY/1.jpg', '厕拍大神潜入某大学沟厕偷拍学妹尿尿，被两个妹子发现回头看', '厕拍大神潜入某大学沟厕偷拍学妹尿尿，被两个妹子发现回头看-en', '厕拍大神潜入某大学沟厕偷拍学妹尿尿，被两个妹子发现回头看-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '一手自拍', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100148, 'hkr5V3qy', '/hkr5V3qy/index.m3u8', '/hkr5V3qy/1.jpg', '国内小情侣美乳丰臀性爱，甄选各式姿势大屌操浪穴真爽（一）', '国内小情侣美乳丰臀性爱，甄选各式姿势大屌操浪穴真爽（一）-en', '国内小情侣美乳丰臀性爱，甄选各式姿势大屌操浪穴真爽（一）-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '一手自拍', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100149, 'IABw4GP4', '/IABw4GP4/index.m3u8', '/IABw4GP4/1.jpg', '女子保健新人良家少妇，真实偷拍看极品美穴', '女子保健新人良家少妇，真实偷拍看极品美穴-en', '女子保健新人良家少妇，真实偷拍看极品美穴-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '一手自拍', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100150, 'UV1KFDHj', '/UV1KFDHj/index.m3u8', '/UV1KFDHj/1.jpg', '网红脸外围小少妇，白嫩圆润美臀满分', '网红脸外围小少妇，白嫩圆润美臀满分-en', '网红脸外围小少妇，白嫩圆润美臀满分-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '偷情王哥', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100151, 'bmMBgZk3', '/bmMBgZk3/index.m3u8', '/bmMBgZk3/1.jpg', '极品身材网红美女樱桃妹妹，要和爸爸玩玩具直到水声不断再从后面进入', '极品身材网红美女樱桃妹妹，要和爸爸玩玩具直到水声不断再从后面进入-en', '极品身材网红美女樱桃妹妹，要和爸爸玩玩具直到水声不断再从后面进入-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '一手自拍', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100152, 'OETxn4Sv', '/OETxn4Sv/index.m3u8', '/OETxn4Sv/1.jpg', '厕拍大神潜入某单位女厕全景后拍白虎少妇逼逼', '厕拍大神潜入某单位女厕全景后拍白虎少妇逼逼-en', '厕拍大神潜入某单位女厕全景后拍白虎少妇逼逼-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '一手自拍', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100153, 'ggYTmb7Y', '/ggYTmb7Y/index.m3u8', '/ggYTmb7Y/1.jpg', '国内小情侣美乳丰臀性爱，甄选各式姿势大屌操浪穴真爽（五）', '国内小情侣美乳丰臀性爱，甄选各式姿势大屌操浪穴真爽（五）-en', '国内小情侣美乳丰臀性爱，甄选各式姿势大屌操浪穴真爽（五）-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '一手自拍', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100154, 'G5gLTfpq', '/G5gLTfpq/index.m3u8', '/G5gLTfpq/1.jpg', '非常白嫩漂亮足球宝贝用身体慰藉球员', '非常白嫩漂亮足球宝贝用身体慰藉球员-en', '非常白嫩漂亮足球宝贝用身体慰藉球员-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '一手自拍', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100155, 'ZTi426Hg', '/ZTi426Hg/index.m3u8', '/ZTi426Hg/1.jpg', '寂寞的23岁女神，跳蛋深入毛茸茸蜜穴', '寂寞的23岁女神，跳蛋深入毛茸茸蜜穴-en', '寂寞的23岁女神，跳蛋深入毛茸茸蜜穴-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 52, '热辣主播', NULL, NULL, 1, 1, 0, '热辣主播', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100156, 'q5ZijSnP', '/q5ZijSnP/index.m3u8', '/q5ZijSnP/1.jpg', '网袜捆绑诱惑主人，骚穴能承受多少次呢？', '网袜捆绑诱惑主人，骚穴能承受多少次呢？-en', '网袜捆绑诱惑主人，骚穴能承受多少次呢？-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '一手自拍', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100157, 'cjZzxmiW', '/cjZzxmiW/index.m3u8', '/cjZzxmiW/1.jpg', '极品嫩模携闺蜜玩双飞，双凤争屌', '极品嫩模携闺蜜玩双飞，双凤争屌-en', '极品嫩模携闺蜜玩双飞，双凤争屌-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '一手自拍', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100158, 'QZVmzo2o', '/QZVmzo2o/index.m3u8', '/QZVmzo2o/1.jpg', '美女被几个饥渴男各种操，最后被男友操得瘫软在床', '美女被几个饥渴男各种操，最后被男友操得瘫软在床-en', '美女被几个饥渴男各种操，最后被男友操得瘫软在床-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '一手自拍', 1, 0, '2023-03-07 15:40:31', '2023-03-23 22:01:29', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100159, '5jmLIR5S', '/5jmLIR5S/index.m3u8', '/5jmLIR5S/1.jpg', '国内小情侣美乳丰臀性爱，甄选各式姿势大屌操浪穴真爽（四）', '国内小情侣美乳丰臀性爱，甄选各式姿势大屌操浪穴真爽（四）-en', '国内小情侣美乳丰臀性爱，甄选各式姿势大屌操浪穴真爽（四）-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '一手自拍', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100160, 'x7sDpBKD', '/x7sDpBKD/index.m3u8', '/x7sDpBKD/1.jpg', '22岁人气小姐姐回归，身姿曼妙玲珑有致', '22岁人气小姐姐回归，身姿曼妙玲珑有致-en', '22岁人气小姐姐回归，身姿曼妙玲珑有致-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 52, '热辣主播', NULL, NULL, 1, 1, 0, '热辣主播', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100161, 'zBMQDmVj', '/zBMQDmVj/index.m3u8', '/zBMQDmVj/1.jpg', '颜值区绿播下海，满分女神颜值抗打，无惧怼脸拍', '颜值区绿播下海，满分女神颜值抗打，无惧怼脸拍-en', '颜值区绿播下海，满分女神颜值抗打，无惧怼脸拍-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 52, '热辣主播', NULL, NULL, 1, 1, 0, '热辣主播', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100162, 'eBihXiZJ', '/eBihXiZJ/index.m3u8', '/eBihXiZJ/1.jpg', '骚气大奶妹和炮友双人啪啪大秀，拨开内裤后入抽插性感大屁股', '骚气大奶妹和炮友双人啪啪大秀，拨开内裤后入抽插性感大屁股-en', '骚气大奶妹和炮友双人啪啪大秀，拨开内裤后入抽插性感大屁股-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 52, '热辣主播', NULL, NULL, 1, 1, 0, '热辣主播', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100163, 'CvdXMUZf', '/CvdXMUZf/index.m3u8', '/CvdXMUZf/1.jpg', '00后骚淫贱浪淫妹在公园被两个大屌操了，前怼后操口爆', '00后骚淫贱浪淫妹在公园被两个大屌操了，前怼后操口爆-en', '00后骚淫贱浪淫妹在公园被两个大屌操了，前怼后操口爆-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '一手自拍', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100164, 'CfzgXyOL', '/CfzgXyOL/index.m3u8', '/CfzgXyOL/1.jpg', '21岁小奶妈哺乳期还能挤出奶水来，高颜值小骚逼特写水多', '21岁小奶妈哺乳期还能挤出奶水来，高颜值小骚逼特写水多-en', '21岁小奶妈哺乳期还能挤出奶水来，高颜值小骚逼特写水多-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 52, '热辣主播', NULL, NULL, 1, 1, 0, '热辣主播', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100165, '98w4omYR', '/98w4omYR/index.m3u8', '/98w4omYR/1.jpg', '专业男技师SPA推油按摩，又赚钱又干逼爽翻了', '专业男技师SPA推油按摩，又赚钱又干逼爽翻了-en', '专业男技师SPA推油按摩，又赚钱又干逼爽翻了-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '一手自拍', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100166, 'MG4aTq5M', '/MG4aTq5M/index.m3u8', '/MG4aTq5M/1.jpg', '20岁的清纯小妹，正是含苞待放之时，杏眼含情', '20岁的清纯小妹，正是含苞待放之时，杏眼含情-en', '20岁的清纯小妹，正是含苞待放之时，杏眼含情-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '一手自拍', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100167, 'QZkAnEnP', '/QZkAnEnP/index.m3u8', '/QZkAnEnP/1.jpg', '国内小情侣美乳丰臀性爱，甄选各式姿势大屌操浪穴真爽（三）', '国内小情侣美乳丰臀性爱，甄选各式姿势大屌操浪穴真爽（三）-en', '国内小情侣美乳丰臀性爱，甄选各式姿势大屌操浪穴真爽（三）-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '一手自拍', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100168, 'XlVTnTWP', '/XlVTnTWP/index.m3u8', '/XlVTnTWP/1.jpg', '退役空姐出来接单养活男朋友', '退役空姐出来接单养活男朋友-en', '退役空姐出来接单养活男朋友-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '偷情王哥', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100169, 'IOopEnrl', '/IOopEnrl/index.m3u8', '/IOopEnrl/1.jpg', '巨乳小妹凌晨1点继续搞', '巨乳小妹凌晨1点继续搞-en', '巨乳小妹凌晨1点继续搞-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '偷情王哥', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100170, 'pVXylGXe', '/pVXylGXe/index.m3u8', '/pVXylGXe/1.jpg', '国内小情侣美乳丰臀性爱，甄选各式姿势大屌操浪穴真爽（二）', '国内小情侣美乳丰臀性爱，甄选各式姿势大屌操浪穴真爽（二）-en', '国内小情侣美乳丰臀性爱，甄选各式姿势大屌操浪穴真爽（二）-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '一手自拍', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100171, 'UaIRPHUZ', '/UaIRPHUZ/index.m3u8', '/UaIRPHUZ/1.jpg', '超棒身材豪乳女孩在汽车旅馆被骗啪啪，无毛浪穴被大屌肆意蹂躏抽插', '超棒身材豪乳女孩在汽车旅馆被骗啪啪，无毛浪穴被大屌肆意蹂躏抽插-en', '超棒身材豪乳女孩在汽车旅馆被骗啪啪，无毛浪穴被大屌肆意蹂躏抽插-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '一手自拍', 1, 0, '2023-03-07 15:40:31', '2023-03-23 21:36:21', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100172, 'rKhSCAXS', '/rKhSCAXS/index.m3u8', '/rKhSCAXS/1.jpg', '海景酒店内射丝袜高跟鞋豪乳女神', '海景酒店内射丝袜高跟鞋豪乳女神-en', '海景酒店内射丝袜高跟鞋豪乳女神-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '一手自拍', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100173, '9m3ovdc7', '/9m3ovdc7/index.m3u8', '/9m3ovdc7/1.jpg', '豪华酒店SPA勾搭技师做爱啪啪爆操', '豪华酒店SPA勾搭技师做爱啪啪爆操-en', '豪华酒店SPA勾搭技师做爱啪啪爆操-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '偷情王哥', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100174, '9VGOVZfv', '/9VGOVZfv/index.m3u8', '/9VGOVZfv/1.jpg', '极品少女爆裂黑丝制服，假鸡巴插入粉穴高潮到抽搐', '极品少女爆裂黑丝制服，假鸡巴插入粉穴高潮到抽搐-en', '极品少女爆裂黑丝制服，假鸡巴插入粉穴高潮到抽搐-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '一手自拍', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100175, 'B2fL2zAq', '/B2fL2zAq/index.m3u8', '/B2fL2zAq/1.jpg', '国内厕拍大神潜入纸箱厂女厕全景后拍，尿尿美女逼逼还挺嫩', '国内厕拍大神潜入纸箱厂女厕全景后拍，尿尿美女逼逼还挺嫩-en', '国内厕拍大神潜入纸箱厂女厕全景后拍，尿尿美女逼逼还挺嫩-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '一手自拍', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100176, 'j5Q7se2k', '/j5Q7se2k/index.m3u8', '/j5Q7se2k/1.jpg', '探索者系列新作，甜美外围2600一炮', '探索者系列新作，甜美外围2600一炮-en', '探索者系列新作，甜美外围2600一炮-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '偷情王哥', 1, 0, '2023-03-07 15:40:31', '2023-03-27 21:20:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100177, 'pTut5Jnh', '/pTut5Jnh/index.m3u8', '/pTut5Jnh/1.jpg', '外围清纯女神躺在胯下，狂插樱桃小口', '外围清纯女神躺在胯下，狂插樱桃小口-en', '外围清纯女神躺在胯下，狂插樱桃小口-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '偷情王哥', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100178, '9hwoq3N2', '/9hwoq3N2/index.m3u8', '/9hwoq3N2/1.jpg', '少妇空虚寂寞冷来让小伙给高潮，偷拍骚逼流水怒操淫穴', '少妇空虚寂寞冷来让小伙给高潮，偷拍骚逼流水怒操淫穴-en', '少妇空虚寂寞冷来让小伙给高潮，偷拍骚逼流水怒操淫穴-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '一手自拍', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100179, 'hFvERz1R', '/hFvERz1R/index.m3u8', '/hFvERz1R/1.jpg', '少妇凌晨真实撩路人宾馆开房做爱，没操满足还要自己玩喷水', '少妇凌晨真实撩路人宾馆开房做爱，没操满足还要自己玩喷水-en', '少妇凌晨真实撩路人宾馆开房做爱，没操满足还要自己玩喷水-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 52, '热辣主播', NULL, NULL, 1, 1, 0, '热辣主播', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100180, 'Fi069cid', '/Fi069cid/index.m3u8', '/Fi069cid/1.jpg', '超棒身材极品网红大尺度性爱啪啪私拍流出，狐尾肛塞多姿势虐操嫩穴小骚货', '超棒身材极品网红大尺度性爱啪啪私拍流出，狐尾肛塞多姿势虐操嫩穴小骚货-en', '超棒身材极品网红大尺度性爱啪啪私拍流出，狐尾肛塞多姿势虐操嫩穴小骚货-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '一手自拍', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100181, 'XuL1TH1x', '/XuL1TH1x/index.m3u8', '/XuL1TH1x/1.jpg', '网红美少女柚子猫，雷姆从零开始的采精生活', '网红美少女柚子猫，雷姆从零开始的采精生活-en', '网红美少女柚子猫，雷姆从零开始的采精生活-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '一手自拍', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100182, 'OyA6L8RS', '/OyA6L8RS/index.m3u8', '/OyA6L8RS/1.jpg', '下午买春偶遇漂亮00后小姐姐', '下午买春偶遇漂亮00后小姐姐-en', '下午买春偶遇漂亮00后小姐姐-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '偷情王哥', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100183, 'vnHH1uzf', '/vnHH1uzf/index.m3u8', '/vnHH1uzf/1.jpg', '字母圈反差婊00后良家小妹，淫贱一整夜情趣酒店调教', '字母圈反差婊00后良家小妹，淫贱一整夜情趣酒店调教-en', '字母圈反差婊00后良家小妹，淫贱一整夜情趣酒店调教-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '偷情王哥', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100184, 'jknBGrbw', '/jknBGrbw/index.m3u8', '/jknBGrbw/1.jpg', '老板爆肏性感金发秘书', '老板爆肏性感金发秘书-en', '老板爆肏性感金发秘书-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 1, 1, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100185, 'DIbiFHnk', '/DIbiFHnk/index.m3u8', '/DIbiFHnk/1.jpg', '在度假饭店粗暴性交', '在度假饭店粗暴性交-en', '在度假饭店粗暴性交-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 1, 1, 0, 'Morgpie', 1, 0, '2023-03-07 15:40:31', '2023-03-27 19:27:35', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100186, 'dxewgMSO', '/dxewgMSO/index.m3u8', '/dxewgMSO/1.jpg', '在外是纯洁女孩在家是饥渴荡妇', '在外是纯洁女孩在家是饥渴荡妇-en', '在外是纯洁女孩在家是饥渴荡妇-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 1, 1, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100187, 'lfEFsITi', '/lfEFsITi/index.m3u8', '/lfEFsITi/1.jpg', '趁女友玩游戏内射她的紧致骚逼', '趁女友玩游戏内射她的紧致骚逼-en', '趁女友玩游戏内射她的紧致骚逼-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 1, 1, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100188, 'zjN3sz0b', '/zjN3sz0b/index.m3u8', '/zjN3sz0b/1.jpg', '妹妹是任我抽插的玩物', '妹妹是任我抽插的玩物-en', '妹妹是任我抽插的玩物-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 1, 1, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100189, 'D51k87iS', '/D51k87iS/index.m3u8', '/D51k87iS/1.jpg', '巨乳妹妹裸睡让我忍不住上了', '巨乳妹妹裸睡让我忍不住上了-en', '巨乳妹妹裸睡让我忍不住上了-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 1, 1, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100190, 'Na1b7Lo9', '/Na1b7Lo9/index.m3u8', '/Na1b7Lo9/1.jpg', '我们的性爱纪录片', '我们的性爱纪录片-en', '我们的性爱纪录片-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 1, 1, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100191, 'KAuEqKZr', '/KAuEqKZr/index.m3u8', '/KAuEqKZr/1.jpg', '进房看到性感美臀女孩你会怎么做?', '进房看到性感美臀女孩你会怎么做?-en', '进房看到性感美臀女孩你会怎么做?-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 1, 1, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100192, 'vpTSF1mg', '/vpTSF1mg/index.m3u8', '/vpTSF1mg/1.jpg', '女友打游戏不理我只能干她了', '女友打游戏不理我只能干她了-en', '女友打游戏不理我只能干她了-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 1, 1, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100193, 'Ql34Fun2', '/Ql34Fun2/index.m3u8', '/Ql34Fun2/1.jpg', '女房客以为我是她的男朋友', '女房客以为我是她的男朋友-en', '女房客以为我是她的男朋友-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 1, 1, 0, 'Morgpie', 1, 0, '2023-03-07 15:40:31', '2023-03-23 22:10:13', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100194, 'nSJAHHW0', '/nSJAHHW0/index.m3u8', '/nSJAHHW0/1.jpg', '女友玩游戏不理我只好用肉棒惩罚', '女友玩游戏不理我只好用肉棒惩罚-en', '女友玩游戏不理我只好用肉棒惩罚-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 1, 1, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100195, 'NiDJ8MJb', '/NiDJ8MJb/index.m3u8', '/NiDJ8MJb/1.jpg', '爸爸把女儿的小骚逼灌满精液', '爸爸把女儿的小骚逼灌满精液-en', '爸爸把女儿的小骚逼灌满精液-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 1, 1, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100196, 'JHQJdzAD', '/JHQJdzAD/index.m3u8', '/JHQJdzAD/1.jpg', '性感女孩被肥大叔抽插高潮', '性感女孩被肥大叔抽插高潮-en', '性感女孩被肥大叔抽插高潮-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 1, 1, 0, 'Morgpie', 81, 0, '2023-03-07 15:40:31', '2023-03-21 22:09:50', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100197, 'AcECxy5T', '/AcECxy5T/index.m3u8', '/AcECxy5T/1.jpg', '浪荡女儿偷偷诱惑男人回家', '浪荡女儿偷偷诱惑男人回家-en', '浪荡女儿偷偷诱惑男人回家-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 1, 1, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100198, 'gkzheKhQ', '/gkzheKhQ/index.m3u8', '/gkzheKhQ/1.jpg', '借住朋友家偷偷跟他女友来一发', '借住朋友家偷偷跟他女友来一发-en', '借住朋友家偷偷跟他女友来一发-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 1, 1, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100199, 'zXgfaD1z', '/zXgfaD1z/index.m3u8', '/zXgfaD1z/1.jpg', '趁男友不在家找男人来做爱', '趁男友不在家找男人来做爱-en', '趁男友不在家找男人来做爱-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 1, 1, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100200, 'C4i0gL0k', '/C4i0gL0k/index.m3u8', '/C4i0gL0k/1.jpg', '荡妇深喉口交把精液全吞下', '荡妇深喉口交把精液全吞下-en', '荡妇深喉口交把精液全吞下-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 1, 1, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100201, 'JoWvkbuS', '/JoWvkbuS/index.m3u8', '/JoWvkbuS/1.jpg', '性感金发尤物舔棒完主动骑上肉棒', '性感金发尤物舔棒完主动骑上肉棒-en', '性感金发尤物舔棒完主动骑上肉棒-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 1, 1, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100202, 'G5IbR0bk', '/G5IbR0bk/index.m3u8', '/G5IbR0bk/1.jpg', '跟性感宝贝起洗澡清洁肉棒', '跟性感宝贝起洗澡清洁肉棒-en', '跟性感宝贝起洗澡清洁肉棒-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 1, 1, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100203, 'LPMOax9C', '/LPMOax9C/index.m3u8', '/LPMOax9C/1.jpg', '一不小心和我的男闺蜜来了一发', '一不小心和我的男闺蜜来了一发-en', '一不小心和我的男闺蜜来了一发-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 1, 1, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100204, 'lVPchT32', '/lVPchT32/index.m3u8', '/lVPchT32/1.jpg', '性感小姐姐被我完到小穴全湿', '性感小姐姐被我完到小穴全湿-en', '性感小姐姐被我完到小穴全湿-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 1, 1, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100205, '22g8qLO7', '/22g8qLO7/index.m3u8', '/22g8qLO7/1.jpg', '乖女孩就该主动蹲下来口交', '乖女孩就该主动蹲下来口交-en', '乖女孩就该主动蹲下来口交-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 1, 1, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100206, 'm1G8rSp9', '/m1G8rSp9/index.m3u8', '/m1G8rSp9/1.jpg', '处女拍卖师 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100207, 'UUf21LuP', '/UUf21LuP/index.m3u8', '/UUf21LuP/1.jpg', 'boin 巨乳教学 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100208, 'qQaeludO', '/qQaeludO/index.m3u8', '/qQaeludO/1.jpg', '跟大姐姐一起做吧！ 5', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100209, 'tskCEVRv', '/tskCEVRv/index.m3u8', '/tskCEVRv/1.jpg', '跟大姐姐一起做吧！ 4', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100210, 'De0IRYf5', '/De0IRYf5/index.m3u8', '/De0IRYf5/1.jpg', 'boin 巨乳教学 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100211, 'EgRWjYcD', '/EgRWjYcD/index.m3u8', '/EgRWjYcD/1.jpg', '处女拍卖师 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100212, 'bLKVzsAd', '/bLKVzsAd/index.m3u8', '/bLKVzsAd/1.jpg', '肉嫁 ～高柳家的人们～ 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100213, 'RNkHqEJ7', '/RNkHqEJ7/index.m3u8', '/RNkHqEJ7/1.jpg', '肉嫁 ～高柳家的人们～ 3', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100214, 'NUewPfvU', '/NUewPfvU/index.m3u8', '/NUewPfvU/1.jpg', '让人在意的室友 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100215, '9ztG3ph0', '/9ztG3ph0/index.m3u8', '/9ztG3ph0/1.jpg', '跟大姐姐一起做吧！ 3', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100216, 'MPQ1IKeA', '/MPQ1IKeA/index.m3u8', '/MPQ1IKeA/1.jpg', '痴母 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100217, 'D67Fw2W0', '/D67Fw2W0/index.m3u8', '/D67Fw2W0/1.jpg', '痴母 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100218, 'sbI4lA7b', '/sbI4lA7b/index.m3u8', '/sbI4lA7b/1.jpg', '凌辱人妻温泉 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100219, '8UI1VOHd', '/8UI1VOHd/index.m3u8', '/8UI1VOHd/1.jpg', '跟大姐姐一起做吧！ 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100220, '3UyBpReM', '/3UyBpReM/index.m3u8', '/3UyBpReM/1.jpg', '坎布里亚 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100221, 'GwHSqxR1', '/GwHSqxR1/index.m3u8', '/GwHSqxR1/1.jpg', '人妻小霞 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100222, 'gDBuCG30', '/gDBuCG30/index.m3u8', '/gDBuCG30/1.jpg', '让人在意的室友 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100223, 'OA96KAXQ', '/OA96KAXQ/index.m3u8', '/OA96KAXQ/1.jpg', 'MAID iN HEAVEN SuperS 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100224, 'p8AaYmbw', '/p8AaYmbw/index.m3u8', '/p8AaYmbw/1.jpg', '肉嫁 ～高柳家的人们～ 4', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100225, 'VVRWSGpK', '/VVRWSGpK/index.m3u8', '/VVRWSGpK/1.jpg', '凌辱人妻温泉 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100226, 'Br1MO2L0', '/Br1MO2L0/index.m3u8', '/Br1MO2L0/1.jpg', 'MAID iN HEAVEN SuperS 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100227, 'NDPHiVVX', '/NDPHiVVX/index.m3u8', '/NDPHiVVX/1.jpg', '跟大姐姐一起做吧！ 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100228, 'eU2dk8ZL', '/eU2dk8ZL/index.m3u8', '/eU2dk8ZL/1.jpg', '坎布里亚 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100229, 'gd6KARQU', '/gd6KARQU/index.m3u8', '/gd6KARQU/1.jpg', '肉嫁 ～高柳家的人们～ 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100230, 'AiqMu3Xa', '/AiqMu3Xa/index.m3u8', '/AiqMu3Xa/1.jpg', '人妻小霞 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100231, 'BPkWwxUc', '/BPkWwxUc/index.m3u8', '/BPkWwxUc/1.jpg', '三名壮汉调教爆操21岁美少女 XRL-010', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 0, 0, 0, '白濑飞鸟', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100232, 'BfixKCzy', '/BfixKCzy/index.m3u8', '/BfixKCzy/1.jpg', '喜欢乘骑位榨取男人精液的小恶魔 WAAA-084', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 12, '月野霞', NULL, NULL, 0, 0, 0, '月野霞', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100233, '0bvtjpFf', '/0bvtjpFf/index.m3u8', '/0bvtjpFf/1.jpg', '两个高中生初次体验性交的快感 WAAA-125', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 12, '月野霞', NULL, NULL, 0, 0, 0, '月野霞', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100234, 'S1Ps0tWz', '/S1Ps0tWz/index.m3u8', '/S1Ps0tWz/1.jpg', '高中生妹子被工人们集体轮奸 APNS-237', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 0, 0, 0, '白濑飞鸟', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100235, 'fluKE9St', '/fluKE9St/index.m3u8', '/fluKE9St/1.jpg', '饥渴难耐的学生妹子叫来发小爆插骚穴 HND-980', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 0, 0, 0, '白濑飞鸟', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100236, 'SlFsXExS', '/SlFsXExS/index.m3u8', '/SlFsXExS/1.jpg', '外表清纯的妹子被几个男人玩弄肉体爽翻天 TPPN-185', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 0, 0, 0, '白濑飞鸟', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100237, 'LBr8VSzO', '/LBr8VSzO/index.m3u8', '/LBr8VSzO/1.jpg', '美少女沦为变态教授的性玩具 MTALL-023', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 12, '月野霞', NULL, NULL, 0, 0, 0, '月野霞', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100238, 'uW47zz9t', '/uW47zz9t/index.m3u8', '/uW47zz9t/1.jpg', '性欲旺盛的痴女需要两根肉棒才能满足骚穴 SDAB-157', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 0, 0, 0, '白濑飞鸟', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100239, 'BRX21o8k', '/BRX21o8k/index.m3u8', '/BRX21o8k/1.jpg', '离家出走的美少女被大叔带回家成为泄欲工具 MDS-892', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 0, 0, 0, '白濑飞鸟', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100240, '7uyh9apS', '/7uyh9apS/index.m3u8', '/7uyh9apS/1.jpg', '全裸女佣连雇主的性欲也能一起解决 HDKA-256', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 12, '月野霞', NULL, NULL, 0, 0, 0, '月野霞', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100241, '8AopgMAv', '/8AopgMAv/index.m3u8', '/8AopgMAv/1.jpg', '美少女诱惑自己的叔叔开房干炮 LOEX-001', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 0, 0, 0, '白濑飞鸟', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100242, 'VW5lwgqZ', '/VW5lwgqZ/index.m3u8', '/VW5lwgqZ/1.jpg', '极品女优精彩场景合集 PPBD-232（下）', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 12, '月野霞', NULL, NULL, 0, 0, 0, '月野霞', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100243, 'stydVv9m', '/stydVv9m/index.m3u8', '/stydVv9m/1.jpg', '超淫荡的美少女酒店索求肉棒的抽插 APKH-170', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 0, 0, 0, '白濑飞鸟', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100244, 'eInJvbXU', '/eInJvbXU/index.m3u8', '/eInJvbXU/1.jpg', '为了赚钱而接受好色店长性处理业务的高中妹子 PPPD-990', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 12, '月野霞', NULL, NULL, 0, 0, 0, '月野霞', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100245, '4Dx6BKxa', '/4Dx6BKxa/index.m3u8', '/4Dx6BKxa/1.jpg', '玩弄痴女的乳头让她高潮潮吹不止 MTALL-011', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 12, '月野霞', NULL, NULL, 0, 0, 0, '月野霞', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100246, 'cNFnfT8l', '/cNFnfT8l/index.m3u8', '/cNFnfT8l/1.jpg', '巨乳搜查官被罪犯捆绑强奸 BEFG-006', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 12, '月野霞', NULL, NULL, 0, 0, 0, '月野霞', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100247, 'hokqHLSG', '/hokqHLSG/index.m3u8', '/hokqHLSG/1.jpg', '巨乳人妻被丈夫的弟弟强暴中出 GVH-334', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 12, '月野霞', NULL, NULL, 0, 0, 0, '月野霞', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100248, 'fsqAFwir', '/fsqAFwir/index.m3u8', '/fsqAFwir/1.jpg', '咖啡店美女被客人强奸后迷恋上了客人的肉棒 HND-963', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 0, 0, 0, '白濑飞鸟', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100249, 'UDI11fQ3', '/UDI11fQ3/index.m3u8', '/UDI11fQ3/1.jpg', '学生妹子初次体验巨根抽插爽到爆 SDAB-146', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 0, 0, 0, '白濑飞鸟', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100250, 'hDpyoyZp', '/hDpyoyZp/index.m3u8', '/hDpyoyZp/1.jpg', '超市的美女店员夜班没事干一炮 MKON-054', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 0, 0, 0, '白濑飞鸟', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100251, 'NAzmTXhy', '/NAzmTXhy/index.m3u8', '/NAzmTXhy/1.jpg', '兄妹俩人在家进行性交联系 CRNX-017', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 0, 0, 0, '白濑飞鸟', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100252, 'wH3k3s09', '/wH3k3s09/index.m3u8', '/wH3k3s09/1.jpg', '欲求不满的旅店老板索求客人的大肉棒 JUFE-339', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 12, '月野霞', NULL, NULL, 0, 0, 0, '月野霞', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100253, '3khFaz7o', '/3khFaz7o/index.m3u8', '/3khFaz7o/1.jpg', '美少女被中年按摩大叔玩弄子宫口 SDAB-161', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 0, 0, 0, '白濑飞鸟', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100254, 'JuKDuUAD', '/JuKDuUAD/index.m3u8', '/JuKDuUAD/1.jpg', '中年大叔与朋友的女儿酒店约炮老牛吃嫩草 MIAA-413', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 0, 0, 0, '白濑飞鸟', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100255, '0tlaQPQa', '/0tlaQPQa/index.m3u8', '/0tlaQPQa/1.jpg', '马上要结婚的女友是个淫荡的女优 HMN-097', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 12, '月野霞', NULL, NULL, 0, 0, 0, '月野霞', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100256, 'TdillGxI', '/TdillGxI/index.m3u8', '/TdillGxI/1.jpg', '20岁现役大学生首次拍摄AV稚嫩的表演不容错过 SDAB-141', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 0, 0, 0, '白濑飞鸟', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100257, 'j82kzXBo', '/j82kzXBo/index.m3u8', '/j82kzXBo/1.jpg', '极品女优精彩场景合集 PPBD-232（上）', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 12, '月野霞', NULL, NULL, 0, 0, 0, '月野霞', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100258, 'PAFsGeDJ', '/PAFsGeDJ/index.m3u8', '/PAFsGeDJ/1.jpg', '好色技师按摩痴女的白嫩巨乳让她爽到抽搐 PPPD-955', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 12, '月野霞', NULL, NULL, 0, 0, 0, '月野霞', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100259, 'zrS5qrJk', '/zrS5qrJk/index.m3u8', '/zrS5qrJk/1.jpg', '性感学生妹子裸体写真 REBD-538', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 0, 0, 0, '白濑飞鸟', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100260, 'VqNJkxYL', '/VqNJkxYL/index.m3u8', '/VqNJkxYL/1.jpg', '超爱性交的美少女展现痴女的模样 MMUS-053', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 0, 0, 0, '白濑飞鸟', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100261, 'vrqVW3Gx', '/vrqVW3Gx/index.m3u8', '/vrqVW3Gx/1.jpg', '美少女遭到继父的持续凌辱 SHKD-947', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 0, 0, 0, '白濑飞鸟', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100262, 'FMVAo6Vl', '/FMVAo6Vl/index.m3u8', '/FMVAo6Vl/1.jpg', '年轻的老师没有忍住小恶魔的诱惑背叛了自己的妻子 CJOD-288', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 0, 0, 0, '白濑飞鸟', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100263, 'zCm1lNJX', '/zCm1lNJX/index.m3u8', '/zCm1lNJX/1.jpg', '变态老师给学生下药后持续调教抽插美少女 DDHH-028', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 0, 0, 0, '白濑飞鸟', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100264, 'nP1hTxdc', '/nP1hTxdc/index.m3u8', '/nP1hTxdc/1.jpg', '超喜欢口交的美少女每天都要吞吐好几根肉棒 SDAB-150', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 0, 0, 0, '白濑飞鸟', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100265, '9JOW9URy', '/9JOW9URy/index.m3u8', '/9JOW9URy/1.jpg', '刚交到的女友原来是一个放荡的痴女 APNS-247', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 0, 0, 0, '白濑飞鸟', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100266, '5N3J3Uij', '/5N3J3Uij/index.m3u8', '/5N3J3Uij/1.jpg', '学生妹子被教练强暴凌辱 SHKD-932', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 0, 0, 0, '白濑飞鸟', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100267, 'zijnKY2z', '/zijnKY2z/index.m3u8', '/zijnKY2z/1.jpg', '小恶魔般的美少女袭击好色大叔的肉棒 MIAA-419', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 0, 0, 0, '白濑飞鸟', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100268, 'WMtvBEzv', '/WMtvBEzv/index.m3u8', '/WMtvBEzv/1.jpg', '巨乳学生妹子成了好色老师的性奴隶 WAAA-093', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 12, '月野霞', NULL, NULL, 0, 0, 0, '月野霞', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100269, 'rCwGPbZ3', '/rCwGPbZ3/index.m3u8', '/rCwGPbZ3/1.jpg', '与女友在家激情性交一整天 YMDS-022', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 0, 0, 0, '白濑飞鸟', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100270, 'RV4dJMxN', '/RV4dJMxN/index.m3u8', '/RV4dJMxN/1.jpg', '最高级的学生妹子为你进行性交按摩 IENF-128', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 63, '白濑飞鸟', NULL, NULL, 0, 0, 0, '白濑飞鸟', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100271, 'mJw9MgCN', '/mJw9MgCN/index.m3u8', '/mJw9MgCN/1.jpg', '约了卫校的淫荡校鸡看她自慰', '约了卫校的淫荡校鸡看她自慰-en', '约了卫校的淫荡校鸡看她自慰-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100272, 'ZAB07xfJ', '/ZAB07xfJ/index.m3u8', '/ZAB07xfJ/1.jpg', '卖力热舞的小学妹酥胸摇晃', '卖力热舞的小学妹酥胸摇晃-en', '卖力热舞的小学妹酥胸摇晃-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100273, 'WWXaM6ps', '/WWXaM6ps/index.m3u8', '/WWXaM6ps/1.jpg', '真诱惑还得看开档黑丝', '真诱惑还得看开档黑丝-en', '真诱惑还得看开档黑丝-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100274, '396mpjy0', '/396mpjy0/index.m3u8', '/396mpjy0/1.jpg', '翘臀小可爱牛仔裤完美勾勒轮廓', '翘臀小可爱牛仔裤完美勾勒轮廓-en', '翘臀小可爱牛仔裤完美勾勒轮廓-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100275, '7Rxf4nwu', '/7Rxf4nwu/index.m3u8', '/7Rxf4nwu/1.jpg', '酒吧女玩的够花的逼逼都成黑木耳了', '酒吧女玩的够花的逼逼都成黑木耳了-en', '酒吧女玩的够花的逼逼都成黑木耳了-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100276, 'DP0UFyH5', '/DP0UFyH5/index.m3u8', '/DP0UFyH5/1.jpg', '年轻美女奢靡淫乱的夜生活', '年轻美女奢靡淫乱的夜生活-en', '年轻美女奢靡淫乱的夜生活-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100277, '7ZOVQIhl', '/7ZOVQIhl/index.m3u8', '/7ZOVQIhl/1.jpg', '拿什么吸引你呢我只是一个爱跳舞的骚比', '拿什么吸引你呢我只是一个爱跳舞的骚比-en', '拿什么吸引你呢我只是一个爱跳舞的骚比-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100278, 'midQ1Y7z', '/midQ1Y7z/index.m3u8', '/midQ1Y7z/1.jpg', '微醺状态下的妹子逼逼洞口微微张开', '微醺状态下的妹子逼逼洞口微微张开-en', '微醺状态下的妹子逼逼洞口微微张开-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100279, 'x4po0XE6', '/x4po0XE6/index.m3u8', '/x4po0XE6/1.jpg', '身材那么好不拿来爆操就太可惜了', '身材那么好不拿来爆操就太可惜了-en', '身材那么好不拿来爆操就太可惜了-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100280, 'VOPHQCLZ', '/VOPHQCLZ/index.m3u8', '/VOPHQCLZ/1.jpg', '就这屁股你能扛得住几次', '就这屁股你能扛得住几次-en', '就这屁股你能扛得住几次-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 1, 0, '2023-03-07 15:40:31', '2023-03-17 15:20:07', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100281, 'waYuJrBl', '/waYuJrBl/index.m3u8', '/waYuJrBl/1.jpg', '放假宿舍没人妹子表演校园春色', '放假宿舍没人妹子表演校园春色-en', '放假宿舍没人妹子表演校园春色-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100282, 'FG9WbAg5', '/FG9WbAg5/index.m3u8', '/FG9WbAg5/1.jpg', '纯欲少女这又是谁的青春', '纯欲少女这又是谁的青春-en', '纯欲少女这又是谁的青春-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 1, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100283, 't7Zg5K2r', '/t7Zg5K2r/index.m3u8', '/t7Zg5K2r/1.jpg', '甜美风骚的人间小尤物', '甜美风骚的人间小尤物-en', '甜美风骚的人间小尤物-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100284, 'qEUNKdlF', '/qEUNKdlF/index.m3u8', '/qEUNKdlF/1.jpg', '游艇宝贝接不到单只能在家自慰', '游艇宝贝接不到单只能在家自慰-en', '游艇宝贝接不到单只能在家自慰-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100285, '0922EWlO', '/0922EWlO/index.m3u8', '/0922EWlO/1.jpg', '梨形身材少妇情趣黑丝腿上穿极品诱惑', '梨形身材少妇情趣黑丝腿上穿极品诱惑-en', '梨形身材少妇情趣黑丝腿上穿极品诱惑-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100286, 'bo2ZW1ZD', '/bo2ZW1ZD/index.m3u8', '/bo2ZW1ZD/1.jpg', '小骚逼一天不自慰浑身难受', '小骚逼一天不自慰浑身难受-en', '小骚逼一天不自慰浑身难受-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100287, '46tZCdbQ', '/46tZCdbQ/index.m3u8', '/46tZCdbQ/1.jpg', '绿茶美女装矜持看到钱立马发骚', '绿茶美女装矜持看到钱立马发骚-en', '绿茶美女装矜持看到钱立马发骚-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100288, 'vUTYwpCJ', '/vUTYwpCJ/index.m3u8', '/vUTYwpCJ/1.jpg', '性感嘟嘟唇少女令人无限遐想', '性感嘟嘟唇少女令人无限遐想-en', '性感嘟嘟唇少女令人无限遐想-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 1, 0, '2023-03-07 15:40:31', '2023-03-18 19:34:03', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100289, '0kUvvUQj', '/0kUvvUQj/index.m3u8', '/0kUvvUQj/1.jpg', '妹子贴身教你跳舞', '妹子贴身教你跳舞-en', '妹子贴身教你跳舞-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100290, 'cfZdbK4x', '/cfZdbK4x/index.m3u8', '/cfZdbK4x/1.jpg', '体操服少女半脱裤子求操', '体操服少女半脱裤子求操-en', '体操服少女半脱裤子求操-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100291, 'KUeoWrHT', '/KUeoWrHT/index.m3u8', '/KUeoWrHT/1.jpg', '是我不够骚吗不然怎么没进入你的心', '是我不够骚吗不然怎么没进入你的心-en', '是我不够骚吗不然怎么没进入你的心-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 1, 0, '2023-03-07 15:40:31', '2023-03-27 13:40:38', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100292, '7jfB0gt1', '/7jfB0gt1/index.m3u8', '/7jfB0gt1/1.jpg', '老婆学了瑜伽非要用高难度姿势', '老婆学了瑜伽非要用高难度姿势-en', '老婆学了瑜伽非要用高难度姿势-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100293, 'rCftmzF2', '/rCftmzF2/index.m3u8', '/rCftmzF2/1.jpg', '妹子直播太辛苦奖励她一根肉棒', '妹子直播太辛苦奖励她一根肉棒-en', '妹子直播太辛苦奖励她一根肉棒-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 1, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100294, 'JtFS5sOH', '/JtFS5sOH/index.m3u8', '/JtFS5sOH/1.jpg', '美女玩点真实的敞开衣服来直播', '美女玩点真实的敞开衣服来直播-en', '美女玩点真实的敞开衣服来直播-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100295, 'V1dxlLjH', '/V1dxlLjH/index.m3u8', '/V1dxlLjH/1.jpg', '韵味少妇永远的神', '韵味少妇永远的神-en', '韵味少妇永远的神-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100296, '4uhUhb4W', '/4uhUhb4W/index.m3u8', '/4uhUhb4W/1.jpg', '你愿意当脱衣舞女的那根钢管吗', '你愿意当脱衣舞女的那根钢管吗-en', '你愿意当脱衣舞女的那根钢管吗-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100297, 'aFBTYsbA', '/aFBTYsbA/index.m3u8', '/aFBTYsbA/1.jpg', '醉酒女人的深夜自嗨', '醉酒女人的深夜自嗨-en', '醉酒女人的深夜自嗨-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100298, '9tVBOLba', '/9tVBOLba/index.m3u8', '/9tVBOLba/1.jpg', '职业技术学院的年轻小妹妹笑起来真美', '职业技术学院的年轻小妹妹笑起来真美-en', '职业技术学院的年轻小妹妹笑起来真美-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100299, 'fQf6ZgyW', '/fQf6ZgyW/index.m3u8', '/fQf6ZgyW/1.jpg', '鉴定完毕辣妹的粉逼都是辣的', '鉴定完毕辣妹的粉逼都是辣的-en', '鉴定完毕辣妹的粉逼都是辣的-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100300, 'FioUdTYm', '/FioUdTYm/index.m3u8', '/FioUdTYm/1.jpg', '清纯美女翘起屁屁求插菊', '清纯美女翘起屁屁求插菊-en', '清纯美女翘起屁屁求插菊-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100301, 'duaQI09c', '/duaQI09c/index.m3u8', '/duaQI09c/1.jpg', '什么时候你才能来操操这样的坏女人啊', '什么时候你才能来操操这样的坏女人啊-en', '什么时候你才能来操操这样的坏女人啊-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 2, 0, '2023-03-07 15:40:31', '2023-03-17 13:39:51', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100302, 'mpbRWCo8', '/mpbRWCo8/index.m3u8', '/mpbRWCo8/1.jpg', '论当代女大学生有多饥渴', '论当代女大学生有多饥渴-en', '论当代女大学生有多饥渴-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 123, 1, '2023-03-07 15:40:31', '2023-03-27 19:46:10', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100303, '6kD749Xe', '/6kD749Xe/index.m3u8', '/6kD749Xe/1.jpg', '钟爱丁字裤的隔壁小姐姐', '钟爱丁字裤的隔壁小姐姐-en', '钟爱丁字裤的隔壁小姐姐-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 41, 1, '2023-03-07 15:40:31', '2023-03-27 19:47:47', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100304, 'yEypCuQP', '/yEypCuQP/index.m3u8', '/yEypCuQP/1.jpg', '发了骚的妹子太主动勾引小哥舔嫩逼', '发了骚的妹子太主动勾引小哥舔嫩逼-en', '发了骚的妹子太主动勾引小哥舔嫩逼-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 19, 1, '2023-03-07 15:40:31', '2023-03-28 13:45:43', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100305, 'unJ5enev', '/unJ5enev/index.m3u8', '/unJ5enev/1.jpg', '舞蹈热身再自慰的艺术学院学妹', '舞蹈热身再自慰的艺术学院学妹-en', '舞蹈热身再自慰的艺术学院学妹-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100306, 'O5CqBuy0', '/O5CqBuy0/index.m3u8', '/O5CqBuy0/1.jpg', '白T少妇穿上蕾丝内裤骚舞', '白T少妇穿上蕾丝内裤骚舞-en', '白T少妇穿上蕾丝内裤骚舞-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100307, '7Q1SFYJ8', '/7Q1SFYJ8/index.m3u8', '/7Q1SFYJ8/1.jpg', '极致诱惑之慢摇脱衣舞', '极致诱惑之慢摇脱衣舞-en', '极致诱惑之慢摇脱衣舞-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 1, 0, '2023-03-07 15:40:31', '2023-03-17 15:17:39', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100308, 'NDqEtUfl', '/NDqEtUfl/index.m3u8', '/NDqEtUfl/1.jpg', '美女想男人想疯了天天喊着让人操她', '美女想男人想疯了天天喊着让人操她-en', '美女想男人想疯了天天喊着让人操她-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 4, 0, '2023-03-07 15:40:31', '2023-03-18 21:15:10', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100309, 'K7MnQwQs', '/K7MnQwQs/index.m3u8', '/K7MnQwQs/1.jpg', '又到了每日一看的抠逼环节', '又到了每日一看的抠逼环节-en', '又到了每日一看的抠逼环节-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 61, 1, '2023-03-07 15:40:31', '2023-03-27 19:47:12', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100310, 'P9em1WSy', '/P9em1WSy/index.m3u8', '/P9em1WSy/1.jpg', '白嫩辣妹还不能让你心动吗', '白嫩辣妹还不能让你心动吗-en', '白嫩辣妹还不能让你心动吗-kh', 0, 0, 0, 0, '01:00:00', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 1, 1, 0, '口罩系列', 6, 0, '2023-03-07 15:40:31', '2023-03-23 22:16:51', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100311, 'kBmONb3R', '/kBmONb3R/index.m3u8', '/kBmONb3R/1.jpg', '淫荡的瑜伽老师小嘴巴差点塞不下肉棒 HEYZO-1377', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 29, '美月瑠奈', NULL, NULL, 0, 0, 0, '美月瑠奈', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100312, 'JTcVwN4E', '/JTcVwN4E/index.m3u8', '/JTcVwN4E/1.jpg', '职业裙美女双乳颤动 061119_001', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 44, '北山柑菜', NULL, NULL, 0, 0, 0, '北山柑菜', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100313, 'iN20idhX', '/iN20idhX/index.m3u8', '/iN20idhX/1.jpg', '丝袜熟女群P淫荡交合 xxx-av 24160（下）', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 13, '北岛玲', NULL, NULL, 0, 0, 0, '北岛玲', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100314, 'siXyi3F1', '/siXyi3F1/index.m3u8', '/siXyi3F1/1.jpg', 'xxx-av 24207', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 54, '音羽蕾恩', NULL, NULL, 0, 0, 0, '音羽蕾恩', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100315, 'eyE78H4A', '/eyE78H4A/index.m3u8', '/eyE78H4A/1.jpg', '可怜的小嫩屄被玩具玩弄的红彤彤 090716-001', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 28, '黑羽美里', NULL, NULL, 0, 0, 0, '黑羽美里', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100316, 'punSlH5O', '/punSlH5O/index.m3u8', '/punSlH5O/1.jpg', '性感内衣美女多姿势抽插 100819-001', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 13, '北岛玲', NULL, NULL, 0, 0, 0, '北岛玲', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100317, 'Tn9ce4fK', '/Tn9ce4fK/index.m3u8', '/Tn9ce4fK/1.jpg', '熟女的嫩屄湿嗒嗒超级诱人 SKYHD-143', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 13, '北岛玲', NULL, NULL, 0, 0, 0, '北岛玲', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100318, 'I913Y4OL', '/I913Y4OL/index.m3u8', '/I913Y4OL/1.jpg', '白嫩美臀美女温泉中被肉棒塞满 CWPBD-117', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 9, '川村真矢', NULL, NULL, 0, 0, 0, '川村真矢', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100319, 'kcWP0m3s', '/kcWP0m3s/index.m3u8', '/kcWP0m3s/1.jpg', '淫荡的母狗需要两根大肉棒上下齐插 xxx-av 24208', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 54, '音羽蕾恩', NULL, NULL, 0, 0, 0, '音羽蕾恩', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100320, 'ZG0TOZp4', '/ZG0TOZp4/index.m3u8', '/ZG0TOZp4/1.jpg', '多种道具玩弄粉逼自慰中的骚货 090116-001', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 15, '结川悠', NULL, NULL, 0, 0, 0, '结川悠', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100321, '2sAggk2f', '/2sAggk2f/index.m3u8', '/2sAggk2f/1.jpg', '大汉围攻美乳女模颜射滚烫精液 092617-506', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 29, '美月瑠奈', NULL, NULL, 0, 0, 0, '美月瑠奈', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100322, 'vW5kkV0g', '/vW5kkV0g/index.m3u8', '/vW5kkV0g/1.jpg', '爆操放学后的淫荡美少女 LAFBD-44', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 9, '川村真矢', NULL, NULL, 0, 0, 0, '川村真矢', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100323, 'i478XU2N', '/i478XU2N/index.m3u8', '/i478XU2N/1.jpg', '美女上下两张小嘴都快忙不过来了 MCDV-29', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 29, '美月瑠奈', NULL, NULL, 0, 0, 0, '美月瑠奈', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100324, 'YME4EAlr', '/YME4EAlr/index.m3u8', '/YME4EAlr/1.jpg', '野外营地操弄白嫩美人 LAFBD-64', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 32, '美神彩', NULL, NULL, 0, 0, 0, '美神彩', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100325, '9PFlkydB', '/9PFlkydB/index.m3u8', '/9PFlkydB/1.jpg', '粉嫩木耳熟女淫荡自慰 032416-124', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 44, '北山柑菜', NULL, NULL, 0, 0, 0, '北山柑菜', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100326, 'X6oUGvaN', '/X6oUGvaN/index.m3u8', '/X6oUGvaN/1.jpg', '被男友的好友安慰我那里 111018-790', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 29, '美月瑠奈', NULL, NULL, 0, 0, 0, '美月瑠奈', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100327, 'xBt3oFtP', '/xBt3oFtP/index.m3u8', '/xBt3oFtP/1.jpg', '气质美女自慰白浆流出 032217-399', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 18, '甲斐美晴', NULL, NULL, 0, 0, 0, '甲斐美晴', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100328, 'vVfylG0b', '/vVfylG0b/index.m3u8', '/vVfylG0b/1.jpg', '气质熟女肥鲍爱吃肉棒 CWPBD-75', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 13, '北岛玲', NULL, NULL, 0, 0, 0, '北岛玲', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100329, 'i36KAdLa', '/i36KAdLa/index.m3u8', '/i36KAdLa/1.jpg', '122018-814', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 55, '初音露莉亚', NULL, NULL, 0, 0, 0, '初音露莉亚', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100330, 'aOW7nyHv', '/aOW7nyHv/index.m3u8', '/aOW7nyHv/1.jpg', '美女张开双腿被人舔弄骚逼 051918_689', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 29, '美月瑠奈', NULL, NULL, 0, 0, 0, '美月瑠奈', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100331, 'rdDiHZ0B', '/rdDiHZ0B/index.m3u8', '/rdDiHZ0B/1.jpg', '骑乘爆操性感身材熟女 SKYHD-146', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 54, '音羽蕾恩', NULL, NULL, 0, 0, 0, '音羽蕾恩', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100332, 'Ue0wIyOQ', '/Ue0wIyOQ/index.m3u8', '/Ue0wIyOQ/1.jpg', '大型淫荡喷尿现场羞耻至极 n1205', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 66, '铃木里绪奈', NULL, NULL, 0, 0, 0, '铃木里绪奈', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100333, 'PoaEy5Lm', '/PoaEy5Lm/index.m3u8', '/PoaEy5Lm/1.jpg', '享受极致的按摩快感吧 HEYZO-2646', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 13, '北岛玲', NULL, NULL, 0, 0, 0, '北岛玲', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100334, 'BeHdi7Cw', '/BeHdi7Cw/index.m3u8', '/BeHdi7Cw/1.jpg', '小美女站着被疯狂后入双乳颤动 SMBD-99', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 54, '音羽蕾恩', NULL, NULL, 0, 0, 0, '音羽蕾恩', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100335, 'bsNpfVcl', '/bsNpfVcl/index.m3u8', '/bsNpfVcl/1.jpg', 'xxx-av 24205', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 54, '音羽蕾恩', NULL, NULL, 0, 0, 0, '音羽蕾恩', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100336, 'QLNZ7ELt', '/QLNZ7ELt/index.m3u8', '/QLNZ7ELt/1.jpg', '丝袜熟女群P淫荡交合 xxx-av 24160（上）', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 13, '北岛玲', NULL, NULL, 0, 0, 0, '北岛玲', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100337, 'Nzbzl3DW', '/Nzbzl3DW/index.m3u8', '/Nzbzl3DW/1.jpg', '骚奶子嫩妹让大肉棒蠢蠢欲动 HEYZO-1709', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 44, '北山柑菜', NULL, NULL, 0, 0, 0, '北山柑菜', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100338, 'UoGETuE6', '/UoGETuE6/index.m3u8', '/UoGETuE6/1.jpg', '小护士连体丝袜被撕烂狂插 040419_001', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 44, '北山柑菜', NULL, NULL, 0, 0, 0, '北山柑菜', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100339, 'KuvttFtK', '/KuvttFtK/index.m3u8', '/KuvttFtK/1.jpg', '调教骚气豪乳美女 n1214', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 66, '铃木里绪奈', NULL, NULL, 0, 0, 0, '铃木里绪奈', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100340, 'XTsGU9I7', '/XTsGU9I7/index.m3u8', '/XTsGU9I7/1.jpg', '自慰少女终究是喜欢异性的玩具调教 011416_003', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 29, '美月瑠奈', NULL, NULL, 0, 0, 0, '美月瑠奈', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100341, 'wledODVV', '/wledODVV/index.m3u8', '/wledODVV/1.jpg', '内射完美身材痴女大肉棒插嘴 SMBD-147', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 5, '星野千纱', NULL, NULL, 0, 0, 0, '星野千纱', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100342, 'zJ9GtWN6', '/zJ9GtWN6/index.m3u8', '/zJ9GtWN6/1.jpg', '把熟女约到小床上操爽了射了一肚子 k1389', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 45, '安西美纱', NULL, NULL, 0, 0, 0, '安西美纱', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100343, 'ARx4eq3Q', '/ARx4eq3Q/index.m3u8', '/ARx4eq3Q/1.jpg', '女性私部按摩带来的快感 HEYZO-2655', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 53, '云母', NULL, NULL, 0, 0, 0, '云母', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100344, 'NiSVqIgp', '/NiSVqIgp/index.m3u8', '/NiSVqIgp/1.jpg', '031717-395', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 55, '初音露莉亚', NULL, NULL, 0, 0, 0, '初音露莉亚', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100345, 'WxdYN2np', '/WxdYN2np/index.m3u8', '/WxdYN2np/1.jpg', '举起屁股迎接大肉棒的浪女 MCBD-03', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 54, '音羽蕾恩', NULL, NULL, 0, 0, 0, '音羽蕾恩', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100346, 'MuK2yamW', '/MuK2yamW/index.m3u8', '/MuK2yamW/1.jpg', 'xxx-av 24158', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 13, '北岛玲', NULL, NULL, 0, 0, 0, '北岛玲', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100347, 'VqkvpnJW', '/VqkvpnJW/index.m3u8', '/VqkvpnJW/1.jpg', '舌头震动棒交替按摩让美女白臀乱耸 HEYZO-1849', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 29, '美月瑠奈', NULL, NULL, 0, 0, 0, '美月瑠奈', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100348, '9XuBfNxS', '/9XuBfNxS/index.m3u8', '/9XuBfNxS/1.jpg', '享受极致玩具抽插的小美人 n1197', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 66, '铃木里绪奈', NULL, NULL, 0, 0, 0, '铃木里绪奈', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100349, 'dALlHNMm', '/dALlHNMm/index.m3u8', '/dALlHNMm/1.jpg', '公开羞耻露出的骚货母狗 SMBD-152', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 43, '铃羽美羽', NULL, NULL, 0, 0, 0, '铃羽美羽', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100350, 'aiFjK9tU', '/aiFjK9tU/index.m3u8', '/aiFjK9tU/1.jpg', '难以把控的肉欲让两人69互舔狂操 xxx-av 24206', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 54, '音羽蕾恩', NULL, NULL, 0, 0, 0, '音羽蕾恩', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100351, 'b6TxbADc', '/b6TxbADc/index.m3u8', '/b6TxbADc/1.jpg', '突击妹妹洗澡猛肏小穴', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100352, 'KHdzsaLO', '/KHdzsaLO/index.m3u8', '/KHdzsaLO/1.jpg', '喜欢H漫的女友发骚用肉棒帮她止痒', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100353, '1YrZWxF4', '/1YrZWxF4/index.m3u8', '/1YrZWxF4/1.jpg', '极骚继母在家运动让我忍不住勃起', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100354, 'DcOhaNd0', '/DcOhaNd0/index.m3u8', '/DcOhaNd0/1.jpg', '给大家看看我淫荡老婆被抽插的样子', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100355, 'gejzMi1t', '/gejzMi1t/index.m3u8', '/gejzMi1t/1.jpg', '和女友挑一个空旷野外做爱', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100356, 'dfbP4Er4', '/dfbP4Er4/index.m3u8', '/dfbP4Er4/1.jpg', '抽插完巨乳荡妇强迫她舔干净', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100357, '8hRSqCSD', '/8hRSqCSD/index.m3u8', '/8hRSqCSD/1.jpg', '妹妹看着H漫自慰到高潮', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100358, 'o5JTHmJv', '/o5JTHmJv/index.m3u8', '/o5JTHmJv/1.jpg', '搭讪运动女孩带回家做活塞运动', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100359, 'Mn0DakWo', '/Mn0DakWo/index.m3u8', '/Mn0DakWo/1.jpg', '社群app上的极品女孩是我的炮友', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100360, 'bwwpYAuX', '/bwwpYAuX/index.m3u8', '/bwwpYAuX/1.jpg', '妹妹任我玩弄把裤子脱下就可以抽插', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100361, '228uromA', '/228uromA/index.m3u8', '/228uromA/1.jpg', '女友喜欢享受在野外自慰的氛围', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100362, 'G7RaJVIE', '/G7RaJVIE/index.m3u8', '/G7RaJVIE/1.jpg', '女儿偷看爸爸在做爱小穴越来越湿', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100363, 'YBTsTnJm', '/YBTsTnJm/index.m3u8', '/YBTsTnJm/1.jpg', '找巨乳女孩来我家玩深喉口交', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100364, 'Q9XdlOMh', '/Q9XdlOMh/index.m3u8', '/Q9XdlOMh/1.jpg', '和巨乳女友开房间享受窒息性爱', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100365, 'o28ooefI', '/o28ooefI/index.m3u8', '/o28ooefI/1.jpg', '淫荡母狗享受被主人调教', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100366, 'e3eAN7dO', '/e3eAN7dO/index.m3u8', '/e3eAN7dO/1.jpg', '最喜欢主动的女孩激烈扭腰', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100367, 'lSWJlfvQ', '/lSWJlfvQ/index.m3u8', '/lSWJlfvQ/1.jpg', '性感荡妇深喉咙特集', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100368, '3ZKa56aS', '/3ZKa56aS/index.m3u8', '/3ZKa56aS/1.jpg', '女室友主动跨到我身上让我舔鲍', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100369, '3MkIIQpR', '/3MkIIQpR/index.m3u8', '/3MkIIQpR/1.jpg', '喜欢看H动漫的极品女友', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100370, 'b1nelyIM', '/b1nelyIM/index.m3u8', '/b1nelyIM/1.jpg', '极品御姐cosplay开箱性玩具', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100371, 'JUbpMAW5', '/JUbpMAW5/index.m3u8', '/JUbpMAW5/1.jpg', '女友的下体运动用在性爱真有效', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100372, 'ut8hCom4', '/ut8hCom4/index.m3u8', '/ut8hCom4/1.jpg', '性感婊子喜欢被颜射', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100373, '14TzdjKs', '/14TzdjKs/index.m3u8', '/14TzdjKs/1.jpg', '可爱女友被我的肉棒肏到娇喘不断', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100374, 'pzSUvobA', '/pzSUvobA/index.m3u8', '/pzSUvobA/1.jpg', '饥渴的妹妹最喜欢趁机挑逗我', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100375, 'Bnnib3ch', '/Bnnib3ch/index.m3u8', '/Bnnib3ch/1.jpg', '性感黑丝女仆打扫家里不忘排解性欲', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100376, 'Tc3DRWJK', '/Tc3DRWJK/index.m3u8', '/Tc3DRWJK/1.jpg', '暴力抽插直到她高潮为止', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100377, 'kTOsmGEm', '/kTOsmGEm/index.m3u8', '/kTOsmGEm/1.jpg', '跳脱衣舞的韩国美女', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100378, 'T09rVf25', '/T09rVf25/index.m3u8', '/T09rVf25/1.jpg', '爆操我的远房表妹', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100379, 'FDSFDakJ', '/FDSFDakJ/index.m3u8', '/FDSFDakJ/1.jpg', '风骚的家政妇女', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100380, 'v9GYxHpW', '/v9GYxHpW/index.m3u8', '/v9GYxHpW/1.jpg', '喜欢被掐着脖子操逼的骚货', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100381, 'GXtePEMf', '/GXtePEMf/index.m3u8', '/GXtePEMf/1.jpg', '一次激情乱伦体验', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100382, 'KHtDAOmc', '/KHtDAOmc/index.m3u8', '/KHtDAOmc/1.jpg', '被同伴小混混强奸的女同学', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100383, 'S0QlV0hA', '/S0QlV0hA/index.m3u8', '/S0QlV0hA/1.jpg', '妻子被邻居连续半年中出', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100384, 'cFNNbGOH', '/cFNNbGOH/index.m3u8', '/cFNNbGOH/1.jpg', '社团的老师是我的性宠物', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100385, '2HftNjkT', '/2HftNjkT/index.m3u8', '/2HftNjkT/1.jpg', '巨乳人妻欲求不满', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100386, 's5oMs1QC', '/s5oMs1QC/index.m3u8', '/s5oMs1QC/1.jpg', '因为害怕而不敢叫出声的小萝莉', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100387, '5HI54dMo', '/5HI54dMo/index.m3u8', '/5HI54dMo/1.jpg', '大屁股骚货欲求不满', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100388, 'O6iqB9E9', '/O6iqB9E9/index.m3u8', '/O6iqB9E9/1.jpg', '一边打游戏一边让女友给我口', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100389, 'QhGy7MGT', '/QhGy7MGT/index.m3u8', '/QhGy7MGT/1.jpg', '小母狗什么姿势都喜欢', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100390, 'NWzIym7H', '/NWzIym7H/index.m3u8', '/NWzIym7H/1.jpg', '私密性爱情欲解谜', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100391, '55s4mZCD', '/55s4mZCD/index.m3u8', '/55s4mZCD/1.jpg', '无毛骚逼等在一根肉棒', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100392, 'NCHnjYim', '/NCHnjYim/index.m3u8', '/NCHnjYim/1.jpg', '火辣辣的苗条女友总是让我欲罢不能', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100393, '1vIpXUVR', '/1vIpXUVR/index.m3u8', '/1vIpXUVR/1.jpg', '白皙母狗要求被内射的一天', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100394, 'cOJh112K', '/cOJh112K/index.m3u8', '/cOJh112K/1.jpg', '短裙制服总有一款适合你', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100395, '87GrqAHc', '/87GrqAHc/index.m3u8', '/87GrqAHc/1.jpg', '接受姐姐的雪白大奶子', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100396, 'FZURDJqv', '/FZURDJqv/index.m3u8', '/FZURDJqv/1.jpg', '慢慢享受大鸡巴带来的快感吧', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100397, '4XWR6DhE', '/4XWR6DhE/index.m3u8', '/4XWR6DhE/1.jpg', '中出了女友的巨乳朋友', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100398, 'vYbC6sMT', '/vYbC6sMT/index.m3u8', '/vYbC6sMT/1.jpg', '猥琐的工人把女上司强奸了', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100399, 'gr2B3CLC', '/gr2B3CLC/index.m3u8', '/gr2B3CLC/1.jpg', '用肉棒的插入作为见面礼', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100400, 'wSIwGi4t', '/wSIwGi4t/index.m3u8', '/wSIwGi4t/1.jpg', '白虎嫩妹妹是我的菜', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100401, 'huYAvRSt', '/huYAvRSt/index.m3u8', '/huYAvRSt/1.jpg', '哥哥不要停我好爽', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100402, 'PrZOLCun', '/PrZOLCun/index.m3u8', '/PrZOLCun/1.jpg', '嫩嫩的性感小美女', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100403, 'y93o8Wcd', '/y93o8Wcd/index.m3u8', '/y93o8Wcd/1.jpg', '性感女主播跳裸体舞蹈', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100404, 'RnyDjtA9', '/RnyDjtA9/index.m3u8', '/RnyDjtA9/1.jpg', '哥哥快射进我的小穴', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100405, 'KKkJj9Yb', '/KKkJj9Yb/index.m3u8', '/KKkJj9Yb/1.jpg', '可爱小美妞太敏感', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100406, 'qJsJ3KzI', '/qJsJ3KzI/index.m3u8', '/qJsJ3KzI/1.jpg', '女仆摆动的腰部一直在勾引我', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100407, 'FLdrDFzT', '/FLdrDFzT/index.m3u8', '/FLdrDFzT/1.jpg', '把所有的精液都交给了美丽人妻', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100408, 'psVLGpOQ', '/psVLGpOQ/index.m3u8', '/psVLGpOQ/1.jpg', '白皙邻家姐姐被我爆操', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100409, 'wwWuizxn', '/wwWuizxn/index.m3u8', '/wwWuizxn/1.jpg', '御姐的骚逼里插了一根肉棒', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100410, 'lfasKAnu', '/lfasKAnu/index.m3u8', '/lfasKAnu/1.jpg', '刚约的极品小美女', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100411, 'qyFyr7tf', '/qyFyr7tf/index.m3u8', '/qyFyr7tf/1.jpg', '骚护士勾引患者', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100412, '4T1cravz', '/4T1cravz/index.m3u8', '/4T1cravz/1.jpg', '极品长腿黑丝御姐', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100413, 'ztyJPiPf', '/ztyJPiPf/index.m3u8', '/ztyJPiPf/1.jpg', '可爱的露脸小母狗', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100414, 'PG9Ngdge', '/PG9Ngdge/index.m3u8', '/PG9Ngdge/1.jpg', '追求丰满的乳房', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100415, 'bNcdZZJ8', '/bNcdZZJ8/index.m3u8', '/bNcdZZJ8/1.jpg', '雪白的皮肤细腻的质感', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100416, 'kFquAzpd', '/kFquAzpd/index.m3u8', '/kFquAzpd/1.jpg', '王心凌《爱你》纯享爱爱版！', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '王心凌《爱你》纯享爱爱版！', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100417, 'U4vcxTkj', '/U4vcxTkj/index.m3u8', '/U4vcxTkj/1.jpg', '抗拒不了的少妇诱惑', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100418, 'tCQnfGJb', '/tCQnfGJb/index.m3u8', '/tCQnfGJb/1.jpg', '在吗？进来看看逼', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100419, 'Bs116OLJ', '/Bs116OLJ/index.m3u8', '/Bs116OLJ/1.jpg', '娇滴滴的妹子下面逼逼水哒哒', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100420, 'dT9iJ4eh', '/dT9iJ4eh/index.m3u8', '/dT9iJ4eh/1.jpg', '170模特小姐姐骚舞不断', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100421, 'EdBHx9lH', '/EdBHx9lH/index.m3u8', '/EdBHx9lH/1.jpg', '骚女在家隔离把发放的黄瓜拿来自慰', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100422, 'YEkgYzWs', '/YEkgYzWs/index.m3u8', '/YEkgYzWs/1.jpg', '小蛮腰主播趴床上撅起屁屁给你看', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100423, 'Q3XDAUL5', '/Q3XDAUL5/index.m3u8', '/Q3XDAUL5/1.jpg', '丁字裤小姐姐迷人舞蹈', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100424, 'KddgOI5t', '/KddgOI5t/index.m3u8', '/KddgOI5t/1.jpg', '气质小女人逼逼很粉嫩', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100425, 'm3gsxqQU', '/m3gsxqQU/index.m3u8', '/m3gsxqQU/1.jpg', '有钱就是好美女网红任你玩弄', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100426, '9Lv6X9tn', '/9Lv6X9tn/index.m3u8', '/9Lv6X9tn/1.jpg', '美女御姐希望有人疼', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100427, '94dbwtBN', '/94dbwtBN/index.m3u8', '/94dbwtBN/1.jpg', '今天是兔女郎风格噢', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100428, 'Z1DyQQND', '/Z1DyQQND/index.m3u8', '/Z1DyQQND/1.jpg', '元气满满的甜美女神自慰给人看', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100429, 'VhxR7f7x', '/VhxR7f7x/index.m3u8', '/VhxR7f7x/1.jpg', '谁能拒绝一个流水的粉色小屄呢', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100430, 'hDKp9mTQ', '/hDKp9mTQ/index.m3u8', '/hDKp9mTQ/1.jpg', '妹子给不给力看看就知道啦', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100431, 'lWQpsCPD', '/lWQpsCPD/index.m3u8', '/lWQpsCPD/1.jpg', '与其花费心思谈情说爱不如和骚货做爱', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100432, '2amKn5yb', '/2amKn5yb/index.m3u8', '/2amKn5yb/1.jpg', '口口声声说爱我不如直接干一场', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100433, 'qveYYjnA', '/qveYYjnA/index.m3u8', '/qveYYjnA/1.jpg', '骚货大学生屁股挺翘一看就饱经风霜', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100434, 'lsjJflF2', '/lsjJflF2/index.m3u8', '/lsjJflF2/1.jpg', '你的淫荡小可爱突然出现', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100435, 'dbUc4gA3', '/dbUc4gA3/index.m3u8', '/dbUc4gA3/1.jpg', '想尝尝小美女粉嫩滑爽的香甜小舌吗', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100436, 'rB8IS64Z', '/rB8IS64Z/index.m3u8', '/rB8IS64Z/1.jpg', '调教爱吃醋的小女友', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100437, 'LYIulENF', '/LYIulENF/index.m3u8', '/LYIulENF/1.jpg', '小哥拽着少女的双马尾狂插嫩逼', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100438, '2VSjpzT4', '/2VSjpzT4/index.m3u8', '/2VSjpzT4/1.jpg', '谈恋爱就找这样够骚够漂亮的美女', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100439, 'l6272a3X', '/l6272a3X/index.m3u8', '/l6272a3X/1.jpg', '温柔的姐姐含住肉棒那一刻太美了', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100440, '6PD2xYUK', '/6PD2xYUK/index.m3u8', '/6PD2xYUK/1.jpg', '深夜玩火直播间刺激嗨不断', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100441, '36l4j3zf', '/36l4j3zf/index.m3u8', '/36l4j3zf/1.jpg', '老哥手撕女主播巴黎世家丝袜狂顶子宫', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100442, 'XBiWrrDw', '/XBiWrrDw/index.m3u8', '/XBiWrrDw/1.jpg', '女大学生宿舍直接开直播黄瓜自慰', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100443, '7jLEkeis', '/7jLEkeis/index.m3u8', '/7jLEkeis/1.jpg', '渴望被粉丝干到尿床的少妇主播', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100444, '4UomyPZ9', '/4UomyPZ9/index.m3u8', '/4UomyPZ9/1.jpg', '吃饭睡觉玩奶子人生三大乐趣', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100445, 'E6PBU6pt', '/E6PBU6pt/index.m3u8', '/E6PBU6pt/1.jpg', '可乐要加冰，操我要走心', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100446, 'AsG0gfPI', '/AsG0gfPI/index.m3u8', '/AsG0gfPI/1.jpg', '新来好下手的舞蹈妹妹', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100447, 'CWuYfrhn', '/CWuYfrhn/index.m3u8', '/CWuYfrhn/1.jpg', '美女说想喝粥谁能想到是精子粥', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100448, 'ni1qZmeK', '/ni1qZmeK/index.m3u8', '/ni1qZmeK/1.jpg', '送上门的黑丝不看太可惜了', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100449, 'iv0Tw9Ox', '/iv0Tw9Ox/index.m3u8', '/iv0Tw9Ox/1.jpg', '管好你的鸡巴因为妹子随时会吃掉', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100450, 'GYzIOYoM', '/GYzIOYoM/index.m3u8', '/GYzIOYoM/1.jpg', '没想到还能看到语文老师的小粉穴', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100451, 'FSXsSQ71', '/FSXsSQ71/index.m3u8', '/FSXsSQ71/1.jpg', '舔了两个月的女神终于答应让我舔她的骚逼了', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100452, 'Yj3Pv4tu', '/Yj3Pv4tu/index.m3u8', '/Yj3Pv4tu/1.jpg', '对你的喜欢变成水从逼里流露', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100453, 'GKAXbpuG', '/GKAXbpuG/index.m3u8', '/GKAXbpuG/1.jpg', '服装女模特完美身材激情自慰', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100454, 'WIkYtZx0', '/WIkYtZx0/index.m3u8', '/WIkYtZx0/1.jpg', '淫荡女神也不知耻，骚浪娇喘特大声', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100455, 'JZaPEU4V', '/JZaPEU4V/index.m3u8', '/JZaPEU4V/1.jpg', '看似斯文的眼镜妹摘下眼镜发骚慢摇', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100456, 'SN8hV4Fi', '/SN8hV4Fi/index.m3u8', '/SN8hV4Fi/1.jpg', '性感纯欲魅惑女神在线热舞', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100457, 'JW8ASOcn', '/JW8ASOcn/index.m3u8', '/JW8ASOcn/1.jpg', '游泳教练面试AV首次拍摄性交视频不容错过 JMTY-016', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 38, '岸谷灯', NULL, NULL, 0, 0, 0, '岸谷灯', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100458, '3wHRyitj', '/3wHRyitj/index.m3u8', '/3wHRyitj/1.jpg', '巨乳学生妹子勾引帅气老师宾馆激情性交 SSIS-403', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 14, '爱宝铃', NULL, NULL, 0, 0, 0, '爱宝铃', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100459, 'zhV9TxaS', '/zhV9TxaS/index.m3u8', '/zhV9TxaS/1.jpg', '被丈夫客户强奸的人妻成了客户的性宠物 GMA-024', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100460, 'Zrb8RNRR', '/Zrb8RNRR/index.m3u8', '/Zrb8RNRR/1.jpg', '巨乳按摩师的骚穴被肉棒插的直冒白浆 HMN-006', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 12, '月野霞', NULL, NULL, 0, 0, 0, '月野霞', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100461, 'Ner6plSY', '/Ner6plSY/index.m3u8', '/Ner6plSY/1.jpg', '润滑液触感与震动棒双管齐下，刺激到她高潮痉挛 DVDMS-603', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 12, '月野霞', NULL, NULL, 0, 0, 0, '月野霞', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100462, 'qo4H1RA1', '/qo4H1RA1/index.m3u8', '/qo4H1RA1/1.jpg', '兄弟俩搭讪爆操海边巨乳美女 BLK-522', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100463, 't52BKNEs', '/t52BKNEs/index.m3u8', '/t52BKNEs/1.jpg', '肥硕的巨臀乘骑位疯狂耸动榨取精液 MIST-344', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100464, 'ccEqx4b9', '/ccEqx4b9/index.m3u8', '/ccEqx4b9/1.jpg', '巨乳骚货被两根巨大的黑屌干到浑身痉挛 GVH-355', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100465, 'UfJsNSeG', '/UfJsNSeG/index.m3u8', '/UfJsNSeG/1.jpg', '被催眠后化身淫荡痴女吞尽大肉棒 SRMC-037', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100466, 'GwMxt84a', '/GwMxt84a/index.m3u8', '/GwMxt84a/1.jpg', '月野霞的风俗天国5种风俗最高级的款待 DVDMS-605（上）', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 12, '月野霞', NULL, NULL, 0, 0, 0, '月野霞', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100467, 'pw1DT0Js', '/pw1DT0Js/index.m3u8', '/pw1DT0Js/1.jpg', '搞到痉挛潮吹的巨乳搜查官 PPPD-943', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 12, '月野霞', NULL, NULL, 0, 0, 0, '月野霞', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100468, 'lpsgxnOy', '/lpsgxnOy/index.m3u8', '/lpsgxnOy/1.jpg', '月野霞的风俗天国5种风俗最高级的款待 DVDMS-605（下）', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 12, '月野霞', NULL, NULL, 0, 0, 0, '月野霞', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100469, 'BqGHkhD2', '/BqGHkhD2/index.m3u8', '/BqGHkhD2/1.jpg', '巨根插入痴女骚穴伺候骚货爽歪歪 MMYM-047', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100470, 'gi4gqLfe', '/gi4gqLfe/index.m3u8', '/gi4gqLfe/1.jpg', '巨乳学生妹放学后与老师进行造人课后辅导 HMN-019', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 12, '月野霞', NULL, NULL, 0, 0, 0, '月野霞', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100471, 'EeF6cBle', '/EeF6cBle/index.m3u8', '/EeF6cBle/1.jpg', '为了保护弟弟的姐姐被黑社会操的直翻白眼 DDK-208', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100472, 'QBdXyFYw', '/QBdXyFYw/index.m3u8', '/QBdXyFYw/1.jpg', '被捆绑成M型的痴女在肉棒加木棍的抽插下尖叫不止 GTJ-098', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100473, 'NwWPHAoK', '/NwWPHAoK/index.m3u8', '/NwWPHAoK/1.jpg', '性饥渴的大姐勾引小帅哥爆操嫩穴 WAAA-077', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 12, '月野霞', NULL, NULL, 0, 0, 0, '月野霞', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100474, 'A1rXWH0R', '/A1rXWH0R/index.m3u8', '/A1rXWH0R/1.jpg', '首次解禁无套中出的痴女被操到浑身抽搐 DVDMS-615', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 12, '月野霞', NULL, NULL, 0, 0, 0, '月野霞', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100475, 'XSlnmPrV', '/XSlnmPrV/index.m3u8', '/XSlnmPrV/1.jpg', '两根肉棒同时插入骚女的两个肉洞无套中出爽翻天 DGCEMD-022', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100476, 'f0tczhzX', '/f0tczhzX/index.m3u8', '/f0tczhzX/1.jpg', '短发学生妹被同学们集体轮奸 SOTB-001', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 38, '岸谷灯', NULL, NULL, 0, 0, 0, '岸谷灯', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100477, 'UWDt1Be1', '/UWDt1Be1/index.m3u8', '/UWDt1Be1/1.jpg', '极品女优与五位素人的两天同居生活 TYSF-005（上）', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100478, 's3Wyu2Ea', '/s3Wyu2Ea/index.m3u8', '/s3Wyu2Ea/1.jpg', '巨乳老师成了学校里的性处理工具 DASD-925', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100479, 'XDkkHTbm', '/XDkkHTbm/index.m3u8', '/XDkkHTbm/1.jpg', '展现痴女多样玩法的精选作品合集 MIST-341（下）', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100480, 'ilVmGIqQ', '/ilVmGIqQ/index.m3u8', '/ilVmGIqQ/1.jpg', '童颜巨乳的美少女露出高潮的淫荡模样 SSIS-299', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 14, '爱宝铃', NULL, NULL, 0, 0, 0, '爱宝铃', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100481, 'HOLoqjlt', '/HOLoqjlt/index.m3u8', '/HOLoqjlt/1.jpg', '骚货的小穴能塞进一整只拳头太淫荡了 DGCEMD-092', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100482, 'nWnpYhID', '/nWnpYhID/index.m3u8', '/nWnpYhID/1.jpg', '一夜未归一边与女友通话一边与痴女激情干炮 PPPD-933', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 12, '月野霞', NULL, NULL, 0, 0, 0, '月野霞', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100483, 'gyiT4U9y', '/gyiT4U9y/index.m3u8', '/gyiT4U9y/1.jpg', '美女大学生被游说拍摄AV全过程 DVDMS-585（上）', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 12, '月野霞', NULL, NULL, 0, 0, 0, '月野霞', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100484, '4HZYx1T4', '/4HZYx1T4/index.m3u8', '/4HZYx1T4/1.jpg', '巨乳女优淫乱性交大合集 CEMD-157', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100485, 'tSzZpH1O', '/tSzZpH1O/index.m3u8', '/tSzZpH1O/1.jpg', '出轨的儿媳妇被公公是大肉棒狠狠的惩罚 MIAA-529', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100486, 'kUFXTV65', '/kUFXTV65/index.m3u8', '/kUFXTV65/1.jpg', '月野霞EBOD-842', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 12, '月野霞', NULL, NULL, 0, 0, 0, '月野霞EBOD-842', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100487, 'jXTFbb8N', '/jXTFbb8N/index.m3u8', '/jXTFbb8N/1.jpg', '刚交到的巨乳女友却被不良学生们轮奸 DVDMS-639', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 12, '月野霞', NULL, NULL, 0, 0, 0, '月野霞', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100488, 'CZUqrcHV', '/CZUqrcHV/index.m3u8', '/CZUqrcHV/1.jpg', '骚货的三个肉洞同时塞满巨根，无套中出 MISM-211', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100489, 'QUm71zXx', '/QUm71zXx/index.m3u8', '/QUm71zXx/1.jpg', '巨乳搜查官在罪犯的调教下变成了性欲的奴隶 GMEM-050', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100490, 'I1ozLLEG', '/I1ozLLEG/index.m3u8', '/I1ozLLEG/1.jpg', '展现痴女多样玩法的精选作品合集 MIST-341（上）', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100491, 'V1jN39xt', '/V1jN39xt/index.m3u8', '/V1jN39xt/1.jpg', '美丽的拳师陷入黑社会的性虐地狱 DBER-112', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100492, 'LUaGqFX1', '/LUaGqFX1/index.m3u8', '/LUaGqFX1/1.jpg', '长满浓密阴毛的骚穴在肉棒的抽插下冒出白色浓浆 DVDMS-628', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 12, '月野霞', NULL, NULL, 0, 0, 0, '月野霞', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100493, '6QvMhOse', '/6QvMhOse/index.m3u8', '/6QvMhOse/1.jpg', '美女大学生被游说拍摄AV全过程 DVDMS-585（下）', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 12, '月野霞', NULL, NULL, 0, 0, 0, '月野霞', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100494, '92yWg30t', '/92yWg30t/index.m3u8', '/92yWg30t/1.jpg', '约会前辈的巨乳女友爆操嫩穴爽歪歪 DVDMS-650', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 12, '月野霞', NULL, NULL, 0, 0, 0, '月野霞', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100495, 'S6M5aqRj', '/S6M5aqRj/index.m3u8', '/S6M5aqRj/1.jpg', '身体性欲觉醒的痴女被抽插到高潮痉挛 SSIS-376', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 14, '爱宝铃', NULL, NULL, 0, 0, 0, '爱宝铃', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100496, 'L5r6wE8d', '/L5r6wE8d/index.m3u8', '/L5r6wE8d/1.jpg', '极品女优与五位素人的两天同居生活 TYSF-005（下）', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100497, 'DFdBmhRw', '/DFdBmhRw/index.m3u8', '/DFdBmhRw/1.jpg', '向女上司的复仇 LAFBD-51', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 50, '广濑奈奈美', NULL, NULL, 0, 0, 0, '广濑奈奈美', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100498, 'r9nx18VO', '/r9nx18VO/index.m3u8', '/r9nx18VO/1.jpg', '性感翘臀夹着的黑木耳 062910_867', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 71, '秋元里奈', NULL, NULL, 0, 0, 0, '秋元里奈', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100499, 'zO94N3w4', '/zO94N3w4/index.m3u8', '/zO94N3w4/1.jpg', '小淫娃的嫩逼都要被操烂了 n0500', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 71, '秋元里奈', NULL, NULL, 0, 0, 0, '秋元里奈', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100500, 'KgDCjUOT', '/KgDCjUOT/index.m3u8', '/KgDCjUOT/1.jpg', '浪女粉嫩多汁的鲍鱼 032917-404', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 26, '舞坂仁美', NULL, NULL, 0, 0, 0, '舞坂仁美', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100501, '1abbBc9f', '/1abbBc9f/index.m3u8', '/1abbBc9f/1.jpg', '丝袜美女惨遭捆绑性爱调教 LAFBD-72', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 41, '心爱', NULL, NULL, 0, 0, 0, '心爱', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100502, 'c7Cu5020', '/c7Cu5020/index.m3u8', '/c7Cu5020/1.jpg', '性欲爆发的OL性感美女 SMBD-159', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 41, '心爱', NULL, NULL, 0, 0, 0, '心爱', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100503, 'nbXoBz0s', '/nbXoBz0s/index.m3u8', '/nbXoBz0s/1.jpg', '爆操极品身材时尚模特 CWPBD-136', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 6, '水木里沙', NULL, NULL, 0, 0, 0, '水木里沙', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100504, 'R0vL9DjG', '/R0vL9DjG/index.m3u8', '/R0vL9DjG/1.jpg', '内射微毛粉嫩鲍鱼浪女 k1429', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 61, '井口惠美', NULL, NULL, 0, 0, 0, '井口惠美', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100505, 'bUSIsJ7w', '/bUSIsJ7w/index.m3u8', '/bUSIsJ7w/1.jpg', '内射逼逼会蠕动的清纯女神 090718_003', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 41, '心爱', NULL, NULL, 0, 0, 0, '心爱', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100506, '9CVqeaHd', '/9CVqeaHd/index.m3u8', '/9CVqeaHd/1.jpg', '被摄影师按在床上操的小美女 LAFBD-54', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 75, '真野优莉亚', NULL, NULL, 0, 0, 0, '真野优莉亚', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100507, 'qqUHTtTk', '/qqUHTtTk/index.m3u8', '/qqUHTtTk/1.jpg', '拨开阴唇露出粉嫩阴道的浪货 022819-868', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 64, '星崎雏', NULL, NULL, 0, 0, 0, '星崎雏', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100508, 'CoCYXJYx', '/CoCYXJYx/index.m3u8', '/CoCYXJYx/1.jpg', '谈完心开始谈肉体的美少女 k1450', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 21, '今井菜那', NULL, NULL, 0, 0, 0, '今井菜那', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100509, '8KHRibf9', '/8KHRibf9/index.m3u8', '/8KHRibf9/1.jpg', '真假阳具带来的快感享受 081010_894', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 71, '秋元里奈', NULL, NULL, 0, 0, 0, '秋元里奈', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100510, 'K13Bb3VK', '/K13Bb3VK/index.m3u8', '/K13Bb3VK/1.jpg', '淫荡的女人肉棒充满阴道 xxx-av 23938 （part3）', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 4, '新山枫', NULL, NULL, 0, 0, 0, '新山枫', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100511, 'VrY0JwTr', '/VrY0JwTr/index.m3u8', '/VrY0JwTr/1.jpg', '淫荡的女人肉棒充满阴道 xxx-av 23939 （part4）', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 4, '新山枫', NULL, NULL, 0, 0, 0, '新山枫', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100512, 'IHXUWGzC', '/IHXUWGzC/index.m3u8', '/IHXUWGzC/1.jpg', '操爽了的美女娇喘淫叫 xxx-av 23942', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 58, '藤井夏树', NULL, NULL, 0, 0, 0, '藤井夏树', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100513, 'nRbVqut3', '/nRbVqut3/index.m3u8', '/nRbVqut3/1.jpg', '凌辱正值青春的美少女 n1216', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 21, '今井菜那', NULL, NULL, 0, 0, 0, '今井菜那', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100514, 'Kh0OxjRR', '/Kh0OxjRR/index.m3u8', '/Kh0OxjRR/1.jpg', '淫荡的女人肉棒充满阴道 xxx-av 23936 （part1）', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 4, '新山枫', NULL, NULL, 0, 0, 0, '新山枫', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100515, 'Q1CWyCZt', '/Q1CWyCZt/index.m3u8', '/Q1CWyCZt/1.jpg', '白嫩美女的嫩逼竟然这么肥美 HEYZO-2337', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 17, '舞衣', NULL, NULL, 0, 0, 0, '舞衣', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100516, 'RePFiP5t', '/RePFiP5t/index.m3u8', '/RePFiP5t/1.jpg', '淫荡的女人肉棒充满阴道 xxx-av 23937 （part2）', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 4, '新山枫', NULL, NULL, 0, 0, 0, '新山枫', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100517, 'WiJDfOzT', '/WiJDfOzT/index.m3u8', '/WiJDfOzT/1.jpg', '群P淫乱本性的人妻 SMBD-19', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 67, '羽月希', NULL, NULL, 0, 0, 0, '羽月希', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100518, 'OZjjMaSO', '/OZjjMaSO/index.m3u8', '/OZjjMaSO/1.jpg', '享受肉棒冲击的樱桃小嘴 LAFBD-73', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 7, '岛崎结衣', NULL, NULL, 0, 0, 0, '岛崎结衣', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100519, 'yc13Mabz', '/yc13Mabz/index.m3u8', '/yc13Mabz/1.jpg', '约个小姐姐享受性爱乐趣 HEYZO-2538', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 17, '舞衣', NULL, NULL, 0, 0, 0, '舞衣', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100520, 'jm8pqCUC', '/jm8pqCUC/index.m3u8', '/jm8pqCUC/1.jpg', '调教新来的美女同事 SMBD-153', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 7, '岛崎结衣', NULL, NULL, 0, 0, 0, '岛崎结衣', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100521, '6zcEyk1V', '/6zcEyk1V/index.m3u8', '/6zcEyk1V/1.jpg', '享受裸体按摩的浑圆翘臀美女 HEYZO-1501', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 41, '心爱', NULL, NULL, 0, 0, 0, '心爱', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100522, '9q8drJah', '/9q8drJah/index.m3u8', '/9q8drJah/1.jpg', '狂插羞涩的绝色人妻 SMBD-14', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 67, '羽月希', NULL, NULL, 0, 0, 0, '羽月希', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100523, 'dgp5m2rh', '/dgp5m2rh/index.m3u8', '/dgp5m2rh/1.jpg', '骚动的曼妙美女被操的气喘吁吁 CWPBD-37', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 67, '羽月希', NULL, NULL, 0, 0, 0, '羽月希', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100524, 'eBB0Qklk', '/eBB0Qklk/index.m3u8', '/eBB0Qklk/1.jpg', '大奶美女豪乳淫乱人妻 SMBD-130', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 76, '杉崎绘里奈', NULL, NULL, 0, 0, 0, '杉崎绘里奈', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100525, 'HcOIZRJ0', '/HcOIZRJ0/index.m3u8', '/HcOIZRJ0/1.jpg', '变成辣妹归来的青梅竹马 SMBD-138', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 75, '真野优莉亚', NULL, NULL, 0, 0, 0, '真野优莉亚', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100526, 'uSvnp0rb', '/uSvnp0rb/index.m3u8', '/uSvnp0rb/1.jpg', '想尝试SM的微胖小浪女 042717_01', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 57, '樱木桃香', NULL, NULL, 0, 0, 0, '樱木桃香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100527, 'fBwJXvAW', '/fBwJXvAW/index.m3u8', '/fBwJXvAW/1.jpg', '圣诞礼服痴女的淫乱晚会 SMBD-148', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 49, '杏', NULL, NULL, 0, 0, 0, '杏', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100528, 'jTfDbiY4', '/jTfDbiY4/index.m3u8', '/jTfDbiY4/1.jpg', '当着熟睡丈夫的面操逼好刺激 SKYHD-132', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 4, '新山枫', NULL, NULL, 0, 0, 0, '新山枫', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100529, 'auDtrq0m', '/auDtrq0m/index.m3u8', '/auDtrq0m/1.jpg', '沙发操弄会吹笛子的熟女 052620_01', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 17, '舞衣', NULL, NULL, 0, 0, 0, '舞衣', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100530, 'Fhw02OjB', '/Fhw02OjB/index.m3u8', '/Fhw02OjB/1.jpg', '喜欢出轨的浪荡老婆 SKYHD-072', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 67, '羽月希', NULL, NULL, 0, 0, 0, '羽月希', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100531, 'ChGRX02H', '/ChGRX02H/index.m3u8', '/ChGRX02H/1.jpg', '差点高潮的淫荡口交 020610_769', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 71, '秋元里奈', NULL, NULL, 0, 0, 0, '秋元里奈', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100532, 'thiBo9Cp', '/thiBo9Cp/index.m3u8', '/thiBo9Cp/1.jpg', '完美身材美女骑乘爆操 022619_001', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 41, '心爱', NULL, NULL, 0, 0, 0, '心爱', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100533, 'TSDTg96q', '/TSDTg96q/index.m3u8', '/TSDTg96q/1.jpg', '口爆乳交简直太完美的性交 HEYZO-2610', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 41, '心爱', NULL, NULL, 0, 0, 0, '心爱', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100534, 'd3h1WNcT', '/d3h1WNcT/index.m3u8', '/d3h1WNcT/1.jpg', '玩具伺候欲求不满的骚货 082710_917', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 71, '秋元里奈', NULL, NULL, 0, 0, 0, '秋元里奈', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100535, '0WpmBttR', '/0WpmBttR/index.m3u8', '/0WpmBttR/1.jpg', '插入充满肉感的美女身体 SMBD-84', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 4, '新山枫', NULL, NULL, 0, 0, 0, '新山枫', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100536, 'ImAMExy8', '/ImAMExy8/index.m3u8', '/ImAMExy8/1.jpg', '太漂亮的社长秘书的工作 SMBD-121', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 50, '广濑奈奈美', NULL, NULL, 0, 0, 0, '广濑奈奈美', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100537, 'SSIKdq8X', '/SSIKdq8X/index.m3u8', '/SSIKdq8X/1.jpg', '为所欲为的内射在小粉穴里', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100538, '8gdhTxUB', '/8gdhTxUB/index.m3u8', '/8gdhTxUB/1.jpg', '手一碰淫水就流个不停了', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100539, 'L5q6PzYD', '/L5q6PzYD/index.m3u8', '/L5q6PzYD/1.jpg', '椰奶波霸美少女让我大饱眼福', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100540, '5U209re6', '/5U209re6/index.m3u8', '/5U209re6/1.jpg', '快塞满我的阴道吧', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100541, 'XymGxGT1', '/XymGxGT1/index.m3u8', '/XymGxGT1/1.jpg', '来自臭婊子的绝对服从', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100542, 'yfPwWU6R', '/yfPwWU6R/index.m3u8', '/yfPwWU6R/1.jpg', '甜心小骚货一直要大肉棒', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100543, 'vBtmrCkS', '/vBtmrCkS/index.m3u8', '/vBtmrCkS/1.jpg', '极致的快感来自于高效的调教', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100544, '3g401Ohb', '/3g401Ohb/index.m3u8', '/3g401Ohb/1.jpg', '肉棒与骚逼之间的强烈撞击', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100545, 'upGa9F2h', '/upGa9F2h/index.m3u8', '/upGa9F2h/1.jpg', '自慰后超猛骑乘大屌', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100546, 'pkIhKRQ7', '/pkIhKRQ7/index.m3u8', '/pkIhKRQ7/1.jpg', '要不要射进粉嫩的骚逼里呢', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100547, 'ltnsYAoY', '/ltnsYAoY/index.m3u8', '/ltnsYAoY/1.jpg', '哥哥的鸡巴好硬我好中意', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100548, 'bUyFMIqy', '/bUyFMIqy/index.m3u8', '/bUyFMIqy/1.jpg', '顶级美女是我的私人玩物', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100549, 'DYjOCOzG', '/DYjOCOzG/index.m3u8', '/DYjOCOzG/1.jpg', '可爱萝莉被刺激阴蒂狂叫不止', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100550, 'Pmqs0h2H', '/Pmqs0h2H/index.m3u8', '/Pmqs0h2H/1.jpg', '吸精天使在人间', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100551, 'YSxUxMAi', '/YSxUxMAi/index.m3u8', '/YSxUxMAi/1.jpg', '巨乳美女学生在学校每个角落都高潮过', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100552, 'uI11PxUN', '/uI11PxUN/index.m3u8', '/uI11PxUN/1.jpg', '兄弟的美女女友成了我的胯下宠物', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100553, 'ntfLwhMb', '/ntfLwhMb/index.m3u8', '/ntfLwhMb/1.jpg', '强奸巨乳女下属', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100554, 'GWcPtoZo', '/GWcPtoZo/index.m3u8', '/GWcPtoZo/1.jpg', '夺去了美少女的第一次', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100555, 'mxEEvImm', '/mxEEvImm/index.m3u8', '/mxEEvImm/1.jpg', '这只大屁股适合肛交内射', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100556, 's7b1b6K1', '/s7b1b6K1/index.m3u8', '/s7b1b6K1/1.jpg', '喜欢上自己老师的淫荡女学生', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100557, 'PqLNEPEA', '/PqLNEPEA/index.m3u8', '/PqLNEPEA/1.jpg', '和老同学激情干炮', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100558, 'uEFNWZRR', '/uEFNWZRR/index.m3u8', '/uEFNWZRR/1.jpg', '津津有味的舔舐大屌', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100559, 'faoFrQwc', '/faoFrQwc/index.m3u8', '/faoFrQwc/1.jpg', '豪乳大奶妹很希望你射在他的大奶子上', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100560, 'CbhFIwfT', '/CbhFIwfT/index.m3u8', '/CbhFIwfT/1.jpg', '这是蛋蛋被掏空的感觉', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100561, 'f1kTNRrI', '/f1kTNRrI/index.m3u8', '/f1kTNRrI/1.jpg', '露脸情趣装诱惑还喜被欢后入', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100562, 'rfI92fRm', '/rfI92fRm/index.m3u8', '/rfI92fRm/1.jpg', '看我把这个白奶子射的黏糊糊', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100563, 'PUim4aOg', '/PUim4aOg/index.m3u8', '/PUim4aOg/1.jpg', '瑜伽老师的另一个职业是荡妇', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100564, 'Kxa0hFrG', '/Kxa0hFrG/index.m3u8', '/Kxa0hFrG/1.jpg', '无毛白虎加长发及腰的骚货', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100565, 'duzvAJls', '/duzvAJls/index.m3u8', '/duzvAJls/1.jpg', '哥哥请尽情的射在我的脸上吧', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100566, 'YlG1ZyfX', '/YlG1ZyfX/index.m3u8', '/YlG1ZyfX/1.jpg', '黑丝开裆裤上被射满了精液', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100567, '0etbT2mW', '/0etbT2mW/index.m3u8', '/0etbT2mW/1.jpg', '邻家姐姐成了我的性奴隶', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100568, 'gyVBaDJ8', '/gyVBaDJ8/index.m3u8', '/gyVBaDJ8/1.jpg', '性感人妻直播勾引观众', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100569, '4q2duPV2', '/4q2duPV2/index.m3u8', '/4q2duPV2/1.jpg', '面前的小母狗是不折不扣的淫娃', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100570, 'ct2NAkLP', '/ct2NAkLP/index.m3u8', '/ct2NAkLP/1.jpg', '独家定制少妇的偷情日常', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100571, 'qZsdDyBF', '/qZsdDyBF/index.m3u8', '/qZsdDyBF/1.jpg', '大白奶子摇摇晃晃', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100572, 'M5ZK9mWG', '/M5ZK9mWG/index.m3u8', '/M5ZK9mWG/1.jpg', '韩国主播不雅视频流出', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100573, 'lywtox67', '/lywtox67/index.m3u8', '/lywtox67/1.jpg', '淫荡人妻的出轨日常', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100574, 'o5v0w2fq', '/o5v0w2fq/index.m3u8', '/o5v0w2fq/1.jpg', '极品小妹妹好身材加小嫩逼', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100575, '75FcZFfF', '/75FcZFfF/index.m3u8', '/75FcZFfF/1.jpg', '小天使般的可爱淫娃被内射', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100576, '0HEpigPf', '/0HEpigPf/index.m3u8', '/0HEpigPf/1.jpg', '小妖精对你的肉棒很有想法', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100577, 'SlIV0k4x', '/SlIV0k4x/index.m3u8', '/SlIV0k4x/1.jpg', '名企爆料！三洋摩托车公司庆功宴上演春.宫.盛.宴?!', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '名企爆料！三洋摩托车公司庆功宴上演春.宫.盛.宴?!', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100578, 'vRhVtaLw', '/vRhVtaLw/index.m3u8', '/vRhVtaLw/1.jpg', '借钱还不上？不怕！坑闺蜜操逼舔奶只够还利息', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '借钱还不上？不怕！坑闺蜜操逼舔奶只够还利息', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100579, '4CR5z9Ob', '/4CR5z9Ob/index.m3u8', '/4CR5z9Ob/1.jpg', '长春某校女教师与补课学生父亲发生关系！', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '第一手视频', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100580, '3Jwyx0Cy', '/3Jwyx0Cy/index.m3u8', '/3Jwyx0Cy/1.jpg', '丈夫嫖娼,妻子竟然是卖淫女？铁证视频被公开！', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '丈夫嫖娼,妻子竟然是卖淫女？铁证视频被公开！', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100581, '3ymh3pYL', '/3ymh3pYL/index.m3u8', '/3ymh3pYL/1.jpg', '巨乳女孩跟弟弟玩Cosplay性爱', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100582, 'FQhLQg7x', '/FQhLQg7x/index.m3u8', '/FQhLQg7x/1.jpg', '火辣高中老师玩假鸡巴抽插屁股', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100583, '4UyNtk49', '/4UyNtk49/index.m3u8', '/4UyNtk49/1.jpg', '粉红可爱少女被我干到失去理智', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100584, 'awczrG6Q', '/awczrG6Q/index.m3u8', '/awczrG6Q/1.jpg', '我的巨乳老师用身体帮我辅导', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100585, 'RCNikDnI', '/RCNikDnI/index.m3u8', '/RCNikDnI/1.jpg', '巨乳小婊子把小穴玩到潮吹', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100586, 'krE1J5es', '/krE1J5es/index.m3u8', '/krE1J5es/1.jpg', '粉红女还被我操到小穴超湿', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100587, 'Mo1RgMq1', '/Mo1RgMq1/index.m3u8', '/Mo1RgMq1/1.jpg', '不良女学生用身体换分数', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100588, 'I33P5VE0', '/I33P5VE0/index.m3u8', '/I33P5VE0/1.jpg', '情趣用品商人亲自来我家让我试用', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100589, 'azjnpHOY', '/azjnpHOY/index.m3u8', '/azjnpHOY/1.jpg', '淫荡情侣在窗前做爱给路人观赏', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100590, 'zy7R8BQV', '/zy7R8BQV/index.m3u8', '/zy7R8BQV/1.jpg', '被大街上的女郎带到厕所榨精', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100591, 'nj2Mw8ES', '/nj2Mw8ES/index.m3u8', '/nj2Mw8ES/1.jpg', '巨乳教练主动帮我乳交', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100592, 'H16MG3Mh', '/H16MG3Mh/index.m3u8', '/H16MG3Mh/1.jpg', '抽插在享受自慰的极品女友', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100593, 'CmJ17fnr', '/CmJ17fnr/index.m3u8', '/CmJ17fnr/1.jpg', '淫荡少妇欲求不满玩炮机高潮连连', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100594, 'mB3GtPAO', '/mB3GtPAO/index.m3u8', '/mB3GtPAO/1.jpg', '性感女教师被一顿狂肏', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100595, 'XQvD0iJo', '/XQvD0iJo/index.m3u8', '/XQvD0iJo/1.jpg', '把性感女教师从海边带回房间肏', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100596, '50HbhHiG', '/50HbhHiG/index.m3u8', '/50HbhHiG/1.jpg', '无毛美鲍女孩主动舔棒', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100597, 'GWCQSrTA', '/GWCQSrTA/index.m3u8', '/GWCQSrTA/1.jpg', '美臀荡妇在我身上摇摆', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100598, 'HTCHn791', '/HTCHn791/index.m3u8', '/HTCHn791/1.jpg', '荡妇试玩情趣用品塞满双穴', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100599, 'FQLkIe5j', '/FQLkIe5j/index.m3u8', '/FQLkIe5j/1.jpg', '在海景房间跟巨乳妹妹激情性爱', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100600, 'JLzWHVso', '/JLzWHVso/index.m3u8', '/JLzWHVso/1.jpg', '可爱宅女小穴跟女优名器你会选哪个?', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100601, 'zgpuJsQh', '/zgpuJsQh/index.m3u8', '/zgpuJsQh/1.jpg', '跟我的巨乳教师不能说的秘密约会', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100602, 'kHwRSzpK', '/kHwRSzpK/index.m3u8', '/kHwRSzpK/1.jpg', '女友网购阴蒂按摩器自慰到抽蓄', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 16, 'Morgpie', NULL, NULL, 0, 0, 0, 'Morgpie', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100603, 'uAjv3UIJ', '/uAjv3UIJ/index.m3u8', '/uAjv3UIJ/1.jpg', '把舞娘带回家肏射在高跟鞋上', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100604, 'h4x7iotF', '/h4x7iotF/index.m3u8', '/h4x7iotF/1.jpg', '黑暗未来－哭泣未来 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100605, 'gEWPDRYc', '/gEWPDRYc/index.m3u8', '/gEWPDRYc/1.jpg', '背德妻 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100606, 'HZBO3mLi', '/HZBO3mLi/index.m3u8', '/HZBO3mLi/1.jpg', '渐进曲 ～天使们的私人课程～ 12', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100607, 'y5W30oRK', '/y5W30oRK/index.m3u8', '/y5W30oRK/1.jpg', '渐进曲 ～天使们的私人课程～ 8', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100608, '2OWgjzJA', '/2OWgjzJA/index.m3u8', '/2OWgjzJA/1.jpg', '透明人间 后编', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100609, 'DPUZ2gpd', '/DPUZ2gpd/index.m3u8', '/DPUZ2gpd/1.jpg', '渐进曲 ～天使们的私人课程～ 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100610, 'ASeQSHV6', '/ASeQSHV6/index.m3u8', '/ASeQSHV6/1.jpg', '渐进曲 ～天使们的私人课程～ 4', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100611, 'bkR9HMa7', '/bkR9HMa7/index.m3u8', '/bkR9HMa7/1.jpg', '渐进曲 ～天使们的私人课程～ 5', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100612, 'pOXZcDF8', '/pOXZcDF8/index.m3u8', '/pOXZcDF8/1.jpg', '透明人间R 后编', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100613, 'bEaukjrO', '/bEaukjrO/index.m3u8', '/bEaukjrO/1.jpg', '渐进曲 ～天使们的私人课程～ 6', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100614, 'XrnNfZoK', '/XrnNfZoK/index.m3u8', '/XrnNfZoK/1.jpg', '绯忍传－呀宇种GAUSU－胧月之章', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100615, 'RD4MzJtV', '/RD4MzJtV/index.m3u8', '/RD4MzJtV/1.jpg', '透明人间 前编', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100616, 'Ld2T6lW5', '/Ld2T6lW5/index.m3u8', '/Ld2T6lW5/1.jpg', '背德妻 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100617, 'jFEJgxG9', '/jFEJgxG9/index.m3u8', '/jFEJgxG9/1.jpg', '渐进曲 ～天使们的私人课程～ 7', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100618, 'hXx3ygXR', '/hXx3ygXR/index.m3u8', '/hXx3ygXR/1.jpg', '渐进曲 ～天使们的私人课程～ 11', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100619, 'C8wfeBC3', '/C8wfeBC3/index.m3u8', '/C8wfeBC3/1.jpg', '姐姐和Boin 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100620, 'l4sZGCs2', '/l4sZGCs2/index.m3u8', '/l4sZGCs2/1.jpg', '透明人间R 前编', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100621, 'WvVPcNCS', '/WvVPcNCS/index.m3u8', '/WvVPcNCS/1.jpg', '黑暗未来－哭泣未来 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100622, 'dp1sBDNr', '/dp1sBDNr/index.m3u8', '/dp1sBDNr/1.jpg', '渐进曲 ～天使们的私人课程～ 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100623, 'YtvBnQlF', '/YtvBnQlF/index.m3u8', '/YtvBnQlF/1.jpg', '渐进曲＆渐慢曲 ULTIMATUM ～SERA～ 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100624, 'v2J1XZDr', '/v2J1XZDr/index.m3u8', '/v2J1XZDr/1.jpg', '渐进曲＆渐慢曲 ULTIMATUM ～SERA～ 3', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100625, 'uKbooHlp', '/uKbooHlp/index.m3u8', '/uKbooHlp/1.jpg', '渐进曲 ～天使们的私人课程～ 3', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100626, 'belhp1w8', '/belhp1w8/index.m3u8', '/belhp1w8/1.jpg', '姐姐和Boin 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100627, 'zrr1rT7v', '/zrr1rT7v/index.m3u8', '/zrr1rT7v/1.jpg', '渐进曲 ～天使们的私人课程～ 9', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100628, 'inO6dfQp', '/inO6dfQp/index.m3u8', '/inO6dfQp/1.jpg', '渐进曲＆渐慢曲 ULTIMATUM ～SERA～ 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100629, 'yhiHAnLt', '/yhiHAnLt/index.m3u8', '/yhiHAnLt/1.jpg', '渐进曲 ～天使们的私人课程～ 10', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100630, 'YDEwytol', '/YDEwytol/index.m3u8', '/YDEwytol/1.jpg', '骚货女大学生黑丝诱惑', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100631, 'RfYQg29V', '/RfYQg29V/index.m3u8', '/RfYQg29V/1.jpg', '美女小姐姐线上蹦迪', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100632, 'YW9tG21m', '/YW9tG21m/index.m3u8', '/YW9tG21m/1.jpg', '可爱妹子穿着小裙子给你看裙底', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100633, 'ytNxYH7K', '/ytNxYH7K/index.m3u8', '/ytNxYH7K/1.jpg', '纯欲女主播扭胯慢摇', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100634, '4yBCxzmO', '/4yBCxzmO/index.m3u8', '/4yBCxzmO/1.jpg', '饭后操个逼有益身心健康', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100635, 'A1z3Z9sA', '/A1z3Z9sA/index.m3u8', '/A1z3Z9sA/1.jpg', '宽松衣服下的粉色小奶头', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100636, 'J66y6KfC', '/J66y6KfC/index.m3u8', '/J66y6KfC/1.jpg', '想走？把鸡巴留下吧', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100637, 'IKu2kXCZ', '/IKu2kXCZ/index.m3u8', '/IKu2kXCZ/1.jpg', '性感逼逼舞完美呈现粉嫩骚逼', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100638, 'WZJCffhE', '/WZJCffhE/index.m3u8', '/WZJCffhE/1.jpg', '发情的处女小姐姐', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100639, 'beK77AVP', '/beK77AVP/index.m3u8', '/beK77AVP/1.jpg', '饥渴女神天天让粉丝交作业', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100640, 'VvMNFtSe', '/VvMNFtSe/index.m3u8', '/VvMNFtSe/1.jpg', '小姐姐撅起屁屁卖力炫耀身材', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100641, 'qiZyqKvl', '/qiZyqKvl/index.m3u8', '/qiZyqKvl/1.jpg', '小姐，你也不想失去这份工作吧', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100642, '0d2xvP6W', '/0d2xvP6W/index.m3u8', '/0d2xvP6W/1.jpg', '冷淡女神这么骚看得浑身冒火', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100643, 'TzqWVC4F', '/TzqWVC4F/index.m3u8', '/TzqWVC4F/1.jpg', '绝美嫩模空姐制服诱惑', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100644, 'RlWkx7KG', '/RlWkx7KG/index.m3u8', '/RlWkx7KG/1.jpg', '强势女神被大哥巨棒征服', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100645, 'YwpGadfs', '/YwpGadfs/index.m3u8', '/YwpGadfs/1.jpg', '火辣身材肉感女神女教师制服诱惑', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100646, 'aN6RIXeg', '/aN6RIXeg/index.m3u8', '/aN6RIXeg/1.jpg', '有个这样的老婆你几点回家', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100647, 'zEfp5hmM', '/zEfp5hmM/index.m3u8', '/zEfp5hmM/1.jpg', '花心美女姐姐是个海王吊着粉丝还不忘给看奶子', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100648, 'o4VesJJd', '/o4VesJJd/index.m3u8', '/o4VesJJd/1.jpg', '对着镜子自慰的御姐', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100649, 'Xn1Nv9os', '/Xn1Nv9os/index.m3u8', '/Xn1Nv9os/1.jpg', '骚货女主播蹲下身子把大屁股坐你脸上', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100650, '4RsPsSiu', '/4RsPsSiu/index.m3u8', '/4RsPsSiu/1.jpg', '不是一见钟情，只是那天她没穿裤', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100651, 'VRjiuK4g', '/VRjiuK4g/index.m3u8', '/VRjiuK4g/1.jpg', '性感包臀裙女神翘臀诱惑', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100652, 'rAG6sUsL', '/rAG6sUsL/index.m3u8', '/rAG6sUsL/1.jpg', '校园里的骚货跳舞', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100653, 'UO6DefaH', '/UO6DefaH/index.m3u8', '/UO6DefaH/1.jpg', '很难不爱这样性感的微胖女友', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100654, 'b0zEzcx8', '/b0zEzcx8/index.m3u8', '/b0zEzcx8/1.jpg', '见一个爱一个的风骚女主播', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100655, '52NhV45z', '/52NhV45z/index.m3u8', '/52NhV45z/1.jpg', '黑长直小姐姐酥胸粉嫩好想吃奶奶', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100656, 'fmPCHdLd', '/fmPCHdLd/index.m3u8', '/fmPCHdLd/1.jpg', '发骚母狗在线认主人', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100657, 'CKU1OnxV', '/CKU1OnxV/index.m3u8', '/CKU1OnxV/1.jpg', '努力装纯的坏女人', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100658, 'p8K4HvmU', '/p8K4HvmU/index.m3u8', '/p8K4HvmU/1.jpg', '肥美鲍鱼女主播大胆勾引狼友', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100659, 'ewWEQScj', '/ewWEQScj/index.m3u8', '/ewWEQScj/1.jpg', '爱情经不起等待，操我就趁现在', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100660, 'UEhfRb2w', '/UEhfRb2w/index.m3u8', '/UEhfRb2w/1.jpg', '艾特你的小兄弟来看逼', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100661, 'VSy2eKB3', '/VSy2eKB3/index.m3u8', '/VSy2eKB3/1.jpg', '来和风骚女主播一起放松身体吧', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100662, '5NLodhZ7', '/5NLodhZ7/index.m3u8', '/5NLodhZ7/1.jpg', '刚健身完充满活力的小姐姐', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100663, 'RLBxP8kN', '/RLBxP8kN/index.m3u8', '/RLBxP8kN/1.jpg', '蝴蝶逼微胖女生想被插', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100664, '6dWLQBEH', '/6dWLQBEH/index.m3u8', '/6dWLQBEH/1.jpg', '春天到了，又到了和女神交配的季节', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100665, 'dAbhhoxu', '/dAbhhoxu/index.m3u8', '/dAbhhoxu/1.jpg', '女神换上保守牛仔裤都那么迷人', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100666, '3Ee0i7tO', '/3Ee0i7tO/index.m3u8', '/3Ee0i7tO/1.jpg', '骚货人妻能把你榨干吗', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100667, 'T9HmNBGA', '/T9HmNBGA/index.m3u8', '/T9HmNBGA/1.jpg', '你迟到了自罚一顿啪啪吧', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100668, 'CAYlHyP0', '/CAYlHyP0/index.m3u8', '/CAYlHyP0/1.jpg', '如果把她借给你一天你会干什么', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100669, '2GuWaxg2', '/2GuWaxg2/index.m3u8', '/2GuWaxg2/1.jpg', '女团成员激情脱衣舞', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100670, 'VTvod8dZ', '/VTvod8dZ/index.m3u8', '/VTvod8dZ/1.jpg', '魔法少女 沙枝 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100671, 'Jm0XbyO2', '/Jm0XbyO2/index.m3u8', '/Jm0XbyO2/1.jpg', '下级生2 ～季花词集〔Anthology〕～ 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100672, 'dGecQgHd', '/dGecQgHd/index.m3u8', '/dGecQgHd/1.jpg', '下级生2～Sketchbook～ 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100673, 'a9TvoIRr', '/a9TvoIRr/index.m3u8', '/a9TvoIRr/1.jpg', '秘汤巡游 隐汤 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100674, 'PirjMczK', '/PirjMczK/index.m3u8', '/PirjMczK/1.jpg', '奸狱 ～淫辱的实验大楼～ LEVEL 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100675, 'jmYEpYGo', '/jmYEpYGo/index.m3u8', '/jmYEpYGo/1.jpg', '奸狱 ～淫辱的实验大楼～ LEVEL 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100676, 'Jbexxlja', '/Jbexxlja/index.m3u8', '/Jbexxlja/1.jpg', '义母的吐息 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100677, 'lgbyVz3v', '/lgbyVz3v/index.m3u8', '/lgbyVz3v/1.jpg', '魔法少女 沙枝 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100678, 'tdPrgEC7', '/tdPrgEC7/index.m3u8', '/tdPrgEC7/1.jpg', '义妹', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100679, 'nLmmndY0', '/nLmmndY0/index.m3u8', '/nLmmndY0/1.jpg', '秘汤巡游 隐汤 舞樱编 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100680, '6DAOcrIg', '/6DAOcrIg/index.m3u8', '/6DAOcrIg/1.jpg', '人妻上瘾者 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100681, 'o6ieGTS2', '/o6ieGTS2/index.m3u8', '/o6ieGTS2/1.jpg', '下级生2 ～季花词集〔Anthology〕～ 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100682, 'DNfl0nTc', '/DNfl0nTc/index.m3u8', '/DNfl0nTc/1.jpg', '圣肛女 ～背德的美臀奴隶～ 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100683, '08YoNdfS', '/08YoNdfS/index.m3u8', '/08YoNdfS/1.jpg', '义母的吐息 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100684, 'MCWqYvqq', '/MCWqYvqq/index.m3u8', '/MCWqYvqq/1.jpg', '秘汤巡游 隐汤 舞樱编 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100685, 'z0UsvZgD', '/z0UsvZgD/index.m3u8', '/z0UsvZgD/1.jpg', '真?秘汤巡游 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100686, 'a3H7o4t9', '/a3H7o4t9/index.m3u8', '/a3H7o4t9/1.jpg', '圣肛女 ～背德的美臀奴隶～ 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100687, 'AqIsj9vE', '/AqIsj9vE/index.m3u8', '/AqIsj9vE/1.jpg', 'CLEAVAGE 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100688, 'd1GhdUQr', '/d1GhdUQr/index.m3u8', '/d1GhdUQr/1.jpg', '秘汤巡游 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100689, '9KMljiYH', '/9KMljiYH/index.m3u8', '/9KMljiYH/1.jpg', '下级生2～Sketchbook～ 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100690, '1DVTWykV', '/1DVTWykV/index.m3u8', '/1DVTWykV/1.jpg', '死妹人形 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100691, 'l1yeaqBe', '/l1yeaqBe/index.m3u8', '/l1yeaqBe/1.jpg', '真?秘汤巡游 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100692, '7BodhpZz', '/7BodhpZz/index.m3u8', '/7BodhpZz/1.jpg', '人妻上瘾者 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100693, 'L5iiPDUL', '/L5iiPDUL/index.m3u8', '/L5iiPDUL/1.jpg', 'CLEAVAGE 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100694, 'hQcCG7GU', '/hQcCG7GU/index.m3u8', '/hQcCG7GU/1.jpg', '义妹×2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100695, 'jgk80Fqh', '/jgk80Fqh/index.m3u8', '/jgk80Fqh/1.jpg', '秘汤巡游 隐汤 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100696, 'aG6nD5Tm', '/aG6nD5Tm/index.m3u8', '/aG6nD5Tm/1.jpg', '110417_01', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 59, '七海', NULL, NULL, 0, 0, 0, '七海', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100697, 'xY8Vj3Mc', '/xY8Vj3Mc/index.m3u8', '/xY8Vj3Mc/1.jpg', '041517_01', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 59, '七海', NULL, NULL, 0, 0, 0, '七海', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100698, 'YZ0hSixy', '/YZ0hSixy/index.m3u8', '/YZ0hSixy/1.jpg', '091019_897', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 56, '前原沙良', NULL, NULL, 0, 0, 0, '前原沙良', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100699, 'JQuU5F6l', '/JQuU5F6l/index.m3u8', '/JQuU5F6l/1.jpg', '021718_01', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 59, '七海', NULL, NULL, 0, 0, 0, '七海', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100700, 'yJNlMSW4', '/yJNlMSW4/index.m3u8', '/yJNlMSW4/1.jpg', 'n0607', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 22, '渚', NULL, NULL, 0, 0, 0, '渚', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100701, 'IVEcnmq2', '/IVEcnmq2/index.m3u8', '/IVEcnmq2/1.jpg', 'SMBD-98', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 19, '爱梨南', NULL, NULL, 0, 0, 0, '爱梨南', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100702, 'M0trEF2z', '/M0trEF2z/index.m3u8', '/M0trEF2z/1.jpg', 'HEYZO-1284', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 72, '本山茉莉', NULL, NULL, 0, 0, 0, '本山茉莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100703, 'GrDVAFJx', '/GrDVAFJx/index.m3u8', '/GrDVAFJx/1.jpg', '070211-740', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 22, '渚', NULL, NULL, 0, 0, 0, '渚', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100704, 'XDNH6Wx7', '/XDNH6Wx7/index.m3u8', '/XDNH6Wx7/1.jpg', 'n1164', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 37, '户田麻美', NULL, NULL, 0, 0, 0, '户田麻美', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100705, 'r7WVfus5', '/r7WVfus5/index.m3u8', '/r7WVfus5/1.jpg', 'SMBD-144', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 60, '清水理纱', NULL, NULL, 0, 0, 0, '清水理纱', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100706, 'lUarJLw0', '/lUarJLw0/index.m3u8', '/lUarJLw0/1.jpg', 'n1207', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 51, '原田志保', NULL, NULL, 0, 0, 0, '原田志保', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100707, 'WYgOz2Zb', '/WYgOz2Zb/index.m3u8', '/WYgOz2Zb/1.jpg', '042017_01', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 39, '杉浦花音', NULL, NULL, 0, 0, 0, '杉浦花音', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100708, 'ZsKroq2p', '/ZsKroq2p/index.m3u8', '/ZsKroq2p/1.jpg', '041818-643', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 39, '杉浦花音', NULL, NULL, 0, 0, 0, '杉浦花音', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100709, 'lJGmYO9b', '/lJGmYO9b/index.m3u8', '/lJGmYO9b/1.jpg', 'HEYZO-1908', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 46, '小高里保', NULL, NULL, 0, 0, 0, '小高里保', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100710, 'PUIYhc1q', '/PUIYhc1q/index.m3u8', '/PUIYhc1q/1.jpg', 'n1199', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 51, '原田志保', NULL, NULL, 0, 0, 0, '原田志保', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100711, 'nyfzXEzJ', '/nyfzXEzJ/index.m3u8', '/nyfzXEzJ/1.jpg', '102015-001', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 69, '源美衣奈', NULL, NULL, 0, 0, 0, '源美衣奈', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100712, 'iIvak8c9', '/iIvak8c9/index.m3u8', '/iIvak8c9/1.jpg', 'LAFBD-58', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 60, '清水理纱', NULL, NULL, 0, 0, 0, '清水理纱', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100713, 'yEcHypCR', '/yEcHypCR/index.m3u8', '/yEcHypCR/1.jpg', '071515-001', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 69, '源美衣奈', NULL, NULL, 0, 0, 0, '源美衣奈', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100714, 'aVEmLWFc', '/aVEmLWFc/index.m3u8', '/aVEmLWFc/1.jpg', 'k1398', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 37, '户田麻美', NULL, NULL, 0, 0, 0, '户田麻美', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100715, 'lfyEVWPB', '/lfyEVWPB/index.m3u8', '/lfyEVWPB/1.jpg', 'HEYZO-1974', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 59, '七海', NULL, NULL, 0, 0, 0, '七海', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100716, '3LpuOnL1', '/3LpuOnL1/index.m3u8', '/3LpuOnL1/1.jpg', '071915-924', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 69, '源美衣奈', NULL, NULL, 0, 0, 0, '源美衣奈', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100717, 'ZBxf9QMl', '/ZBxf9QMl/index.m3u8', '/ZBxf9QMl/1.jpg', 'S2MBD-051', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 69, '源美衣奈', NULL, NULL, 0, 0, 0, '源美衣奈', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100718, '4EBKx4xh', '/4EBKx4xh/index.m3u8', '/4EBKx4xh/1.jpg', '112615_196', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 69, '源美衣奈', NULL, NULL, 0, 0, 0, '源美衣奈', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100719, 'HFnI4WHr', '/HFnI4WHr/index.m3u8', '/HFnI4WHr/1.jpg', '032010-326', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 22, '渚', NULL, NULL, 0, 0, 0, '渚', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100720, 'jg1mIwUV', '/jg1mIwUV/index.m3u8', '/jg1mIwUV/1.jpg', 'LAFBD-03', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 19, '爱梨南', NULL, NULL, 0, 0, 0, '爱梨南', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100721, 'LBiDfCT5', '/LBiDfCT5/index.m3u8', '/LBiDfCT5/1.jpg', '110418_001', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 41, '心爱', NULL, NULL, 0, 0, 0, '心爱', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100722, 'GeeArmEu', '/GeeArmEu/index.m3u8', '/GeeArmEu/1.jpg', 'SKYHD-139', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 20, '百合川纱良', NULL, NULL, 0, 0, 0, '百合川纱良', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100723, 'QCGuOR08', '/QCGuOR08/index.m3u8', '/QCGuOR08/1.jpg', 'HEYZO-1230', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 79, '千叶可怜', NULL, NULL, 0, 0, 0, '千叶可怜', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100724, 'QPPltSeA', '/QPPltSeA/index.m3u8', '/QPPltSeA/1.jpg', 'CWPBD-74', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 27, '由纪诚', NULL, NULL, 0, 0, 0, '由纪诚', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100725, 'jMiflfSt', '/jMiflfSt/index.m3u8', '/jMiflfSt/1.jpg', 'HEYZO-1561', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 40, '泷川惠里菜', NULL, NULL, 0, 0, 0, '泷川惠里菜', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100726, 'P8agDTzm', '/P8agDTzm/index.m3u8', '/P8agDTzm/1.jpg', '070915-917', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 72, '本山茉莉', NULL, NULL, 0, 0, 0, '本山茉莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100727, 'ppjyj3oV', '/ppjyj3oV/index.m3u8', '/ppjyj3oV/1.jpg', '060117_01', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 79, '千叶可怜', NULL, NULL, 0, 0, 0, '千叶可怜', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100728, 'koHX6YVH', '/koHX6YVH/index.m3u8', '/koHX6YVH/1.jpg', '012518_637', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 41, '心爱', NULL, NULL, 0, 0, 0, '心爱', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100729, '8oeUI62M', '/8oeUI62M/index.m3u8', '/8oeUI62M/1.jpg', 'HEYZO-1554', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 41, '心爱', NULL, NULL, 0, 0, 0, '心爱', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100730, 'ef0uHLLe', '/ef0uHLLe/index.m3u8', '/ef0uHLLe/1.jpg', 'S2MBD-053', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 69, '源美衣奈', NULL, NULL, 0, 0, 0, '源美衣奈', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100731, '1q7Cof7C', '/1q7Cof7C/index.m3u8', '/1q7Cof7C/1.jpg', 'S2MBD-049', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 69, '源美衣奈', NULL, NULL, 0, 0, 0, '源美衣奈', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100732, 'fJWl6kN2', '/fJWl6kN2/index.m3u8', '/fJWl6kN2/1.jpg', '070915-001', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 72, '本山茉莉', NULL, NULL, 0, 0, 0, '本山茉莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100733, 'gbKPMtTP', '/gbKPMtTP/index.m3u8', '/gbKPMtTP/1.jpg', '111617_606', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 40, '泷川惠里菜', NULL, NULL, 0, 0, 0, '泷川惠里菜', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100734, 'xaJqn3jc', '/xaJqn3jc/index.m3u8', '/xaJqn3jc/1.jpg', 'HEYZO-1810', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 72, '本山茉莉', NULL, NULL, 0, 0, 0, '本山茉莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100735, 'Po4vFIv8', '/Po4vFIv8/index.m3u8', '/Po4vFIv8/1.jpg', '122917_624', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 41, '心爱', NULL, NULL, 0, 0, 0, '心爱', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100736, 'SEIKk12W', '/SEIKk12W/index.m3u8', '/SEIKk12W/1.jpg', '淫荡的人妻渴求肉棒疯狂抽插自己的骚穴 JUL-594', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 23, '三尾惠', NULL, NULL, 0, 0, 0, '三尾惠', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100737, 'Opwey3Rw', '/Opwey3Rw/index.m3u8', '/Opwey3Rw/1.jpg', '玩弄骚货的乳头用巨根让她高潮不断 PPPD-893', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100738, 'dbw3bTQI', '/dbw3bTQI/index.m3u8', '/dbw3bTQI/1.jpg', '约会巨乳美女玩弄丰满的肉体让她高潮不断 MIAA-285', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100739, 'z1VRZShy', '/z1VRZShy/index.m3u8', '/z1VRZShy/1.jpg', '好色人妻出轨老公朋友高潮连连 KSBJ-116', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100740, 'vtP6NJoc', '/vtP6NJoc/index.m3u8', '/vtP6NJoc/1.jpg', '回家探亲与已为人妻的同学激情性交 JUL-697', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 23, '三尾惠', NULL, NULL, 0, 0, 0, '三尾惠', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100741, 'tFCusiY2', '/tFCusiY2/index.m3u8', '/tFCusiY2/1.jpg', '好色人妻背着老公与小叔子激情干炮 KSBJ-187', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 23, '三尾惠', NULL, NULL, 0, 0, 0, '三尾惠', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100742, 'oeRopG1g', '/oeRopG1g/index.m3u8', '/oeRopG1g/1.jpg', '变态弟弟趁姐夫不在家爆操姐姐嫩穴 YST-196', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100743, 'jTZlXjkM', '/jTZlXjkM/index.m3u8', '/jTZlXjkM/1.jpg', '美丽的老师沦为不良学生们的泄欲工具 GVH-362', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 23, '三尾惠', NULL, NULL, 0, 0, 0, '三尾惠', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100744, 'jR8IpyJP', '/jR8IpyJP/index.m3u8', '/jR8IpyJP/1.jpg', '26岁的已婚人妻AV出道首次亮相 JUL-556', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 23, '三尾惠', NULL, NULL, 0, 0, 0, '三尾惠', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100745, 'CYgyRIAd', '/CYgyRIAd/index.m3u8', '/CYgyRIAd/1.jpg', '隔壁的性感大姐帮我进行性交练习 HMN-113', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 23, '三尾惠', NULL, NULL, 0, 0, 0, '三尾惠', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100746, 'EQFi9H5T', '/EQFi9H5T/index.m3u8', '/EQFi9H5T/1.jpg', '爆操对面巨乳人妻干到她高潮潮吹 JUFE-192', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100747, 'okLHhuVy', '/okLHhuVy/index.m3u8', '/okLHhuVy/1.jpg', '巨乳美少女性感裸体极致诱惑 SXAR-016', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 47, '稻场流花', NULL, NULL, 0, 0, 0, '稻场流花', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100748, '9ZDENNGu', '/9ZDENNGu/index.m3u8', '/9ZDENNGu/1.jpg', '好好的露营让我的妻子成了村民的性玩具 JUL-665', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 23, '三尾惠', NULL, NULL, 0, 0, 0, '三尾惠', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100749, 'ia46uTHc', '/ia46uTHc/index.m3u8', '/ia46uTHc/1.jpg', '喜欢被绳子捆绑起来的风骚人妻 OIGS-035', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100750, 'pyakxG2Z', '/pyakxG2Z/index.m3u8', '/pyakxG2Z/1.jpg', '淫荡的换妻性交让人血脉膨胀 NYH-109', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100751, 'gxTOHkVs', '/gxTOHkVs/index.m3u8', '/gxTOHkVs/1.jpg', '掏出坚硬的肉棒猛插赛车皇后的肉穴 DPMI-066', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 23, '三尾惠', NULL, NULL, 0, 0, 0, '三尾惠', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100752, 'OSGS3giZ', '/OSGS3giZ/index.m3u8', '/OSGS3giZ/1.jpg', '被上司强奸的人妻沦为了泄欲工具 JUL-628', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 23, '三尾惠', NULL, NULL, 0, 0, 0, '三尾惠', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100753, 'a6gbUapm', '/a6gbUapm/index.m3u8', '/a6gbUapm/1.jpg', '两根肉棒轮流开发痴女子宫无套中出 DGCEMD-141', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 23, '三尾惠', NULL, NULL, 0, 0, 0, '三尾惠', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100754, 'e8JJP5JK', '/e8JJP5JK/index.m3u8', '/e8JJP5JK/1.jpg', '巨乳人妻被隔壁邻居的肉棒干的浑身抽搐 AKDL-006', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100755, 'LaruGfb7', '/LaruGfb7/index.m3u8', '/LaruGfb7/1.jpg', '许久未见的小情侣酒店花样做爱 HODV-21449', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100756, '4p0Z1XLJ', '/4p0Z1XLJ/index.m3u8', '/4p0Z1XLJ/1.jpg', '巨乳老师被捆绑调教后变成了性欲的奴隶 VDD-163', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100757, '1fBwDwHc', '/1fBwDwHc/index.m3u8', '/1fBwDwHc/1.jpg', '性欲旺盛的儿媳妇连公公的肉棒都不放过 NACR-375', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100758, 'EA5huwDW', '/EA5huwDW/index.m3u8', '/EA5huwDW/1.jpg', '吃下药物转变成女性体验性高潮的快感 OMHD-007', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100759, 'NyFsc3vt', '/NyFsc3vt/index.m3u8', '/NyFsc3vt/1.jpg', '淫荡叔母勾引自己的侄子激情干炮 JUFE-353', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 23, '三尾惠', NULL, NULL, 0, 0, 0, '三尾惠', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100760, 'sR7KKzlB', '/sR7KKzlB/index.m3u8', '/sR7KKzlB/1.jpg', '欲求得不到满足的人妻下海拍片寻求刺激 CEMD-124', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 23, '三尾惠', NULL, NULL, 0, 0, 0, '三尾惠', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100761, 'n4ExdWgS', '/n4ExdWgS/index.m3u8', '/n4ExdWgS/1.jpg', '尽情释放自己淫乱性欲的巨乳女优 DGCEAD-339', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100762, 'vzqy4W2M', '/vzqy4W2M/index.m3u8', '/vzqy4W2M/1.jpg', '渴望前男友大肉棒的痴女给现男友带绿帽 DGCESD-999', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100763, 'rE3Yvt9A', '/rE3Yvt9A/index.m3u8', '/rE3Yvt9A/1.jpg', '自己的未婚妻被黑社会绑架轮奸 APNS-275', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 23, '三尾惠', NULL, NULL, 0, 0, 0, '三尾惠', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100764, 'uSpG7RzX', '/uSpG7RzX/index.m3u8', '/uSpG7RzX/1.jpg', '随着肉棒的持续抽插痴女的呻吟声不断变大 DFE-035', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100765, 'jdxNQzVe', '/jdxNQzVe/index.m3u8', '/jdxNQzVe/1.jpg', '千金小姐被丈夫的客户囚禁轮奸无套中出到高潮 APNS-270', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 23, '三尾惠', NULL, NULL, 0, 0, 0, '三尾惠', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100766, 'yyPxoaE0', '/yyPxoaE0/index.m3u8', '/yyPxoaE0/1.jpg', '出差的酒店里我占有了美丽上司肉体 JUL-765', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 23, '三尾惠', NULL, NULL, 0, 0, 0, '三尾惠', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100767, 'xcuRWLIy', '/xcuRWLIy/index.m3u8', '/xcuRWLIy/1.jpg', '丈夫的尺寸无法满足风骚妻子，与两个黑鬼激情大战 WAAA-032', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100768, 'DUJYuF8I', '/DUJYuF8I/index.m3u8', '/DUJYuF8I/1.jpg', '面试的巨乳女优化身学生妹被肉棒直插子宫深处 MIHA-038', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100769, '3g5dZuzQ', '/3g5dZuzQ/index.m3u8', '/3g5dZuzQ/1.jpg', '美丽的少妇被中年男子侵犯了美丽的肉体 MVSD-494', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 23, '三尾惠', NULL, NULL, 0, 0, 0, '三尾惠', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100770, 'Y6P9EbQn', '/Y6P9EbQn/index.m3u8', '/Y6P9EbQn/1.jpg', '体检的小护士被医生强行抽插小穴 ZOZO-033', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100771, 'vjQLvKGn', '/vjQLvKGn/index.m3u8', '/vjQLvKGn/1.jpg', '在排卵日上班的按摩女郎被客人无套中出 MIHA-052', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100772, 'osCYy7JJ', '/osCYy7JJ/index.m3u8', '/osCYy7JJ/1.jpg', '禁止吃肉一个月的美女性欲大爆发 HND-917', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100773, 'Wdfkee8P', '/Wdfkee8P/index.m3u8', '/Wdfkee8P/1.jpg', '欲求不满的人妻与店长发生性关系 MEYD-643', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100774, '5Kn0dfn8', '/5Kn0dfn8/index.m3u8', '/5Kn0dfn8/1.jpg', '面试的美女被捆绑强奸到直翻白眼 DDFF-005', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 78, '望月绚香', NULL, NULL, 0, 0, 0, '望月绚香', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100775, '1tamOndm', '/1tamOndm/index.m3u8', '/1tamOndm/1.jpg', '猫女郎用巨尻榨取粉丝精液 MTALL-017', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 47, '稻场流花', NULL, NULL, 0, 0, 0, '稻场流花', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100776, 'e5HTZTjm', '/e5HTZTjm/index.m3u8', '/e5HTZTjm/1.jpg', '在泳池派对上认识的巨乳女孩', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100777, 'nNeBAXcD', '/nNeBAXcD/index.m3u8', '/nNeBAXcD/1.jpg', '把钢管女郎带回家肏到她腿软', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100778, '4HUQqiqC', '/4HUQqiqC/index.m3u8', '/4HUQqiqC/1.jpg', '情窦初开的妹妹练习扭腰榨精', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100779, 'LeQ41yGE', '/LeQ41yGE/index.m3u8', '/LeQ41yGE/1.jpg', '巨乳小模被面具男跟踪狠肏', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100780, 'IZgj6h8E', '/IZgj6h8E/index.m3u8', '/IZgj6h8E/1.jpg', '淫荡女孩最爱的礼物是男人肉棒', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100781, 'wA7WxFk8', '/wA7WxFk8/index.m3u8', '/wA7WxFk8/1.jpg', '模特儿女友用淫荡舌头舔我蛋', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100782, 'SenBRU3q', '/SenBRU3q/index.m3u8', '/SenBRU3q/1.jpg', '好色女友说想在阳台打炮给邻居看', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100783, 'YJSCyySV', '/YJSCyySV/index.m3u8', '/YJSCyySV/1.jpg', '在app上认识的淫荡洋装小妞来我家', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100784, 'Y5z3otkZ', '/Y5z3otkZ/index.m3u8', '/Y5z3otkZ/1.jpg', '小模女友最爱塞满双穴', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100785, 'cGHkU4Aj', '/cGHkU4Aj/index.m3u8', '/cGHkU4Aj/1.jpg', '荡妇邻居在阳台诱惑我', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100786, 'g48yzYGX', '/g48yzYGX/index.m3u8', '/g48yzYGX/1.jpg', '骚货小穴塞着跳蛋出门逛街', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100787, 'lAdyUTHr', '/lAdyUTHr/index.m3u8', '/lAdyUTHr/1.jpg', '小模女友在家是我的性玩物', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100788, 'UjBt7GSR', '/UjBt7GSR/index.m3u8', '/UjBt7GSR/1.jpg', '宠物就该乖乖被绑住给主人肏', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100789, 'Mc5FNUZT', '/Mc5FNUZT/index.m3u8', '/Mc5FNUZT/1.jpg', '短裤女孩背着男友带人回家做爱', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100790, '8R72SdAz', '/8R72SdAz/index.m3u8', '/8R72SdAz/1.jpg', '巨乳婊子换上新佯装肏完她射肚子上', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100791, 'NoMkNPEs', '/NoMkNPEs/index.m3u8', '/NoMkNPEs/1.jpg', '泳池旁的黝黑女孩找我做爱', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100792, '7MMpaBX1', '/7MMpaBX1/index.m3u8', '/7MMpaBX1/1.jpg', '巨乳女孩诱惑青梅竹马', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100793, 'K6VYEYRd', '/K6VYEYRd/index.m3u8', '/K6VYEYRd/1.jpg', '超辣钢管女郎被客人开发后门', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100794, 'IlZNF9Ac', '/IlZNF9Ac/index.m3u8', '/IlZNF9Ac/1.jpg', '健美女孩性欲喷发找弟弟做爱', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100795, 'K4iKR8Iv', '/K4iKR8Iv/index.m3u8', '/K4iKR8Iv/1.jpg', '扯住她的头发不断撞击巨臀', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100796, 'QrXpjgGp', '/QrXpjgGp/index.m3u8', '/QrXpjgGp/1.jpg', '在迈阿密的高级住宅区找外约妹做爱', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100797, 'iYvfkit2', '/iYvfkit2/index.m3u8', '/iYvfkit2/1.jpg', '巨乳女孩的新泳装骚吗?', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100798, 'FVfIjbGY', '/FVfIjbGY/index.m3u8', '/FVfIjbGY/1.jpg', '学生妹主动趴到桌下舔棒', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100799, 'qesxIf3E', '/qesxIf3E/index.m3u8', '/qesxIf3E/1.jpg', '巨乳炮友穿上黑丝还让我无套抽插', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100800, 'jwou3RaI', '/jwou3RaI/index.m3u8', '/jwou3RaI/1.jpg', '我的按摩师特地穿上洋装诱惑我', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100801, 'KgfY28aG', '/KgfY28aG/index.m3u8', '/KgfY28aG/1.jpg', '美女小姨子偷偷爬上了我的床', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100802, 'ocuLXSVM', '/ocuLXSVM/index.m3u8', '/ocuLXSVM/1.jpg', '无套顶操内射粉穴', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100803, 'qrjxjol0', '/qrjxjol0/index.m3u8', '/qrjxjol0/1.jpg', '被干的精疲力尽也要起身接精', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100804, 'NEPv1jaf', '/NEPv1jaf/index.m3u8', '/NEPv1jaf/1.jpg', '哥哥快来爆操我的骚逼', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100805, 'nEWHJ6XU', '/nEWHJ6XU/index.m3u8', '/nEWHJ6XU/1.jpg', '捡到一个极品美女自然不能放过', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100806, 'shm257VG', '/shm257VG/index.m3u8', '/shm257VG/1.jpg', '脱下衣服的主播真是够色情', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100807, '1r3mdTu3', '/1r3mdTu3/index.m3u8', '/1r3mdTu3/1.jpg', '热衷于吃鸡巴的美少女', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100808, 'ykS0Oa1i', '/ykS0Oa1i/index.m3u8', '/ykS0Oa1i/1.jpg', '先刮干净毛再操骚逼', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100809, 'dJlMrqhc', '/dJlMrqhc/index.m3u8', '/dJlMrqhc/1.jpg', '极品女神郑艾莉无码流出', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100810, '75G26rXt', '/75G26rXt/index.m3u8', '/75G26rXt/1.jpg', '调皮妹妹总是让我感受插入的乐趣', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100811, 'zrqj1xcG', '/zrqj1xcG/index.m3u8', '/zrqj1xcG/1.jpg', '激情口爆这只小母狗', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100812, 'DMk7EN2r', '/DMk7EN2r/index.m3u8', '/DMk7EN2r/1.jpg', '妹妹的欲望彻底释放在了今天', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100813, 'ryhpQcSL', '/ryhpQcSL/index.m3u8', '/ryhpQcSL/1.jpg', '淫秽的女教师每天都在勾引我', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100814, 'Nj2r5LvL', '/Nj2r5LvL/index.m3u8', '/Nj2r5LvL/1.jpg', '邻家姐姐闯入了我平平无奇的生活', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100815, 'TMOG6eUC', '/TMOG6eUC/index.m3u8', '/TMOG6eUC/1.jpg', '巨乳美女陷入了猛烈的抽插中', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100816, 'xnL67vhZ', '/xnL67vhZ/index.m3u8', '/xnL67vhZ/1.jpg', '小骚逼吃掉了大肉棒', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100817, 'WFljKOiN', '/WFljKOiN/index.m3u8', '/WFljKOiN/1.jpg', '学长的巨根是对我最好的奖励', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100818, 'JLcC5p6b', '/JLcC5p6b/index.m3u8', '/JLcC5p6b/1.jpg', '这么骚的货色让我欲罢不能', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100819, '6fSyEWBG', '/6fSyEWBG/index.m3u8', '/6fSyEWBG/1.jpg', '湿透的胸罩在勾引我', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100820, 'lPztJIxy', '/lPztJIxy/index.m3u8', '/lPztJIxy/1.jpg', '光是刺激奶头就能达到高潮的学生妹', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100821, '0JN0BBmJ', '/0JN0BBmJ/index.m3u8', '/0JN0BBmJ/1.jpg', '好久不见的他已经有了我不懂的姿势', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100822, 'HhHvnJdj', '/HhHvnJdj/index.m3u8', '/HhHvnJdj/1.jpg', '风骚女主播尽情搔首弄姿', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100823, 'c0fuUhmn', '/c0fuUhmn/index.m3u8', '/c0fuUhmn/1.jpg', '把漂亮的邻居拿下了', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100824, 'Ux4OcuQy', '/Ux4OcuQy/index.m3u8', '/Ux4OcuQy/1.jpg', '对妹妹总是把持不住的意淫', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100825, 'j3gaLmT9', '/j3gaLmT9/index.m3u8', '/j3gaLmT9/1.jpg', '混血美女竟然是淫乱的臭婊子', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100826, 'YcF3gZuJ', '/YcF3gZuJ/index.m3u8', '/YcF3gZuJ/1.jpg', '纯洁的少女被我培养成了性奴', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100827, '5ddYQE8F', '/5ddYQE8F/index.m3u8', '/5ddYQE8F/1.jpg', '会自己摇屁股的骚货可不多见', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100828, 'aq6c7jbC', '/aq6c7jbC/index.m3u8', '/aq6c7jbC/1.jpg', '女神倒在了我的怀里', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100829, 'rURvIpUa', '/rURvIpUa/index.m3u8', '/rURvIpUa/1.jpg', '体验了帝皇一般的做爱服务', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100830, 'CHO8LQYg', '/CHO8LQYg/index.m3u8', '/CHO8LQYg/1.jpg', '爆操韩国顶级美女主播', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100831, 'HmvyPEre', '/HmvyPEre/index.m3u8', '/HmvyPEre/1.jpg', '被粉丝金主爸爸偷干了一整天', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100832, 'IBDEgzUI', '/IBDEgzUI/index.m3u8', '/IBDEgzUI/1.jpg', '和漂亮妹妹来一次无限交配吧', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100833, '01Uwk1ft', '/01Uwk1ft/index.m3u8', '/01Uwk1ft/1.jpg', '为了升职只好用身体色诱上司', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100834, 'IZsFlTWM', '/IZsFlTWM/index.m3u8', '/IZsFlTWM/1.jpg', '白丝肉丝样样都有', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100835, 'j2NMDtdF', '/j2NMDtdF/index.m3u8', '/j2NMDtdF/1.jpg', '美女的居家生活自慰骚穴', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100836, 'EOq0f3oD', '/EOq0f3oD/index.m3u8', '/EOq0f3oD/1.jpg', '美女为了救哥哥只好拿身体还债', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100837, 'NTovWtGk', '/NTovWtGk/index.m3u8', '/NTovWtGk/1.jpg', '清晨来一发内射好吗', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100838, 'Rg337Xgj', '/Rg337Xgj/index.m3u8', '/Rg337Xgj/1.jpg', '迷人小白屁股沾满了我的精液', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100839, 'slOH9Jp9', '/slOH9Jp9/index.m3u8', '/slOH9Jp9/1.jpg', '超短裙黑丝女上司', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100840, '1Z8CJviM', '/1Z8CJviM/index.m3u8', '/1Z8CJviM/1.jpg', '空降女优送到你家', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100841, 'w3s5aeCQ', '/w3s5aeCQ/index.m3u8', '/w3s5aeCQ/1.jpg', '两个大白奶子的贱货露出奶子摸B', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100842, 'nWwrI97G', '/nWwrI97G/index.m3u8', '/nWwrI97G/1.jpg', '黑丝长腿妹呻吟还自慰', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100843, 'jnKPFdE3', '/jnKPFdE3/index.m3u8', '/jnKPFdE3/1.jpg', '直播女神网盘色图流出', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100844, 'uqAxP1Qa', '/uqAxP1Qa/index.m3u8', '/uqAxP1Qa/1.jpg', '陌陌上的妹子直接摸奶又扣逼', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100845, 'ZoABYkj7', '/ZoABYkj7/index.m3u8', '/ZoABYkj7/1.jpg', '可遇不可求的清纯高中女孩', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100846, 'msXVOHyE', '/msXVOHyE/index.m3u8', '/msXVOHyE/1.jpg', '不穿内衣的女主播风骚光腚舞', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100847, '0WpyBAs0', '/0WpyBAs0/index.m3u8', '/0WpyBAs0/1.jpg', '最骚女主播三点装赤裸上阵', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100848, 'S69ce4T6', '/S69ce4T6/index.m3u8', '/S69ce4T6/1.jpg', '主播乖乖脱下衣服给榜一大哥跳舞', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100849, 'VhXgKc9X', '/VhXgKc9X/index.m3u8', '/VhXgKc9X/1.jpg', '韩国最色女主播最新色色视频曝光', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100850, 'KKBmSlO4', '/KKBmSlO4/index.m3u8', '/KKBmSlO4/1.jpg', '小妹偷偷激情直播被我发现了', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100851, 'ldr77RvS', '/ldr77RvS/index.m3u8', '/ldr77RvS/1.jpg', '美女人妻身材太棒了背着老公出轨', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100852, 'BeNm9KW5', '/BeNm9KW5/index.m3u8', '/BeNm9KW5/1.jpg', '年少不知阿姨好，错把少女当成宝', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100853, 'UTS5wdWh', '/UTS5wdWh/index.m3u8', '/UTS5wdWh/1.jpg', '女贱货只穿内裤在床上抠B', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100854, 'gJyvR6t9', '/gJyvR6t9/index.m3u8', '/gJyvR6t9/1.jpg', '女少妇每晚屋子开激情视频', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100855, 'oNbMWJ4q', '/oNbMWJ4q/index.m3u8', '/oNbMWJ4q/1.jpg', '黑丝长腿妹扒开内裤展示黑色阴毛', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100856, 'hXg8qPQG', '/hXg8qPQG/index.m3u8', '/hXg8qPQG/1.jpg', '要想没遗憾，按着老婆闺蜜拼命干', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100857, 'MbWAMjM7', '/MbWAMjM7/index.m3u8', '/MbWAMjM7/1.jpg', '大型自慰喷水现场', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100858, 'nIGUXEWH', '/nIGUXEWH/index.m3u8', '/nIGUXEWH/1.jpg', '懂得利用肉棒保养的韵味少女', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100859, 'mz0Gspmb', '/mz0Gspmb/index.m3u8', '/mz0Gspmb/1.jpg', '吃不到鸡巴好委屈的小妹妹', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100860, 'IyMhzZYp', '/IyMhzZYp/index.m3u8', '/IyMhzZYp/1.jpg', '寡妇带上口罩放肆的摸奶插穴', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100861, 'tfmfMPRF', '/tfmfMPRF/index.m3u8', '/tfmfMPRF/1.jpg', '给女友的漂亮闺蜜治病之一天两次口服大肉棒', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100862, '66soZWrH', '/66soZWrH/index.m3u8', '/66soZWrH/1.jpg', '自信女孩挑战最刺激的自慰玩具', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100863, 'oOeqYMLd', '/oOeqYMLd/index.m3u8', '/oOeqYMLd/1.jpg', '隔壁少妇背着老公每晚色情直播', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100864, 'HDQsc2ng', '/HDQsc2ng/index.m3u8', '/HDQsc2ng/1.jpg', '小婊子终于沦陷在我的抚摸下', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100865, 'Z0NwPlGq', '/Z0NwPlGq/index.m3u8', '/Z0NwPlGq/1.jpg', '摸奶不过瘾就用道具抽插骚穴', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100866, '52MTEbix', '/52MTEbix/index.m3u8', '/52MTEbix/1.jpg', '黑逼的骚贱货使劲揉奶', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100867, 'jz2jxqxc', '/jz2jxqxc/index.m3u8', '/jz2jxqxc/1.jpg', '看看这是你的专属炮架吗', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100868, 'CfNYOHaz', '/CfNYOHaz/index.m3u8', '/CfNYOHaz/1.jpg', '黑丝长腿妹自摸奶子的诱惑', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100869, 'RlzyejJi', '/RlzyejJi/index.m3u8', '/RlzyejJi/1.jpg', '喜欢被主人调教的淫荡女学生', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100870, 'uKN8Vkk4', '/uKN8Vkk4/index.m3u8', '/uKN8Vkk4/1.jpg', '女大学生福利姬jk制服自慰', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100871, 'D59N7C3J', '/D59N7C3J/index.m3u8', '/D59N7C3J/1.jpg', '来打球吗？我有两个大白球', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100872, 'Yn5Ha5Ap', '/Yn5Ha5Ap/index.m3u8', '/Yn5Ha5Ap/1.jpg', '发情的浪荡母狗一直抠逼', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100873, 'bw5lKy4r', '/bw5lKy4r/index.m3u8', '/bw5lKy4r/1.jpg', '风骚表姐“逼试”挑选男友', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100874, 'S27noLc8', '/S27noLc8/index.m3u8', '/S27noLc8/1.jpg', '黑丝长腿妹美乳抹油摸臀双重快乐', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100875, 'qExw0mWL', '/qExw0mWL/index.m3u8', '/qExw0mWL/1.jpg', '骚浪微胖少女这谁顶得住啊', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100876, 'Ws8pylOX', '/Ws8pylOX/index.m3u8', '/Ws8pylOX/1.jpg', '酒吧气氛组爱跳舞的三陪女', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100877, 'bThuao2R', '/bThuao2R/index.m3u8', '/bThuao2R/1.jpg', '女同桌约我晚上10点脱衣裸聊', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100878, 'MJ4hUxIe', '/MJ4hUxIe/index.m3u8', '/MJ4hUxIe/1.jpg', '一进房间就看穿丝袜的骚逼', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100879, 'XnILkbw3', '/XnILkbw3/index.m3u8', '/XnILkbw3/1.jpg', '穿丝袜的性感熟女撅着腚发出浪叫', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100880, 'QafZiPgx', '/QafZiPgx/index.m3u8', '/QafZiPgx/1.jpg', '表姐背着家人直播白嫩双乳全被我看了', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100881, 'LBeEYxVj', '/LBeEYxVj/index.m3u8', '/LBeEYxVj/1.jpg', '花与蛇 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100882, '1uxIXEJn', '/1uxIXEJn/index.m3u8', '/1uxIXEJn/1.jpg', '两个大嫂 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100883, 'BO8BQub0', '/BO8BQub0/index.m3u8', '/BO8BQub0/1.jpg', '乱伦家族 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100884, '5xD2FlVi', '/5xD2FlVi/index.m3u8', '/5xD2FlVi/1.jpg', '姬骑士莉莉亚 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100885, 'QPrvPLbB', '/QPrvPLbB/index.m3u8', '/QPrvPLbB/1.jpg', '医辱 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100886, 'OsZj3wDu', '/OsZj3wDu/index.m3u8', '/OsZj3wDu/1.jpg', '姬骑士莉莉亚 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100887, 'Z2fpV3TH', '/Z2fpV3TH/index.m3u8', '/Z2fpV3TH/1.jpg', '燐月 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100888, 'RjzBxjMU', '/RjzBxjMU/index.m3u8', '/RjzBxjMU/1.jpg', '真?燐月 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100889, 'hGqwIUFR', '/hGqwIUFR/index.m3u8', '/hGqwIUFR/1.jpg', '天空的颜色、水的颜色 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100890, '5aLUhtv3', '/5aLUhtv3/index.m3u8', '/5aLUhtv3/1.jpg', '痴汉物语 前编', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100891, '74hNxtcg', '/74hNxtcg/index.m3u8', '/74hNxtcg/1.jpg', '痴汉物语 后编', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100892, 'UJg4qyb2', '/UJg4qyb2/index.m3u8', '/UJg4qyb2/1.jpg', '姬骑士莉莉亚 3', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100893, 'r2RY0q36', '/r2RY0q36/index.m3u8', '/r2RY0q36/1.jpg', '姬骑士莉莉亚 4', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100894, 'h0iKnhxZ', '/h0iKnhxZ/index.m3u8', '/h0iKnhxZ/1.jpg', '姬骑士莉莉亚 6', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100895, 'Nc6prJDH', '/Nc6prJDH/index.m3u8', '/Nc6prJDH/1.jpg', '燐月 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100896, 'Q0lxqEdG', '/Q0lxqEdG/index.m3u8', '/Q0lxqEdG/1.jpg', '真?燐月 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100897, '13li7DOb', '/13li7DOb/index.m3u8', '/13li7DOb/1.jpg', '天空的颜色、水的颜色 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100898, 'ceSz8GyW', '/ceSz8GyW/index.m3u8', '/ceSz8GyW/1.jpg', '乱伦家族 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100899, 'Jft3qwPg', '/Jft3qwPg/index.m3u8', '/Jft3qwPg/1.jpg', '花与蛇 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100900, '8aIPeJpQ', '/8aIPeJpQ/index.m3u8', '/8aIPeJpQ/1.jpg', '燐月 3', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100901, 'rXrzOPsS', '/rXrzOPsS/index.m3u8', '/rXrzOPsS/1.jpg', '医辱 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100902, 'bscLBCgB', '/bscLBCgB/index.m3u8', '/bscLBCgB/1.jpg', '两个大嫂 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100903, '9pZs7erE', '/9pZs7erE/index.m3u8', '/9pZs7erE/1.jpg', '花与蛇 3', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100904, 'iNLAdZKd', '/iNLAdZKd/index.m3u8', '/iNLAdZKd/1.jpg', '姬骑士莉莉亚 5', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100905, '9YDwRaIA', '/9YDwRaIA/index.m3u8', '/9YDwRaIA/1.jpg', '美女签订奴隶契约接受拘束调教变身淫荡母狗 n1195', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 10, '浅井奈央', NULL, NULL, 0, 0, 0, '浅井奈央', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100906, 'XEINqFMh', '/XEINqFMh/index.m3u8', '/XEINqFMh/1.jpg', '黑色鲍鱼不停吞吐大肉棒流出白色汁液 k0837', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 36, '水泽莉乃', NULL, NULL, 0, 0, 0, '水泽莉乃', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100907, '4aVK12Da', '/4aVK12Da/index.m3u8', '/4aVK12Da/1.jpg', '在女仆的嫩穴上涂满润肤乳肉棒进入爽翻天 101018-770', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 33, '铃村伊吕波', NULL, NULL, 0, 0, 0, '铃村伊吕波', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100908, 'Vl0gFG4w', '/Vl0gFG4w/index.m3u8', '/Vl0gFG4w/1.jpg', '极品美鲍被肉棒抽插的不断抽搐 101217_001', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 48, '三浦凛', NULL, NULL, 0, 0, 0, '三浦凛', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100909, 'D6w5UZ1u', '/D6w5UZ1u/index.m3u8', '/D6w5UZ1u/1.jpg', '泳池边，野外玩弄巨乳女郎的嫩穴 081619_153', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 33, '铃村伊吕波', NULL, NULL, 0, 0, 0, '铃村伊吕波', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100910, 'm6lCQ3uW', '/m6lCQ3uW/index.m3u8', '/m6lCQ3uW/1.jpg', '想要租房子先要满足房东的性欲 HEYZO-1413', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 73, '井上绫子', NULL, NULL, 0, 0, 0, '井上绫子', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100911, '380wHnwx', '/380wHnwx/index.m3u8', '/380wHnwx/1.jpg', '来自风骚母亲的性教育 010318_200', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 73, '井上绫子', NULL, NULL, 0, 0, 0, '井上绫子', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100912, 'pfv6SkMs', '/pfv6SkMs/index.m3u8', '/pfv6SkMs/1.jpg', '好好的一次按摩变成了肉棒大战骚穴 HEYZO-2130', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 31, '西冈奈央', NULL, NULL, 0, 0, 0, '西冈奈央', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100913, 'fcRy53Ag', '/fcRy53Ag/index.m3u8', '/fcRy53Ag/1.jpg', '粉嫩的美鲍在灵活舌头的挑逗下淫水直流 102718_761', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 33, '铃村伊吕波', NULL, NULL, 0, 0, 0, '铃村伊吕波', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100914, 'f7MEONoq', '/f7MEONoq/index.m3u8', '/f7MEONoq/1.jpg', '爆操中年淑女的骚穴满足她旺盛的性欲 052317_530', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 73, '井上绫子', NULL, NULL, 0, 0, 0, '井上绫子', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100915, 'E3MaPvnW', '/E3MaPvnW/index.m3u8', '/E3MaPvnW/1.jpg', '美少女被中年大叔的巨根干的直叫不要 032817-403', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 34, '一之濑铃', NULL, NULL, 0, 0, 0, '一之濑铃', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100916, 'nOPoSIHF', '/nOPoSIHF/index.m3u8', '/nOPoSIHF/1.jpg', '爆乳老师的特别的性交补习课 031518_658', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 33, '铃村伊吕波', NULL, NULL, 0, 0, 0, '铃村伊吕波', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100917, 'QAxregED', '/QAxregED/index.m3u8', '/QAxregED/1.jpg', '身材姣好的业余姑娘被玩弄到潮吹不断 HEY-094', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 48, '三浦凛', NULL, NULL, 0, 0, 0, '三浦凛', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100918, '9vZRMWjU', '/9vZRMWjU/index.m3u8', '/9vZRMWjU/1.jpg', '下班回家先跟风骚的老婆来一炮 081917_134', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 73, '井上绫子', NULL, NULL, 0, 0, 0, '井上绫子', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100919, '6E9lPKiU', '/6E9lPKiU/index.m3u8', '/6E9lPKiU/1.jpg', '性欲旺盛的欲女被三根肉棒轮番抽插爽到爆 n0749', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 36, '水泽莉乃', NULL, NULL, 0, 0, 0, '水泽莉乃', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100920, 'ZJ6pwVD9', '/ZJ6pwVD9/index.m3u8', '/ZJ6pwVD9/1.jpg', '巨大的奶子包裹着你的老弟太爽了吧 HEYZO-2411', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 33, '铃村伊吕波', NULL, NULL, 0, 0, 0, '铃村伊吕波', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100921, 'dEqpVpWa', '/dEqpVpWa/index.m3u8', '/dEqpVpWa/1.jpg', '体操美女摆出各种高难度动作被肉棒操爽了 081911_159', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 24, '阳菜', NULL, NULL, 0, 0, 0, '阳菜', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100922, 'kunso5sW', '/kunso5sW/index.m3u8', '/kunso5sW/1.jpg', '双龙戏鲍进出美鲍流出好多淫水 HEYZO-0743', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 33, '铃村伊吕波', NULL, NULL, 0, 0, 0, '铃村伊吕波', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100923, 'gWGYgKtY', '/gWGYgKtY/index.m3u8', '/gWGYgKtY/1.jpg', '帮助好友的发情人妻释放自己的性欲 061918_702', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 31, '西冈奈央', NULL, NULL, 0, 0, 0, '西冈奈央', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100924, 'nLE4kZiO', '/nLE4kZiO/index.m3u8', '/nLE4kZiO/1.jpg', '酒店爆操极品模特的黑色美鲍 030817_495', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 74, '白川沙耶', NULL, NULL, 0, 0, 0, '白川沙耶', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100925, 'Xv38u7yz', '/Xv38u7yz/index.m3u8', '/Xv38u7yz/1.jpg', '痴女帮助按摩技师口交爽歪歪 HEYZO-1639', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 73, '井上绫子', NULL, NULL, 0, 0, 0, '井上绫子', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100926, 'RsLOt4hs', '/RsLOt4hs/index.m3u8', '/RsLOt4hs/1.jpg', '无套中出粉嫩的美鲍巨大的奶子随着晃动 HEYZO-1756', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 33, '铃村伊吕波', NULL, NULL, 0, 0, 0, '铃村伊吕波', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100927, 'euBkmtMd', '/euBkmtMd/index.m3u8', '/euBkmtMd/1.jpg', '双手握住巨大的奶子爆操骚穴爽歪歪 HEYZO-1795', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 33, '铃村伊吕波', NULL, NULL, 0, 0, 0, '铃村伊吕波', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100928, 'NQHMe4QO', '/NQHMe4QO/index.m3u8', '/NQHMe4QO/1.jpg', '紧缚美少女的嫩穴被无套中出大量精液 HEYZO-1514', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 65, '松田安娜', NULL, NULL, 0, 0, 0, '松田安娜', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100929, 'CIskNriq', '/CIskNriq/index.m3u8', '/CIskNriq/1.jpg', '巨乳女郎沦为黑社会的泄欲工具 022811-631', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 8, '星优奈', NULL, NULL, 0, 0, 0, '星优奈', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100930, 'myLeNsv6', '/myLeNsv6/index.m3u8', '/myLeNsv6/1.jpg', '身材无敌的美少女被几根肉棒操到浑身抽搐 n1163', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 77, '北原真奈', NULL, NULL, 0, 0, 0, '北原真奈', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100931, 'GcwCOnMv', '/GcwCOnMv/index.m3u8', '/GcwCOnMv/1.jpg', '巨乳骚货瘙痒难耐只能用震动棒自己解决 111418-792', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 33, '铃村伊吕波', NULL, NULL, 0, 0, 0, '铃村伊吕波', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100932, 'AZ5WK4mH', '/AZ5WK4mH/index.m3u8', '/AZ5WK4mH/1.jpg', '三兄弟一起玩弄漂亮女主播的无毛白虎 n1156', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 77, '北原真奈', NULL, NULL, 0, 0, 0, '北原真奈', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100933, '86QInKum', '/86QInKum/index.m3u8', '/86QInKum/1.jpg', '玩弄黑丝美足撕开丝袜爆操嫩穴 HEYZO-1461', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 65, '松田安娜', NULL, NULL, 0, 0, 0, '松田安娜', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100934, 'RySedBZO', '/RySedBZO/index.m3u8', '/RySedBZO/1.jpg', '发现欲求不满的人妻自慰，用肉棒满足她的欲望 110320_379', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 31, '西冈奈央', NULL, NULL, 0, 0, 0, '西冈奈央', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100935, 'QDFna4BP', '/QDFna4BP/index.m3u8', '/QDFna4BP/1.jpg', '极品美少女粉嫩的小穴被灌满大量精液 n1176', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 77, '北原真奈', NULL, NULL, 0, 0, 0, '北原真奈', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100936, 'c13MwSqh', '/c13MwSqh/index.m3u8', '/c13MwSqh/1.jpg', '捆绑中的巨乳骚货被调教的高潮不断 100814_898', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 33, '铃村伊吕波', NULL, NULL, 0, 0, 0, '铃村伊吕波', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100937, '5oTrxyDw', '/5oTrxyDw/index.m3u8', '/5oTrxyDw/1.jpg', '街边搭讪美女带回家干到骚穴潮吹 HEYZO-1283', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 48, '三浦凛', NULL, NULL, 0, 0, 0, '三浦凛', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100938, 'Awnji1ta', '/Awnji1ta/index.m3u8', '/Awnji1ta/1.jpg', '淫荡女上司办公室勾引下属抽插自己骚穴 HEYZO-1562', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 73, '井上绫子', NULL, NULL, 0, 0, 0, '井上绫子', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100939, 'MiIo7LU6', '/MiIo7LU6/index.m3u8', '/MiIo7LU6/1.jpg', '欲求不满的人妻与情夫偷偷干炮爽翻天 031018_004', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 73, '井上绫子', NULL, NULL, 0, 0, 0, '井上绫子', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100940, 'jeTqtZky', '/jeTqtZky/index.m3u8', '/jeTqtZky/1.jpg', '圣诞美女调教M男，被巨根抽插的浑身颤抖 120818-807', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 33, '铃村伊吕波', NULL, NULL, 0, 0, 0, '铃村伊吕波', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100941, 'hOoGioPQ', '/hOoGioPQ/index.m3u8', '/hOoGioPQ/1.jpg', '爆插巨乳骚货的粉嫩美鲍爽的呻吟不断 080418_723', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 33, '铃村伊吕波', NULL, NULL, 0, 0, 0, '铃村伊吕波', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100942, 'bJ9SxXa2', '/bJ9SxXa2/index.m3u8', '/bJ9SxXa2/1.jpg', '我老婆新买情趣衣喜欢吗?', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100943, 'A6UQF8AO', '/A6UQF8AO/index.m3u8', '/A6UQF8AO/1.jpg', '贵妇继母主动跑来诱惑我', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100944, '2u2EXaWf', '/2u2EXaWf/index.m3u8', '/2u2EXaWf/1.jpg', '女仆在工作也要当主人的肉变器', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100945, '1Ne3DQDX', '/1Ne3DQDX/index.m3u8', '/1Ne3DQDX/1.jpg', '富婆在车库被建筑工人内射', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100946, 'VpC1K3YI', '/VpC1K3YI/index.m3u8', '/VpC1K3YI/1.jpg', '闯入女同学的淋浴间激情干炮', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100947, 'E4MRuDV8', '/E4MRuDV8/index.m3u8', '/E4MRuDV8/1.jpg', '外约性感模特儿被我抽插到不断颤抖', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100948, 'hVF7a5ve', '/hVF7a5ve/index.m3u8', '/hVF7a5ve/1.jpg', '白雪公主挑战7根假屌', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100949, 'j8SkQn0l', '/j8SkQn0l/index.m3u8', '/j8SkQn0l/1.jpg', '隔壁的淫荡贵妇找上门', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100950, 'AXfoXYHb', '/AXfoXYHb/index.m3u8', '/AXfoXYHb/1.jpg', '极M乖母狗随便你玩', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100951, 'VYIumR44', '/VYIumR44/index.m3u8', '/VYIumR44/1.jpg', '巨乳啦啦队带回家狠肏内射', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100952, 'zSeu0UFj', '/zSeu0UFj/index.m3u8', '/zSeu0UFj/1.jpg', '少女在干扰哥哥玩游戏被狠肏', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100953, 'CeexKf2Y', '/CeexKf2Y/index.m3u8', '/CeexKf2Y/1.jpg', '有钱爸爸用肉棒填满学生妹小穴', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100954, 'v7a0cjbP', '/v7a0cjbP/index.m3u8', '/v7a0cjbP/1.jpg', '塞着肛塞不断被抽插无毛嫩穴', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100955, 'CgoPRKhW', '/CgoPRKhW/index.m3u8', '/CgoPRKhW/1.jpg', '巨乳家教每次上课都想榨干我', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100956, 'm1IFomNF', '/m1IFomNF/index.m3u8', '/m1IFomNF/1.jpg', '淫荡小红帽用巨乳喂饱大野狼', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100957, '2hamOjMW', '/2hamOjMW/index.m3u8', '/2hamOjMW/1.jpg', '闺密太久没交男友盯上我的大肉棒', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100958, 'TbMXxWPD', '/TbMXxWPD/index.m3u8', '/TbMXxWPD/1.jpg', '巨乳女佣用翘臀夹紧我肉棒', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100959, 'LBxMG76Y', '/LBxMG76Y/index.m3u8', '/LBxMG76Y/1.jpg', '性感黑护士被抽插到不断声淫', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100960, 'Pqc1h7Zo', '/Pqc1h7Zo/index.m3u8', '/Pqc1h7Zo/1.jpg', '黑丝女佣一边打炮一边做饭', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100961, 'hzqfXeXc', '/hzqfXeXc/index.m3u8', '/hzqfXeXc/1.jpg', '与黑女孩在海景旁的浪漫性爱', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100962, 'oPBHRlLs', '/oPBHRlLs/index.m3u8', '/oPBHRlLs/1.jpg', '火辣老师中午外出约炮', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100963, 'O05I3nzX', '/O05I3nzX/index.m3u8', '/O05I3nzX/1.jpg', '考试成绩太差巨乳教师惩罚我', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100964, 'TeFaOLsV', '/TeFaOLsV/index.m3u8', '/TeFaOLsV/1.jpg', '无套抽插黝黑小模骚穴', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100965, 'XguAAoXF', '/XguAAoXF/index.m3u8', '/XguAAoXF/1.jpg', '炮友用精液灌满了我的骚穴', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100966, 'phwyidru', '/phwyidru/index.m3u8', '/phwyidru/1.jpg', '淫荡妈妈趁家里没人跑来舔我大屌', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100967, 'GU4pwHdw', '/GU4pwHdw/index.m3u8', '/GU4pwHdw/1.jpg', '与性感辣妹阳台做爱给路人观赏', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100968, 'v6NxJIQa', '/v6NxJIQa/index.m3u8', '/v6NxJIQa/1.jpg', '气质女孩喝了春药后变得更放荡了', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100969, 'RGD8yFnn', '/RGD8yFnn/index.m3u8', '/RGD8yFnn/1.jpg', '塞着肛塞的女孩拜托你射在巨乳上', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100970, 'qHgVklQO', '/qHgVklQO/index.m3u8', '/qHgVklQO/1.jpg', '巨乳学生不断挤压巨乳勾引教练', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100971, 'pm4rccp1', '/pm4rccp1/index.m3u8', '/pm4rccp1/1.jpg', '学妹抹上润滑液帮我全身按摩', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100972, 'H1wocWSi', '/H1wocWSi/index.m3u8', '/H1wocWSi/1.jpg', '拘束玩弄榨取肉棒的精液', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100973, 'AYo3K4hx', '/AYo3K4hx/index.m3u8', '/AYo3K4hx/1.jpg', '通常早上吃鸡巴的臭婊子', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100974, 'R8l9Ntx5', '/R8l9Ntx5/index.m3u8', '/R8l9Ntx5/1.jpg', '干翻了我性感的表姐', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100975, 'FExZqi4u', '/FExZqi4u/index.m3u8', '/FExZqi4u/1.jpg', '在隔壁房被中年大叔中出', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100976, 'AhpGjz2r', '/AhpGjz2r/index.m3u8', '/AhpGjz2r/1.jpg', '大奶母狗跪地求取精液', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100977, 'Sw7BdXWH', '/Sw7BdXWH/index.m3u8', '/Sw7BdXWH/1.jpg', '被爸爸操的不能自理的小萝莉', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100978, 'wmwXMBDD', '/wmwXMBDD/index.m3u8', '/wmwXMBDD/1.jpg', '淫荡女教师的诱惑', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100979, 'dwhqMU2O', '/dwhqMU2O/index.m3u8', '/dwhqMU2O/1.jpg', '健身房认识的骚货', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100980, 'ELEzgKCo', '/ELEzgKCo/index.m3u8', '/ELEzgKCo/1.jpg', '强插领导的嫩妻', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100981, 'LlgyqqBd', '/LlgyqqBd/index.m3u8', '/LlgyqqBd/1.jpg', '和青梅竹马淫乱的日常', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100982, 'IIy4wrou', '/IIy4wrou/index.m3u8', '/IIy4wrou/1.jpg', '被姐姐的大奶子诱惑了', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100983, '5eKGKHv5', '/5eKGKHv5/index.m3u8', '/5eKGKHv5/1.jpg', '和顺从的美女激烈的做爱', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100984, 'MNklysWR', '/MNklysWR/index.m3u8', '/MNklysWR/1.jpg', '女主播约我晚上脱衣裸聊', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100985, 'J47E7tpu', '/J47E7tpu/index.m3u8', '/J47E7tpu/1.jpg', '现役女大学生第一次就这么给了我', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100986, 'QdjDjSEm', '/QdjDjSEm/index.m3u8', '/QdjDjSEm/1.jpg', '酒精催使美丽人妻发泄性欲', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100987, 'xcMI73GI', '/xcMI73GI/index.m3u8', '/xcMI73GI/1.jpg', '魅惑姐夫的小护士', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100988, 'liouMzZE', '/liouMzZE/index.m3u8', '/liouMzZE/1.jpg', '性感姐姐被弟弟调教', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100989, 'NONEqgE6', '/NONEqgE6/index.m3u8', '/NONEqgE6/1.jpg', '用精油让巨乳女大学生达到高潮', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100990, 'w6pVoCXd', '/w6pVoCXd/index.m3u8', '/w6pVoCXd/1.jpg', '你要为我舔干净小穴吗', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100991, 'FMfBCIX3', '/FMfBCIX3/index.m3u8', '/FMfBCIX3/1.jpg', '天使般的甜蜜诱惑', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100992, 'caRReUGu', '/caRReUGu/index.m3u8', '/caRReUGu/1.jpg', '加速抽插直到她受不了', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100993, 'uOE4KCQd', '/uOE4KCQd/index.m3u8', '/uOE4KCQd/1.jpg', '脱下了漂亮姐姐的性感内衣', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100994, 'OqTMWw4m', '/OqTMWw4m/index.m3u8', '/OqTMWw4m/1.jpg', '光是摸奶子已经不够满足我了', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100995, 'lvdYXhLf', '/lvdYXhLf/index.m3u8', '/lvdYXhLf/1.jpg', '外表冷淡的女神私底下是个骚逼', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100996, 'ogur4ZBl', '/ogur4ZBl/index.m3u8', '/ogur4ZBl/1.jpg', '遭受暴虐的美穴', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100997, 'BsJXSTUO', '/BsJXSTUO/index.m3u8', '/BsJXSTUO/1.jpg', '欠操的臭婊子', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100998, 'PjhFEi9I', '/PjhFEi9I/index.m3u8', '/PjhFEi9I/1.jpg', '是第一次也是最后一次的中出', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (100999, '0ZIa846F', '/0ZIa846F/index.m3u8', '/0ZIa846F/1.jpg', '发骚的母狗到处寻找自己的主人', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101000, 'YRfmB3SL', '/YRfmB3SL/index.m3u8', '/YRfmB3SL/1.jpg', '母狗同学说想尝尝我精液的味道', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101001, '9hmpNW1a', '/9hmpNW1a/index.m3u8', '/9hmpNW1a/1.jpg', '淫贱放荡的臭母狗想被爆操', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101002, '6ClvxA8K', '/6ClvxA8K/index.m3u8', '/6ClvxA8K/1.jpg', '穿着黑丝的骚货发出浪叫', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101003, 'PrYBRM4x', '/PrYBRM4x/index.m3u8', '/PrYBRM4x/1.jpg', '玩弄骚货的乳头直到高潮为止', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101004, 'gRGj9qqj', '/gRGj9qqj/index.m3u8', '/gRGj9qqj/1.jpg', '惊人的身材压倒性的大胸脯', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101005, 'yN1hFZTa', '/yN1hFZTa/index.m3u8', '/yN1hFZTa/1.jpg', '哥哥快来揉搓我的大奶子', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101006, 'LOy5z7su', '/LOy5z7su/index.m3u8', '/LOy5z7su/1.jpg', '大肉棒才能满足的骚浪贱货', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101007, 'TLPVuVzV', '/TLPVuVzV/index.m3u8', '/TLPVuVzV/1.jpg', '哥哥的鸡巴好硬我好喜欢', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101008, 'GfWxG6qe', '/GfWxG6qe/index.m3u8', '/GfWxG6qe/1.jpg', '使劲操这个粉嫩嫩的骚穴', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101009, 'PeVLIOje', '/PeVLIOje/index.m3u8', '/PeVLIOje/1.jpg', '喜欢撩拨肉棒的痴女女友', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101010, 'q19qRfdD', '/q19qRfdD/index.m3u8', '/q19qRfdD/1.jpg', '异国风情操起来确实爽', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101011, 'R6hYUvGc', '/R6hYUvGc/index.m3u8', '/R6hYUvGc/1.jpg', '小骚逼原来是个吸精小神器', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 15:40:31', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 15:40:31', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101012, 'ovhaY20b', '/ovhaY20b/index.m3u8', '/ovhaY20b/1.jpg', '极品美女被写真摄影师一顿爆操爽翻天 HEYZO-0834', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 96, '露娜', NULL, NULL, 0, 0, 0, '露娜', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101013, 'uE8B6qk9', '/uE8B6qk9/index.m3u8', '/uE8B6qk9/1.jpg', '工作累了在办公室来一炮放松放松 HEYZO-1890', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 96, '露娜', NULL, NULL, 0, 0, 0, '露娜', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101014, 'vRQJ1HrL', '/vRQJ1HrL/index.m3u8', '/vRQJ1HrL/1.jpg', '露娜061320_001', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 96, '露娜', NULL, NULL, 0, 0, 0, '露娜061320_001', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101015, 'ucXqyIg7', '/ucXqyIg7/index.m3u8', '/ucXqyIg7/1.jpg', '爸爸们快来吸D杯大奶', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101016, 'C69ccNm1', '/C69ccNm1/index.m3u8', '/C69ccNm1/1.jpg', '泳池旁的少妇盯上年轻肉棒', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101017, 'DSyAPtYK', '/DSyAPtYK/index.m3u8', '/DSyAPtYK/1.jpg', '姐姐☆怀孕MIX 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101018, 'uPGEGeza', '/uPGEGeza/index.m3u8', '/uPGEGeza/1.jpg', '淫荡阴道镜探索少女的粉逼 xxx-av 23427', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 85, '樱井梨花', NULL, NULL, 0, 0, 0, '樱井梨花', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101019, 'tDhbei8k', '/tDhbei8k/index.m3u8', '/tDhbei8k/1.jpg', '可怜的妹子被拉着手爆操', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101020, '6uvh9gDF', '/6uvh9gDF/index.m3u8', '/6uvh9gDF/1.jpg', '被上司潜规则的顶级美女', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101021, 'VPhzfUCQ', '/VPhzfUCQ/index.m3u8', '/VPhzfUCQ/1.jpg', '粉嫩小可爱小脸羞红真诱人', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101022, '9hgR7op1', '/9hgR7op1/index.m3u8', '/9hgR7op1/1.jpg', '巨乳女子和年轻情夫偷偷约啪', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101023, 'ibvlBoWP', '/ibvlBoWP/index.m3u8', '/ibvlBoWP/1.jpg', '妹妹全裸在泳池旁把她叫来帮我泄欲', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101024, 'HTuIYj3W', '/HTuIYj3W/index.m3u8', '/HTuIYj3W/1.jpg', '哥哥不要停下来', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101025, '26LBnje3', '/26LBnje3/index.m3u8', '/26LBnje3/1.jpg', '骚货勾搭狼友玩真的', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101026, 'J3lqcN6n', '/J3lqcN6n/index.m3u8', '/J3lqcN6n/1.jpg', '特务搜查官小玲＆风子 4', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101027, 'LWfLTXXD', '/LWfLTXXD/index.m3u8', '/LWfLTXXD/1.jpg', '淫荡女孩穿上小丁字裤你能抵抗诱惑吗?', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101028, 'oQEISIdB', '/oQEISIdB/index.m3u8', '/oQEISIdB/1.jpg', '被金发妈妈湿漉漉的股间诱惑 032319_825', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 96, '露娜', NULL, NULL, 0, 0, 0, '露娜', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101029, 'B59x0XZa', '/B59x0XZa/index.m3u8', '/B59x0XZa/1.jpg', '一边玩弄巨大的奶子一边爆操骚穴 HEYZO-2120', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, '上原正树', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101030, 'IFLDiiIH', '/IFLDiiIH/index.m3u8', '/IFLDiiIH/1.jpg', '全裸女佣连主人的性欲都能一起满足 112319_932', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 96, '露娜', NULL, NULL, 0, 0, 0, '露娜', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101031, 'vyk790hC', '/vyk790hC/index.m3u8', '/vyk790hC/1.jpg', '大大水蜜桃在线娇喘', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101032, 'ORqQSMGw', '/ORqQSMGw/index.m3u8', '/ORqQSMGw/1.jpg', '白嫩美女享受阴部按摩忍不住口爆 HEYZO-2053', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 84, '椎名美优', NULL, NULL, 0, 0, 0, '椎名美优', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101033, 'yioe5msE', '/yioe5msE/index.m3u8', '/yioe5msE/1.jpg', '美女在家里与摄影师的交合 083117-490', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 82, '木村美羽', NULL, NULL, 0, 0, 0, '木村美羽', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101034, 'bWwSrY2h', '/bWwSrY2h/index.m3u8', '/bWwSrY2h/1.jpg', '当我的邻居变成了清纯少女后', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101035, 'HD5VynHC', '/HD5VynHC/index.m3u8', '/HD5VynHC/1.jpg', '魔墮之夜 3', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101036, '4nmiQcda', '/4nmiQcda/index.m3u8', '/4nmiQcda/1.jpg', '粉色小骚包少妇黑木耳水流不断', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101037, '8vl0QQoy', '/8vl0QQoy/index.m3u8', '/8vl0QQoy/1.jpg', '敏感度暴涨的大胸妹妹', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101038, 'IiQYOGPY', '/IiQYOGPY/index.m3u8', '/IiQYOGPY/1.jpg', '放学后和女教师在情趣酒店的故事', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101039, 'SvKQrxYL', '/SvKQrxYL/index.m3u8', '/SvKQrxYL/1.jpg', '各种情趣内衣只为让你勃起', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101040, 'PxAFjkgT', '/PxAFjkgT/index.m3u8', '/PxAFjkgT/1.jpg', '韩国女主播直播露出好身材', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101041, 'JaBtrjDB', '/JaBtrjDB/index.m3u8', '/JaBtrjDB/1.jpg', '白花花的奶子被我抓在手里', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101042, 'Px6ndekf', '/Px6ndekf/index.m3u8', '/Px6ndekf/1.jpg', '和网袜女在邻居家后院猛干', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101043, 'MjgQIiYZ', '/MjgQIiYZ/index.m3u8', '/MjgQIiYZ/1.jpg', '体验偶像少女的第一次', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101044, 'L8CT9sXt', '/L8CT9sXt/index.m3u8', '/L8CT9sXt/1.jpg', '身材不错的女主播和我做爱', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101045, '4DFt8cNo', '/4DFt8cNo/index.m3u8', '/4DFt8cNo/1.jpg', '姐姐☆怀孕MIX 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101046, 'gvljP6cR', '/gvljP6cR/index.m3u8', '/gvljP6cR/1.jpg', 'M罩杯兔女郎给你做胸推爽歪歪 CWPBD-107', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '市来美保', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101047, 'RvUvL4VK', '/RvUvL4VK/index.m3u8', '/RvUvL4VK/1.jpg', '好色妹子遇上外拍变态摄影师', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101048, 'kbDHYzcb', '/kbDHYzcb/index.m3u8', '/kbDHYzcb/1.jpg', '超可爱的巨乳小淫娃', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101049, 'PkCDQwyo', '/PkCDQwyo/index.m3u8', '/PkCDQwyo/1.jpg', '妹妹洗澡时偷偷把我叫进浴室', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101050, 'AvbqvWTD', '/AvbqvWTD/index.m3u8', '/AvbqvWTD/1.jpg', '永恒的不只有钻石，还有姐对你的爱', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101051, 'yNATgcK6', '/yNATgcK6/index.m3u8', '/yNATgcK6/1.jpg', '淫荡痴女喜欢舔舐大哥的肉棒 HEYZO-1836', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 96, '露娜', NULL, NULL, 0, 0, 0, '露娜', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101052, 'xOOimONt', '/xOOimONt/index.m3u8', '/xOOimONt/1.jpg', '路遇烂醉的白领妹子带回家抽插肉穴 071820_01', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 80, '上原正树', NULL, NULL, 0, 0, 0, '上原正树', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101053, 'Sn9mxq1D', '/Sn9mxq1D/index.m3u8', '/Sn9mxq1D/1.jpg', '舅舅太坏了直接强行插入我的下面', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101054, 'yVAEres3', '/yVAEres3/index.m3u8', '/yVAEres3/1.jpg', '淫妖虫 蚀 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101055, 'w2u8KzSA', '/w2u8KzSA/index.m3u8', '/w2u8KzSA/1.jpg', '火辣美女被肉棒直插子宫呻吟不止 011011-587', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '琥珀歌', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101056, 'xIjyLtfN', '/xIjyLtfN/index.m3u8', '/xIjyLtfN/1.jpg', '黑木耳湿嗒嗒两根大肉棒同时塞入 SKY-269', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 84, '椎名美优', NULL, NULL, 0, 0, 0, '椎名美优', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101057, 'raSEPJjz', '/raSEPJjz/index.m3u8', '/raSEPJjz/1.jpg', '小母狗好想要谁来满足她', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101058, 'fpa4pA6p', '/fpa4pA6p/index.m3u8', '/fpa4pA6p/1.jpg', '找女网友来家中追剧她却看上我的肉棒', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101059, 'RXO2qJRK', '/RXO2qJRK/index.m3u8', '/RXO2qJRK/1.jpg', '医生用精液治疗巨乳女孩', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101060, '1r3WiJCk', '/1r3WiJCk/index.m3u8', '/1r3WiJCk/1.jpg', '韩国混血美女和我直播裸聊', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101061, '8uCpXjwg', '/8uCpXjwg/index.m3u8', '/8uCpXjwg/1.jpg', '可怜的小美女没人操', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101062, '0corpPm9', '/0corpPm9/index.m3u8', '/0corpPm9/1.jpg', '我的Pico 3', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101063, 'mDYXbBwU', '/mDYXbBwU/index.m3u8', '/mDYXbBwU/1.jpg', '美女被两根肉棒连续无套中出爽到爆 HEYZO-0860', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 96, '露娜', NULL, NULL, 0, 0, 0, '露娜', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101064, 'VFAjARlj', '/VFAjARlj/index.m3u8', '/VFAjARlj/1.jpg', '巨乳妻子被侵犯过后就迷恋上了这种感觉', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101065, 'vWFb71rW', '/vWFb71rW/index.m3u8', '/vWFb71rW/1.jpg', '新人女主播珍珠勒逼内裤激情自慰', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101066, 'gH2IuZII', '/gH2IuZII/index.m3u8', '/gH2IuZII/1.jpg', '我污污的小妖精表姐', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101067, 'o2fUx7mT', '/o2fUx7mT/index.m3u8', '/o2fUx7mT/1.jpg', '女佣用嘴把我的脏肉棒舔干净', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101068, 'NY6iHQ9u', '/NY6iHQ9u/index.m3u8', '/NY6iHQ9u/1.jpg', '再害羞的妹妹也无法拒绝我的大屌', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101069, 'jMvvGYel', '/jMvvGYel/index.m3u8', '/jMvvGYel/1.jpg', '诱惑的热带深林身体 HEYZO-0371', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 88, '爱乃娜美', NULL, NULL, 0, 0, 0, '爱乃娜美', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101070, 'EMnPSZC1', '/EMnPSZC1/index.m3u8', '/EMnPSZC1/1.jpg', '翘臀美女随意舔弄操逼 123011-900', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 90, '真木今日子', NULL, NULL, 0, 0, 0, '真木今日子', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101071, '5A1dsA78', '/5A1dsA78/index.m3u8', '/5A1dsA78/1.jpg', '骚逼嫩妹玩弄自己的美鲍', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101072, 'X8JQmFdp', '/X8JQmFdp/index.m3u8', '/X8JQmFdp/1.jpg', '巨型肉棒差点顶到美女的子宫 081613-408', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 88, '爱乃娜美', NULL, NULL, 0, 0, 0, '爱乃娜美', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101073, 'CQt9XDog', '/CQt9XDog/index.m3u8', '/CQt9XDog/1.jpg', '圣诞老人被辣妈连榨两次精', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101074, 'DrlD2JEr', '/DrlD2JEr/index.m3u8', '/DrlD2JEr/1.jpg', 'BLOOD ROYAL Princess 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101075, 'ru2c8t75', '/ru2c8t75/index.m3u8', '/ru2c8t75/1.jpg', '可以随时抽插嫩穴的巨乳女仆 HEYZO-2016', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 80, '上原正树', NULL, NULL, 0, 0, 0, '上原正树', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101076, 'aKYdD8KI', '/aKYdD8KI/index.m3u8', '/aKYdD8KI/1.jpg', '风流少妇等弟弟插入', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101077, 'Fc4zyYtu', '/Fc4zyYtu/index.m3u8', '/Fc4zyYtu/1.jpg', '骚是她的本性快来操死她', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101078, 'jvX4JIXg', '/jvX4JIXg/index.m3u8', '/jvX4JIXg/1.jpg', '翘臀都快要顶到我的肉棒上了', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101079, 'pjvlU1va', '/pjvlU1va/index.m3u8', '/pjvlU1va/1.jpg', '路边搭讪美女干到骚穴流出白浆 HEYZO-1351', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 89, '田中美春', NULL, NULL, 0, 0, 0, '田中美春', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101080, 'L4qQaK7Z', '/L4qQaK7Z/index.m3u8', '/L4qQaK7Z/1.jpg', '中年大叔上门爆操少女嫩穴 101317_002', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '山崎麻里子', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101081, 'WJg7Ah4C', '/WJg7Ah4C/index.m3u8', '/WJg7Ah4C/1.jpg', '越是大力抽插越爽的婊子', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101082, '9prtveo3', '/9prtveo3/index.m3u8', '/9prtveo3/1.jpg', '骚货被好色按摩师挑逗的性欲大开 HEYZO-1659', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 96, '露娜', NULL, NULL, 0, 0, 0, '露娜', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101083, 'ENeEGLeb', '/ENeEGLeb/index.m3u8', '/ENeEGLeb/1.jpg', '约会美少女上门抽插少女粉嫩美鲍 083018_01', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '山崎麻里子', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101084, 'gpoMhIbM', '/gpoMhIbM/index.m3u8', '/gpoMhIbM/1.jpg', '爆操小萝莉', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101085, '1J1lC6l6', '/1J1lC6l6/index.m3u8', '/1J1lC6l6/1.jpg', '无套中出巨乳按摩女郎精液灌入子宫深处 CWPBD-145', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '安居茜', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101086, 'mpKF1FUS', '/mpKF1FUS/index.m3u8', '/mpKF1FUS/1.jpg', '趁着室友上班跟大奶女友在客厅打炮', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101087, 'fVbNJx4x', '/fVbNJx4x/index.m3u8', '/fVbNJx4x/1.jpg', '魔墮之夜 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101088, 'Cj07SvH9', '/Cj07SvH9/index.m3u8', '/Cj07SvH9/1.jpg', '淫荡痴女上门与部长激情啪啪啪 091318_01', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 89, '田中美春', NULL, NULL, 0, 0, 0, '田中美春', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101089, 'zS2WTmfn', '/zS2WTmfn/index.m3u8', '/zS2WTmfn/1.jpg', '姊姊换上性感睡衣要去找男友被我发现', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101090, 'sVnxXMub', '/sVnxXMub/index.m3u8', '/sVnxXMub/1.jpg', '母狗就该好好服务主人把精液全吞下', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101091, 'g8ngHhng', '/g8ngHhng/index.m3u8', '/g8ngHhng/1.jpg', '淫荡女神自慰寻求肉棒刺激 013114_761', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 88, '爱乃娜美', NULL, NULL, 0, 0, 0, '爱乃娜美', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101092, 'F9nYBeK2', '/F9nYBeK2/index.m3u8', '/F9nYBeK2/1.jpg', '一早醒来看到妹妹在旁边裸睡', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101093, 'VrtgwGFx', '/VrtgwGFx/index.m3u8', '/VrtgwGFx/1.jpg', '性感私部按摩的快感 HEYZO-2624', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 95, '长谷川美裸', NULL, NULL, 0, 0, 0, '长谷川美裸', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101094, 'Wkoybgfc', '/Wkoybgfc/index.m3u8', '/Wkoybgfc/1.jpg', '趁女友游戏插入呻吟给队友听', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101095, '3qv5SHbG', '/3qv5SHbG/index.m3u8', '/3qv5SHbG/1.jpg', 'M字腿娇喘不断的淫娃 070117-454', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 82, '木村美羽', NULL, NULL, 0, 0, 0, '木村美羽', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101096, 'OzDFKiIl', '/OzDFKiIl/index.m3u8', '/OzDFKiIl/1.jpg', '我的Pico 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101097, 'eLUcyaLH', '/eLUcyaLH/index.m3u8', '/eLUcyaLH/1.jpg', '我的Pico 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101098, 'UFfvLgWv', '/UFfvLgWv/index.m3u8', '/UFfvLgWv/1.jpg', '夜店巧遇女网红带回家狂肏', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101099, '5llJbbv3', '/5llJbbv3/index.m3u8', '/5llJbbv3/1.jpg', '甜蜜的声音来自于这个淫荡的少女', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101100, '07kHVXNI', '/07kHVXNI/index.m3u8', '/07kHVXNI/1.jpg', '舔一舔爸爸的大鸡巴', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101101, 'GfFFjG6t', '/GfFFjG6t/index.m3u8', '/GfFFjG6t/1.jpg', '无套内射大二学妹肛交喷水', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101102, 'r2UbTyZ7', '/r2UbTyZ7/index.m3u8', '/r2UbTyZ7/1.jpg', '身材姣好的女儿突然开始诱惑我', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101103, 'L1bIDHS6', '/L1bIDHS6/index.m3u8', '/L1bIDHS6/1.jpg', '淫荡的全裸按摩让痴女性欲觉醒 HEYZO-1938', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 92, '羽奈美鈴', NULL, NULL, 0, 0, 0, '羽奈美鈴', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101104, 'Kgjd8eev', '/Kgjd8eev/index.m3u8', '/Kgjd8eev/1.jpg', '你要抵抗我？这黑丝你怎么抵抗', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101105, '4MLI0x31', '/4MLI0x31/index.m3u8', '/4MLI0x31/1.jpg', '护士小姐姐舞蹈诱惑', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101106, 'q4nn9iry', '/q4nn9iry/index.m3u8', '/q4nn9iry/1.jpg', '喜欢捆绑操逼的浪女 n0939', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 87, '渡边美羽', NULL, NULL, 0, 0, 0, '渡边美羽', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101107, 'N11yAo7d', '/N11yAo7d/index.m3u8', '/N11yAo7d/1.jpg', '美少女与三个壮汉激情干炮精液射了一脸 061617_001', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '琥珀歌', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101108, 'OdqG6Tym', '/OdqG6Tym/index.m3u8', '/OdqG6Tym/1.jpg', '表姐直接把我扑到了床上', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101109, 'Bd51IgEh', '/Bd51IgEh/index.m3u8', '/Bd51IgEh/1.jpg', '大搞服装店老板娘', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101110, 'RHUjtLqk', '/RHUjtLqk/index.m3u8', '/RHUjtLqk/1.jpg', '好色妻子真的难以满足 SKY-288', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 87, '渡边美羽', NULL, NULL, 0, 0, 0, '渡边美羽', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101111, 'TbAfvJNS', '/TbAfvJNS/index.m3u8', '/TbAfvJNS/1.jpg', '新入职女职员在办公室伺候三根肉棒 SMBD-157', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '安居茜', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101112, 'CeJJ4gfC', '/CeJJ4gfC/index.m3u8', '/CeJJ4gfC/1.jpg', '淫妖虫 悦 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101113, 'uwS4xw0G', '/uwS4xw0G/index.m3u8', '/uwS4xw0G/1.jpg', '老公不在家邀年轻邻居来客厅做爱', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101114, 'x8NonU7n', '/x8NonU7n/index.m3u8', '/x8NonU7n/1.jpg', '主人的心情还好吗', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101115, 'ZDNd6CFE', '/ZDNd6CFE/index.m3u8', '/ZDNd6CFE/1.jpg', '拉着双马尾美少女的手后入狂插 030519-871', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 84, '椎名美优', NULL, NULL, 0, 0, 0, '椎名美优', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101116, '1sdb0uwd', '/1sdb0uwd/index.m3u8', '/1sdb0uwd/1.jpg', '美貌痴女淫荡的课外辅导 HEYZO-1401', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 84, '椎名美优', NULL, NULL, 0, 0, 0, '椎名美优', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101117, 'WhoIqVsK', '/WhoIqVsK/index.m3u8', '/WhoIqVsK/1.jpg', '爱上弟弟的浪荡表姐', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101118, 'b1IBj6o3', '/b1IBj6o3/index.m3u8', '/b1IBj6o3/1.jpg', '暴露癖美女性交想被对面大楼偷看 012419-846', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 84, '椎名美优', NULL, NULL, 0, 0, 0, '椎名美优', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101119, 'nKfVGl7I', '/nKfVGl7I/index.m3u8', '/nKfVGl7I/1.jpg', '大屌老师来告诉你什么是课后作业', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101120, 'M0y6icmI', '/M0y6icmI/index.m3u8', '/M0y6icmI/1.jpg', '淫妖虫 悦 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101121, 'JfYdiJbM', '/JfYdiJbM/index.m3u8', '/JfYdiJbM/1.jpg', '玩具伺候极品大骚逼 HEYZO-1669', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 95, '长谷川美裸', NULL, NULL, 0, 0, 0, '长谷川美裸', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101122, 'XXi6VfU3', '/XXi6VfU3/index.m3u8', '/XXi6VfU3/1.jpg', '爆乳姐姐乱伦失恋弟弟', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101123, 'vmKfBWwR', '/vmKfBWwR/index.m3u8', '/vmKfBWwR/1.jpg', '超级巨乳女郎大战两根肉棒爽翻天 113017_612', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '市来美保', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101124, 'r9UrMVLh', '/r9UrMVLh/index.m3u8', '/r9UrMVLh/1.jpg', '漂亮黑丝姐姐邀请我摧毁她的骚逼', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101125, 'Hos1nrND', '/Hos1nrND/index.m3u8', '/Hos1nrND/1.jpg', '嫂子喝醉回家把我当成姊夫诱惑', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101126, '2G1SmikR', '/2G1SmikR/index.m3u8', '/2G1SmikR/1.jpg', '火辣女老师在圣诞节勾引学生', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101127, 'mUwawzPt', '/mUwawzPt/index.m3u8', '/mUwawzPt/1.jpg', '黑丝大长腿顶级美女出没', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101128, 'LDbPktuZ', '/LDbPktuZ/index.m3u8', '/LDbPktuZ/1.jpg', '约会极品模特急速抽插嫩穴爽歪歪 021518_646', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 89, '田中美春', NULL, NULL, 0, 0, 0, '田中美春', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101129, 'dNke5FpW', '/dNke5FpW/index.m3u8', '/dNke5FpW/1.jpg', '玩具调教性感骚母狗 xxx-av 23429', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 85, '樱井梨花', NULL, NULL, 0, 0, 0, '樱井梨花', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101130, 'h9LBKHdT', '/h9LBKHdT/index.m3u8', '/h9LBKHdT/1.jpg', '中出裸体制服美少女 SKYHD-158', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 95, '长谷川美裸', NULL, NULL, 0, 0, 0, '长谷川美裸', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101131, 'NZnybsYp', '/NZnybsYp/index.m3u8', '/NZnybsYp/1.jpg', '无毛白虎妹子伺候大哥的黑棍淫水直流 k1373', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 83, '椎名柚子', NULL, NULL, 0, 0, 0, '椎名柚子', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101132, 'e4CfCNG5', '/e4CfCNG5/index.m3u8', '/e4CfCNG5/1.jpg', '潮喷个不停的长腿少女', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101133, 'xcKWMdGj', '/xcKWMdGj/index.m3u8', '/xcKWMdGj/1.jpg', '金发美女的无毛白虎被干到潮吹不断 072019_873', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 96, '露娜', NULL, NULL, 0, 0, 0, '露娜', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101134, 'YVg22Qf1', '/YVg22Qf1/index.m3u8', '/YVg22Qf1/1.jpg', '与儿时的老师疯狂做爱', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101135, 'Rxsdds1k', '/Rxsdds1k/index.m3u8', '/Rxsdds1k/1.jpg', '把钢管女郎囚禁起来在塞上口球', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101136, 'FOxT22OK', '/FOxT22OK/index.m3u8', '/FOxT22OK/1.jpg', '美女被两根肉棒操瘫了 032812-979', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 90, '真木今日子', NULL, NULL, 0, 0, 0, '真木今日子', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101137, 'qOc4OysK', '/qOc4OysK/index.m3u8', '/qOc4OysK/1.jpg', '粉嫩嫩的小美逼真是诱人', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101138, 'xd2sn44u', '/xd2sn44u/index.m3u8', '/xd2sn44u/1.jpg', '苗条小仙女被大长屌直入骚穴', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101139, 'UZjs1LNg', '/UZjs1LNg/index.m3u8', '/UZjs1LNg/1.jpg', '爆乳魅惑的骚浪美人 040512-986', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 90, '真木今日子', NULL, NULL, 0, 0, 0, '真木今日子', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101140, 'OPijmVdo', '/OPijmVdo/index.m3u8', '/OPijmVdo/1.jpg', '甜美美少女被大肉棒狂插 112015_428', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 94, '西川千寻', NULL, NULL, 0, 0, 0, '西川千寻', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101141, 'DFQxGwgU', '/DFQxGwgU/index.m3u8', '/DFQxGwgU/1.jpg', '浪逼骚妹妹不学习玩自慰', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101142, 'e3jNNhPv', '/e3jNNhPv/index.m3u8', '/e3jNNhPv/1.jpg', '18岁的粉逼萝莉', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101143, 'FVfXVbVY', '/FVfXVbVY/index.m3u8', '/FVfXVbVY/1.jpg', '淫荡母狗双洞齐开被大汉玩弄 xxx-av 23428', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 85, '樱井梨花', NULL, NULL, 0, 0, 0, '樱井梨花', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101144, 's617E5am', '/s617E5am/index.m3u8', '/s617E5am/1.jpg', '裸体按摩带来的快感 HEYZO-1953', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 82, '木村美羽', NULL, NULL, 0, 0, 0, '木村美羽', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101145, '8MY32WEl', '/8MY32WEl/index.m3u8', '/8MY32WEl/1.jpg', '巨乳人妻吞了春药后更骚了', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101146, 'QTp9Y0PY', '/QTp9Y0PY/index.m3u8', '/QTp9Y0PY/1.jpg', '魔墮之夜 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101147, 'HFV8a0S7', '/HFV8a0S7/index.m3u8', '/HFV8a0S7/1.jpg', '淫妖虫 蚀 -怀孕堕落的少女们-', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101148, 'yeee2MxP', '/yeee2MxP/index.m3u8', '/yeee2MxP/1.jpg', '风骚学妹宿舍内玩大秀', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101149, '9vyFyNaw', '/9vyFyNaw/index.m3u8', '/9vyFyNaw/1.jpg', '在山上狠肏女友浪穴', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101150, 'bSfUW4Rj', '/bSfUW4Rj/index.m3u8', '/bSfUW4Rj/1.jpg', '内射了客舱服务人员', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101151, 'IhTvHqtE', '/IhTvHqtE/index.m3u8', '/IhTvHqtE/1.jpg', '淫妖虫 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101152, 'IDJ9k6Py', '/IDJ9k6Py/index.m3u8', '/IDJ9k6Py/1.jpg', '巨乳秘书为了加薪出卖自己身体', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101153, 'aL5NMk73', '/aL5NMk73/index.m3u8', '/aL5NMk73/1.jpg', '小恶魔般的痴女看到肉棒就忍不住吞吐起来 053118_694', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 89, '田中美春', NULL, NULL, 0, 0, 0, '田中美春', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101154, '4IPYoFaS', '/4IPYoFaS/index.m3u8', '/4IPYoFaS/1.jpg', '品尝别人老婆的风骚小嫩逼 HEYZO-1281', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 87, '渡边美羽', NULL, NULL, 0, 0, 0, '渡边美羽', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101155, '6GO7G2e1', '/6GO7G2e1/index.m3u8', '/6GO7G2e1/1.jpg', '只要刷礼物就能体验的韩国女主播', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101156, 'oh417PY5', '/oh417PY5/index.m3u8', '/oh417PY5/1.jpg', '淫妖虫 蚀 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101157, '2fDMh8Z2', '/2fDMh8Z2/index.m3u8', '/2fDMh8Z2/1.jpg', '爆操巨乳痴女的无毛白虎骚穴 090319_894', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 80, '上原正树', NULL, NULL, 0, 0, 0, '上原正树', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101158, 'mnuTAEzd', '/mnuTAEzd/index.m3u8', '/mnuTAEzd/1.jpg', '大四女模为挣钱出卖肉体', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101159, 'hgEPvUBe', '/hgEPvUBe/index.m3u8', '/hgEPvUBe/1.jpg', '妹妹故意把洋装剪破若隐若现的诱惑', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101160, 'MLkvBbmw', '/MLkvBbmw/index.m3u8', '/MLkvBbmw/1.jpg', '和兄弟换老婆操爽翻了', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101161, '3V5L7n4g', '/3V5L7n4g/index.m3u8', '/3V5L7n4g/1.jpg', '3秒喷水速度进来撸', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101162, 'P3PlckhR', '/P3PlckhR/index.m3u8', '/P3PlckhR/1.jpg', '好友的性癖是看着老婆被人肏翻', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101163, 'lwPK6V5t', '/lwPK6V5t/index.m3u8', '/lwPK6V5t/1.jpg', '少女丝袜高跟的诱惑', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101164, 'H93ekhCE', '/H93ekhCE/index.m3u8', '/H93ekhCE/1.jpg', '巨乳熟女操的奶子乱耸 111318_768', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 93, '荒木梨奈', NULL, NULL, 0, 0, 0, '荒木梨奈', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101165, 'bg9UTtpY', '/bg9UTtpY/index.m3u8', '/bg9UTtpY/1.jpg', '巨乳痴女饥渴难耐只能用玩具解决自己的性欲 SKY-272', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 86, '泷川索菲亚', NULL, NULL, 0, 0, 0, '泷川索菲亚', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101166, 'r6hOpUC9', '/r6hOpUC9/index.m3u8', '/r6hOpUC9/1.jpg', '小骚货不远万里求操', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101167, 'LW9Q3WkE', '/LW9Q3WkE/index.m3u8', '/LW9Q3WkE/1.jpg', '淫妖虫 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101168, 'hCLzhTW4', '/hCLzhTW4/index.m3u8', '/hCLzhTW4/1.jpg', '姐姐☆怀孕MIX 3', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101169, 'iiQuijCw', '/iiQuijCw/index.m3u8', '/iiQuijCw/1.jpg', '姐姐☆怀孕MIX 4', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101170, 'RROG3LAs', '/RROG3LAs/index.m3u8', '/RROG3LAs/1.jpg', '用淫语和骑乘位来款待男粉丝', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101171, 'E4pdIrNa', '/E4pdIrNa/index.m3u8', '/E4pdIrNa/1.jpg', '吹着海风操着逼 SMBD-59', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 90, '真木今日子', NULL, NULL, 0, 0, 0, '真木今日子', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101172, 'wPaREORn', '/wPaREORn/index.m3u8', '/wPaREORn/1.jpg', '女系家族 ～淫谋～ 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101173, 'XUYZjSFu', '/XUYZjSFu/index.m3u8', '/XUYZjSFu/1.jpg', '巨乳继母抓到正在对着自己手淫的儿子', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101174, 'LTxqByxn', '/LTxqByxn/index.m3u8', '/LTxqByxn/1.jpg', '无套中出梦幻级巨乳女郎阴道精液大量流出 110917_603', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '市来美保', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101175, '15uAFSfs', '/15uAFSfs/index.m3u8', '/15uAFSfs/1.jpg', '用咪咪给你乳交噢 HEYZO-2184', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 87, '渡边美羽', NULL, NULL, 0, 0, 0, '渡边美羽', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101176, 'hhE8iW5T', '/hhE8iW5T/index.m3u8', '/hhE8iW5T/1.jpg', '小萝莉想要吃香蕉', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101177, 'GftlR7hg', '/GftlR7hg/index.m3u8', '/GftlR7hg/1.jpg', '69姿势互舔小骚逼水流不断 SKYHD-120', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 88, '爱乃娜美', NULL, NULL, 0, 0, 0, '爱乃娜美', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101178, 'ikF84NUX', '/ikF84NUX/index.m3u8', '/ikF84NUX/1.jpg', '放学后的淫荡美少女 HEYZO-1060', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 91, '绫森市香', NULL, NULL, 0, 0, 0, '绫森市香', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101179, 'Ijt9IGJK', '/Ijt9IGJK/index.m3u8', '/Ijt9IGJK/1.jpg', '黝黑巨乳女孩的紧致鲍鱼夹紧肉棒', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101180, 'ZZSdDUAh', '/ZZSdDUAh/index.m3u8', '/ZZSdDUAh/1.jpg', '水手服纯情少女性欲大爆发 n1166', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 83, '椎名柚子', NULL, NULL, 0, 0, 0, '椎名柚子', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101181, 'pLfZvBec', '/pLfZvBec/index.m3u8', '/pLfZvBec/1.jpg', '倒立劈叉技能多多小姐姐激情大秀', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101182, 'mPWern0o', '/mPWern0o/index.m3u8', '/mPWern0o/1.jpg', '骚货嘴里浪叫个不停', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101183, 'VNrRZWZn', '/VNrRZWZn/index.m3u8', '/VNrRZWZn/1.jpg', '特务搜查官小玲＆风子 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101184, 'ygEwSC9v', '/ygEwSC9v/index.m3u8', '/ygEwSC9v/1.jpg', '少妇一颦一笑都带着诱惑太迷人了', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101185, 'fNwUZX6w', '/fNwUZX6w/index.m3u8', '/fNwUZX6w/1.jpg', '堕落人妻沦为别人的性奴隶', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101186, 'syNIxVWc', '/syNIxVWc/index.m3u8', '/syNIxVWc/1.jpg', '肉肉的翘臀一不小心坐在鸡巴上了', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101187, 'dBliUiH9', '/dBliUiH9/index.m3u8', '/dBliUiH9/1.jpg', '兼职学妹送福利', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101188, 'Lph6C8rV', '/Lph6C8rV/index.m3u8', '/Lph6C8rV/1.jpg', '极品骚女被男人爆操 SKY-330', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 95, '长谷川美裸', NULL, NULL, 0, 0, 0, '长谷川美裸', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101189, 'kwFJx6pQ', '/kwFJx6pQ/index.m3u8', '/kwFJx6pQ/1.jpg', '继母逼我把她干爽才肯做饭', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101190, '2dRwISqM', '/2dRwISqM/index.m3u8', '/2dRwISqM/1.jpg', '小哥狂插小母狗', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101191, 'rWTW7Mnr', '/rWTW7Mnr/index.m3u8', '/rWTW7Mnr/1.jpg', '疯狂抽插风骚少妇的无毛白虎肉穴 090820-001', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 96, '露娜', NULL, NULL, 0, 0, 0, '露娜', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101192, '6uIdf1Lv', '/6uIdf1Lv/index.m3u8', '/6uIdf1Lv/1.jpg', '活色生香的大肉棒素描 022718-611', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 94, '西川千寻', NULL, NULL, 0, 0, 0, '西川千寻', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101193, 'tETuzCAe', '/tETuzCAe/index.m3u8', '/tETuzCAe/1.jpg', 'BLOOD ROYAL Princess 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101194, 'h4opnqmi', '/h4opnqmi/index.m3u8', '/h4opnqmi/1.jpg', '少妇姐姐找大鸡巴情人', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101195, 'kTWy2rC0', '/kTWy2rC0/index.m3u8', '/kTWy2rC0/1.jpg', '颜射极致身材军装小骚货 HEYZO-0415', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 88, '爱乃娜美', NULL, NULL, 0, 0, 0, '爱乃娜美', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101196, 'DjqeSlea', '/DjqeSlea/index.m3u8', '/DjqeSlea/1.jpg', '美女敏感体质连续高潮 051518-666', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 94, '西川千寻', NULL, NULL, 0, 0, 0, '西川千寻', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101197, 'j0tHqc0j', '/j0tHqc0j/index.m3u8', '/j0tHqc0j/1.jpg', '男优的巨屌满足痴女淫荡的性欲 HEYZO-1287', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '安居茜', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101198, '0uo8g342', '/0uo8g342/index.m3u8', '/0uo8g342/1.jpg', '不操烂这个小逼逼都对不起自己的大肉棒', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101199, 'RKpysZCQ', '/RKpysZCQ/index.m3u8', '/RKpysZCQ/1.jpg', '成熟少妇被小哥无套内射', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101200, 'uhjkwqsf', '/uhjkwqsf/index.m3u8', '/uhjkwqsf/1.jpg', '从厨房操到沙发的淫荡美人 HEYZO-1455', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 95, '长谷川美裸', NULL, NULL, 0, 0, 0, '长谷川美裸', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101201, 'QWAj4FYv', '/QWAj4FYv/index.m3u8', '/QWAj4FYv/1.jpg', '接受了一根大肉棒进入到了小穴中', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101202, '1dJEIYtX', '/1dJEIYtX/index.m3u8', '/1dJEIYtX/1.jpg', '能从容三连发的极品女神 LAFBD-60', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 82, '木村美羽', NULL, NULL, 0, 0, 0, '木村美羽', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101203, 'ZynLZbcn', '/ZynLZbcn/index.m3u8', '/ZynLZbcn/1.jpg', '瑜伽女孩的自慰日常', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101204, 'qnRgFhkb', '/qnRgFhkb/index.m3u8', '/qnRgFhkb/1.jpg', '女系家族III ～秘密HIMITSU卑蜜～', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101205, 'D0M95QGv', '/D0M95QGv/index.m3u8', '/D0M95QGv/1.jpg', '模特身材御姐范的骚货老师', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101206, 'bOMtjHdl', '/bOMtjHdl/index.m3u8', '/bOMtjHdl/1.jpg', '清纯学生妹子被三根肉棒连续阴道中出 n0757', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 81, '京野七夏', NULL, NULL, 0, 0, 0, '京野七夏', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101207, 'UIv0Yitq', '/UIv0Yitq/index.m3u8', '/UIv0Yitq/1.jpg', '三个美少女与两个壮汉的集体大乱交 072311-759', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '琥珀歌', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101208, '4OtQcisM', '/4OtQcisM/index.m3u8', '/4OtQcisM/1.jpg', '巨乳妻子无法抗拒私人教练的肉体', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101209, 'TvKMB5Sd', '/TvKMB5Sd/index.m3u8', '/TvKMB5Sd/1.jpg', '学姐的小嫩逼好湿啊啊啊啊', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101210, 'iqIFe3Gg', '/iqIFe3Gg/index.m3u8', '/iqIFe3Gg/1.jpg', '女系家族 ～淫谋～ 2', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101211, '0xXTNEai', '/0xXTNEai/index.m3u8', '/0xXTNEai/1.jpg', '美术部淫荡的秘密 042616_287', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 94, '西川千寻', NULL, NULL, 0, 0, 0, '西川千寻', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101212, 'k5oC5bcJ', '/k5oC5bcJ/index.m3u8', '/k5oC5bcJ/1.jpg', '饥渴的痴女用各种玩具抽插自己淫水直流的骚穴 HEYZO-1043', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '琥珀歌', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101213, 'ULnCdAl7', '/ULnCdAl7/index.m3u8', '/ULnCdAl7/1.jpg', '饥渴的骚货主动索取肉棒的插入 SKYHD-110', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 86, '泷川索菲亚', NULL, NULL, 0, 0, 0, '泷川索菲亚', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101214, 'B0yDR4PD', '/B0yDR4PD/index.m3u8', '/B0yDR4PD/1.jpg', '内射香汗淋漓的绝色美女 040718-637', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 94, '西川千寻', NULL, NULL, 0, 0, 0, '西川千寻', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101215, 'ZIWjgR2V', '/ZIWjgR2V/index.m3u8', '/ZIWjgR2V/1.jpg', '小母狗渴望主人赐予她精液', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101216, 'l95b4i0s', '/l95b4i0s/index.m3u8', '/l95b4i0s/1.jpg', '用真假两根肉棒才能满足淫荡小母狗的性欲 100819_911', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 80, '上原正树', NULL, NULL, 0, 0, 0, '上原正树', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101217, 'cpQceK2O', '/cpQceK2O/index.m3u8', '/cpQceK2O/1.jpg', '主播约粉丝来家里放飞自己', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101218, 'FzmthXY4', '/FzmthXY4/index.m3u8', '/FzmthXY4/1.jpg', '大奶女乘客在后座诱惑师机', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101219, '8oRIQbcG', '/8oRIQbcG/index.m3u8', '/8oRIQbcG/1.jpg', '特务搜查官小玲＆风子 1', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101220, '86scGKfP', '/86scGKfP/index.m3u8', '/86scGKfP/1.jpg', '疯狂索取肉棒的梦中女神 SMBD-66', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 90, '真木今日子', NULL, NULL, 0, 0, 0, '真木今日子', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101221, 'yKA3tMZt', '/yKA3tMZt/index.m3u8', '/yKA3tMZt/1.jpg', '圣诞女孩练习用骚穴夹紧肉棒', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101222, 'dddbnwPZ', '/dddbnwPZ/index.m3u8', '/dddbnwPZ/1.jpg', '办公室的骚货被玩弄到浑身抽搐尖叫不止 n0945', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '琥珀歌', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101223, 'KkgrylaP', '/KkgrylaP/index.m3u8', '/KkgrylaP/1.jpg', '特务搜查官小玲＆风子 3', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '日本动画', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101224, 'k2orG2Ta', '/k2orG2Ta/index.m3u8', '/k2orG2Ta/1.jpg', '在公共场合尽情做爱的AV主播', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101225, 'iCpgJNAq', '/iCpgJNAq/index.m3u8', '/iCpgJNAq/1.jpg', '看着自己的妻子被陌生人肏翻', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 11, 'misslexa', NULL, NULL, 0, 0, 0, 'misslexa', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101226, 'sdC0KjZs', '/sdC0KjZs/index.m3u8', '/sdC0KjZs/1.jpg', '骑乘爆操开裆小骚货萌妹子 020318-597', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 94, '西川千寻', NULL, NULL, 0, 0, 0, '西川千寻', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101227, '4y0FuzAl', '/4y0FuzAl/index.m3u8', '/4y0FuzAl/1.jpg', '水灵灵的粉色小萝莉', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101228, 'jY0Ap02N', '/jY0Ap02N/index.m3u8', '/jY0Ap02N/1.jpg', '痴女乘骑位用骚穴榨取肉棒精液 022522_003', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 96, '露娜', NULL, NULL, 0, 0, 0, '露娜', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101229, 'EqSTGKBE', '/EqSTGKBE/index.m3u8', '/EqSTGKBE/1.jpg', '好玩又刺激的退役空姐', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 0, '未知', 'unknown', 'unknown', 0, 0, 0, '口罩系列', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101230, 'qnvPwcDi', '/qnvPwcDi/index.m3u8', '/qnvPwcDi/1.jpg', '喜欢上了隔壁的阿姨', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 70, '郑艾莉', NULL, NULL, 0, 0, 0, '郑艾莉', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101231, 'njRMLtLM', '/njRMLtLM/index.m3u8', '/njRMLtLM/1.jpg', '巨乳美女的淫荡性交 SMBD-97', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 93, '荒木梨奈', NULL, NULL, 0, 0, 0, '荒木梨奈', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');
INSERT INTO `kpn_movie` VALUES (101232, 'R8KjDJu0', '/R8KjDJu0/index.m3u8', '/R8KjDJu0/1.jpg', '巨乳骚货给大哥的肉棒进行口交服务 040420_01', NULL, NULL, NULL, 0, 0, 0, '0', NULL, NULL, '2023-03-07 22:01:09', 80, '上原正树', NULL, NULL, 0, 0, 0, '上原正树', 0, 0, '2023-03-07 22:01:09', '2023-03-13 13:16:40', 'admin', 'admin');

-- ----------------------------
-- Table structure for kpn_movie_tag
-- ----------------------------
DROP TABLE IF EXISTS `kpn_movie_tag`;
CREATE TABLE `kpn_movie_tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `movie_id` bigint(20) NULL DEFAULT NULL COMMENT '影片id',
  `tag_id` bigint(20) NULL DEFAULT NULL COMMENT '标签id',
  `tag_category_id` bigint(20) NULL DEFAULT NULL COMMENT '标签分类id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_movieid_tagid`(`movie_id`, `tag_id`) USING BTREE COMMENT '唯一索引',
  INDEX `idx_tagid`(`tag_id`) USING BTREE COMMENT '按标签统计'
) ENGINE = InnoDB AUTO_INCREMENT = 374 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kpn_movie_tag
-- ----------------------------
INSERT INTO `kpn_movie_tag` VALUES (1, 100285, 100000, 100000);
INSERT INTO `kpn_movie_tag` VALUES (2, 100289, 100001, 100000);
INSERT INTO `kpn_movie_tag` VALUES (3, 100106, 100002, 100000);
INSERT INTO `kpn_movie_tag` VALUES (4, 100205, 100003, 100000);
INSERT INTO `kpn_movie_tag` VALUES (5, 100072, 100004, 100000);
INSERT INTO `kpn_movie_tag` VALUES (6, 100003, 100005, 100001);
INSERT INTO `kpn_movie_tag` VALUES (7, 100274, 100006, 100001);
INSERT INTO `kpn_movie_tag` VALUES (8, 100049, 100000, 100000);
INSERT INTO `kpn_movie_tag` VALUES (9, 100287, 100001, 100000);
INSERT INTO `kpn_movie_tag` VALUES (10, 100064, 100002, 100000);
INSERT INTO `kpn_movie_tag` VALUES (11, 100065, 100003, 100000);
INSERT INTO `kpn_movie_tag` VALUES (12, 100296, 100004, 100000);
INSERT INTO `kpn_movie_tag` VALUES (13, 100110, 100005, 100001);
INSERT INTO `kpn_movie_tag` VALUES (14, 100025, 100006, 100001);
INSERT INTO `kpn_movie_tag` VALUES (15, 100159, 100000, 100000);
INSERT INTO `kpn_movie_tag` VALUES (16, 100143, 100001, 100000);
INSERT INTO `kpn_movie_tag` VALUES (17, 100303, 100002, 100000);
INSERT INTO `kpn_movie_tag` VALUES (18, 100082, 100003, 100000);
INSERT INTO `kpn_movie_tag` VALUES (19, 100092, 100004, 100000);
INSERT INTO `kpn_movie_tag` VALUES (20, 100292, 100005, 100001);
INSERT INTO `kpn_movie_tag` VALUES (21, 100307, 100006, 100001);
INSERT INTO `kpn_movie_tag` VALUES (22, 100275, 100000, 100000);
INSERT INTO `kpn_movie_tag` VALUES (23, 100059, 100001, 100000);
INSERT INTO `kpn_movie_tag` VALUES (24, 100277, 100002, 100000);
INSERT INTO `kpn_movie_tag` VALUES (25, 100012, 100003, 100000);
INSERT INTO `kpn_movie_tag` VALUES (26, 100099, 100004, 100000);
INSERT INTO `kpn_movie_tag` VALUES (27, 100134, 100005, 100001);
INSERT INTO `kpn_movie_tag` VALUES (28, 100004, 100006, 100001);
INSERT INTO `kpn_movie_tag` VALUES (29, 100165, 100007, 100001);
INSERT INTO `kpn_movie_tag` VALUES (30, 100178, 100008, 100002);
INSERT INTO `kpn_movie_tag` VALUES (31, 100044, 100009, 100002);
INSERT INTO `kpn_movie_tag` VALUES (32, 100173, 100010, 100003);
INSERT INTO `kpn_movie_tag` VALUES (33, 100115, 100011, 100003);
INSERT INTO `kpn_movie_tag` VALUES (34, 100085, 100012, 100004);
INSERT INTO `kpn_movie_tag` VALUES (35, 100298, 100013, 100004);
INSERT INTO `kpn_movie_tag` VALUES (36, 100174, 100014, 100005);
INSERT INTO `kpn_movie_tag` VALUES (37, 100042, 100015, 100005);
INSERT INTO `kpn_movie_tag` VALUES (38, 100075, 100007, 100001);
INSERT INTO `kpn_movie_tag` VALUES (39, 100197, 100008, 100002);
INSERT INTO `kpn_movie_tag` VALUES (40, 100297, 100009, 100002);
INSERT INTO `kpn_movie_tag` VALUES (41, 100113, 100010, 100003);
INSERT INTO `kpn_movie_tag` VALUES (42, 100132, 100011, 100003);
INSERT INTO `kpn_movie_tag` VALUES (43, 100053, 100012, 100004);
INSERT INTO `kpn_movie_tag` VALUES (44, 100145, 100013, 100004);
INSERT INTO `kpn_movie_tag` VALUES (45, 100175, 100014, 100005);
INSERT INTO `kpn_movie_tag` VALUES (46, 100137, 100015, 100005);
INSERT INTO `kpn_movie_tag` VALUES (47, 100125, 100007, 100001);
INSERT INTO `kpn_movie_tag` VALUES (48, 100151, 100008, 100002);
INSERT INTO `kpn_movie_tag` VALUES (49, 100286, 100009, 100002);
INSERT INTO `kpn_movie_tag` VALUES (50, 100138, 100010, 100003);
INSERT INTO `kpn_movie_tag` VALUES (51, 100200, 100011, 100003);
INSERT INTO `kpn_movie_tag` VALUES (52, 100060, 100012, 100004);
INSERT INTO `kpn_movie_tag` VALUES (53, 100290, 100013, 100004);
INSERT INTO `kpn_movie_tag` VALUES (54, 100164, 100014, 100005);
INSERT INTO `kpn_movie_tag` VALUES (55, 100045, 100015, 100005);
INSERT INTO `kpn_movie_tag` VALUES (56, 100157, 100007, 100001);
INSERT INTO `kpn_movie_tag` VALUES (57, 100037, 100008, 100002);
INSERT INTO `kpn_movie_tag` VALUES (58, 100087, 100009, 100002);
INSERT INTO `kpn_movie_tag` VALUES (59, 100163, 100010, 100003);
INSERT INTO `kpn_movie_tag` VALUES (60, 100041, 100011, 100003);
INSERT INTO `kpn_movie_tag` VALUES (61, 100093, 100012, 100004);
INSERT INTO `kpn_movie_tag` VALUES (62, 100189, 100013, 100004);
INSERT INTO `kpn_movie_tag` VALUES (63, 100185, 100014, 100005);
INSERT INTO `kpn_movie_tag` VALUES (64, 100276, 100015, 100005);
INSERT INTO `kpn_movie_tag` VALUES (65, 100083, 100007, 100001);
INSERT INTO `kpn_movie_tag` VALUES (66, 100141, 100008, 100002);
INSERT INTO `kpn_movie_tag` VALUES (67, 100301, 100009, 100002);
INSERT INTO `kpn_movie_tag` VALUES (68, 100139, 100010, 100003);
INSERT INTO `kpn_movie_tag` VALUES (69, 100186, 100011, 100003);
INSERT INTO `kpn_movie_tag` VALUES (70, 100031, 100012, 100004);
INSERT INTO `kpn_movie_tag` VALUES (71, 100162, 100013, 100004);
INSERT INTO `kpn_movie_tag` VALUES (72, 100029, 100014, 100005);
INSERT INTO `kpn_movie_tag` VALUES (73, 100131, 100015, 100005);
INSERT INTO `kpn_movie_tag` VALUES (74, 100101, 100007, 100001);
INSERT INTO `kpn_movie_tag` VALUES (75, 100094, 100008, 100002);
INSERT INTO `kpn_movie_tag` VALUES (76, 100096, 100009, 100002);
INSERT INTO `kpn_movie_tag` VALUES (77, 100088, 100010, 100003);
INSERT INTO `kpn_movie_tag` VALUES (78, 100105, 100011, 100003);
INSERT INTO `kpn_movie_tag` VALUES (79, 100040, 100012, 100004);
INSERT INTO `kpn_movie_tag` VALUES (80, 100089, 100013, 100004);
INSERT INTO `kpn_movie_tag` VALUES (81, 100282, 100014, 100005);
INSERT INTO `kpn_movie_tag` VALUES (82, 100180, 100015, 100005);
INSERT INTO `kpn_movie_tag` VALUES (83, 100300, 100007, 100001);
INSERT INTO `kpn_movie_tag` VALUES (84, 100021, 100008, 100002);
INSERT INTO `kpn_movie_tag` VALUES (85, 100299, 100009, 100002);
INSERT INTO `kpn_movie_tag` VALUES (86, 100056, 100010, 100003);
INSERT INTO `kpn_movie_tag` VALUES (87, 100154, 100011, 100003);
INSERT INTO `kpn_movie_tag` VALUES (88, 100202, 100012, 100004);
INSERT INTO `kpn_movie_tag` VALUES (89, 100153, 100013, 100004);
INSERT INTO `kpn_movie_tag` VALUES (90, 100198, 100014, 100005);
INSERT INTO `kpn_movie_tag` VALUES (91, 100035, 100015, 100005);
INSERT INTO `kpn_movie_tag` VALUES (92, 100066, 100016, 100006);
INSERT INTO `kpn_movie_tag` VALUES (93, 100010, 100017, 100006);
INSERT INTO `kpn_movie_tag` VALUES (94, 100078, 100018, 100007);
INSERT INTO `kpn_movie_tag` VALUES (95, 100179, 100019, 100008);
INSERT INTO `kpn_movie_tag` VALUES (96, 100148, 100020, 100009);
INSERT INTO `kpn_movie_tag` VALUES (97, 100117, 100016, 100006);
INSERT INTO `kpn_movie_tag` VALUES (98, 100015, 100017, 100006);
INSERT INTO `kpn_movie_tag` VALUES (99, 100112, 100018, 100007);
INSERT INTO `kpn_movie_tag` VALUES (100, 100127, 100019, 100008);
INSERT INTO `kpn_movie_tag` VALUES (101, 100149, 100020, 100009);
INSERT INTO `kpn_movie_tag` VALUES (102, 100102, 100016, 100006);
INSERT INTO `kpn_movie_tag` VALUES (103, 100098, 100017, 100006);
INSERT INTO `kpn_movie_tag` VALUES (104, 100169, 100018, 100007);
INSERT INTO `kpn_movie_tag` VALUES (105, 100018, 100019, 100008);
INSERT INTO `kpn_movie_tag` VALUES (106, 100070, 100020, 100009);
INSERT INTO `kpn_movie_tag` VALUES (107, 100068, 100016, 100006);
INSERT INTO `kpn_movie_tag` VALUES (108, 100176, 100017, 100006);
INSERT INTO `kpn_movie_tag` VALUES (109, 100069, 100018, 100007);
INSERT INTO `kpn_movie_tag` VALUES (110, 100196, 100019, 100008);
INSERT INTO `kpn_movie_tag` VALUES (111, 100184, 100020, 100009);
INSERT INTO `kpn_movie_tag` VALUES (112, 100201, 100003, 100000);
INSERT INTO `kpn_movie_tag` VALUES (113, 100062, 100003, 100000);
INSERT INTO `kpn_movie_tag` VALUES (114, 100091, 100005, 100001);
INSERT INTO `kpn_movie_tag` VALUES (115, 100067, 100005, 100001);
INSERT INTO `kpn_movie_tag` VALUES (116, 100294, 100007, 100001);
INSERT INTO `kpn_movie_tag` VALUES (117, 100061, 100007, 100001);
INSERT INTO `kpn_movie_tag` VALUES (118, 100309, 100003, 100000);
INSERT INTO `kpn_movie_tag` VALUES (119, 100191, 100010, 100003);
INSERT INTO `kpn_movie_tag` VALUES (120, 100027, 100005, 100001);
INSERT INTO `kpn_movie_tag` VALUES (121, 100291, 100012, 100004);
INSERT INTO `kpn_movie_tag` VALUES (122, 100121, 100007, 100001);
INSERT INTO `kpn_movie_tag` VALUES (123, 100187, 100003, 100000);
INSERT INTO `kpn_movie_tag` VALUES (124, 100119, 100015, 100005);
INSERT INTO `kpn_movie_tag` VALUES (125, 100063, 100005, 100001);
INSERT INTO `kpn_movie_tag` VALUES (126, 100203, 100003, 100000);
INSERT INTO `kpn_movie_tag` VALUES (127, 100020, 100007, 100001);
INSERT INTO `kpn_movie_tag` VALUES (128, 100204, 100019, 100008);
INSERT INTO `kpn_movie_tag` VALUES (129, 100100, 100019, 100008);
INSERT INTO `kpn_movie_tag` VALUES (130, 100166, 100010, 100003);
INSERT INTO `kpn_movie_tag` VALUES (131, 100104, 100003, 100000);
INSERT INTO `kpn_movie_tag` VALUES (132, 100278, 100012, 100004);
INSERT INTO `kpn_movie_tag` VALUES (133, 100271, 100005, 100001);
INSERT INTO `kpn_movie_tag` VALUES (134, 100302, 100019, 100008);
INSERT INTO `kpn_movie_tag` VALUES (135, 100073, 100007, 100001);
INSERT INTO `kpn_movie_tag` VALUES (136, 100130, 100006, 100001);
INSERT INTO `kpn_movie_tag` VALUES (137, 100039, 100007, 100001);
INSERT INTO `kpn_movie_tag` VALUES (138, 100190, 100008, 100002);
INSERT INTO `kpn_movie_tag` VALUES (139, 100308, 100009, 100002);
INSERT INTO `kpn_movie_tag` VALUES (140, 100011, 100010, 100003);
INSERT INTO `kpn_movie_tag` VALUES (141, 100002, 100011, 100003);
INSERT INTO `kpn_movie_tag` VALUES (142, 100195, 100012, 100004);
INSERT INTO `kpn_movie_tag` VALUES (143, 100050, 100013, 100004);
INSERT INTO `kpn_movie_tag` VALUES (144, 100116, 100006, 100001);
INSERT INTO `kpn_movie_tag` VALUES (145, 100194, 100007, 100001);
INSERT INTO `kpn_movie_tag` VALUES (146, 100000, 100008, 100002);
INSERT INTO `kpn_movie_tag` VALUES (147, 100306, 100009, 100002);
INSERT INTO `kpn_movie_tag` VALUES (148, 100152, 100010, 100003);
INSERT INTO `kpn_movie_tag` VALUES (149, 100036, 100011, 100003);
INSERT INTO `kpn_movie_tag` VALUES (150, 100129, 100012, 100004);
INSERT INTO `kpn_movie_tag` VALUES (151, 100014, 100013, 100004);
INSERT INTO `kpn_movie_tag` VALUES (152, 100032, 100006, 100001);
INSERT INTO `kpn_movie_tag` VALUES (153, 100107, 100007, 100001);
INSERT INTO `kpn_movie_tag` VALUES (154, 100114, 100008, 100002);
INSERT INTO `kpn_movie_tag` VALUES (155, 100182, 100009, 100002);
INSERT INTO `kpn_movie_tag` VALUES (156, 100124, 100010, 100003);
INSERT INTO `kpn_movie_tag` VALUES (157, 100310, 100011, 100003);
INSERT INTO `kpn_movie_tag` VALUES (158, 100023, 100012, 100004);
INSERT INTO `kpn_movie_tag` VALUES (159, 100080, 100013, 100004);
INSERT INTO `kpn_movie_tag` VALUES (160, 100126, 100006, 100001);
INSERT INTO `kpn_movie_tag` VALUES (161, 100177, 100007, 100001);
INSERT INTO `kpn_movie_tag` VALUES (162, 100170, 100008, 100002);
INSERT INTO `kpn_movie_tag` VALUES (163, 100047, 100009, 100002);
INSERT INTO `kpn_movie_tag` VALUES (164, 100103, 100010, 100003);
INSERT INTO `kpn_movie_tag` VALUES (165, 100156, 100011, 100003);
INSERT INTO `kpn_movie_tag` VALUES (166, 100140, 100012, 100004);
INSERT INTO `kpn_movie_tag` VALUES (167, 100074, 100013, 100004);
INSERT INTO `kpn_movie_tag` VALUES (168, 100058, 100006, 100001);
INSERT INTO `kpn_movie_tag` VALUES (169, 100136, 100007, 100001);
INSERT INTO `kpn_movie_tag` VALUES (170, 100109, 100008, 100002);
INSERT INTO `kpn_movie_tag` VALUES (171, 100006, 100009, 100002);
INSERT INTO `kpn_movie_tag` VALUES (172, 100284, 100010, 100003);
INSERT INTO `kpn_movie_tag` VALUES (173, 100193, 100011, 100003);
INSERT INTO `kpn_movie_tag` VALUES (174, 100147, 100012, 100004);
INSERT INTO `kpn_movie_tag` VALUES (175, 100033, 100013, 100004);
INSERT INTO `kpn_movie_tag` VALUES (176, 100022, 100011, 100003);
INSERT INTO `kpn_movie_tag` VALUES (177, 100167, 100012, 100004);
INSERT INTO `kpn_movie_tag` VALUES (178, 100158, 100013, 100004);
INSERT INTO `kpn_movie_tag` VALUES (179, 100086, 100000, 100000);
INSERT INTO `kpn_movie_tag` VALUES (180, 100019, 100001, 100000);
INSERT INTO `kpn_movie_tag` VALUES (181, 100293, 100002, 100000);
INSERT INTO `kpn_movie_tag` VALUES (182, 100079, 100003, 100000);
INSERT INTO `kpn_movie_tag` VALUES (183, 100172, 100004, 100000);
INSERT INTO `kpn_movie_tag` VALUES (184, 100108, 100005, 100001);
INSERT INTO `kpn_movie_tag` VALUES (185, 100008, 100006, 100001);
INSERT INTO `kpn_movie_tag` VALUES (186, 100057, 100007, 100001);
INSERT INTO `kpn_movie_tag` VALUES (187, 100052, 100008, 100002);
INSERT INTO `kpn_movie_tag` VALUES (188, 100142, 100009, 100002);
INSERT INTO `kpn_movie_tag` VALUES (189, 100024, 100010, 100003);
INSERT INTO `kpn_movie_tag` VALUES (190, 100001, 100011, 100003);
INSERT INTO `kpn_movie_tag` VALUES (191, 100030, 100012, 100004);
INSERT INTO `kpn_movie_tag` VALUES (192, 100007, 100013, 100004);
INSERT INTO `kpn_movie_tag` VALUES (193, 100081, 100014, 100005);
INSERT INTO `kpn_movie_tag` VALUES (194, 100028, 100015, 100005);
INSERT INTO `kpn_movie_tag` VALUES (195, 100123, 100016, 100006);
INSERT INTO `kpn_movie_tag` VALUES (196, 100283, 100017, 100006);
INSERT INTO `kpn_movie_tag` VALUES (197, 100077, 100018, 100007);
INSERT INTO `kpn_movie_tag` VALUES (198, 100128, 100019, 100008);
INSERT INTO `kpn_movie_tag` VALUES (199, 100048, 100020, 100009);
INSERT INTO `kpn_movie_tag` VALUES (200, 100171, 100000, 100000);
INSERT INTO `kpn_movie_tag` VALUES (201, 100016, 100001, 100000);
INSERT INTO `kpn_movie_tag` VALUES (202, 100013, 100002, 100000);
INSERT INTO `kpn_movie_tag` VALUES (203, 100305, 100003, 100000);
INSERT INTO `kpn_movie_tag` VALUES (204, 100144, 100004, 100000);
INSERT INTO `kpn_movie_tag` VALUES (205, 100051, 100005, 100001);
INSERT INTO `kpn_movie_tag` VALUES (206, 100009, 100006, 100001);
INSERT INTO `kpn_movie_tag` VALUES (207, 100150, 100007, 100001);
INSERT INTO `kpn_movie_tag` VALUES (208, 100120, 100008, 100002);
INSERT INTO `kpn_movie_tag` VALUES (209, 100026, 100009, 100002);
INSERT INTO `kpn_movie_tag` VALUES (210, 100295, 100010, 100003);
INSERT INTO `kpn_movie_tag` VALUES (211, 100095, 100011, 100003);
INSERT INTO `kpn_movie_tag` VALUES (212, 100038, 100012, 100004);
INSERT INTO `kpn_movie_tag` VALUES (213, 100071, 100013, 100004);
INSERT INTO `kpn_movie_tag` VALUES (214, 100183, 100014, 100005);
INSERT INTO `kpn_movie_tag` VALUES (215, 100280, 100015, 100005);
INSERT INTO `kpn_movie_tag` VALUES (216, 100192, 100016, 100006);
INSERT INTO `kpn_movie_tag` VALUES (217, 100097, 100017, 100006);
INSERT INTO `kpn_movie_tag` VALUES (218, 100288, 100018, 100007);
INSERT INTO `kpn_movie_tag` VALUES (219, 100055, 100019, 100008);
INSERT INTO `kpn_movie_tag` VALUES (220, 100281, 100020, 100009);
INSERT INTO `kpn_movie_tag` VALUES (221, 100076, 100000, 100000);
INSERT INTO `kpn_movie_tag` VALUES (222, 100273, 100001, 100000);
INSERT INTO `kpn_movie_tag` VALUES (223, 100279, 100002, 100000);
INSERT INTO `kpn_movie_tag` VALUES (224, 100160, 100003, 100000);
INSERT INTO `kpn_movie_tag` VALUES (225, 100135, 100004, 100000);
INSERT INTO `kpn_movie_tag` VALUES (226, 100168, 100005, 100001);
INSERT INTO `kpn_movie_tag` VALUES (227, 100111, 100006, 100001);
INSERT INTO `kpn_movie_tag` VALUES (228, 100181, 100007, 100001);
INSERT INTO `kpn_movie_tag` VALUES (229, 100046, 100008, 100002);
INSERT INTO `kpn_movie_tag` VALUES (230, 100054, 100009, 100002);
INSERT INTO `kpn_movie_tag` VALUES (231, 100005, 100010, 100003);
INSERT INTO `kpn_movie_tag` VALUES (232, 100122, 100011, 100003);
INSERT INTO `kpn_movie_tag` VALUES (233, 100304, 100012, 100004);
INSERT INTO `kpn_movie_tag` VALUES (234, 100084, 100013, 100004);
INSERT INTO `kpn_movie_tag` VALUES (235, 100017, 100014, 100005);
INSERT INTO `kpn_movie_tag` VALUES (236, 100043, 100015, 100005);
INSERT INTO `kpn_movie_tag` VALUES (237, 100133, 100016, 100006);
INSERT INTO `kpn_movie_tag` VALUES (238, 100146, 100017, 100006);
INSERT INTO `kpn_movie_tag` VALUES (239, 100272, 100018, 100007);
INSERT INTO `kpn_movie_tag` VALUES (240, 100161, 100019, 100008);
INSERT INTO `kpn_movie_tag` VALUES (241, 100034, 100020, 100009);
INSERT INTO `kpn_movie_tag` VALUES (242, 100118, 100000, 100000);
INSERT INTO `kpn_movie_tag` VALUES (243, 100090, 100001, 100000);
INSERT INTO `kpn_movie_tag` VALUES (244, 100188, 100002, 100000);
INSERT INTO `kpn_movie_tag` VALUES (245, 100155, 100003, 100000);
INSERT INTO `kpn_movie_tag` VALUES (246, 100199, 100004, 100000);
INSERT INTO `kpn_movie_tag` VALUES (256, 100000, 100007, 100001);
INSERT INTO `kpn_movie_tag` VALUES (257, 100000, 100004, 100000);
INSERT INTO `kpn_movie_tag` VALUES (258, 100000, 100001, 100000);
INSERT INTO `kpn_movie_tag` VALUES (259, 100000, 100002, 100000);
INSERT INTO `kpn_movie_tag` VALUES (260, 100000, 100011, 100003);
INSERT INTO `kpn_movie_tag` VALUES (263, 100001, 100017, 100006);
INSERT INTO `kpn_movie_tag` VALUES (264, 100001, 100015, 100005);
INSERT INTO `kpn_movie_tag` VALUES (265, 100002, 100005, 100001);
INSERT INTO `kpn_movie_tag` VALUES (266, 100003, 100000, 100000);
INSERT INTO `kpn_movie_tag` VALUES (267, 100011, 100020, 100009);
INSERT INTO `kpn_movie_tag` VALUES (268, 100011, 100019, 100008);
INSERT INTO `kpn_movie_tag` VALUES (269, 100011, 100018, 100007);
INSERT INTO `kpn_movie_tag` VALUES (270, 100022, 100002, 100000);
INSERT INTO `kpn_movie_tag` VALUES (271, 100022, 100003, 100000);
INSERT INTO `kpn_movie_tag` VALUES (272, 100022, 100004, 100000);
INSERT INTO `kpn_movie_tag` VALUES (273, 100022, 100005, 100001);
INSERT INTO `kpn_movie_tag` VALUES (274, 100022, 100006, 100001);
INSERT INTO `kpn_movie_tag` VALUES (275, 100033, 100011, 100003);
INSERT INTO `kpn_movie_tag` VALUES (276, 100033, 100012, 100004);
INSERT INTO `kpn_movie_tag` VALUES (277, 100033, 100014, 100005);
INSERT INTO `kpn_movie_tag` VALUES (278, 100033, 100015, 100005);
INSERT INTO `kpn_movie_tag` VALUES (279, 100033, 100016, 100006);
INSERT INTO `kpn_movie_tag` VALUES (280, 100044, 100020, 100009);
INSERT INTO `kpn_movie_tag` VALUES (281, 100044, 100017, 100006);
INSERT INTO `kpn_movie_tag` VALUES (282, 100044, 100015, 100005);
INSERT INTO `kpn_movie_tag` VALUES (283, 100044, 100014, 100005);
INSERT INTO `kpn_movie_tag` VALUES (284, 100044, 100003, 100000);
INSERT INTO `kpn_movie_tag` VALUES (285, 100055, 100010, 100003);
INSERT INTO `kpn_movie_tag` VALUES (286, 100055, 100004, 100000);
INSERT INTO `kpn_movie_tag` VALUES (287, 100055, 100008, 100002);
INSERT INTO `kpn_movie_tag` VALUES (288, 100055, 100017, 100006);
INSERT INTO `kpn_movie_tag` VALUES (289, 100055, 100016, 100006);
INSERT INTO `kpn_movie_tag` VALUES (290, 100066, 100015, 100005);
INSERT INTO `kpn_movie_tag` VALUES (291, 100066, 100003, 100000);
INSERT INTO `kpn_movie_tag` VALUES (292, 100066, 100000, 100000);
INSERT INTO `kpn_movie_tag` VALUES (298, 100077, 100000, 100000);
INSERT INTO `kpn_movie_tag` VALUES (299, 100077, 100001, 100000);
INSERT INTO `kpn_movie_tag` VALUES (300, 100077, 100002, 100000);
INSERT INTO `kpn_movie_tag` VALUES (301, 100077, 100003, 100000);
INSERT INTO `kpn_movie_tag` VALUES (302, 100088, 100001, 100000);
INSERT INTO `kpn_movie_tag` VALUES (303, 100088, 100002, 100000);
INSERT INTO `kpn_movie_tag` VALUES (304, 100088, 100003, 100000);
INSERT INTO `kpn_movie_tag` VALUES (305, 100088, 100004, 100000);
INSERT INTO `kpn_movie_tag` VALUES (306, 100088, 100005, 100001);
INSERT INTO `kpn_movie_tag` VALUES (307, 100099, 100011, 100003);
INSERT INTO `kpn_movie_tag` VALUES (308, 100099, 100012, 100004);
INSERT INTO `kpn_movie_tag` VALUES (309, 100099, 100013, 100004);
INSERT INTO `kpn_movie_tag` VALUES (310, 100099, 100014, 100005);
INSERT INTO `kpn_movie_tag` VALUES (311, 100099, 100015, 100005);
INSERT INTO `kpn_movie_tag` VALUES (312, 100100, 100009, 100002);
INSERT INTO `kpn_movie_tag` VALUES (313, 100100, 100010, 100003);
INSERT INTO `kpn_movie_tag` VALUES (314, 100111, 100000, 100000);
INSERT INTO `kpn_movie_tag` VALUES (315, 100111, 100001, 100000);
INSERT INTO `kpn_movie_tag` VALUES (316, 100111, 100002, 100000);
INSERT INTO `kpn_movie_tag` VALUES (317, 100111, 100003, 100000);
INSERT INTO `kpn_movie_tag` VALUES (323, 100122, 100007, 100001);
INSERT INTO `kpn_movie_tag` VALUES (324, 100122, 100008, 100002);
INSERT INTO `kpn_movie_tag` VALUES (325, 100122, 100009, 100002);
INSERT INTO `kpn_movie_tag` VALUES (326, 100122, 100010, 100003);
INSERT INTO `kpn_movie_tag` VALUES (327, 100122, 100012, 100004);
INSERT INTO `kpn_movie_tag` VALUES (328, 100133, 100018, 100007);
INSERT INTO `kpn_movie_tag` VALUES (329, 100133, 100019, 100008);
INSERT INTO `kpn_movie_tag` VALUES (330, 100133, 100020, 100009);
INSERT INTO `kpn_movie_tag` VALUES (331, 100144, 100013, 100004);
INSERT INTO `kpn_movie_tag` VALUES (332, 100144, 100014, 100005);
INSERT INTO `kpn_movie_tag` VALUES (333, 100155, 100002, 100000);
INSERT INTO `kpn_movie_tag` VALUES (334, 100155, 100007, 100001);
INSERT INTO `kpn_movie_tag` VALUES (335, 100155, 100004, 100000);
INSERT INTO `kpn_movie_tag` VALUES (336, 100155, 100005, 100001);
INSERT INTO `kpn_movie_tag` VALUES (337, 100155, 100006, 100001);
INSERT INTO `kpn_movie_tag` VALUES (338, 100166, 100015, 100005);
INSERT INTO `kpn_movie_tag` VALUES (339, 100166, 100016, 100006);
INSERT INTO `kpn_movie_tag` VALUES (340, 100166, 100017, 100006);
INSERT INTO `kpn_movie_tag` VALUES (341, 100177, 100001, 100000);
INSERT INTO `kpn_movie_tag` VALUES (342, 100177, 100002, 100000);
INSERT INTO `kpn_movie_tag` VALUES (343, 100188, 100015, 100005);
INSERT INTO `kpn_movie_tag` VALUES (344, 100188, 100016, 100006);
INSERT INTO `kpn_movie_tag` VALUES (345, 100199, 100019, 100008);
INSERT INTO `kpn_movie_tag` VALUES (346, 100200, 100007, 100001);
INSERT INTO `kpn_movie_tag` VALUES (347, 100200, 100008, 100002);
INSERT INTO `kpn_movie_tag` VALUES (348, 100200, 100009, 100002);
INSERT INTO `kpn_movie_tag` VALUES (349, 100200, 100010, 100003);
INSERT INTO `kpn_movie_tag` VALUES (350, 100201, 100018, 100007);
INSERT INTO `kpn_movie_tag` VALUES (351, 100201, 100019, 100008);
INSERT INTO `kpn_movie_tag` VALUES (354, 100277, 100001, 100000);
INSERT INTO `kpn_movie_tag` VALUES (355, 100277, 100009, 100002);
INSERT INTO `kpn_movie_tag` VALUES (356, 100277, 100003, 100000);
INSERT INTO `kpn_movie_tag` VALUES (357, 100277, 100004, 100000);
INSERT INTO `kpn_movie_tag` VALUES (358, 100277, 100005, 100001);
INSERT INTO `kpn_movie_tag` VALUES (359, 100288, 100019, 100008);
INSERT INTO `kpn_movie_tag` VALUES (360, 100288, 100020, 100009);
INSERT INTO `kpn_movie_tag` VALUES (361, 100299, 100013, 100004);
INSERT INTO `kpn_movie_tag` VALUES (362, 100299, 100014, 100005);
INSERT INTO `kpn_movie_tag` VALUES (363, 100299, 100015, 100005);
INSERT INTO `kpn_movie_tag` VALUES (364, 100299, 100016, 100006);
INSERT INTO `kpn_movie_tag` VALUES (365, 100300, 100019, 100008);
INSERT INTO `kpn_movie_tag` VALUES (366, 100123, 100009, 100002);
INSERT INTO `kpn_movie_tag` VALUES (367, 100123, 100010, 100003);
INSERT INTO `kpn_movie_tag` VALUES (368, 100123, 100011, 100003);
INSERT INTO `kpn_movie_tag` VALUES (369, 100123, 100012, 100004);
INSERT INTO `kpn_movie_tag` VALUES (370, 100123, 100013, 100004);
INSERT INTO `kpn_movie_tag` VALUES (371, 100285, 100012, 100004);
INSERT INTO `kpn_movie_tag` VALUES (372, 100285, 100013, 100004);
INSERT INTO `kpn_movie_tag` VALUES (373, 100285, 100014, 100005);

-- ----------------------------
-- Table structure for kpn_site
-- ----------------------------
DROP TABLE IF EXISTS `kpn_site`;
CREATE TABLE `kpn_site`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点编码',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点名称',
  `logo_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点logo',
  `domains` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '域名',
  `status` tinyint(255) NOT NULL DEFAULT 1 COMMENT '站点状态 0关闭,1开启',
  `repair_status` tinyint(255) NOT NULL DEFAULT 0 COMMENT '站点维护状态 0未维护,1维护中',
  `currency_code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '币种编码',
  `member_num` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '会员数',
  `vip_num` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'vip会员数(会员子集)',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `remark` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_code`(`code`) USING BTREE COMMENT '站点编码唯一',
  UNIQUE INDEX `uni_name`(`name`) USING BTREE COMMENT '站点名称唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '站点信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kpn_site
-- ----------------------------
INSERT INTO `kpn_site` VALUES (1, 'kk-porn01', 'kk黄站1号', 'kkporn/site_1_logo.png', 'localhost:7300,localhost:9900,http://kkpornh5.kk88824.net,searchporn.kk88824.net:80', 1, 0, 'USD', 0, 0, '2023-02-01 21:53:05', '2023-03-17 14:38:48', 'admin', 'admin', NULL);
INSERT INTO `kpn_site` VALUES (11, 'code07', 'lulu07', 'test/f4895350-2130-4a38-be56-98c1aeb3903e.jpg', '127.0.0.11', 1, 0, 'CNY', 0, 0, '2023-02-06 20:29:20', '2023-03-06 14:34:16', NULL, NULL, NULL);
INSERT INTO `kpn_site` VALUES (18, 'code08', 'lulu08', '127.0.0.1', '127.0.0.1', 1, 0, 'USD', 0, 0, '2023-03-09 20:35:20', '2023-03-09 20:35:20', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for kpn_site_actor
-- ----------------------------
DROP TABLE IF EXISTS `kpn_site_actor`;
CREATE TABLE `kpn_site_actor`  (
  `site_id` bigint(20) NOT NULL COMMENT '站点id',
  `actor_id` bigint(20) NOT NULL COMMENT '演员id',
  `favorites` bigint(20) NOT NULL DEFAULT 0 COMMENT '收藏量',
  UNIQUE INDEX `uni_siteid_actorid`(`site_id`, `actor_id`) USING BTREE COMMENT '唯一索引',
  INDEX `idx_siteid_actorid_favorites`(`site_id`, `actor_id`, `favorites`) USING BTREE COMMENT '用于演员列表统计'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kpn_site_actor
-- ----------------------------
INSERT INTO `kpn_site_actor` VALUES (1, 0, 3);
INSERT INTO `kpn_site_actor` VALUES (1, 1, 5);
INSERT INTO `kpn_site_actor` VALUES (1, 2, 1);
INSERT INTO `kpn_site_actor` VALUES (1, 16, 0);
INSERT INTO `kpn_site_actor` VALUES (1, 25, 0);
INSERT INTO `kpn_site_actor` VALUES (1, 30, 0);
INSERT INTO `kpn_site_actor` VALUES (1, 35, 0);
INSERT INTO `kpn_site_actor` VALUES (1, 42, 0);
INSERT INTO `kpn_site_actor` VALUES (1, 63, 0);
INSERT INTO `kpn_site_actor` VALUES (1, 68, 1);
INSERT INTO `kpn_site_actor` VALUES (1, 70, 1);

-- ----------------------------
-- Table structure for kpn_site_advertise
-- ----------------------------
DROP TABLE IF EXISTS `kpn_site_advertise`;
CREATE TABLE `kpn_site_advertise`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `site_id` bigint(11) NULL DEFAULT NULL COMMENT '站点id',
  `site_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点编码',
  `site_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点名称',
  `name_zh` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '广告中文名',
  `name_en` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '广告英文名',
  `name_kh` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '广告柬文名',
  `device` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '投放平台(H5,PC)',
  `position` tinyint(5) NULL DEFAULT NULL COMMENT '投放位置 1首页轮播图,2首页平台展示,3首页专题广告,4福利,5游戏轮播图,6游戏广告',
  `hits` bigint(255) NOT NULL DEFAULT 0 COMMENT '点击量',
  `sort` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序(越大越靠前)',
  `status` tinyint(255) NOT NULL DEFAULT 1 COMMENT '状态 0下架,1上架',
  `start_time` datetime NOT NULL COMMENT '有效开始时间',
  `end_time` datetime NOT NULL COMMENT '有效结束时间',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '广告url',
  `link_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '连接url',
  `remark` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_siteid_status_device_position_starttime_endtime`(`site_id`, `status`, `device`, `position`, `start_time`, `end_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kpn_site_advertise
-- ----------------------------
INSERT INTO `kpn_site_advertise` VALUES (1, 1, 'kk-porn01', 'kk黄站1号', '澳门威尼斯', '澳门威尼斯-en', '澳门威尼斯-kh', 'H5', 3, 0, 7, 1, '2023-02-02 21:08:37', '2023-07-04 23:01:43', 'kkporn/ads/1.png', 'https://kk1688.net/', 'kk-澳门威尼斯', 'year', 'year', '2023-02-02 21:09:59', '2023-03-11 20:37:15');
INSERT INTO `kpn_site_advertise` VALUES (2, 1, 'kk-porn01', 'kk黄站1号', '大润发', '大润发-en', '大润发-kh', 'H5', 3, 0, 0, 1, '2023-02-02 21:11:20', '2023-07-04 23:01:43', 'kkporn/ads/2.png', 'https://kk1688.net/', 'kk-大润发', 'year', 'year', '2023-02-02 21:11:29', '2023-03-11 20:37:15');
INSERT INTO `kpn_site_advertise` VALUES (3, 1, 'kk-porn01', 'kk黄站1号', '易发国际', '易发国际-en', '易发国际-kh', 'PC', 3, 0, 0, 1, '2023-02-02 21:11:20', '2023-07-04 23:01:43', 'kkporn/ads/3.png', 'https://kk1688.net/', 'kk-易发国际', 'year', 'year', '2023-02-02 21:12:54', '2023-03-11 20:37:15');
INSERT INTO `kpn_site_advertise` VALUES (4, 1, 'kk-porn01', 'kk黄站1号', '捕鱼', '捕鱼-en', '捕鱼-kh', 'PC', 3, 0, 0, 1, '2023-02-02 22:24:30', '2023-07-04 23:01:43', 'kkporn/ads/4.png', 'https://kk1688.net/', 'kk-puyu', 'year', 'year', '2023-02-02 22:23:11', '2023-03-11 20:37:15');
INSERT INTO `kpn_site_advertise` VALUES (8, 1, 'kk-porn01', 'kk黄站1号', '广告1', '广告1-en', '广告1-kh', 'H5', 1, 3, 77, 1, '2023-02-07 00:00:00', '2023-07-04 23:01:43', 'kkporn/ads/13.png', 'https://kk1688.net/', 'lulu测试', 'year', 'year', '2023-02-03 15:21:59', '2023-03-16 21:01:08');
INSERT INTO `kpn_site_advertise` VALUES (9, 1, 'kk-porn01', 'kk黄站1号', '广告1', '广告1-en', '广告1-kh', 'H5', 1, 1, 77, 1, '2023-02-07 00:00:00', '2023-07-04 23:01:43', 'kkporn/ads/14.png', 'https://kk1688.net/', 'lulu测试', 'year', 'year', '2023-02-03 21:08:34', '2023-03-16 21:46:22');
INSERT INTO `kpn_site_advertise` VALUES (20, 1, 'kk-porn01', 'kk黄站1号', 'zh', 'en', 'kh', 'H5', 2, 1, 5, 1, '2023-02-07 00:00:00', '2023-07-04 23:01:43', 'kkporn/ads/8.png', 'https://kk1688.net/', '备注', 'year', 'year', '2023-02-09 23:49:23', '2023-03-16 21:00:08');
INSERT INTO `kpn_site_advertise` VALUES (27, 1, 'kk-porn01', 'kk黄站1号', 'zh', 'en', 'kh', 'H5', 1, 2, 5, 1, '2023-02-07 00:00:00', '2023-07-04 23:01:43', 'kkporn/ads/15.png', 'https://kk1688.net/', '备注', 'year', 'year', '2023-02-10 00:23:36', '2023-03-16 21:39:35');
INSERT INTO `kpn_site_advertise` VALUES (28, 1, 'kk-porn01', 'kk黄站1号', '他吞吞吐吐', '123123', 'dsfadsf', 'H5', 4, 0, 0, 1, '2023-02-10 13:00:00', '2023-07-04 23:01:43', 'kkporn/ads/9.png', 'https://kk1688.net/', 'null', 'year', 'year', '2023-02-10 00:25:00', '2023-03-11 20:37:15');
INSERT INTO `kpn_site_advertise` VALUES (41, 1, 'kk-porn01', 'kk黄站1号', '范德萨范德萨', '范德萨范德萨', 'dsfadsf', 'H5', 5, 0, 0, 1, '2023-02-10 13:00:00', '2023-07-04 23:01:43', 'kkporn/ads/10.png', 'https://kk1688.net/', 'null', 'year', 'year', '2023-02-10 00:25:00', '2023-03-11 20:37:15');
INSERT INTO `kpn_site_advertise` VALUES (42, 1, 'kk-porn01', 'kk黄站1号', '就会高科技化工', '就会高科技化工', 'dsfadsf', 'H5', 6, 0, 0, 1, '2023-02-10 13:00:00', '2023-07-04 23:01:43', 'kkporn/ads/11.png', 'https://kk1688.net/', 'null', 'year', 'year', '2023-02-10 00:25:00', '2023-03-11 20:37:15');
INSERT INTO `kpn_site_advertise` VALUES (43, 1, 'kk-porn01', 'kk黄站1号', '广告1', '广告1-en', '广告1-kh', 'H5', 1, 3, 77, 0, '2023-02-10 13:00:00', '2023-07-04 23:01:43', 'kkporn/ads/16.png', 'https://kk1688.net/', NULL, NULL, NULL, '2023-02-10 00:25:00', '2023-03-17 12:42:56');

-- ----------------------------
-- Table structure for kpn_site_announcement
-- ----------------------------
DROP TABLE IF EXISTS `kpn_site_announcement`;
CREATE TABLE `kpn_site_announcement`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `site_id` bigint(11) NULL DEFAULT NULL COMMENT '站点id',
  `site_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点编码',
  `site_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点名称',
  `title_zh` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公告标题(中文)',
  `content_zh` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公告内容(中文)',
  `title_en` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公告标题(英文)',
  `content_en` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公告内容(英文)',
  `title_kh` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公告标题(柬文)',
  `content_kh` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公告内容(柬文)',
  `status` tinyint(255) NOT NULL DEFAULT 1 COMMENT '状态 0下架,1上架',
  `sort` bigint(255) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_siteid_status_sort_createtime`(`site_id`, `status`, `sort`, `create_time`) USING BTREE COMMENT '用户端查询'
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '站点公告' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kpn_site_announcement
-- ----------------------------
INSERT INTO `kpn_site_announcement` VALUES (3, 22, 'code', 'name', 'tzh', 'zh', 'ten', 'en', 'tkh', 'kh', 1, 2, NULL, NULL, '2023-02-06 03:35:23', '2023-02-06 03:35:23');
INSERT INTO `kpn_site_announcement` VALUES (6, 0, NULL, NULL, 'adfadfads', 'fasdfasdf', 'adsfasd', 'fadsfa', 'sdfadsf', 'adsfasdf', 0, 2, NULL, NULL, '2023-02-07 05:56:31', '2023-02-09 02:52:43');
INSERT INTO `kpn_site_announcement` VALUES (7, 0, NULL, NULL, 'vvvadf', 'adfadf', 'adfadf', 'adsfa', 'dfasdfadsf', '10', 1, 0, NULL, NULL, '2023-02-09 02:51:01', '2023-02-09 02:51:01');
INSERT INTO `kpn_site_announcement` VALUES (11, 0, NULL, NULL, '22', '22', '22', '22', '22', '1022', 0, 2, NULL, NULL, '2023-02-20 16:06:02', '2023-02-20 16:06:02');
INSERT INTO `kpn_site_announcement` VALUES (12, 1, 'kk-porn01', 'kk黄站1号', 'title-01-zh', 'content-01-zh', 'title-01-en', 'content-01-en', 'title-01-en', 'content-01-kh', 1, 0, NULL, NULL, '2023-02-21 23:47:24', '2023-02-21 23:47:24');
INSERT INTO `kpn_site_announcement` VALUES (13, 1, 'kk-porn01', 'kk黄站1号', 'title-02-zh', 'content-02-zh', 'title-02-en', 'content-02-en', 'title-02-en', 'content-02-kh', 0, 0, NULL, NULL, '2023-02-21 23:49:39', '2023-02-21 23:59:22');
INSERT INTO `kpn_site_announcement` VALUES (14, 1, 'kk-porn01', 'kk黄站1号', 'title-03-zh', 'content-03-zh', 'title-03-en', 'content-03-en', 'title-03-en', 'content-03-kh', 1, 0, NULL, NULL, '2023-02-21 23:50:39', '2023-02-21 23:50:10');
INSERT INTO `kpn_site_announcement` VALUES (15, 1, 'kk-porn01', 'kk黄站1号', 'title-04-zh', 'content-04-zh', 'title-04-en', 'content-04-en', 'title-04-en', 'content-04-kh', 1, 0, NULL, NULL, '2023-02-21 23:51:39', '2023-02-21 23:50:10');
INSERT INTO `kpn_site_announcement` VALUES (16, 1, 'kk-porn01', 'kk黄站1号', 'title-05-zh', 'content-05-zh', 'title-05-en', 'content-05-en', 'title-05-en', 'content-05-kh', 1, 0, NULL, NULL, '2023-02-21 23:52:39', '2023-02-21 23:50:10');

-- ----------------------------
-- Table structure for kpn_site_announcement_user
-- ----------------------------
DROP TABLE IF EXISTS `kpn_site_announcement_user`;
CREATE TABLE `kpn_site_announcement_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ann_id` bigint(11) NOT NULL COMMENT '公告id',
  `is_read` tinyint(255) NOT NULL DEFAULT 1 COMMENT '状态 0未读,1已读',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `user_id` bigint(11) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_annid_userid_createtime`(`ann_id`, `user_id`) USING BTREE COMMENT '用户端查询'
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户是否已读站点公告' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kpn_site_announcement_user
-- ----------------------------
INSERT INTO `kpn_site_announcement_user` VALUES (1, 12, 1, NULL, 'kk-porn01_qwe112233', '2023-03-27 17:46:53', '2023-03-27 20:48:23', 136);
INSERT INTO `kpn_site_announcement_user` VALUES (17, 14, 1, 'kk-porn01_qwe112233', 'kk-porn01_qwe112233', '2023-03-27 20:47:03', '2023-03-27 20:48:22', 136);
INSERT INTO `kpn_site_announcement_user` VALUES (18, 15, 1, 'kk-porn01_qwe112233', 'kk-porn01_qwe112233', '2023-03-27 20:47:28', '2023-03-27 20:48:57', 136);
INSERT INTO `kpn_site_announcement_user` VALUES (19, 16, 1, 'kk-porn01_qwe112233', 'kk-porn01_qwe112233', '2023-03-27 20:47:29', '2023-03-27 20:48:58', 136);
INSERT INTO `kpn_site_announcement_user` VALUES (20, 15, 1, 'kk-porn01_qwe1122333', 'kk-porn01_qwe1122333', '2023-03-27 20:50:53', '2023-03-27 20:50:53', 141);
INSERT INTO `kpn_site_announcement_user` VALUES (21, 12, 1, 'kk-porn01_qwe1122333', 'kk-porn01_qwe1122333', '2023-03-27 20:50:56', '2023-03-27 20:50:56', 141);
INSERT INTO `kpn_site_announcement_user` VALUES (22, 14, 1, 'kk-porn01_qwe1122333', 'kk-porn01_qwe1122333', '2023-03-27 20:50:57', '2023-03-27 20:50:57', 141);
INSERT INTO `kpn_site_announcement_user` VALUES (23, 16, 1, 'kk-porn01_qwe1122333', 'kk-porn01_qwe1122333', '2023-03-27 20:50:58', '2023-03-27 20:50:58', 141);

-- ----------------------------
-- Table structure for kpn_site_app
-- ----------------------------
DROP TABLE IF EXISTS `kpn_site_app`;
CREATE TABLE `kpn_site_app`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `site_id` int(11) NULL DEFAULT NULL COMMENT '站点id',
  `site_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点编码',
  `site_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点名称',
  `type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '终端类型 android,ios',
  `version_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '版本名称',
  `version_num` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '版本号',
  `download_url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '下载地址',
  `is_force` tinyint(255) NOT NULL DEFAULT 0 COMMENT '是否强制更新 0不强制,1强制',
  `remark` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '站点app更新配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kpn_site_app
-- ----------------------------
INSERT INTO `kpn_site_app` VALUES (1, 1, 'kk-porn01', 'kk黄站1号', 'android', '版本1', '1.0.0', '444444444', 0, '测试', '2022-03-08 19:56:23', '2022-03-08 19:56:23', 'yixiu', 'yixiu');
INSERT INTO `kpn_site_app` VALUES (2, 0, 'kk-porn01', 'kk黄站1号', 'ios', '版本1', '2.0.0', '444444444', 0, '测试', '2022-03-08 19:56:23', '2022-03-08 19:56:23', 'yixiu', 'yixiu');

-- ----------------------------
-- Table structure for kpn_site_bank
-- ----------------------------
DROP TABLE IF EXISTS `kpn_site_bank`;
CREATE TABLE `kpn_site_bank`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `site_id` int(11) NULL DEFAULT NULL COMMENT '站点id',
  `site_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点编码',
  `site_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点名称',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '银行名称',
  `logo_url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'logo地址',
  `status` tinyint(255) NOT NULL DEFAULT 1 COMMENT '状态 0禁用,1启用',
  `remark` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `site_bank_normal`(`site_id`, `name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '收款银行渠道' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kpn_site_bank
-- ----------------------------
INSERT INTO `kpn_site_bank` VALUES (1, 1, 'kk-porn01', 'kk黄站1号', '招商银行', 'test123', 1, 'test123', '2023-02-24 18:00:34', '2023-03-07 14:42:57', 'year', 'year');
INSERT INTO `kpn_site_bank` VALUES (2, 1, 'kk-porn01', 'kk黄站1号', '中国银行', 'test123', 1, 'test123', '2023-02-24 18:00:54', '2023-03-07 14:42:52', 'year', 'year');
INSERT INTO `kpn_site_bank` VALUES (3, 1, 'kk-porn01', 'kk黄站1号', '中国农业银行', 'test123', 1, 'test123', '2023-02-24 18:01:05', '2023-03-07 14:42:51', 'year', 'year');

-- ----------------------------
-- Table structure for kpn_site_bank_card
-- ----------------------------
DROP TABLE IF EXISTS `kpn_site_bank_card`;
CREATE TABLE `kpn_site_bank_card`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `site_id` int(11) NULL DEFAULT NULL COMMENT '站点id',
  `site_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点编码',
  `site_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点名称',
  `bank_id` bigint(20) NULL DEFAULT NULL COMMENT '所属银行id',
  `bank_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '银行名称',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '开户姓名',
  `card` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '卡号',
  `sort` int(255) NOT NULL DEFAULT 0 COMMENT '排序(越大越靠前)',
  `status` tinyint(255) NOT NULL DEFAULT 1 COMMENT '状态 0下架,1上架',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `bank_card_normal`(`site_id`, `bank_id`, `bank_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '收款银行卡配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kpn_site_bank_card
-- ----------------------------
INSERT INTO `kpn_site_bank_card` VALUES (1, 1, 'kk-porn01', 'kk黄站1号', 1, '招商银行', '周杰伦', '222222222222222', 0, 1, '2023-02-24 18:02:49', '2023-02-24 20:16:13', 'year', 'year');
INSERT INTO `kpn_site_bank_card` VALUES (2, 1, 'kk-porn01', 'kk黄站1号', 1, '中国银行', '周星驰', '1212121212121212', 0, 1, '2023-02-24 18:04:09', '2023-02-24 20:16:15', 'year', 'year');
INSERT INTO `kpn_site_bank_card` VALUES (3, 1, 'kk-porn01', 'kk黄站1号', 1, '招商银行', '周润发', '111111111111111', 0, 1, '2023-02-24 21:16:46', '2023-02-24 21:17:35', 'year', 'year');

-- ----------------------------
-- Table structure for kpn_site_channel
-- ----------------------------
DROP TABLE IF EXISTS `kpn_site_channel`;
CREATE TABLE `kpn_site_channel`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `site_id` int(11) NULL DEFAULT NULL COMMENT '站点id',
  `site_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点编码',
  `site_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点名称',
  `name_zh` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '频道名称(中文)',
  `name_en` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '频道名称(英文)',
  `name_kh` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '频道名称(柬文)',
  `icon` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '频道图标url',
  `sort` bigint(255) NOT NULL DEFAULT 0 COMMENT '排序(越大越靠前)',
  `status` tinyint(255) NOT NULL DEFAULT 1 COMMENT '频道状态 0下架,1上架',
  `tags` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联标签(tag_id_1,tag_id_2,....)',
  `is_stable` tinyint(255) NOT NULL DEFAULT 0 COMMENT '是否固定频道 0自定义,1内置固定',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_isstable`(`is_stable`) USING BTREE COMMENT '获取固定频道',
  INDEX `idx_siteid_status_sort`(`site_id`, `status`, `sort`) USING BTREE COMMENT '查询频道'
) ENGINE = InnoDB AUTO_INCREMENT = 100021 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '站点频道' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kpn_site_channel
-- ----------------------------
INSERT INTO `kpn_site_channel` VALUES (100000, 0, NULL, '写死，用于生成对应站点数据', '推荐', 'recommend', 'ណែនាំ', 'kkporn/channel/default_recommend.svg', 4, 1, NULL, 1, '2023-03-08 15:24:36', '2023-03-13 20:55:26', NULL, NULL);
INSERT INTO `kpn_site_channel` VALUES (100001, 0, NULL, '写死，用于生成对应站点数据', '最新', 'newest', 'ថ្មីបំផុត', 'kkporn/channel/default_latest.svg', 3, 1, NULL, 1, '2023-03-08 15:24:36', '2023-03-13 20:55:26', NULL, NULL);
INSERT INTO `kpn_site_channel` VALUES (100002, 0, NULL, '写死，用于生成对应站点数据', '最热', 'popular', 'ពេញនិយមបំផុត។', 'kkporn/channel/default_hot.svg', 2, 1, NULL, 1, '2023-03-08 15:24:36', '2023-03-13 20:55:26', NULL, NULL);
INSERT INTO `kpn_site_channel` VALUES (100003, 0, NULL, '写死，用于生成对应站点数据', '找片', 'find film', 'ស្វែងរកខ្សែភាពយន្ត', 'kkporn/channel/default_search.svg', 1, 1, NULL, 1, '2023-03-08 15:24:36', '2023-03-13 20:55:26', NULL, NULL);
INSERT INTO `kpn_site_channel` VALUES (100004, 1, 'kk-porn01', 'kk黄站1号', '推荐', 'recommend', 'ណែនាំ', 'kkporn/channel/default_recommend.svg', 4, 1, NULL, 1, '2023-03-08 15:24:36', '2023-03-11 19:54:20', NULL, NULL);
INSERT INTO `kpn_site_channel` VALUES (100005, 1, 'kk-porn01', 'kk黄站1号', '最新', 'newest', 'ថ្មីបំផុត', 'kkporn/channel/default_latest.svg', 3, 1, NULL, 1, '2023-03-08 15:24:36', '2023-03-11 19:54:20', NULL, NULL);
INSERT INTO `kpn_site_channel` VALUES (100006, 1, 'kk-porn01', 'kk黄站1号', '最热', 'popular', 'ពេញនិយមបំផុត។', 'kkporn/channel/default_hot.svg', 2, 1, NULL, 1, '2023-03-08 15:24:36', '2023-03-11 19:54:20', NULL, NULL);
INSERT INTO `kpn_site_channel` VALUES (100007, 1, 'kk-porn01', 'kk黄站1号', '找片', 'find film', 'ស្វែងរកខ្សែភាពយន្ត', 'kkporn/channel/default_search.svg', 1, 1, NULL, 1, '2023-03-08 15:24:36', '2023-03-11 19:54:20', NULL, NULL);
INSERT INTO `kpn_site_channel` VALUES (100012, 1, 'kk-porn01', 'kk黄站1号', '人妻/少妇', 'Married/young woman', 'រៀបការ / ស្ត្រីវ័យក្មេង', NULL, 0, 1, NULL, 0, '2023-03-08 15:24:37', '2023-03-08 15:24:37', NULL, NULL);
INSERT INTO `kpn_site_channel` VALUES (100013, 1, 'kk-porn01', 'kk黄站1号', '动画', 'animation', 'ចលនា', 'kkporn/channel/default_hot.svg', 0, 1, '100007,100008,100009,100010,100011,100012', 0, '2023-03-08 15:24:37', '2023-03-11 19:54:20', NULL, NULL);
INSERT INTO `kpn_site_channel` VALUES (100014, 1, 'kk-porn01', 'kk黄站1号', '主播', 'anchor', 'យុថ្កា', 'kkporn/channel/default_search.svg', 0, 1, '100012,100010,100003,100007,100000,100008', 0, '2023-03-08 15:24:37', '2023-03-11 19:54:20', NULL, NULL);
INSERT INTO `kpn_site_channel` VALUES (100015, 1, 'kk-porn01', 'kk黄站1号', '热舞', 'dance', 'រាំ', 'kkporn/channel/default_recommend.svg', 0, 1, '100014,100013,100012', 0, '2023-03-08 15:24:37', '2023-03-11 19:54:20', NULL, NULL);
INSERT INTO `kpn_site_channel` VALUES (100016, 1, 'kk-porn01', 'kk黄站1号', '自慰', 'masturbate', 'សម្រេចកាមដោយខ្លួន', 'test/e5336f1f-71fa-4388-b64e-9f7273332f0e.jpg', 0, 1, '100020,100019,100018,100017,100016,100015', 0, '2023-03-08 15:24:37', '2023-03-11 19:54:20', NULL, NULL);
INSERT INTO `kpn_site_channel` VALUES (100017, 18, 'code08', 'lulu08', '找片', 'find film', 'ស្វែងរកខ្សែភាពយន្ត', NULL, 1, 1, NULL, 1, '2023-03-08 15:24:36', '2023-03-09 21:28:22', NULL, NULL);
INSERT INTO `kpn_site_channel` VALUES (100018, 18, 'code08', 'lulu08', '最热', 'popular', 'ពេញនិយមបំផុត។', NULL, 2, 1, NULL, 1, '2023-03-08 15:24:36', '2023-03-09 21:28:22', NULL, NULL);
INSERT INTO `kpn_site_channel` VALUES (100019, 18, 'code08', 'lulu08', '最新', 'newest', 'ថ្មីបំផុត', NULL, 3, 1, NULL, 1, '2023-03-08 15:24:36', '2023-03-09 21:28:22', NULL, NULL);
INSERT INTO `kpn_site_channel` VALUES (100020, 18, 'code08', 'lulu08', '推荐', 'recommend', 'ណែនាំ', NULL, 4, 1, NULL, 1, '2023-03-08 15:24:36', '2023-03-09 21:28:22', NULL, NULL);

-- ----------------------------
-- Table structure for kpn_site_login_log
-- ----------------------------
DROP TABLE IF EXISTS `kpn_site_login_log`;
CREATE TABLE `kpn_site_login_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `site_id` bigint(11) NULL DEFAULT NULL COMMENT '站点id',
  `site_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点编码',
  `site_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点名称',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '会员账号',
  `nick_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `user_id` bigint(20) NOT NULL COMMENT '会员id',
  `login_time` datetime NOT NULL COMMENT '登录时间',
  `login_ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL COMMENT '登录ip',
  `login_location` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL COMMENT '登录国家',
  `login_device` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL COMMENT '设备',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL COMMENT '账号类型：APP：前端用户，BACKEND：后端管理用户',
  `vip` tinyint(255) NULL DEFAULT NULL COMMENT '是否vip 0普通会员,1vip',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '账号权限id(管理员)',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 918 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of kpn_site_login_log
-- ----------------------------
INSERT INTO `kpn_site_login_log` VALUES (704, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year001', 'year001', 94, '2023-02-21 00:35:13', '192.168.1.23', NULL, NULL, 'APP', 0, NULL, '2023-02-21 00:35:14', '2023-02-21 00:35:14', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (705, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year001', 'year001', 94, '2023-02-21 20:03:53', '192.168.1.23', NULL, NULL, 'APP', 0, NULL, '2023-02-21 20:07:47', '2023-02-21 20:07:47', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (706, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year001', 'year001', 94, '2023-02-21 20:39:27', '192.168.1.23', NULL, NULL, 'APP', 0, NULL, '2023-02-21 20:39:28', '2023-02-21 20:39:28', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (707, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year001', 'year001', 94, '2023-02-21 20:41:57', '192.168.1.23', NULL, NULL, 'APP', 0, NULL, '2023-02-21 20:41:57', '2023-02-21 20:41:57', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (708, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year001', 'year001', 94, '2023-02-21 23:00:52', '192.168.1.23', NULL, NULL, 'APP', 0, NULL, '2023-02-21 23:00:52', '2023-02-21 23:00:52', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (709, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year001', 'year001', 94, '2023-02-22 19:37:53', '192.168.1.23', NULL, NULL, 'APP', 0, NULL, '2023-02-22 19:37:54', '2023-02-22 19:37:54', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (710, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year001', 'year001', 94, '2023-02-01 20:08:03', '192.168.1.23', NULL, NULL, 'APP', 0, NULL, '2023-02-01 20:08:04', '2023-02-01 20:08:04', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (711, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year001', 'year001', 94, '2023-02-22 21:45:20', '192.168.1.23', NULL, NULL, 'APP', 1, NULL, '2023-02-22 21:45:21', '2023-02-22 21:45:21', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (712, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year001', 'year001', 94, '2023-02-10 22:50:27', '192.168.1.23', NULL, NULL, 'APP', 1, NULL, '2023-02-10 22:50:28', '2023-02-10 22:50:28', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (713, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_Year01', 'Year01', 6, '2023-02-22 23:28:35', '192.168.1.23', NULL, NULL, 'APP', 0, NULL, '2023-02-22 23:28:36', '2023-02-22 23:28:36', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (714, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year001', 'year001', 94, '2023-02-23 15:30:10', '192.168.1.23', NULL, NULL, 'APP', 1, NULL, '2023-02-23 15:30:10', '2023-02-23 15:30:10', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (715, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_Year01', 'Year01', 6, '2023-02-23 17:41:28', '192.168.1.23', NULL, NULL, 'APP', 0, NULL, '2023-02-23 17:41:29', '2023-02-23 17:41:29', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (716, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_Year01', 'Year01', 6, '2023-02-23 20:09:14', '192.168.1.23', NULL, NULL, 'APP', 0, NULL, '2023-02-23 20:09:14', '2023-02-23 20:09:14', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (717, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year002', 'year002', 100, '2023-02-23 20:57:06', '192.168.1.23', NULL, NULL, 'APP', 1, NULL, '2023-02-23 20:57:06', '2023-02-23 20:57:06', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (718, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_YEar01', 'YEar01', 80, '2023-02-23 21:41:08', '192.168.1.23', NULL, NULL, 'APP', 0, NULL, '2023-02-23 21:41:09', '2023-02-23 21:41:09', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (719, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year003', 'year003', 101, '2023-02-23 21:44:58', '192.168.1.23', NULL, NULL, 'APP', 1, NULL, '2023-02-23 21:44:59', '2023-02-23 21:44:59', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (720, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year001', 'year001', 94, '2023-02-23 23:39:39', '192.168.1.23', NULL, NULL, 'APP', 0, NULL, '2023-02-23 23:39:39', '2023-02-23 23:39:39', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (721, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year002', 'year002', 100, '2023-02-24 00:09:45', '192.168.1.23', NULL, NULL, 'APP', 0, NULL, '2023-02-24 00:09:45', '2023-02-24 00:09:45', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (722, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year004', 'year004', 102, '2023-02-24 00:13:54', '192.168.1.23', NULL, NULL, 'APP', 1, NULL, '2023-02-24 00:13:54', '2023-02-24 00:13:54', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (723, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year005', 'year005', 103, '2023-02-24 00:22:32', '192.168.1.23', NULL, NULL, 'APP', 1, NULL, '2023-02-24 00:22:33', '2023-02-24 00:22:33', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (724, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year001', 'year001', 94, '2023-02-24 16:02:13', '192.168.1.23', NULL, NULL, 'APP', 1, NULL, '2023-02-24 16:02:13', '2023-02-24 16:02:13', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (725, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year001', 'year001', 94, '2023-02-24 16:03:06', '192.168.1.23', NULL, NULL, 'APP', 1, NULL, '2023-02-24 16:03:07', '2023-02-24 16:03:07', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (726, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year001', 'year001', 94, '2023-02-24 17:18:29', '192.168.1.23', NULL, NULL, 'APP', 0, NULL, '2023-02-24 17:18:30', '2023-02-24 17:18:30', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (727, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year006', 'year006', 104, '2023-02-24 17:25:43', '192.168.1.23', NULL, NULL, 'APP', 1, NULL, '2023-02-24 17:25:44', '2023-02-24 17:25:44', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (728, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year001', 'year001', 94, '2023-02-24 21:01:23', '192.168.1.23', NULL, NULL, 'APP', 1, NULL, '2023-02-24 21:01:24', '2023-02-24 21:01:24', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (729, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year001', 'year001', 94, '2023-02-24 23:00:13', '192.168.1.23', NULL, NULL, 'APP', 1, NULL, '2023-02-24 23:00:13', '2023-02-24 23:00:13', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (730, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year001', 'year001', 94, '2023-02-25 14:17:56', '192.168.1.23', NULL, NULL, 'APP', 1, NULL, '2023-02-25 14:17:56', '2023-02-25 14:17:56', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (731, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year001', 'year001', 94, '2023-02-25 16:26:47', '192.168.1.23', NULL, NULL, 'APP', 1, NULL, '2023-02-25 16:26:48', '2023-02-25 16:26:48', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (732, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year001', 'year001', 94, '2023-02-25 20:30:27', '192.168.1.23', NULL, NULL, 'APP', 1, NULL, '2023-02-25 20:30:27', '2023-02-25 20:30:27', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (733, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year001', 'year001', 94, '2023-02-27 16:58:42', '192.168.1.23', NULL, NULL, 'APP', 1, NULL, '2023-02-27 16:58:43', '2023-02-27 16:58:43', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (734, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year001', 'year001', 94, '2023-02-27 20:50:32', '192.168.1.23', NULL, NULL, 'APP', 1, NULL, '2023-02-27 20:50:33', '2023-02-27 20:50:33', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (735, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year001', 'year001', 94, '2023-02-27 22:09:27', '192.168.1.23', NULL, NULL, 'APP', 1, NULL, '2023-02-27 22:09:28', '2023-02-27 22:09:28', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (736, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year001', 'year001', 94, '2023-02-27 22:36:58', '192.168.1.23', NULL, NULL, 'APP', 1, NULL, '2023-02-27 22:36:59', '2023-02-27 22:36:59', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (737, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year007', 'year007', 110, '2023-02-28 15:40:17', '192.168.1.23', NULL, NULL, 'APP', 0, NULL, '2023-02-28 15:40:18', '2023-02-28 15:40:18', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (738, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year008', 'year008', 111, '2023-02-28 15:41:36', '192.168.1.23', NULL, NULL, 'APP', 1, NULL, '2023-02-28 15:41:37', '2023-02-28 15:41:37', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (739, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year01', 'year01', 2, '2023-02-28 16:06:52', '192.168.1.23', NULL, NULL, 'APP', 0, NULL, '2023-02-28 16:06:53', '2023-02-28 16:06:53', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (740, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year01', 'year01', 2, '2023-02-28 17:16:00', '192.168.1.23', NULL, NULL, 'APP', 1, NULL, '2023-02-28 17:16:01', '2023-02-28 17:16:01', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (741, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year01', 'year01', 2, '2023-02-28 17:16:11', '192.168.1.23', NULL, NULL, 'APP', 1, NULL, '2023-02-28 17:16:12', '2023-02-28 17:16:12', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (742, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_aaaa123123', 'aaaa123123', 116, '2023-03-04 13:36:37', '192.168.1.23', NULL, NULL, 'APP', 0, NULL, '2023-03-04 13:36:37', '2023-03-04 13:36:37', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (743, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_jeff001', 'jeff001', 117, '2023-03-04 13:38:59', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-04 13:38:59', '2023-03-04 13:38:59', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (744, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_123123', '123123', 118, '2023-03-04 14:01:53', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-04 14:01:54', '2023-03-04 14:01:54', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (745, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_100861', '100861', 119, '2023-03-04 14:06:28', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-04 14:06:28', '2023-03-04 14:06:28', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (746, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_100026', '100026', 120, '2023-03-04 14:08:16', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-04 14:08:16', '2023-03-04 14:08:16', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (747, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_1000171', '1000171', 121, '2023-03-04 14:09:04', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-04 14:09:04', '2023-03-04 14:09:04', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (748, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_100015', '100015', 122, '2023-03-04 14:10:09', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-04 14:10:10', '2023-03-04 14:10:10', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (749, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_1000152', '1000152', 123, '2023-03-04 14:14:09', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-04 14:14:10', '2023-03-04 14:14:10', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (750, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_1000262', '1000262', 124, '2023-03-04 14:14:52', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-04 14:14:52', '2023-03-04 14:14:52', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (751, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_2222222', '2222222', 125, '2023-03-04 14:15:29', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-04 14:15:29', '2023-03-04 14:15:29', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (752, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_jeff001', 'jeff001', 117, '2023-03-04 14:31:16', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-04 14:31:17', '2023-03-04 14:31:17', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (753, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_123123', '123123', 118, '2023-03-04 14:31:57', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-04 14:31:58', '2023-03-04 14:31:58', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (754, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_123123', '123123', 118, '2023-03-04 14:35:25', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-04 14:35:26', '2023-03-04 14:35:26', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (755, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_123123', '123123', 118, '2023-03-04 14:37:24', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-04 14:37:25', '2023-03-04 14:37:25', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (756, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_123123', '123123', 118, '2023-03-04 14:38:22', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-04 14:38:23', '2023-03-04 14:38:23', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (757, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year001', 'year001', 94, '2023-03-04 16:54:21', '192.168.100.100', NULL, NULL, 'APP', 1, NULL, '2023-03-04 16:54:22', '2023-03-04 16:54:22', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (758, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_year001', 'year001', 94, '2023-03-04 17:07:06', '192.168.1.23', NULL, NULL, 'APP', 1, NULL, '2023-03-04 17:07:06', '2023-03-04 17:07:06', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (759, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_123123', '123123', 118, '2023-03-04 21:25:46', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-04 21:25:47', '2023-03-04 21:25:47', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (760, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_123123', '123123', 118, '2023-03-06 16:32:19', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-06 16:32:19', '2023-03-06 16:32:19', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (761, 11, 'code07', 'lulu07', 'lulu01', NULL, 95, '2023-03-08 13:49:28', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-08 13:49:29', '2023-03-08 13:49:29', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (762, 11, 'code07', 'lulu07', 'lulu01', NULL, 95, '2023-03-08 14:00:55', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-08 14:00:56', '2023-03-08 14:00:56', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (763, 11, 'code07', 'lulu07', 'lulu038', 'lulu038', 126, '2023-03-08 14:01:04', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-08 14:01:05', '2023-03-08 14:01:05', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (764, 11, 'code07', 'lulu07', 'lulu038', 'lulu038', 126, '2023-03-08 14:04:30', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-08 14:04:31', '2023-03-08 14:04:31', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (765, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_1331111110', '1331111110', 127, '2023-03-08 20:34:37', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-08 20:34:37', '2023-03-08 20:34:37', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (766, 11, 'code07', 'lulu07', 'lulu01', NULL, 95, '2023-03-09 13:43:03', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-09 13:43:04', '2023-03-09 13:43:04', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (767, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_1331111110', '1331111110', 127, '2023-03-09 21:08:29', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-09 21:08:30', '2023-03-09 21:08:30', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (768, 0, '0', '0', 'admin', '超级管理员', 1, '2023-03-09 23:01:14', '192.168.1.23', NULL, NULL, 'BACKEND', 0, NULL, '2023-03-09 23:01:14', '2023-03-09 23:01:14', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (769, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_1331111110', '1331111110', 127, '2023-03-10 22:45:26', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-10 22:45:27', '2023-03-10 22:45:27', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (770, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_1331111110', '1331111110', 127, '2023-03-12 00:04:53', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-12 00:04:54', '2023-03-12 00:04:54', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (771, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_sdfsdf', 'sdfsdf', 131, '2023-03-11 16:53:04', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-11 16:53:05', '2023-03-11 16:53:05', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (772, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_1331111110', '1331111110', 127, '2023-03-11 18:10:12', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-11 18:10:12', '2023-03-11 18:10:12', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (773, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_1331111110', '1331111110', 127, '2023-03-11 21:25:02', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-11 21:25:03', '2023-03-11 21:25:03', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (774, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_1331111110', '1331111110', 127, '2023-03-11 21:55:00', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-11 21:55:01', '2023-03-11 21:55:01', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (775, 0, '0', '0', 'admin', '超级管理员', 1, '2023-03-11 22:37:33', '192.168.1.23', NULL, NULL, 'BACKEND', 0, NULL, '2023-03-11 22:37:33', '2023-03-11 22:37:33', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (776, 0, '0', '0', 'admin', '超级管理员', 1, '2023-03-13 12:46:54', '192.168.1.23', NULL, NULL, 'BACKEND', 0, NULL, '2023-03-13 12:46:55', '2023-03-13 12:46:55', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (777, 0, '0', '0', 'admin', '超级管理员', 1, '2023-03-13 15:31:11', '192.168.1.23', NULL, NULL, 'BACKEND', 0, NULL, '2023-03-13 15:31:12', '2023-03-13 15:31:12', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (778, 0, '0', '0', 'admin', '超级管理员', 1, '2023-03-13 16:29:59', '192.168.1.23', NULL, NULL, 'BACKEND', 0, NULL, '2023-03-13 16:30:00', '2023-03-13 16:30:00', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (779, 0, '0', '0', 'admin', '超级管理员', 1, '2023-03-13 17:29:19', '192.168.1.23', NULL, NULL, 'BACKEND', 0, NULL, '2023-03-13 17:29:19', '2023-03-13 17:29:19', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (780, 0, '0', '0', 'admin', '超级管理员', 1, '2023-03-13 19:47:22', '192.168.1.23', NULL, NULL, 'BACKEND', 0, NULL, '2023-03-13 19:47:23', '2023-03-13 19:47:23', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (781, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_123123', '123123', 118, '2023-03-14 21:06:24', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-14 21:06:24', '2023-03-14 21:06:24', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (782, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_jackson', 'jackson', 132, '2023-03-16 14:36:04', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-16 14:36:05', '2023-03-16 14:36:05', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (783, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_1331111110', '1331111110', 127, '2023-03-16 16:30:30', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-16 16:30:31', '2023-03-16 16:30:31', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (784, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_1331111110', '1331111110', 127, '2023-03-16 16:32:28', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-16 16:32:28', '2023-03-16 16:32:28', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (785, 1, 'kk-porn01', 'kk黄站1号', 'lulu003', 'lulu003', 135, '2023-03-16 19:52:46', '192.168.100.100', NULL, NULL, 'BACKEND', 0, 3, '2023-03-16 19:52:47', '2023-03-16 19:52:47', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (786, 1, 'kk-porn01', 'kk黄站1号', 'lulu003', 'lulu003', 135, '2023-03-16 19:58:23', '192.168.100.100', NULL, NULL, 'BACKEND', 0, 3, '2023-03-16 19:58:24', '2023-03-16 19:58:24', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (787, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-16 20:16:59', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-16 20:16:59', '2023-03-16 20:16:59', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (788, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_1331111110', '1331111110', 127, '2023-03-16 20:40:45', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-16 20:40:45', '2023-03-16 20:40:45', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (789, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_1331111110', '1331111110', 127, '2023-03-16 22:08:18', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-16 22:08:19', '2023-03-16 22:08:19', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (790, 0, '0', '0', 'admin', '超级管理员', 1, '2023-03-16 23:25:11', '192.168.1.23', NULL, NULL, 'BACKEND', 0, NULL, '2023-03-16 23:25:12', '2023-03-16 23:25:12', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (791, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_1331111110', '1331111110', 127, '2023-03-17 01:15:32', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-17 01:15:33', '2023-03-17 01:15:33', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (792, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_1331111110', '1331111110', 127, '2023-03-17 01:44:16', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-17 01:44:16', '2023-03-17 01:44:16', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (793, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-17 12:38:40', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-17 12:38:41', '2023-03-17 12:38:41', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (794, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_1331111110', '1331111110', 127, '2023-03-17 13:30:53', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-17 13:30:54', '2023-03-17 13:30:54', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (795, 1, 'kk-porn01', 'kk黄站1号', 'lulu003', 'lulu003', 135, '2023-03-17 13:38:45', '192.168.100.100', NULL, NULL, 'BACKEND', 0, 3, '2023-03-17 13:38:45', '2023-03-17 13:38:45', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (796, 1, 'kk-porn01', 'kk黄站1号', 'lulu003', 'lulu003', 135, '2023-03-17 13:41:04', '192.168.100.100', NULL, NULL, 'BACKEND', 0, 3, '2023-03-17 13:41:04', '2023-03-17 13:41:04', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (797, 1, 'kk-porn01', 'kk黄站1号', 'lulu003', 'lulu003', 135, '2023-03-17 13:41:51', '192.168.100.100', NULL, NULL, 'BACKEND', 0, 3, '2023-03-17 13:41:52', '2023-03-17 13:41:52', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (798, 1, 'kk-porn01', 'kk黄站1号', 'lulu003', 'lulu003', 135, '2023-03-17 14:05:37', '192.168.100.100', NULL, NULL, 'BACKEND', 0, 3, '2023-03-17 14:05:37', '2023-03-17 14:05:37', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (799, 1, 'kk-porn01', 'kk黄站1号', 'lulu003', 'lulu003', 135, '2023-03-17 14:09:50', '192.168.100.100', NULL, NULL, 'BACKEND', 0, 3, '2023-03-17 14:09:51', '2023-03-17 14:09:51', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (800, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-17 19:37:57', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-17 19:37:57', '2023-03-17 19:37:57', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (801, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-17 19:39:42', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-17 19:39:42', '2023-03-17 19:39:42', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (802, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-17 19:43:18', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-17 19:43:19', '2023-03-17 19:43:19', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (803, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-17 21:00:54', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-17 21:00:55', '2023-03-17 21:00:55', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (804, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-17 21:01:39', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-17 21:01:40', '2023-03-17 21:01:40', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (805, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-17 21:13:14', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-17 21:13:14', '2023-03-17 21:13:14', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (806, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe12323', 'qwe12323', 137, '2023-03-17 21:30:19', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-17 21:30:19', '2023-03-17 21:30:19', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (807, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-17 22:04:34', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-17 22:04:34', '2023-03-17 22:04:34', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (808, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe1123', 'qwe1123', 138, '2023-03-17 22:22:19', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-17 22:22:20', '2023-03-17 22:22:20', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (809, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe2233', 'qwe2233', 139, '2023-03-17 22:23:05', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-17 22:23:05', '2023-03-17 22:23:05', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (810, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-18 15:27:26', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-18 15:27:26', '2023-03-18 15:27:26', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (811, 1, 'kk-porn01', 'kk黄站1号', 'lulu003', 'lulu003', 135, '2023-03-18 16:55:20', '192.168.100.100', NULL, NULL, 'BACKEND', 0, 3, '2023-03-18 16:55:20', '2023-03-18 16:55:20', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (812, 0, '0', '0', 'admin', '超级管理员', 1, '2023-03-19 22:32:01', '192.168.100.100', NULL, NULL, 'BACKEND', 0, NULL, '2023-03-19 22:32:02', '2023-03-19 22:32:02', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (813, 0, '0', '0', 'admin', '超级管理员', 1, '2023-03-19 22:32:05', '192.168.100.100', NULL, NULL, 'BACKEND', 0, NULL, '2023-03-19 22:32:06', '2023-03-19 22:32:06', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (814, 0, '0', '0', 'admin', '超级管理员', 1, '2023-03-19 23:09:01', '192.168.100.100', NULL, NULL, 'BACKEND', 0, NULL, '2023-03-19 23:09:01', '2023-03-19 23:09:01', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (815, 0, '0', '0', 'admin', '超级管理员', 1, '2023-03-19 23:12:11', '192.168.100.100', NULL, NULL, 'BACKEND', 0, NULL, '2023-03-19 23:12:12', '2023-03-19 23:12:12', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (816, 0, '0', '0', 'admin', '超级管理员', 1, '2023-03-19 23:13:56', '192.168.100.100', NULL, NULL, 'BACKEND', 0, NULL, '2023-03-19 23:13:57', '2023-03-19 23:13:57', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (817, 0, '0', '0', 'admin', '超级管理员', 1, '2023-03-19 23:14:42', '192.168.100.100', NULL, NULL, 'BACKEND', 0, NULL, '2023-03-19 23:14:42', '2023-03-19 23:14:42', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (818, 0, '0', '0', 'admin', '超级管理员', 1, '2023-03-19 23:16:09', '192.168.100.100', NULL, NULL, 'BACKEND', 0, NULL, '2023-03-19 23:16:09', '2023-03-19 23:16:09', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (819, 0, '0', '0', 'admin', '超级管理员', 1, '2023-03-19 23:18:03', '192.168.100.100', NULL, NULL, 'BACKEND', 0, NULL, '2023-03-19 23:18:03', '2023-03-19 23:18:03', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (820, 0, '0', '0', 'admin', '超级管理员', 1, '2023-03-19 23:19:00', '192.168.100.100', NULL, NULL, 'BACKEND', 0, NULL, '2023-03-19 23:19:01', '2023-03-19 23:19:01', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (821, 0, '0', '0', 'admin', '超级管理员', 1, '2023-03-19 23:19:59', '192.168.100.100', NULL, NULL, 'BACKEND', 0, NULL, '2023-03-19 23:19:59', '2023-03-19 23:19:59', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (822, 0, '0', '0', 'admin', '超级管理员', 1, '2023-03-19 23:23:24', '192.168.100.100', NULL, NULL, 'BACKEND', 0, NULL, '2023-03-19 23:23:25', '2023-03-19 23:23:25', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (823, 0, '0', '0', 'admin', '超级管理员', 1, '2023-03-19 23:25:31', '192.168.100.100', NULL, NULL, 'BACKEND', 0, NULL, '2023-03-19 23:25:32', '2023-03-19 23:25:32', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (824, 0, '0', '0', 'admin', '超级管理员', 1, '2023-03-19 23:27:02', '192.168.100.100', NULL, NULL, 'BACKEND', 0, NULL, '2023-03-19 23:27:03', '2023-03-19 23:27:03', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (825, 0, '0', '0', 'admin', '超级管理员', 1, '2023-03-19 23:28:35', '192.168.100.100', NULL, NULL, 'BACKEND', 0, NULL, '2023-03-19 23:28:36', '2023-03-19 23:28:36', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (826, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-20 15:16:14', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-20 15:16:14', '2023-03-20 15:16:14', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (827, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-20 16:13:01', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-20 16:13:02', '2023-03-20 16:13:02', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (828, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-20 16:14:57', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-20 16:14:58', '2023-03-20 16:14:58', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (829, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-20 19:58:11', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-20 19:58:11', '2023-03-20 19:58:11', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (830, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-20 21:01:43', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-20 21:01:43', '2023-03-20 21:01:43', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (831, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-21 22:21:50', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-21 22:21:50', '2023-03-21 22:21:50', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (832, 0, '0', '0', 'admin', '超级管理员', 1, '2023-03-22 04:18:31', '192.168.100.100', NULL, NULL, 'BACKEND', 0, NULL, '2023-03-22 04:18:32', '2023-03-22 04:18:32', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (833, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-22 05:12:48', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-22 05:12:48', '2023-03-22 05:12:48', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (834, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-22 05:12:59', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-22 05:12:59', '2023-03-22 05:12:59', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (835, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-22 05:13:23', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-22 05:13:24', '2023-03-22 05:13:24', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (836, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-22 05:24:08', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-22 05:24:08', '2023-03-22 05:24:08', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (837, 1, 'kk-porn01', 'kk黄站1号', 'lulu003', 'lulu003', 135, '2023-03-22 06:28:40', '192.168.100.100', NULL, NULL, 'BACKEND', 0, 3, '2023-03-22 06:28:41', '2023-03-22 06:28:41', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (838, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-22 20:35:51', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-22 20:35:51', '2023-03-22 20:35:51', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (839, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-22 20:38:41', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-22 20:38:42', '2023-03-22 20:38:42', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (840, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-22 21:22:39', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-22 21:22:39', '2023-03-22 21:22:39', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (841, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-22 21:22:57', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-22 21:22:57', '2023-03-22 21:22:57', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (842, 1, 'kk-porn01', 'kk黄站1号', 'lulu003', 'lulu003', 135, '2023-03-22 21:35:52', '192.168.100.100', NULL, NULL, 'BACKEND', 0, 3, '2023-03-22 21:35:53', '2023-03-22 21:35:53', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (843, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-22 21:51:06', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-22 21:51:06', '2023-03-22 21:51:06', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (844, 0, '0', '0', 'admin', '超级管理员', 1, '2023-03-22 22:08:22', '192.168.100.100', NULL, NULL, 'BACKEND', 0, NULL, '2023-03-22 22:08:23', '2023-03-22 22:08:23', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (845, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-22 22:32:40', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-22 22:32:41', '2023-03-22 22:32:41', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (846, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-22 22:33:01', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-22 22:33:01', '2023-03-22 22:33:01', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (847, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-22 22:34:00', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-22 22:34:01', '2023-03-22 22:34:01', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (848, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-22 22:37:18', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-22 22:37:19', '2023-03-22 22:37:19', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (849, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-22 22:45:44', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-22 22:45:44', '2023-03-22 22:45:44', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (850, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-22 22:50:50', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-22 22:50:50', '2023-03-22 22:50:50', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (851, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-22 22:51:26', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-22 22:51:27', '2023-03-22 22:51:27', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (852, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-22 23:27:51', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-22 23:27:52', '2023-03-22 23:27:52', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (853, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-22 23:39:33', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-22 23:39:34', '2023-03-22 23:39:34', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (854, 1, 'kk-porn01', 'kk黄站1号', 'lulu003', 'lulu003', 135, '2023-03-23 00:02:12', '192.168.100.100', NULL, NULL, 'BACKEND', 0, 3, '2023-03-23 00:02:12', '2023-03-23 00:02:12', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (855, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-23 00:03:36', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-23 00:03:36', '2023-03-23 00:03:36', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (856, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-23 00:07:28', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-23 00:07:28', '2023-03-23 00:07:28', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (857, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-23 00:35:15', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-23 00:35:16', '2023-03-23 00:35:16', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (858, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-23 00:51:49', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-23 00:51:50', '2023-03-23 00:51:50', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (859, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-23 01:00:14', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-23 01:00:15', '2023-03-23 01:00:15', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (860, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-23 01:21:15', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-23 01:21:16', '2023-03-23 01:21:16', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (861, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-23 01:22:08', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-23 01:22:08', '2023-03-23 01:22:08', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (862, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-23 20:25:27', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-23 20:25:27', '2023-03-23 20:25:27', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (863, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-23 23:45:18', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-23 23:45:19', '2023-03-23 23:45:19', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (864, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-23 23:45:42', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-23 23:45:43', '2023-03-23 23:45:43', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (865, 1, 'kk-porn01', 'kk黄站1号', 'lulu003', 'lulu003', 135, '2023-03-24 00:45:46', '192.168.100.100', NULL, NULL, 'BACKEND', 0, 3, '2023-03-24 00:45:47', '2023-03-24 00:45:47', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (866, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-24 01:00:04', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-24 01:00:04', '2023-03-24 01:00:04', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (867, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-24 03:27:19', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-24 03:27:20', '2023-03-24 03:27:20', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (868, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-24 03:48:16', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-24 03:48:17', '2023-03-24 03:48:17', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (869, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-24 05:03:48', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-24 05:03:48', '2023-03-24 05:03:48', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (870, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-24 06:01:27', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-24 06:01:27', '2023-03-24 06:01:27', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (871, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-24 06:07:01', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-24 06:07:02', '2023-03-24 06:07:02', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (872, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-24 20:43:44', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-24 20:43:45', '2023-03-24 20:43:45', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (873, 0, '0', '0', 'admin', '超级管理员', 1, '2023-03-24 21:31:10', '192.168.100.100', NULL, NULL, 'BACKEND', 0, NULL, '2023-03-24 21:31:10', '2023-03-24 21:31:10', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (874, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-25 00:21:11', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-25 00:21:12', '2023-03-25 00:21:12', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (875, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-25 00:21:27', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-25 00:21:28', '2023-03-25 00:21:28', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (876, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-25 00:24:10', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-25 00:24:10', '2023-03-25 00:24:10', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (877, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-25 03:38:01', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-25 03:38:01', '2023-03-25 03:38:01', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (878, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-25 22:10:05', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-25 22:10:06', '2023-03-25 22:10:06', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (879, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-25 16:30:12', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-25 16:30:12', '2023-03-25 16:30:12', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (880, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-25 16:32:15', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-25 16:32:16', '2023-03-25 16:32:16', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (881, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-25 17:49:17', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-25 17:49:18', '2023-03-25 17:49:18', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (882, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe11233', 'qwe11233', 140, '2023-03-25 17:49:45', '192.168.100.100', NULL, NULL, 'APP', 1, NULL, '2023-03-25 17:49:45', '2023-03-25 17:49:45', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (883, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-25 19:21:16', '192.168.100.100', NULL, NULL, 'APP', 1, NULL, '2023-03-25 19:21:16', '2023-03-25 19:21:16', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (884, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-25 19:23:42', '192.168.100.100', NULL, NULL, 'APP', 1, NULL, '2023-03-25 19:23:43', '2023-03-25 19:23:43', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (885, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-25 20:55:48', '192.168.100.100', NULL, NULL, 'APP', 1, NULL, '2023-03-25 20:55:49', '2023-03-25 20:55:49', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (886, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-25 21:16:02', '192.168.100.100', NULL, NULL, 'APP', 1, NULL, '2023-03-25 21:16:02', '2023-03-25 21:16:02', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (887, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-26 15:26:50', '192.168.100.100', NULL, NULL, 'APP', 1, NULL, '2023-03-26 15:26:50', '2023-03-26 15:26:50', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (888, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe1122333', 'qwe1122333', 141, '2023-03-26 17:02:21', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-26 17:02:22', '2023-03-26 17:02:22', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (889, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_chezai01', 'chezai01', 142, '2023-03-26 17:11:02', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-26 17:11:03', '2023-03-26 17:11:03', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (890, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-27 12:23:44', '192.168.100.100', NULL, NULL, 'APP', 1, NULL, '2023-03-27 12:23:45', '2023-03-27 12:23:45', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (891, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-27 15:34:28', '192.168.100.100', NULL, NULL, 'APP', 1, NULL, '2023-03-27 15:34:28', '2023-03-27 15:34:28', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (892, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-27 16:46:09', '192.168.100.100', NULL, NULL, 'APP', 1, NULL, '2023-03-27 16:46:09', '2023-03-27 16:46:09', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (893, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-27 16:56:17', '192.168.100.100', NULL, NULL, 'APP', 1, NULL, '2023-03-27 16:56:18', '2023-03-27 16:56:18', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (894, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-27 17:06:53', '192.168.100.100', NULL, NULL, 'APP', 1, NULL, '2023-03-27 17:06:53', '2023-03-27 17:06:53', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (895, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-27 17:19:11', '192.168.100.100', NULL, NULL, 'APP', 1, NULL, '2023-03-27 17:19:12', '2023-03-27 17:19:12', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (896, 1, 'kk-porn01', 'kk黄站1号', 'lulu003', 'lulu003', 135, '2023-03-27 17:29:09', '192.168.100.100', NULL, NULL, 'BACKEND', 0, 3, '2023-03-27 17:29:09', '2023-03-27 17:29:09', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (897, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112333', 'qwe112333', 143, '2023-03-27 20:07:59', '192.168.100.100', NULL, NULL, 'APP', 1, NULL, '2023-03-27 20:08:00', '2023-03-27 20:08:00', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (898, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-27 20:08:08', '192.168.100.100', NULL, NULL, 'APP', 1, NULL, '2023-03-27 20:08:08', '2023-03-27 20:08:08', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (899, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-27 20:23:51', '192.168.100.100', NULL, NULL, 'APP', 1, NULL, '2023-03-27 20:23:51', '2023-03-27 20:23:51', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (900, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-27 20:24:18', '192.168.100.100', NULL, NULL, 'APP', 1, NULL, '2023-03-27 20:24:19', '2023-03-27 20:24:19', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (901, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-27 20:24:29', '192.168.100.100', NULL, NULL, 'APP', 1, NULL, '2023-03-27 20:24:30', '2023-03-27 20:24:30', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (902, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-27 20:25:46', '192.168.100.100', NULL, NULL, 'APP', 1, NULL, '2023-03-27 20:25:47', '2023-03-27 20:25:47', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (903, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-27 20:25:53', '192.168.100.100', NULL, NULL, 'APP', 1, NULL, '2023-03-27 20:25:54', '2023-03-27 20:25:54', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (904, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-27 20:37:29', '192.168.100.100', NULL, NULL, 'APP', 1, NULL, '2023-03-27 20:37:30', '2023-03-27 20:37:30', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (905, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-27 20:37:40', '192.168.100.100', NULL, NULL, 'APP', 1, NULL, '2023-03-27 20:37:41', '2023-03-27 20:37:41', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (906, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-27 20:39:53', '192.168.100.100', NULL, NULL, 'APP', 1, NULL, '2023-03-27 20:39:54', '2023-03-27 20:39:54', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (907, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe1122333', 'qwe1122333', 141, '2023-03-27 20:50:31', '192.168.100.100', NULL, NULL, 'APP', 0, NULL, '2023-03-27 20:50:32', '2023-03-27 20:50:32', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (908, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-27 20:51:04', '192.168.100.100', NULL, NULL, 'APP', 1, NULL, '2023-03-27 20:51:04', '2023-03-27 20:51:04', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (909, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-27 21:50:18', '192.168.100.100', NULL, NULL, 'APP', 1, NULL, '2023-03-27 21:50:19', '2023-03-27 21:50:19', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (910, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-27 21:52:17', '192.168.100.100', NULL, NULL, 'APP', 1, NULL, '2023-03-27 21:52:18', '2023-03-27 21:52:18', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (911, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-27 21:52:22', '192.168.100.100', NULL, NULL, 'APP', 1, NULL, '2023-03-27 21:52:23', '2023-03-27 21:52:23', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (912, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-27 21:55:07', '192.168.100.100', NULL, NULL, 'APP', 1, NULL, '2023-03-27 21:55:08', '2023-03-27 21:55:08', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (913, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-27 21:55:13', '192.168.100.100', NULL, NULL, 'APP', 1, NULL, '2023-03-27 21:55:13', '2023-03-27 21:55:13', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (914, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-27 21:55:17', '192.168.100.100', NULL, NULL, 'APP', 1, NULL, '2023-03-27 21:55:17', '2023-03-27 21:55:17', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (915, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-27 21:57:32', '192.168.100.100', NULL, NULL, 'APP', 1, NULL, '2023-03-27 21:57:33', '2023-03-27 21:57:33', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (916, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-27 21:57:39', '192.168.100.100', NULL, NULL, 'APP', 1, NULL, '2023-03-27 21:57:39', '2023-03-27 21:57:39', NULL, NULL);
INSERT INTO `kpn_site_login_log` VALUES (917, 1, 'kk-porn01', 'kk黄站1号', 'kk-porn01_qwe112233', 'qwe112233', 136, '2023-03-28 13:19:06', '192.168.100.100', NULL, NULL, 'APP', 1, NULL, '2023-03-28 13:19:07', '2023-03-28 13:19:07', NULL, NULL);

-- ----------------------------
-- Table structure for kpn_site_movie
-- ----------------------------
DROP TABLE IF EXISTS `kpn_site_movie`;
CREATE TABLE `kpn_site_movie`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `site_id` bigint(20) NULL DEFAULT NULL COMMENT '站点id',
  `site_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点编码',
  `site_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点名称',
  `actor_id` bigint(20) NULL DEFAULT NULL COMMENT '演员id',
  `actor_name_zh` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '演员中文名',
  `actor_name_en` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '演员英文名',
  `actor_name_kh` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '演员柬文名',
  `actor_create_time` datetime NULL DEFAULT NULL COMMENT '演员创建时间',
  `movie_id` bigint(20) NULL DEFAULT NULL COMMENT '影片id',
  `name_zh` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '中文名称',
  `name_en` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '英文名称',
  `name_kh` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '柬文名称',
  `duration` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '影片时长(HH:mm:ss 如00:10:02)',
  `pay_type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '付费类型 0:免费,1:付费',
  `vv` bigint(255) NOT NULL DEFAULT 0 COMMENT '播放量',
  `favorites` bigint(255) NOT NULL DEFAULT 0 COMMENT '影片收藏量',
  `status` tinyint(255) NOT NULL DEFAULT 0 COMMENT '状态 0待发布,1上架,2下架',
  `url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '播放地址',
  `cover_url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '封页图地址',
  `country` tinyint(255) NULL DEFAULT NULL COMMENT '影片所属国家 0:日本,1:中国大陆,2:中国台湾,3:韩国,4:欧美,5:东南亚,6:其他地区',
  `type` tinyint(255) NOT NULL DEFAULT 0 COMMENT '影片类型 0无码 1有码',
  `shooting_type` tinyint(2) NOT NULL DEFAULT 0 COMMENT '拍摄性质 0:专业拍摄,1:偷拍,2:自拍,3:其他',
  `subtitle_type` tinyint(2) NOT NULL DEFAULT 0 COMMENT '字幕类型 0:无字幕,1:中文,2:英文,3:中英,4:其他',
  `serial_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '番号',
  `company` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '制作商',
  `publish_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发行时间(处理时间)',
  `remark` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简介',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_siteid_movieid`(`site_id`, `movie_id`) USING BTREE COMMENT '唯一索引',
  UNIQUE INDEX `idx_siteid_status_actorcreatetime`(`site_id`, `status`, `actor_create_time`, `movie_id`) USING BTREE COMMENT '站点演员创建时间排序',
  UNIQUE INDEX `idx_siteid_status_paytype_vv`(`site_id`, `status`, `vv`, `create_time`, `movie_id`) USING BTREE COMMENT '站点影片观看量排序',
  UNIQUE INDEX `idx_siteid_status_duration`(`site_id`, `status`, `duration`, `create_time`, `movie_id`) USING BTREE COMMENT '站点演员时长排序',
  INDEX `uni_siteid_movie`(`site_id`, `actor_id`, `movie_id`, `status`, `update_time`, `country`, `type`, `shooting_type`, `subtitle_type`) USING BTREE COMMENT '站点影片列表查询',
  INDEX `idx_siteid_status_actorid_movieid`(`site_id`, `status`, `actor_id`, `movie_id`) USING BTREE COMMENT '站点演员查询',
  FULLTEXT INDEX `uni_siteid_movie_name`(`name_en`, `name_kh`, `name_zh`) COMMENT '站点影片列表名字查询'
) ENGINE = InnoDB AUTO_INCREMENT = 247 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '站点影片' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kpn_site_movie
-- ----------------------------
INSERT INTO `kpn_site_movie` VALUES (1, 1, '1,kk-porn01', 'kk黄站1号', 0, 'unknown', 'unknown', NULL, '2023-02-01 23:07:24', 100000, '淫母 3', '淫母 3-en', '淫母 3-kh', '01:00:00', 1, 140, 1, 1, '/nVjse4zW/index.m3u8', '/nVjse4zW/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '日本动画', '2023-03-08 13:18:59', '2023-03-27 21:35:36', 'admin', 'lulu003');
INSERT INTO `kpn_site_movie` VALUES (2, 1, '1,kk-porn01', 'kk黄站1号', 0, 'unknown', 'unknown', NULL, '2023-02-01 23:07:24', 100001, '双胞胎的母性本能 1', '双胞胎的母性本能 1-en', '双胞胎的母性本能 1-kh', '01:00:00', 1, 23, 0, 1, '/sMEInRSU/index.m3u8', '/sMEInRSU/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '日本动画', '2023-03-08 13:18:59', '2023-03-27 21:57:57', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (3, 1, '1,kk-porn01', 'kk黄站1号', 0, 'unknown', 'unknown', NULL, '2023-02-01 23:07:24', 100002, '集团痴汉电车 1', '集团痴汉电车 1-en', '集团痴汉电车 1-kh', '01:00:00', 1, 35, 1, 1, '/nEG5TBOQ/index.m3u8', '/nEG5TBOQ/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '日本动画', '2023-03-08 13:18:59', '2023-03-28 15:26:55', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (4, 1, '1,kk-porn01', 'kk黄站1号', 0, 'unknown', 'unknown', NULL, '2023-02-01 23:07:24', 100003, '大失禁海倫娜 2', '大失禁海倫娜 2-en', '大失禁海倫娜 2-kh', '01:00:00', 1, 142, 1, 1, '/2rbe3TNe/index.m3u8', '/2rbe3TNe/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '日本动画', '2023-03-08 13:18:59', '2023-03-28 15:31:55', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (5, 1, '1,kk-porn01', 'kk黄站1号', 0, 'unknown', 'unknown', NULL, '2023-02-01 23:07:24', 100004, '双胞胎的母性本能 2', '双胞胎的母性本能 2-en', '双胞胎的母性本能 2-kh', '01:00:00', 1, 219, 1, 1, '/989ncNPQ/index.m3u8', '/989ncNPQ/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '日本动画', '2023-03-08 13:18:59', '2023-03-28 15:37:24', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (6, 1, '1,kk-porn01', 'kk黄站1号', 0, 'unknown', 'unknown', NULL, '2023-02-01 23:07:24', 100005, '人妻×人妻 1', '人妻×人妻 1-en', '人妻×人妻 1-kh', '01:00:00', 1, 227, 1, 1, '/Y6gJX0DZ/index.m3u8', '/Y6gJX0DZ/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '日本动画', '2023-03-08 13:18:59', '2023-03-27 21:49:40', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (7, 1, '1,kk-porn01', 'kk黄站1号', 0, 'unknown', 'unknown', NULL, '2023-02-01 23:07:24', 100006, '毛茸茸大正电动娘ARISA 1', '毛茸茸大正电动娘ARISA 1-en', '毛茸茸大正电动娘ARISA 1-kh', '01:00:00', 1, 1, 0, 1, '/qdXQukrJ/index.m3u8', '/qdXQukrJ/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '日本动画', '2023-03-08 13:18:59', '2023-03-16 20:54:55', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (8, 1, '1,kk-porn01', 'kk黄站1号', 0, 'unknown', 'unknown', NULL, '2023-02-01 23:07:24', 100007, '魔法少女梅露露 1', '魔法少女梅露露 1-en', '魔法少女梅露露 1-kh', '01:00:00', 1, 1, 0, 1, '/ssqs08Pg/index.m3u8', '/ssqs08Pg/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '日本动画', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (9, 1, '1,kk-porn01', 'kk黄站1号', 0, 'unknown', 'unknown', NULL, '2023-02-01 23:07:24', 100008, '淫母 2', '淫母 2-en', '淫母 2-kh', '01:00:00', 1, 16, 0, 1, '/rwutFZJe/index.m3u8', '/rwutFZJe/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '日本动画', '2023-03-08 13:18:59', '2023-03-23 22:08:30', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (10, 1, '1,kk-porn01', 'kk黄站1号', 0, 'unknown', 'unknown', NULL, '2023-02-01 23:07:24', 100009, '安魂曲 Requiem 2', '安魂曲 Requiem 2-en', '安魂曲 Requiem 2-kh', '01:00:00', 1, 1, 0, 1, '/uS6yHIll/index.m3u8', '/uS6yHIll/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '日本动画', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (11, 1, '1,kk-porn01', 'kk黄站1号', 0, 'unknown', 'unknown', NULL, '2023-02-01 23:07:24', 100010, '集团痴汉电车 2', '集团痴汉电车 2-en', '集团痴汉电车 2-kh', '01:00:00', 1, 1, 0, 1, '/GRbaGWgT/index.m3u8', '/GRbaGWgT/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '日本动画', '2023-03-08 13:18:59', '2023-03-23 14:49:57', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (12, 1, '1,kk-porn01', 'kk黄站1号', 0, 'unknown', 'unknown', NULL, '2023-02-01 23:07:24', 100011, '大失禁海倫娜 1', '大失禁海倫娜 1-en', '大失禁海倫娜 1-kh', '01:00:00', 1, 1, 0, 1, '/nE8jqIi5/index.m3u8', '/nE8jqIi5/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '日本动画', '2023-03-08 13:18:59', '2023-03-13 20:57:18', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (13, 1, '1,kk-porn01', 'kk黄站1号', 0, 'unknown', 'unknown', NULL, '2023-02-01 23:07:24', 100012, '毛茸茸大正电动娘ARISA 2', '毛茸茸大正电动娘ARISA 2-en', '毛茸茸大正电动娘ARISA 2-kh', '01:00:00', 1, 0, 0, 1, '/8G2srVsN/index.m3u8', '/8G2srVsN/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '日本动画', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (14, 1, '1,kk-porn01', 'kk黄站1号', 0, 'unknown', 'unknown', NULL, '2023-02-01 23:07:24', 100013, '安魂曲 Requiem 1', '安魂曲 Requiem 1-en', '安魂曲 Requiem 1-kh', '01:00:00', 1, 3, 0, 1, '/UGUXmBS3/index.m3u8', '/UGUXmBS3/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '日本动画', '2023-03-08 13:18:59', '2023-03-27 19:50:51', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (15, 1, '1,kk-porn01', 'kk黄站1号', 0, 'unknown', 'unknown', NULL, '2023-02-01 23:07:24', 100014, '淫娘 3', '淫娘 3-en', '淫娘 3-kh', '01:00:00', 1, 1, 0, 1, '/OlM5fkCt/index.m3u8', '/OlM5fkCt/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '日本动画', '2023-03-08 13:18:59', '2023-03-24 14:09:05', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (16, 1, '1,kk-porn01', 'kk黄站1号', 0, 'unknown', 'unknown', NULL, '2023-02-01 23:07:24', 100015, '满淫电车 调书 3', '满淫电车 调书 3-en', '满淫电车 调书 3-kh', '01:00:00', 1, 62, 1, 1, '/i1fLpNn5/index.m3u8', '/i1fLpNn5/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '日本动画', '2023-03-08 13:18:59', '2023-03-28 15:31:57', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (17, 1, '1,kk-porn01', 'kk黄站1号', 0, 'unknown', 'unknown', NULL, '2023-02-01 23:07:24', 100016, '魔法少女梅露露 2', '魔法少女梅露露 2-en', '魔法少女梅露露 2-kh', '01:00:00', 1, 2, 1, 1, '/uGNP5Puw/index.m3u8', '/uGNP5Puw/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '日本动画', '2023-03-08 13:18:59', '2023-03-27 13:57:42', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (18, 1, '1,kk-porn01', 'kk黄站1号', 0, 'unknown', 'unknown', NULL, '2023-02-01 23:07:24', 100017, '人妻×人妻 2', '人妻×人妻 2-en', '人妻×人妻 2-kh', '01:00:00', 1, 11, 1, 1, '/YlqY5IxC/index.m3u8', '/YlqY5IxC/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '日本动画', '2023-03-08 13:18:59', '2023-03-28 16:36:58', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (19, 1, '1,kk-porn01', 'kk黄站1号', 0, 'unknown', 'unknown', NULL, '2023-02-01 23:07:24', 100018, '满淫电车 调书 2', '满淫电车 调书 2-en', '满淫电车 调书 2-kh', '01:00:00', 1, 6, 1, 1, '/iwGh27BE/index.m3u8', '/iwGh27BE/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '日本动画', '2023-03-08 13:18:59', '2023-03-27 14:41:22', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (20, 1, '1,kk-porn01', 'kk黄站1号', 0, 'unknown', 'unknown', NULL, '2023-02-01 23:07:24', 100019, '满淫电车 调书 1', '满淫电车 调书 1-en', '满淫电车 调书 1-kh', '01:00:00', 1, 94, 1, 1, '/r2Ojbcve/index.m3u8', '/r2Ojbcve/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '日本动画', '2023-03-08 13:18:59', '2023-03-28 16:15:32', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (21, 1, '1,kk-porn01', 'kk黄站1号', 0, 'unknown', 'unknown', NULL, '2023-02-01 23:07:24', 100020, '淫娘 2', '淫娘 2-en', '淫娘 2-kh', '01:00:00', 1, 2, 1, 1, '/lvJkNZIb/index.m3u8', '/lvJkNZIb/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '日本动画', '2023-03-08 13:18:59', '2023-03-27 13:57:56', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (22, 1, '1,kk-porn01', 'kk黄站1号', 0, 'unknown', 'unknown', NULL, '2023-02-01 23:07:24', 100021, '轮奸学园 1', '轮奸学园 1-en', '轮奸学园 1-kh', '01:00:00', 0, 4, 0, 1, '/Fj8bmsYk/index.m3u8', '/Fj8bmsYk/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '日本动画', '2023-03-08 13:18:59', '2023-03-16 21:46:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (23, 1, '1,kk-porn01', 'kk黄站1号', 0, 'unknown', 'unknown', NULL, '2023-02-01 23:07:24', 100022, '淫娘 1', '淫娘 1-en', '淫娘 1-kh', '01:00:00', 0, 0, 0, 1, '/qRxYRmca/index.m3u8', '/qRxYRmca/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '日本动画', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (24, 1, '1,kk-porn01', 'kk黄站1号', 0, 'unknown', 'unknown', NULL, '2023-02-01 23:07:24', 100023, '淫母 1', '淫母 1-en', '淫母 1-kh', '01:00:00', 0, 0, 0, 1, '/PcPFaCS8/index.m3u8', '/PcPFaCS8/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '日本动画', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (25, 1, '1,kk-porn01', 'kk黄站1号', 42, '美波桃', NULL, NULL, '2023-03-07 16:20:23', 100024, '巨乳兔女郎满足客人的所有性癖要求 MIAA-580', '巨乳兔女郎满足客人的所有性癖要求 MIAA-580-en', '巨乳兔女郎满足客人的所有性癖要求 MIAA-580-kh', '01:00:00', 0, 0, 0, 1, '/SKiJLd6N/index.m3u8', '/SKiJLd6N/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '美波桃', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (26, 1, '1,kk-porn01', 'kk黄站1号', 68, '小仓七海', NULL, NULL, '2023-03-07 16:20:24', 100025, '与美女大学生女友同居的性福生活 SSIS-401', '与美女大学生女友同居的性福生活 SSIS-401-en', '与美女大学生女友同居的性福生活 SSIS-401-kh', '01:00:00', 0, 5, 0, 1, '/5JFixdNW/index.m3u8', '/5JFixdNW/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '小仓七海', '2023-03-08 13:18:59', '2023-03-24 12:42:33', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (27, 1, '1,kk-porn01', 'kk黄站1号', 42, '美波桃', NULL, NULL, '2023-03-07 16:20:23', 100026, '随着肉棒的剧烈抽插两只雪白的大奶子上下晃动 BOBB-330', '随着肉棒的剧烈抽插两只雪白的大奶子上下晃动 BOBB-330-en', '随着肉棒的剧烈抽插两只雪白的大奶子上下晃动 BOBB-330-kh', '01:00:00', 0, 0, 0, 1, '/uWq1Vcpv/index.m3u8', '/uWq1Vcpv/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '美波桃', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (28, 1, '1,kk-porn01', 'kk黄站1号', 35, '香澄莉子', NULL, NULL, '2023-03-07 16:20:22', 100027, '两根巨屌连续抽插骚穴爽的骚货尖叫不止 SSIS-311', '两根巨屌连续抽插骚穴爽的骚货尖叫不止 SSIS-311-en', '两根巨屌连续抽插骚穴爽的骚货尖叫不止 SSIS-311-kh', '01:00:00', 0, 0, 0, 1, '/KjQOmYnn/index.m3u8', '/KjQOmYnn/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '香澄莉子', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (29, 1, '1,kk-porn01', 'kk黄站1号', 63, '白濑飞鸟', NULL, NULL, '2023-03-07 16:20:24', 100028, '学生妹子翘课与大叔宾馆激情干炮 JKSR-518', '学生妹子翘课与大叔宾馆激情干炮 JKSR-518-en', '学生妹子翘课与大叔宾馆激情干炮 JKSR-518-kh', '01:00:00', 0, 0, 0, 1, '/SxjvHRih/index.m3u8', '/SxjvHRih/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '白濑飞鸟', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (30, 1, '1,kk-porn01', 'kk黄站1号', 35, '香澄莉子', NULL, NULL, '2023-03-07 16:20:22', 100029, '美女出差被好色上司灌醉后强奸 SSIS-363', '美女出差被好色上司灌醉后强奸 SSIS-363-en', '美女出差被好色上司灌醉后强奸 SSIS-363-kh', '01:00:00', 0, 1, 0, 1, '/emLiiYlx/index.m3u8', '/emLiiYlx/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '香澄莉子', '2023-03-08 13:18:59', '2023-03-23 20:23:31', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (31, 1, '1,kk-porn01', 'kk黄站1号', 68, '小仓七海', NULL, NULL, '2023-03-07 16:20:24', 100030, '强行进去女友妹妹的嫩穴把她变成专属性工具 SSIS-348', '强行进去女友妹妹的嫩穴把她变成专属性工具 SSIS-348-en', '强行进去女友妹妹的嫩穴把她变成专属性工具 SSIS-348-kh', '01:00:00', 0, 5, 0, 1, '/sScKf88K/index.m3u8', '/sScKf88K/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '小仓七海', '2023-03-08 13:18:59', '2023-03-24 12:42:22', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (32, 1, '1,kk-porn01', 'kk黄站1号', 63, '白濑飞鸟', NULL, NULL, '2023-03-07 16:20:24', 100031, '小恶魔外遇专家调教M男爽翻天 HMN-100', '小恶魔外遇专家调教M男爽翻天 HMN-100-en', '小恶魔外遇专家调教M男爽翻天 HMN-100-kh', '01:00:00', 0, 38, 0, 1, '/e3lh8Waq/index.m3u8', '/e3lh8Waq/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '白濑飞鸟', '2023-03-08 13:18:59', '2023-03-27 22:04:40', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (33, 1, '1,kk-porn01', 'kk黄站1号', 63, '白濑飞鸟', NULL, NULL, '2023-03-07 16:20:24', 100032, '当着男友的面被陌生肉棒爆操骚穴 MKON-066', '当着男友的面被陌生肉棒爆操骚穴 MKON-066-en', '当着男友的面被陌生肉棒爆操骚穴 MKON-066-kh', '01:00:00', 0, 0, 0, 1, '/oQsBLvcg/index.m3u8', '/oQsBLvcg/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '白濑飞鸟', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (34, 1, '1,kk-porn01', 'kk黄站1号', 35, '香澄莉子', NULL, NULL, '2023-03-07 16:20:22', 100033, '第一次玩3P就被干到敏感肉体高潮不断 SSIS-255', '第一次玩3P就被干到敏感肉体高潮不断 SSIS-255-en', '第一次玩3P就被干到敏感肉体高潮不断 SSIS-255-kh', '01:00:00', 0, 1, 0, 1, '/qPnEafF4/index.m3u8', '/qPnEafF4/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '香澄莉子', '2023-03-08 13:18:59', '2023-03-16 21:18:46', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (35, 1, '1,kk-porn01', 'kk黄站1号', 35, '香澄莉子', NULL, NULL, '2023-03-07 16:20:22', 100034, '眼睁睁看着自己的女友被壮汉强奸 SSIS-389', '眼睁睁看着自己的女友被壮汉强奸 SSIS-389-en', '眼睁睁看着自己的女友被壮汉强奸 SSIS-389-kh', '01:00:00', 0, 0, 0, 1, '/zEDDQPtP/index.m3u8', '/zEDDQPtP/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '香澄莉子', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (36, 1, '1,kk-porn01', 'kk黄站1号', 63, '白濑飞鸟', NULL, NULL, '2023-03-07 16:20:24', 100035, '美少女被变态叔叔调教成性奴隶 MUDR-170', '美少女被变态叔叔调教成性奴隶 MUDR-170-en', '美少女被变态叔叔调教成性奴隶 MUDR-170-kh', '01:00:00', 0, 1, 0, 1, '/GLdnSUP4/index.m3u8', '/GLdnSUP4/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '白濑飞鸟', '2023-03-08 13:18:59', '2023-03-25 13:27:35', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (37, 1, '1,kk-porn01', 'kk黄站1号', 63, '白濑飞鸟', NULL, NULL, '2023-03-07 16:20:24', 100036, '泳装美少女被蒙面男的巨根操到瘫软在地 OKS-118', '泳装美少女被蒙面男的巨根操到瘫软在地 OKS-118-en', '泳装美少女被蒙面男的巨根操到瘫软在地 OKS-118-kh', '01:00:00', 0, 1, 0, 1, '/OGTC3V5d/index.m3u8', '/OGTC3V5d/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '白濑飞鸟', '2023-03-08 13:18:59', '2023-03-25 19:24:01', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (38, 1, '1,kk-porn01', 'kk黄站1号', 42, '美波桃', NULL, NULL, '2023-03-07 16:20:23', 100037, '游泳部的巨乳老师被我的肉棒抽插成了淫荡的性宠物 MVSD-489', '游泳部的巨乳老师被我的肉棒抽插成了淫荡的性宠物 MVSD-489-en', '游泳部的巨乳老师被我的肉棒抽插成了淫荡的性宠物 MVSD-489-kh', '01:00:00', 0, 0, 0, 1, '/CkbUrcfQ/index.m3u8', '/CkbUrcfQ/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '美波桃', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (39, 1, '1,kk-porn01', 'kk黄站1号', 35, '香澄莉子', NULL, NULL, '2023-03-07 16:20:22', 100038, '约会长腿妹子温泉宾馆激情性交 SSIS-416', '约会长腿妹子温泉宾馆激情性交 SSIS-416-en', '约会长腿妹子温泉宾馆激情性交 SSIS-416-kh', '01:00:00', 0, 1, 0, 1, '/VaJ0fKT1/index.m3u8', '/VaJ0fKT1/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '香澄莉子', '2023-03-08 13:18:59', '2023-03-23 20:23:47', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (40, 1, '1,kk-porn01', 'kk黄站1号', 68, '小仓七海', NULL, NULL, '2023-03-07 16:20:24', 100039, '外表清纯的女大学生首次拍片露出淫荡的痴态 SSIS-180', '外表清纯的女大学生首次拍片露出淫荡的痴态 SSIS-180-en', '外表清纯的女大学生首次拍片露出淫荡的痴态 SSIS-180-kh', '01:00:00', 0, 7, 0, 1, '/n4dfBpRa/index.m3u8', '/n4dfBpRa/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '小仓七海', '2023-03-08 13:18:59', '2023-03-24 12:42:34', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (41, 1, '1,kk-porn01', 'kk黄站1号', 30, '时田亞美', NULL, NULL, '2023-03-07 16:20:22', 100040, '第一次拍片的美女在肉棒的抽插下仅有的紧张感也消失了 FSDSS-358', '第一次拍片的美女在肉棒的抽插下仅有的紧张感也消失了 FSDSS-358-en', '第一次拍片的美女在肉棒的抽插下仅有的紧张感也消失了 FSDSS-358-kh', '01:00:00', 0, 2, 0, 1, '/fBFVdgLP/index.m3u8', '/fBFVdgLP/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '时田亞美', '2023-03-08 13:18:59', '2023-03-25 14:13:59', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (42, 1, '1,kk-porn01', 'kk黄站1号', 63, '白濑飞鸟', NULL, NULL, '2023-03-07 16:20:24', 100041, '痴女学生妹网上约来10名男人一起爽歪歪 HMN-162', '痴女学生妹网上约来10名男人一起爽歪歪 HMN-162-en', '痴女学生妹网上约来10名男人一起爽歪歪 HMN-162-kh', '01:00:00', 0, 0, 0, 1, '/cX3wXbdi/index.m3u8', '/cX3wXbdi/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '白濑飞鸟', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (43, 1, '1,kk-porn01', 'kk黄站1号', 35, '香澄莉子', NULL, NULL, '2023-03-07 16:20:22', 100042, '猛男操持着巨根急速抽插骚货的肉穴 SSIS-284', '猛男操持着巨根急速抽插骚货的肉穴 SSIS-284-en', '猛男操持着巨根急速抽插骚货的肉穴 SSIS-284-kh', '01:00:00', 0, 0, 0, 1, '/a46sewUQ/index.m3u8', '/a46sewUQ/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '香澄莉子', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (44, 1, '1,kk-porn01', 'kk黄站1号', 30, '时田亞美', NULL, NULL, '2023-03-07 16:20:22', 100043, '痴女被大叔的肉棒干到高潮连连 FSDSS-359', '痴女被大叔的肉棒干到高潮连连 FSDSS-359-en', '痴女被大叔的肉棒干到高潮连连 FSDSS-359-kh', '01:00:00', 0, 0, 0, 1, '/yMpS5bPI/index.m3u8', '/yMpS5bPI/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '时田亞美', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (45, 1, '1,kk-porn01', 'kk黄站1号', 25, '宍户里帆', NULL, NULL, '2023-03-07 16:20:22', 100044, '巨乳美女从中学时代开始的性交妄想初体验 MIDV-076（下）', '巨乳美女从中学时代开始的性交妄想初体验 MIDV-076（下）-en', '巨乳美女从中学时代开始的性交妄想初体验 MIDV-076（下）-kh', '01:00:00', 0, 2, 0, 1, '/9IVCdMLj/index.m3u8', '/9IVCdMLj/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '宍户里帆', '2023-03-08 13:18:59', '2023-03-27 19:47:00', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (46, 1, '1,kk-porn01', 'kk黄站1号', 63, '白濑飞鸟', NULL, NULL, '2023-03-07 16:20:24', 100045, '饥渴难耐的哥哥忍不住强奸了自己的继妹 ROYD-063', '饥渴难耐的哥哥忍不住强奸了自己的继妹 ROYD-063-en', '饥渴难耐的哥哥忍不住强奸了自己的继妹 ROYD-063-kh', '01:00:00', 0, 0, 0, 1, '/CIGxBQ2m/index.m3u8', '/CIGxBQ2m/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '白濑飞鸟', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (47, 1, '1,kk-porn01', 'kk黄站1号', 30, '时田亞美', NULL, NULL, '2023-03-07 16:20:22', 100046, '用肉棒对女友的妹妹进行性高潮指导 FSDSS-400', '用肉棒对女友的妹妹进行性高潮指导 FSDSS-400-en', '用肉棒对女友的妹妹进行性高潮指导 FSDSS-400-kh', '01:00:00', 0, 1, 0, 1, '/Xy5GlreQ/index.m3u8', '/Xy5GlreQ/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '时田亞美', '2023-03-08 13:18:59', '2023-03-17 15:20:25', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (48, 1, '1,kk-porn01', 'kk黄站1号', 68, '小仓七海', NULL, NULL, '2023-03-07 16:20:24', 100047, '美少女被摄影师挑逗的内裤湿了一大片 MMND-202', '美少女被摄影师挑逗的内裤湿了一大片 MMND-202-en', '美少女被摄影师挑逗的内裤湿了一大片 MMND-202-kh', '01:00:00', 0, 4, 0, 1, '/pXqlOpm3/index.m3u8', '/pXqlOpm3/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '小仓七海', '2023-03-08 13:18:59', '2023-03-24 14:27:39', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (49, 1, '1,kk-porn01', 'kk黄站1号', 63, '白濑飞鸟', NULL, NULL, '2023-03-07 16:20:24', 100048, '极品女优性感裸体写真集 SXAR-010', '极品女优性感裸体写真集 SXAR-010-en', '极品女优性感裸体写真集 SXAR-010-kh', '01:00:00', 0, 0, 0, 1, '/tsev3wuK/index.m3u8', '/tsev3wuK/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '白濑飞鸟', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (50, 1, '1,kk-porn01', 'kk黄站1号', 68, '小仓七海', NULL, NULL, '2023-03-07 16:20:24', 100049, '痴女学生妹子诱惑老师抽插自己的骚穴 SSIS-297', '痴女学生妹子诱惑老师抽插自己的骚穴 SSIS-297-en', '痴女学生妹子诱惑老师抽插自己的骚穴 SSIS-297-kh', '01:00:00', 0, 4, 0, 1, '/3E4YB9N7/index.m3u8', '/3E4YB9N7/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '小仓七海', '2023-03-08 13:18:59', '2023-03-24 12:42:29', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (51, 1, '1,kk-porn01', 'kk黄站1号', 42, '美波桃', NULL, NULL, '2023-03-07 16:20:23', 100050, '与客人温泉酒店激情性交大玩3P BAB-054', '与客人温泉酒店激情性交大玩3P BAB-054-en', '与客人温泉酒店激情性交大玩3P BAB-054-kh', '01:00:00', 0, 0, 0, 1, '/niVuUYtd/index.m3u8', '/niVuUYtd/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '美波桃', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (52, 1, '1,kk-porn01', 'kk黄站1号', 42, '美波桃', NULL, NULL, '2023-03-07 16:20:23', 100051, '电话叫来巨乳妓女兄弟俩一起爆操骚穴 EBOD-864', '电话叫来巨乳妓女兄弟俩一起爆操骚穴 EBOD-864-en', '电话叫来巨乳妓女兄弟俩一起爆操骚穴 EBOD-864-kh', '01:00:00', 0, 1, 0, 1, '/uqpVMVZu/index.m3u8', '/uqpVMVZu/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '美波桃', '2023-03-08 13:18:59', '2023-03-27 17:19:21', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (53, 1, '1,kk-porn01', 'kk黄站1号', 63, '白濑飞鸟', NULL, NULL, '2023-03-07 16:20:24', 100052, '为了救弟弟，姐姐成了黑社会的性宠物 MVSD-510', '为了救弟弟，姐姐成了黑社会的性宠物 MVSD-510-en', '为了救弟弟，姐姐成了黑社会的性宠物 MVSD-510-kh', '01:00:00', 0, 0, 0, 1, '/s02ncK79/index.m3u8', '/s02ncK79/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '白濑飞鸟', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (54, 1, '1,kk-porn01', 'kk黄站1号', 25, '宍户里帆', NULL, NULL, '2023-03-07 16:20:22', 100053, '巨乳美女从中学时代开始的性交妄想初体验 MIDV-076（上）', '巨乳美女从中学时代开始的性交妄想初体验 MIDV-076（上）-en', '巨乳美女从中学时代开始的性交妄想初体验 MIDV-076（上）-kh', '01:00:00', 0, 0, 0, 1, '/av6utpx6/index.m3u8', '/av6utpx6/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '宍户里帆', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (55, 1, '1,kk-porn01', 'kk黄站1号', 25, '宍户里帆', NULL, NULL, '2023-03-07 16:20:22', 100054, '童颜巨乳的女大学生下海拍片 MIDV-056', '童颜巨乳的女大学生下海拍片 MIDV-056-en', '童颜巨乳的女大学生下海拍片 MIDV-056-kh', '01:00:00', 0, 1, 0, 1, '/y4DECOAF/index.m3u8', '/y4DECOAF/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '宍户里帆', '2023-03-08 13:18:59', '2023-03-27 21:23:03', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (56, 1, '1,kk-porn01', 'kk黄站1号', 68, '小仓七海', NULL, NULL, '2023-03-07 16:20:24', 100055, '随时随地可以让你爆操嫩穴的极品女仆 SSIS-374', '随时随地可以让你爆操嫩穴的极品女仆 SSIS-374-en', '随时随地可以让你爆操嫩穴的极品女仆 SSIS-374-kh', '01:00:00', 0, 3, 0, 1, '/w1Gm1ej8/index.m3u8', '/w1Gm1ej8/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '小仓七海', '2023-03-08 13:18:59', '2023-03-27 19:47:07', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (57, 1, '1,kk-porn01', 'kk黄站1号', 30, '时田亞美', NULL, NULL, '2023-03-07 16:20:22', 100056, '美少女恳求肉棒插入自己的骚穴只求高潮 FSDSS-360', '美少女恳求肉棒插入自己的骚穴只求高潮 FSDSS-360-en', '美少女恳求肉棒插入自己的骚穴只求高潮 FSDSS-360-kh', '01:00:00', 0, 0, 0, 1, '/fVgRQ99T/index.m3u8', '/fVgRQ99T/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '时田亞美', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (58, 1, '1,kk-porn01', 'kk黄站1号', 25, '宍户里帆', NULL, NULL, '2023-03-07 16:20:22', 100057, '现役美女大学生按摩店体验被无套中出 MIDV-097', '现役美女大学生按摩店体验被无套中出 MIDV-097-en', '现役美女大学生按摩店体验被无套中出 MIDV-097-kh', '01:00:00', 0, 1, 0, 1, '/RYd9vQMz/index.m3u8', '/RYd9vQMz/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '宍户里帆', '2023-03-08 13:18:59', '2023-03-24 14:34:23', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (59, 1, '1,kk-porn01', 'kk黄站1号', 42, '美波桃', NULL, NULL, '2023-03-07 16:20:23', 100058, '巨乳学生妹子恳求肉棒无套中出爽翻天 WAAA-130', '巨乳学生妹子恳求肉棒无套中出爽翻天 WAAA-130-en', '巨乳学生妹子恳求肉棒无套中出爽翻天 WAAA-130-kh', '01:00:00', 0, 0, 0, 1, '/QBn1MYHb/index.m3u8', '/QBn1MYHb/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '美波桃', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (60, 1, '1,kk-porn01', 'kk黄站1号', 35, '香澄莉子', NULL, NULL, '2023-03-07 16:20:22', 100059, '众多巨屌男优对痴女进行子宫开放让她浑身抽搐爽歪歪 SSIS-336', '众多巨屌男优对痴女进行子宫开放让她浑身抽搐爽歪歪 SSIS-336-en', '众多巨屌男优对痴女进行子宫开放让她浑身抽搐爽歪歪 SSIS-336-kh', '01:00:00', 0, 1, 0, 1, '/7zBlGYAD/index.m3u8', '/7zBlGYAD/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '香澄莉子', '2023-03-08 13:18:59', '2023-03-27 20:16:13', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (61, 1, '1,kk-porn01', 'kk黄站1号', 68, '小仓七海', NULL, NULL, '2023-03-07 16:20:24', 100060, '性欲旺盛的痴女与炮友酒店激情干炮 SSIS-239', '性欲旺盛的痴女与炮友酒店激情干炮 SSIS-239-en', '性欲旺盛的痴女与炮友酒店激情干炮 SSIS-239-kh', '01:00:00', 0, 4, 0, 1, '/CFGW5EJb/index.m3u8', '/CFGW5EJb/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '小仓七海', '2023-03-08 13:18:59', '2023-03-24 12:42:32', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (62, 1, '1,kk-porn01', 'kk黄站1号', 68, '小仓七海', NULL, NULL, '2023-03-07 16:20:24', 100061, '巨屌狠狠插入子宫深处让痴女爽到腰部弓起痉挛不止 SSIS-209', '巨屌狠狠插入子宫深处让痴女爽到腰部弓起痉挛不止 SSIS-209-en', '巨屌狠狠插入子宫深处让痴女爽到腰部弓起痉挛不止 SSIS-209-kh', '01:00:00', 0, 5, 1, 1, '/jYy2Kw7t/index.m3u8', '/jYy2Kw7t/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '小仓七海', '2023-03-08 13:18:59', '2023-03-24 14:48:07', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (63, 1, '1,kk-porn01', 'kk黄站1号', 68, '小仓七海', NULL, NULL, '2023-03-07 16:20:24', 100062, '七海性欲觉醒被巨根抽插到浑身抽搐 SSIS-267', '七海性欲觉醒被巨根抽插到浑身抽搐 SSIS-267-en', '七海性欲觉醒被巨根抽插到浑身抽搐 SSIS-267-kh', '01:00:00', 0, 2, 0, 1, '/jqc5zLrS/index.m3u8', '/jqc5zLrS/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '小仓七海', '2023-03-08 13:18:59', '2023-03-27 19:38:08', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (64, 1, '1,kk-porn01', 'kk黄站1号', 63, '白濑飞鸟', NULL, NULL, '2023-03-07 16:20:24', 100063, '小情侣在家换上泳装激情性交 OKK-036', '小情侣在家换上泳装激情性交 OKK-036-en', '小情侣在家换上泳装激情性交 OKK-036-kh', '01:00:00', 0, 1, 0, 1, '/LLqIFMpM/index.m3u8', '/LLqIFMpM/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '白濑飞鸟', '2023-03-08 13:18:59', '2023-03-28 14:01:00', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (65, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100064, '一进房间就看两个穿泳装的美女', '一进房间就看两个穿泳装的美女-en', '一进房间就看两个穿泳装的美女-kh', '01:00:00', 0, 0, 0, 1, '/4FsSsnAH/index.m3u8', '/4FsSsnAH/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (66, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100065, '私密展示自己的黑色丛林', '私密展示自己的黑色丛林-en', '私密展示自己的黑色丛林-kh', '01:00:00', 0, 0, 0, 1, '/4NDKSrsI/index.m3u8', '/4NDKSrsI/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (67, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100066, '火辣辣的小学妹勾引学长', '火辣辣的小学妹勾引学长-en', '火辣辣的小学妹勾引学长-kh', '01:00:00', 0, 0, 0, 1, '/GNy40lDR/index.m3u8', '/GNy40lDR/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (68, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100067, '透明情趣制服小骚货', '透明情趣制服小骚货-en', '透明情趣制服小骚货-kh', '01:00:00', 0, 0, 0, 1, '/jsbb660D/index.m3u8', '/jsbb660D/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (69, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100068, '看脸也就养养眼，看逼才是王道', '看脸也就养养眼，看逼才是王道-en', '看脸也就养养眼，看逼才是王道-kh', '01:00:00', 0, 2, 0, 1, '/J3Rag74n/index.m3u8', '/J3Rag74n/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-23 15:18:07', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (70, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100069, '就喜欢这样低俗的妹子', '就喜欢这样低俗的妹子-en', '就喜欢这样低俗的妹子-kh', '01:00:00', 0, 0, 0, 1, '/JceWlVam/index.m3u8', '/JceWlVam/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (71, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100070, '练习老汉推车的韩国骚主播', '练习老汉推车的韩国骚主播-en', '练习老汉推车的韩国骚主播-kh', '01:00:00', 0, 6, 0, 1, '/j2yeQHF8/index.m3u8', '/j2yeQHF8/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-28 14:00:55', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (72, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100071, '可爱真的比不上黑丝诱惑', '可爱真的比不上黑丝诱惑-en', '可爱真的比不上黑丝诱惑-kh', '01:00:00', 0, 132, 1, 1, '/vFmvv1d2/index.m3u8', '/vFmvv1d2/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-27 22:03:32', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (73, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100072, '在房间练习扭臀骚舞的韩国小婊子', '在房间练习扭臀骚舞的韩国小婊子-en', '在房间练习扭臀骚舞的韩国小婊子-kh', '01:00:00', 0, 0, 0, 1, '/2QCIWixa/index.m3u8', '/2QCIWixa/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (74, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100073, '爱在哪里？在女神逼逼里', '爱在哪里？在女神逼逼里-en', '爱在哪里？在女神逼逼里-kh', '01:00:00', 0, 0, 0, 1, '/mudj1Ruo/index.m3u8', '/mudj1Ruo/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (75, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100074, '妹子屁股真够大的完美炮架', '妹子屁股真够大的完美炮架-en', '妹子屁股真够大的完美炮架-kh', '01:00:00', 0, 0, 0, 1, '/Q7JUZVic/index.m3u8', '/Q7JUZVic/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (76, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100075, '有这么个老婆两个腰子都不够用', '有这么个老婆两个腰子都不够用-en', '有这么个老婆两个腰子都不够用-kh', '01:00:00', 0, 1, 0, 1, '/a4gAe9G1/index.m3u8', '/a4gAe9G1/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-27 19:46:24', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (77, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100076, '肉感美腿女神黑丝诱惑', '肉感美腿女神黑丝诱惑-en', '肉感美腿女神黑丝诱惑-kh', '01:00:00', 0, 1, 0, 1, '/WS31a4JK/index.m3u8', '/WS31a4JK/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-23 22:06:33', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (78, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100077, '主播的丰满的美臀还不点开看', '主播的丰满的美臀还不点开看-en', '主播的丰满的美臀还不点开看-kh', '01:00:00', 0, 0, 0, 1, '/TiSMPqNl/index.m3u8', '/TiSMPqNl/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (79, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100078, '柔软白嫩的大咪咪想摸摸吗', '柔软白嫩的大咪咪想摸摸吗-en', '柔软白嫩的大咪咪想摸摸吗-kh', '01:00:00', 0, 0, 0, 1, '/gSzhViTz/index.m3u8', '/gSzhViTz/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (80, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100079, '小哥肉棒安慰女闺蜜', '小哥肉棒安慰女闺蜜-en', '小哥肉棒安慰女闺蜜-kh', '01:00:00', 0, 0, 0, 1, '/rcQ171Il/index.m3u8', '/rcQ171Il/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (81, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100080, '微胖萌妹子肥肥馒头逼好粉嫩', '微胖萌妹子肥肥馒头逼好粉嫩-en', '微胖萌妹子肥肥馒头逼好粉嫩-kh', '01:00:00', 0, 0, 0, 1, '/PgXs8bWX/index.m3u8', '/PgXs8bWX/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (82, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100081, '嫂子穿上性感睡衣给我倒茶时咪咪若隐若现', '嫂子穿上性感睡衣给我倒茶时咪咪若隐若现-en', '嫂子穿上性感睡衣给我倒茶时咪咪若隐若现-kh', '01:00:00', 0, 0, 0, 1, '/SvBlOmGj/index.m3u8', '/SvBlOmGj/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (83, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100082, '直播间用按摩棒将自己搞到潮吹', '直播间用按摩棒将自己搞到潮吹-en', '直播间用按摩棒将自己搞到潮吹-kh', '01:00:00', 0, 0, 0, 1, '/71ELH8Yl/index.m3u8', '/71ELH8Yl/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (84, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100083, '浑身赤裸的萌妹子发骚', '浑身赤裸的萌妹子发骚-en', '浑身赤裸的萌妹子发骚-kh', '01:00:00', 0, 0, 0, 1, '/DqEylERS/index.m3u8', '/DqEylERS/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (85, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100084, '韩国妹妹的自摸诱惑', '韩国妹妹的自摸诱惑-en', '韩国妹妹的自摸诱惑-kh', '01:00:00', 0, 0, 0, 1, '/yGW6kxnh/index.m3u8', '/yGW6kxnh/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (86, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100085, '大奶子的骚主播使劲抠逼', '大奶子的骚主播使劲抠逼-en', '大奶子的骚主播使劲抠逼-kh', '01:00:00', 0, 0, 0, 1, '/9SOQW0hf/index.m3u8', '/9SOQW0hf/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (87, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100086, '圆润的奶子的主播露出奶子摸逼', '圆润的奶子的主播露出奶子摸逼-en', '圆润的奶子的主播露出奶子摸逼-kh', '01:00:00', 0, 0, 0, 1, '/R0nTM0TV/index.m3u8', '/R0nTM0TV/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (88, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100087, '性感车模寂寞居家自慰', '性感车模寂寞居家自慰-en', '性感车模寂寞居家自慰-kh', '01:00:00', 0, 0, 0, 1, '/cm3vstmK/index.m3u8', '/cm3vstmK/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (89, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100088, '妹子打赌输了鼓起勇气当众自慰', '妹子打赌输了鼓起勇气当众自慰-en', '妹子打赌输了鼓起勇气当众自慰-kh', '01:00:00', 0, 0, 0, 1, '/EWnOVTkA/index.m3u8', '/EWnOVTkA/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (90, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100089, '白色的液体使劲揉搓大奶子', '白色的液体使劲揉搓大奶子-en', '白色的液体使劲揉搓大奶子-kh', '01:00:00', 0, 0, 0, 1, '/fEHE2an9/index.m3u8', '/fEHE2an9/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (91, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100090, '手指不够长就用道具抽插骚穴', '手指不够长就用道具抽插骚穴-en', '手指不够长就用道具抽插骚穴-kh', '01:00:00', 0, 0, 0, 1, '/ZjDskx3o/index.m3u8', '/ZjDskx3o/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (92, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100091, '韩国婊子自慰到高潮', '韩国婊子自慰到高潮-en', '韩国婊子自慰到高潮-kh', '01:00:00', 0, 0, 0, 1, '/JRDl0NS4/index.m3u8', '/JRDl0NS4/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (93, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100092, '女主播每晚屋子开激情视频', '女主播每晚屋子开激情视频-en', '女主播每晚屋子开激情视频-kh', '01:00:00', 0, 0, 0, 1, '/7i1cdCs6/index.m3u8', '/7i1cdCs6/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (94, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100093, '不穿衣服的主播撅着腚发出浪叫', '不穿衣服的主播撅着腚发出浪叫-en', '不穿衣服的主播撅着腚发出浪叫-kh', '01:00:00', 0, 0, 0, 1, '/cXpBJlPP/index.m3u8', '/cXpBJlPP/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (95, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100094, '韩国不良少女展示热辣身材', '韩国不良少女展示热辣身材-en', '韩国不良少女展示热辣身材-kh', '01:00:00', 0, 0, 0, 1, '/eSfJ7sPK/index.m3u8', '/eSfJ7sPK/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (96, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100095, '气质熟女诱人脱衣舞', '气质熟女诱人脱衣舞-en', '气质熟女诱人脱衣舞-kh', '01:00:00', 0, 0, 0, 1, '/v6ADnNR4/index.m3u8', '/v6ADnNR4/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (97, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100096, '一对奶子重十斤的可爱妹子', '一对奶子重十斤的可爱妹子-en', '一对奶子重十斤的可爱妹子-kh', '01:00:00', 0, 0, 0, 1, '/eW9Q87G9/index.m3u8', '/eW9Q87G9/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (98, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100097, '今天到女神家里给她做深喉运动', '今天到女神家里给她做深喉运动-en', '今天到女神家里给她做深喉运动-kh', '01:00:00', 0, 0, 0, 1, '/VS6j8GX2/index.m3u8', '/VS6j8GX2/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (99, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100098, '淫荡女孩想当男人专用肉便器', '淫荡女孩想当男人专用肉便器-en', '淫荡女孩想当男人专用肉便器-kh', '01:00:00', 0, 0, 0, 1, '/INcdn1lo/index.m3u8', '/INcdn1lo/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (100, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100099, '上不了台面的美女台下玩的够淫荡', '上不了台面的美女台下玩的够淫荡-en', '上不了台面的美女台下玩的够淫荡-kh', '01:00:00', 0, 0, 0, 1, '/8HMwugQD/index.m3u8', '/8HMwugQD/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (101, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100100, '打开视频看到女主播只穿内裤在床上', '打开视频看到女主播只穿内裤在床上-en', '打开视频看到女主播只穿内裤在床上-kh', '01:00:00', 0, 0, 0, 1, '/M5qzrMZQ/index.m3u8', '/M5qzrMZQ/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (102, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100101, '韩国女主播人前文静床上骚气', '韩国女主播人前文静床上骚气-en', '韩国女主播人前文静床上骚气-kh', '01:00:00', 0, 6, 1, 1, '/ENPLnba0/index.m3u8', '/ENPLnba0/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-27 20:12:53', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (103, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100102, '出来偷腥没想到约了个大美女', '出来偷腥没想到约了个大美女-en', '出来偷腥没想到约了个大美女-kh', '01:00:00', 0, 0, 0, 1, '/iiHXlJPS/index.m3u8', '/iiHXlJPS/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (104, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100103, '大奶少女跳起舞来奶子都快甩到脸上了', '大奶少女跳起舞来奶子都快甩到脸上了-en', '大奶少女跳起舞来奶子都快甩到脸上了-kh', '01:00:00', 0, 0, 0, 1, '/q1rroMXq/index.m3u8', '/q1rroMXq/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (105, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100104, '翘臀就在我的眼睛前面来回抖动', '翘臀就在我的眼睛前面来回抖动-en', '翘臀就在我的眼睛前面来回抖动-kh', '01:00:00', 0, 0, 0, 1, '/mIdBJctg/index.m3u8', '/mIdBJctg/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (106, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100105, '鲜嫩多汁的风骚表姐', '鲜嫩多汁的风骚表姐-en', '鲜嫩多汁的风骚表姐-kh', '01:00:00', 0, 3, 0, 1, '/EzupkJBq/index.m3u8', '/EzupkJBq/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-23 16:11:43', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (107, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100106, '我的淫荡女同事', '我的淫荡女同事-en', '我的淫荡女同事-kh', '01:00:00', 0, 0, 0, 1, '/1FPWnGzy/index.m3u8', '/1FPWnGzy/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (108, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100107, '约了个妹妹见面之后一发不可收拾', '约了个妹妹见面之后一发不可收拾-en', '约了个妹妹见面之后一发不可收拾-kh', '01:00:00', 0, 5, 1, 1, '/OwE8IOFi/index.m3u8', '/OwE8IOFi/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-27 19:47:21', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (109, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100108, '小骚货隔着内裤水都要流出来了', '小骚货隔着内裤水都要流出来了-en', '小骚货隔着内裤水都要流出来了-kh', '01:00:00', 0, 8, 1, 1, '/RTqxdfy4/index.m3u8', '/RTqxdfy4/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-27 13:58:20', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (110, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100109, '上门服务的臭婊子', '上门服务的臭婊子-en', '上门服务的臭婊子-kh', '01:00:00', 0, 2, 0, 1, '/QDsfcEi3/index.m3u8', '/QDsfcEi3/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-28 13:52:49', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (111, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100110, '妹妹两个大奶子双手都抓不住', '妹妹两个大奶子双手都抓不住-en', '妹妹两个大奶子双手都抓不住-kh', '01:00:00', 0, 87, 1, 1, '/5clf0Rk5/index.m3u8', '/5clf0Rk5/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-28 13:45:45', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (112, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100111, '蕾丝胸罩里面是波涛汹涌的大奶子', '蕾丝胸罩里面是波涛汹涌的大奶子-en', '蕾丝胸罩里面是波涛汹涌的大奶子-kh', '01:00:00', 0, 0, 0, 1, '/xoW6slTF/index.m3u8', '/xoW6slTF/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (113, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100112, '阿姨撅起大屁股等待我的进入', '阿姨撅起大屁股等待我的进入-en', '阿姨撅起大屁股等待我的进入-kh', '01:00:00', 0, 3, 0, 1, '/i3Y78J1S/index.m3u8', '/i3Y78J1S/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-23 16:11:44', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (114, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100113, '女大学生喜欢买自己的原味丝袜', '女大学生喜欢买自己的原味丝袜-en', '女大学生喜欢买自己的原味丝袜-kh', '01:00:00', 0, 0, 0, 1, '/ajcIg3pO/index.m3u8', '/ajcIg3pO/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (115, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100114, '被干到失禁的小淫娃', '被干到失禁的小淫娃-en', '被干到失禁的小淫娃-kh', '01:00:00', 0, 0, 0, 1, '/oX0vyOMA/index.m3u8', '/oX0vyOMA/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (116, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100115, '淫荡少妇喜欢年轻的肉体', '淫荡少妇喜欢年轻的肉体-en', '淫荡少妇喜欢年轻的肉体-kh', '01:00:00', 0, 7, 1, 1, '/9RtOJ6pa/index.m3u8', '/9RtOJ6pa/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-27 13:58:23', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (117, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100116, '萝莉装扮御姐的气场', '萝莉装扮御姐的气场-en', '萝莉装扮御姐的气场-kh', '01:00:00', 0, 0, 0, 1, '/NkYXwCLB/index.m3u8', '/NkYXwCLB/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (118, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100117, '张开双腿等待肉棒进入', '张开双腿等待肉棒进入-en', '张开双腿等待肉棒进入-kh', '01:00:00', 0, 0, 0, 1, '/HkXpNZE4/index.m3u8', '/HkXpNZE4/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (119, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100118, '韩国主播喜欢不穿衣服出来直播', '韩国主播喜欢不穿衣服出来直播-en', '韩国主播喜欢不穿衣服出来直播-kh', '01:00:00', 0, 0, 0, 1, '/ZeXajU1W/index.m3u8', '/ZeXajU1W/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (120, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100119, '异地恋的小情侣刚见面就开操', '异地恋的小情侣刚见面就开操-en', '异地恋的小情侣刚见面就开操-kh', '01:00:00', 0, 0, 0, 1, '/lGpMkT4B/index.m3u8', '/lGpMkT4B/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (121, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100120, '隔壁的姐姐一直在勾引我', '隔壁的姐姐一直在勾引我-en', '隔壁的姐姐一直在勾引我-kh', '01:00:00', 0, 0, 0, 1, '/uVzmfPm7/index.m3u8', '/uVzmfPm7/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (122, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100121, '跪着被疯狂后入的小们嫩模', '跪着被疯狂后入的小们嫩模-en', '跪着被疯狂后入的小们嫩模-kh', '01:00:00', 0, 1, 0, 1, '/l1mEqoI3/index.m3u8', '/l1mEqoI3/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-27 19:39:37', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (123, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100122, '女舍友的胸部发育的真不错', '女舍友的胸部发育的真不错-en', '女舍友的胸部发育的真不错-kh', '01:00:00', 0, 0, 0, 1, '/Yc3ED8Xn/index.m3u8', '/Yc3ED8Xn/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (124, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100123, '调教我的萌妹女友', '调教我的萌妹女友-en', '调教我的萌妹女友-kh', '01:00:00', 0, 0, 0, 1, '/T5Cn7EKs/index.m3u8', '/T5Cn7EKs/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (125, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100124, '马尾少女丝袜诱惑', '马尾少女丝袜诱惑-en', '马尾少女丝袜诱惑-kh', '01:00:00', 0, 0, 0, 1, '/p8bQyU9m/index.m3u8', '/p8bQyU9m/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (126, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100125, '法式舌吻把妹子给征服了', '法式舌吻把妹子给征服了-en', '法式舌吻把妹子给征服了-kh', '01:00:00', 0, 0, 0, 1, '/BLTVL6EH/index.m3u8', '/BLTVL6EH/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (127, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100126, '美少女的真空诱惑', '美少女的真空诱惑-en', '美少女的真空诱惑-kh', '01:00:00', 0, 0, 0, 1, '/PTaEYQdM/index.m3u8', '/PTaEYQdM/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (128, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100127, '哥哥过来陪陪我', '哥哥过来陪陪我-en', '哥哥过来陪陪我-kh', '01:00:00', 0, 0, 0, 1, '/i8efQmH2/index.m3u8', '/i8efQmH2/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (129, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100128, '猛男把妹子干的淫水直流', '猛男把妹子干的淫水直流-en', '猛男把妹子干的淫水直流-kh', '01:00:00', 0, 0, 0, 1, '/TNtap7Li/index.m3u8', '/TNtap7Li/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (130, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100129, '奶子好翘的韩国女主播', '奶子好翘的韩国女主播-en', '奶子好翘的韩国女主播-kh', '01:00:00', 0, 0, 0, 1, '/OieZo5ZL/index.m3u8', '/OieZo5ZL/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (131, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100130, '小骚货希望找到一个大鸡巴哥哥', '小骚货希望找到一个大鸡巴哥哥-en', '小骚货希望找到一个大鸡巴哥哥-kh', '01:00:00', 0, 0, 0, 1, '/MvlEr699/index.m3u8', '/MvlEr699/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (132, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100131, '透明的外套衬托出完美的身材', '透明的外套衬托出完美的身材-en', '透明的外套衬托出完美的身材-kh', '01:00:00', 0, 0, 0, 1, '/ENM3o3sR/index.m3u8', '/ENM3o3sR/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (133, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100132, '妹妹的骚逼只能被哥哥填满', '妹妹的骚逼只能被哥哥填满-en', '妹妹的骚逼只能被哥哥填满-kh', '01:00:00', 0, 0, 0, 1, '/AQRbqNzj/index.m3u8', '/AQRbqNzj/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (134, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100133, '猛操刚在一起的女朋友', '猛操刚在一起的女朋友-en', '猛操刚在一起的女朋友-kh', '01:00:00', 0, 0, 0, 1, '/ynWZI6du/index.m3u8', '/ynWZI6du/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (135, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100134, '被掐住脖子疯狂抽插', '被掐住脖子疯狂抽插-en', '被掐住脖子疯狂抽插-kh', '01:00:00', 0, 0, 0, 1, '/8wAiZlpR/index.m3u8', '/8wAiZlpR/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (136, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100135, '包臀裙尽显风骚韵味', '包臀裙尽显风骚韵味-en', '包臀裙尽显风骚韵味-kh', '01:00:00', 0, 0, 0, 1, '/x9wZg3uq/index.m3u8', '/x9wZg3uq/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (137, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100136, '喝醉的漂亮女孩喜欢追着我亲亲', '喝醉的漂亮女孩喜欢追着我亲亲-en', '喝醉的漂亮女孩喜欢追着我亲亲-kh', '01:00:00', 0, 0, 0, 1, '/QC261uuG/index.m3u8', '/QC261uuG/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (138, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100137, '表姐在家偷偷自慰被我发现了', '表姐在家偷偷自慰被我发现了-en', '表姐在家偷偷自慰被我发现了-kh', '01:00:00', 0, 0, 0, 1, '/BDD7aV3U/index.m3u8', '/BDD7aV3U/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (139, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100138, '黑丝女秘书被老板慢慢调教', '黑丝女秘书被老板慢慢调教-en', '黑丝女秘书被老板慢慢调教-kh', '01:00:00', 0, 0, 0, 1, '/c2m8Lq7r/index.m3u8', '/c2m8Lq7r/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (140, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100139, '纯欲学姐带给我无尽的性交', '纯欲学姐带给我无尽的性交-en', '纯欲学姐带给我无尽的性交-kh', '01:00:00', 0, 0, 0, 1, '/dx7Apm1Q/index.m3u8', '/dx7Apm1Q/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (141, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100140, '被惩罚的女仆小妹妹', '被惩罚的女仆小妹妹-en', '被惩罚的女仆小妹妹-kh', '01:00:00', 0, 0, 0, 1, '/q7JdOsjO/index.m3u8', '/q7JdOsjO/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (142, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100141, '文静的少女脱了衣服诱惑我', '文静的少女脱了衣服诱惑我-en', '文静的少女脱了衣服诱惑我-kh', '01:00:00', 0, 0, 0, 1, '/DTTh7P9h/index.m3u8', '/DTTh7P9h/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (143, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100142, '发情的女老师看上了自己的学生', '发情的女老师看上了自己的学生-en', '发情的女老师看上了自己的学生-kh', '01:00:00', 0, 0, 0, 1, '/sb7Xeeox/index.m3u8', '/sb7Xeeox/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (144, 1, '1,kk-porn01', 'kk黄站1号', 70, '郑艾莉', NULL, NULL, '2023-03-07 16:20:24', 100143, '淫荡小母狗喜欢含着我的肉棒', '淫荡小母狗喜欢含着我的肉棒-en', '淫荡小母狗喜欢含着我的肉棒-kh', '01:00:00', 0, 0, 0, 1, '/5SLCtUvi/index.m3u8', '/5SLCtUvi/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '郑艾莉', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (145, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100144, '风情万种32岁少妇提前开好情趣酒店等待操逼', '风情万种32岁少妇提前开好情趣酒店等待操逼-en', '风情万种32岁少妇提前开好情趣酒店等待操逼-kh', '01:00:00', 0, 0, 0, 1, '/UQ3mWl7h/index.m3u8', '/UQ3mWl7h/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '偷情王哥', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (146, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100145, '魔都极品身材美少女被男朋友操到无力还击，扒开内裤无套直插无毛浪穴', '魔都极品身材美少女被男朋友操到无力还击，扒开内裤无套直插无毛浪穴-en', '魔都极品身材美少女被男朋友操到无力还击，扒开内裤无套直插无毛浪穴-kh', '01:00:00', 0, 0, 0, 1, '/AxMp1QuC/index.m3u8', '/AxMp1QuC/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '一手自拍', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (147, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100146, '1500网约漂亮小少妇，网红脸极品美乳', '1500网约漂亮小少妇，网红脸极品美乳-en', '1500网约漂亮小少妇，网红脸极品美乳-kh', '01:00:00', 0, 0, 0, 1, '/Z1WS5oPI/index.m3u8', '/Z1WS5oPI/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '偷情王哥', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (148, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100147, '厕拍大神潜入某大学沟厕偷拍学妹尿尿，被两个妹子发现回头看', '厕拍大神潜入某大学沟厕偷拍学妹尿尿，被两个妹子发现回头看-en', '厕拍大神潜入某大学沟厕偷拍学妹尿尿，被两个妹子发现回头看-kh', '01:00:00', 0, 0, 0, 1, '/qLVybPzY/index.m3u8', '/qLVybPzY/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '一手自拍', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (149, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100148, '国内小情侣美乳丰臀性爱，甄选各式姿势大屌操浪穴真爽（一）', '国内小情侣美乳丰臀性爱，甄选各式姿势大屌操浪穴真爽（一）-en', '国内小情侣美乳丰臀性爱，甄选各式姿势大屌操浪穴真爽（一）-kh', '01:00:00', 0, 0, 0, 1, '/hkr5V3qy/index.m3u8', '/hkr5V3qy/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '一手自拍', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (150, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100149, '女子保健新人良家少妇，真实偷拍看极品美穴', '女子保健新人良家少妇，真实偷拍看极品美穴-en', '女子保健新人良家少妇，真实偷拍看极品美穴-kh', '01:00:00', 0, 0, 0, 1, '/IABw4GP4/index.m3u8', '/IABw4GP4/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '一手自拍', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (151, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100150, '网红脸外围小少妇，白嫩圆润美臀满分', '网红脸外围小少妇，白嫩圆润美臀满分-en', '网红脸外围小少妇，白嫩圆润美臀满分-kh', '01:00:00', 0, 0, 0, 1, '/UV1KFDHj/index.m3u8', '/UV1KFDHj/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '偷情王哥', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (152, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100151, '极品身材网红美女樱桃妹妹，要和爸爸玩玩具直到水声不断再从后面进入', '极品身材网红美女樱桃妹妹，要和爸爸玩玩具直到水声不断再从后面进入-en', '极品身材网红美女樱桃妹妹，要和爸爸玩玩具直到水声不断再从后面进入-kh', '01:00:00', 0, 0, 0, 1, '/bmMBgZk3/index.m3u8', '/bmMBgZk3/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '一手自拍', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (153, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100152, '厕拍大神潜入某单位女厕全景后拍白虎少妇逼逼', '厕拍大神潜入某单位女厕全景后拍白虎少妇逼逼-en', '厕拍大神潜入某单位女厕全景后拍白虎少妇逼逼-kh', '01:00:00', 0, 0, 0, 1, '/OETxn4Sv/index.m3u8', '/OETxn4Sv/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '一手自拍', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (154, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100153, '国内小情侣美乳丰臀性爱，甄选各式姿势大屌操浪穴真爽（五）', '国内小情侣美乳丰臀性爱，甄选各式姿势大屌操浪穴真爽（五）-en', '国内小情侣美乳丰臀性爱，甄选各式姿势大屌操浪穴真爽（五）-kh', '01:00:00', 0, 0, 0, 1, '/ggYTmb7Y/index.m3u8', '/ggYTmb7Y/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '一手自拍', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (155, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100154, '非常白嫩漂亮足球宝贝用身体慰藉球员', '非常白嫩漂亮足球宝贝用身体慰藉球员-en', '非常白嫩漂亮足球宝贝用身体慰藉球员-kh', '01:00:00', 0, 0, 0, 1, '/G5gLTfpq/index.m3u8', '/G5gLTfpq/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '一手自拍', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (156, 1, '1,kk-porn01', 'kk黄站1号', 52, '热辣主播', NULL, NULL, '2023-03-07 16:20:23', 100155, '寂寞的23岁女神，跳蛋深入毛茸茸蜜穴', '寂寞的23岁女神，跳蛋深入毛茸茸蜜穴-en', '寂寞的23岁女神，跳蛋深入毛茸茸蜜穴-kh', '01:00:00', 0, 0, 0, 1, '/ZTi426Hg/index.m3u8', '/ZTi426Hg/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '热辣主播', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (157, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100156, '网袜捆绑诱惑主人，骚穴能承受多少次呢？', '网袜捆绑诱惑主人，骚穴能承受多少次呢？-en', '网袜捆绑诱惑主人，骚穴能承受多少次呢？-kh', '01:00:00', 0, 0, 0, 1, '/q5ZijSnP/index.m3u8', '/q5ZijSnP/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '一手自拍', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (158, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100157, '极品嫩模携闺蜜玩双飞，双凤争屌', '极品嫩模携闺蜜玩双飞，双凤争屌-en', '极品嫩模携闺蜜玩双飞，双凤争屌-kh', '01:00:00', 0, 0, 0, 1, '/cjZzxmiW/index.m3u8', '/cjZzxmiW/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '一手自拍', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (159, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100158, '美女被几个饥渴男各种操，最后被男友操得瘫软在床', '美女被几个饥渴男各种操，最后被男友操得瘫软在床-en', '美女被几个饥渴男各种操，最后被男友操得瘫软在床-kh', '01:00:00', 0, 1, 0, 1, '/QZVmzo2o/index.m3u8', '/QZVmzo2o/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '一手自拍', '2023-03-08 13:18:59', '2023-03-23 22:01:29', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (160, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100159, '国内小情侣美乳丰臀性爱，甄选各式姿势大屌操浪穴真爽（四）', '国内小情侣美乳丰臀性爱，甄选各式姿势大屌操浪穴真爽（四）-en', '国内小情侣美乳丰臀性爱，甄选各式姿势大屌操浪穴真爽（四）-kh', '01:00:00', 0, 0, 0, 1, '/5jmLIR5S/index.m3u8', '/5jmLIR5S/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '一手自拍', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (161, 1, '1,kk-porn01', 'kk黄站1号', 52, '热辣主播', NULL, NULL, '2023-03-07 16:20:23', 100160, '22岁人气小姐姐回归，身姿曼妙玲珑有致', '22岁人气小姐姐回归，身姿曼妙玲珑有致-en', '22岁人气小姐姐回归，身姿曼妙玲珑有致-kh', '01:00:00', 0, 0, 0, 1, '/x7sDpBKD/index.m3u8', '/x7sDpBKD/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '热辣主播', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (162, 1, '1,kk-porn01', 'kk黄站1号', 52, '热辣主播', NULL, NULL, '2023-03-07 16:20:23', 100161, '颜值区绿播下海，满分女神颜值抗打，无惧怼脸拍', '颜值区绿播下海，满分女神颜值抗打，无惧怼脸拍-en', '颜值区绿播下海，满分女神颜值抗打，无惧怼脸拍-kh', '01:00:00', 0, 0, 0, 1, '/zBMQDmVj/index.m3u8', '/zBMQDmVj/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '热辣主播', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (163, 1, '1,kk-porn01', 'kk黄站1号', 52, '热辣主播', NULL, NULL, '2023-03-07 16:20:23', 100162, '骚气大奶妹和炮友双人啪啪大秀，拨开内裤后入抽插性感大屁股', '骚气大奶妹和炮友双人啪啪大秀，拨开内裤后入抽插性感大屁股-en', '骚气大奶妹和炮友双人啪啪大秀，拨开内裤后入抽插性感大屁股-kh', '01:00:00', 0, 0, 0, 1, '/eBihXiZJ/index.m3u8', '/eBihXiZJ/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '热辣主播', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (164, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100163, '00后骚淫贱浪淫妹在公园被两个大屌操了，前怼后操口爆', '00后骚淫贱浪淫妹在公园被两个大屌操了，前怼后操口爆-en', '00后骚淫贱浪淫妹在公园被两个大屌操了，前怼后操口爆-kh', '01:00:00', 0, 0, 0, 1, '/CvdXMUZf/index.m3u8', '/CvdXMUZf/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '一手自拍', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (165, 1, '1,kk-porn01', 'kk黄站1号', 52, '热辣主播', NULL, NULL, '2023-03-07 16:20:23', 100164, '21岁小奶妈哺乳期还能挤出奶水来，高颜值小骚逼特写水多', '21岁小奶妈哺乳期还能挤出奶水来，高颜值小骚逼特写水多-en', '21岁小奶妈哺乳期还能挤出奶水来，高颜值小骚逼特写水多-kh', '01:00:00', 0, 0, 0, 1, '/CfzgXyOL/index.m3u8', '/CfzgXyOL/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '热辣主播', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (166, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100165, '专业男技师SPA推油按摩，又赚钱又干逼爽翻了', '专业男技师SPA推油按摩，又赚钱又干逼爽翻了-en', '专业男技师SPA推油按摩，又赚钱又干逼爽翻了-kh', '01:00:00', 0, 0, 0, 1, '/98w4omYR/index.m3u8', '/98w4omYR/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '一手自拍', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (167, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100166, '20岁的清纯小妹，正是含苞待放之时，杏眼含情', '20岁的清纯小妹，正是含苞待放之时，杏眼含情-en', '20岁的清纯小妹，正是含苞待放之时，杏眼含情-kh', '01:00:00', 0, 0, 0, 1, '/MG4aTq5M/index.m3u8', '/MG4aTq5M/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '一手自拍', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (168, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100167, '国内小情侣美乳丰臀性爱，甄选各式姿势大屌操浪穴真爽（三）', '国内小情侣美乳丰臀性爱，甄选各式姿势大屌操浪穴真爽（三）-en', '国内小情侣美乳丰臀性爱，甄选各式姿势大屌操浪穴真爽（三）-kh', '01:00:00', 0, 0, 0, 1, '/QZkAnEnP/index.m3u8', '/QZkAnEnP/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '一手自拍', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (169, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100168, '退役空姐出来接单养活男朋友', '退役空姐出来接单养活男朋友-en', '退役空姐出来接单养活男朋友-kh', '01:00:00', 0, 0, 0, 1, '/XlVTnTWP/index.m3u8', '/XlVTnTWP/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '偷情王哥', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (170, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100169, '巨乳小妹凌晨1点继续搞', '巨乳小妹凌晨1点继续搞-en', '巨乳小妹凌晨1点继续搞-kh', '01:00:00', 0, 0, 0, 1, '/IOopEnrl/index.m3u8', '/IOopEnrl/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '偷情王哥', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (171, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100170, '国内小情侣美乳丰臀性爱，甄选各式姿势大屌操浪穴真爽（二）', '国内小情侣美乳丰臀性爱，甄选各式姿势大屌操浪穴真爽（二）-en', '国内小情侣美乳丰臀性爱，甄选各式姿势大屌操浪穴真爽（二）-kh', '01:00:00', 0, 0, 0, 1, '/pVXylGXe/index.m3u8', '/pVXylGXe/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '一手自拍', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (172, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100171, '超棒身材豪乳女孩在汽车旅馆被骗啪啪，无毛浪穴被大屌肆意蹂躏抽插', '超棒身材豪乳女孩在汽车旅馆被骗啪啪，无毛浪穴被大屌肆意蹂躏抽插-en', '超棒身材豪乳女孩在汽车旅馆被骗啪啪，无毛浪穴被大屌肆意蹂躏抽插-kh', '01:00:00', 0, 1, 0, 1, '/UaIRPHUZ/index.m3u8', '/UaIRPHUZ/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '一手自拍', '2023-03-08 13:18:59', '2023-03-23 21:36:21', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (173, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100172, '海景酒店内射丝袜高跟鞋豪乳女神', '海景酒店内射丝袜高跟鞋豪乳女神-en', '海景酒店内射丝袜高跟鞋豪乳女神-kh', '01:00:00', 0, 0, 0, 1, '/rKhSCAXS/index.m3u8', '/rKhSCAXS/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '一手自拍', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (174, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100173, '豪华酒店SPA勾搭技师做爱啪啪爆操', '豪华酒店SPA勾搭技师做爱啪啪爆操-en', '豪华酒店SPA勾搭技师做爱啪啪爆操-kh', '01:00:00', 0, 0, 0, 1, '/9m3ovdc7/index.m3u8', '/9m3ovdc7/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '偷情王哥', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (175, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100174, '极品少女爆裂黑丝制服，假鸡巴插入粉穴高潮到抽搐', '极品少女爆裂黑丝制服，假鸡巴插入粉穴高潮到抽搐-en', '极品少女爆裂黑丝制服，假鸡巴插入粉穴高潮到抽搐-kh', '01:00:00', 0, 0, 0, 1, '/9VGOVZfv/index.m3u8', '/9VGOVZfv/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '一手自拍', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (176, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100175, '国内厕拍大神潜入纸箱厂女厕全景后拍，尿尿美女逼逼还挺嫩', '国内厕拍大神潜入纸箱厂女厕全景后拍，尿尿美女逼逼还挺嫩-en', '国内厕拍大神潜入纸箱厂女厕全景后拍，尿尿美女逼逼还挺嫩-kh', '01:00:00', 0, 0, 0, 1, '/B2fL2zAq/index.m3u8', '/B2fL2zAq/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '一手自拍', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (177, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100176, '探索者系列新作，甜美外围2600一炮', '探索者系列新作，甜美外围2600一炮-en', '探索者系列新作，甜美外围2600一炮-kh', '01:00:00', 0, 1, 0, 1, '/j5Q7se2k/index.m3u8', '/j5Q7se2k/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '偷情王哥', '2023-03-08 13:18:59', '2023-03-27 21:20:40', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (178, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100177, '外围清纯女神躺在胯下，狂插樱桃小口', '外围清纯女神躺在胯下，狂插樱桃小口-en', '外围清纯女神躺在胯下，狂插樱桃小口-kh', '01:00:00', 0, 0, 0, 1, '/pTut5Jnh/index.m3u8', '/pTut5Jnh/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '偷情王哥', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (179, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100178, '少妇空虚寂寞冷来让小伙给高潮，偷拍骚逼流水怒操淫穴', '少妇空虚寂寞冷来让小伙给高潮，偷拍骚逼流水怒操淫穴-en', '少妇空虚寂寞冷来让小伙给高潮，偷拍骚逼流水怒操淫穴-kh', '01:00:00', 0, 0, 0, 1, '/9hwoq3N2/index.m3u8', '/9hwoq3N2/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '一手自拍', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (180, 1, '1,kk-porn01', 'kk黄站1号', 52, '热辣主播', NULL, NULL, '2023-03-07 16:20:23', 100179, '少妇凌晨真实撩路人宾馆开房做爱，没操满足还要自己玩喷水', '少妇凌晨真实撩路人宾馆开房做爱，没操满足还要自己玩喷水-en', '少妇凌晨真实撩路人宾馆开房做爱，没操满足还要自己玩喷水-kh', '01:00:00', 0, 0, 0, 1, '/hFvERz1R/index.m3u8', '/hFvERz1R/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '热辣主播', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (181, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100180, '超棒身材极品网红大尺度性爱啪啪私拍流出，狐尾肛塞多姿势虐操嫩穴小骚货', '超棒身材极品网红大尺度性爱啪啪私拍流出，狐尾肛塞多姿势虐操嫩穴小骚货-en', '超棒身材极品网红大尺度性爱啪啪私拍流出，狐尾肛塞多姿势虐操嫩穴小骚货-kh', '01:00:00', 0, 0, 0, 1, '/Fi069cid/index.m3u8', '/Fi069cid/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '一手自拍', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (182, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100181, '网红美少女柚子猫，雷姆从零开始的采精生活', '网红美少女柚子猫，雷姆从零开始的采精生活-en', '网红美少女柚子猫，雷姆从零开始的采精生活-kh', '01:00:00', 0, 0, 0, 1, '/XuL1TH1x/index.m3u8', '/XuL1TH1x/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '一手自拍', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (183, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100182, '下午买春偶遇漂亮00后小姐姐', '下午买春偶遇漂亮00后小姐姐-en', '下午买春偶遇漂亮00后小姐姐-kh', '01:00:00', 0, 0, 0, 1, '/OyA6L8RS/index.m3u8', '/OyA6L8RS/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '偷情王哥', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (184, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100183, '字母圈反差婊00后良家小妹，淫贱一整夜情趣酒店调教', '字母圈反差婊00后良家小妹，淫贱一整夜情趣酒店调教-en', '字母圈反差婊00后良家小妹，淫贱一整夜情趣酒店调教-kh', '01:00:00', 0, 0, 0, 1, '/vnHH1uzf/index.m3u8', '/vnHH1uzf/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '偷情王哥', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (185, 1, '1,kk-porn01', 'kk黄站1号', 16, 'Morgpie', NULL, NULL, '2023-03-07 16:20:21', 100184, '老板爆肏性感金发秘书', '老板爆肏性感金发秘书-en', '老板爆肏性感金发秘书-kh', '01:00:00', 0, 0, 0, 1, '/jknBGrbw/index.m3u8', '/jknBGrbw/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', 'Morgpie', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (186, 1, '1,kk-porn01', 'kk黄站1号', 16, 'Morgpie', NULL, NULL, '2023-03-07 16:20:21', 100185, '在度假饭店粗暴性交', '在度假饭店粗暴性交-en', '在度假饭店粗暴性交-kh', '01:00:00', 0, 1, 0, 1, '/DIbiFHnk/index.m3u8', '/DIbiFHnk/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', 'Morgpie', '2023-03-08 13:18:59', '2023-03-27 19:27:35', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (187, 1, '1,kk-porn01', 'kk黄站1号', 16, 'Morgpie', NULL, NULL, '2023-03-07 16:20:21', 100186, '在外是纯洁女孩在家是饥渴荡妇', '在外是纯洁女孩在家是饥渴荡妇-en', '在外是纯洁女孩在家是饥渴荡妇-kh', '01:00:00', 0, 0, 0, 1, '/dxewgMSO/index.m3u8', '/dxewgMSO/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', 'Morgpie', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (188, 1, '1,kk-porn01', 'kk黄站1号', 16, 'Morgpie', NULL, NULL, '2023-03-07 16:20:21', 100187, '趁女友玩游戏内射她的紧致骚逼', '趁女友玩游戏内射她的紧致骚逼-en', '趁女友玩游戏内射她的紧致骚逼-kh', '01:00:00', 0, 0, 0, 1, '/lfEFsITi/index.m3u8', '/lfEFsITi/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', 'Morgpie', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (189, 1, '1,kk-porn01', 'kk黄站1号', 16, 'Morgpie', NULL, NULL, '2023-03-07 16:20:21', 100188, '妹妹是任我抽插的玩物', '妹妹是任我抽插的玩物-en', '妹妹是任我抽插的玩物-kh', '01:00:00', 0, 0, 0, 1, '/zjN3sz0b/index.m3u8', '/zjN3sz0b/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', 'Morgpie', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (190, 1, '1,kk-porn01', 'kk黄站1号', 16, 'Morgpie', NULL, NULL, '2023-03-07 16:20:21', 100189, '巨乳妹妹裸睡让我忍不住上了', '巨乳妹妹裸睡让我忍不住上了-en', '巨乳妹妹裸睡让我忍不住上了-kh', '01:00:00', 0, 0, 0, 1, '/D51k87iS/index.m3u8', '/D51k87iS/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', 'Morgpie', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (191, 1, '1,kk-porn01', 'kk黄站1号', 16, 'Morgpie', NULL, NULL, '2023-03-07 16:20:21', 100190, '我们的性爱纪录片', '我们的性爱纪录片-en', '我们的性爱纪录片-kh', '01:00:00', 0, 0, 0, 1, '/Na1b7Lo9/index.m3u8', '/Na1b7Lo9/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', 'Morgpie', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (192, 1, '1,kk-porn01', 'kk黄站1号', 16, 'Morgpie', NULL, NULL, '2023-03-07 16:20:21', 100191, '进房看到性感美臀女孩你会怎么做?', '进房看到性感美臀女孩你会怎么做?-en', '进房看到性感美臀女孩你会怎么做?-kh', '01:00:00', 0, 0, 0, 1, '/KAuEqKZr/index.m3u8', '/KAuEqKZr/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', 'Morgpie', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (193, 1, '1,kk-porn01', 'kk黄站1号', 16, 'Morgpie', NULL, NULL, '2023-03-07 16:20:21', 100192, '女友打游戏不理我只能干她了', '女友打游戏不理我只能干她了-en', '女友打游戏不理我只能干她了-kh', '01:00:00', 0, 0, 0, 1, '/vpTSF1mg/index.m3u8', '/vpTSF1mg/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', 'Morgpie', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (194, 1, '1,kk-porn01', 'kk黄站1号', 16, 'Morgpie', NULL, NULL, '2023-03-07 16:20:21', 100193, '女房客以为我是她的男朋友', '女房客以为我是她的男朋友-en', '女房客以为我是她的男朋友-kh', '01:00:00', 0, 1, 0, 1, '/Ql34Fun2/index.m3u8', '/Ql34Fun2/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', 'Morgpie', '2023-03-08 13:18:59', '2023-03-23 22:10:13', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (195, 1, '1,kk-porn01', 'kk黄站1号', 16, 'Morgpie', NULL, NULL, '2023-03-07 16:20:21', 100194, '女友玩游戏不理我只好用肉棒惩罚', '女友玩游戏不理我只好用肉棒惩罚-en', '女友玩游戏不理我只好用肉棒惩罚-kh', '01:00:00', 0, 0, 0, 1, '/nSJAHHW0/index.m3u8', '/nSJAHHW0/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', 'Morgpie', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (196, 1, '1,kk-porn01', 'kk黄站1号', 16, 'Morgpie', NULL, NULL, '2023-03-07 16:20:21', 100195, '爸爸把女儿的小骚逼灌满精液', '爸爸把女儿的小骚逼灌满精液-en', '爸爸把女儿的小骚逼灌满精液-kh', '01:00:00', 0, 0, 0, 1, '/NiDJ8MJb/index.m3u8', '/NiDJ8MJb/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', 'Morgpie', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (197, 1, '1,kk-porn01', 'kk黄站1号', 16, 'Morgpie', NULL, NULL, '2023-03-07 16:20:21', 100196, '性感女孩被肥大叔抽插高潮', '性感女孩被肥大叔抽插高潮-en', '性感女孩被肥大叔抽插高潮-kh', '01:00:00', 0, 81, 0, 1, '/JHQJdzAD/index.m3u8', '/JHQJdzAD/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', 'Morgpie', '2023-03-08 13:18:59', '2023-03-21 22:09:50', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (198, 1, '1,kk-porn01', 'kk黄站1号', 16, 'Morgpie', NULL, NULL, '2023-03-07 16:20:21', 100197, '浪荡女儿偷偷诱惑男人回家', '浪荡女儿偷偷诱惑男人回家-en', '浪荡女儿偷偷诱惑男人回家-kh', '01:00:00', 0, 0, 0, 1, '/AcECxy5T/index.m3u8', '/AcECxy5T/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', 'Morgpie', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (199, 1, '1,kk-porn01', 'kk黄站1号', 16, 'Morgpie', NULL, NULL, '2023-03-07 16:20:21', 100198, '借住朋友家偷偷跟他女友来一发', '借住朋友家偷偷跟他女友来一发-en', '借住朋友家偷偷跟他女友来一发-kh', '01:00:00', 0, 0, 0, 1, '/gkzheKhQ/index.m3u8', '/gkzheKhQ/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', 'Morgpie', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (200, 1, '1,kk-porn01', 'kk黄站1号', 16, 'Morgpie', NULL, NULL, '2023-03-07 16:20:21', 100199, '趁男友不在家找男人来做爱', '趁男友不在家找男人来做爱-en', '趁男友不在家找男人来做爱-kh', '01:00:00', 0, 0, 0, 1, '/zXgfaD1z/index.m3u8', '/zXgfaD1z/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', 'Morgpie', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (201, 1, '1,kk-porn01', 'kk黄站1号', 16, 'Morgpie', NULL, NULL, '2023-03-07 16:20:21', 100200, '荡妇深喉口交把精液全吞下', '荡妇深喉口交把精液全吞下-en', '荡妇深喉口交把精液全吞下-kh', '01:00:00', 0, 0, 0, 1, '/C4i0gL0k/index.m3u8', '/C4i0gL0k/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', 'Morgpie', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (202, 1, '1,kk-porn01', 'kk黄站1号', 16, 'Morgpie', NULL, NULL, '2023-03-07 16:20:21', 100201, '性感金发尤物舔棒完主动骑上肉棒', '性感金发尤物舔棒完主动骑上肉棒-en', '性感金发尤物舔棒完主动骑上肉棒-kh', '01:00:00', 0, 0, 0, 1, '/JoWvkbuS/index.m3u8', '/JoWvkbuS/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', 'Morgpie', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (203, 1, '1,kk-porn01', 'kk黄站1号', 16, 'Morgpie', NULL, NULL, '2023-03-07 16:20:21', 100202, '跟性感宝贝起洗澡清洁肉棒', '跟性感宝贝起洗澡清洁肉棒-en', '跟性感宝贝起洗澡清洁肉棒-kh', '01:00:00', 0, 0, 0, 1, '/G5IbR0bk/index.m3u8', '/G5IbR0bk/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', 'Morgpie', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (204, 1, '1,kk-porn01', 'kk黄站1号', 16, 'Morgpie', NULL, NULL, '2023-03-07 16:20:21', 100203, '一不小心和我的男闺蜜来了一发', '一不小心和我的男闺蜜来了一发-en', '一不小心和我的男闺蜜来了一发-kh', '01:00:00', 0, 0, 0, 1, '/LPMOax9C/index.m3u8', '/LPMOax9C/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', 'Morgpie', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (205, 1, '1,kk-porn01', 'kk黄站1号', 16, 'Morgpie', NULL, NULL, '2023-03-07 16:20:21', 100204, '性感小姐姐被我完到小穴全湿', '性感小姐姐被我完到小穴全湿-en', '性感小姐姐被我完到小穴全湿-kh', '01:00:00', 0, 0, 0, 1, '/lVPchT32/index.m3u8', '/lVPchT32/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', 'Morgpie', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (206, 1, '1,kk-porn01', 'kk黄站1号', 16, 'Morgpie', NULL, NULL, '2023-03-07 16:20:21', 100205, '乖女孩就该主动蹲下来口交', '乖女孩就该主动蹲下来口交-en', '乖女孩就该主动蹲下来口交-kh', '01:00:00', 0, 0, 0, 1, '/22g8qLO7/index.m3u8', '/22g8qLO7/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', 'Morgpie', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (207, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100271, '约了卫校的淫荡校鸡看她自慰', '约了卫校的淫荡校鸡看她自慰-en', '约了卫校的淫荡校鸡看她自慰-kh', '01:00:00', 0, 0, 0, 1, '/mJw9MgCN/index.m3u8', '/mJw9MgCN/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (208, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100272, '卖力热舞的小学妹酥胸摇晃', '卖力热舞的小学妹酥胸摇晃-en', '卖力热舞的小学妹酥胸摇晃-kh', '01:00:00', 0, 0, 0, 1, '/ZAB07xfJ/index.m3u8', '/ZAB07xfJ/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (209, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100273, '真诱惑还得看开档黑丝', '真诱惑还得看开档黑丝-en', '真诱惑还得看开档黑丝-kh', '01:00:00', 0, 0, 0, 1, '/WWXaM6ps/index.m3u8', '/WWXaM6ps/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (210, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100274, '翘臀小可爱牛仔裤完美勾勒轮廓', '翘臀小可爱牛仔裤完美勾勒轮廓-en', '翘臀小可爱牛仔裤完美勾勒轮廓-kh', '01:00:00', 0, 0, 0, 1, '/396mpjy0/index.m3u8', '/396mpjy0/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (211, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100275, '酒吧女玩的够花的逼逼都成黑木耳了', '酒吧女玩的够花的逼逼都成黑木耳了-en', '酒吧女玩的够花的逼逼都成黑木耳了-kh', '01:00:00', 0, 0, 0, 1, '/7Rxf4nwu/index.m3u8', '/7Rxf4nwu/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (212, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100276, '年轻美女奢靡淫乱的夜生活', '年轻美女奢靡淫乱的夜生活-en', '年轻美女奢靡淫乱的夜生活-kh', '01:00:00', 0, 0, 0, 1, '/DP0UFyH5/index.m3u8', '/DP0UFyH5/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (213, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100277, '拿什么吸引你呢我只是一个爱跳舞的骚比', '拿什么吸引你呢我只是一个爱跳舞的骚比-en', '拿什么吸引你呢我只是一个爱跳舞的骚比-kh', '01:00:00', 0, 0, 0, 1, '/7ZOVQIhl/index.m3u8', '/7ZOVQIhl/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (214, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100278, '微醺状态下的妹子逼逼洞口微微张开', '微醺状态下的妹子逼逼洞口微微张开-en', '微醺状态下的妹子逼逼洞口微微张开-kh', '01:00:00', 0, 0, 0, 1, '/midQ1Y7z/index.m3u8', '/midQ1Y7z/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (215, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100279, '身材那么好不拿来爆操就太可惜了', '身材那么好不拿来爆操就太可惜了-en', '身材那么好不拿来爆操就太可惜了-kh', '01:00:00', 0, 0, 0, 1, '/x4po0XE6/index.m3u8', '/x4po0XE6/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (216, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100280, '就这屁股你能扛得住几次', '就这屁股你能扛得住几次-en', '就这屁股你能扛得住几次-kh', '01:00:00', 0, 1, 0, 1, '/VOPHQCLZ/index.m3u8', '/VOPHQCLZ/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-17 15:20:07', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (217, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100281, '放假宿舍没人妹子表演校园春色', '放假宿舍没人妹子表演校园春色-en', '放假宿舍没人妹子表演校园春色-kh', '01:00:00', 0, 0, 0, 1, '/waYuJrBl/index.m3u8', '/waYuJrBl/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (218, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100282, '纯欲少女这又是谁的青春', '纯欲少女这又是谁的青春-en', '纯欲少女这又是谁的青春-kh', '01:00:00', 0, 1, 0, 1, '/FG9WbAg5/index.m3u8', '/FG9WbAg5/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (219, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100283, '甜美风骚的人间小尤物', '甜美风骚的人间小尤物-en', '甜美风骚的人间小尤物-kh', '01:00:00', 0, 0, 0, 1, '/t7Zg5K2r/index.m3u8', '/t7Zg5K2r/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (220, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100284, '游艇宝贝接不到单只能在家自慰', '游艇宝贝接不到单只能在家自慰-en', '游艇宝贝接不到单只能在家自慰-kh', '01:00:00', 0, 0, 0, 1, '/qEUNKdlF/index.m3u8', '/qEUNKdlF/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (221, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100285, '梨形身材少妇情趣黑丝腿上穿极品诱惑', '梨形身材少妇情趣黑丝腿上穿极品诱惑-en', '梨形身材少妇情趣黑丝腿上穿极品诱惑-kh', '01:00:00', 0, 0, 0, 1, '/0922EWlO/index.m3u8', '/0922EWlO/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (222, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100286, '小骚逼一天不自慰浑身难受', '小骚逼一天不自慰浑身难受-en', '小骚逼一天不自慰浑身难受-kh', '01:00:00', 0, 0, 0, 1, '/bo2ZW1ZD/index.m3u8', '/bo2ZW1ZD/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (223, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100287, '绿茶美女装矜持看到钱立马发骚', '绿茶美女装矜持看到钱立马发骚-en', '绿茶美女装矜持看到钱立马发骚-kh', '01:00:00', 0, 0, 0, 1, '/46tZCdbQ/index.m3u8', '/46tZCdbQ/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (224, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100288, '性感嘟嘟唇少女令人无限遐想', '性感嘟嘟唇少女令人无限遐想-en', '性感嘟嘟唇少女令人无限遐想-kh', '01:00:00', 0, 1, 0, 1, '/vUTYwpCJ/index.m3u8', '/vUTYwpCJ/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-18 19:34:03', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (225, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100289, '妹子贴身教你跳舞', '妹子贴身教你跳舞-en', '妹子贴身教你跳舞-kh', '01:00:00', 0, 0, 0, 1, '/0kUvvUQj/index.m3u8', '/0kUvvUQj/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (226, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100290, '体操服少女半脱裤子求操', '体操服少女半脱裤子求操-en', '体操服少女半脱裤子求操-kh', '01:00:00', 0, 0, 0, 1, '/cfZdbK4x/index.m3u8', '/cfZdbK4x/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (227, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100291, '是我不够骚吗不然怎么没进入你的心', '是我不够骚吗不然怎么没进入你的心-en', '是我不够骚吗不然怎么没进入你的心-kh', '01:00:00', 0, 1, 0, 1, '/KUeoWrHT/index.m3u8', '/KUeoWrHT/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-27 13:40:38', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (228, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100292, '老婆学了瑜伽非要用高难度姿势', '老婆学了瑜伽非要用高难度姿势-en', '老婆学了瑜伽非要用高难度姿势-kh', '01:00:00', 0, 0, 0, 1, '/7jfB0gt1/index.m3u8', '/7jfB0gt1/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (229, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100293, '妹子直播太辛苦奖励她一根肉棒', '妹子直播太辛苦奖励她一根肉棒-en', '妹子直播太辛苦奖励她一根肉棒-kh', '01:00:00', 0, 1, 0, 1, '/rCftmzF2/index.m3u8', '/rCftmzF2/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (230, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100294, '美女玩点真实的敞开衣服来直播', '美女玩点真实的敞开衣服来直播-en', '美女玩点真实的敞开衣服来直播-kh', '01:00:00', 0, 0, 0, 1, '/JtFS5sOH/index.m3u8', '/JtFS5sOH/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (231, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100295, '韵味少妇永远的神', '韵味少妇永远的神-en', '韵味少妇永远的神-kh', '01:00:00', 0, 0, 0, 1, '/V1dxlLjH/index.m3u8', '/V1dxlLjH/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (232, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100296, '你愿意当脱衣舞女的那根钢管吗', '你愿意当脱衣舞女的那根钢管吗-en', '你愿意当脱衣舞女的那根钢管吗-kh', '01:00:00', 0, 0, 0, 1, '/4uhUhb4W/index.m3u8', '/4uhUhb4W/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (233, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100297, '醉酒女人的深夜自嗨', '醉酒女人的深夜自嗨-en', '醉酒女人的深夜自嗨-kh', '01:00:00', 0, 0, 0, 1, '/aFBTYsbA/index.m3u8', '/aFBTYsbA/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (234, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100298, '职业技术学院的年轻小妹妹笑起来真美', '职业技术学院的年轻小妹妹笑起来真美-en', '职业技术学院的年轻小妹妹笑起来真美-kh', '01:00:00', 0, 0, 0, 1, '/9tVBOLba/index.m3u8', '/9tVBOLba/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (235, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100299, '鉴定完毕辣妹的粉逼都是辣的', '鉴定完毕辣妹的粉逼都是辣的-en', '鉴定完毕辣妹的粉逼都是辣的-kh', '01:00:00', 0, 0, 0, 1, '/fQf6ZgyW/index.m3u8', '/fQf6ZgyW/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (236, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100300, '清纯美女翘起屁屁求插菊', '清纯美女翘起屁屁求插菊-en', '清纯美女翘起屁屁求插菊-kh', '01:00:00', 0, 0, 0, 1, '/FioUdTYm/index.m3u8', '/FioUdTYm/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (237, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100301, '什么时候你才能来操操这样的坏女人啊', '什么时候你才能来操操这样的坏女人啊-en', '什么时候你才能来操操这样的坏女人啊-kh', '01:00:00', 0, 2, 0, 1, '/duaQI09c/index.m3u8', '/duaQI09c/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-17 13:39:51', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (238, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100302, '论当代女大学生有多饥渴', '论当代女大学生有多饥渴-en', '论当代女大学生有多饥渴-kh', '01:00:00', 0, 123, 1, 1, '/mpbRWCo8/index.m3u8', '/mpbRWCo8/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-27 19:46:10', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (239, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100303, '钟爱丁字裤的隔壁小姐姐', '钟爱丁字裤的隔壁小姐姐-en', '钟爱丁字裤的隔壁小姐姐-kh', '01:00:00', 0, 41, 1, 1, '/6kD749Xe/index.m3u8', '/6kD749Xe/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-27 19:47:47', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (240, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100304, '发了骚的妹子太主动勾引小哥舔嫩逼', '发了骚的妹子太主动勾引小哥舔嫩逼-en', '发了骚的妹子太主动勾引小哥舔嫩逼-kh', '01:00:00', 0, 19, 1, 1, '/yEypCuQP/index.m3u8', '/yEypCuQP/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-28 13:45:43', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (241, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100305, '舞蹈热身再自慰的艺术学院学妹', '舞蹈热身再自慰的艺术学院学妹-en', '舞蹈热身再自慰的艺术学院学妹-kh', '01:00:00', 0, 0, 0, 1, '/unJ5enev/index.m3u8', '/unJ5enev/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (242, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100306, '白T少妇穿上蕾丝内裤骚舞', '白T少妇穿上蕾丝内裤骚舞-en', '白T少妇穿上蕾丝内裤骚舞-kh', '01:00:00', 0, 0, 0, 1, '/O5CqBuy0/index.m3u8', '/O5CqBuy0/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-13 13:19:16', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (243, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100307, '极致诱惑之慢摇脱衣舞', '极致诱惑之慢摇脱衣舞-en', '极致诱惑之慢摇脱衣舞-kh', '01:00:00', 0, 1, 0, 1, '/7Q1SFYJ8/index.m3u8', '/7Q1SFYJ8/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-17 15:17:39', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (244, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100308, '美女想男人想疯了天天喊着让人操她', '美女想男人想疯了天天喊着让人操她-en', '美女想男人想疯了天天喊着让人操她-kh', '01:00:00', 0, 4, 0, 1, '/NDqEtUfl/index.m3u8', '/NDqEtUfl/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-18 21:15:10', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (245, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100309, '又到了每日一看的抠逼环节', '又到了每日一看的抠逼环节-en', '又到了每日一看的抠逼环节-kh', '01:00:00', 0, 61, 1, 1, '/K7MnQwQs/index.m3u8', '/K7MnQwQs/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-27 19:47:11', 'admin', 'admin');
INSERT INTO `kpn_site_movie` VALUES (246, 1, '1,kk-porn01', 'kk黄站1号', 0, '未知', 'unknown', 'unknown', '2023-02-01 23:07:24', 100310, '白嫩辣妹还不能让你心动吗', '白嫩辣妹还不能让你心动吗-en', '白嫩辣妹还不能让你心动吗-kh', '01:00:00', 0, 6, 0, 1, '/P9em1WSy/index.m3u8', '/P9em1WSy/1.jpg', NULL, 0, 0, 0, NULL, NULL, '2023-03-07 15:40:31', '口罩系列', '2023-03-08 13:18:59', '2023-03-23 22:16:50', 'admin', 'admin');

-- ----------------------------
-- Table structure for kpn_site_platform
-- ----------------------------
DROP TABLE IF EXISTS `kpn_site_platform`;
CREATE TABLE `kpn_site_platform`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `site_id` bigint(11) NULL DEFAULT NULL COMMENT '站点id',
  `site_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点编码',
  `site_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点名称',
  `exchange` decimal(16, 2) NULL DEFAULT NULL COMMENT '兑换K币数',
  `try_time` int(11) NULL DEFAULT NULL COMMENT '试看时长(秒)',
  `lost_domain` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '防丢失域名',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_siteid`(`site_id`) USING BTREE COMMENT '站点唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '站点平台配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kpn_site_platform
-- ----------------------------
INSERT INTO `kpn_site_platform` VALUES (1, 1, 'kk-porn01', 'kk黄站1号', 100000.00, 30, 'http://www.baidu.com', '2023-02-24 13:40:40', '2023-02-24 18:09:11', 'admin', 'admin');
INSERT INTO `kpn_site_platform` VALUES (2, 11, 'code07', 'lulu07', 888.00, 10, 'http://', '2023-02-24 15:55:17', '2023-02-24 18:09:11', 'admin', 'admin');

-- ----------------------------
-- Table structure for kpn_site_product
-- ----------------------------
DROP TABLE IF EXISTS `kpn_site_product`;
CREATE TABLE `kpn_site_product`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `site_id` bigint(11) NULL DEFAULT NULL COMMENT '站点id',
  `site_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点编码',
  `site_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点名称',
  `name_zh` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品名称(中文)',
  `name_en` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品名称(英文)',
  `name_kh` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品名称(柬文)',
  `price` decimal(18, 2) NULL DEFAULT NULL COMMENT '价格(K币)',
  `real_price` decimal(18, 2) NULL DEFAULT NULL COMMENT '实际价格(K币)',
  `valid_days` int(255) NULL DEFAULT NULL COMMENT '有效期(天数)',
  `status` tinyint(255) NOT NULL DEFAULT 1 COMMENT '状态 0下架,1上架',
  `discount_remark` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '优惠描述',
  `sort` int(255) NOT NULL DEFAULT 0 COMMENT '排序(越大越靠前)',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_siteid_status_sort`(`site_id`, `status`, `sort`) USING BTREE,
  FULLTEXT INDEX `site_product_fulltext`(`name_zh`, `name_en`, `name_kh`)
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'vip产品' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kpn_site_product
-- ----------------------------
INSERT INTO `kpn_site_product` VALUES (1, 11, 'code07', 'lulu07', '季度VIP', '季度VIP-en', '季度VIP-kh', 80.00, 80.00, 365, 1, '季度', 0, '2023-02-23 14:19:11', '2023-02-24 14:18:19', NULL, NULL);
INSERT INTO `kpn_site_product` VALUES (2, 1, 'kk-porn01', 'kk黄站1号', '月度VIP', '月度VIP-en', '月度VIP-kh', 100000.00, 100000.00, 30, 1, '月度', 0, '2023-02-24 13:45:11', '2023-02-24 14:18:19', NULL, NULL);
INSERT INTO `kpn_site_product` VALUES (3, 1, 'kk-porn01', 'kk黄站1号', '季度VIP', '季度VIP-en', '季度VIP-kh', 200000.00, 200000.00, 90, 1, '季度', 0, '2023-02-24 13:45:57', '2023-02-24 14:18:19', NULL, NULL);
INSERT INTO `kpn_site_product` VALUES (4, 1, 'kk-porn01', 'kk黄站1号', '年度VIP', '年度VIP-en', '年度VIP-kh', 500000.00, 500000.00, 365, 1, '年度', 0, '2023-02-24 13:46:15', '2023-02-24 14:18:19', NULL, NULL);

-- ----------------------------
-- Table structure for kpn_site_promotion
-- ----------------------------
DROP TABLE IF EXISTS `kpn_site_promotion`;
CREATE TABLE `kpn_site_promotion`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `site_id` int(11) NULL DEFAULT NULL COMMENT '站点id',
  `site_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点编码',
  `site_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点名称',
  `invite_vip` int(255) NULL DEFAULT NULL COMMENT '邀请人获取vip天数',
  `invite_kb` decimal(18, 2) NULL DEFAULT NULL COMMENT '邀请人获取k币数',
  `kb_day_limit` decimal(18, 2) NULL DEFAULT NULL COMMENT '邀请人单日k币上限',
  `be_invited_vip` int(255) NULL DEFAULT NULL COMMENT '被邀请人获取vip天数',
  `be_invited_kb` decimal(18, 2) NULL DEFAULT NULL COMMENT '被邀请人获取K币数',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_siteid`(`site_id`) USING BTREE COMMENT '站点id唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '站点推广好友配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kpn_site_promotion
-- ----------------------------
INSERT INTO `kpn_site_promotion` VALUES (1, 1, 'code1', '名字', 2, 10.00, 777.00, 1, 14.00, '2023-02-02 16:08:49', '2023-02-23 23:36:59', NULL, NULL);
INSERT INTO `kpn_site_promotion` VALUES (2, 2, 'code2', 'name77', 77, 77.00, 77.00, 77, 77.00, '2023-02-06 03:52:32', '2023-02-06 03:52:32', NULL, NULL);

-- ----------------------------
-- Table structure for kpn_site_serve
-- ----------------------------
DROP TABLE IF EXISTS `kpn_site_serve`;
CREATE TABLE `kpn_site_serve`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `site_id` bigint(11) NULL DEFAULT NULL COMMENT '站点id',
  `site_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点编码',
  `site_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点名称',
  `platform` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '平台(Skype,QQ,Wechat,Telegram,WhatsApp,手机号)',
  `serve_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '平台客服账号',
  `status` tinyint(255) NOT NULL DEFAULT 0 COMMENT '状态 0关闭,1启用',
  `pc_icon_url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'pc图标地址',
  `app_icon_url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'app图标地址',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '站点客服配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kpn_site_serve
-- ----------------------------
INSERT INTO `kpn_site_serve` VALUES (1, 1, 'kk-porn01', 'kk黄站1号', 'QQ', 'yixiutest', 1, 'kkporn/serve/qq.png', 'kkporn/serve/qq.png', '2022-03-08 19:56:23', '2023-03-17 16:17:58', 'yixiu', 'yixiu');
INSERT INTO `kpn_site_serve` VALUES (2, 1, 'kk-porn01', 'kk黄站1号', 'WeChat', 'yixiutest', 1, 'kkporn/serve/wechat.png', 'kkporn/serve/wechat.png', '2022-03-08 19:56:23', '2023-03-17 16:31:41', 'yixiu', 'yixiu');
INSERT INTO `kpn_site_serve` VALUES (3, 1, 'kk-porn01', 'kk黄站1号', 'Skype', 'yixiutest', 1, 'kkporn/serve/skype.png', 'kkporn/serve/skype.png', '2022-03-08 19:56:23', '2023-03-17 16:17:58', 'yixiu', 'yixiu');
INSERT INTO `kpn_site_serve` VALUES (4, 1, 'kk-porn01', 'kk黄站1号', 'Telegram', 'yixiutest', 1, 'kkporn/serve/tele.png', 'kkporn/serve/tele.png', '2022-03-08 19:56:23', '2023-03-17 16:17:58', 'yixiu', 'yixiu');
INSERT INTO `kpn_site_serve` VALUES (5, 1, 'kk-porn01', 'kk黄站1号', 'WhatsApp', 'yixiutest', 1, 'kkporn/serve/whatsapp.png', 'kkporn/serve/whatsapp.png', '2022-03-08 19:56:23', '2023-03-17 16:17:58', 'yixiu', 'yixiu');
INSERT INTO `kpn_site_serve` VALUES (6, 1, 'kk-porn01', 'kk黄站1号', 'Phone', 'yixiutest', 1, 'kkporn/serve/phone.png', 'kkporn/serve/phone.png', '2022-03-08 19:56:23', '2023-03-17 16:17:58', 'yixiu', 'yixiu');

-- ----------------------------
-- Table structure for kpn_site_sign
-- ----------------------------
DROP TABLE IF EXISTS `kpn_site_sign`;
CREATE TABLE `kpn_site_sign`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `site_id` bigint(11) NULL DEFAULT NULL COMMENT '站点id',
  `site_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点编码',
  `site_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点名称',
  `sign_days` int(255) UNSIGNED NOT NULL DEFAULT 1 COMMENT '累计天数',
  `reward_vip` int(255) UNSIGNED NOT NULL DEFAULT 0 COMMENT '奖励vip天数',
  `reward_kb` decimal(18, 2) UNSIGNED NOT NULL COMMENT '奖励K币数据',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_siteid_signdays`(`site_id`, `sign_days`) USING BTREE COMMENT '站点签到天数唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '站点签到配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kpn_site_sign
-- ----------------------------
INSERT INTO `kpn_site_sign` VALUES (1, 1, 'code1', '名字', 1, 1, 30.00, '2023-02-02 15:04:20', '2023-02-23 23:35:36', NULL, NULL);
INSERT INTO `kpn_site_sign` VALUES (2, 1, 'code2', '名字2', 3, 2, 50.00, '2023-02-02 15:04:20', '2023-02-23 23:36:05', NULL, NULL);
INSERT INTO `kpn_site_sign` VALUES (3, 1, 'code1', '名字', 5, 3, 100.00, '2023-02-02 15:05:19', '2023-02-23 23:36:04', NULL, NULL);
INSERT INTO `kpn_site_sign` VALUES (4, 1, 'code1', '名字', 10, 4, 200.00, '2023-02-02 15:05:19', '2023-02-23 23:36:04', NULL, NULL);
INSERT INTO `kpn_site_sign` VALUES (7, 2, 'code2', 'name', 1, 1, 1.00, '2023-02-06 03:46:00', '2023-02-06 03:46:00', NULL, NULL);

-- ----------------------------
-- Table structure for kpn_site_sign_detail
-- ----------------------------
DROP TABLE IF EXISTS `kpn_site_sign_detail`;
CREATE TABLE `kpn_site_sign_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `site_id` bigint(11) NULL DEFAULT NULL COMMENT '站点id',
  `site_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点编码',
  `site_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点名称',
  `user_id` bigint(20) UNSIGNED NULL DEFAULT 0 COMMENT '会员id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会员账号',
  `days` int(255) NOT NULL DEFAULT 1 COMMENT '连续签到天数',
  `sign_month` char(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '签到月份',
  `sign_date` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '签到日期',
  `is_reward` tinyint(20) NOT NULL DEFAULT 0 COMMENT '是否得到奖励(0:未得到,1:获取到)',
  `site_sign_id` bigint(20) NULL DEFAULT NULL COMMENT '站点签到规则id',
  `reward_vip` int(255) NULL DEFAULT NULL COMMENT '奖励vip天数',
  `reward_kb` decimal(18, 2) NULL DEFAULT NULL COMMENT '奖励k币数',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_userid_signmonth_signdate`(`user_id`, `sign_month`, `sign_date`) USING BTREE COMMENT '唯一',
  UNIQUE INDEX `uni_userid_signdate`(`user_id`, `sign_date`) USING BTREE COMMENT '唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '站点会员签到详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kpn_site_sign_detail
-- ----------------------------
INSERT INTO `kpn_site_sign_detail` VALUES (4, 1, 'kk-porn01', 'kk黄站1号', 94, 'kk-porn01_year001', 1, '2023-02', '2023-02-24', 1, 1, 1, 30.00, '2023-02-24 00:07:34');
INSERT INTO `kpn_site_sign_detail` VALUES (5, 1, 'kk-porn01', 'kk黄站1号', 100, 'kk-porn01_year002', 1, '2023-02', '2023-02-24', 1, 1, 1, 30.00, '2023-02-24 00:15:33');
INSERT INTO `kpn_site_sign_detail` VALUES (6, 1, 'kk-porn01', 'kk黄站1号', 136, 'kk-porn01_qwe112233', 1, '2023-03', '2023-03-25', 1, 1, 1, 30.00, '2023-03-25 20:49:47');
INSERT INTO `kpn_site_sign_detail` VALUES (7, 1, 'kk-porn01', 'kk黄站1号', 136, 'kk-porn01_qwe112233', 2, '2023-03', '2023-03-26', 0, NULL, NULL, NULL, '2023-03-26 15:27:24');
INSERT INTO `kpn_site_sign_detail` VALUES (8, 1, 'kk-porn01', 'kk黄站1号', 141, 'kk-porn01_qwe1122333', 1, '2023-03', '2023-03-26', 1, 1, 1, 30.00, '2023-03-26 17:02:58');
INSERT INTO `kpn_site_sign_detail` VALUES (9, 1, 'kk-porn01', 'kk黄站1号', 142, 'kk-porn01_chezai01', 1, '2023-03', '2023-03-26', 1, 1, 1, 30.00, '2023-03-26 17:11:31');
INSERT INTO `kpn_site_sign_detail` VALUES (10, 1, 'kk-porn01', 'kk黄站1号', 136, 'kk-porn01_qwe112233', 3, '2023-03', '2023-03-27', 1, 2, 2, 50.00, '2023-03-27 12:25:19');

-- ----------------------------
-- Table structure for kpn_site_suggestion
-- ----------------------------
DROP TABLE IF EXISTS `kpn_site_suggestion`;
CREATE TABLE `kpn_site_suggestion`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `site_id` bigint(11) NULL DEFAULT NULL COMMENT '站点id',
  `site_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点编码',
  `site_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点名称',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '会员id',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会员账号',
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '意见反馈',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `status` tinyint(255) NOT NULL DEFAULT 0 COMMENT '状态 0待处理,1已处理',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '站点意见' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kpn_site_suggestion
-- ----------------------------
INSERT INTO `kpn_site_suggestion` VALUES (1, 0, NULL, NULL, 1, 'ewew', '待处理2', NULL, '12436453645', 1, '2023-02-01 21:35:01', '2023-02-20 16:37:57', NULL, NULL);
INSERT INTO `kpn_site_suggestion` VALUES (2, 0, NULL, NULL, 2, '333', '待处理1', NULL, NULL, 1, '2023-01-01 21:42:51', '2023-02-20 16:25:30', NULL, NULL);
INSERT INTO `kpn_site_suggestion` VALUES (3, NULL, NULL, NULL, 3, '22', '已处理', NULL, NULL, 1, '2023-02-01 21:41:05', '2023-02-01 21:43:20', NULL, NULL);
INSERT INTO `kpn_site_suggestion` VALUES (4, 1, 'kk-porn01', 'kk黄站1号', 94, 'kk-porn01_year001', '123', NULL, NULL, 0, '2023-02-21 23:02:38', '2023-02-21 23:02:38', NULL, NULL);
INSERT INTO `kpn_site_suggestion` VALUES (5, 1, 'kk-porn01', 'kk黄站1号', 94, 'kk-porn01_year001', '内容不精彩', '123@231.com', NULL, 0, '2023-02-21 23:04:43', '2023-02-21 23:04:43', NULL, NULL);
INSERT INTO `kpn_site_suggestion` VALUES (6, 1, 'kk-porn01', 'kk黄站1号', 136, 'kk-porn01_qwe112233', '测试', '123@13.com', NULL, 0, '2023-03-27 14:37:13', '2023-03-27 14:37:13', NULL, NULL);
INSERT INTO `kpn_site_suggestion` VALUES (7, 1, 'kk-porn01', 'kk黄站1号', 136, 'kk-porn01_qwe112233', '测试', '123@13.com', NULL, 0, '2023-03-27 14:37:17', '2023-03-27 14:37:17', NULL, NULL);

-- ----------------------------
-- Table structure for kpn_site_topic
-- ----------------------------
DROP TABLE IF EXISTS `kpn_site_topic`;
CREATE TABLE `kpn_site_topic`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `site_id` int(11) NULL DEFAULT NULL COMMENT '站点id',
  `site_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点编码',
  `site_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点名称',
  `name_zh` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '频道名称(中文)',
  `name_en` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '频道名称(英文)',
  `name_kh` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '频道名称(柬文)',
  `sort` bigint(255) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序(越大越靠前)',
  `hits` bigint(255) NOT NULL DEFAULT 0 COMMENT '点击量',
  `composing_id` int(255) NOT NULL DEFAULT 1 COMMENT '排版id',
  `status` tinyint(255) NOT NULL DEFAULT 0 COMMENT '状态 0待发布,1上架,2下架',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_siteid_status_sort`(`site_id`, `sort`, `status`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100007 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '站点专题' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kpn_site_topic
-- ----------------------------
INSERT INTO `kpn_site_topic` VALUES (100000, 1, 'kk-porn01', 'kk黄站1号', '测试专题1', '测试专题1-en', '测试专题1-kh', 0, 0, 1, 0, '2023-03-08 17:46:20', '2023-03-08 17:46:20', NULL, NULL);
INSERT INTO `kpn_site_topic` VALUES (100001, 1, 'kk-porn01', 'kk黄站1号', '测试专题2', '测试专题2-en', '测试专题2-kh', 0, 0, 2, 1, '2023-03-08 17:47:42', '2023-03-08 17:50:49', NULL, NULL);
INSERT INTO `kpn_site_topic` VALUES (100002, 1, 'kk-porn01', 'kk黄站1号', '测试专题3', '测试专题3-en', '测试专题3-kh', 0, 0, 3, 1, '2023-03-08 17:49:24', '2023-03-08 17:50:49', NULL, NULL);
INSERT INTO `kpn_site_topic` VALUES (100003, 1, 'kk-porn01', 'kk黄站1号', '测试专题4', '测试专题4-en', '测试专题4-kh', 0, 0, 4, 1, '2023-03-08 17:49:50', '2023-03-08 17:50:49', NULL, NULL);
INSERT INTO `kpn_site_topic` VALUES (100004, 1, 'kk-porn01', 'kk黄站1号', '测试专题5', '测试专题5-en', '测试专题5-kh', 0, 0, 1, 1, '2023-03-08 17:50:29', '2023-03-08 17:50:49', NULL, NULL);
INSERT INTO `kpn_site_topic` VALUES (100005, 1, 'kk-porn01', 'kk黄站1号', '测试专题6', '测试专题6-en', '测试专题6-kh', 0, 0, 3, 1, '2023-03-08 17:51:20', '2023-03-08 17:51:27', NULL, NULL);
INSERT INTO `kpn_site_topic` VALUES (100006, 1, 'kk-porn01', 'kk黄站1号', '测试专题7', '测试专题7-en', '测试专题7-kh', 0, 0, 1, 2, '2023-03-08 17:51:53', '2023-03-08 17:51:58', NULL, NULL);

-- ----------------------------
-- Table structure for kpn_site_topic_composing
-- ----------------------------
DROP TABLE IF EXISTS `kpn_site_topic_composing`;
CREATE TABLE `kpn_site_topic_composing`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `image` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '缩略图',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '专题排版表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kpn_site_topic_composing
-- ----------------------------
INSERT INTO `kpn_site_topic_composing` VALUES (1, '/kkporn/topic/5.jpg', '一张大图+4张小图');
INSERT INTO `kpn_site_topic_composing` VALUES (2, '/kkporn/topic/4.jpg', '4张小图 ');
INSERT INTO `kpn_site_topic_composing` VALUES (3, '/kkporn/topic/10.jpg', '左右滑动10张图');
INSERT INTO `kpn_site_topic_composing` VALUES (4, '/kkporn/topic/8.jpg', ' 8张小图');

-- ----------------------------
-- Table structure for kpn_site_topic_movie
-- ----------------------------
DROP TABLE IF EXISTS `kpn_site_topic_movie`;
CREATE TABLE `kpn_site_topic_movie`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `site_id` int(11) NULL DEFAULT NULL COMMENT '站点id',
  `site_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点编码',
  `site_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点名称',
  `topic_id` bigint(20) NULL DEFAULT NULL COMMENT '站点专题',
  `movie_id` bigint(20) NULL DEFAULT NULL COMMENT '影片id',
  `sort` bigint(255) NOT NULL DEFAULT 0 COMMENT '排序(越大越靠前)',
  `vv` bigint(255) NOT NULL DEFAULT 0 COMMENT '专题影片播放量',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_siteid_topicid_movieid`(`site_id`, `topic_id`, `movie_id`) USING BTREE COMMENT '唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 142 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '站点专题影片关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kpn_site_topic_movie
-- ----------------------------
INSERT INTO `kpn_site_topic_movie` VALUES (1, 1, 'kk-porn01', 'kk黄站1号', 100000, 100000, 0, 6, '2023-02-04 16:16:37', '2023-03-10 22:18:33', 'year', 'year');
INSERT INTO `kpn_site_topic_movie` VALUES (2, 1, 'kk-porn01', 'kk黄站1号', 100000, 100001, 0, 0, '2023-02-04 16:16:37', '2023-03-08 17:57:16', 'year', 'year');
INSERT INTO `kpn_site_topic_movie` VALUES (3, 1, 'kk-porn01', 'kk黄站1号', 100000, 100002, 0, 0, '2023-02-04 16:16:37', '2023-03-08 17:57:16', 'year', 'year');
INSERT INTO `kpn_site_topic_movie` VALUES (4, 1, 'kk-porn01', 'kk黄站1号', 100000, 100003, 444, 0, '2023-02-04 16:16:37', '2023-03-08 17:57:16', 'year', 'year');
INSERT INTO `kpn_site_topic_movie` VALUES (5, 1, 'kk-porn01', 'kk黄站1号', 100000, 100004, 555, 0, '2023-02-04 16:16:37', '2023-03-08 17:57:16', 'year', 'year');
INSERT INTO `kpn_site_topic_movie` VALUES (6, 1, 'kk-porn01', 'kk黄站1号', 100000, 100005, 0, 0, '2023-02-04 16:16:37', '2023-03-08 17:57:15', 'year', 'year');
INSERT INTO `kpn_site_topic_movie` VALUES (7, 1, 'kk-porn01', 'kk黄站1号', 100000, 100006, 0, 0, '2023-02-04 16:16:37', '2023-03-08 17:57:15', 'year', 'year');
INSERT INTO `kpn_site_topic_movie` VALUES (8, 1, 'kk-porn01', 'kk黄站1号', 100000, 100007, 0, 0, '2023-02-04 16:16:37', '2023-03-08 17:57:16', 'year', 'year');
INSERT INTO `kpn_site_topic_movie` VALUES (9, 1, 'kk-porn01', 'kk黄站1号', 100000, 100008, 0, 0, '2023-02-04 16:16:37', '2023-03-08 17:57:16', 'year', 'year');
INSERT INTO `kpn_site_topic_movie` VALUES (10, 1, 'kk-porn01', 'kk黄站1号', 100000, 100009, 0, 0, '2023-02-04 16:16:37', '2023-03-08 17:57:16', 'year', 'year');
INSERT INTO `kpn_site_topic_movie` VALUES (11, 1, 'kk-porn01', 'kk黄站1号', 100000, 100010, 0, 0, '2023-02-04 16:16:37', '2023-03-08 17:57:16', 'year', 'year');
INSERT INTO `kpn_site_topic_movie` VALUES (12, 1, 'kk-porn01', 'kk黄站1号', 100000, 100011, 0, 0, '2023-02-04 16:16:37', '2023-03-08 17:57:16', 'year', 'year');
INSERT INTO `kpn_site_topic_movie` VALUES (13, 1, 'kk-porn01', 'kk黄站1号', 100000, 100012, 0, 0, '2023-02-04 16:16:37', '2023-03-08 17:57:16', 'year', 'year');
INSERT INTO `kpn_site_topic_movie` VALUES (21, 1, 'kk-porn01', 'kk黄站1号', 100000, 100013, 0, 0, '2023-03-03 19:39:57', '2023-03-08 17:57:15', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (22, 1, 'kk-porn01', 'kk黄站1号', 100000, 100014, 0, 0, '2023-03-03 19:40:05', '2023-03-08 17:57:15', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (23, 1, 'kk-porn01', 'kk黄站1号', 100000, 100015, 0, 0, '2023-03-03 19:40:19', '2023-03-08 17:57:15', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (24, 1, 'kk-porn01', 'kk黄站1号', 100000, 100016, 0, 0, '2023-03-08 17:54:04', '2023-03-08 17:57:15', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (25, 1, 'kk-porn01', 'kk黄站1号', 100000, 100017, 0, 0, '2023-03-08 17:54:04', '2023-03-08 17:57:15', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (26, 1, 'kk-porn01', 'kk黄站1号', 100000, 100018, 0, 0, '2023-03-08 17:54:04', '2023-03-08 17:57:15', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (27, 1, 'kk-porn01', 'kk黄站1号', 100000, 100019, 0, 0, '2023-03-08 17:54:04', '2023-03-08 17:57:15', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (28, 1, 'kk-porn01', 'kk黄站1号', 100000, 100020, 0, 0, '2023-03-08 17:54:04', '2023-03-08 17:57:15', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (29, 1, 'kk-porn01', 'kk黄站1号', 100001, 100006, 0, 0, '2023-03-08 17:57:16', '2023-03-08 17:57:16', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (30, 1, 'kk-porn01', 'kk黄站1号', 100001, 100007, 0, 0, '2023-03-08 17:57:16', '2023-03-08 17:57:16', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (31, 1, 'kk-porn01', 'kk黄站1号', 100001, 100008, 0, 0, '2023-03-08 17:57:16', '2023-03-08 17:57:16', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (32, 1, 'kk-porn01', 'kk黄站1号', 100001, 100009, 0, 0, '2023-03-08 17:57:16', '2023-03-08 17:57:16', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (33, 1, 'kk-porn01', 'kk黄站1号', 100001, 100010, 0, 0, '2023-03-08 17:57:16', '2023-03-08 17:57:16', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (34, 1, 'kk-porn01', 'kk黄站1号', 100001, 100011, 0, 0, '2023-03-08 17:57:16', '2023-03-08 17:57:16', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (35, 1, 'kk-porn01', 'kk黄站1号', 100001, 100012, 0, 0, '2023-03-08 17:57:17', '2023-03-08 17:57:17', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (36, 1, 'kk-porn01', 'kk黄站1号', 100001, 100013, 0, 0, '2023-03-08 17:57:17', '2023-03-08 17:57:17', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (37, 1, 'kk-porn01', 'kk黄站1号', 100001, 100014, 0, 0, '2023-03-08 17:57:17', '2023-03-08 17:57:17', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (38, 1, 'kk-porn01', 'kk黄站1号', 100001, 100015, 0, 0, '2023-03-08 17:57:17', '2023-03-08 17:57:17', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (39, 1, 'kk-porn01', 'kk黄站1号', 100001, 100016, 0, 0, '2023-03-08 17:57:17', '2023-03-08 17:57:17', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (40, 1, 'kk-porn01', 'kk黄站1号', 100001, 100017, 0, 0, '2023-03-08 17:57:17', '2023-03-08 17:57:17', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (41, 1, 'kk-porn01', 'kk黄站1号', 100001, 100018, 0, 0, '2023-03-08 17:57:17', '2023-03-08 17:57:17', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (42, 1, 'kk-porn01', 'kk黄站1号', 100001, 100019, 0, 0, '2023-03-08 17:57:17', '2023-03-08 17:57:17', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (43, 1, 'kk-porn01', 'kk黄站1号', 100001, 100020, 0, 0, '2023-03-08 17:57:17', '2023-03-08 17:57:17', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (44, 1, 'kk-porn01', 'kk黄站1号', 100001, 100000, 0, 0, '2023-03-08 17:58:10', '2023-03-08 17:58:10', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (45, 1, 'kk-porn01', 'kk黄站1号', 100001, 100001, 0, 0, '2023-03-08 17:58:10', '2023-03-08 17:58:10', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (46, 1, 'kk-porn01', 'kk黄站1号', 100001, 100002, 0, 0, '2023-03-08 17:58:10', '2023-03-08 17:58:10', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (47, 1, 'kk-porn01', 'kk黄站1号', 100001, 100003, 0, 0, '2023-03-08 17:58:10', '2023-03-08 17:58:10', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (48, 1, 'kk-porn01', 'kk黄站1号', 100001, 100004, 0, 0, '2023-03-08 17:58:10', '2023-03-08 17:58:10', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (49, 1, 'kk-porn01', 'kk黄站1号', 100001, 100005, 0, 0, '2023-03-08 17:58:10', '2023-03-08 17:58:10', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (50, 1, 'kk-porn01', 'kk黄站1号', 100002, 100030, 0, 0, '2023-03-08 18:00:24', '2023-03-08 18:00:30', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (51, 1, 'kk-porn01', 'kk黄站1号', 100002, 100031, 0, 0, '2023-03-08 18:00:24', '2023-03-08 18:00:29', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (52, 1, 'kk-porn01', 'kk黄站1号', 100002, 100032, 0, 0, '2023-03-08 18:00:24', '2023-03-08 18:00:29', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (53, 1, 'kk-porn01', 'kk黄站1号', 100002, 100033, 0, 0, '2023-03-08 18:00:24', '2023-03-08 18:00:29', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (54, 1, 'kk-porn01', 'kk黄站1号', 100002, 100034, 0, 0, '2023-03-08 18:00:24', '2023-03-08 18:00:29', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (55, 1, 'kk-porn01', 'kk黄站1号', 100002, 100035, 0, 0, '2023-03-08 18:00:24', '2023-03-08 18:00:29', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (56, 1, 'kk-porn01', 'kk黄站1号', 100002, 100036, 0, 0, '2023-03-08 18:00:24', '2023-03-08 18:00:29', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (57, 1, 'kk-porn01', 'kk黄站1号', 100002, 100037, 0, 0, '2023-03-08 18:00:24', '2023-03-08 18:00:29', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (58, 1, 'kk-porn01', 'kk黄站1号', 100002, 100038, 0, 0, '2023-03-08 18:00:24', '2023-03-08 18:00:29', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (59, 1, 'kk-porn01', 'kk黄站1号', 100002, 100039, 0, 0, '2023-03-08 18:00:24', '2023-03-08 18:00:29', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (60, 1, 'kk-porn01', 'kk黄站1号', 100002, 100040, 0, 0, '2023-03-08 18:00:24', '2023-03-08 18:00:29', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (61, 1, 'kk-porn01', 'kk黄站1号', 100002, 100041, 0, 0, '2023-03-08 18:00:24', '2023-03-08 18:00:29', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (62, 1, 'kk-porn01', 'kk黄站1号', 100002, 100042, 0, 0, '2023-03-08 18:00:24', '2023-03-08 18:00:29', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (63, 1, 'kk-porn01', 'kk黄站1号', 100002, 100043, 0, 0, '2023-03-08 18:00:24', '2023-03-08 18:00:30', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (64, 1, 'kk-porn01', 'kk黄站1号', 100002, 100044, 0, 0, '2023-03-08 18:00:24', '2023-03-08 18:00:30', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (65, 1, 'kk-porn01', 'kk黄站1号', 100002, 100045, 0, 0, '2023-03-08 18:01:24', '2023-03-08 18:01:24', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (66, 1, 'kk-porn01', 'kk黄站1号', 100002, 100046, 0, 0, '2023-03-08 18:01:24', '2023-03-08 18:01:24', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (67, 1, 'kk-porn01', 'kk黄站1号', 100002, 100047, 0, 0, '2023-03-08 18:01:24', '2023-03-08 18:01:24', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (68, 1, 'kk-porn01', 'kk黄站1号', 100002, 100048, 0, 0, '2023-03-08 18:01:24', '2023-03-08 18:01:24', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (69, 1, 'kk-porn01', 'kk黄站1号', 100002, 100049, 0, 0, '2023-03-08 18:01:24', '2023-03-08 18:01:24', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (70, 1, 'kk-porn01', 'kk黄站1号', 100002, 100050, 0, 0, '2023-03-08 18:01:24', '2023-03-08 18:01:24', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (71, 1, 'kk-porn01', 'kk黄站1号', 100002, 100051, 0, 0, '2023-03-08 18:03:40', '2023-03-08 18:03:40', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (72, 1, 'kk-porn01', 'kk黄站1号', 100002, 100052, 0, 0, '2023-03-08 18:03:40', '2023-03-08 18:03:40', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (73, 1, 'kk-porn01', 'kk黄站1号', 100002, 100053, 0, 0, '2023-03-08 18:03:40', '2023-03-08 18:03:40', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (74, 1, 'kk-porn01', 'kk黄站1号', 100002, 100054, 0, 0, '2023-03-08 18:03:40', '2023-03-08 18:03:40', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (75, 1, 'kk-porn01', 'kk黄站1号', 100002, 100055, 0, 0, '2023-03-08 18:03:40', '2023-03-08 18:03:40', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (76, 1, 'kk-porn01', 'kk黄站1号', 100002, 100056, 0, 0, '2023-03-08 18:03:40', '2023-03-08 18:03:40', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (77, 1, 'kk-porn01', 'kk黄站1号', 100002, 100057, 0, 0, '2023-03-08 18:03:40', '2023-03-08 18:03:40', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (78, 1, 'kk-porn01', 'kk黄站1号', 100002, 100058, 0, 0, '2023-03-08 18:03:40', '2023-03-08 18:03:40', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (79, 1, 'kk-porn01', 'kk黄站1号', 100002, 100059, 0, 0, '2023-03-08 18:03:40', '2023-03-08 18:03:40', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (80, 1, 'kk-porn01', 'kk黄站1号', 100002, 100060, 0, 0, '2023-03-08 18:03:40', '2023-03-08 18:03:40', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (81, 1, 'kk-porn01', 'kk黄站1号', 100002, 100061, 0, 0, '2023-03-08 18:03:40', '2023-03-08 18:03:40', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (82, 1, 'kk-porn01', 'kk黄站1号', 100002, 100062, 0, 0, '2023-03-08 18:03:40', '2023-03-08 18:03:40', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (83, 1, 'kk-porn01', 'kk黄站1号', 100002, 100063, 0, 0, '2023-03-08 18:03:40', '2023-03-08 18:03:40', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (84, 1, 'kk-porn01', 'kk黄站1号', 100002, 100064, 0, 0, '2023-03-08 18:03:40', '2023-03-08 18:03:40', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (85, 1, 'kk-porn01', 'kk黄站1号', 100002, 100065, 0, 0, '2023-03-08 18:03:40', '2023-03-08 18:03:40', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (86, 1, 'kk-porn01', 'kk黄站1号', 100002, 100066, 0, 0, '2023-03-08 18:03:40', '2023-03-08 18:03:40', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (87, 1, 'kk-porn01', 'kk黄站1号', 100002, 100067, 0, 0, '2023-03-08 18:03:41', '2023-03-08 18:03:41', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (88, 1, 'kk-porn01', 'kk黄站1号', 100002, 100068, 0, 0, '2023-03-08 18:03:41', '2023-03-08 18:03:41', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (89, 1, 'kk-porn01', 'kk黄站1号', 100002, 100069, 0, 0, '2023-03-08 18:03:41', '2023-03-08 18:03:41', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (90, 1, 'kk-porn01', 'kk黄站1号', 100002, 100070, 0, 0, '2023-03-08 18:03:41', '2023-03-08 18:03:41', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (91, 1, 'kk-porn01', 'kk黄站1号', 100002, 100071, 0, 0, '2023-03-08 18:03:41', '2023-03-08 18:03:41', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (92, 1, 'kk-porn01', 'kk黄站1号', 100003, 100000, 0, 0, '2023-02-04 16:16:37', '2023-03-08 17:57:15', 'year', NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (93, 1, 'kk-porn01', 'kk黄站1号', 100003, 100001, 0, 0, '2023-02-04 16:16:37', '2023-03-08 17:57:16', 'year', NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (94, 1, 'kk-porn01', 'kk黄站1号', 100003, 100002, 0, 0, '2023-02-04 16:16:37', '2023-03-08 17:57:16', 'year', NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (95, 1, 'kk-porn01', 'kk黄站1号', 100003, 100003, 444, 0, '2023-02-04 16:16:37', '2023-03-08 17:57:16', 'year', NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (96, 1, 'kk-porn01', 'kk黄站1号', 100003, 100004, 555, 0, '2023-02-04 16:16:37', '2023-03-08 17:57:16', 'year', NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (97, 1, 'kk-porn01', 'kk黄站1号', 100003, 100005, 0, 0, '2023-02-04 16:16:37', '2023-03-08 17:57:15', 'year', NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (98, 1, 'kk-porn01', 'kk黄站1号', 100003, 100006, 0, 0, '2023-02-04 16:16:37', '2023-03-08 17:57:15', 'year', NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (99, 1, 'kk-porn01', 'kk黄站1号', 100003, 100007, 0, 0, '2023-02-04 16:16:37', '2023-03-08 17:57:16', 'year', NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (100, 1, 'kk-porn01', 'kk黄站1号', 100003, 100008, 0, 0, '2023-02-04 16:16:37', '2023-03-08 17:57:16', 'year', NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (101, 1, 'kk-porn01', 'kk黄站1号', 100003, 100009, 0, 0, '2023-02-04 16:16:37', '2023-03-08 17:57:16', 'year', NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (102, 1, 'kk-porn01', 'kk黄站1号', 100003, 100010, 0, 0, '2023-02-04 16:16:37', '2023-03-08 17:57:16', 'year', NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (103, 1, 'kk-porn01', 'kk黄站1号', 100003, 100011, 0, 0, '2023-02-04 16:16:37', '2023-03-08 17:57:16', 'year', NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (104, 1, 'kk-porn01', 'kk黄站1号', 100003, 100012, 0, 0, '2023-02-04 16:16:37', '2023-03-08 17:57:16', 'year', NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (105, 1, 'kk-porn01', 'kk黄站1号', 100003, 100013, 0, 0, '2023-03-03 19:39:57', '2023-03-08 17:57:15', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (106, 1, 'kk-porn01', 'kk黄站1号', 100003, 100014, 0, 0, '2023-03-03 19:40:05', '2023-03-08 17:57:15', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (107, 1, 'kk-porn01', 'kk黄站1号', 100003, 100015, 0, 0, '2023-03-03 19:40:19', '2023-03-08 17:57:15', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (108, 1, 'kk-porn01', 'kk黄站1号', 100003, 100016, 0, 0, '2023-03-08 17:54:04', '2023-03-08 17:57:15', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (109, 1, 'kk-porn01', 'kk黄站1号', 100003, 100017, 0, 0, '2023-03-08 17:54:04', '2023-03-08 17:57:15', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (110, 1, 'kk-porn01', 'kk黄站1号', 100003, 100018, 0, 0, '2023-03-08 17:54:04', '2023-03-08 17:57:15', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (111, 1, 'kk-porn01', 'kk黄站1号', 100003, 100019, 0, 0, '2023-03-08 17:54:04', '2023-03-08 17:57:15', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (112, 1, 'kk-porn01', 'kk黄站1号', 100003, 100020, 0, 0, '2023-03-08 17:54:04', '2023-03-08 17:57:15', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (113, 1, 'kk-porn01', 'kk黄站1号', 100004, 100305, 0, 0, '2023-03-08 19:45:36', '2023-03-08 19:45:36', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (114, 1, 'kk-porn01', 'kk黄站1号', 100004, 100306, 0, 0, '2023-03-08 19:45:36', '2023-03-08 19:45:36', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (115, 1, 'kk-porn01', 'kk黄站1号', 100004, 100307, 0, 0, '2023-03-08 19:45:36', '2023-03-08 19:45:36', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (116, 1, 'kk-porn01', 'kk黄站1号', 100004, 100308, 0, 0, '2023-03-08 19:45:36', '2023-03-08 19:45:36', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (117, 1, 'kk-porn01', 'kk黄站1号', 100004, 100309, 0, 0, '2023-03-08 19:45:36', '2023-03-08 19:45:36', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (118, 1, 'kk-porn01', 'kk黄站1号', 100004, 100304, 0, 0, '2023-03-08 19:45:36', '2023-03-08 19:45:36', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (119, 1, 'kk-porn01', 'kk黄站1号', 100004, 100303, 0, 0, '2023-03-08 19:45:36', '2023-03-08 19:45:36', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (120, 1, 'kk-porn01', 'kk黄站1号', 100004, 100302, 0, 0, '2023-03-08 19:45:36', '2023-03-08 19:45:36', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (121, 1, 'kk-porn01', 'kk黄站1号', 100005, 100100, 0, 0, '2023-03-08 19:47:12', '2023-03-08 19:47:12', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (122, 1, 'kk-porn01', 'kk黄站1号', 100005, 100101, 0, 0, '2023-03-08 19:47:12', '2023-03-08 19:47:12', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (123, 1, 'kk-porn01', 'kk黄站1号', 100005, 100102, 0, 0, '2023-03-08 19:47:12', '2023-03-08 19:47:12', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (124, 1, 'kk-porn01', 'kk黄站1号', 100005, 100103, 0, 0, '2023-03-08 19:47:12', '2023-03-08 19:47:12', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (125, 1, 'kk-porn01', 'kk黄站1号', 100005, 100104, 0, 0, '2023-03-08 19:47:12', '2023-03-08 19:47:12', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (126, 1, 'kk-porn01', 'kk黄站1号', 100005, 100105, 0, 0, '2023-03-08 19:47:12', '2023-03-08 19:47:12', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (127, 1, 'kk-porn01', 'kk黄站1号', 100005, 100106, 0, 0, '2023-03-08 19:47:12', '2023-03-08 19:47:12', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (128, 1, 'kk-porn01', 'kk黄站1号', 100005, 100107, 0, 0, '2023-03-08 19:47:12', '2023-03-08 19:47:12', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (129, 1, 'kk-porn01', 'kk黄站1号', 100005, 100108, 0, 0, '2023-03-08 19:47:12', '2023-03-08 19:47:12', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (130, 1, 'kk-porn01', 'kk黄站1号', 100005, 100109, 0, 0, '2023-03-08 19:47:12', '2023-03-08 19:47:12', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (131, 1, 'kk-porn01', 'kk黄站1号', 100005, 100110, 0, 0, '2023-03-08 19:47:12', '2023-03-08 19:47:12', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (132, 1, 'kk-porn01', 'kk黄站1号', 100006, 100111, 0, 0, '2023-03-08 19:48:15', '2023-03-08 19:48:15', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (133, 1, 'kk-porn01', 'kk黄站1号', 100006, 100112, 0, 0, '2023-03-08 19:48:15', '2023-03-08 19:48:15', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (134, 1, 'kk-porn01', 'kk黄站1号', 100006, 100113, 0, 0, '2023-03-08 19:48:15', '2023-03-08 19:48:15', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (135, 1, 'kk-porn01', 'kk黄站1号', 100006, 100114, 0, 0, '2023-03-08 19:48:15', '2023-03-08 19:48:15', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (136, 1, 'kk-porn01', 'kk黄站1号', 100006, 100115, 0, 0, '2023-03-08 19:48:16', '2023-03-08 19:48:16', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (137, 1, 'kk-porn01', 'kk黄站1号', 100006, 100116, 0, 0, '2023-03-08 19:48:16', '2023-03-08 19:48:16', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (138, 1, 'kk-porn01', 'kk黄站1号', 100006, 100117, 0, 0, '2023-03-08 19:48:16', '2023-03-08 19:48:16', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (139, 1, 'kk-porn01', 'kk黄站1号', 100006, 100118, 0, 0, '2023-03-08 19:48:16', '2023-03-08 19:48:16', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (140, 1, 'kk-porn01', 'kk黄站1号', 100006, 100119, 0, 0, '2023-03-08 19:48:16', '2023-03-08 19:48:16', NULL, NULL);
INSERT INTO `kpn_site_topic_movie` VALUES (141, 1, 'kk-porn01', 'kk黄站1号', 100006, 100120, 0, 0, '2023-03-08 19:48:16', '2023-03-08 19:48:16', NULL, NULL);

-- ----------------------------
-- Table structure for kpn_site_user_actor_favorites
-- ----------------------------
DROP TABLE IF EXISTS `kpn_site_user_actor_favorites`;
CREATE TABLE `kpn_site_user_actor_favorites`  (
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '会员id',
  `actor_id` bigint(20) NULL DEFAULT NULL COMMENT '演员id',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '收藏时间',
  UNIQUE INDEX `uni_userid_actorid`(`user_id`, `actor_id`) USING BTREE COMMENT '唯一',
  INDEX `idx_userid_actorid_createtime`(`user_id`, `create_time`, `actor_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '演员收藏表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kpn_site_user_actor_favorites
-- ----------------------------
INSERT INTO `kpn_site_user_actor_favorites` VALUES (2, 2, '2023-02-09 17:53:30');
INSERT INTO `kpn_site_user_actor_favorites` VALUES (94, 2, '2023-02-21 20:55:18');
INSERT INTO `kpn_site_user_actor_favorites` VALUES (94, 1, '2023-02-25 15:00:06');
INSERT INTO `kpn_site_user_actor_favorites` VALUES (94, 0, '2023-02-25 15:00:13');
INSERT INTO `kpn_site_user_actor_favorites` VALUES (136, 68, '2023-03-24 14:48:05');
INSERT INTO `kpn_site_user_actor_favorites` VALUES (136, 70, '2023-03-27 19:39:34');

-- ----------------------------
-- Table structure for kpn_site_user_channel
-- ----------------------------
DROP TABLE IF EXISTS `kpn_site_user_channel`;
CREATE TABLE `kpn_site_user_channel`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `site_id` int(11) NULL DEFAULT NULL COMMENT '站点id',
  `site_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点编码',
  `site_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点名称',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '会员id',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会员账号',
  `channel_id` bigint(20) NULL DEFAULT 0 COMMENT '频道id',
  `sort` bigint(20) NOT NULL DEFAULT 0 COMMENT '排序(越大越靠前)',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会员频道' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kpn_site_user_channel
-- ----------------------------
INSERT INTO `kpn_site_user_channel` VALUES (1, 1, NULL, NULL, 127, 'kk-porn01_1331111110', 100005, 1, '2023-03-09 00:27:04', '2023-03-11 18:11:33', NULL, NULL);
INSERT INTO `kpn_site_user_channel` VALUES (2, 1, NULL, NULL, 127, 'kk-porn01_1331111110', 100007, 0, '2023-03-11 18:11:34', '2023-03-11 18:11:34', NULL, NULL);
INSERT INTO `kpn_site_user_channel` VALUES (11, 1, NULL, NULL, 136, 'kk-porn01_qwe112233', 0, 35, '2023-03-22 22:34:21', '2023-03-23 16:16:45', NULL, NULL);
INSERT INTO `kpn_site_user_channel` VALUES (12, 1, NULL, NULL, 136, 'kk-porn01_qwe112233', 0, 34, '2023-03-22 22:34:22', '2023-03-23 16:16:45', NULL, NULL);
INSERT INTO `kpn_site_user_channel` VALUES (41, 1, NULL, NULL, 136, 'kk-porn01_qwe112233', 100015, 5, '2023-03-23 01:26:45', '2023-03-23 16:16:45', NULL, NULL);
INSERT INTO `kpn_site_user_channel` VALUES (44, 1, NULL, NULL, 136, 'kk-porn01_qwe112233', 100014, 2, '2023-03-23 01:26:47', '2023-03-23 16:16:45', NULL, NULL);
INSERT INTO `kpn_site_user_channel` VALUES (45, 1, NULL, NULL, 136, 'kk-porn01_qwe112233', 100016, 1, '2023-03-24 00:16:53', '2023-03-23 16:16:45', NULL, NULL);
INSERT INTO `kpn_site_user_channel` VALUES (46, 1, NULL, NULL, 136, 'kk-porn01_qwe112233', 100013, 0, '2023-03-24 00:16:53', '2023-03-24 00:16:53', NULL, NULL);

-- ----------------------------
-- Table structure for kpn_site_user_movie_favorites
-- ----------------------------
DROP TABLE IF EXISTS `kpn_site_user_movie_favorites`;
CREATE TABLE `kpn_site_user_movie_favorites`  (
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '会员id',
  `movie_id` bigint(20) NULL DEFAULT NULL COMMENT '影片id',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '收藏时间',
  UNIQUE INDEX `uni_userid_movieid`(`user_id`, `movie_id`) USING BTREE COMMENT '唯一',
  INDEX `idx_userid_createtime_movieid`(`user_id`, `create_time`, `movie_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会员影片收藏表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kpn_site_user_movie_favorites
-- ----------------------------
INSERT INTO `kpn_site_user_movie_favorites` VALUES (2, 10, '2023-02-09 20:39:11');
INSERT INTO `kpn_site_user_movie_favorites` VALUES (94, 10, '2023-02-09 20:41:26');
INSERT INTO `kpn_site_user_movie_favorites` VALUES (94, 1, '2023-02-10 20:41:26');
INSERT INTO `kpn_site_user_movie_favorites` VALUES (94, 2, '2023-02-10 20:45:26');
INSERT INTO `kpn_site_user_movie_favorites` VALUES (94, 3, '2023-02-11 20:45:26');
INSERT INTO `kpn_site_user_movie_favorites` VALUES (94, 5, '2023-02-12 20:45:26');
INSERT INTO `kpn_site_user_movie_favorites` VALUES (94, 4, '2023-02-13 20:45:26');
INSERT INTO `kpn_site_user_movie_favorites` VALUES (94, 7, '2023-02-14 20:45:26');
INSERT INTO `kpn_site_user_movie_favorites` VALUES (94, 8, '2023-02-15 20:45:26');
INSERT INTO `kpn_site_user_movie_favorites` VALUES (136, 100003, '2023-03-23 16:13:23');
INSERT INTO `kpn_site_user_movie_favorites` VALUES (136, 100019, '2023-03-23 16:15:30');
INSERT INTO `kpn_site_user_movie_favorites` VALUES (136, 100000, '2023-03-23 17:03:42');
INSERT INTO `kpn_site_user_movie_favorites` VALUES (136, 100061, '2023-03-24 14:48:07');
INSERT INTO `kpn_site_user_movie_favorites` VALUES (136, 100110, '2023-03-24 16:20:00');
INSERT INTO `kpn_site_user_movie_favorites` VALUES (136, 100005, '2023-03-27 13:57:01');
INSERT INTO `kpn_site_user_movie_favorites` VALUES (136, 100004, '2023-03-27 13:57:04');
INSERT INTO `kpn_site_user_movie_favorites` VALUES (136, 100302, '2023-03-27 13:57:08');
INSERT INTO `kpn_site_user_movie_favorites` VALUES (136, 100071, '2023-03-27 13:57:10');
INSERT INTO `kpn_site_user_movie_favorites` VALUES (136, 100303, '2023-03-27 13:57:16');
INSERT INTO `kpn_site_user_movie_favorites` VALUES (136, 100015, '2023-03-27 13:57:19');
INSERT INTO `kpn_site_user_movie_favorites` VALUES (136, 100016, '2023-03-27 13:57:42');
INSERT INTO `kpn_site_user_movie_favorites` VALUES (136, 100017, '2023-03-27 13:57:50');
INSERT INTO `kpn_site_user_movie_favorites` VALUES (136, 100018, '2023-03-27 13:57:53');
INSERT INTO `kpn_site_user_movie_favorites` VALUES (136, 100020, '2023-03-27 13:57:56');
INSERT INTO `kpn_site_user_movie_favorites` VALUES (136, 100309, '2023-03-27 13:58:05');
INSERT INTO `kpn_site_user_movie_favorites` VALUES (136, 100304, '2023-03-27 13:58:09');
INSERT INTO `kpn_site_user_movie_favorites` VALUES (136, 100108, '2023-03-27 13:58:20');
INSERT INTO `kpn_site_user_movie_favorites` VALUES (136, 100115, '2023-03-27 13:58:23');
INSERT INTO `kpn_site_user_movie_favorites` VALUES (136, 100107, '2023-03-27 13:58:26');
INSERT INTO `kpn_site_user_movie_favorites` VALUES (136, 100101, '2023-03-27 13:58:45');
INSERT INTO `kpn_site_user_movie_favorites` VALUES (136, 100002, '2023-03-27 14:42:35');

-- ----------------------------
-- Table structure for kpn_site_user_movie_history
-- ----------------------------
DROP TABLE IF EXISTS `kpn_site_user_movie_history`;
CREATE TABLE `kpn_site_user_movie_history`  (
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '会员id',
  `movie_id` bigint(20) NULL DEFAULT NULL COMMENT '影片id',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '收藏时间',
  UNIQUE INDEX `uni_userid_movieid`(`user_id`, `movie_id`) USING BTREE COMMENT '唯一',
  INDEX `idx_userid_createtime_movieid`(`user_id`, `create_time`, `movie_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会员收藏影片表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kpn_site_user_movie_history
-- ----------------------------
INSERT INTO `kpn_site_user_movie_history` VALUES (94, 10, '2023-02-09 20:41:26');
INSERT INTO `kpn_site_user_movie_history` VALUES (94, 1, '2023-02-10 20:41:26');
INSERT INTO `kpn_site_user_movie_history` VALUES (94, 2, '2023-02-10 20:45:26');
INSERT INTO `kpn_site_user_movie_history` VALUES (94, 3, '2023-02-11 20:45:26');
INSERT INTO `kpn_site_user_movie_history` VALUES (94, 5, '2023-02-12 20:45:26');
INSERT INTO `kpn_site_user_movie_history` VALUES (94, 4, '2023-02-13 20:45:26');
INSERT INTO `kpn_site_user_movie_history` VALUES (94, 7, '2023-02-14 20:45:26');
INSERT INTO `kpn_site_user_movie_history` VALUES (94, 8, '2023-02-15 20:45:26');
INSERT INTO `kpn_site_user_movie_history` VALUES (127, 100015, '2023-03-10 22:50:42');
INSERT INTO `kpn_site_user_movie_history` VALUES (127, 100001, '2023-03-10 22:53:15');
INSERT INTO `kpn_site_user_movie_history` VALUES (127, 100196, '2023-03-10 22:53:38');
INSERT INTO `kpn_site_user_movie_history` VALUES (127, 100061, '2023-03-11 16:50:00');
INSERT INTO `kpn_site_user_movie_history` VALUES (127, 100039, '2023-03-11 16:50:28');
INSERT INTO `kpn_site_user_movie_history` VALUES (127, 100021, '2023-03-11 16:51:04');
INSERT INTO `kpn_site_user_movie_history` VALUES (127, 100303, '2023-03-11 16:51:06');
INSERT INTO `kpn_site_user_movie_history` VALUES (127, 100302, '2023-03-11 16:51:13');
INSERT INTO `kpn_site_user_movie_history` VALUES (127, 100031, '2023-03-11 16:51:25');
INSERT INTO `kpn_site_user_movie_history` VALUES (127, 100000, '2023-03-11 21:41:17');
INSERT INTO `kpn_site_user_movie_history` VALUES (127, 100004, '2023-03-16 17:33:08');
INSERT INTO `kpn_site_user_movie_history` VALUES (127, 100005, '2023-03-16 22:09:52');
INSERT INTO `kpn_site_user_movie_history` VALUES (132, 100310, '2023-03-16 14:43:43');
INSERT INTO `kpn_site_user_movie_history` VALUES (132, 100004, '2023-03-16 14:44:15');
INSERT INTO `kpn_site_user_movie_history` VALUES (132, 100071, '2023-03-16 15:12:51');
INSERT INTO `kpn_site_user_movie_history` VALUES (132, 100000, '2023-03-16 15:30:24');
INSERT INTO `kpn_site_user_movie_history` VALUES (132, 100303, '2023-03-16 15:30:25');
INSERT INTO `kpn_site_user_movie_history` VALUES (132, 100005, '2023-03-16 15:30:27');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100196, '2023-03-16 20:44:46');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100006, '2023-03-16 20:54:55');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100033, '2023-03-16 21:18:46');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100010, '2023-03-23 14:49:57');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100112, '2023-03-23 16:11:44');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100049, '2023-03-23 20:21:35');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100029, '2023-03-23 20:23:31');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100038, '2023-03-23 20:23:47');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100025, '2023-03-23 20:38:46');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100158, '2023-03-23 22:01:29');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100076, '2023-03-23 22:06:33');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100068, '2023-03-23 23:18:15');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100105, '2023-03-24 00:11:51');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100039, '2023-03-24 04:21:35');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100060, '2023-03-24 04:21:43');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100030, '2023-03-24 04:22:29');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100008, '2023-03-24 06:07:11');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100014, '2023-03-24 14:09:05');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100057, '2023-03-24 14:34:23');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100061, '2023-03-24 14:48:03');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100047, '2023-03-24 22:27:47');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100036, '2023-03-25 19:24:01');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100040, '2023-03-25 22:14:08');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100291, '2023-03-27 13:40:38');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100016, '2023-03-27 13:57:41');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100020, '2023-03-27 13:57:52');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100108, '2023-03-27 13:58:16');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100115, '2023-03-27 13:58:18');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100018, '2023-03-27 14:41:19');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100051, '2023-03-27 17:19:21');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100185, '2023-03-27 19:27:35');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100062, '2023-03-27 19:38:08');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100121, '2023-03-27 19:39:37');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100302, '2023-03-27 19:46:07');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100075, '2023-03-27 19:46:24');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100044, '2023-03-27 19:46:57');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100055, '2023-03-27 19:47:04');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100309, '2023-03-27 19:47:08');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100107, '2023-03-27 19:47:18');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100303, '2023-03-27 19:47:44');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100013, '2023-03-27 19:50:48');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100005, '2023-03-27 19:53:16');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100101, '2023-03-27 20:12:50');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100059, '2023-03-27 20:16:13');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100176, '2023-03-27 21:20:40');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100054, '2023-03-27 21:23:03');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100000, '2023-03-27 21:35:33');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100001, '2023-03-27 21:57:54');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100071, '2023-03-27 22:03:29');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100031, '2023-03-27 22:04:37');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100304, '2023-03-28 13:45:40');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100110, '2023-03-28 13:45:42');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100109, '2023-03-28 13:52:46');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100070, '2023-03-28 14:00:52');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100063, '2023-03-28 14:01:00');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100002, '2023-03-28 15:26:52');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100003, '2023-03-28 15:31:52');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100015, '2023-03-28 15:31:54');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100004, '2023-03-28 15:37:21');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100019, '2023-03-28 16:15:29');
INSERT INTO `kpn_site_user_movie_history` VALUES (136, 100017, '2023-03-28 16:36:55');
INSERT INTO `kpn_site_user_movie_history` VALUES (142, 100304, '2023-03-26 17:32:51');
INSERT INTO `kpn_site_user_movie_history` VALUES (142, 100005, '2023-03-26 17:32:52');

-- ----------------------------
-- Table structure for kpn_site_user_order
-- ----------------------------
DROP TABLE IF EXISTS `kpn_site_user_order`;
CREATE TABLE `kpn_site_user_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `site_id` int(11) NULL DEFAULT NULL COMMENT '站点id',
  `site_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点编码',
  `site_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点名称',
  `order_no` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '充值订单',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '会员id',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会员账号',
  `bank_id` bigint(20) NULL DEFAULT NULL COMMENT '收款银行id',
  `bank_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收款银行',
  `bank_card_id` bigint(255) NULL DEFAULT NULL COMMENT '收款卡id',
  `bank_card` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收款卡号',
  `bank_card_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收款卡开户名',
  `remitter_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '汇款人姓名',
  `mobile` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `product_id` bigint(20) NULL DEFAULT NULL COMMENT '产品id',
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品名称',
  `product_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '货币价格',
  `certificate` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '银行交易号后6位',
  `remark` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `status` tinyint(255) NOT NULL DEFAULT 0 COMMENT '状态 0待审核,1审核通过,2审核拒绝',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_siteid_orderno`(`site_id`, `order_no`) USING BTREE COMMENT '唯一\r\n',
  INDEX `idx_userid_createtime`(`user_id`, `create_time`) USING BTREE COMMENT '客户端查询用户订单列表'
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '站点充值订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kpn_site_user_order
-- ----------------------------
INSERT INTO `kpn_site_user_order` VALUES (2, 11, 'code07', 'lulu07', '11111', 95, 'lulu01', 54344, '工商', 222, '12222', 'lulu01', NULL, '15048374736', 1, 'vip', 100.00, '345678', '通过', 0, '2023-02-21 16:13:28', '2023-02-25 15:16:08', 'admin', 'admin');
INSERT INTO `kpn_site_user_order` VALUES (3, 11, 'code07', 'lulu07', '22222', 95, 'lulu01', 54344, '工商', 222, '12222', 'lulu01', NULL, '18098763647', 1, 'vip', 100.00, '345678', '通过', 1, '2023-02-23 16:13:28', '2023-02-28 19:26:14', 'admin', 'admin');
INSERT INTO `kpn_site_user_order` VALUES (4, 1, 'kk-porn01', 'kk黄站1号', 'CE079993316241995170', 94, 'kk-porn01_year001', 1, '中国银行', 2, '1212121212121212', '周星驰', '草莓', NULL, 3, '季度VIP', 2.00, '789654', NULL, 0, '2023-02-24 23:11:39', '2023-02-27 21:00:30', NULL, NULL);
INSERT INTO `kpn_site_user_order` VALUES (5, 1, 'kk-porn01', 'kk黄站1号', 'CE079993316241995171', 94, 'kk-porn01_year001', 1, '中国银行', 2, '1212121212121212', '周星驰', '草莓', NULL, 2, '月度VIP', 1.00, '789655', NULL, 1, '2023-02-27 20:53:41', '2023-02-27 21:00:30', NULL, NULL);
INSERT INTO `kpn_site_user_order` VALUES (6, 1, 'kk-porn01', 'kk黄站1号', 'CE586537072993124821', 136, 'kk-porn01_qwe112233', 1, '招商银行', 1, '222222222222222', '周杰伦', '123大师傅', '2312312312', 2, '月度VIP', 1.00, '123123', NULL, 0, '2023-03-25 05:35:52', '2023-03-25 05:35:52', NULL, NULL);
INSERT INTO `kpn_site_user_order` VALUES (7, 1, 'kk-porn01', 'kk黄站1号', 'CE953602608216320112', 136, 'kk-porn01_qwe112233', 1, '招商银行', 1, '222222222222222', '周杰伦', '231fsd', ' 123123111223', 2, '月度VIP', 1.00, '312323', NULL, 0, '2023-03-25 05:36:41', '2023-03-25 05:36:41', NULL, NULL);
INSERT INTO `kpn_site_user_order` VALUES (8, 1, 'kk-porn01', 'kk黄站1号', 'CE126352847387544191', 136, 'kk-porn01_qwe112233', 1, '中国银行', 2, '1212121212121212', '周星驰', '发射点发生', '12312312312', 3, '季度VIP', 2.00, '123123', NULL, 0, '2023-03-25 05:38:31', '2023-03-25 05:38:31', NULL, NULL);
INSERT INTO `kpn_site_user_order` VALUES (9, 1, 'kk-porn01', 'kk黄站1号', 'CE557746219498624771', 136, 'kk-porn01_qwe112233', 1, '中国银行', 2, '1212121212121212', '周星驰', '2312 ', '123123', 3, '季度VIP', 2.00, '123123', NULL, 0, '2023-03-25 05:38:57', '2023-03-25 05:38:57', NULL, NULL);
INSERT INTO `kpn_site_user_order` VALUES (10, 1, 'kk-porn01', 'kk黄站1号', 'CE737101317038303241', 136, 'kk-porn01_qwe112233', 1, '招商银行', 3, '111111111111111', '周润发', '似懂非懂', '123123123', 2, '月度VIP', 1.00, '123123', NULL, 0, '2023-03-25 05:40:06', '2023-03-25 05:40:06', NULL, NULL);

-- ----------------------------
-- Table structure for kpn_site_user_vip_log
-- ----------------------------
DROP TABLE IF EXISTS `kpn_site_user_vip_log`;
CREATE TABLE `kpn_site_user_vip_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `site_id` int(11) NULL DEFAULT NULL COMMENT '站点id',
  `site_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点编码',
  `site_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点名称',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '会员id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会员账号',
  `type` tinyint(255) NULL DEFAULT NULL COMMENT '类型 1:签到,2:填写邀请码,3:推广,4:k币兑换,5:金额购买',
  `amount` decimal(18, 2) NULL DEFAULT NULL COMMENT 'k币数/金额',
  `currency_code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '金额货币',
  `before_expire` datetime NULL DEFAULT NULL COMMENT '原有vip到期日期(yyyy-MM-dd)',
  `days` int(255) NULL DEFAULT NULL COMMENT '获取vip天数',
  `after_expire` datetime NULL DEFAULT NULL COMMENT '变动后vip到期日期',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'vip变动日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kpn_site_user_vip_log
-- ----------------------------
INSERT INTO `kpn_site_user_vip_log` VALUES (35, 1, 'kk-porn01', 'kk黄站1号', 2, 'kk-porn01_year01', 3, NULL, NULL, NULL, 2, '2023-02-26 00:05:43', '推广会员id:94,获取vip天数:2', '2023-02-24 00:05:43', NULL);
INSERT INTO `kpn_site_user_vip_log` VALUES (37, 1, 'kk-porn01', 'kk黄站1号', 100, 'kk-porn01_year002', 2, NULL, NULL, NULL, 1, '2023-02-25 00:10:14', '填写邀请码:abcedf,获取vip天数:1', '2023-02-24 00:10:13', NULL);
INSERT INTO `kpn_site_user_vip_log` VALUES (38, 1, 'kk-porn01', 'kk黄站1号', 2, 'kk-porn01_year01', 3, NULL, NULL, '2023-02-26 00:05:43', 2, '2023-02-28 00:05:43', '推广会员id:100,获取vip天数:2', '2023-02-24 00:10:15', NULL);
INSERT INTO `kpn_site_user_vip_log` VALUES (39, 1, 'kk-porn01', 'kk黄站1号', 102, 'kk-porn01_year004', 2, NULL, NULL, NULL, 1, '2023-02-25 00:13:52', '填写邀请码:DRlLPf,获取vip天数:1', '2023-02-24 00:13:52', NULL);
INSERT INTO `kpn_site_user_vip_log` VALUES (41, 1, 'kk-porn01', 'kk黄站1号', 100, 'kk-porn01_year002', 1, NULL, NULL, '2023-02-25 00:10:14', 1, '2023-02-26 00:10:14', '签到累计达到:1天,获取vip天数:1', '2023-02-24 00:15:33', NULL);
INSERT INTO `kpn_site_user_vip_log` VALUES (42, 1, 'kk-porn01', 'kk黄站1号', 103, 'kk-porn01_year005', 2, NULL, NULL, NULL, 1, '2023-02-25 00:22:31', '填写邀请码:drBfmW,获取vip天数:1', '2023-02-24 00:22:31', NULL);
INSERT INTO `kpn_site_user_vip_log` VALUES (43, 1, 'kk-porn01', 'kk黄站1号', 100, 'kk-porn01_year002', 3, NULL, NULL, '2023-02-26 00:10:14', 2, '2023-02-28 00:10:14', '推广会员id:103,获取vip天数:2', '2023-02-24 00:22:31', NULL);
INSERT INTO `kpn_site_user_vip_log` VALUES (44, 1, 'kk-porn01', 'kk黄站1号', 94, 'kk-porn01_year001', 4, NULL, NULL, NULL, 30, '2023-03-26 17:21:24', '消费K币:100000.00,获取vip天数:30', '2023-02-24 17:21:24', NULL);
INSERT INTO `kpn_site_user_vip_log` VALUES (45, 1, 'kk-porn01', 'kk黄站1号', 104, 'kk-porn01_year006', 2, NULL, NULL, NULL, 1, '2023-02-25 17:25:43', '填写邀请码:DRlLPf,获取vip天数:1', '2023-02-24 17:25:42', NULL);
INSERT INTO `kpn_site_user_vip_log` VALUES (46, 1, 'kk-porn01', 'kk黄站1号', 94, 'kk-porn01_year001', 3, NULL, NULL, '2023-03-26 17:21:21', 2, '2023-03-28 17:21:21', '推广会员id:104,获取vip天数:2', '2023-02-24 17:25:43', NULL);
INSERT INTO `kpn_site_user_vip_log` VALUES (47, 1, 'kk-porn01', 'kk黄站1号', 94, 'kk-porn01_year001', 4, NULL, NULL, '2023-03-28 17:21:21', 90, '2023-06-26 17:21:21', '消费K币:200000.00,获取vip天数:90', '2023-02-24 17:28:25', NULL);
INSERT INTO `kpn_site_user_vip_log` VALUES (49, 11, 'code07', 'lulu07', 95, 'lulu01', 5, 100.00, 'CNY', '2023-02-25 14:44:57', 365, '2024-02-25 14:44:57', '消费金额:100.00,币种:CNY,获取vip天数:365', '2023-02-25 15:44:56', NULL);
INSERT INTO `kpn_site_user_vip_log` VALUES (50, 1, 'kk-porn01', 'kk黄站1号', 111, 'kk-porn01_year008', 2, NULL, NULL, NULL, 1, '2023-03-01 15:41:29', '填写邀请码:DRlLPf,获取vip天数:1', '2023-02-28 15:41:31', NULL);
INSERT INTO `kpn_site_user_vip_log` VALUES (51, 1, 'kk-porn01', 'kk黄站1号', 94, 'kk-porn01_year001', 3, NULL, NULL, '2023-06-26 17:21:21', 2, '2023-06-28 17:21:21', '推广会员id:111,获取vip天数:2', '2023-02-28 15:41:34', NULL);
INSERT INTO `kpn_site_user_vip_log` VALUES (52, 1, 'kk-porn01', 'kk黄站1号', 2, 'kk-porn01_year01', 4, NULL, NULL, NULL, 90, '2023-05-29 16:07:11', '消费K币:200000.00,获取vip天数:90', '2023-02-28 16:07:11', NULL);
INSERT INTO `kpn_site_user_vip_log` VALUES (53, 1, 'kk-porn01', 'kk黄站1号', 2, 'kk-porn01_year01', 4, NULL, NULL, NULL, 90, '2023-05-29 16:16:34', '消费K币:200000.00,获取vip天数:90', '2023-02-28 16:16:39', NULL);
INSERT INTO `kpn_site_user_vip_log` VALUES (54, 1, 'kk-porn01', 'kk黄站1号', 2, 'kk-porn01_year01', 4, NULL, NULL, '2023-05-29 16:16:18', 90, '2023-08-27 16:16:18', '消费K币:200000.00,获取vip天数:90', '2023-02-28 16:18:09', NULL);
INSERT INTO `kpn_site_user_vip_log` VALUES (55, 1, 'kk-porn01', 'kk黄站1号', 2, 'kk-porn01_year01', 4, NULL, NULL, NULL, 90, '2023-05-29 16:18:41', '消费K币:200000.00,获取vip天数:90', '2023-02-28 16:18:41', NULL);
INSERT INTO `kpn_site_user_vip_log` VALUES (56, 11, 'code07', 'lulu07', 95, 'lulu01', 5, 100.00, 'CNY', '2024-02-25 14:44:57', 365, '2025-02-24 14:44:57', '消费金额:100.00,币种:CNY,获取vip天数:365', '2023-02-28 20:25:58', NULL);
INSERT INTO `kpn_site_user_vip_log` VALUES (57, 11, 'code07', 'lulu07', 95, 'lulu01', 5, 100.00, 'CNY', '2025-02-24 14:44:57', 365, '2026-02-24 14:44:57', '消费金额:100.00,币种:CNY,获取vip天数:365', '2023-02-28 20:26:13', NULL);
INSERT INTO `kpn_site_user_vip_log` VALUES (58, 1, 'kk-porn01', 'kk黄站1号', 140, 'kk-porn01_qwe11233', 2, NULL, NULL, NULL, 1, '2023-03-26 17:49:45', '填写邀请码:xOusz6,获取vip天数:1', '2023-03-25 17:49:48', NULL);
INSERT INTO `kpn_site_user_vip_log` VALUES (59, 1, 'kk-porn01', 'kk黄站1号', 136, 'kk-porn01_qwe112233', 3, NULL, NULL, NULL, 2, '2023-03-27 17:49:45', '推广会员id:140,获取vip天数:2', '2023-03-25 17:49:48', NULL);
INSERT INTO `kpn_site_user_vip_log` VALUES (60, 1, 'kk-porn01', 'kk黄站1号', 136, 'kk-porn01_qwe112233', 1, NULL, NULL, '2023-03-27 17:49:45', 1, '2023-03-28 17:49:45', '签到累计达到:1天,获取vip天数:1', '2023-03-25 20:49:47', NULL);
INSERT INTO `kpn_site_user_vip_log` VALUES (61, 1, 'kk-porn01', 'kk黄站1号', 141, 'kk-porn01_qwe1122333', 1, NULL, NULL, NULL, 1, '2023-03-27 17:02:55', '签到累计达到:1天,获取vip天数:1', '2023-03-26 17:02:58', NULL);
INSERT INTO `kpn_site_user_vip_log` VALUES (62, 1, 'kk-porn01', 'kk黄站1号', 142, 'kk-porn01_chezai01', 1, NULL, NULL, NULL, 1, '2023-03-27 17:11:28', '签到累计达到:1天,获取vip天数:1', '2023-03-26 17:11:31', NULL);
INSERT INTO `kpn_site_user_vip_log` VALUES (63, 1, 'kk-porn01', 'kk黄站1号', 136, 'kk-porn01_qwe112233', 1, NULL, NULL, '2023-03-28 17:49:45', 2, '2023-03-30 17:49:45', '签到累计达到:3天,获取vip天数:2', '2023-03-27 12:25:19', NULL);
INSERT INTO `kpn_site_user_vip_log` VALUES (64, 1, 'kk-porn01', 'kk黄站1号', 143, 'kk-porn01_qwe112333', 2, NULL, NULL, NULL, 1, '2023-03-28 20:07:59', '填写邀请码:xOusz6,获取vip天数:1', '2023-03-27 20:08:02', NULL);
INSERT INTO `kpn_site_user_vip_log` VALUES (65, 1, 'kk-porn01', 'kk黄站1号', 136, 'kk-porn01_qwe112233', 3, NULL, NULL, '2023-03-30 17:49:45', 2, '2023-04-01 17:49:45', '推广会员id:143,获取vip天数:2', '2023-03-27 20:08:02', NULL);

-- ----------------------------
-- Table structure for kpn_tag
-- ----------------------------
DROP TABLE IF EXISTS `kpn_tag`;
CREATE TABLE `kpn_tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_id` bigint(20) NULL DEFAULT NULL COMMENT '标签分类id',
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签分类名称',
  `name_zh` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签名称(中文)',
  `name_en` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签名称(英文)',
  `name_kh` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签名称(柬文)',
  `vv` bigint(255) NOT NULL DEFAULT 0 COMMENT '标签播放量',
  `remark` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `tag_id_index`(`id`, `category_id`) USING BTREE,
  INDEX `tag_normal`(`category_id`, `name_zh`, `name_en`, `name_kh`) USING BTREE,
  FULLTEXT INDEX `tag_name_fulltext`(`name_zh`, `name_en`, `name_kh`)
) ENGINE = InnoDB AUTO_INCREMENT = 100021 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '影片标签' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kpn_tag
-- ----------------------------
INSERT INTO `kpn_tag` VALUES (100000, 100000, '标签分类-1', '标签1(zh)', '标签1(en)', '标签1(kh)', 0, NULL, '2023-03-08 14:39:34', '2023-03-08 14:39:34', NULL, NULL);
INSERT INTO `kpn_tag` VALUES (100001, 100000, '标签分类-1', '标签2(zh)', '标签2(en)', '标签2(kh)', 0, NULL, '2023-03-08 14:40:56', '2023-03-08 14:40:56', NULL, NULL);
INSERT INTO `kpn_tag` VALUES (100002, 100000, '标签分类-1', '标签3(zh)', '标签3(en)', '标签3(kh)', 0, NULL, '2023-03-08 14:40:56', '2023-03-08 14:40:56', NULL, NULL);
INSERT INTO `kpn_tag` VALUES (100003, 100000, '标签分类-1', '标签4(zh)', '标签4(en)', '标签4(kh)', 0, NULL, '2023-03-08 14:40:56', '2023-03-08 14:40:56', NULL, NULL);
INSERT INTO `kpn_tag` VALUES (100004, 100000, '标签分类-1', '标签5(zh)', '标签5(en)', '标签5(kh)', 0, NULL, '2023-03-08 14:40:56', '2023-03-08 14:40:56', NULL, NULL);
INSERT INTO `kpn_tag` VALUES (100005, 100001, '标签分类-2', '标签6(zh)', '标签6(en)', '标签6(kh)', 0, NULL, '2023-03-08 14:42:00', '2023-03-08 14:42:00', NULL, NULL);
INSERT INTO `kpn_tag` VALUES (100006, 100001, '标签分类-2', '标签7(zh)', '标签7(en)', '标签7(kh)', 0, NULL, '2023-03-08 14:42:00', '2023-03-08 14:42:00', NULL, NULL);
INSERT INTO `kpn_tag` VALUES (100007, 100001, '标签分类-2', '标签8(zh)', '标签8(en)', '标签8(kh)', 0, NULL, '2023-03-08 14:42:00', '2023-03-08 14:42:00', NULL, NULL);
INSERT INTO `kpn_tag` VALUES (100008, 100002, '标签分类-3', '标签9(zh)', '标签9(en)', '标签9(kh)', 0, NULL, '2023-03-08 14:42:43', '2023-03-08 14:42:43', NULL, NULL);
INSERT INTO `kpn_tag` VALUES (100009, 100002, '标签分类-3', '标签10(zh)', '标签10(en)', '标签10(kh)', 0, NULL, '2023-03-08 14:42:43', '2023-03-08 14:42:43', NULL, NULL);
INSERT INTO `kpn_tag` VALUES (100010, 100003, '标签分类-4', '标签11(zh)', '标签11(en)', '标签11(kh)', 0, NULL, '2023-03-08 14:43:32', '2023-03-08 14:43:32', NULL, NULL);
INSERT INTO `kpn_tag` VALUES (100011, 100003, '标签分类-4', '标签12(zh)', '标签12(en)', '标签12(kh)', 0, NULL, '2023-03-08 14:43:32', '2023-03-08 14:43:32', NULL, NULL);
INSERT INTO `kpn_tag` VALUES (100012, 100004, '标签分类-5', '标签13(zh)', '标签13(en)', '标签13(kh)', 0, NULL, '2023-03-08 14:44:07', '2023-03-08 14:44:07', NULL, NULL);
INSERT INTO `kpn_tag` VALUES (100013, 100004, '标签分类-5', '标签14(zh)', '标签14(en)', '标签14(kh)', 0, NULL, '2023-03-08 14:44:07', '2023-03-08 14:44:07', NULL, NULL);
INSERT INTO `kpn_tag` VALUES (100014, 100005, '标签分类-6', '标签15(zh)', '标签15(en)', '标签15(kh)', 0, NULL, '2023-03-08 14:44:50', '2023-03-08 14:44:50', NULL, NULL);
INSERT INTO `kpn_tag` VALUES (100015, 100005, '标签分类-6', '标签16(zh)', '标签16(en)', '标签16(kh)', 0, NULL, '2023-03-08 14:44:50', '2023-03-08 14:44:50', NULL, NULL);
INSERT INTO `kpn_tag` VALUES (100016, 100006, '标签分类-7', '标签17(zh)', '标签17(en)', '标签17(kh)', 0, NULL, '2023-03-08 14:45:29', '2023-03-08 14:45:29', NULL, NULL);
INSERT INTO `kpn_tag` VALUES (100017, 100006, '标签分类-7', '标签18(zh)', '标签18(en)', '标签18(kh)', 0, NULL, '2023-03-08 14:45:29', '2023-03-08 14:45:29', NULL, NULL);
INSERT INTO `kpn_tag` VALUES (100018, 100007, '标签分类-8', '标签19(zh)', '标签19(en)', '标签19(kh)', 0, NULL, '2023-03-08 14:45:52', '2023-03-08 14:45:52', NULL, NULL);
INSERT INTO `kpn_tag` VALUES (100019, 100008, '标签分类-9', '标签20(zh)', '标签20(en)', '标签20(kh)', 0, NULL, '2023-03-08 14:46:14', '2023-03-08 14:46:14', NULL, NULL);
INSERT INTO `kpn_tag` VALUES (100020, 100009, '标签分类-10', '标签21(zh)', '标签21(en)', '标签21(kh)', 0, NULL, '2023-03-08 14:46:49', '2023-03-08 14:46:49', NULL, NULL);

-- ----------------------------
-- Table structure for kpn_tag_category
-- ----------------------------
DROP TABLE IF EXISTS `kpn_tag_category`;
CREATE TABLE `kpn_tag_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `remark` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `tag_category_id_index`(`id`) USING BTREE,
  FULLTEXT INDEX `tag_category_name`(`name`)
) ENGINE = InnoDB AUTO_INCREMENT = 100010 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '影片标签分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kpn_tag_category
-- ----------------------------
INSERT INTO `kpn_tag_category` VALUES (100000, '标签分类-1', NULL, '2023-03-08 14:35:47', '2023-03-08 14:35:47', NULL, NULL);
INSERT INTO `kpn_tag_category` VALUES (100001, '标签分类-2', NULL, '2023-03-08 14:35:54', '2023-03-08 14:35:54', NULL, NULL);
INSERT INTO `kpn_tag_category` VALUES (100002, '标签分类-3', NULL, '2023-03-08 14:36:04', '2023-03-08 14:36:04', NULL, NULL);
INSERT INTO `kpn_tag_category` VALUES (100003, '标签分类-4', NULL, '2023-03-08 14:36:04', '2023-03-08 14:36:04', NULL, NULL);
INSERT INTO `kpn_tag_category` VALUES (100004, '标签分类-5', NULL, '2023-03-08 14:38:30', '2023-03-08 14:38:30', NULL, NULL);
INSERT INTO `kpn_tag_category` VALUES (100005, '标签分类-6', NULL, '2023-03-08 14:38:30', '2023-03-08 14:38:30', NULL, NULL);
INSERT INTO `kpn_tag_category` VALUES (100006, '标签分类-7', NULL, '2023-03-08 14:38:30', '2023-03-08 14:38:30', NULL, NULL);
INSERT INTO `kpn_tag_category` VALUES (100007, '标签分类-8', NULL, '2023-03-08 14:38:30', '2023-03-08 14:38:30', NULL, NULL);
INSERT INTO `kpn_tag_category` VALUES (100008, '标签分类-9', NULL, '2023-03-08 14:38:30', '2023-03-08 14:38:30', NULL, NULL);
INSERT INTO `kpn_tag_category` VALUES (100009, '标签分类-10', NULL, '2023-03-08 14:38:30', '2023-03-08 14:38:30', NULL, NULL);

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `client_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用标识',
  `resource_ids` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源限定串(逗号分割)',
  `client_secret` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用密钥(bcyt) 加密',
  `client_secret_str` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用密钥(明文)',
  `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '范围',
  `authorized_grant_types` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '5种oauth授权方式(authorization_code,password,refresh_token,client_credentials)',
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回调地址 ',
  `authorities` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限',
  `access_token_validity` int(11) NULL DEFAULT NULL COMMENT 'access_token有效期',
  `refresh_token_validity` int(11) NULL DEFAULT NULL COMMENT 'refresh_token有效期',
  `additional_information` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '{}' COMMENT '{}',
  `autoapprove` char(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'true' COMMENT '是否自动授权 是-true',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `client_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '应用名称',
  `support_id_token` tinyint(1) NULL DEFAULT 1 COMMENT '是否支持id_token',
  `id_token_validity` int(11) NULL DEFAULT 60 COMMENT 'id_token有效期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES (1, 'webApp', NULL, '$2a$10$06msMGYRH8nrm4iVnKFNKOoddB8wOwymVhbUzw/d3ZixD7Nq8ot72', 'webApp', 'app', 'authorization_code,password,refresh_token,client_credentials,implicit,password_code,openId,mobile_password,password_google,guest', NULL, NULL, 3600, NULL, '{\"LOGOUT_NOTIFY_URL_LIST\":\"http://127.0.0.1:8082/logoutNotify\"}', 'true', NULL, NULL, 'pc端', 1, 60);
INSERT INTO `oauth_client_details` VALUES (2, 'app', NULL, '$2a$10$i3F515wEDiB4Gvj9ym9Prui0dasRttEUQ9ink4Wpgb4zEDCAlV8zO', 'app', 'app', 'authorization_code,password,refresh_token', 'http://127.0.0.1:8081/callback.html', NULL, 43200, NULL, '{\"LOGOUT_NOTIFY_URL_LIST\":\"http://127.0.0.1:8081/logoutNotify\"}', 'true', NULL, NULL, '移动端', 1, 60);
INSERT INTO `oauth_client_details` VALUES (3, 'zlt', '', '$2a$10$/o.wuORzVcXaezmYVzwYMuoY7qeWXBALwQmkskXD/7C6rqfCyPrna', 'zlt', 'all', 'authorization_code,password,refresh_token,client_credentials', 'http://127.0.0.1:8080/singleLogin', NULL, 43200, 28800, '{}', 'true', '2018-12-27 00:50:30', '2018-12-27 00:50:30', '第三方应用', 1, 60);

-- ----------------------------
-- Table structure for rpt_site_movie_date
-- ----------------------------
DROP TABLE IF EXISTS `rpt_site_movie_date`;
CREATE TABLE `rpt_site_movie_date`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `site_id` bigint(20) NULL DEFAULT NULL COMMENT '站点id',
  `movie_id` bigint(20) NULL DEFAULT NULL COMMENT '影片id',
  `date` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '日期yyyy-MM-dd',
  `vv` bigint(255) NOT NULL DEFAULT 0 COMMENT '播放量',
  `favorites` bigint(255) NOT NULL DEFAULT 0 COMMENT '影片收藏量',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_siteid_movieid_date`(`site_id`, `movie_id`, `date`) USING BTREE COMMENT '唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1754 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '站点影片播放量/收藏量日报表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rpt_site_movie_date
-- ----------------------------
INSERT INTO `rpt_site_movie_date` VALUES (1, 1, 10, '2023-02-12', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (3, 1, 1, '2023-02-12', 5, 0);
INSERT INTO `rpt_site_movie_date` VALUES (8, 1, 5, '2023-02-12', 15, 0);
INSERT INTO `rpt_site_movie_date` VALUES (23, 1, 5, '2023-02-15', 10, 0);
INSERT INTO `rpt_site_movie_date` VALUES (33, 1, 10, '2023-02-15', 11, 0);
INSERT INTO `rpt_site_movie_date` VALUES (44, 1, 10, '2023-02-27', 5, 0);
INSERT INTO `rpt_site_movie_date` VALUES (48, 1, 3, '2023-02-27', 5, 0);
INSERT INTO `rpt_site_movie_date` VALUES (54, 1, 100304, '2023-03-08', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (55, 1, 100015, '2023-03-08', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (56, 1, 100196, '2023-03-08', 76, 0);
INSERT INTO `rpt_site_movie_date` VALUES (57, 1, NULL, '2023-03-08', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (58, 1, NULL, '2023-03-08', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (59, 1, NULL, '2023-03-08', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (60, 1, NULL, '2023-03-08', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (61, 1, 2, '2023-03-08', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (79, 1, NULL, '2023-03-08', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (80, 1, NULL, '2023-03-08', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (81, 1, NULL, '2023-03-08', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (82, 1, NULL, '2023-03-08', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (83, 1, NULL, '2023-03-08', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (84, 1, 100005, '2023-03-08', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (85, 1, 199443937, '2023-03-08', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (140, 1, 100031, '2023-03-08', 30, 0);
INSERT INTO `rpt_site_movie_date` VALUES (175, 1, 100196, '2023-03-09', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (176, 1, 100005, '2023-03-09', 7, 0);
INSERT INTO `rpt_site_movie_date` VALUES (183, 1, 100293, '2023-03-10', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (184, 1, 100001, '2023-03-10', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (185, 1, 100021, '2023-03-10', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (186, 1, 100007, '2023-03-10', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (187, 1, 100009, '2023-03-10', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (188, 1, 100005, '2023-03-10', 19, 0);
INSERT INTO `rpt_site_movie_date` VALUES (207, 1, 100000, '2023-03-10', 6, 0);
INSERT INTO `rpt_site_movie_date` VALUES (213, 1, 100282, '2023-03-10', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (214, 1, 100015, '2023-03-10', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (216, 1, 100031, '2023-03-10', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (217, 1, 100196, '2023-03-10', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (219, 1, 100005, '2023-03-12', 3, 0);
INSERT INTO `rpt_site_movie_date` VALUES (222, 1, 100196, '2023-03-12', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (223, 1, 100021, '2023-03-12', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (224, 1, 100309, '2023-03-12', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (225, 1, 100310, '2023-03-12', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (226, 1, 100303, '2023-03-12', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (227, 1, 100061, '2023-03-12', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (229, 1, 100004, '2023-03-12', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (230, 1, 100004, '2023-03-11', 9, 0);
INSERT INTO `rpt_site_movie_date` VALUES (231, 1, 100061, '2023-03-11', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (233, 1, 100039, '2023-03-11', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (236, 1, 100303, '2023-03-11', 3, 0);
INSERT INTO `rpt_site_movie_date` VALUES (237, 1, 100021, '2023-03-11', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (240, 1, 100302, '2023-03-11', 3, 0);
INSERT INTO `rpt_site_movie_date` VALUES (241, 1, 100031, '2023-03-11', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (242, 1, 100015, '2023-03-11', 3, 0);
INSERT INTO `rpt_site_movie_date` VALUES (245, 1, 100310, '2023-03-11', 3, 0);
INSERT INTO `rpt_site_movie_date` VALUES (247, 1, 100003, '2023-03-11', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (254, 1, 100304, '2023-03-11', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (257, 1, 100000, '2023-03-11', 3, 0);
INSERT INTO `rpt_site_movie_date` VALUES (260, 1, 100003, '2023-03-13', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (261, 1, 100004, '2023-03-13', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (262, 1, 100304, '2023-03-13', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (265, 1, 100011, '2023-03-13', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (266, 1, 100004, '2023-03-14', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (267, 1, 100302, '2023-03-15', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (268, 1, 100005, '2023-03-15', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (269, 1, 100005, '2023-03-16', 44, 0);
INSERT INTO `rpt_site_movie_date` VALUES (271, 1, 100000, '2023-03-16', 12, 0);
INSERT INTO `rpt_site_movie_date` VALUES (272, 1, 100015, '2023-03-16', 5, 0);
INSERT INTO `rpt_site_movie_date` VALUES (276, 1, 100004, '2023-03-16', 19, 0);
INSERT INTO `rpt_site_movie_date` VALUES (280, 1, 100309, '2023-03-16', 7, 0);
INSERT INTO `rpt_site_movie_date` VALUES (302, 1, 100310, '2023-03-16', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (306, 1, 100071, '2023-03-16', 6, 0);
INSERT INTO `rpt_site_movie_date` VALUES (309, 1, 100303, '2023-03-16', 4, 0);
INSERT INTO `rpt_site_movie_date` VALUES (334, 1, 100003, '2023-03-16', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (336, 1, 100302, '2023-03-16', 6, 0);
INSERT INTO `rpt_site_movie_date` VALUES (341, 1, 100304, '2023-03-16', 4, 0);
INSERT INTO `rpt_site_movie_date` VALUES (354, 1, 100110, '2023-03-16', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (355, 1, 100108, '2023-03-16', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (356, 1, 100115, '2023-03-16', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (367, 1, 100196, '2023-03-16', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (368, 1, 100006, '2023-03-16', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (369, 1, 100002, '2023-03-16', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (373, 1, 100070, '2023-03-16', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (377, 1, 100033, '2023-03-16', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (384, 1, 100021, '2023-03-16', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (390, 1, 100308, '2023-03-16', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (393, 1, 100301, '2023-03-17', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (394, 1, 100309, '2023-03-17', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (396, 1, 100004, '2023-03-17', 3, 0);
INSERT INTO `rpt_site_movie_date` VALUES (398, 1, 100308, '2023-03-17', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (400, 1, 100071, '2023-03-17', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (401, 1, 100307, '2023-03-17', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (402, 1, 100280, '2023-03-17', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (403, 1, 100046, '2023-03-17', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (405, 1, 100303, '2023-03-17', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (407, 1, 100308, '2023-03-18', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (408, 1, 100005, '2023-03-18', 4, 0);
INSERT INTO `rpt_site_movie_date` VALUES (412, 1, 100004, '2023-03-18', 9, 0);
INSERT INTO `rpt_site_movie_date` VALUES (416, 1, 100288, '2023-03-18', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (417, 1, 100017, '2023-03-18', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (424, 1, 100002, '2023-03-18', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (425, 1, 100302, '2023-03-20', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (426, 1, 100005, '2023-03-20', 4, 0);
INSERT INTO `rpt_site_movie_date` VALUES (431, 1, 100004, '2023-03-20', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (432, 1, 100003, '2023-03-20', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (433, 1, 100003, '2023-03-21', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (434, 1, 100303, '2023-03-21', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (435, 1, 100005, '2023-03-21', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (437, 1, 100196, '2023-03-22', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (438, 1, 100071, '2023-03-22', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (439, 1, 100004, '2023-03-22', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (440, 1, 100005, '2023-03-22', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (441, 1, 100309, '2023-03-22', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (442, 1, 100302, '2023-03-22', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (445, 1, 100004, '2023-03-23', 133, 0);
INSERT INTO `rpt_site_movie_date` VALUES (446, 1, 100005, '2023-03-23', 104, 0);
INSERT INTO `rpt_site_movie_date` VALUES (462, 1, 100003, '2023-03-23', 112, 0);
INSERT INTO `rpt_site_movie_date` VALUES (465, 1, 100002, '2023-03-23', 6, 0);
INSERT INTO `rpt_site_movie_date` VALUES (488, 1, 100110, '2023-03-23', 55, 0);
INSERT INTO `rpt_site_movie_date` VALUES (489, 1, 100302, '2023-03-23', 104, 0);
INSERT INTO `rpt_site_movie_date` VALUES (526, 1, 100019, '2023-03-23', 81, 0);
INSERT INTO `rpt_site_movie_date` VALUES (542, 1, 100071, '2023-03-23', 98, 0);
INSERT INTO `rpt_site_movie_date` VALUES (554, 1, 100304, '2023-03-23', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (590, 1, 100309, '2023-03-23', 43, 0);
INSERT INTO `rpt_site_movie_date` VALUES (601, 1, 100017, '2023-03-23', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (602, 1, 100015, '2023-03-23', 38, 0);
INSERT INTO `rpt_site_movie_date` VALUES (606, 1, 100108, '2023-03-23', 4, 0);
INSERT INTO `rpt_site_movie_date` VALUES (608, 1, 100112, '2023-03-23', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (623, 1, 100070, '2023-03-23', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (656, 1, 100020, '2023-03-23', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (704, 1, 100000, '2023-03-23', 72, 0);
INSERT INTO `rpt_site_movie_date` VALUES (722, 1, 100303, '2023-03-23', 23, 0);
INSERT INTO `rpt_site_movie_date` VALUES (984, 1, 100001, '2023-03-23', 3, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1087, 1, 100010, '2023-03-23', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1147, 1, 100013, '2023-03-23', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1236, 1, 100068, '2023-03-23', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1302, 1, 1, '2023-03-23', 6, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1341, 1, 100110, '2023-03-24', 19, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1343, 1, 100002, '2023-03-24', 14, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1345, 1, 100105, '2023-03-24', 3, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1351, 1, 100108, '2023-03-24', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1354, 1, 100112, '2023-03-24', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1355, 1, 100115, '2023-03-24', 3, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1358, 1, 100003, '2023-03-24', 13, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1359, 1, 100000, '2023-03-24', 40, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1362, 1, 100019, '2023-03-24', 6, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1365, 1, 100071, '2023-03-24', 4, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1366, 1, 100070, '2023-03-24', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1370, 1, 100309, '2023-03-24', 3, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1371, 1, 100303, '2023-03-24', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1372, 1, 100015, '2023-03-24', 8, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1374, 1, 100005, '2023-03-24', 15, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1375, 1, 100004, '2023-03-24', 21, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1414, 1, 100039, '2023-03-24', 6, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1419, 1, 100001, '2023-03-24', 12, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1444, 1, 100030, '2023-03-24', 5, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1447, 1, 100060, '2023-03-24', 4, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1448, 1, 100047, '2023-03-24', 4, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1450, 1, 100049, '2023-03-24', 4, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1451, 1, 100055, '2023-03-24', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1453, 1, 100029, '2023-03-24', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1454, 1, 100038, '2023-03-24', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1455, 1, 100025, '2023-03-24', 5, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1458, 1, 100018, '2023-03-24', 4, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1463, 1, 100008, '2023-03-24', 8, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1472, 1, 100171, '2023-03-24', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1479, 1, 100158, '2023-03-24', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1484, 1, 100076, '2023-03-24', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1490, 1, 100193, '2023-03-24', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1494, 1, 100310, '2023-03-24', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1506, 1, 100302, '2023-03-24', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1512, 1, 100016, '2023-03-24', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1535, 1, 100062, '2023-03-24', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1541, 1, 100061, '2023-03-24', 3, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1555, 1, 100014, '2023-03-24', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1557, 1, 100017, '2023-03-24', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1559, 1, 100057, '2023-03-24', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1566, 1, 100110, '2023-03-25', 3, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1567, 1, 100115, '2023-03-25', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1570, 1, 100005, '2023-03-25', 9, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1577, 1, 100002, '2023-03-25', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1578, 1, 100004, '2023-03-25', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1579, 1, 100003, '2023-03-25', 3, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1581, 1, 100015, '2023-03-25', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1582, 1, 100304, '2023-03-25', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1584, 1, 100017, '2023-03-25', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1586, 1, 100071, '2023-03-25', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1589, 1, 100035, '2023-03-25', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1590, 1, 100040, '2023-03-25', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1592, 1, 100000, '2023-03-25', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1593, 1, 100302, '2023-03-25', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1596, 1, 100036, '2023-03-25', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1599, 1, 100304, '2023-03-26', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1600, 1, 100005, '2023-03-26', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1601, 1, 100002, '2023-03-27', 11, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1603, 1, 100071, '2023-03-27', 20, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1604, 1, 100017, '2023-03-27', 4, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1607, 1, 100003, '2023-03-27', 5, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1608, 1, 100304, '2023-03-27', 5, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1611, 1, 100291, '2023-03-27', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1612, 1, 100004, '2023-03-27', 16, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1613, 1, 100031, '2023-03-27', 5, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1616, 1, 100110, '2023-03-27', 7, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1617, 1, 100005, '2023-03-27', 9, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1619, 1, 100000, '2023-03-27', 3, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1621, 1, 100302, '2023-03-27', 3, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1623, 1, 100019, '2023-03-27', 5, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1624, 1, 100303, '2023-03-27', 4, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1625, 1, 100015, '2023-03-27', 3, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1627, 1, 100016, '2023-03-27', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1630, 1, 100018, '2023-03-27', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1631, 1, 100020, '2023-03-27', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1634, 1, 100309, '2023-03-27', 3, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1639, 1, 100108, '2023-03-27', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1640, 1, 100115, '2023-03-27', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1641, 1, 100107, '2023-03-27', 5, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1642, 1, 100101, '2023-03-27', 6, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1659, 1, 100051, '2023-03-27', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1663, 1, 100185, '2023-03-27', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1665, 1, 100062, '2023-03-27', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1668, 1, 100121, '2023-03-27', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1678, 1, 100075, '2023-03-27', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1680, 1, 100044, '2023-03-27', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1682, 1, 100055, '2023-03-27', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1687, 1, 100013, '2023-03-27', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1705, 1, 100109, '2023-03-27', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1707, 1, 100059, '2023-03-27', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1709, 1, 100176, '2023-03-27', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1710, 1, 100001, '2023-03-27', 5, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1711, 1, 100054, '2023-03-27', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1740, 1, 100019, '2023-03-28', 2, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1741, 1, 100304, '2023-03-28', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1742, 1, 100110, '2023-03-28', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1743, 1, 100109, '2023-03-28', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1744, 1, 100070, '2023-03-28', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1745, 1, 100063, '2023-03-28', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1746, 1, 100002, '2023-03-28', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1747, 1, 100003, '2023-03-28', 3, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1750, 1, 100015, '2023-03-28', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1751, 1, 100004, '2023-03-28', 1, 0);
INSERT INTO `rpt_site_movie_date` VALUES (1753, 1, 100017, '2023-03-28', 1, 0);

-- ----------------------------
-- Table structure for rpt_site_search_date
-- ----------------------------
DROP TABLE IF EXISTS `rpt_site_search_date`;
CREATE TABLE `rpt_site_search_date`  (
  `site_id` bigint(20) NOT NULL COMMENT '站点id',
  `keywords` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '搜索关键词',
  `date` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '日期(yyyy-MM-dd)',
  `number` bigint(20) NOT NULL DEFAULT 1 COMMENT '次数',
  UNIQUE INDEX `uni_siteid_date_keywords`(`site_id`, `date`, `keywords`) USING BTREE COMMENT '唯一索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rpt_site_search_date
-- ----------------------------
INSERT INTO `rpt_site_search_date` VALUES (1, '城下', '2023-02-14', 6);
INSERT INTO `rpt_site_search_date` VALUES (1, '泰坦', '2023-02-14', 5);
INSERT INTO `rpt_site_search_date` VALUES (1, '泰坦尼克号', '2023-02-14', 2);
INSERT INTO `rpt_site_search_date` VALUES (1, '抽插', '2023-03-08', 7);
INSERT INTO `rpt_site_search_date` VALUES (1, '电车', '2023-03-08', 2);
INSERT INTO `rpt_site_search_date` VALUES (1, '调教', '2023-03-08', 10);
INSERT INTO `rpt_site_search_date` VALUES (1, '调教', '2023-03-09', 6);
INSERT INTO `rpt_site_search_date` VALUES (1, '抽插', '2023-03-10', 24);
INSERT INTO `rpt_site_search_date` VALUES (1, '调教', '2023-03-10', 4);
INSERT INTO `rpt_site_search_date` VALUES (1, '抽插', '2023-03-24', 7);
INSERT INTO `rpt_site_search_date` VALUES (1, '电车', '2023-03-24', 19);
INSERT INTO `rpt_site_search_date` VALUES (1, '调教', '2023-03-25', 2);
INSERT INTO `rpt_site_search_date` VALUES (1, '抽插', '2023-03-27', 2);

-- ----------------------------
-- Table structure for rpt_site_search_total
-- ----------------------------
DROP TABLE IF EXISTS `rpt_site_search_total`;
CREATE TABLE `rpt_site_search_total`  (
  `site_id` bigint(20) NOT NULL COMMENT '站点id',
  `keywords` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '搜索关键词',
  `number` bigint(20) NOT NULL DEFAULT 1 COMMENT '次数',
  UNIQUE INDEX `uni_siteid_keywords`(`site_id`, `keywords`) USING BTREE COMMENT '唯一索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rpt_site_search_total
-- ----------------------------
INSERT INTO `rpt_site_search_total` VALUES (1, '城下', 6);
INSERT INTO `rpt_site_search_total` VALUES (1, '抽插', 40);
INSERT INTO `rpt_site_search_total` VALUES (1, '泰坦', 5);
INSERT INTO `rpt_site_search_total` VALUES (1, '泰坦尼克号', 2);
INSERT INTO `rpt_site_search_total` VALUES (1, '电车', 21);
INSERT INTO `rpt_site_search_total` VALUES (1, '调教', 22);

-- ----------------------------
-- Table structure for rpt_site_summary
-- ----------------------------
DROP TABLE IF EXISTS `rpt_site_summary`;
CREATE TABLE `rpt_site_summary`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `site_id` bigint(11) NULL DEFAULT NULL COMMENT '站点id',
  `site_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点编码',
  `site_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点名称',
  `date` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '统计日期(yyyy-MM-dd)',
  `pv` bigint(20) NULL DEFAULT NULL COMMENT 'pv',
  `uv` bigint(20) NULL DEFAULT NULL COMMENT 'uv',
  `new_user_num` int(11) NOT NULL DEFAULT 0 COMMENT '新增会员数',
  `new_vip_num` int(11) NOT NULL DEFAULT 0 COMMENT '新增vip数',
  `recharge_number` int(11) NOT NULL DEFAULT 0 COMMENT '充值单数',
  `recharge` decimal(20, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '充值金额',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_site_date`(`site_id`, `date`) USING BTREE COMMENT '站点日期唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '站点运营报表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rpt_site_summary
-- ----------------------------
INSERT INTO `rpt_site_summary` VALUES (3, 1, 'kk-porn01', 'kk黄站1号', '2023-02-28', NULL, NULL, 2, 1, 0, 0.00, '2023-02-28 15:35:36', '2023-02-28 16:26:16', NULL, NULL);
INSERT INTO `rpt_site_summary` VALUES (12, 11, 'code07', 'lulu07', '2023-02-28', NULL, NULL, 2, 2, 2, 200.00, '2023-02-28 20:21:56', '2023-02-28 20:26:13', NULL, NULL);
INSERT INTO `rpt_site_summary` VALUES (16, 1, 'kk-porn01', 'kk黄站1号', '2023-03-04', NULL, NULL, 10, 0, 0, 0.00, '2023-03-04 13:36:35', '2023-03-04 14:15:29', NULL, NULL);
INSERT INTO `rpt_site_summary` VALUES (26, 11, 'code07', 'lulu07', '2023-03-08', NULL, NULL, 1, 0, 0, 0.00, '2023-03-08 13:23:37', '2023-03-08 13:23:37', NULL, NULL);
INSERT INTO `rpt_site_summary` VALUES (27, 1, 'kk-porn01', 'kk黄站1号', '2023-03-08', NULL, NULL, 1, 0, 0, 0.00, '2023-03-08 20:34:36', '2023-03-08 20:34:36', NULL, NULL);
INSERT INTO `rpt_site_summary` VALUES (28, 1, 'kk-porn01', 'kk黄站1号', '2023-03-11', NULL, NULL, 1, 0, 0, 0.00, '2023-03-11 16:53:03', '2023-03-11 16:53:03', NULL, NULL);
INSERT INTO `rpt_site_summary` VALUES (29, 1, 'kk-porn01', 'kk黄站1号', '2023-03-16', NULL, NULL, 2, 0, 0, 0.00, '2023-03-16 14:36:04', '2023-03-16 20:16:58', NULL, NULL);
INSERT INTO `rpt_site_summary` VALUES (31, 1, 'kk-porn01', 'kk黄站1号', '2023-03-17', NULL, NULL, 3, 0, 0, 0.00, '2023-03-17 21:30:18', '2023-03-17 22:23:04', NULL, NULL);
INSERT INTO `rpt_site_summary` VALUES (34, 1, 'kk-porn01', 'kk黄站1号', '2023-03-25', NULL, NULL, 1, 2, 0, 0.00, '2023-03-25 17:49:48', '2023-03-25 17:49:48', NULL, NULL);
INSERT INTO `rpt_site_summary` VALUES (37, 1, 'kk-porn01', 'kk黄站1号', '2023-03-26', NULL, NULL, 2, 0, 0, 0.00, '2023-03-26 17:02:24', '2023-03-26 17:11:06', NULL, NULL);
INSERT INTO `rpt_site_summary` VALUES (39, 1, 'kk-porn01', 'kk黄站1号', '2023-03-27', NULL, NULL, 1, 1, 0, 0.00, '2023-03-27 20:08:02', '2023-03-27 20:08:02', NULL, NULL);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `url` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `path` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `path_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `css` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sort` int(11) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `type` tinyint(1) NOT NULL,
  `hidden` tinyint(1) NOT NULL DEFAULT 0,
  `tenant_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_parent_id`(`parent_id`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 267 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (0, -1, '首页', '/index', NULL, NULL, NULL, NULL, '2022-09-22 14:51:48', '2022-10-15 19:59:20', 1, 0, '');
INSERT INTO `sys_menu` VALUES (1, -1, '会员查询', '/usersList/index', NULL, NULL, NULL, NULL, '2022-09-22 14:51:48', '2022-10-08 15:46:31', 1, 0, '');
INSERT INTO `sys_menu` VALUES (2, -1, '商户管理', '/Merchant/index', NULL, NULL, NULL, NULL, '2022-09-22 14:54:54', '2022-09-22 14:55:28', 1, 0, '');
INSERT INTO `sys_menu` VALUES (3, -1, '上下分记录', '/Points/index', NULL, NULL, NULL, NULL, '2022-09-22 20:57:20', '2022-09-22 20:58:51', 1, 0, '');
INSERT INTO `sys_menu` VALUES (4, -1, '财务报表', '/Financial/index', NULL, NULL, NULL, NULL, '2022-09-22 14:56:26', '2022-09-22 14:56:26', 1, 0, '');
INSERT INTO `sys_menu` VALUES (5, -1, '即时注单', '/Betting/index', NULL, NULL, NULL, NULL, '2022-09-22 14:58:14', '2022-09-22 14:58:14', 1, 0, '');
INSERT INTO `sys_menu` VALUES (6, -1, '日志查询', '/Log/index', NULL, NULL, NULL, NULL, '2022-09-22 15:03:33', '2022-09-22 15:03:33', 1, 0, '');
INSERT INTO `sys_menu` VALUES (7, -1, '系统设定', '/Setting/index', NULL, NULL, NULL, NULL, '2022-09-22 14:59:51', '2022-09-22 14:59:51', 1, 0, '');
INSERT INTO `sys_menu` VALUES (8, -1, '管理员中心', '/Administrator/index', NULL, NULL, NULL, NULL, '2022-09-22 15:01:54', '2022-09-22 15:01:54', 1, 0, '');
INSERT INTO `sys_menu` VALUES (9, -1, '模版设定', '/TemplateSettings/index', NULL, NULL, NULL, NULL, '2022-09-22 15:03:33', '2022-09-22 15:03:33', 1, 0, '');
INSERT INTO `sys_menu` VALUES (13, 2, '商户列表', '/Merchant/List', NULL, NULL, NULL, NULL, '2022-09-22 19:03:59', '2022-09-22 19:03:59', 1, 0, '');
INSERT INTO `sys_menu` VALUES (14, 7, '前台国际化翻译', '/FrontPageTranslate', NULL, NULL, NULL, NULL, '2022-09-22 20:00:47', '2022-09-22 20:00:47', 1, 0, '');
INSERT INTO `sys_menu` VALUES (15, 7, '后台国际化翻译', '/BackendPageTranslate', NULL, NULL, NULL, NULL, '2022-09-22 20:01:09', '2022-09-22 20:01:09', 1, 0, '');
INSERT INTO `sys_menu` VALUES (16, 7, '公告管理', '/notice/backend/notice/findNoticeList', NULL, NULL, NULL, NULL, '2022-09-22 20:37:26', '2022-09-22 20:37:26', 1, 0, '');
INSERT INTO `sys_menu` VALUES (17, 8, '管理员列表', '/users', NULL, NULL, NULL, NULL, '2022-09-22 19:58:24', '2022-09-22 19:58:24', 1, 0, '');
INSERT INTO `sys_menu` VALUES (18, 8, '角色管理', '/roles', NULL, NULL, NULL, NULL, '2022-09-22 19:58:49', '2022-09-22 19:58:49', 1, 0, '');
INSERT INTO `sys_menu` VALUES (19, 8, '菜单管理', '/menus/findAlls', NULL, NULL, NULL, NULL, '2022-09-22 19:59:13', '2022-09-22 19:59:13', 1, 0, '');
INSERT INTO `sys_menu` VALUES (20, 3, '记录查询', '/sysTansterMoney/findList', NULL, NULL, NULL, NULL, '2022-09-22 20:24:27', '2022-09-26 15:54:19', 1, 0, '');
INSERT INTO `sys_menu` VALUES (21, 7, '启停设定', '/configure/findLtyCityInfoList', NULL, NULL, NULL, NULL, '2022-09-22 20:26:16', '2022-09-22 20:26:18', 1, 0, '');
INSERT INTO `sys_menu` VALUES (22, 21, '修改状态', '/configure/updateLtyCityInfoState', NULL, NULL, NULL, NULL, '2022-09-22 20:26:16', '2022-09-22 20:26:18', 2, 0, '');
INSERT INTO `sys_menu` VALUES (23, 7, '封盘设定', '/configure/findLtyCityInfoSealList', NULL, NULL, NULL, NULL, '2022-09-22 20:26:16', '2022-09-22 20:26:18', 1, 0, '');
INSERT INTO `sys_menu` VALUES (24, 23, '编辑', '/configure/updateLtyCityInfoSeal', NULL, NULL, NULL, NULL, '2022-09-22 20:26:16', '2022-09-22 20:26:18', 2, 0, '');
INSERT INTO `sys_menu` VALUES (25, 7, '盘口设定', '/configure/findHandicapList', NULL, NULL, NULL, NULL, '2022-09-22 20:26:16', '2022-09-22 20:26:18', 1, 0, '');
INSERT INTO `sys_menu` VALUES (26, 25, '查询赔率', '/configure/findHandicapOddsList', NULL, NULL, NULL, NULL, '2022-09-22 20:26:16', '2022-09-22 20:26:18', 2, 0, '');
INSERT INTO `sys_menu` VALUES (27, 25, '编辑赔率', '/configure/updateOdds', NULL, NULL, NULL, NULL, '2022-09-22 20:26:16', '2022-09-22 20:26:18', 2, 0, '');
INSERT INTO `sys_menu` VALUES (28, 25, '新增盘口', '/configure/saveOrUpdateHandicap', NULL, NULL, NULL, NULL, '2022-09-22 20:26:16', '2022-09-22 20:26:18', 2, 0, '');
INSERT INTO `sys_menu` VALUES (29, 25, '编辑盘口状态', '/configure/saveOrUpdateHandicap', NULL, NULL, NULL, NULL, '2022-09-22 20:26:16', '2022-09-22 20:26:18', 2, 0, '');
INSERT INTO `sys_menu` VALUES (30, 25, '编辑盘口', '/configure/saveOrUpdateHandicap', NULL, NULL, NULL, NULL, '2022-09-22 20:26:16', '2022-09-22 20:26:18', 2, 0, '');
INSERT INTO `sys_menu` VALUES (31, 7, '投注限额', '/configure/findLtyQuotasList', NULL, NULL, NULL, NULL, '2022-09-22 20:26:16', '2022-09-22 20:26:18', 1, 0, '');
INSERT INTO `sys_menu` VALUES (32, 31, '新增', '/configure/saveOrUpdateQuotas', NULL, NULL, NULL, NULL, '2022-09-22 20:26:16', '2022-09-22 20:26:18', 2, 0, '');
INSERT INTO `sys_menu` VALUES (33, 31, '编辑', '/configure/saveOrUpdateQuotas', NULL, NULL, NULL, NULL, '2022-09-22 20:26:16', '2022-09-22 20:26:18', 2, 0, '');
INSERT INTO `sys_menu` VALUES (34, 31, '新增限额设定', '/configure/updateQuotaBet', NULL, NULL, NULL, NULL, '2022-09-22 20:26:16', '2022-09-22 20:26:18', 2, 0, '');
INSERT INTO `sys_menu` VALUES (36, 1, '编辑', '/updateRemark', NULL, NULL, NULL, NULL, '2022-09-22 20:26:16', '2022-09-22 20:26:18', 1, 0, '');
INSERT INTO `sys_menu` VALUES (37, 16, '新增', '/notice/saveOrUpdate', NULL, NULL, NULL, NULL, '2022-09-22 20:37:26', '2022-09-22 20:37:26', 2, 0, '');
INSERT INTO `sys_menu` VALUES (38, 16, '编辑', '/notice/saveOrUpdate', NULL, NULL, NULL, NULL, '2022-09-22 20:37:26', '2022-09-22 20:37:26', 2, 0, '');
INSERT INTO `sys_menu` VALUES (39, 16, '删除', '/notice/deleteNoticeId', NULL, NULL, NULL, NULL, '2022-09-22 20:50:42', '2022-09-22 20:50:43', 2, 0, '');
INSERT INTO `sys_menu` VALUES (40, 18, '添加角色', '/roles/saveOrUpdate', NULL, NULL, NULL, NULL, '2022-04-12 15:31:09', '2022-04-12 15:31:09', 2, 0, '');
INSERT INTO `sys_menu` VALUES (41, 18, '删除角色', '/roles/{id}', NULL, NULL, NULL, NULL, '2022-04-12 15:31:41', '2022-04-12 15:32:50', 2, 0, '');
INSERT INTO `sys_menu` VALUES (42, 18, '分配权限', '/menus/granted', NULL, NULL, NULL, NULL, '2022-04-12 15:32:16', '2022-04-12 15:32:16', 2, 0, '');
INSERT INTO `sys_menu` VALUES (45, 19, '编辑', '/menus/saveOrUpdate', '', '', '', NULL, '2022-04-12 18:36:03', '2022-04-12 18:36:03', 2, 0, '');
INSERT INTO `sys_menu` VALUES (46, 19, '删除', '/menus/{id}', '', '', '', NULL, '2022-04-12 18:36:32', '2022-04-12 18:36:32', 2, 0, '');
INSERT INTO `sys_menu` VALUES (47, 19, '新增', '/menus/saveOrUpdate', '', '', '', NULL, '2022-04-12 18:36:54', '2022-04-12 18:36:54', 2, 0, '');
INSERT INTO `sys_menu` VALUES (48, 17, '编辑', '/users/saveOrUpdate', NULL, NULL, NULL, NULL, '2022-04-12 15:18:34', '2022-04-12 15:18:34', 2, 0, '');
INSERT INTO `sys_menu` VALUES (49, 17, '删除', '/users/{id}', NULL, NULL, NULL, NULL, '2022-04-12 15:19:06', '2022-04-12 15:34:32', 2, 0, '');
INSERT INTO `sys_menu` VALUES (50, 17, '谷歌验证码状态修改', '/users/{id}/updateVerify', NULL, NULL, NULL, NULL, '2022-04-12 15:25:47', '2022-04-12 15:29:16', 2, 0, '');
INSERT INTO `sys_menu` VALUES (51, 17, '修改用户状态', '/users/updateEnabled', NULL, NULL, NULL, NULL, '2022-04-12 15:26:30', '2022-04-12 15:26:30', 2, 0, '');
INSERT INTO `sys_menu` VALUES (52, 17, '重置谷歌验证码', '/users/{id}/resetGoogleCode', NULL, NULL, NULL, NULL, '2022-04-12 15:27:08', '2022-04-12 15:27:08', 2, 0, '');
INSERT INTO `sys_menu` VALUES (53, 17, '重置密码', '/users/{id}/password', NULL, NULL, NULL, NULL, '2022-04-12 15:27:41', '2022-04-12 15:27:41', 2, 0, '');
INSERT INTO `sys_menu` VALUES (54, 17, '添加用户', '/users/saveOrUpdate', NULL, NULL, NULL, NULL, '2022-04-12 15:28:25', '2022-04-12 15:28:25', 2, 0, '');
INSERT INTO `sys_menu` VALUES (55, 17, '列表', '/users', NULL, NULL, NULL, NULL, '2022-08-15 22:16:01', '2022-08-15 22:16:01', 2, 0, '');
INSERT INTO `sys_menu` VALUES (56, 3, '分组报表', '/sysTansterMoney/findGroupList', NULL, NULL, NULL, NULL, '2022-09-22 20:24:27', '2022-09-22 20:24:29', 1, 0, '');
INSERT INTO `sys_menu` VALUES (57, 9, '盘口模板', '/configure/updateSysOdds', NULL, NULL, NULL, NULL, '2022-09-22 20:26:16', '2022-09-22 20:26:18', 1, 0, '');
INSERT INTO `sys_menu` VALUES (58, 9, '限额模板', '/configure/updateSysQuotaBet', NULL, NULL, NULL, NULL, '2022-09-22 20:26:16', '2022-09-22 20:26:18', 1, 0, '');
INSERT INTO `sys_menu` VALUES (59, 57, '查询', '/configure/findHandicapOddsList', NULL, NULL, NULL, NULL, '2022-09-22 20:26:16', '2022-09-22 20:26:18', 2, 0, '');
INSERT INTO `sys_menu` VALUES (60, 57, '保存', '/configure/updateSysOdds', NULL, NULL, NULL, NULL, '2022-09-22 20:26:16', '2022-09-22 20:26:18', 2, 0, '');
INSERT INTO `sys_menu` VALUES (61, 58, '查询', '/configure/findQuotaBetList', NULL, NULL, NULL, NULL, '2022-09-22 20:26:16', '2022-09-22 20:26:18', 2, 0, '');
INSERT INTO `sys_menu` VALUES (62, 58, '保存', '/configure/updateSysQuotaBet', NULL, NULL, NULL, NULL, '2022-09-22 20:26:16', '2022-09-22 20:26:18', 2, 0, '');
INSERT INTO `sys_menu` VALUES (63, 8, '管理员白名单', '/white/findWhiteList', NULL, NULL, NULL, NULL, '2022-09-22 19:59:13', '2022-09-22 19:59:13', 1, 0, '');
INSERT INTO `sys_menu` VALUES (234, 63, '编辑', '/white/saveOrUpdate', '', '', '', NULL, '2022-04-12 18:36:03', '2022-04-12 18:36:03', 2, 0, '');
INSERT INTO `sys_menu` VALUES (235, 63, '删除', '/white/deleteWhiteId/{id}', '', '', '', NULL, '2022-04-12 18:36:32', '2022-04-12 18:36:32', 2, 0, '');
INSERT INTO `sys_menu` VALUES (236, 63, '新增', '/white/saveOrUpdate', '', '', '', NULL, '2022-04-12 18:36:54', '2022-04-12 18:36:54', 2, 0, '');
INSERT INTO `sys_menu` VALUES (254, 1, '会员列表', '/usersList', NULL, NULL, NULL, NULL, '2022-09-23 23:13:15', '2022-09-23 23:13:15', 1, 0, '');
INSERT INTO `sys_menu` VALUES (257, 4, '未结算注单', '/findNoSettlementDetailPage/index', NULL, NULL, NULL, NULL, '2022-09-26 18:07:33', '2022-09-26 18:07:33', 1, 0, '');
INSERT INTO `sys_menu` VALUES (258, 4, '盘口报表', '/findRptHandicapPage/index', NULL, NULL, NULL, NULL, '2022-09-26 18:08:11', '2022-09-26 18:08:11', 1, 0, '');
INSERT INTO `sys_menu` VALUES (259, 4, '交收报表', '/findRptSettlementPage/index', NULL, NULL, NULL, NULL, '2022-09-26 18:09:42', '2022-09-26 18:09:42', 1, 0, '');
INSERT INTO `sys_menu` VALUES (260, 4, '商户报表', '/findRptMerchantPage/index', NULL, NULL, NULL, NULL, '2022-09-26 18:13:30', '2022-09-26 18:13:30', 1, 0, '');
INSERT INTO `sys_menu` VALUES (261, 4, '取消注单报表', '/findBetCancelPage/index', NULL, NULL, NULL, NULL, '2022-09-26 18:14:30', '2022-09-26 18:14:30', 1, 0, '');
INSERT INTO `sys_menu` VALUES (262, 4, '已结算注单', '/findSettlementPage/index', NULL, NULL, NULL, NULL, '2022-09-26 19:11:46', '2022-09-26 19:11:46', 1, 0, '');
INSERT INTO `sys_menu` VALUES (263, 5, '实时滚单', '/rptBetInfo/index', NULL, NULL, NULL, NULL, '2022-09-26 19:45:38', '2022-09-26 19:45:38', 1, 0, '');
INSERT INTO `sys_menu` VALUES (264, 6, '会员登录', '/findUserLoginLogList/index', NULL, NULL, NULL, NULL, '2022-09-26 20:15:27', '2022-09-26 20:15:27', 1, 0, '');
INSERT INTO `sys_menu` VALUES (265, 6, '管理员登录', '/findAdminUserLoginLogList/index', NULL, NULL, NULL, NULL, '2022-09-26 20:16:35', '2022-09-26 20:16:35', 1, 0, '');
INSERT INTO `sys_menu` VALUES (266, 6, '操作日志', '/operation/findOperationList', NULL, NULL, NULL, NULL, '2022-09-26 20:17:46', '2022-09-26 20:17:46', 1, 0, '');

-- ----------------------------
-- Table structure for sys_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_log`;
CREATE TABLE `sys_operation_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '操作人id',
  `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL COMMENT '操作人姓名(账号)',
  `nick_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL COMMENT '昵称',
  `user_ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL COMMENT 'IP',
  `module_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL COMMENT '模块名称',
  `operation_event` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL COMMENT '操作内容',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_operation_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色code',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名',
  `tenant_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '租户字段',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_code`(`code`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '1', '超级管理员', '', NULL, '2022-04-09 20:48:08', '2023-03-10 20:42:58', '', '');
INSERT INTO `sys_role` VALUES (2, '2', '商户管理员', '', '', '2023-03-10 19:52:31', '2023-03-10 20:42:44', '', '');
INSERT INTO `sys_role` VALUES (3, '3', '站点管理员', '', NULL, '2022-04-12 19:02:27', '2023-03-10 20:43:03', '', '');
INSERT INTO `sys_role` VALUES (4, 'ADMIN', '管理员', 'webApp', NULL, '2017-11-17 16:56:59', '2023-03-10 20:43:14', '', '');
INSERT INTO `sys_role` VALUES (5, '4', '客服', '', NULL, '2022-04-09 21:09:16', '2023-03-10 20:43:16', '', '');
INSERT INTO `sys_role` VALUES (6, '5', '翻译', '', NULL, '2022-07-12 18:45:15', '2023-03-10 20:43:21', '', '');
INSERT INTO `sys_role` VALUES (7, 'SH', '商户', '', NULL, '2022-10-06 18:36:05', '2023-03-10 20:43:24', '', '');
INSERT INTO `sys_role` VALUES (8, '1234', '测试', '', NULL, '2022-10-08 15:22:24', '2023-03-10 20:43:27', '', '');
INSERT INTO `sys_role` VALUES (9, '33', 'test', '', NULL, '2022-10-11 19:56:24', '2023-03-10 20:43:29', '', '');
INSERT INTO `sys_role` VALUES (10, '8', '运营', '', NULL, '2022-10-25 10:38:50', '2023-03-10 20:43:33', '', '');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES (129, 2);
INSERT INTO `sys_role_user` VALUES (130, 2);
INSERT INTO `sys_role_user` VALUES (133, 2);
INSERT INTO `sys_role_user` VALUES (134, 3);
INSERT INTO `sys_role_user` VALUES (135, 3);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `site_id` int(10) UNSIGNED NOT NULL COMMENT '站点id',
  `site_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '站点编码',
  `site_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '站点名称',
  `parent_id` bigint(100) NULL DEFAULT NULL COMMENT '所属上级id',
  `parent_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属上级账号',
  `invite_code` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邀请码',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '账号',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录密码',
  `nickname` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `promotion_code` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '我的推广码',
  `head_img_url` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `mobile` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `sex` tinyint(1) NOT NULL DEFAULT 1 COMMENT '性别(0:女,1:男)',
  `type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账户类型 APP:前端用户,BACKEND:平台管理员',
  `vip` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否vip会员( 0普通会员,1vip )',
  `vip_expire` datetime NULL DEFAULT NULL COMMENT 'vip到期时间',
  `k_balance` decimal(22, 4) UNSIGNED NOT NULL DEFAULT 0.0000 COMMENT 'k币余额',
  `role_sites` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '管理站点id',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `enabled` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态：0.冻结，1.启用',
  `is_del` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除 0未删除 1删除',
  `is_login` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否登录过 0未登录,1已登录',
  `login_time` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `login_ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '最后登录IP',
  `is_online` tinyint(255) NULL DEFAULT NULL COMMENT '在线状态 0:下线,1:在线',
  `ga_bind` tinyint(1) NULL DEFAULT NULL COMMENT '谷歌验证码是否绑定1 1：已绑定，其他：未绑定',
  `ga_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '谷歌验证码KEY',
  `verify` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否验证 1：是，其他：否',
  `is_reg` tinyint(255) NOT NULL DEFAULT 1 COMMENT '创建方式 0:注册, 1:后台创建',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_username`(`username`) USING BTREE COMMENT '账号唯一',
  UNIQUE INDEX `uni_promotioncode`(`promotion_code`) USING BTREE COMMENT '推广码唯一',
  UNIQUE INDEX `uni_mobile`(`mobile`) USING BTREE COMMENT '手机号唯一',
  INDEX `idx_invitecode`(`invite_code`) USING BTREE COMMENT '邀请码'
) ENGINE = InnoDB AUTO_INCREMENT = 144 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 0, '0', '0', NULL, NULL, NULL, 'admin', '$2a$10$2fButpgZhyR6zV3sRl.DUu4eE.ZCLDCE4UXM.cp8dqgwZad/ubnxu', '超级管理员', NULL, NULL, NULL, 1, 'BACKEND', 0, NULL, 0.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.100.100', NULL, NULL, NULL, 0, 1, '2023-03-09 22:51:01', '2023-03-24 21:31:10', '', NULL);
INSERT INTO `sys_user` VALUES (2, 1, 'kk-porn01', 'kk黄站1号', NULL, NULL, '123456', 'kk-porn01_year01', '$2a$10$8eMwAlAR99gtfskfUuY1Sec/41DoiTPDRrv0ZLdbXS5TYG1jNyaq2', 'year01', 'abcedf', NULL, NULL, 1, 'APP', 1, '2023-05-29 16:18:41', 400000.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.1.23', NULL, NULL, NULL, 1, 1, '2023-02-03 14:05:13', '2023-02-28 17:16:12', 'year', 'year');
INSERT INTO `sys_user` VALUES (3, 11, 'code07', 'lulu07', NULL, NULL, NULL, 'lulu', '$2a$10$x8zxoA6z4yGWArTScLdP1egDzjO4osxuwjcS8ICsXPvE5sF0neAdu', NULL, 'ABCEDF', NULL, '15048374736', 1, 'APP', 0, NULL, 0.0000, NULL, NULL, 1, 0, 0, NULL, NULL, NULL, NULL, NULL, 1, 1, '2023-02-16 13:21:16', '2023-03-16 17:37:07', '', '');
INSERT INTO `sys_user` VALUES (6, 1, 'kk-porn01', 'kk黄站1号', NULL, NULL, NULL, 'kk-porn01_Year01', '$2a$10$khdRaySvcO4g73MJ0ZvDLulObKMifbPyr.ExSKBC.3S7blgJ.dpLq', 'Year01', 'tGOeyj', NULL, NULL, 1, 'APP', 0, NULL, 0.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.1.23', NULL, NULL, NULL, 1, 1, '2023-02-20 22:13:34', '2023-02-23 23:38:26', '', '');
INSERT INTO `sys_user` VALUES (80, 1, 'kk-porn01', 'kk黄站1号', NULL, NULL, NULL, 'kk-porn01_YEar01', '$2a$10$j3o8BpjSazhgI9c5BJKUY.v116Df2HK8J8YQp/a3wF36nHz6ECfGO', 'YEar01', 'mxdSdX', NULL, NULL, 1, 'APP', 0, NULL, 0.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.1.23', NULL, NULL, NULL, 1, 1, '2023-02-20 22:26:38', '2023-02-23 23:38:26', '', '');
INSERT INTO `sys_user` VALUES (81, 1, 'kk-porn01', 'kk黄站1号', NULL, NULL, NULL, 'kk-porn01_YEAr01', '$2a$10$KSXjg6HedpIxf8wE0WSC1u9/h6GKa4nr5dz4Wy/Rti.ScVwiA6f46', 'YEAr01', 'qdEeK2', NULL, NULL, 1, 'APP', 0, NULL, 0.0000, NULL, NULL, 1, 0, 0, NULL, NULL, NULL, NULL, NULL, 1, 1, '2023-02-20 22:27:53', '2023-02-21 20:58:10', '', '');
INSERT INTO `sys_user` VALUES (94, 1, 'kk-porn01', 'kk黄站1号', 2, 'kk-porn01_year01', 'abcedf', 'kk-porn01_year001', '$2a$10$tu4dCPqz4YNu1bkv1mOGxOz7MHLzPmp9XEbfzDzmEVYTo0S2K07nO', 'year001', 'DRlLPf', 'kkporn/me.jpg', NULL, 1, 'APP', 1, '2023-06-28 17:21:21', 700020.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.1.23', NULL, NULL, NULL, 1, 1, '2023-02-20 23:09:40', '2023-03-04 17:07:06', '', NULL);
INSERT INTO `sys_user` VALUES (95, 11, 'code07', 'lulu07', NULL, NULL, NULL, 'lulu01', '$2a$10$c9QTs5UZGr9pOoB7ER0oVeDoysriLG5VpEbX4fYSBYxDZ.tmm7cpK', NULL, NULL, NULL, NULL, 1, 'APP', 0, '2026-02-24 14:44:57', 0.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.100.100', NULL, NULL, NULL, 1, 1, '2023-02-21 16:34:25', '2023-03-09 13:43:04', '', NULL);
INSERT INTO `sys_user` VALUES (96, 11, 'code07', 'lulu07', NULL, NULL, NULL, 'lulu009', '$2a$10$LsqMhHAJMA0nT.1S/Y8HLOdu1vjjUsRXo58DocyyxhsJpZY/mviRm', 'lulu009', 'Iz1tmf', NULL, NULL, 1, 'APP', 0, NULL, 0.0000, NULL, NULL, 1, 0, 0, NULL, NULL, NULL, NULL, NULL, 1, 1, '2023-02-23 14:52:29', '2023-02-28 22:11:48', '', '');
INSERT INTO `sys_user` VALUES (100, 1, 'kk-porn01', 'kk黄站1号', 2, 'kk-porn01_year01', 'abcedf', 'kk-porn01_year002', '$2a$10$vQ12CRShL7qPghbcCgeTiusFAmJ5klK0Eqz588EfD1FymJMWR1R3G', 'year002', 'drBfmW', NULL, NULL, 1, 'APP', 0, NULL, 54.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.1.23', NULL, NULL, NULL, 1, 0, '2023-02-23 20:56:24', '2023-02-28 21:39:11', '', '');
INSERT INTO `sys_user` VALUES (101, 1, 'kk-porn01', 'kk黄站1号', NULL, NULL, NULL, 'kk-porn01_year003', '$2a$10$cHn2BEucpWSlZ3IvCBRF2eBnVDj2Ny.AijNRHLFSyhH3OS2h9k/va', 'year003', 'nk5EJK', NULL, NULL, 1, 'APP', 0, NULL, 0.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.1.23', NULL, NULL, NULL, 1, 0, '2023-02-23 21:44:56', '2023-02-28 22:11:47', '', '');
INSERT INTO `sys_user` VALUES (102, 1, 'kk-porn01', 'kk黄站1号', 94, 'kk-porn01_year001', 'DRlLPf', 'kk-porn01_year004', '$2a$10$BjDE41IbfGFXgkKVFXZ.cuKEGDUm1jRdMzXqALV3M4vH0a9hsEjvu', 'year004', '5pe5QX', NULL, NULL, 1, 'APP', 1, '2023-02-25 00:13:53', 14.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.1.23', NULL, NULL, NULL, 1, 0, '2023-02-24 00:13:52', '2023-02-28 22:11:47', '', '');
INSERT INTO `sys_user` VALUES (103, 1, 'kk-porn01', 'kk黄站1号', 100, 'kk-porn01_year002', 'drBfmW', 'kk-porn01_year005', '$2a$10$yawdOw9n8.mmkOkE2BsdKuwXH2Y8h3bWxUa4WQfvz5PkSEnOiHTS.', 'year005', 'yn5pNm', NULL, NULL, 1, 'APP', 0, NULL, 14.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.1.23', NULL, NULL, NULL, 1, 0, '2023-02-24 00:22:31', '2023-02-28 22:11:46', '', '');
INSERT INTO `sys_user` VALUES (104, 1, 'kk-porn01', 'kk黄站1号', 94, 'kk-porn01_year001', 'DRlLPf', 'kk-porn01_year006', '$2a$10$XM9TRyPbFSn0q5EAJgeer.Kf9HQJs91ZL5UKAUFgryzv1O1y6cCpC', 'year006', 'PNhnYk', NULL, NULL, 1, 'APP', 0, NULL, 14.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.1.23', NULL, NULL, NULL, 1, 0, '2023-02-24 17:25:43', '2023-02-28 22:11:47', '', '');
INSERT INTO `sys_user` VALUES (110, 1, 'kk-porn01', 'kk黄站1号', NULL, NULL, NULL, 'kk-porn01_year007', '$2a$10$GxatWZC.L8bxwNrC42XOmevA.Dnge3rxRVUKt7bY/dnnCCRuVJkAS', 'year007', 'SC5HjX', NULL, NULL, 1, 'APP', 0, NULL, 0.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.1.23', NULL, NULL, NULL, 1, 0, '2023-02-28 15:40:10', '2023-02-28 22:11:48', '', '');
INSERT INTO `sys_user` VALUES (111, 1, 'kk-porn01', 'kk黄站1号', 94, 'kk-porn01_year001', 'DRlLPf', 'kk-porn01_year008', '$2a$10$8eMwAlAR99gtfskfUuY1Sec/41DoiTPDRrv0ZLdbXS5TYG1jNyaq2', 'year008', 'VUG8PW', NULL, NULL, 1, 'APP', 0, NULL, 14.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.1.23', NULL, NULL, NULL, 1, 0, '2023-02-28 15:41:19', '2023-03-01 15:41:33', '', '');
INSERT INTO `sys_user` VALUES (114, 11, 'code07', 'lulu07', NULL, NULL, NULL, 'luluTese', '$2a$10$WFfXQ.O5BKmxMgccRVa0UelD.CE0tn82JyBPVLi9i4EgFVbty82SO', 'luluTese', 'YeHSXy', NULL, NULL, 1, 'APP', 0, NULL, 0.0000, NULL, NULL, 1, 0, 0, NULL, NULL, NULL, NULL, NULL, 1, 1, '2023-02-28 19:21:56', '2023-02-28 22:11:48', '', '');
INSERT INTO `sys_user` VALUES (115, 11, 'code07', 'lulu07', NULL, NULL, NULL, 'luluTese01', '$2a$10$U92snnlmyQWGuXXfoaanoOUq3AMwAkB7Gn1OkQZADtBY6ArzvvDPS', 'luluTese01', 'nEJYc2', NULL, NULL, 1, 'APP', 0, NULL, 0.0000, NULL, NULL, 0, 0, 0, NULL, NULL, NULL, NULL, NULL, 1, 1, '2023-02-28 19:22:10', '2023-02-28 22:18:23', '', '');
INSERT INTO `sys_user` VALUES (116, 1, 'kk-porn01', 'kk黄站1号', NULL, NULL, NULL, 'kk-porn01_aaaa123123', '$2a$10$KdYtreHlAvrJw.eMBd0lLO63XAcHmH9xPBxsnfUsUM31yCWM2A5Uu', 'aaaa123123', 'kPfH4D', NULL, NULL, 1, 'APP', 0, NULL, 0.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.1.23', NULL, NULL, NULL, 1, 0, '2023-03-04 13:36:35', '2023-03-04 13:36:37', NULL, NULL);
INSERT INTO `sys_user` VALUES (117, 1, 'kk-porn01', 'kk黄站1号', NULL, NULL, NULL, 'kk-porn01_jeff001', '$2a$10$ghNqyWuFKE0c2Y2JwpIlbekH/K00GCPeRS3Cm0y98OX1P0y2asxky', 'jeff001', 'SBwJbO', NULL, NULL, 1, 'APP', 0, NULL, 0.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.100.100', NULL, NULL, NULL, 1, 0, '2023-03-04 13:38:58', '2023-03-04 14:31:17', NULL, NULL);
INSERT INTO `sys_user` VALUES (118, 1, 'kk-porn01', 'kk黄站1号', NULL, NULL, NULL, 'kk-porn01_123123', '$2a$10$NQ2L.YErSb/vSEBI/Z644uMtCxtU6ijbatbSoy3ZQPgK0f0UeaYYO', '123123', 'x189HE', NULL, NULL, 1, 'APP', 0, NULL, 0.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.100.100', NULL, NULL, NULL, 1, 0, '2023-03-04 14:01:53', '2023-03-14 21:06:24', NULL, NULL);
INSERT INTO `sys_user` VALUES (119, 1, 'kk-porn01', 'kk黄站1号', NULL, NULL, NULL, 'kk-porn01_100861', '$2a$10$FmVza/hh1mv6A/a9N0m88OxXDrEuIheSrV/YlZ6Nq1kqzDqZ75kdu', '100861', '3sfF0J', NULL, NULL, 1, 'APP', 0, NULL, 0.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.100.100', NULL, NULL, NULL, 1, 0, '2023-03-04 14:06:28', '2023-03-04 14:06:28', NULL, NULL);
INSERT INTO `sys_user` VALUES (120, 1, 'kk-porn01', 'kk黄站1号', NULL, NULL, NULL, 'kk-porn01_100026', '$2a$10$3w27IaJgS/eHRaIIRggvQ.VPRI8A7wqF4KFFzvi/7i.Tm.y6nz/GO', '100026', 's2Sw6k', NULL, NULL, 1, 'APP', 0, NULL, 0.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.100.100', NULL, NULL, NULL, 1, 0, '2023-03-04 14:08:16', '2023-03-04 14:08:16', NULL, NULL);
INSERT INTO `sys_user` VALUES (121, 1, 'kk-porn01', 'kk黄站1号', NULL, NULL, NULL, 'kk-porn01_1000171', '$2a$10$Y.tyEa4IJBFVi1V8TdJUB.fVQ60Q4JMkFOpbIqmNpRbhNabHP8gpm', '1000171', 'b8cX6b', NULL, NULL, 1, 'APP', 0, NULL, 0.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.100.100', NULL, NULL, NULL, 1, 0, '2023-03-04 14:09:04', '2023-03-04 14:09:04', NULL, NULL);
INSERT INTO `sys_user` VALUES (122, 1, 'kk-porn01', 'kk黄站1号', NULL, NULL, NULL, 'kk-porn01_100015', '$2a$10$YXno8nQfKP8Yv.oKLZMFv.dZlLi9bqdLeN6.SgLaXlZB6ao9/RCDm', '100015', '7Qmp7k', NULL, NULL, 1, 'APP', 0, NULL, 0.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.100.100', NULL, NULL, NULL, 1, 0, '2023-03-04 14:10:10', '2023-03-04 14:10:10', NULL, NULL);
INSERT INTO `sys_user` VALUES (123, 1, 'kk-porn01', 'kk黄站1号', NULL, NULL, NULL, 'kk-porn01_1000152', '$2a$10$TABZxWTgw94rca71XIOp8uBeFV1J6LGVKJHc9Oktk4JDzbWuLdwPO', '1000152', 'WUYI42', NULL, NULL, 1, 'APP', 0, NULL, 0.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.100.100', NULL, NULL, NULL, 1, 0, '2023-03-04 14:14:09', '2023-03-04 14:14:10', NULL, NULL);
INSERT INTO `sys_user` VALUES (124, 1, 'kk-porn01', 'kk黄站1号', NULL, NULL, NULL, 'kk-porn01_1000262', '$2a$10$or6h9MBQhLH8VO/9tCCa6uMZVWW3TpjJBtvNQ1z3X5rhU8G5WnEMS', '1000262', 'H1xlCR', NULL, NULL, 1, 'APP', 0, NULL, 0.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.100.100', NULL, NULL, NULL, 1, 0, '2023-03-04 14:14:52', '2023-03-04 14:14:52', NULL, NULL);
INSERT INTO `sys_user` VALUES (125, 1, 'kk-porn01', 'kk黄站1号', NULL, NULL, NULL, 'kk-porn01_2222222', '$2a$10$KmsOXMo8zbuakP9is23n9ufVKj05PXjzV4oZVtDhGx5vlhcP77LOi', '2222222', 'ZuqCDS', NULL, NULL, 1, 'APP', 0, NULL, 0.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.100.100', NULL, NULL, NULL, 1, 0, '2023-03-04 14:15:29', '2023-03-04 14:15:29', NULL, NULL);
INSERT INTO `sys_user` VALUES (126, 11, 'code07', 'lulu07', NULL, NULL, NULL, 'lulu038', '$2a$10$G0s0YetJnWLu/lBr8J1Wje4UJvGQB0BPhp9acQHNFJ7Jl0X/K9pfa', 'lulu038', '3vrLBT', NULL, NULL, 1, 'APP', 0, NULL, 0.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.100.100', NULL, NULL, NULL, 1, 1, '2023-03-08 13:23:38', '2023-03-08 14:04:31', NULL, NULL);
INSERT INTO `sys_user` VALUES (127, 1, 'kk-porn01', 'kk黄站1号', NULL, NULL, NULL, 'kk-porn01_1331111110', '$2a$10$7Rpovpvp4U6YU45/T8F2HeQYnYgg8fm8tKION88NOYTcgkHzo7fOS', '1331111110', 'pufrQ9', NULL, NULL, 1, 'APP', 0, NULL, 0.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.100.100', NULL, NULL, NULL, 1, 0, '2023-03-08 20:34:36', '2023-03-17 13:30:54', NULL, NULL);
INSERT INTO `sys_user` VALUES (129, 0, '0', '0', NULL, NULL, NULL, 'yixiu', '$2a$10$LZWyuuhQbmyHOBIVQ71OM.MVPyCxLVr7bV1D/oKsSJDLmj/.ZX1J.', 'yixiu', NULL, NULL, NULL, 1, 'BACKEND', 0, NULL, 0.0000, '[1]', '', 1, 0, 0, NULL, NULL, NULL, NULL, NULL, 1, 1, '2023-03-10 19:27:14', '2023-03-10 19:27:14', NULL, '');
INSERT INTO `sys_user` VALUES (130, 0, '0', '0', NULL, NULL, NULL, 'yixiu2', '$2a$10$EkikbZlko.lqh56W4Wf/Aeid/TNbVmRlXEd6DucTAv4dIL8YyFRr6', 'yixiu2', NULL, NULL, NULL, 1, 'BACKEND', 0, NULL, 0.0000, '[1,18,11]', '', 1, 0, 0, NULL, NULL, NULL, NULL, NULL, 1, 1, '2023-03-10 19:28:32', '2023-03-10 19:28:32', NULL, '');
INSERT INTO `sys_user` VALUES (131, 1, 'kk-porn01', 'kk黄站1号', NULL, NULL, NULL, 'kk-porn01_sdfsdf', '$2a$10$FNEgahVYpT1vIvKLSHuLH.HgGPnE9pFqX3TA/eRJGZF0IWUe19tRm', 'sdfsdf', 'bn2Rci', NULL, NULL, 1, 'APP', 0, NULL, 0.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.100.100', NULL, NULL, NULL, 1, 0, '2023-03-11 16:53:04', '2023-03-11 16:53:05', NULL, NULL);
INSERT INTO `sys_user` VALUES (132, 1, 'kk-porn01', 'kk黄站1号', NULL, NULL, NULL, 'kk-porn01_jackson', '$2a$10$s7AgZph/aH.9bXZyHnI3.ejRl5A8fe2n2DcuPZGpL9u4EkVAxql4.', 'jackson', 'mk41YL', NULL, NULL, 1, 'APP', 0, NULL, 0.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.100.100', NULL, NULL, NULL, 1, 0, '2023-03-16 14:36:05', '2023-03-16 14:36:05', NULL, NULL);
INSERT INTO `sys_user` VALUES (133, 0, '0', '0', NULL, NULL, NULL, 'lulu001', '$2a$10$Vw5XSd7deJ/HynEnu5ZYzudfsHxJwJA9JP3wnzpyPjEqtS4YsJ2me', 'lulu001', NULL, NULL, NULL, 1, 'BACKEND', 0, NULL, 0.0000, '[1]', '', 1, 0, 0, NULL, NULL, NULL, NULL, NULL, 1, 1, '2023-03-16 17:37:08', '2023-03-16 17:37:08', NULL, '');
INSERT INTO `sys_user` VALUES (134, 1, 'kk-porn01', 'kk黄站1号', NULL, NULL, NULL, 'lulu002', '$2a$10$P9N1/.rJH3UCO1fw7Okjy.UNCW5A8LnO8ZwUL8GVfFc0p8KZ1p4Iu', 'lulu002', NULL, NULL, NULL, 1, 'BACKEND', 0, NULL, 0.0000, NULL, '', 1, 0, 0, NULL, NULL, NULL, NULL, NULL, 1, 1, '2023-03-16 16:57:06', '2023-03-16 16:57:06', NULL, '');
INSERT INTO `sys_user` VALUES (135, 1, 'kk-porn01', 'kk黄站1号', NULL, NULL, NULL, 'lulu003', '$2a$10$aDLJiXejoGXJGXMiyr.oR.tP2vkg2y6H4qkO79w9C4fpOy6LhRaam', 'lulu003', NULL, NULL, NULL, 1, 'BACKEND', 0, NULL, 0.0000, NULL, '', 1, 0, 1, NULL, '192.168.100.100', NULL, NULL, NULL, 1, 1, '2023-03-16 18:49:11', '2023-03-27 17:29:09', NULL, NULL);
INSERT INTO `sys_user` VALUES (136, 1, 'kk-porn01', 'kk黄站1号', NULL, NULL, NULL, 'kk-porn01_qwe112233', '$2a$10$RSfauZNEGsWTpJ3p8kPoXu39Z67XzsdTOlEIhznt6Hy2pPaYfBhS6', 'qwe112233', 'xOusz6', 'kkporn/logo.png', NULL, 1, 'APP', 1, '2023-04-01 17:49:45', 100.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.100.100', NULL, NULL, NULL, 1, 0, '2023-03-16 20:16:59', '2023-03-28 13:19:07', NULL, NULL);
INSERT INTO `sys_user` VALUES (137, 1, 'kk-porn01', 'kk黄站1号', NULL, NULL, NULL, 'kk-porn01_qwe12323', '$2a$10$XXblbz8KVF0XcESMMRPNG./aEBpB.VotEYjVtp7WVOcu.0WlLWCbO', 'qwe12323', '7BKG9r', NULL, NULL, 1, 'APP', 0, NULL, 0.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.100.100', NULL, NULL, NULL, 1, 0, '2023-03-17 21:30:19', '2023-03-17 21:30:19', NULL, NULL);
INSERT INTO `sys_user` VALUES (138, 1, 'kk-porn01', 'kk黄站1号', NULL, NULL, NULL, 'kk-porn01_qwe1123', '$2a$10$rU7bn9n3E5nn2mg3XtFx2O4shth492DPXGL7BimypiU0WB.tHD3MS', 'qwe1123', 'RUnn53', NULL, NULL, 1, 'APP', 0, NULL, 0.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.100.100', NULL, NULL, NULL, 1, 0, '2023-03-17 22:22:19', '2023-03-17 22:22:20', NULL, NULL);
INSERT INTO `sys_user` VALUES (139, 1, 'kk-porn01', 'kk黄站1号', NULL, NULL, NULL, 'kk-porn01_qwe2233', '$2a$10$NI.mpVtuPXFivQvRq91sBud1ZsKLLpP18PeJ9Kik3D8flGsmcqWaC', 'qwe2233', 'xYlTZP', NULL, NULL, 1, 'APP', 0, NULL, 0.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.100.100', NULL, NULL, NULL, 1, 0, '2023-03-17 22:23:05', '2023-03-17 22:23:05', NULL, NULL);
INSERT INTO `sys_user` VALUES (140, 1, 'kk-porn01', 'kk黄站1号', 136, 'kk-porn01_qwe112233', 'xOusz6', 'kk-porn01_qwe11233', '$2a$10$sC04yhldXpes7uzQJJ0i/.ZkOof/QnC.W2M6vnfQLokDPeMSTc3IG', 'qwe11233', 'QvCovZ', 'kkporn/logo.png', NULL, 1, 'APP', 0, NULL, 14.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.100.100', NULL, NULL, NULL, 1, 0, '2023-03-25 17:49:45', '2023-03-26 17:49:50', NULL, NULL);
INSERT INTO `sys_user` VALUES (141, 1, 'kk-porn01', 'kk黄站1号', NULL, NULL, NULL, 'kk-porn01_qwe1122333', '$2a$10$7ImLLbgmPFg01.plOxz1LOIqSaIAAtr454NfLHGhNxvYP5DdPaUBO', 'qwe1122333', 'BWQLnw', NULL, NULL, 1, 'APP', 0, NULL, 30.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.100.100', NULL, NULL, NULL, 1, 0, '2023-03-26 17:02:21', '2023-03-27 20:50:32', NULL, NULL);
INSERT INTO `sys_user` VALUES (142, 1, 'kk-porn01', 'kk黄站1号', NULL, NULL, NULL, 'kk-porn01_chezai01', '$2a$10$qUkP9g7t3p6LIfTAs0PryesV6yQG5HlRdavL/YeNzBPsnYBGmpc8K', 'chezai01', 'YTkq7d', NULL, NULL, 1, 'APP', 0, NULL, 30.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.100.100', NULL, NULL, NULL, 1, 0, '2023-03-26 17:11:03', '2023-03-27 17:11:33', NULL, NULL);
INSERT INTO `sys_user` VALUES (143, 1, 'kk-porn01', 'kk黄站1号', 136, 'kk-porn01_qwe112233', 'xOusz6', 'kk-porn01_qwe112333', '$2a$10$YlE.oLCXz5N54eIhhndTTeI1ILypSWRP4fVFPkvPA9JdqaquUYYF.', 'qwe112333', 'rxkhKQ', NULL, NULL, 1, 'APP', 1, '2023-03-28 20:07:59', 14.0000, NULL, NULL, 1, 0, 1, NULL, '192.168.100.100', NULL, NULL, NULL, 1, 0, '2023-03-27 20:07:59', '2023-03-27 20:07:59', NULL, NULL);

-- ----------------------------
-- Table structure for sys_white_ip
-- ----------------------------
DROP TABLE IF EXISTS `sys_white_ip`;
CREATE TABLE `sys_white_ip`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ip` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '后台白名单',
  `remark` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `shite_ip_ip`(`ip`) USING BTREE,
  INDEX `shite_ip_normal`(`ip`, `update_time`, `update_by`) USING BTREE,
  FULLTEXT INDEX `shite_ip_fulltext`(`update_by`)
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_white_ip
-- ----------------------------
INSERT INTO `sys_white_ip` VALUES (1, '127.0.0.1', '', '2023-03-04 12:32:51', '2023-03-04 12:32:51', '', '');

SET FOREIGN_KEY_CHECKS = 1;
