/**
 * Copyright © 2020-2020 BaymaxG.guNingbo
 */

package cn.itcast.product.dto;

import lombok.Data;

import java.util.List;

/**
 * @author guNingbo
 * @since 2020/4/5
 */
@Data
public class ProductQueryDto {
    private List<String> status;

    private String productName;

    private String caption;

    private String fromDate;

    private String endDate;
}
