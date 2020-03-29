package cn.itcast.test;

import cn.itcast.bean.anno.EnableUserBean;
import cn.itcast.bean.anno.User;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@EnableUserBean
public class BeanImportTest {

    @Test
    public void test() {
        // 获取spring容器
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(BeanImportTest.class);
        User user = ac.getBean(User.class);
        System.out.println(user);
    }
}
