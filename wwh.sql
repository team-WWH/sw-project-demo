/*
 Navicat Premium Dump SQL

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80042 (8.0.42)
 Source Host           : localhost:3306
 Source Schema         : wwh

 Target Server Type    : MySQL
 Target Server Version : 80042 (8.0.42)
 File Encoding         : 65001

 Date: 26/07/2025 10:31:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for claconspe
-- ----------------------------
DROP TABLE IF EXISTS `claconspe`;
CREATE TABLE `claconspe`  (
  `ClaconSpeID` int NOT NULL AUTO_INCREMENT COMMENT '上课数据--演讲',
  `CladataID` int NULL DEFAULT NULL,
  `SpeechID` int NULL DEFAULT NULL,
  PRIMARY KEY (`ClaconSpeID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of claconspe
-- ----------------------------

-- ----------------------------
-- Table structure for cladata
-- ----------------------------
DROP TABLE IF EXISTS `cladata`;
CREATE TABLE `cladata`  (
  `CladataID` int NOT NULL AUTO_INCREMENT COMMENT '上课数据',
  `Datatype` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '数据类型',
  `datalink` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '数据链接',
  PRIMARY KEY (`CladataID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cladata
-- ----------------------------

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect`  (
  `CollectID` int NOT NULL AUTO_INCREMENT COMMENT '题库ID',
  `QuestionID` int NULL DEFAULT NULL COMMENT '题目ID',
  `ListenerID` int NULL DEFAULT NULL COMMENT '听众ID',
  PRIMARY KEY (`CollectID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES (2, 2, 1);
INSERT INTO `collect` VALUES (3, 3, 1);
INSERT INTO `collect` VALUES (6, 1, 1);

-- ----------------------------
-- Table structure for comconque
-- ----------------------------
DROP TABLE IF EXISTS `comconque`;
CREATE TABLE `comconque`  (
  `ComconQueID` int NOT NULL AUTO_INCREMENT COMMENT '评论---题目',
  `CommentID` int NULL DEFAULT NULL,
  `QuestionID` int NULL DEFAULT NULL,
  PRIMARY KEY (`ComconQueID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comconque
-- ----------------------------
INSERT INTO `comconque` VALUES (1, 1, 2);
INSERT INTO `comconque` VALUES (2, 1, 2);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `CommentID` int NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `ListenerID` int NULL DEFAULT NULL COMMENT '听众ID',
  `QuestionID` int NULL DEFAULT NULL COMMENT '题目ID',
  `Comcontent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '内容',
  `Comtime` datetime NULL DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`CommentID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 1, 2, '谁评论了', '2025-07-18 10:01:14');
INSERT INTO `comment` VALUES (2, 1, 1, '第一天', NULL);
INSERT INTO `comment` VALUES (3, 1, 1, '第二天', NULL);

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback`  (
  `FeedbackID` int NOT NULL AUTO_INCREMENT COMMENT '反馈ID',
  `ListenerID` int NULL DEFAULT NULL COMMENT '听众ID',
  `SpeechID` int NULL DEFAULT NULL COMMENT '演讲ID',
  `Fcontent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '反馈内容',
  PRIMARY KEY (`FeedbackID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of feedback
-- ----------------------------
INSERT INTO `feedback` VALUES (1, 1, 1, '1');
INSERT INTO `feedback` VALUES (2, 1, 1, NULL);
INSERT INTO `feedback` VALUES (3, 1, 1, NULL);
INSERT INTO `feedback` VALUES (4, 1, 1, NULL);
INSERT INTO `feedback` VALUES (5, 1, 1, NULL);
INSERT INTO `feedback` VALUES (6, 1, 1, '7777777777777');
INSERT INTO `feedback` VALUES (7, 1, 1, 'kkkkkkkkk');
INSERT INTO `feedback` VALUES (8, 1, 1, NULL);
INSERT INTO `feedback` VALUES (9, 1, 1, '555555');
INSERT INTO `feedback` VALUES (10, 1, 1, '5555555555');
INSERT INTO `feedback` VALUES (11, 1, 1, '作者吱吱吱吱吱吱吱吱');
INSERT INTO `feedback` VALUES (12, 0, 0, '111');
INSERT INTO `feedback` VALUES (13, 1, 2, '111');
INSERT INTO `feedback` VALUES (14, 1, 2, '啦啦啦啦啦啦啦啦');
INSERT INTO `feedback` VALUES (15, 1, 2, '1111');
INSERT INTO `feedback` VALUES (16, 2, 2, '222');
INSERT INTO `feedback` VALUES (17, 2, 2, '钱钱钱钱钱');
INSERT INTO `feedback` VALUES (18, 1, 2, '在在在在在在');
INSERT INTO `feedback` VALUES (19, 1, 2, '555');
INSERT INTO `feedback` VALUES (20, 1, 2, '7');
INSERT INTO `feedback` VALUES (21, 2, 1, '11111111');
INSERT INTO `feedback` VALUES (22, 1, 2, '阿萨');
INSERT INTO `feedback` VALUES (23, 1, 2, '啊啊啊');
INSERT INTO `feedback` VALUES (24, 1, 2, '哎哎哎');
INSERT INTO `feedback` VALUES (25, 1, 2, '1234');
INSERT INTO `feedback` VALUES (26, 1, 2, '111');
INSERT INTO `feedback` VALUES (27, 1, 2, '前二天');
INSERT INTO `feedback` VALUES (28, 2, 2, '阿达');
INSERT INTO `feedback` VALUES (29, 1, 2, '申请');
INSERT INTO `feedback` VALUES (30, 2, 1, '爱是');
INSERT INTO `feedback` VALUES (31, 1, 2, '飒飒');
INSERT INTO `feedback` VALUES (32, 2, 1, '上述');
INSERT INTO `feedback` VALUES (33, 2, 2, '事项');
INSERT INTO `feedback` VALUES (34, 1, 2, '买买买');
INSERT INTO `feedback` VALUES (35, 2, 1, '不买');
INSERT INTO `feedback` VALUES (36, 1, 2, '到底买不买');
INSERT INTO `feedback` VALUES (37, 1, 2, 'o((>ω< ))o');
INSERT INTO `feedback` VALUES (38, 2, 2, '娃娃亲');
INSERT INTO `feedback` VALUES (39, 1, 2, '可以把');
INSERT INTO `feedback` VALUES (40, 1, 2, '怎么说');
INSERT INTO `feedback` VALUES (41, 1, 2, '1323');

-- ----------------------------
-- Table structure for lisconspe
-- ----------------------------
DROP TABLE IF EXISTS `lisconspe`;
CREATE TABLE `lisconspe`  (
  `LisconSpeID` int NOT NULL AUTO_INCREMENT COMMENT '演讲--听众',
  `SpeechID` int NULL DEFAULT NULL,
  `ListenerID` int NULL DEFAULT NULL,
  PRIMARY KEY (`LisconSpeID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lisconspe
-- ----------------------------
INSERT INTO `lisconspe` VALUES (1, 1, 1);
INSERT INTO `lisconspe` VALUES (2, 2, 1);
INSERT INTO `lisconspe` VALUES (3, 3, 1);
INSERT INTO `lisconspe` VALUES (4, 2, 2);

-- ----------------------------
-- Table structure for listener
-- ----------------------------
DROP TABLE IF EXISTS `listener`;
CREATE TABLE `listener`  (
  `ListenerID` int NOT NULL AUTO_INCREMENT,
  `Uname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `Password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `Mail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `Phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `Anonymous` int NOT NULL COMMENT '匿名选项1为匿，0不',
  `Sex` int NULL DEFAULT NULL COMMENT '1男  0女',
  `CollectID` int NULL DEFAULT NULL COMMENT '收藏题目库ID',
  PRIMARY KEY (`ListenerID` DESC, `Uname`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of listener
-- ----------------------------
INSERT INTO `listener` VALUES (2, '8酷酷酷', '111111', '775275s', '7775', 0, 0, 1);
INSERT INTO `listener` VALUES (1, '鸡然', '$2a$10$nQFrX77w..8W4YVnSB7bsuQVXtxjAi/HRcyjjNJGnK5DDE6BjEeJq', 'ropamad@mail.com', '1577411', 1, 0, NULL);

-- ----------------------------
-- Table structure for organizer
-- ----------------------------
DROP TABLE IF EXISTS `organizer`;
CREATE TABLE `organizer`  (
  `OrganizerID` int NOT NULL AUTO_INCREMENT COMMENT '组织者ID',
  `Oname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `Opassword` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `Omail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `Ophone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `Osex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`OrganizerID` DESC, `Oname`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of organizer
-- ----------------------------
INSERT INTO `organizer` VALUES (1, '零零零零', '$2a$10$ZSgx8n3PxArk8drh5NZkfu1F6M0dwC1Zh2yLj.QwlYB/CZaSVBQm6', 'ropamkkkk@mail.com', '1577411', '1');

-- ----------------------------
-- Table structure for orgconspe
-- ----------------------------
DROP TABLE IF EXISTS `orgconspe`;
CREATE TABLE `orgconspe`  (
  `OrgconSpeID` int NOT NULL AUTO_INCREMENT COMMENT '组织者---演讲者',
  `OrganizerID` int NULL DEFAULT NULL,
  `SpeakerID` int NULL DEFAULT NULL,
  PRIMARY KEY (`OrgconSpeID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orgconspe
-- ----------------------------
INSERT INTO `orgconspe` VALUES (1, 1, 1);
INSERT INTO `orgconspe` VALUES (4, 1, 2);
INSERT INTO `orgconspe` VALUES (5, 1, 4);

-- ----------------------------
-- Table structure for pranking
-- ----------------------------
DROP TABLE IF EXISTS `pranking`;
CREATE TABLE `pranking`  (
  `PrankingID` int NOT NULL AUTO_INCREMENT COMMENT '个人排名ID',
  `ListenerID` int NULL DEFAULT NULL COMMENT '听众ID',
  `SpeechID` int NULL DEFAULT NULL COMMENT '演讲ID',
  `rank` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '个人排名',
  PRIMARY KEY (`PrankingID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pranking
-- ----------------------------

-- ----------------------------
-- Table structure for qresults
-- ----------------------------
DROP TABLE IF EXISTS `qresults`;
CREATE TABLE `qresults`  (
  `QresultsID` int NOT NULL AUTO_INCREMENT COMMENT '题目分析结果ID',
  `SpeechID` int NULL DEFAULT NULL COMMENT '演讲ID',
  `Correctrate` float NULL DEFAULT NULL COMMENT '正确率',
  PRIMARY KEY (`QresultsID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qresults
-- ----------------------------

-- ----------------------------
-- Table structure for queconspe
-- ----------------------------
DROP TABLE IF EXISTS `queconspe`;
CREATE TABLE `queconspe`  (
  `QueconSpeID` int NOT NULL AUTO_INCREMENT COMMENT '题目--演讲',
  `QuestionID` int NULL DEFAULT NULL,
  `SpeechID` int NULL DEFAULT NULL,
  PRIMARY KEY (`QueconSpeID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of queconspe
-- ----------------------------
INSERT INTO `queconspe` VALUES (1, 1, 2);
INSERT INTO `queconspe` VALUES (2, 2, 2);
INSERT INTO `queconspe` VALUES (3, 3, 2);
INSERT INTO `queconspe` VALUES (4, 4, 2);
INSERT INTO `queconspe` VALUES (5, 5, 2);
INSERT INTO `queconspe` VALUES (6, 6, 1);
INSERT INTO `queconspe` VALUES (7, 7, 1);

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `QuestionID` int NOT NULL AUTO_INCREMENT,
  `A` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '选项A',
  `B` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '选项B',
  `C` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '选项C',
  `D` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '选项D',
  `Questioncontent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '题目内容',
  `QresultsID` int NULL DEFAULT NULL COMMENT '题目分析结果ID',
  `StuconQueID` int NULL DEFAULT NULL COMMENT '学生答案--题目链接ID',
  `ComconQueID` int UNSIGNED NULL DEFAULT NULL COMMENT '评论--题目链接ID',
  `Qstatus` int UNSIGNED NOT NULL COMMENT '题目状态1-未结束，2-已结束',
  `Answercon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '答案解析',
  `Answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '答案ABCD',
  `Qtime` datetime NULL DEFAULT NULL COMMENT '开始时间',
  PRIMARY KEY (`QuestionID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (1, 'ervHLBOrDX', 'Ar9EiZn2CO', '05r7B0i4oG', 'Us2stHKFIm', 'FTD5svO3fJ', 1, 1, 1, 2, 'Wof23ylmUj', 'B', '2025-07-25 10:46:32');
INSERT INTO `question` VALUES (2, 'duW6iMBTs8', 'QaAjVYSFwm', 'FUobr6MRW8', 'kiufPGQRJo', '4KYLuaeQFc', 2, 2, 2, 2, 'PhLLHx8m7j', 'B', '2025-07-25 10:46:35');
INSERT INTO `question` VALUES (3, 'pBYCbAlwyv', '34h1NpsEZ5', 'Fw8E6t1ReH', 'biQPUEcqzt', 'OPkTZNZYrG', 3, 3, 3, 2, 'mnxMWdmdBn', 'C', '2025-07-25 10:46:37');
INSERT INTO `question` VALUES (4, 'Klg6yxlw3e', 'oR4ShxNO6f', 't8k6QDJF4V', 'tLWoUzyZER', 'Q0gnGYazGV', 4, 4, 4, 2, '46KhVmE7Bf', 'D', '2025-07-25 14:01:00');
INSERT INTO `question` VALUES (5, 'gRBc5JXXTB', 'uoqcZ5ox0e', 'xHlNXWwyvu', 'VPoWKYac88', 'HynWmW86i7', 5, 5, 5, 2, 'PyHClzrcV3', 'A', '2025-07-25 12:22:39');
INSERT INTO `question` VALUES (6, '请求', '请', '去', '的', ' 吧', 6, 6, 6, 2, '对都都', 'A', '2025-07-25 10:46:41');
INSERT INTO `question` VALUES (7, '看', '可', '卡', '快看', '克', 7, 7, 7, 2, 'の', 'B', '2025-07-25 10:46:43');

-- ----------------------------
-- Table structure for speaker
-- ----------------------------
DROP TABLE IF EXISTS `speaker`;
CREATE TABLE `speaker`  (
  `SpeakerID` int NOT NULL AUTO_INCREMENT,
  `Sname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `Spassword` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `Smail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `Sphone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `Ssex` int NULL DEFAULT NULL COMMENT '1男',
  PRIMARY KEY (`SpeakerID` DESC, `Sname` DESC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of speaker
-- ----------------------------
INSERT INTO `speaker` VALUES (4, 'kkkk', '$2a$10$w1Cqupl.WwXmqF45UohWz.QPOKn6H6yxFjWoNQ4EmucEimf/Adune', 'kkkk@mail.com', '1577411', 1);
INSERT INTO `speaker` VALUES (1, '鸡然', '$2a$10$3KH/xuG15XjlQj4do.CEcuBE8s6IRz90h7VEudq7izacVMuY1UkH.', 'ropamadsola@mail.com', '777777777', 1);

-- ----------------------------
-- Table structure for speech
-- ----------------------------
DROP TABLE IF EXISTS `speech`;
CREATE TABLE `speech`  (
  `SpeechID` int NOT NULL AUTO_INCREMENT COMMENT '演讲ID',
  `SpeakerID` int NULL DEFAULT NULL COMMENT '演讲者ID',
  `OrganizerID` int NULL DEFAULT NULL COMMENT '组织者ID',
  `LisconSpeID` int NULL DEFAULT NULL COMMENT '演讲--听众',
  `QueconSpeID` int NULL DEFAULT NULL COMMENT '演讲--题目',
  `SresultsID` int NULL DEFAULT NULL COMMENT '演讲分析ID',
  `ClaconSpeID` int NULL DEFAULT NULL COMMENT '上课数据---演讲ID',
  `Otime` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `Stime` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `Sstatus` int NULL DEFAULT NULL COMMENT '1进行中   0已结束',
  `Speechname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '演讲名称',
  `filename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`SpeechID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of speech
-- ----------------------------
INSERT INTO `speech` VALUES (1, 1, 1, 1, 1, 1, 1, '2025-07-16 16:28:14', '2025-07-16 16:28:17', 0, '演讲1', NULL);
INSERT INTO `speech` VALUES (2, 1, 1, 2, 1, 2, 2, '2025-07-16 16:31:46', '2025-07-16 16:31:48', 1, '演讲2', NULL);
INSERT INTO `speech` VALUES (3, 1, 1, 3, 1, 3, 3, '2025-07-20 19:52:05', '2025-07-20 19:52:08', 1, '演讲3', NULL);
INSERT INTO `speech` VALUES (4, 1, NULL, NULL, NULL, NULL, NULL, NULL, '2025-07-25 09:46:52', 1, '666666', NULL);
INSERT INTO `speech` VALUES (5, 1, NULL, NULL, NULL, NULL, NULL, NULL, '2025-07-25 09:51:19', 1, '666666问问', NULL);
INSERT INTO `speech` VALUES (6, 1, NULL, NULL, NULL, NULL, NULL, NULL, '2025-07-25 10:04:17', 1, '请求', NULL);
INSERT INTO `speech` VALUES (7, 1, NULL, NULL, NULL, NULL, NULL, NULL, '2025-07-25 15:35:32', 1, '666666', NULL);
INSERT INTO `speech` VALUES (8, 1, NULL, NULL, NULL, NULL, NULL, NULL, '2025-07-25 15:41:11', 1, '666666', NULL);
INSERT INTO `speech` VALUES (9, 1, NULL, NULL, NULL, NULL, NULL, NULL, '2025-07-25 15:41:42', 1, '666666', NULL);

-- ----------------------------
-- Table structure for sresults
-- ----------------------------
DROP TABLE IF EXISTS `sresults`;
CREATE TABLE `sresults`  (
  `SresultsID` int NOT NULL AUTO_INCREMENT COMMENT '演讲分析结果ID',
  `SpeechID` int NULL DEFAULT NULL COMMENT '演讲ID',
  `Scorrectrate` float NULL DEFAULT NULL COMMENT '演讲----正确率',
  PRIMARY KEY (`SresultsID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sresults
-- ----------------------------

-- ----------------------------
-- Table structure for stuanswers
-- ----------------------------
DROP TABLE IF EXISTS `stuanswers`;
CREATE TABLE `stuanswers`  (
  `StuanswersID` int NOT NULL AUTO_INCREMENT COMMENT '学生答案ID',
  `Sanscontent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '1----A   2----B   3----C   4-----D',
  `QuestionID` int NULL DEFAULT NULL COMMENT '题目ID',
  `State` int NULL DEFAULT NULL COMMENT '1----正确     0---- 错误',
  `QS` int UNSIGNED NOT NULL COMMENT '0---未抽到   1---抽到，未完成  2---抽到，已完成',
  `ListenerID` int NULL DEFAULT NULL,
  PRIMARY KEY (`StuanswersID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stuanswers
-- ----------------------------
INSERT INTO `stuanswers` VALUES (1, '0', 1, 1, 2, 1);
INSERT INTO `stuanswers` VALUES (2, 'B', 2, 1, 2, 1);
INSERT INTO `stuanswers` VALUES (3, 'A', 3, 1, 0, 1);
INSERT INTO `stuanswers` VALUES (4, 'B', 4, 0, 1, 1);
INSERT INTO `stuanswers` VALUES (5, '0', 6, 1, 1, 1);
INSERT INTO `stuanswers` VALUES (6, 'A', 7, 0, 2, 1);

-- ----------------------------
-- Table structure for stuconque
-- ----------------------------
DROP TABLE IF EXISTS `stuconque`;
CREATE TABLE `stuconque`  (
  `StuconQueID` int NOT NULL AUTO_INCREMENT COMMENT '学生答案---题目',
  `StuanswersID` int NULL DEFAULT NULL,
  `QuestionID` int NULL DEFAULT NULL,
  `ListenerID` int NULL DEFAULT NULL,
  PRIMARY KEY (`StuconQueID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stuconque
-- ----------------------------

-- ----------------------------
-- Table structure for tresults
-- ----------------------------
DROP TABLE IF EXISTS `tresults`;
CREATE TABLE `tresults`  (
  `TresultsID` int NOT NULL AUTO_INCREMENT COMMENT '测试分析结果',
  `Tcorrectrate` float NULL DEFAULT NULL COMMENT '测试正确率',
  `Trank` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '位次',
  `ListenerID` int NULL DEFAULT NULL COMMENT '听众ID',
  `SpeechID` int NULL DEFAULT NULL COMMENT '演讲ID',
  `Totalpeo` int NULL DEFAULT NULL COMMENT '总人数',
  PRIMARY KEY (`TresultsID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tresults
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
