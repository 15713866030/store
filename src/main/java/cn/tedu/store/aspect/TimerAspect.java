package cn.tedu.store.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimerAspect {
	//execution(* cn.tedu.store.service.impl.*.*(..))包名下所有类.*  类下所有方法.*  所有带参数的方法(..)
	@Around("execution(* cn.tedu.store.service.impl.*.*(..))")
	public Object a(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();

		// 执行方法
		Object result=pjp.proceed();

		long end = System.currentTimeMillis();

		// 计算耗时
		System.out.println("耗时" + (end - start) + "毫秒！");
		
		//返回切面之后的返回值
		return result;
	}
}
