# ComputerMall
  基于SpringBoot、SpringCloud、Vue及Thymeleaf等技术的网上电脑商城项目。
  本项目是前后端分离的，是采用电脑这种商品为对象简单模拟电商平台。本项目前端采用Thymeleaf和Vue技术，后端采用SpringBoot创建多个模块，采用SpringCloud管理和协调各个模块，数据库采用
MySQL。其它技术有Redis、Nginx、ElasticSearch、RabbitMQ等。本项目有14个SpringBoot模块，依次是mall-commons模块：负责放置一些其它模块都用到的代码和依赖；mall-coupon模块：负责
优惠券管理；mall-gateway模块：网关模块，负责代理后台其他模块；mall-member模块：管理会员信息；mall-order模块：管理订单信息；mall-product模块：商品管理；mall-search模块：搜索功
能，与ElasticSearch对接；mall-third-party模块：里面有用到的第三方技术；mall-ware模块：库存服务；renren-fast模块：管理员登录模块；mall-cart模块：购物车模块；mall-auth-server
模块：注册与登录模块；mall-seckill模块：秒杀模块；renren-generator模块：后端Java与前端Vue代码生成器模块。本项目有5个数据库，总计大约50张表。有Redis做缓存，有RabbitMQ负责各个模
块间的通信。前端通过Nginx代理到后端网关进而访问其它的各个模块。
