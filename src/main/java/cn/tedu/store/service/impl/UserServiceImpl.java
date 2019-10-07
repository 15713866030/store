package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UpdateException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameConflictException;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public void reg(User user) {
		// 从参数user对象中获取用户名
		String username = user.getUsername();
		System.out.println("名字"+username);
		// 调用userMapper根据用户名查询用户数据
		User result = userMapper.findByUsername(username);
		// 判断查询结果是否不为null
		// 是：用户名已存在，不允许注册，抛出UsernameConflictException异常
		if (result != null) {
			throw new UsernameConflictException("用户名(" + username + ")已经存在");
		}
		// 参数user是客户端提交的注册数据，并不包含隐藏数据
		//执行加密的方法
		String salt=UUID.randomUUID().toString().toUpperCase();		
		String md5Password=getMd5Password(user.getPassword(), salt);
		//补全数据salt：
		user.setSalt(salt);
		//补全数据：加密后的密码
		user.setPassword(md5Password);
		// 补全数据：is_delete，值为0
		user.setIsDelete(0);
		// 补全数据：4项日志，用户名为注册的用户名，时间为当前时间
		Date now = new Date();
		user.setCreatedTime(now);
		user.setCreatedUser(username);
		user.setModifiedTime(now);
		user.setModifiedUser(username);
		// 调用userMapper的功能插入用户数据，实现注册，并获取插入数据时的返回值
		Integer rows = userMapper.save(user);	

		// 判断以上返回值是否不为1
		// 是：插入用户数据失败，抛出InsertException
		if (rows != 1) {
			throw new InsertException("插入数据出现问题，请练习管理员");
		}
	}

	@Override
	public User login(String username, String password) {
		// 根据参数username查询用户数据
		User result=userMapper.findByUsername(username);
		// 判断查询结果是否为null
		if(result==null) {
			// 是：用户名不存在，抛出UserNotFoundException
			throw new UserNotFoundException("用户名不存在，请重新输入");
		}
		if(result.getIsDelete().equals(1)) {
			// 判断查询结果中的isDelete是否为1
			// 是：用户被标记为已删除，抛出UserNotFoundException
			throw new UserNotFoundException("用户标示被删除");
		}

		String salt=result.getSalt();
		// 从查询结果中取出盐值
		String md5=getMd5Password(password, salt);
		// 调用getMd5Password()，基于参数password和盐值进行加密
		if(!result.getPassword().equals(md5)){
			// 判断加密后的密码与查询结果中的密码是否不匹配
			// 是：密码错误，抛出PasswordNotMatchException
			throw new PasswordNotMatchException("密码错误");
		}
		// 将查询结果中不应该返回的字段设置为null
		result.setIsDelete(null);
		result.setCreatedUser(null);
		result.setCreatedTime(null);
		result.setModifiedUser(null);
		result.setModifiedTime(null);
		result.setPassword(null);
		result.setSalt(null);
		// 例如：isDelete、createdUser、createdTime、modifiedUser、modifiedTime、password、salt
		// 返回查询结果
		return result;
	}

	@Override
	public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
		// 根据参数uid查询用户数据
		User result=userMapper.findByUid(uid);
		// 判断查询结果是否为null
		if(result==null) {
			// 是：用户数据不存在，抛出UserNotFoundException
			throw new UserNotFoundException();
		}

		// 判断查询结果中的isDelete是否为1
		if(result.getIsDelete().equals(1)) {
			// 是：用户被标记为已删除，抛出UserNotFoundException
			throw new UserNotFoundException("用户已经被删除");
		}
		// 取出查询结果中的盐值
		String salt=result.getSalt();
		// 将参数oldPassword进行加密，得到oldMd5Password
		String oldMd5Password=getMd5Password(oldPassword, salt);
		// 判断查询结果中的password与oldMd5Password是否不匹配
		if(!oldMd5Password.equals(result.getPassword())) {
			// 是：密码验证失败，抛出PasswordNotMatchException
			throw new PasswordNotMatchException("原始密码错误");
		}
		// 将参数newPassword进行加密，得到newMd5Password
		String newMd5Password=getMd5Password(newPassword, salt);
		// 执行更新密码，获取返回值
		Integer rows=userMapper.updatePassword(uid, newMd5Password, username, new Date());
		// 判断返回的受影响的行数是否不为1
		if(rows!=1) {
			// 是：更新失败，抛出UpdateException
			throw new UpdateException("更改密码发生未知错误");
		}
	}
	@Override
	public User getByUid(Integer uid) {
		// 根据参数uid查询用户数据
		User result=userMapper.findByUid(uid);
		// 判断查询结果是否为null
		if(result==null) {
			// 是：用户数据不存在，抛出UserNotFoundException
			throw new UserNotFoundException("用户数据不存在");
		}

		// 判断查询结果中的isDelete是否为1
		if(result.getIsDelete().equals(1)) {
			// 是：用户被标记为已删除，抛出UserNotFoundException
			throw new UserNotFoundException("用户已经被删除");
		}

		// 创建新的User对象
		User user=new User();

		// 将查询结果中的username,gender,phone,email封装到新对象中
		user.setUsername(result.getUsername());
		user.setGender(result.getGender());
		user.setEmail(result.getEmail());
		user.setPhone(result.getPhone());
		// 返回新对象
		return user;
	}

	/**
	 * 
	 */
	public void changeInfo(Integer uid, String username, User user) {
		// 根据参数uid查询用户数据
		User result=userMapper.findByUid(uid);
		// 判断查询结果是否为null
		if(result==null) {
			// 是：用户数据不存在，抛出UserNotFoundException
			throw new UserNotFoundException("用户数据不存在");
		}

		// 判断查询结果中的isDelete是否为1
		if(result.getIsDelete().equals(1)) {
			// 是：用户被标记为已删除，抛出UserNotFoundException
			throw new UserNotFoundException("用户已经被删除");
		}
		//将参数uid封装到参数user的uid中
		user.setUid(uid);
		user.setModifiedUser(username);
		user.setModifiedTime(new Date());
		Integer rows=	userMapper.updateInfo(user);
		if(rows!=1) {
			throw new UpdateException("更新用户信息发生未知错误");
		}		
	}

	/**
	 * 
	 */
	public void changeAvatar(Integer uid, String username, String avatar) {
		// 根据参数uid查询用户数据
		User result=userMapper.findByUid(uid);
		// 判断查询结果是否为null
		if(result==null) {
			// 是：用户数据不存在，抛出UserNotFoundException
			throw new UserNotFoundException("用户数据不存在");
		}

		// 判断查询结果中的isDelete是否为1
		if(result.getIsDelete().equals(1)) {
			// 是：用户被标记为已删除，抛出UserNotFoundException
			throw new UserNotFoundException("用户已经被删除");
		}
		Integer rows=	userMapper.updateAvatar(uid, avatar, username, new Date());
		if(rows!=1) {
			throw new UpdateException("更新用户头像发生未知错误");
		}		
	}


	/**
	 * 执行密码加密
	 * @param password原密码
	 * @param salt盐值
	 * @param 加密后密码
	 */
	private String getMd5Password(String password,String salt) {
		//加密规则，在原规则的左侧和右侧拼接上盐值，循环加密5次
		String str=salt+password+salt;
		for (int i = 0; i < 5; i++) {
			str=DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();
		}
		return str;
	}




}
