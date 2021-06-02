package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

	// AOP 쓰려면 @Aspect 붙이고 @Component 붙이거나 스프링 빈에 직접 등록함. 
	// 메소드 호출할 때마다 인터셉트 되는 거
	/*
	AOP 적용 전에는 controller와 servie가 바로 의존관계지만 
	AOP 적용하면 진짜 service말고 프록시로 가짜 service를 호출해서 AOP 실행하고
	joinPoint.proceed가 실행된 후에 진짜 service가 호출되는 방식.
	*/
	
	// @Around 해당 패키지 하위 파일에 전부 적용 --> 문법 써서 원하는 파일에만 적용 가능한데 패키지 단위로 많이 함
	@Around("execution(* hello.hellospring..*(..))")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
		long start = System.currentTimeMillis();
		System.out.println("start: "+joinPoint.toString());
		
		try {
			Object result = joinPoint.proceed();
			return result;
			
		} finally {
			long finish = System.currentTimeMillis();
			long timeMs = finish - start;
			System.out.println("end: "+joinPoint.toString()+" "+timeMs+"ms");
		}
	}
}
