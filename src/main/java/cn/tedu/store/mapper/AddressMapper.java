package cn.tedu.store.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Address;
/**
 * 处理收货地址的持久层接口
 * @author soft01
 *
 */
public interface AddressMapper {
	/**
	 * 插入地址信息的接口方法
	 * @param adderss收货地址
	 * @return
	 */
	Integer save(Address adderss);
	/**
	 * 根据有几个uid
	 * @param uid
	 * @return
	 */
	Integer countByUid(Integer uid);
	/**
	 * 通过用户Uid查询收货地址信息
	 * @param uid 用户uid
	 * @return
	 */
	List<Address> findByUid(Integer uid);
	/**
	 * 将指定的收货地址设置为默认，根据aid修改信息
	 * @param id
	 * @param modifiedUser
	 * @param modifiedTime
	 * @return
	 */
	Integer updateDefault(@Param("aid")Integer aid,
			@Param("modifiedUser")String modifiedUser,
			@Param("modifiedTime")Date modifiedTime);
	
	Integer updateNonDefault(Integer uid);
	
	Address findByAid(Integer aid);
	/**
	 * 通过uid删除收货地址
	 * @param uid
	 * @return
	 */
	Integer deleteByAid(Integer aid);
	/**
	 * 通过uid查到其它收货地址的信息
	 * @param uid
	 * @return
	 */
	Address findLastModified(Integer uid);

}
