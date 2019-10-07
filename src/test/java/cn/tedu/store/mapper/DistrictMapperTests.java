package cn.tedu.store.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.District;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictMapperTests {
	@Autowired
	private DistrictMapper districtMapper;
	
	@Test
	public void district() {
		String parent="86";
		List<District> lists=districtMapper.findByParent(parent);
		System.out.println(lists);
	}
	@Test
	public void findByCode() {
		String code="130130";
		District result=districtMapper.findByCode(code);
		System.out.println(result);
	}

}
