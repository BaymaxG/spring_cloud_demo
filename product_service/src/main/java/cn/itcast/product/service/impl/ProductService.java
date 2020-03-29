package cn.itcast.product.service.impl;

import cn.itcast.product.dao.ProductDao;
import cn.itcast.product.entity.Product;
import cn.itcast.product.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductService implements IProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductDao productDao;

    @Value("${spring.cloud.client.ip-address}")
    private String ip;

    @Value("${server.port}")
    private String port;

    @Override
    public Product findById(Long id) {
        LOGGER.info("productService findById start, id: {}.", id);
        Product product = productDao.queryById(id);
        if (product == null) {
            product = new Product();
        }
        product.setProductName(ip + ":" + port);
        return product;
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return productDao.saveOrUpdate(product);
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }
}
