### 简单库存管理系统

描述

需求提出

1)系统具有管理员、采购员和车间工人三类用户，每类用户都要进行登录认证；

2)管理员负责零件等生产材料的录入与维护，并设置库存的临界值，当某零件或材料低于库存临界值时，产生订货清单并通知采购员去订货；

3)采购员登录系统，查询需要订货的零件或材料清单，负责订货；

4)车间工人登录系统，预定某零件或材料的提取数量，预定后库存数量实时更新。

大概的样式如下图，因为功能并没有太多，就用了两个页面

思路：
	
1、思考数据库的建表都有哪些字段，不同的用户都会有哪些操作，一会我们写静态页面模拟数据时，需要用到；

2、编写前端页面，就简单的写了两个页面，也不算丑，能看的下去

3、根据功能由简到繁，我们先写登陆功能，再写数据渲染功能，最后写数据维护功能(即一些增删改查)
	
4、登陆功能，需要做登陆检测，用自定义过滤器，把用户名存储到session和cookie中
	
5、采用mvc架构，后端代码分为controller 、 service 、dao 三层；采用从后往前写，写每一层只需要写好当前层的逻辑，不需要考虑其他层的逻辑，逻辑清晰，写起来比较高效
	
6、数据的交互采用ajax
	
7、项目整体用到的技术html、css、jquery、ssm
	
8、仓库里有前端静态页面，有可执行的war包，也有源代码