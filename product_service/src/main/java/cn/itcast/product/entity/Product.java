package cn.itcast.product.entity;

import cn.itcast.product.entity.listener.ProductListener;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 商品实体类
 */
@Data
@Entity
@Table(name = "db_product_t")
@EntityListeners(value = ProductListener.class)
public class Product {
    @Id
    @GeneratedValue(generator="sys_uid")
    @GenericGenerator(name="sys_uid", strategy="uuid")
    @Column(name = "id", columnDefinition = "VARCHAR(255) COMMENT '主键'")
    private String id;

    @Column(name = "create_by", updatable = false, columnDefinition = "VARCHAR(200) COMMENT '创建人'")
    private String createBy;

    @Column(name = "create_account", updatable = false, columnDefinition = "BIGINT(20)  COMMENT '创建人账号'")
    private long createAccount;

    @Column(name = "create_date", updatable = false, columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createDate;

    @Column(name = "last_update_by", columnDefinition = "VARCHAR(200) COMMENT '最后更新人'")
    private String lastUpdateBy;

    @Column(name = "last_update_account", columnDefinition = "BIGINT(20) COMMENT '最后更新人账号'")
    private long lastUpdateAccount;

    @Column(name = "last_update_date", columnDefinition = "DATETIME COMMENT '最后更新时间'")
    private Date lastUpdateDate;

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
