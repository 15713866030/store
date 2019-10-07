package cn.tedu.store.mapper;

import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
	@Autowired
	UserMapper mapper;

	@Test
	public void a() {
		User user = new User();
		user.setUsername("admin");
		user.setPassword("1234");
		Integer rows = mapper.save(user);
		System.err.println(rows);
	}

	@Test
	public void selectByName() {
		User user = mapper.findByUsername("serviceTest");
		System.out.println(user);
	}
	@Test
	public void uuid() {
		for (int i = 0; i <10; i++) {
			System.out.println(UUID.randomUUID().hashCode());
		}
	}
	@Test
	public void updatePassword() {
		Integer uid=7;
		String password="1234";
		String modifiedUser="超级管理员";
		Date modifiedTime=new Date();
		Integer rows=mapper.updatePassword(uid, password, modifiedUser, modifiedTime);
		System.out.println(rows);
	}
	@Test
	public void findByUid() {
		Integer uid=8;
		User result=mapper.findByUid(uid);
		System.out.println(result);
	}
	@Test
	public void updateInfo() {
		User user=new User();
		user.setUid(9);
		user.setGender(0);
		user.setPhone("18903866231");
		user.setEmail("root@qq.com");
		Integer rows=mapper.updateInfo(user);
		System.out.println(rows);
	}
	@Test
	public void updateAv() {
		Integer uid=10;
		String avatar="1234";
		String modifiedUser="超级管理员";
		Date modifiedTime=new Date();
		Integer rows=mapper.updateAvatar(uid, avatar, modifiedUser, modifiedTime);
		System.out.println(rows);
	}
}