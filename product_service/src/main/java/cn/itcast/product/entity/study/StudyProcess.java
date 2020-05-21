/**
 * Copyright © 2020-2020 BaymaxG.guNingbo
 */

package cn.itcast.product.entity.study;

import cn.itcast.product.entity.listener.StudyProcessListener;
import com.baymax.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.util.Date;

/**
 * 进度记录表
 *
 * @author guNingbo
 * @since 2020/5/19
 */
@Data
@Entity
@Table(name = "dev_study_process_t")
@EntityListeners(StudyProcessListener.class)
public class StudyProcess extends BaseEntity {
    @Column(name = "content", columnDefinition = "VARCHAR(100) COMMENT '学习内容'")
    private String content;

    @Column(name = "current_page", columnDefinition = "int COMMENT '当前页'")
    private int currentPage;

    @Column(name = "status", columnDefinition = "VARCHAR(10) COMMENT '状态'")
    private String status;

    @Column(name = "status_desc", columnDefinition = "VARCHAR(10) COMMENT '状态描述'")
    private String statusDesc;

    @Column(name = "study_address", columnDefinition = "VARCHAR(255) COMMENT '学习地址'")
    private String studyAddress;

    @Column(name = "remark", columnDefinition = "VARCHAR(60) COMMENT '备注'")
    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "planning_date", columnDefinition = "DATETIME COMMENT '计划时间'")
    private Date planningDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "completed_date", columnDefinition = "DATETIME COMMENT '完成时间'")
    private Date completedDate;
}
