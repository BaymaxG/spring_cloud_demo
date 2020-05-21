package cn.itcast.product.service.impl;

import cn.itcast.product.dao.ProductDao;
import cn.itcast.product.dto.ProductQueryDto;
import cn.itcast.product.entity.product.Product;
import cn.itcast.product.service.IProductService;
import com.baymax.api.dto.ResultMsg;
import com.baymax.utils.CollectionUtil;
import com.baymax.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public ResultMsg findProductById(String id) {
        LOGGER.info("productService findById start, id: {}.", id);
        if (StringUtil.isNullOrEmpty(id + "")) {
            return ResultMsg.buildFailed();
        }
        Product product = productDao.queryById(id);
        if (product == null) {
            return ResultMsg.buildSuccess("未查询到结果");
        }
        LOGGER.info("sources: {}.", ip + ":" + port);
        return ResultMsg.buildSuccess(product);
    }

    @Override
    @Transactional
    public ResultMsg saveOrUpdateProduct(Product product) {
        LOGGER.info("[save KPI] ProductService save start. product: {}.", product);
        long startTime = System.currentTimeMillis();
        if (!StringUtil.isNullOrEmpty(product.getId())) {
            Product oldProduct = productDao.queryById(product.getId());
            if (!StringUtil.isNullOrEmpty(product.getStatus())) {
                oldProduct.setStatus(product.getStatus());
            }
            if (product.getPrice() != 0) {
                oldProduct.setPrice(product.getPrice());
            }
            product = oldProduct;
        } else {
            if (StringUtils.isEmpty(product.getProductName()) || StringUtil.isNullOrEmpty(product.getStatus())
                    || product.getPrice() == 0) {
                return ResultMsg.buildFailed("product is null");
            }
        }
        Product updateResult = productDao.saveOrUpdate(product);
        LOGGER.info("[save KPI] ProductService save end. cost: {}ms.", System.currentTimeMillis() - startTime);
        return ResultMsg.buildSuccess(updateResult);
    }

    @Override
    public ResultMsg findAllProducts() {
        LOGGER.info("[query KPI] ProductService findAll start.");
        return ResultMsg.buildSuccess(productDao.findAll());
    }

    @Override
    public ResultMsg findProductsByCondition(ProductQueryDto queryDto) {
        LOGGER.info("[query KPI] findProductsByStatus start, param: {}.", queryDto);
        List<Product> products = productDao.findProducts(queryDto);
        if (!CollectionUtil.isNullOrEmpty(products)) {
            return ResultMsg.buildSuccess(products);
        } else {
            return ResultMsg.buildSuccess("查询结果为空");
        }
    }
}
