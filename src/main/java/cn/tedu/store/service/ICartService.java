package cn.tedu.store.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.entity.vo.CartVO;

public interface ICartService {
	/**
	 * 
	 * @param uid
	 * @param pid
	 * @param num
	 * @param username
	 */
	void addToCart(Integer uid,Integer pid,Integer num,String username);
	/**
	 * 
	 * @param uid
	 * @return
	 */
	List<CartVO> getByUid(Integer uid);
	
	Integer addNum(Integer cid,Integer uid,String username);
	
	List<CartVO> getByCids(Integer[] cids,Integer uid);
}
