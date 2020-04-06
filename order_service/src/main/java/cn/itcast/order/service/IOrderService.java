package cn.itcast.order.service;

import cn.itcast.tools.ResultMsg;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public interface IOrderService {
    @RequestMapping(value = "/buy/product", method = RequestMethod.GET)
    ResultMsg buyProduct(@RequestParam("id") String id, @RequestParam("method") int method);
}
