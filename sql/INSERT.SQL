-- user init insert
INSERT INTO `t_console_user` VALUES (1, 'azir', 'azir', 'ee7e4c5bc02861ffac25c8e1825f3da6', NOW(), NOW(), 0, 'https://blogimg.bytestroll.com/avatar/bnyte.jpg', 1, -1, 0);

-- menu init insert
INSERT INTO `t_console_menu` VALUES (null, -1, '首页', 'SmileOutlined', NOW(), NOW(), 0, '/welcome', 2, 0);
INSERT INTO `t_console_menu` VALUES (null, -1, '路由管理', 'SlidersOutlined', NOW(), NOW(), 0, '/menu', 3, 0);
INSERT INTO `t_console_menu` VALUES (null, 2, '路由列表', 'smile', NOW(), NOW(), 0, '/menu/list', 3, 0);
INSERT INTO `t_console_menu` VALUES (null, -1, '用户管理', 'UserOutlined', NOW(), NOW(), 0, '/account', 3, 0);
INSERT INTO `t_console_menu` VALUES (null, -1, '集群管理', 'ClusterOutlined', NOW(), NOW(), 0, '/cluster', 3, 0);
