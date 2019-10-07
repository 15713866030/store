package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.entity.Product;
/**
 * 商品类的实体接口
 * @author soft01
 *
 */
public interface ProductMapper {
	/**
	 * 得到最热商品
	 * @return
	 */
	List<Product> findHotList();
	/**
	 * 通过id得到商品信息
	 * @param id商品id，如果没有匹配的数据则返回null
	 * @return 商品详情
	 */
	Product findById(Integer id);

}
