package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.District;

public interface IDistrictService {
	
	List<District> getByParent(String parent);
	/**
	 * 通过code得到信息的抽象方法
	 * @param code
	 * @return
	 */
	District getByCode(String code);

}
