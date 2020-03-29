package cn.itcast.test;

import cn.itcast.bean.EnableUserBean;
import cn.itcast.bean.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@EnableUserBean
public class Test {
    public static void main(String[] args) {
        // 获取spring容器
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Test.class);
        User user = ac.getBean(User.class);
        System.out.println(user);
    }
}
