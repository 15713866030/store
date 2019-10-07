package cn.tedu.store.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Product;
import cn.tedu.store.entity.vo.CartVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartService {
	@Autowired
	private ICartService cartService;

	@Test
	public void addToCart() {
		cartService.addToCart(9, 10000043, 5, "郭");
		System.out.println("ok");
	}
	@Test
	public void addSum() {
		Integer num=cartService.addNum(5, 9, "admin");
		System.out.println(num);
	}
	@Test
	public void fjdf() {
		Integer[] cids= {2,3,4};
		List<CartVO> result=cartService.getByCids(cids, 9);
		for (CartVO cartVO : result) {
			System.out.println(cartVO);
		}
	}
}
