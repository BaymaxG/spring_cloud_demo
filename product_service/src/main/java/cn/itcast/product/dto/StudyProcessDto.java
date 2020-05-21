/**
 * Copyright © 2020-2020 BaymaxG.guNingbo
 */

package cn.itcast.product.dto;

import lombok.Data;

/**
 * 学习计划Dto
 *
 * @author guNingbo
 * @since 2020/5/19
 */
@Data
public class StudyProcessDto {
    private String id;

    private String content;

    private String studyAddress;

    private int currentPage;

    private String planningDate;

    private String status;
}
