/*
Navicat MySQL Data Transfer

Source Server         : 32
Source Server Version : 50636
Source Host           : 218.3.247.227:3206
Source Database       : dyl-blog

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2018-12-01 20:03:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for blog_article
-- ----------------------------
DROP TABLE IF EXISTS `blog_article`;
CREATE TABLE `blog_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '所属用户',
  `title` varchar(50) DEFAULT NULL COMMENT '文章名称',
  `content` text COMMENT '文章内容',
  `overview` varchar(200) DEFAULT NULL,
  `res_img` int(11) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL COMMENT '栏目',
  `classify` tinyint(4) DEFAULT NULL COMMENT '所属分类',
  `open` tinyint(4) DEFAULT NULL COMMENT '0:私有; 1: 公开; 2: 好友可见;',
  `create_time` datetime DEFAULT NULL COMMENT '发布时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_top` tinyint(4) DEFAULT NULL COMMENT '置顶',
  `see_count` int(11) DEFAULT NULL COMMENT '查看人数',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_article
-- ----------------------------
INSERT INTO `blog_article` VALUES ('6', '1', 'Java 配置环境变量', '<ol>\r\n	<li>打开我的电脑--属性--<span style=\"color:#ff0000\">高级</span>--环境变量<br />\r\n	&nbsp;</li>\r\n	<li>新建系统变量<span style=\"color:#c0392b\"><strong>JAVA_HOME</strong></span>和<span style=\"color:#c0392b\"><strong>CLASSPATH</strong></span>\r\n	<blockquote>\r\n	<pre style=\"margin-left:0in; margin-right:0in\">\r\n变量名：JAVA_HOME\r\n\r\n变量值：C:\\Program Files\\Java\\jdk1.7.0\r\n\r\n变量名：CLASSPATH\r\n\r\n变量值：.;%JAVA_HOME%\\lib\\dt.jar;%JAVA_HOME%\\lib\\tools.jar;</pre>\r\n	</blockquote>\r\n	</li>\r\n	<li>选择&ldquo;系统变量&rdquo;中变量名为&ldquo;Path&rdquo;的环境变量，双击该变量，把JDK安装路径中bin目录的绝对路径，添加到Path变量的值中，并使用半角的分号和已有的路径进行分隔。&nbsp;\r\n	<blockquote>\r\n	<pre style=\"margin-left:0in; margin-right:0in\">\r\n变量名：Path\r\n\r\n变量值：;%JAVA_HOME%\\bin;%JAVA_HOME%\\jre\\bin;</pre>\r\n	</blockquote>\r\n\r\n	<p style=\"margin-left:0in; margin-right:0in\"><span style=\"color:#c0392b\"><strong>验证</strong></span>环境变量是否配置成功，<strong><span style=\"color:#c0392b\">javac</span></strong>&nbsp;有下面输出即可。</p>\r\n\r\n	<p style=\"margin-left:0in; margin-right:0in\"><img height=\"319\" src=\"/image/pic/2018/5\" width=\"610\" /></p>\r\n\r\n	<p style=\"margin-left:0in; margin-right:0in\">&nbsp;</p>\r\n\r\n	<p style=\"margin-left:0in; margin-right:0in\">&nbsp;</p>\r\n\r\n	<p style=\"margin-left:0in; margin-right:0in\">&nbsp;</p>\r\n\r\n	<p style=\"margin-left:0in; margin-right:0in\">&nbsp;</p>\r\n\r\n	<p style=\"margin-left:0in; margin-right:0in\">&nbsp;</p>\r\n	</li>\r\n</ol>\r\n', '打开我的电脑--属性--高级--环境变量新建系统变量JAVA_HOME和CLASSPATH变量名：JAVA_HOME\n\n变量值：C:\\Program Files\\Java\\jdk1.7.0\n\n变量名：', '5', '1', '1', '1', '2018-11-23 16:50:57', '2018-11-29 22:02:02', '1', '52', '1');
INSERT INTO `blog_article` VALUES ('7', '1', 'Redis 开启远程访问', '<p>连接服务器：&nbsp;redis-cli -h host -p port -a password</p>\n\n<p><img src=\"/image/pic/20181127 211851/6\" width=\"703\" /></p>\n\n<blockquote>&nbsp;redis<span style=\"color:#ff0000\">开启远程访问</span></blockquote>\n\n<p>redis默认只允许本地访问，要使redis可以远程访问可以修改<strong>redis.conf</strong></p>\n\n<p><span style=\"color:#ff0000\">解决办法：</span></p>\n\n<p>注释掉bind 127.0.0.1可以使所有的ip访问redis</p>\n\n<p>若是想指定多个ip访问，但并不是全部的ip访问，可以bind</p>\n\n<blockquote>&nbsp;在redis3.2之后，redis增加了<span style=\"color:#ff0000\">protected-mode</span></blockquote>\n\n<p>在这个模式下，即使注释掉了bind 127.0.0.1，再访问redisd时候还是报错</p>\n\n<p><span style=\"color:#ff0000\">修改办法：</span></p>\n\n<p><strong>protected-mode no</strong></p>\n\n<div>&nbsp;</div>\n\n<p>&nbsp;</p>\n\n<div>&nbsp;</div>\n\n<p>&nbsp;</p>\n', '连接服务器： redis-cli -h host -p port -a password redis', '6', '1', '2', '1', '2018-11-27 21:19:38', '2018-11-30 21:26:00', null, '47', '1');
INSERT INTO `blog_article` VALUES ('9', '1', 'IDEA 开发工具激活', '<pre style=\"text-align:center\">\n<strong>首先感谢<span style=\"color:#3498db\"> lanyus</span> 的支持</strong></pre>\n\n<ol>\n	<li><a href=\"http://idea.lanyus.com/\">http://idea.lanyus.com</a>&nbsp;获取code，然后打开IDEA -》HELP -》Register<br />\n	<br />\n	<img height=\"430\" src=\"/image/pic/20181128 103046/8\" width=\"610\" /><br />\n	&nbsp;</li>\n	<li>修改hosts文件 (<strong>‪C:\\Windows\\System32\\drivers\\etc\\hosts</strong>)<br />\n	添加&nbsp;<span style=\"color:#ff0000\"> 0.0.0.0&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;account.jetbrains.com</span><br />\n	<br />\n	<img height=\"435\" src=\"/image/pic/20181128 103330/9\" width=\"610\" /><br />\n	&nbsp;</li>\n	<li>重新打开IDEA可以看到过期时间，到期后可以重复上面操作。<br />\n	&nbsp;</li>\n</ol>\n', '首先感谢 lanyus 的支持http://idea.lanyus.com 获取code，然后打开IDEA -》HELP -》Register修改hosts文件 (‪C:\\Windows\\System', '8', '1', '1', '1', '2018-11-28 10:36:34', '2018-11-29 22:07:29', '1', '49', '1');
INSERT INTO `blog_article` VALUES ('18', '1', 'IDEA 开启 lombok 注解支持', '<blockquote>\n<pre>\n<strong><span style=\"color:#c0392b\"><code>Lombok</code> </span></strong>是一种 <code>Java&trade;</code> 实用工具，可用来帮助开发人员消除 Java 的冗长，\n尤其是对于简单的 Java 对象（POJO）。它通过注解实现这一目的。</pre>\n</blockquote>\n\n<p>添加依赖：</p>\n\n<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;compile group: &#39;org.projectlombok&#39;, name: &#39;lombok&#39;, version: &#39;1.16.20&#39;</p>\n\n<p>&nbsp;</p>\n\n<p>IDEA&nbsp;<span style=\"color:#c0392b\">安装&nbsp;lombok&nbsp;插件</span>：</p>\n\n<p>&nbsp;&nbsp;&nbsp;&nbsp;<img height=\"377\" src=\"/image/pic/20181130 091043/18\" width=\"610\" /></p>\n\n<p>&nbsp; &nbsp; 安装完成<span style=\"color:#c0392b\"><strong>重启</strong></span> IDEA。</p>\n\n<p>开启 lombok 注解功能：</p>\n\n<p>&nbsp;&nbsp;&nbsp;&nbsp;<img alt=\"\" height=\"420\" src=\"/image/pic/20181130 091318/19\" width=\"610\" /></p>\n', 'Lombok 是一种 Java™ 实用工具，可用来帮助开发人员消除 Java 的冗长，​​​​​​​尤其是对于简单的 Java 对象（POJO）。它通过注解实现这一目的。添加依赖：     compi', '18', '1', '1', '1', '2018-11-30 09:17:00', '2018-11-30 09:17:51', '1', '20', '1');
INSERT INTO `blog_article` VALUES ('19', '1', 'Gradle 配置本地仓库位置', '<ul>\n	<li>设置系统环境变量<br />\n	添加变量&nbsp;<strong><span style=\"color:#c0392b\">GRADLE_HOME</span></strong>，添加值为E:\\Develop\\Gradle\\gradle-3.5；<br />\n	<span style=\"color:#c0392b\"><strong>PATH&nbsp;</strong></span>环境变量中，添加%GRADLE_HOME%/bin；<br />\n	&nbsp;</li>\n	<li>配置 gradle 本地仓库位置 将 C:\\Users\\DIYILIU\\.gradle 的默认目录复制到（目标目录）E:\\Develop\\local\\repo\\.gradle，<br />\n	然后设置系统环境变量： <span style=\"color:#c0392b\"><strong>GRADLE_USER_HOME</strong></span>=E:\\Develop\\local\\repo\\.gradle<br />\n	<br />\n	<span style=\"color:#c0392b\"><strong>注意</strong></span>：修改完成后一定要<strong><span style=\"color:#ffffff\"><span style=\"background-color:#27ae60\">重启计算机</span></span></strong>才能生效。<br />\n	&nbsp;</li>\n</ul>\n\n<p><img align=\"left\" height=\"305\" src=\"/image/pic/1543654292008/22\" style=\"margin-left:20px; margin-right:20px\" width=\"620\" /></p>\n\n<p>&nbsp;</p>\n\n<p>&nbsp;</p>\n\n<p>&nbsp;</p>\n\n<p>&nbsp;</p>\n\n<p>&nbsp;</p>\n', '设置系统环境变量添加变量 GRADLE_HOME，添加值为E:\\Develop\\Gradle\\gra', '22', '1', '1', '1', '2018-12-01 16:53:17', '2018-12-01 17:02:53', '1', '10', '1');

-- ----------------------------
-- Table structure for blog_classify
-- ----------------------------
DROP TABLE IF EXISTS `blog_classify`;
CREATE TABLE `blog_classify` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL COMMENT '父ID',
  `user_id` int(11) DEFAULT NULL COMMENT '所属用户',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `note` varchar(100) DEFAULT NULL COMMENT '备注',
  `type` tinyint(4) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8 COMMENT='博客分类';

-- ----------------------------
-- Records of blog_classify
-- ----------------------------
INSERT INTO `blog_classify` VALUES ('0', '0', '1', '首页', null, null, '1');
INSERT INTO `blog_classify` VALUES ('1', '0', '1', '编程', null, '1', '10');
INSERT INTO `blog_classify` VALUES ('2', '0', '1', '分布式', null, '1', '20');
INSERT INTO `blog_classify` VALUES ('3', '0', '1', '设计', null, '1', '30');
INSERT INTO `blog_classify` VALUES ('99', '0', '1', '其他', null, '1', '40');
INSERT INTO `blog_classify` VALUES ('100', '0', '1', '关于', null, null, '100');

-- ----------------------------
-- Table structure for blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_tag`;
CREATE TABLE `blog_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) DEFAULT NULL COMMENT '文章ID',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `sort` tinyint(4) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8 COMMENT='标签';

-- ----------------------------
-- Records of blog_tag
-- ----------------------------
INSERT INTO `blog_tag` VALUES ('31', '6', '环境变量', '1');
INSERT INTO `blog_tag` VALUES ('32', '6', 'Java', '2');
INSERT INTO `blog_tag` VALUES ('53', '9', 'IDEA', '1');
INSERT INTO `blog_tag` VALUES ('54', '9', '激活', '2');
INSERT INTO `blog_tag` VALUES ('55', '9', 'Java', '3');
INSERT INTO `blog_tag` VALUES ('56', '9', '开发工具', '4');
INSERT INTO `blog_tag` VALUES ('68', '18', 'Lombok', '1');
INSERT INTO `blog_tag` VALUES ('69', '18', 'Java', '2');
INSERT INTO `blog_tag` VALUES ('70', '18', 'IDEA', '3');
INSERT INTO `blog_tag` VALUES ('123', '7', 'Redis', '1');
INSERT INTO `blog_tag` VALUES ('124', '7', '远程访问', '2');
INSERT INTO `blog_tag` VALUES ('125', '7', '分布式', '3');
INSERT INTO `blog_tag` VALUES ('138', '19', 'Gradle', '1');
INSERT INTO `blog_tag` VALUES ('139', '19', '配置仓库', '2');
INSERT INTO `blog_tag` VALUES ('140', '19', 'Java', '3');

-- ----------------------------
-- Table structure for rel_user_role
-- ----------------------------
DROP TABLE IF EXISTS `rel_user_role`;
CREATE TABLE `rel_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rel_user_role
-- ----------------------------
INSERT INTO `rel_user_role` VALUES ('48', '1', '1');

-- ----------------------------
-- Table structure for res_img
-- ----------------------------
DROP TABLE IF EXISTS `res_img`;
CREATE TABLE `res_img` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(100) DEFAULT NULL COMMENT '路径',
  `user_id` int(11) DEFAULT NULL COMMENT '所属用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='图片资源';

-- ----------------------------
-- Records of res_img
-- ----------------------------
INSERT INTO `res_img` VALUES ('3', '.\\upload\\pic\\201811\\pic5204028673149667661.jpg', '1', '2018-11-16 10:28:54');
INSERT INTO `res_img` VALUES ('5', '.\\upload\\pic\\201811\\pic7550951990260583527.png', '1', '2018-11-23 16:49:47');
INSERT INTO `res_img` VALUES ('6', '.\\upload\\pic\\201811\\pic778904259101237387.jpg', '1', '2018-11-27 21:18:51');
INSERT INTO `res_img` VALUES ('8', '.\\upload\\pic\\201811\\pic1733341196402226671.jpg', '1', '2018-11-28 10:30:47');
INSERT INTO `res_img` VALUES ('9', '.\\upload\\pic\\201811\\pic8887282670831657456.png', '1', '2018-11-28 10:33:30');
INSERT INTO `res_img` VALUES ('10', '.\\upload\\pic\\201811\\pic3690816564149493613.jpg', '1', '2018-11-29 14:29:39');
INSERT INTO `res_img` VALUES ('15', '.\\upload\\pic\\201811\\pic1537625003943684602.jpg', '1', '2018-11-29 14:49:54');
INSERT INTO `res_img` VALUES ('16', '.\\upload\\pic\\201811\\pic5550947533808887582.jpg', '1', '2018-11-29 14:59:19');
INSERT INTO `res_img` VALUES ('17', '.\\upload\\pic\\201811\\pic3195053477649299809.jpg', '1', '2018-11-29 15:09:47');
INSERT INTO `res_img` VALUES ('18', '.\\upload\\pic\\201811\\pic8353165236788580441.png', '1', '2018-11-30 09:10:44');
INSERT INTO `res_img` VALUES ('19', '.\\upload\\pic\\201811\\pic6673666642545563428.png', '1', '2018-11-30 09:13:18');
INSERT INTO `res_img` VALUES ('20', '.\\upload\\pic\\201811\\pic255698524943846960.jpg', '1', '2018-11-30 22:29:22');
INSERT INTO `res_img` VALUES ('22', '.\\upload\\pic\\201812\\pic449347529002547911.png', '1', '2018-12-01 16:51:32');

-- ----------------------------
-- Table structure for sys_asset
-- ----------------------------
DROP TABLE IF EXISTS `sys_asset`;
CREATE TABLE `sys_asset` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '资源名称',
  `code` varchar(50) DEFAULT NULL COMMENT '资源代码',
  `pid` int(11) DEFAULT NULL COMMENT '父ID',
  `pids` varchar(80) DEFAULT NULL COMMENT '父节组ID',
  `type` varchar(50) DEFAULT NULL COMMENT '类型',
  `controller` varchar(100) DEFAULT NULL COMMENT '控制器',
  `view` varchar(100) DEFAULT NULL COMMENT '视图',
  `icon_css` varchar(100) DEFAULT NULL COMMENT '图标css',
  `is_menu` int(11) DEFAULT NULL COMMENT '是否菜单',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=54 DEFAULT CHARSET=utf8 COMMENT='系统资源';

-- ----------------------------
-- Records of sys_asset
-- ----------------------------
INSERT INTO `sys_asset` VALUES ('6', '用户管理', 'user', '5', '0/5', 'menu', 'console/user', 'console/sys/user', null, '1', '5');
INSERT INTO `sys_asset` VALUES ('7', '角色管理', 'role', '5', '0/5', 'menu', 'console/role', 'console/sys/role', null, '1', '10');
INSERT INTO `sys_asset` VALUES ('8', '菜单管理', 'menu', '5', '0/5', 'menu', 'console/menu', 'console/sys/menu', null, '1', '15');
INSERT INTO `sys_asset` VALUES ('1', '首页', 'index', '0', '0', 'menu', 'console', 'console/dashboard', 'dashboard', '1', '1');
INSERT INTO `sys_asset` VALUES ('5', '系统管理', 'sys', '0', '0', 'node', '', '', 'settings', '1', '100');
INSERT INTO `sys_asset` VALUES ('51', '我的博客', 'blog', '0', '0', 'node', '', '', 'chrome_reader_mode', '1', '20');
INSERT INTO `sys_asset` VALUES ('52', '文章管理', 'article', '51', '0/51', 'menu', 'console/article', 'console/blog/article', '', '1', '10');
INSERT INTO `sys_asset` VALUES ('53', '文章编辑', 'editor', '51', '0/51', 'menu', 'console/editor', 'console/blog/editor', '', '1', '20');

-- ----------------------------
-- Table structure for sys_privilege
-- ----------------------------
DROP TABLE IF EXISTS `sys_privilege`;
CREATE TABLE `sys_privilege` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `master` varchar(50) DEFAULT NULL,
  `master_value` varchar(100) DEFAULT NULL,
  `access` varchar(50) DEFAULT NULL,
  `access_value` varchar(200) DEFAULT NULL,
  `permission` varchar(50) DEFAULT NULL,
  `comment` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=354 DEFAULT CHARSET=utf8 COMMENT='权限';

-- ----------------------------
-- Records of sys_privilege
-- ----------------------------
INSERT INTO `sys_privilege` VALUES ('351', 'role', '1', 'menu', '8', 'sys:menu', null);
INSERT INTO `sys_privilege` VALUES ('350', 'role', '1', 'menu', '7', 'sys:role', null);
INSERT INTO `sys_privilege` VALUES ('349', 'role', '1', 'menu', '6', 'sys:user', null);
INSERT INTO `sys_privilege` VALUES ('348', 'role', '1', 'menu', '1', 'console:index', null);
INSERT INTO `sys_privilege` VALUES ('352', 'role', '1', 'menu', '52', 'blog:article', null);
INSERT INTO `sys_privilege` VALUES ('353', 'role', '1', 'menu', '53', 'blog:editArticle', null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL COMMENT '父ID',
  `name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `code` varchar(30) DEFAULT NULL COMMENT '角色代码',
  `comment` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', null, '管理员角色', 'admin', '管理员角色', 'admin', '2018-09-16 00:24:55');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(80) DEFAULT NULL COMMENT '登录密码',
  `salt` varchar(50) DEFAULT NULL COMMENT '盐',
  `name` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `tel` varchar(15) DEFAULT NULL COMMENT '联系电话',
  `org_id` int(11) DEFAULT NULL COMMENT '用户所属机构',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建人',
  `status` int(11) DEFAULT NULL COMMENT '用户状态',
  `user_icon` varchar(100) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `login_count` int(11) DEFAULT NULL COMMENT '登陆次数',
  `last_login_ip` varchar(20) DEFAULT NULL COMMENT '最后登陆IP',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登陆时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '68e67c5bf95c6194c4f0e30dca45a418', '6a75262bcb161d22eae1638f4a75bd14', '管理员', '87166669@dyl.com', '18086776931', null, '2018-11-13 09:15:36', null, null, 'icon26586743348640622.jpg', '积极进取，持之以恒。', '2020-05-20 00:00:00', '1460', '0:0:0:0:0:0:0:1', '2018-12-01 16:55:42');
