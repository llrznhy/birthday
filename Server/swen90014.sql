/*
 Navicat Premium Dump SQL

 Source Server         : SWEN90014
 Source Server Type    : MySQL
 Source Server Version : 50710 (5.7.10)
 Source Host           : localhost:3306
 Source Schema         : swen90054

 Target Server Type    : MySQL
 Target Server Version : 50710 (5.7.10)
 File Encoding         : 65001

 Date: 11/09/2024 00:46:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for f_user
-- ----------------------------
DROP TABLE IF EXISTS `f_user`;
CREATE TABLE `f_user` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'User ID should be unique',
  `userName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `role` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `avatar` longblob,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for option
-- ----------------------------
DROP TABLE IF EXISTS `option`;
CREATE TABLE `option`  (
  `id` int(11) NOT NULL,
  `optionContext` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `optionDetail` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `optionBelongto` int(11) NULL DEFAULT NULL,
  `score` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `optionBelongto`(`optionBelongto`) USING BTREE,
  CONSTRAINT `optionBelongto` FOREIGN KEY (`optionBelongto`) REFERENCES `question` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of option
-- ----------------------------
INSERT INTO `option` VALUES (0, 'option A', 'detail', 0, 3);
INSERT INTO `option` VALUES (1, 'option B', 'detail', 0, 2);
INSERT INTO `option` VALUES (2, 'option C', 'detail', 0, 1);
INSERT INTO `option` VALUES (3, 'option D', 'detail', 0, 0);
INSERT INTO `option` VALUES (4, 'option A', 'detail', 1, 3);
INSERT INTO `option` VALUES (5, 'option B', 'detail', 1, 2);
INSERT INTO `option` VALUES (6, 'option C', 'detail', 1, 1);
INSERT INTO `option` VALUES (7, 'option A', 'detail', 2, 3);
INSERT INTO `option` VALUES (8, 'option B', 'detail', 2, 2);
INSERT INTO `option` VALUES (9, 'option C', 'detail', 2, 1);
INSERT INTO `option` VALUES (10, 'option A', 'detail', 3, 3);
INSERT INTO `option` VALUES (11, 'option B', 'detail', 3, 2);
INSERT INTO `option` VALUES (12, 'option C', 'detail', 3, 1);
INSERT INTO `option` VALUES (13, 'option A', 'detail', 4, 3);
INSERT INTO `option` VALUES (14, 'option B', 'detail', 4, 2);
INSERT INTO `option` VALUES (15, 'option C', 'detail', 4, 1);
INSERT INTO `option` VALUES (16, 'option A', 'detail', 5, 3);
INSERT INTO `option` VALUES (17, 'option B', 'detail', 5, 2);
INSERT INTO `option` VALUES (18, 'option C', 'detail', 5, 1);
INSERT INTO `option` VALUES (19, 'option A', 'detail', 6, 3);
INSERT INTO `option` VALUES (20, 'option B', 'detail', 6, 2);
INSERT INTO `option` VALUES (21, 'option C', 'detail', 6, 1);
INSERT INTO `option` VALUES (22, 'option A', 'detail', 7, 3);
INSERT INTO `option` VALUES (23, 'option B', 'detail', 7, 2);
INSERT INTO `option` VALUES (24, 'option C', 'detail', 7, 1);
INSERT INTO `option` VALUES (25, 'option A', 'detail', 8, 3);
INSERT INTO `option` VALUES (26, 'option B', 'detail', 8, 2);
INSERT INTO `option` VALUES (27, 'option C', 'detail', 8, 1);

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` int(11) NOT NULL COMMENT 'Primary Key',
  `questionContext` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `questionCriteria` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `questionBelongto` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `questionBelongto`(`questionBelongto`) USING BTREE,
  CONSTRAINT `questionBelongto` FOREIGN KEY (`questionBelongto`) REFERENCES `questionnaire` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (0, 'question test 01','question criteria', 0);
INSERT INTO `question` VALUES (1, 'question test 02','question criteria', 0);
INSERT INTO `question` VALUES (2, 'question test 03','question criteria', 0);
INSERT INTO `question` VALUES (3, 'question test 01','question criteria', 1);
INSERT INTO `question` VALUES (4, 'question test 02','question criteria', 1);
INSERT INTO `question` VALUES (5, 'question test 03','question criteria', 1);
INSERT INTO `question` VALUES (6, 'question test 01','question criteria', 2);
INSERT INTO `question` VALUES (7, 'question test 02','question criteria', 2);
INSERT INTO `question` VALUES (8, 'question test 03','question criteria', 2);

-- ----------------------------
-- Table structure for questionnaire
-- ----------------------------
DROP TABLE IF EXISTS `questionnaire`;
CREATE TABLE `questionnaire`  (
  `id` int(11) NOT NULL COMMENT 'Primary key',
  `questionnaireName` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of questionnaire
-- ----------------------------
INSERT INTO `questionnaire` VALUES (0, 'Math', 'This is a description');
INSERT INTO `questionnaire` VALUES (1, 'Chinese', 'This is a description');
INSERT INTO `questionnaire` VALUES (2, 'English', 'This is a description.');

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for Marking
-- ----------------------------
DROP TABLE IF EXISTS `marking`;
CREATE TABLE marking (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       studentId BIGINT NOT NULL,
                       questionnaireId BIGINT NOT NULL,
                       questionId BIGINT NOT NULL,
                       `option` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL
)ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of questionnaire
-- ----------------------------
INSERT INTO `marking` VALUES (0,10,1,1, 'option A');
INSERT INTO `marking` VALUES (0,10,1,1, 'option B');
INSERT INTO `marking` VALUES (0,10,1,1, 'option C');

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE grade (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       studentId BIGINT NOT NULL,
                       moduleId BIGINT NOT NULL,
                       optionID BIGINT NOT NULL,
                       `optionContext` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL
)ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO `grade` VALUES (1, 1, 3, 1, 'optionA');
INSERT INTO `grade` VALUES (2, 2, 3, 2, 'optionB');
INSERT INTO `grade` VALUES (3, 3, 3, 3, 'optionC');
INSERT INTO `grade` VALUES (4, 4, 3, 4, 'optionD');
INSERT INTO `grade` VALUES (5, 5, 3, 5, 'optionE');
