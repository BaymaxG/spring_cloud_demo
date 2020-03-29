package cn.itcast.mytest.service;

import cn.itcast.mytest.DefaultTest;
import cn.itcast.order.entity.Product;
import cn.itcast.order.service.impl.OrderService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceTest extends DefaultTest {
    @Autowired
    private OrderService orderService;

    @Test
    public void testFindById() {
        Product product = orderService.findById((long) 1, 4);
        Assert.assertNotNull(product);
    }
}
