package cn.itcast.bean;

import org.springframework.context.annotation.Bean;

/**
 * 模拟获取bean对象
 * 没有Spring配置的注解
 */
public class UserConfiguration {
    @Bean
    public User getUser() {
        User user = new User();
        user.setAge(20);
        user.setName("顾宁波");
        return user;
    }
}
