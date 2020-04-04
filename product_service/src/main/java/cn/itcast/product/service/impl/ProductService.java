package cn.itcast.product.service.impl;

import cn.itcast.product.dao.ProductDao;
import cn.itcast.product.entity.Product;
import cn.itcast.product.service.IProductService;
import cn.itcast.tools.ResultMsg;
import cn.itcast.tools.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;

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
    public ResultMsg findById(Long id) {
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
    public ResultMsg save(Product product) {
        LOGGER.info("[query KPI] ProductService save start. product: {}.", product);
        if (StringUtils.isEmpty(product.getId())) {
            productDao.insertObject(product);
            return ResultMsg.buildSuccess("添加成功");
        }
        return ResultMsg.buildSuccess(productDao.saveOrUpdate(product));
    }

    @Override
    public ResultMsg findAll() {
        LOGGER.info("[query KPI] ProductService findAll start.");
        return ResultMsg.buildSuccess(productDao.findAll());
    }
}
