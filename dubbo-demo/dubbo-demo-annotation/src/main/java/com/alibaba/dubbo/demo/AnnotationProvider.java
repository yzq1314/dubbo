package com.alibaba.dubbo.demo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Abel Yu
 * @description
 * @email 153027831@qq.com
 * @Date: 2022/5/16 21:45
 */
public class AnnotationProvider {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProviderConfiguration.class);
        context.start();
        System.in.read();
    }

    /**
     * 指定扫描服务所在的包
     */
    @Configuration
    @EnableDubbo(scanBasePackages = "com.alibaba.dubbo.demo")
    static class ProviderConfiguration {

        @Bean
        public ProviderConfig providerConfig() {
            return new ProviderConfig();
        }

        /**
         * 相当于<dubbo:application name="demo-provider"/>
         *
         * @return 返回应用配置对象
         */
        @Bean
        public ApplicationConfig applicationConfig() {
            ApplicationConfig applicationConfig = new ApplicationConfig();
            applicationConfig.setName("demo-annotation-provider");
            return applicationConfig;
        }

        /**
         * 相当于<dubbo:registry address="zookeeper://127.0.0.1:2181"/>
         * @return 返回注册中心的配置
         */
        @Bean
        public RegistryConfig registryConfig() {
            RegistryConfig registryConfig = new RegistryConfig();
            // 使用Zookeeper作为注册中心，同时给出注册中心的IP和端口
            registryConfig.setProtocol("zookeeper");
            registryConfig.setAddress("localhost");
            registryConfig.setPort(2181);
            return registryConfig;
        }

        /**
         * 默认服务使用dubbo协议，在20880端口监听服务
         * 相当于<dubbo:protocol name="dubbo" port="20880"/>
         * @return 返回协议配置
         */
        @Bean
        public ProtocolConfig protocolConfig(){
            ProtocolConfig protocolConfig = new ProtocolConfig();
            protocolConfig.setPort(20882);
            protocolConfig.setName("dubbo");
            return protocolConfig;
        }
    }
}
