/**
 * Copyright © 2020-2020 BaymaxG.guNingbo
 */

package cn.itcast.product.entity.listener;

import cn.itcast.product.common.Constant;
import cn.itcast.product.common.ConstantEnum;
import cn.itcast.product.entity.Product;
import com.baymax.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.PostLoad;

/**
 * @author guNingbo
 * @since 2020/4/5
 */
public class ProductListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductListener.class);

    @PostLoad
    public void loadItem(Product product) {
        if (product != null) {
            if (!StringUtil.isNullOrEmpty(product.getStatus())) {
                switch (product.getStatus()) {
                    case Constant.YES:
                        product.setStatusDesc(ConstantEnum.YES.getDesc());
                        break;
                    case Constant.NO:
                        product.setStatusDesc(ConstantEnum.NO.getDesc());
                        break;
                    default:
                        LOGGER.info("loading..., item: {}.", product.getStatus());
                        break;
                }
            }
        }
    }
}
