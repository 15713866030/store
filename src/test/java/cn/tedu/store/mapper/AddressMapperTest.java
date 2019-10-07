package cn.tedu.store.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Address;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressMapperTest {

	@Autowired
	private AddressMapper addressMapper;

	@Test
	public void addressSave() {
		Integer uid = 1;
		String name = "张大";
		Address ads = new Address();
		ads.setName(name);
		ads.setUid(uid);
		Integer rows = addressMapper.save(ads);
		System.out.println(rows);

	}
	@Test
	public void select() {
		Integer uid = 1;
		Integer rows = addressMapper.countByUid(uid);
		System.out.println(rows);
	}
	@Test
	public void findByUid() {
		Integer uid = 9;
		List<Address> lists=addressMapper.findByUid(uid);
		System.out.println(lists.size());
		for (Address address : lists) {
			System.out.println(address);
		}
	
	}
	@Test
	public void findByAid() {
		Integer aid=10;
		Address item=addressMapper.findByAid(aid);
		System.out.println(item);
	}
}
