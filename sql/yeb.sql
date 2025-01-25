/*
 Navicat Premium Data Transfer

 Source Server         : paper_order_system
 Source Server Type    : MySQL
 Source Server Version : 50744 (5.7.44-log)
 Source Host           : 47.122.118.117:3306
 Source Schema         : paper_order_system

 Target Server Type    : MySQL
 Target Server Version : 50744 (5.7.44-log)
 File Encoding         : 65001

 Date: 26/01/2025 00:14:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for audit_logs
-- ----------------------------
DROP TABLE IF EXISTS `audit_logs`;
CREATE TABLE `audit_logs`  (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志唯一标识，自增主键',
                               `user_id` bigint(20) NOT NULL COMMENT '操作用户ID，关联到 users.id，不为空',
                               `action` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作描述，如“创建订单”、“删除用户”等，不为空',
                               `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间，默认当前时间',
                               `details` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '详细信息，建议以 JSON 格式存储，可为空',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '记录用户操作日志，增强系统安全性和可追溯性' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of audit_logs
-- ----------------------------

-- ----------------------------
-- Table structure for completion_statuses
-- ----------------------------
DROP TABLE IF EXISTS `completion_statuses`;
CREATE TABLE `completion_statuses`  (
                                        `id` int(11) NOT NULL COMMENT '状态唯一标识，如 0, 1, 2, 3',
                                        `status_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态名称，如“进行中”、“已完成”等',
                                        `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态描述，详细描述状态含义，可为空',
                                        `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，默认当前时间',
                                        `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间，自动更新时间',
                                        PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用于管理订单的完成状况，增强灵活性和可维护性' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of completion_statuses
-- ----------------------------
INSERT INTO `completion_statuses` VALUES (0, '进行中', '订单正在进行中', '2025-01-21 02:03:58', '2025-01-21 02:03:58');
INSERT INTO `completion_statuses` VALUES (1, '已完成', '订单已完成', '2025-01-21 02:03:58', '2025-01-21 02:03:58');
INSERT INTO `completion_statuses` VALUES (2, '纠纷中', '订单存在纠纷', '2025-01-21 02:03:58', '2025-01-21 02:03:58');
INSERT INTO `completion_statuses` VALUES (3, '退单', '订单已退回', '2025-01-21 02:03:58', '2025-01-21 02:03:58');

-- ----------------------------
-- Table structure for menu_role
-- ----------------------------
DROP TABLE IF EXISTS `menu_role`;
CREATE TABLE `menu_role`  (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '关联标识，自增主键',
                              `mid` bigint(20) NOT NULL COMMENT '菜单ID，关联到 menus.id',
                              `rid` bigint(20) NOT NULL COMMENT '角色ID，关联到 roles.id',
                              `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，默认当前时间',
                              `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间，自动更新时间',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '关联菜单与角色，实现多对多关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu_role
-- ----------------------------
INSERT INTO `menu_role` VALUES (1, 1, 1, '2025-01-25 17:59:53', '2025-01-25 17:59:53');
INSERT INTO `menu_role` VALUES (2, 2, 2, '2025-01-25 17:59:53', '2025-01-25 23:05:07');
INSERT INTO `menu_role` VALUES (3, 3, 3, '2025-01-25 17:59:53', '2025-01-25 23:05:07');
INSERT INTO `menu_role` VALUES (4, 4, 4, '2025-01-25 17:59:53', '2025-01-25 23:05:08');
INSERT INTO `menu_role` VALUES (5, 5, 5, '2025-01-25 17:59:53', '2025-01-25 23:05:22');

-- ----------------------------
-- Table structure for menus
-- ----------------------------
DROP TABLE IF EXISTS `menus`;
CREATE TABLE `menus`  (
                          `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单唯一标识，自增主键',
                          `url` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单对应的URL',
                          `path` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单路径，用于路由',
                          `component` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '前端组件名称',
                          `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
                          `iconCls` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标类名',
                          `keepAlive` tinyint(1) NULL DEFAULT NULL COMMENT '是否保持组件状态，1为是，0为否',
                          `requireAuth` tinyint(1) NULL DEFAULT NULL COMMENT '是否需要权限校验，1为是，0为否',
                          `parentId` int(11) NULL DEFAULT NULL COMMENT '父菜单ID，用于菜单层级',
                          `enabled` tinyint(1) NULL DEFAULT NULL COMMENT '是否启用该菜单，1为启用，0为禁用',
                          PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menus
-- ----------------------------
INSERT INTO `menus` VALUES (1, '/admin/**', '/admin', 'AdminComponent', '管理员', 'icon-admin', 1, 1, NULL, 1);
INSERT INTO `menus` VALUES (2, '/customer/**', '/customer', 'CustomerServiceComponent', '客服', 'icon-customer-service', 1, 1, NULL, 1);
INSERT INTO `menus` VALUES (3, '/dispatch/**', '/dispatch', 'DispatchingComponent', '派单员', 'icon-dispatch', 1, 1, NULL, 1);
INSERT INTO `menus` VALUES (4, '/writer/**', '/writer', 'WriterComponent', '写手', 'icon-writer', 1, 1, NULL, 1);
INSERT INTO `menus` VALUES (5, '/finance/**', '/finance', 'FinanceComponent', '财务', 'icon-finance', 1, 1, NULL, 1);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
                           `id` bigint(20) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '订单唯一标识，自增主键',
                           `order_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单编号，唯一且不为空',
                           `writer_id` bigint(20) NULL DEFAULT NULL COMMENT '写手ID，关联到 users.id',
                           `dispatcher_id` bigint(20) NULL DEFAULT NULL COMMENT '派单员ID，关联到 users.id',
                           `customer_service_id` bigint(20) NOT NULL COMMENT '客服ID，关联到 users.id',
                           `paper_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '论文类型，如“本科论文”、“硕士论文”等',
                           `assigned_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '接单价格',
                           `writer_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '写手价格',
                           `dispatcher_commission` decimal(10, 2) NULL DEFAULT NULL COMMENT '派单员提成',
                           `customer_service_commission` decimal(10, 2) NULL DEFAULT NULL COMMENT '客服提成',
                           `is_admin_paid` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '管理员是否结款',
                           `is_writer_paid` tinyint(1) NULL DEFAULT 0 COMMENT '写手是否结款',
                           `is_dispatcher_paid` tinyint(1) NULL DEFAULT 0 COMMENT '派单员是否结款',
                           `assignment_date` date NULL DEFAULT NULL COMMENT '指派日期',
                           `deadline_date` date NULL DEFAULT NULL COMMENT '截止日期',
                           `proposal_deadline` date NULL DEFAULT NULL COMMENT '开题报告截止日期',
                           `is_assigned_to_dispatcher` tinyint(1) NULL DEFAULT 0 COMMENT '是否已安排给派单员，默认 FALSE',
                           `is_assigned_to_writer` tinyint(1) NULL DEFAULT 0 COMMENT '是否已安排给写手，默认 FALSE',
                           `completion_status_id` int(11) NULL DEFAULT NULL COMMENT '订单完成状态，关联到 completion_statuses.id，不为空',
                           `wechat_group_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信群名称，用于记录关联的微信群名，可为空',
                           `remarks` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '订单备注，用于记录额外信息，可为空',
                           `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除标志，默认 FALSE',
                           `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，默认当前时间',
                           `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间，自动更新时间',
                           PRIMARY KEY (`id`) USING BTREE,
                           UNIQUE INDEX `order_number`(`order_number`) USING BTREE,
                           INDEX `idx_orders_order_number`(`order_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '存储论文订单的详细信息，包括定价、提成、关联用户、订单状态及微信群名称' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色英文名称，唯一',
                          `nameZh` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色中文名称',
                          PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES (1, 'ROLE_admin', '管理员');
INSERT INTO `roles` VALUES (2, 'ROLE_customer', '客服');
INSERT INTO `roles` VALUES (3, 'ROLE_dispatch', '派单员');
INSERT INTO `roles` VALUES (4, 'ROLE_writer', '写手');
INSERT INTO `roles` VALUES (5, 'ROLE_finance', '财务');

-- ----------------------------
-- Table structure for user_roles
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles`  (
                               `user_id` bigint(20) NOT NULL COMMENT '用户ID，关联到 users.id',
                               `role_id` bigint(20) NOT NULL COMMENT '角色ID，关联到 roles.id',
                               PRIMARY KEY (`user_id`, `role_id`) USING BTREE COMMENT '组合主键，确保每个用户与角色的唯一关联'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '关联用户与角色，实现多对多关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_roles
-- ----------------------------
INSERT INTO `user_roles` VALUES (1, 1);
INSERT INTO `user_roles` VALUES (2, 2);
INSERT INTO `user_roles` VALUES (3, 3);
INSERT INTO `user_roles` VALUES (4, 4);
INSERT INTO `user_roles` VALUES (5, 5);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
                          `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户唯一标识，自增主键',
                          `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名，唯一且不为空',
                          `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码，存储加密后的密码，不为空',
                          `real_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '真实姓名，不为空',
                          `wechat_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信号，可为空',
                          `alipay_account` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付宝账号，可为空',
                          `added_by` bigint(20) NULL DEFAULT NULL COMMENT '添加此用户的管理员ID，可为空',
                          `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除标志，默认 FALSE',
                          `remarks` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注信息，可为空',
                          `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，默认当前时间',
                          `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间，自动更新时间',
                          `enabled` tinyint(1) NULL DEFAULT NULL COMMENT '是否启用 1启用，0禁用',
                          PRIMARY KEY (`id`) USING BTREE,
                          UNIQUE INDEX `username`(`username`) USING BTREE,
                          INDEX `idx_users_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '存储系统中所有用户的信息，包括管理员、写手、客服和派单员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'admin', '$2a$10$tQDwEZz4srnwXgf6iXDzQe4Bux0OcSx/T2C20HVqc4ZHeQog7RSeO', '管理员A', 'admin_wechat', 'admin_alipay', NULL, 0, '系统创建的管理员账户', '2025-01-25 17:55:36', '2025-01-25 21:46:57', 1);
INSERT INTO `users` VALUES (2, 'customer_service', '$2a$2a$10$tQDwEZz4srnwXgf6iXDzQe4Bux0OcSx/T2C20HVqc4ZHeQog7RSeO', '客服B', 'cs_wechat', 'cs_alipay', NULL, 0, '系统创建的客服账户', '2025-01-25 17:55:36', '2025-01-25 23:05:32', 1);
INSERT INTO `users` VALUES (3, 'dispatcher', '$2a$2a$10$tQDwEZz4srnwXgf6iXDzQe4Bux0OcSx/T2C20HVqc4ZHeQog7RSeO', '派单员C', 'dispatcher_wechat', 'dispatcher_alipay', NULL, 0, '系统创建的派单员账户', '2025-01-25 17:55:36', '2025-01-25 23:05:33', 1);
INSERT INTO `users` VALUES (4, 'writer', '$2a$10$tQDwEZz4srnwXgf6iXDzQe4Bux0OcSx/T2C20HVqc4ZHeQog7RSeO', '写手D', 'writer_wechat', 'writer_alipay', NULL, 0, '系统创建的写手账户', '2025-01-25 17:55:36', '2025-01-25 23:05:37', 1);

SET FOREIGN_KEY_CHECKS = 1;
