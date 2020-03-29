package cn.itcast.mytest;

import cn.itcast.order.OrderApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 默认测试类入口
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class DefaultTest {
    @Test
    public void test() {}
}
