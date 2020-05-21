/**
 * Copyright © 2020-2020 BaymaxG.guNingbo
 */

package cn.itcast.product.common;

/**
 * @author guNingbo
 * @since 2020/5/19
 */
public enum StudyProcessStatusEnum {
    START("START", "开始"),
    STUDYING("STUDYING", "学习中"),
    COMPLETED("COMPLETED", "已完成");

    String code;

    String value;

    StudyProcessStatusEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
    public  String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
