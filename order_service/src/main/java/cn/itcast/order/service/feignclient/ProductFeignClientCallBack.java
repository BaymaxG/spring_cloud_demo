package cn.itcast.order.service.feignclient;

import cn.itcast.tools.ResultMsg;
import org.springframework.stereotype.Component;

@Component
public class ProductFeignClientCallBack implements ProductFeignClient {
    @Override
    public ResultMsg findById(Long id) {
        return ResultMsg.buildFailed("触发feign的降级处理");
    }
}
