package cn.tedu.store.config;



import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.tedu.store.service.impl.LoginInterceptor;
@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//拦截其类实例化
		HandlerInterceptor interceptor=new LoginInterceptor();
		//白名单中加入的集合
		List<String> paths=new ArrayList<String>();
		paths.add("/js/**");
		paths.add("/css/**");
		paths.add("/images/**");
		paths.add("/bootstrap3/**");
		
		paths.add("/web/register.html");
		paths.add("/web/login.html");
		
		paths.add("/user/reg");
		paths.add("/users/login");
		paths.add("/districts/**");
		paths.add("/product/**");
		paths.add("/web/index.html");
		
		//添加黑名单和白名单
		registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(paths);
		
		
		
	}

}
