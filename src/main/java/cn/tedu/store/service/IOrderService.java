package cn.tedu.store.service;

import cn.tedu.store.entity.Order;

public interface IOrderService {
	/**
	 * 
	 * @param aid
	 * @param cids
	 * @param uid
	 * @param username
	 * @return
	 */
	Order createOrder(Integer aid,Integer[] cids,Integer uid,String username);

}
