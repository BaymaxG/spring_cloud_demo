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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * @author guNingbo
 * @since 2020/4/5
 */
public class ProductListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductListener.class);

    /**
     * 被 @PrePersist- 在新实体持久化之前（添加到EntityManager）
     * 被 @PostPersist- 在数据库中存储新实体（在commit或期间flush）
     * 被 @PostLoad - 从数据库中检索实体后。
     * 被 @PreUpdate- 当一个实体被识别为被修改时EntityManager
     * 被 @PostUpdate- 更新数据库中的实体（在commit或期间flush）
     * 被 @PreRemove - 在EntityManager中标记要删除的实体时
     */
    @PrePersist
    public void prePersist(Product product) {
        product.setCreateAccount(12345678L);
        product.setCreateBy("test");
        product.setCreateDate(new Date());
        product.setLastUpdateBy("test-first");
        product.setLastUpdateAccount(12345678L);
        product.setLastUpdateDate(new Date());
    }

    @PreUpdate
    public void preUpdate(Product product) {
        product.setLastUpdateBy("test-after");
        product.setLastUpdateAccount(87654321L);
        product.setLastUpdateDate(new Date());
    }

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
