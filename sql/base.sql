-- 用户表
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户 id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '用户密码，MD5加密',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  `role_id` INT NOT NULL COMMENT '角色 id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 系统初始化添加一个超级管理员
INSERT INTO t_user(username, password, create_time, update_time, role_id) VALUES ('admin', '123456', NOW(), NOW(), 1);

-- 角色表
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE t_role (
  `role_id` INT AUTO_INCREMENT COMMENT '角色 id',
  `role_name` VARCHAR (500) NOT NULL COMMENT '角色名',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  `remark` VARCHAR (500) COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO t_role(role_name, create_time, update_time) VALUES ('超级管理员', NOW(), NOW());

-- 菜单表
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE t_menu (
  `menu_id` INT AUTO_INCREMENT COMMENT '菜单 id',
  `name` VARCHAR (50) NOT NULL COMMENT '菜单名称',
  `url` VARCHAR(500) NOT NULL COMMENT '菜单url',
  `parent_id` INT NOT NULL DEFAULT 0 COMMENT '父菜单id,若为0，则为顶级菜单',
  `type` INT NOT NULL DEFAULT 1 COMMENT '菜单级别，1表示一级菜单，2表示二级菜单',
  `icon` VARCHAR(50) COMMENT '菜单图标',
  `sort` INT NOT NULL DEFAULT 0 COMMENT '菜单排序, 0表示排第一',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  `remark` VARCHAR (500) COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO t_menu(name, url, parent_id, type, sort, create_time, update_time) VALUES ('订单管理', '/sys/order/manager/list', 0, 1, 0, NOW(), NOW());
INSERT INTO t_menu(name, url, parent_id, type, sort, create_time, update_time) VALUES ('订单信息', '/sys/order/manager/list', 4, 2, 0, NOW(), NOW());
INSERT INTO t_menu(name, url, parent_id, type, sort, create_time, update_time) VALUES ('系统管理', '/sys/user/manager/list', 0, 1, 1, NOW(), NOW());
INSERT INTO t_menu(name, url, parent_id, type, sort, create_time, update_time) VALUES ('用户管理', '/sys/user/manager/list', 1, 2, 0, NOW(), NOW());
INSERT INTO t_menu(name, url, parent_id, type, sort, create_time, update_time) VALUES ('角色管理', '/sys/role/manager/list', 1, 2, 1, NOW(), NOW());

-- 角色菜单关系表
DROP TABLE IF EXISTS `t_role_menu_rel`;
CREATE TABLE t_role_menu_rel (
  `id` INT AUTO_INCREMENT COMMENT 'id',
  `role_id` INT NOT NULL COMMENT '角色id',
  `menu_id` INT NOT NULL COMMENT '菜单id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;





