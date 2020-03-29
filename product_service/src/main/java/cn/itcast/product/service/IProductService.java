package cn.itcast.product.service;

import cn.itcast.product.entity.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 产品服务：提供者
 */
@RestController
@RequestMapping("/product")
public interface IProductService {
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Product findById(@PathVariable Long id);

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    Product save(@RequestBody Product product);

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    List<Product> findAll();
}
