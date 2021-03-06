package cn.itcast.order.service.impl;

import cn.itcast.order.service.IOrderService;
import cn.itcast.order.service.feignclient.ProductFeignClient;
import com.baymax.api.dto.ResultMsg;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@DefaultProperties(defaultFallback = "defaultFallBack") // 开启hystrix的降级处理
public class OrderService implements IOrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
    /**
     * 1.使用restTemplate调用微服务
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 2.使用eureka客户端服务
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 3.feignClient
     */
    @Autowired
    private ProductFeignClient productFeignClient;

    @Value("${myUrl}")
    private String url; // http://localhost:9001/product/

    @Override
//    @HystrixCommand(fallbackMethod = "findByIdFallBack") // 开启服务降级
    @HystrixCommand // 配置公共降级方法后，不需要指定特点方法名
    public ResultMsg buyProduct(String id, int method) {
        LOGGER.info("orderService buyProduct start, id: {}, method: {}.", id, method);
        switch (method) {
            case 1:
                return getProductByStaticUrl(url + id);
            case 2:
                return getProductByEureka(id);
            case 3:
                return getProductByRibbon(id);
            case 4:
                return getProductByFeign(id);
            default:
                return findByIdFallBack(null);
        }
    }

    @Override
    public ResultMsg order(String id) {
        return getProductByFeign(id);
    }

    /**
     * 1.通过静态url直接发送rest请求
     */
    private ResultMsg getProductByStaticUrl(String url) {
        // 1.rest调用
        return restTemplate.getForObject(url, ResultMsg.class);
    }

    /**
     * 2.通过从注册中心获取元数据，拼接地址
     */
    private ResultMsg getProductByEureka(String id) {
        // 2.从eureka中获取服务元数据
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        ServiceInstance serviceInstance = instances.get(0);
        String newUrl = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/product/" + id;
        return restTemplate.getForObject(newUrl, ResultMsg.class);
    }

    /**
     * 3.ribbon负载均衡调用
     */
    private ResultMsg getProductByRibbon(String id) {
        // 3.1 使用@LoadBalanced声明restTemplate
        // 3.2 基于ribbon调用微服务:使用服务名代替IP地址
        String newUrl = "http://service-product/product/" + id;
        return restTemplate.getForObject(newUrl, ResultMsg.class);
    }

    /**
     * 4.通过feign调用
     */
    private ResultMsg getProductByFeign(String id) {
        // 4.feign发送请求
        return productFeignClient.findById(id);
    }

    // 服务降级
    private ResultMsg findByIdFallBack(String id) {
        LOGGER.error("has error, id: {}.", id);
        return ResultMsg.buildFailed("触发降级1:method未找到");
    }

    /**
     * 公共的降级方法，不需要入参
     */
    public ResultMsg defaultFallBack() {
        LOGGER.error("system has error!");
        return ResultMsg.buildFailed("服务调用失败,触发降级");
    }
}
