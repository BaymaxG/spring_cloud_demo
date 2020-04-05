package cn.itcast.product.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 商品
 */
@Data
@Entity
@Table(name = "tb_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    private Integer status;

    private BigDecimal price;

    private String productDesc;

    private String caption;

    private Integer inventory;

    public Product() {
    }

    public Product(String productName, Integer status) {
        this.productName = productName;
        this.status = status;
    }
}
