package cn.tedu.store.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.ex.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTest {
	@Autowired
	private IAddressService addressService;
	
	@Test
	public void saveAddress() {
		Integer uid = 1;
		String username = "张大大";
		Address address = new Address();	
		address.setName("张三");
		address.setUid(2);
		addressService.addnew(address, uid, username);
		System.out.println("ok");
	}
	@Test
	public void update() {
		try {
		Integer uid = 9;
		String username = "张大大";
		Integer aid=13;
		addressService.setDefault(aid, uid, username);
		System.out.println("ok");
		}catch(ServiceException e) {
			System.out.println(e.getClass().getName());
			System.out.println(e.getMessage());
		}
		
	}
	@Test
	public void updateAddress() {
		try {
		Integer uid = 9;
		String username = "郭道军";
		Integer aid=9;
		addressService.delete(aid, uid, username);
		System.out.println("ok");
		}catch(ServiceException e) {
			System.out.println(e.getClass().getName());
			System.out.println(e.getMessage());
		}
	}
	
}
