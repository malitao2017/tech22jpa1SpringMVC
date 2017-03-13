
--系统可以自动生成

CREATE TABLE `tech_jpa_springmvc_userinfo` (
  `id` bigint(20) NOT NULL auto_increment,
  `account` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf-8;

