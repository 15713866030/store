package cn.tedu.store.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.Product;
import cn.tedu.store.mapper.OrderMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderService {
	@Autowired
	private IOrderService orderService;
	@Test
	public void createOrder() {
		Integer [] cids= {4,5,6};
	Order result=	orderService.createOrder(10, cids, 9, "jim");
	System.out.println(result);
	}

}
