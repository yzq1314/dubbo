package com.alibaba.dubbo.demo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.demo.api.DemoService;

/**
 * @author Abel Yu
 * @description 基于API的服务消费
 * @email 153027831@qq.com
 * @Date: 2022/5/17 0:24
 */
public class DemoConsumer {
    public static void main(String[] args) {
        ReferenceConfig<DemoService> reference = new ReferenceConfig<DemoService>();
        reference.setApplication(new ApplicationConfig("java-demo-consumer"));
        reference.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        // 指定服务暴露的接口
        reference.setInterface(DemoService.class);
        // 创建远程连接并做动态代理转换
        DemoService demoService = reference.get();
        String hello = demoService.sayHello("World");
        System.out.println(hello);
    }
}
