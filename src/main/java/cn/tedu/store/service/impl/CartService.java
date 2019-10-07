package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.entity.Product;
import cn.tedu.store.entity.vo.CartVO;
import cn.tedu.store.mapper.CartMapper;
import cn.tedu.store.mapper.ProductMapper;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.IProductService;
import cn.tedu.store.service.ex.AccessDeniedException;
import cn.tedu.store.service.ex.CartNotFoundException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.UpdateException;

@Service
public class CartService implements ICartService {

	@Autowired
	private CartMapper cartMapper;

	@Autowired
	private IProductService productMapper;

	/**
	 * 向购物车添加商品
	 * 
	 * @param cart
	 * @return
	 */
	void save(Cart cart) {
		Integer rows = cartMapper.save(cart);
		if (rows != 1) {
			throw new InsertException("插入购物车数据出现未知错误");
		}

	}

	/**
	 * 修改购物车物品商品数量
	 * 
	 * @param cid
	 *            购物车列表
	 * @param num
	 * @param modifiedUser
	 * @param modifiedTime
	 * @return
	 */
	void updateNum(Integer cid, Integer num, String modifiedUser, Date modifiedTime) {
		Integer rows = cartMapper.updateNum(cid, num, modifiedUser, modifiedTime);
		if (rows != 1) {
			throw new UpdateException("更新购物车数据出现错误");
		}
	}

	/**
	 * 获取某用户和商品id查询购物车是否有该商品
	 * 
	 * @param uid
	 *            用户id
	 * @param pid
	 *            商品id
	 * @return 如果有返回查询到商品，如果查不到返回null
	 */
	Cart findByUidAndPid(Integer uid, Integer pid) {

		return cartMapper.findByUidAndPid(uid, pid);

	}

	public void addToCart(Integer uid, Integer pid, Integer num, String username) {
		// 根据参数uid和pid查询数据
		Cart cart = findByUidAndPid(uid, pid);
		// 判断查询结果是否为null
		// -- 是
		if (cart == null) {
			Cart cart1 = new Cart();
			// -- -- 通过productService查出商品价格
			Product result = productMapper.getById(pid);
			Long price = result.getPrice();
			// -- -- 执行添加
			cart1.setUid(uid);
			cart1.setPid(pid);
			cart1.setNum(num);
			cart1.setPrice(price);
			cart1.setCreatedUser(username);
			cart1.setCreatedTime(new Date());
			cart1.setModifiedUser(username);
			cart1.setModifiedTime(new Date());
			save(cart1);
		} else {
			// -- 否
			// -- -- 从查询结果中获取原数量，和参数num相加，得到新数量
			Integer number = cart.getNum();
			// -- -- 执行更新数量
			updateNum(cart.getCid(), number + num, username, new Date());
		}

	}

	public List<CartVO> getByUid(Integer uid) {

		return cartMapper.findByUid(uid);
	}

	public Integer addNum(Integer cid, Integer uid, String username) {
		Cart result = cartMapper.findByCid(cid);
		if (result == null) {
			throw new CartNotFoundException("收货地址查找出现错误");
		}
		if (!result.getUid().equals(uid)) {
			throw new AccessDeniedException("加入错误");
		}
		Integer num = result.getNum() + 1;
		Integer rows = cartMapper.updateNum(cid, num, username, new Date());
		if (rows != 1) {
			throw new UpdateException("未知错误");
		}
		return num;
	}

	public List<CartVO> getByCids(Integer[] cids,Integer uid) {
		//执行查询
		List<CartVO> list=cartMapper.findByCids(cids);
		//遍历查询结果，移除非当前uid的数据
		Iterator<CartVO> it=list.iterator();
		while(it.hasNext()) {
			CartVO item=it.next();
			if(!item.getUid().equals(uid)) {
				it.remove();
			}
		}	
		return list;
	}

}
