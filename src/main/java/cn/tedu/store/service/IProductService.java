package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Product;

public interface IProductService {
/**
 * 获取最热列表的抽象方法
 * @return
 */
	List<Product> findHotList();
	/**
	 * 通过id得到商品信息
	 * @param id
	 * @return
	 */
	Product getById(Integer id);
}
