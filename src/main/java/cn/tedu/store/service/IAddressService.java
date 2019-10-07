package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.Order;

public interface IAddressService {
	/**
	 * 
	 */
	int MAX_COUNT=20;
	/**
	 * 保存地址信息的接口
	 * @param address
	 * @param uid
	 * @param username
	 */
	
	void addnew(Address address,Integer uid,String username);
	/**
	 * 
	 * @param uid
	 * @return
	 */
	List<Address> findByUid(Integer uid);
	/**
	 * 
	 * @param aid
	 * @param uid
	 * @param username
	 */
	void setDefault(Integer aid,Integer uid,String username);
	/**
	 * 删除收货地址
	 * @param aid 被删除收货地址的aid
	 * @param uid 被删除收货地址的uid
	 * @param username 修改人姓名
	 */
	void delete(Integer aid,Integer uid,String username);
	
	Address getByAid(Integer aid);
	
	

}
