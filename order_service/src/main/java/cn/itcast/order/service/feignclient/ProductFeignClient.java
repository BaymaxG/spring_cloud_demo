package cn.itcast.order.service.feignclient;

import com.baymax.api.dto.ResultMsg;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @FeignClient 开启feign调用
 *  name：要调用的服务名称
 *  fallback：降级方法（类）
 */
@FeignClient(name = "service-product", fallback = ProductFeignClientCallBack.class)
public interface ProductFeignClient {
    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    ResultMsg findById(@PathVariable("id") String id);
}
