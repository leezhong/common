# common
springMVC+dubbo+zookeeper+maven 搭建的服务框架

说明:
api项目,主要包含公共接口.
service项目,依赖api项目,作为dubbo的服务provider,包含接口实现及持久层操作等.如需要可将service项目再做分割.
web项目,依赖api项目,作为dubbo服务customer初衷为提供非Java项目/手机端/H5端等的服务访问.

启动顺序:
1.启动zookeeper
2.启动service项目
3.启动web项目

2017.10.11
修改在spring MVC中AutoWire dubbo调用的类时,爆空,是由于springMVC初始化及加载Bean的顺序问题,在Springmvc.xml配置最上添加<dubbo:annotation package="com.wangli.controller"/>解决.
