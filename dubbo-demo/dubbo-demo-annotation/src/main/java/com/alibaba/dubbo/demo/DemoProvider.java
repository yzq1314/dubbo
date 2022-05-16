package com.alibaba.dubbo.demo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.dubbo.demo.api.DemoService;
import com.alibaba.dubbo.demo.impl.DemoServiceImpl;

/**
 * @author Abel Yu
 * @description 基于AP服务暴露
 * @email 153027831@qq.com
 * @Date: 2022/5/17 0:18
 */
public class DemoProvider {
    public static void main(String[] args) throws Exception {
        ServiceConfig<DemoService> service = new ServiceConfig<DemoService>();
        service.setApplication(new ApplicationConfig("java-demo-provider"));
        service.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        // 指定服务暴露的接口
        service.setInterface(DemoService.class);
        // 指定真实服务对象
        service.setRef(new DemoServiceImpl());
        // 暴露服务
        service.export();
        System.out.println("java-demo-provider is running.");
        System.in.read();
    }
}
