package cn.tedu.store.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.ex.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {
	@Autowired
	private IUserService service;
	@Test
	public void a() {
		try {
			User user = new User();
			user.setUsername("service");
			user.setPassword("123456");
			service.reg(user);
			System.out.println("注册成功");
		} catch (ServiceException e) {
			System.out.println(e.getClass().getName());
			System.out.println(e.getMessage());
		}
	}
	@Test
	public void b() {
		try {
			String username="serviceTest";
			String password="123456";
			service.login(username, password);
			System.out.println("登录成功");
		} catch (ServiceException e) {
			System.out.println(e.getClass().getName());
			System.out.println(e.getMessage());
		}
	}
	@Test
	public void changePassword() {
		try {
		Integer uid=9;
		String username="管理员";
		String oldPassword="1234";
		String newPassword="123456";
		service.changePassword(uid, username, oldPassword, newPassword);
		System.out.println("修改成功"+newPassword);
		}catch(ServiceException e) {
			System.out.println(e.getClass().getName());
			System.out.println(e.getMessage());
		}
	}
	@Test
	public void udpateInfo() {
		try {
		Integer uid=9;
		String username="管理员";
		String phone="18945221";
		String email="8121@qq.com";
	
		}catch(ServiceException e) {
			System.out.println(e.getClass().getName());
			System.out.println(e.getMessage());
		}
	}
}
