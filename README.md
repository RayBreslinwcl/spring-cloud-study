# Spring Cloud学习笔记

## 觉得整理的不错的朋友，star一下哟，(*￣︶￣)

- 博客：[https://zpj80231.gitee.io/znote/](https://zpj80231.gitee.io/znote/)

## 欢迎大家提问题交流

- 项目fork自gongxings/spring-cloud-study，基于自己的理解又添加了修改

![](/images/springcloud架构图.png)

## 项目版本

- spring cloud版本：Dalston.SR1
- spring boot版本：1.5.19.RELEASE

## 项目模块说明

``` lua
spring-cloud-study
├── springcloud-study-api -- 构建公共子模块 + Hystrix服务降级
├── springcloud-study-euraka-7001 -- Eureka集群
├── springcloud-study-euraka-7002 -- Eureka集群
├── springcloud-study-euraka-7003 -- Eureka集群
├── springcloud-study-provider-dept-8001 -- 构建服务提供者集群
├── springcloud-study-provider-dept-8002 -- 构建服务提供者集群
├── springcloud-study-provider-dept-8003 -- 构建服务提供者集群
├── springcloud-study-consumer-dept-80 -- 构建服务消费者
├── springcloud-study-consumer-dept-feign -- 构建服务消费者
├── springcloud-study-provider-dept-hystrix-8001 -- Hystrix服务熔断
├── springcloud-study-consumer-hystrix-dashboard -- Hystrix调用监控 
├── springcloud-study-zuul-gateway-9527 -- Zuul路由网关 
├── springcloud-study-config-3344 -- config服务端 
└── springcloud-study-config-client-3355 -- config客户端
```

## 项目搭建

- 见工程中：[笔记.md](https://github.com/zpj80231/spring-cloud-study/blob/master/笔记.md) (非常详细)

## 项目启动

1. 启动euraka-service端集群：
   - [springcloud-study-euraka-7001](https://github.com/zpj80231/spring-cloud-study/tree/master/springcloud-study-euraka-7001)
   - [springcloud-study-euraka-7002](https://github.com/zpj80231/spring-cloud-study/tree/master/springcloud-study-euraka-7002)
   - [springcloud-study-euraka-7003](https://github.com/zpj80231/spring-cloud-study/tree/master/springcloud-study-euraka-7003)
   
2. 启动微服务提供者集群：
   - [springcloud-study-provider-dept-8001 (连01数据库)](https://github.com/zpj80231/spring-cloud-study/tree/master/springcloud-study-provider-dept-8001)
   - [springcloud-study-provider-dept-8002 (连02数据库)](https://github.com/zpj80231/spring-cloud-study/tree/master/springcloud-study-provider-dept-8002)
   - [springcloud-study-provider-dept-8003 (连03数据库)](https://github.com/zpj80231/spring-cloud-study/tree/master/springcloud-study-provider-dept-8003)
   
3. 启动为服务消费者：
   > 以下两者二选一启动，它两主要区别（具体见笔记中第四小节）是：<br/>
   > dept-80：Ribbon+RestTemplate 调用Rest服务<br/>
   > dept-feign：Feign+接口 调用Rest服务（优雅简单）
   - [springcloud-study-consumer-dept-80 (含Ribbon客户端负载均衡)](https://github.com/zpj80231/spring-cloud-study/tree/master/springcloud-study-consumer-dept-80)
   - [springcloud-study-consumer-dept-feign (含Feign负载均衡、Hystrix服务降级)](https://github.com/zpj80231/spring-cloud-study/tree/master/springcloud-study-consumer-dept-feign)
   
4. Hystrix 服务熔断、降级、监控   
   > 服务熔断：<br/>
   > 	缺点就是每一个方法对应都得写一个fallBackMethod方法，代码膨胀，所以我们之后看服务降级(*^__^*)<br/>
   >     主要在单个服务出异常的时候用<br/>
   > 
   > 服务降级：<br/>
   > 	统一处理主业务与熔断方法解耦，是在客户端（消费者）处理完成的，与服务端没关系<br/>
   >     主要在单个服务整个被关闭的时候用
   - [springcloud-study-provider-dept-hystrix-8001 (Hystrix服务熔断)](https://github.com/zpj80231/spring-cloud-study/tree/master/springcloud-study-provider-dept-hystrix-8001)
   - [springcloud-study-api (Hystrix服务降级)](https://github.com/zpj80231/spring-cloud-study/tree/master/springcloud-study-api)
   - [springcloud-study-consumer-hystrix-dashboard (Hystrix调用监控)](https://github.com/zpj80231/spring-cloud-study/tree/master/springcloud-study-consumer-hystrix-dashboard)
   
5. Zuul 路由网关
   
   - [springcloud-study-zuul-gateway-9527 (Zuul路由网关)](https://github.com/zpj80231/spring-cloud-study/tree/master/springcloud-study-zuul-gateway-9527)
   
6. Config 分布式配置中心
   - [springcloud-study-config-3344 (config服务端)](https://github.com/zpj80231/spring-cloud-study/tree/master/springcloud-study-config-3344)
   - [springcloud-study-config-client-3355 (config客户端)](https://github.com/zpj80231/spring-cloud-study/tree/master/springcloud-study-config-client-3355)
