package cn.tedu.store.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductService {
	@Autowired
	private IProductService productService;
	
	@Test
	public void findHotList() {
		List<Product> lists=productService.findHotList();
		for (Product product : lists) {
			System.out.println(product);
		}
	}
	@Test
	public void findById() {
		
	Integer id=10000028;
	Product result=productService.getById(id);
	System.out.println(result);
	}

}
