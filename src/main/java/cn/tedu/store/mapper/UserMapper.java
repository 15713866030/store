package cn.tedu.store.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.User;
 /**
  * 处理用户数据的持久层接口
  * @author soft01
  *
  */
public interface UserMapper {
	/**
	 * 保存个人信息的方法
	 */
	Integer save(User user);
	/**
	 * 更新密码
	 * @param uid
	 * @param password
	 * @param modifiedUser
	 * @param modifiedTime
	 * @return
	 */
	Integer updatePassword(
			@Param("uid") Integer uid,
			@Param("password") String password,
			@Param("modifiedUser") String modifiedUser,
			@Param("modifiedTime") Date modifiedTime);
	/**
	 * 将图片信息添加到数据库中
	 * @param uid用户id
	 * @param avatar
	 * @param modifiedUser修改人
	 * @param modifiedTime修改时间
	 * @return 受影响的行数
	 */
	Integer updateAvatar(
			@Param("uid") Integer uid,
			@Param("avatar") String avatar,
			@Param("modifiedUser") String modifiedUser,
			@Param("modifiedTime") Date modifiedTime);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */	
	Integer updateInfo(User user);
	/**
	 * 通过id查询数据库的用户数据
	 * @param uid 用户id
	 * @return 匹配的用户数据，如果没有匹配的数据，则返回null。
	 */
	User findByUid(Integer uid);
	
	/**
	 * 通过名字查询数据库的用户数据
	 * @param username 用户名
	 * @return 匹配的用户数据，如果没有匹配的数据，则返回null。
	 */
	User findByUsername(String username);
	

}
