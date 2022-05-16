package com.alibaba.dubbo.demo.refer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.demo.api.DemoService;
import org.springframework.stereotype.Component;

/**
 * @author Abel Yu
 * @description 给基于注解包装消费
 * @email 153027831@qq.com
 * @Date: 2022/5/16 22:44
 */
@Component
public class DemoConsumer {

    @Reference
    private DemoService demoService;


    public String sayHello(String name) {
        return demoService.sayHello(name);
    }
}
