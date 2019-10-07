package cn.tedu.store.service;

import cn.tedu.store.entity.User;
/**
 * 处理数据的业务层接口
 * @author soft01
 *
 */
public interface IUserService {
	/**
	 * 插入用户数据的方法
	 * @param user
	 */
	void reg(User user);
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	User login(String username,String password);
	
	void changePassword(Integer uid,String username,String oldPassword,String newPassword);
/**
 * 根据用户id查询用户数据
 * @param uid
 * @return如果没有匹配的用户id 返回null
 */
	User getByUid(Integer uid);
	
	void changeInfo(Integer uid,String username,User user);
	/**
	 * 修改用户头像
	 * @param uid 用户id
	 * @param username用户名
	 * @param avatar新头像
	 */
	void changeAvatar(Integer uid,String username,String avatar);
}
