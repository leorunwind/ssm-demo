# ssm-demo

## 关于
这是一个java web站点的源码，提供一个最小demo，为那些想学习Spring+SpringMVC+Mybatis+Velocity的Java web后台开发技术体系的同学提供最方便的体验，此外前端使用Vue.js组件，前端模板套用了bootstrap。也为那些想快速搭建Java web项目的人提供现成模板，你只需要git clone到本地，并进行最基本的配置即可。

## 依赖
* 最好使用IntelliJ IDEA，因为本项目就是在IDEA上跑的
* git clone到本地后，用IDEA打开，配置Tomcat服务器
* 本地创建mysql数据库，数据库名TestSpring，并创建sp_users的表，创建表的命令如下  

    CREATE TABLE `sp_users` (`id` int(11) NOT NULL AUTO_INCREMENT, `username` varchar(255) NOT NULL, `password` varchar(255) NOT NULL, `gender` tinyint(1) DEFAULT 0, `mail` varchar(255) NOT NULL, PRIMARY KEY (`id`), UNIQUE KEY (`username`), UNIQUE KEY (`mail`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;  

    并插入管理员账号  

    INSERT INTO sp_users (username,password,mail) VALUES ('w-admin','your password','mail@xx.com');

* 接着就启动试试看吧，你可以开始你的Java web探索之旅了
