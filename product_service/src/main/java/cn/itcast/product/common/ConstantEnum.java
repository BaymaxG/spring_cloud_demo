/**
 * Copyright © 2020-2020 BaymaxG.guNingbo
 */

package cn.itcast.product.common;

/**
 * @author guNingbo
 * @since 2020/4/5
 */
public enum ConstantEnum {
    YES("Y", "有效"),
    NO("N", "失效");

    String value;

    String desc;

    ConstantEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }
}
