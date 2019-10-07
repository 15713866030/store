package cn.tedu.store.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Cart;
/**
 * 购物车数据的持久层接口
 * @author soft01
 *
 */
import cn.tedu.store.entity.vo.CartVO;
public interface CartMapper {
	/**
	 * 向购物车添加商品
	 * 
	 * @param cart
	 * @return
	 */
	Integer save(Cart cart);

	/**
	 * 修改购物车物品商品数量
	 * 
	 * @param cid 购物车列表
	 * @param num
	 * @param modifiedUser
	 * @param modifiedTime
	 * @return
	 */
	Integer updateNum(@Param("cid") Integer cid, @Param("num") Integer num, @Param("modifiedUser") String modifiedUser,
			@Param("modifiedTime") Date modifiedTime);

	/**
	 * 获取某用户和商品id查询购物车是否有该商品
	 * @param uid 用户id
	 * @param pid 商品id
	 * @return 如果有返回查询到商品，如果查不到返回null
	 */
	Cart findByUidAndPid(@Param("uid") Integer uid, @Param("pid") Integer pid);
	/**
	 * 获取某用户的购物车列表信息
	 * @param uid 用户的uid
	 * @return 购物车信息，如果没有就返回null
	 */
	List<CartVO> findByUid(Integer uid);

	/**
	 * 获取某用户购物车的某件商品信息
	 * @param cid 购物车数据id
	 * @return 购物车详情，如果没有匹配的数据，返回null，
	 */
	Cart findByCid(Integer cid);
	/**
	 * 
	 * @param cids
	 * @return
	 */
	List<CartVO> findByCids(Integer[] cids);

}
