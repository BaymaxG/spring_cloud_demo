package cn.itcast.order.service.feignclient;

import cn.itcast.order.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductFeignClientCallBack implements ProductFeignClient {
    @Override
    public Product findById(Long id) {
        return new Product("触发feign的降级处理");
    }
}
