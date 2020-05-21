package cn.itcast.product.service;

import cn.itcast.product.dto.ProductQueryDto;
import cn.itcast.product.entity.product.Product;
import com.baymax.api.dto.ResultMsg;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 产品服务：提供者
 */
@RestController
@RequestMapping("/product")
public interface IProductService {
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResultMsg findProductById(@PathVariable String id);

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    ResultMsg saveOrUpdateProduct(@RequestBody Product product);

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    ResultMsg findAllProducts();

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    ResultMsg findProductsByCondition(@RequestBody ProductQueryDto queryDto);
}
