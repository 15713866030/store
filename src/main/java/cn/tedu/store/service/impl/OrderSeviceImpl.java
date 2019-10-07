package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;
import cn.tedu.store.entity.vo.CartVO;
import cn.tedu.store.mapper.OrderMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.IOrderService;
import cn.tedu.store.service.ex.AccessDeniedException;
import cn.tedu.store.service.ex.AddressNotFoundException;
@Service
public class OrderSeviceImpl implements IOrderService {

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private IAddressService addressService;

	@Autowired
	private ICartService cartService;
	
	
@Transactional
	public Order createOrder(Integer aid, Integer[] cids, Integer uid, String username) {
		// 通过参数aid查询收货地址数据
		Address address=addressService.getByAid(aid);
		// 检查数据归属是否正确
		if(!uid.equals(address.getUid())) {
			throw new AccessDeniedException("非法访问收货地址不正确");
		}
		
		// 通过参数cids查询购物车数据，得到List<CartVO>
		List<CartVO> lists=cartService.getByCids(cids, uid);
		
		// 遍历List<CartVO>，计算商品总价
		Long totalPrice = 0l;
		for (CartVO cartVO : lists) {
			totalPrice+=cartVO.getPrice()*cartVO.getNum();
		}

		// 创建当前时间对象now

		// 创建Order对象
		Order order=new Order();
		// 封装Order属性：uid
		order.setUid(uid);
		// 封装Order属性：recv_???
		order.setRecvAddress(address.getAddress());
		order.setRecvArea(address.getAreaName());
		order.setRecvCity(address.getCityName());
		order.setRecvName(address.getName());
		order.setRecvPhone(address.getPhone());
		order.setRecvProvince(address.getProvinceName());
		// 封装Order属性：total_price(以上计算的商品总价)
		order.setTotalPrice(totalPrice);
		// 封装Order属性：status(0)
		order.setStatus(0);
		// 封装Order属性：order_time(now)
		order.setOrderTime(new Date());
		// 封装Order属性：pay_type(null), pay_time(null)
		order.setPayType(null);
		order.setPayTime(null);
		// 封装Order属性：4项日志
		order.setCreatedTime(new Date());
		order.setCreatedUser(username);
		order.setModifiedTime(new Date());
		order.setModifiedUser(username);
		// 向t_order表中插入数据：saveOrder(Order order)
		Integer rows=orderMapper.saveOrder(order);
		// 遍历List<CartVO>
		for (CartVO cartVO : lists) {
			// -- 创建OrderItem对象
			OrderItem orderItem=new OrderItem();
			// -- 封装OrderItem属性：oid(order.getOid())
			orderItem.setOid(order.getOid());
			// -- 封装OrderItem属性：pid,title,image,price,num
			orderItem.setPid(cartVO.getPid());
			orderItem.setTitle(cartVO.getTitle());
			orderItem.setImage(cartVO.getImage());
			orderItem.setPrice(cartVO.getPrice());
			orderItem.setNum(cartVO.getNum());
			// -- 封装OrderItem属性：4项日志
			orderItem.setCreatedTime(new Date());
			orderItem.setCreatedUser(username);
			orderItem.setModifiedTime(new Date());
			orderItem.setModifiedUser(username);
			// -- 向t_order_item表中插入若干条数据：saveOrderItem(OrderItem orderItem)
			orderMapper.saveOrderItem(orderItem);
			System.out.println(orderItem);
		}
		

		// 返回order对象，返回之前可以将部分数据设置为null
		order.setCreatedTime(null);
		order.setCreatedTime(null);
		order.setModifiedTime(null);
		order.setModifiedUser(null);

		return order;
	}

}
