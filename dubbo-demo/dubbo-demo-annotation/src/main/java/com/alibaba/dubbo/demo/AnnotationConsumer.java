package com.alibaba.dubbo.demo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.dubbo.demo.refer.DemoConsumer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Abel Yu
 * @description
 * @email 153027831@qq.com
 * @Date: 2022/5/16 22:48
 */
public class AnnotationConsumer {
    public static void main(String[] args) {
        // 基于注解配置初始化spring上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        context.start();
        // 发起服务调用
        DemoConsumer consumer = context.getBean(DemoConsumer.class);
        String hello = consumer.sayHello("Hello World");
        System.out.println("result: " + hello);
    }

    /**
     * 指定要扫描的消费注解，会触发注入
     */
    @Configuration
    @EnableDubbo(scanBasePackages = "com.alibaba.dubbo.demo")
    @ComponentScan(value = {"com.alibaba.dubbo.demo.refer"})
    static class ConsumerConfiguration {

        @Bean
        public ConsumerConfig consumerConfig() {
            return new ConsumerConfig();
        }


        //        <dubbo:application name="demo-consumer"/>
        //        <dubbo:registry address="zookeeper://127.0.0.1:2181"/>
        @Bean
        public ApplicationConfig applicationConfig() {
            ApplicationConfig applicationConfig = new ApplicationConfig();
            applicationConfig.setName("demo-annotation-consumer");
            return applicationConfig;
        }

        @Bean
        public RegistryConfig registryConfig() {
            RegistryConfig registryConfig = new RegistryConfig();
            // 使用Zookeeper作为注册中心，同时给出注册中心的IP和端口
            registryConfig.setProtocol("zookeeper");
            registryConfig.setAddress("localhost");
            registryConfig.setPort(2181);
            return registryConfig;
        }

    }
}
