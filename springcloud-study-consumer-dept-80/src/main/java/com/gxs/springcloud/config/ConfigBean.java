package com.gxs.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author GongXings
 * @createTime 30 15:43
 * @description
 */
@Configuration
public class ConfigBean {

    /**
     * 配置RestTemplate
     * 通过RestTemplate调用提供者服务 ，发送rest请求
     * 提供了多种访问http服务的方法，
     * 针对于访问rest服务<strong>客户端</strong>的调用的模板类
     * @return
     */
    @Bean
    @LoadBalanced  //使用@LoadBalanced注解赋予RestTemplate负载均衡的能力 默认轮询
    public  RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public IRule myRule(){
        //指定LoadBalanced的算法 用自带的随机算法
//        return new RandomRule();
//        先按RoundRobinRule轮询算法获取服务，如果失败则在指定时间内进行重试
        return new RetryRule();
    }


}
