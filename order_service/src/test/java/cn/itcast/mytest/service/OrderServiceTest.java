package cn.itcast.mytest.service;

import cn.itcast.mytest.DefaultTest;
import cn.itcast.order.service.impl.OrderService;
import cn.itcast.tools.ResultMsg;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceTest extends DefaultTest {
    @Autowired
    private OrderService orderService;

    @Test
    public void testFindById() {
        ResultMsg resultMsg = orderService.buyProduct((long) 1, 4);
        Assert.assertNotNull(resultMsg);
    }
}
