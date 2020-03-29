package cn.itcast.order;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @SpringCloudApplication 可以替代以下3个注解
 * @SpringBootApplication // 启动项
 * @EnableDiscoveryClient // eureka客户端服务发现
 * @EnableCircuitBreaker // 激活hystrix，熔断
 */
@SpringCloudApplication
@EnableFeignClients // 1.引入依赖，2.激活feign
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    /**
     * rest调用，注入bean
     * LoadBalanced:开启基于ribbon的形式调用远程服务
     * 注意：开启ribbon调用之后，使用url拼接的将会失效
     */
    @Bean
    @LoadBalanced // 基于ribbon的形式调用远程服务
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}



