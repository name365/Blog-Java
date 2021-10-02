-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(50) NOT NULL COMMENT '用户名',
  `nick_name` varchar(150) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(150) DEFAULT NULL COMMENT '头像',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(40) DEFAULT NULL COMMENT '加密盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '机构ID',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `last_update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='用户管理';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '管理员', null, 'bd1718f058d8a02468134432b8656a86', 'YzcmCZNvbXocrsz9dm8e', 'admin@qq.com', '13612345678', '1', '4', 'admin', '2018-08-14 11:11:11', 'admin', '2018-08-14 11:11:11', '0');
INSERT INTO `sys_user` VALUES ('2', 'liubei', '刘备', null, 'fd80ebd493a655608dc893a9f897d845', 'YzcmCZNvbXocrsz9dm8e', 'test@qq.com', '13889700023', '1', '7', 'admin', '2018-09-23 19:43:00', 'admin', '2019-01-10 11:41:13', '0');
INSERT INTO `sys_user` VALUES ('3', 'zhaoyun', '赵云', null, 'fd80ebd493a655608dc893a9f897d845', 'YzcmCZNvbXocrsz9dm8e', 'test@qq.com', '13889700023', '1', '7', 'admin', '2018-09-23 19:43:44', 'admin', '2018-09-23 19:43:52', '0');
INSERT INTO `sys_user` VALUES ('4', 'zhugeliang', '诸葛亮', null, 'fd80ebd493a655608dc893a9f897d845', 'YzcmCZNvbXocrsz9dm8e', 'test@qq.com', '13889700023', '7', '11', 'admin', '2018-09-23 19:44:23', 'admin', '2018-09-23 19:44:29', '0');
INSERT INTO `sys_user` VALUES ('5', 'caocao', '曹操', null, 'fd80ebd493a655608dc893a9f897d845', 'YzcmCZNvbXocrsz9dm8e', 'test@qq.com', '13889700023', '1', '8', 'admin', '2018-09-23 19:45:32', 'admin', '2019-01-10 17:59:14', '0');
INSERT INTO `sys_user` VALUES ('6', 'dianwei', '典韦', null, 'fd80ebd493a655608dc893a9f897d845', 'YzcmCZNvbXocrsz9dm8e', 'test@qq.com', '13889700023', '1', '10', 'admin', '2018-09-23 19:45:48', 'admin', '2018-09-23 19:45:57', '0');
INSERT INTO `sys_user` VALUES ('7', 'xiahoudun', '夏侯惇', null, 'fd80ebd493a655608dc893a9f897d845', 'YzcmCZNvbXocrsz9dm8e', 'test@qq.com', '13889700023', '1', '8', 'admin', '2018-09-23 19:46:09', 'admin', '2018-09-23 19:46:17', '0');
INSERT INTO `sys_user` VALUES ('8', 'xunyu', '荀彧', null, 'fd80ebd493a655608dc893a9f897d845', 'YzcmCZNvbXocrsz9dm8e', 'test@qq.com', '13889700023', '1', '10', 'admin', '2018-09-23 19:46:38', 'admin', '2018-11-04 15:33:17', '0');
INSERT INTO `sys_user` VALUES ('9', 'sunquan', '孙权', null, 'fd80ebd493a655608dc893a9f897d845', 'YzcmCZNvbXocrsz9dm8e', 'test@qq.com', '13889700023', '1', '10', 'admin', '2018-09-23 19:46:54', 'admin', '2018-09-23 19:47:03', '0');
INSERT INTO `sys_user` VALUES ('0', 'zhouyu', '周瑜', null, 'fd80ebd493a655608dc893a9f897d845', 'YzcmCZNvbXocrsz9dm8e', 'test@qq.com', '13889700023', '1', '11', 'admin', '2018-09-23 19:47:28', 'admin', '2018-09-23 19:48:04', '0');
INSERT INTO `sys_user` VALUES ('11', 'luxun', '陆逊', null, 'fd80ebd493a655608dc893a9f897d845', 'YzcmCZNvbXocrsz9dm8e', 'test@qq.com', '13889700023', '1', '11', 'admin', '2018-09-23 19:47:44', 'admin', '2018-09-23 19:47:58', '0');
INSERT INTO `sys_user` VALUES ('12', 'huanggai', '黄盖', null, 'fd80ebd493a655608dc893a9f897d845', 'YzcmCZNvbXocrsz9dm8e', 'test@qq.com', '13889700023', '1', '11', 'admin', '2018-09-23 19:48:38', 'admin', '2018-09-23 19:49:02', '0');

