package cn.itcast.order.service;

import cn.itcast.order.entity.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public interface IOrderService {
    @RequestMapping(value = "/buy/{id}", method = RequestMethod.GET)
    Product findById(@PathVariable Long id, @RequestParam("method") int method);
}
