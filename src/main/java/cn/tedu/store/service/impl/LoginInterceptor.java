package cn.tedu.store.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		if (session.getAttribute("uid") == null) {
			//"/"标示从根目录开始访问
			response.sendRedirect("/web/login.html");
			return false;
		}

		return true;
	}

}
