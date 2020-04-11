package cn.itcast.order.service.feignclient;

import com.baymax.api.dto.ResultMsg;
import org.springframework.stereotype.Component;

@Component
public class ProductFeignClientCallBack implements ProductFeignClient {
    @Override
    public ResultMsg findById(String id) {
        return ResultMsg.buildFailed("触发feign的降级处理");
    }
}
