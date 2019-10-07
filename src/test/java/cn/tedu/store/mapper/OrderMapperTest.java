package cn.tedu.store.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.entity.District;
import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.vo.CartVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMapperTest {
	@Autowired
	private OrderMapper orderMapper;
	
	@Test
	public void saveOrder() {
		Order order=new Order();
		order.setUid(9);
		order.setRecvName("郭");
		Integer rows=orderMapper.saveOrder(order);
		System.out.println(rows);
	}
	
}
