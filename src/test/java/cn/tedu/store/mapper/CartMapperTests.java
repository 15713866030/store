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
import cn.tedu.store.entity.vo.CartVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartMapperTests {
	@Autowired
	private CartMapper cartMampper;
	
	@Test
	public void save() {
		Integer uid=9;
		Integer pid=100100;
		Integer num=1;
		Long price=(long) 12;
		Cart cart=new Cart();
		cart.setUid(uid);
		cart.setPid(pid);
		cart.setNum(num);
		cart.setPrice(price);
		Integer row=cartMampper.save(cart);
		System.out.println(row);
	}
	@Test
	public void updateNum() {
		Integer num=3;
		Integer cid=1;
		Integer row=cartMampper.updateNum(cid, num, "郭道军", new Date());
		System.out.println(row);
	}
	@Test
	public void findBycCid() {
		Cart result=cartMampper.findByUidAndPid(9, 100100);
		System.out.println(result);
	}
	@Test
	public void findCart() {
		List<CartVO> lists=cartMampper.findByUid(9);
		for (CartVO cartVO : lists) {
			System.out.println(cartVO);
		}
	}
	@Test
	public void findByCid() {
		Cart result=cartMampper.findByCid(4);
		System.out.println(result);
				
	}
	@Test
	public void findByCids() {
		Integer[] cids= {2,3,4};
		List<CartVO> result=cartMampper.findByCids(cids);
		for (CartVO cartVO : result) {
			System.out.println(cartVO);
		}
	}
}
