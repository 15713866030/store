package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;

import cn.tedu.store.controller.ex.FileEmptyException;
import cn.tedu.store.controller.ex.FileToLangException;
import cn.tedu.store.controller.ex.FileTypeException;
import cn.tedu.store.controller.ex.FileUploadException;
import cn.tedu.store.controller.ex.FileUploadIOException;
import cn.tedu.store.controller.ex.FileUploadStateException;
import cn.tedu.store.service.ex.AccessDeniedException;
import cn.tedu.store.service.ex.AddressNotFoundException;
import cn.tedu.store.service.ex.AddressSizeLimitException;
import cn.tedu.store.service.ex.CartNotFoundException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.ServiceException;
import cn.tedu.store.service.ex.UpdateException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameConflictException;
import cn.tedu.store.unit.JsonResult;

public class BaseController {
	// 响应成功的标示码
	public static final int SUCCESS = 2000;
	/**
	 * 从当前session中获取当前登录的用户的id
	 * @param session
	 * @return 当前登录的用户id
	 */
	protected Integer getUidFromSession(HttpSession session) {
		return Integer.valueOf(session.getAttribute("uid").toString());
		
	}
	/**
	 * 从当前session中获取当前登录的用户的username
	 * @param session
	 * @return 当前登录的用户username
	 */
	protected String getUsernameFromSession(HttpSession session) {
		return session.getAttribute("username").toString();
		
	}
	
	

	// 会捕获这个类及其子类中的异常
	// 里面的类名限制了处理异常的类型，仅处理括号内类型的异常
	// 方法括号内的参数为捕获到异常对象的父类类型。
	@ExceptionHandler({ServiceException.class,FileUploadException.class})
	public JsonResult<Void> handlerException(Throwable e) {
		JsonResult<Void> jr = new JsonResult<>(e);
		if (e instanceof UsernameConflictException) {
			// 4000-用户名冲突异常，例如，注册时用户已经存在
			jr.setState(4000);
		} else if (e instanceof UserNotFoundException) {
			// 4001-用户数据不存在
			jr.setState(4001);

		} else if (e instanceof PasswordNotMatchException) {
			// 4002-验证密码错误
			jr.setState(4002);

		} else if (e instanceof InsertException) {
			// 5000-插入数据异常
			jr.setState(5000);

		}else if(e instanceof UpdateException) {
			//5001-更新数据异常
			jr.setState(5001);
			
		}else if(e instanceof FileEmptyException) {
			//6001-上传文件为空
			jr.setState(6001);
		}else if(e instanceof FileToLangException) {
			//7001-上传文件太大
			jr.setState(6002);
		}else if(e instanceof FileTypeException) {
			//6003上传文件格式异常
			jr.setState(6003);
		}else if(e instanceof FileUploadStateException) {
			//6004上传文件可能被删除或修改
			jr.setState(6004);
		}else if(e instanceof FileUploadIOException) {
			//6005出现读写错误
			jr.setState(6005);
		}else if(e instanceof AddressSizeLimitException) {
			//7001保存地址失败
			jr.setState(7001);
		}else if(e instanceof AccessDeniedException) {
			//7002
			jr.setState(7002);
		}else if(e instanceof AddressNotFoundException) {
			//7003地址找不到
			jr.setState(7003);
		}else if(e instanceof AddressNotFoundException) {
			//7003地址找不到
			jr.setState(7003);
		}else if(e instanceof CartNotFoundException) {
			//7004地址找不到
			jr.setState(7004);
		}
		return jr;
	}
}
