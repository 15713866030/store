package cn.tedu.store.mapper;

import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;

/**
 * 订单的持久层抽象接口
 * @author soft01
 *
 */
public interface OrderMapper {
	/**
	 * 将数据保存订单表中
	 * @param order
	 * @return
	 */
	Integer saveOrder(Order order);
	/**
	 * 将数据保存在订单商品表中
	 * @param orderItem
	 * @return
	 */
	Integer saveOrderItem(OrderItem orderItem);

}
