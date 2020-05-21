package cn.itcast.product.entity.product;

import cn.itcast.product.entity.listener.ProductListener;
import com.baymax.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

/**
 * 商品实体类
 */
@Data
@Entity
@Table(name = "dev_product_t")
@EntityListeners(value = ProductListener.class)
public class Product extends BaseEntity {
    @Column(name = "product_name", columnDefinition = "VARCHAR(40) COMMENT '产品名称'")
    private String productName;

    @Column(name = "product_desc", columnDefinition = "VARCHAR(120) COMMENT '产品描述'")
    private String productDesc;

    @Column(name = "status", columnDefinition = "VARCHAR(1) COMMENT '状态'")
    private String status;

    @Column(name = "status_desc", columnDefinition = "VARCHAR(40) COMMENT '状态描述'", updatable = false)
    private String statusDesc;

    @Column(name = "price", columnDefinition = "DOUBLE COMMENT '价格'")
    private double price;

    @Column(name = "caption", columnDefinition = "VARCHAR(150) COMMENT '说明'")
    private String caption;

    @Column(name = "inventory", columnDefinition = "INT(11) COMMENT '库存量'")
    private int inventory;

    public Product() {
    }

    public Product(String productName) {
        this.productName = productName;
    }
}
