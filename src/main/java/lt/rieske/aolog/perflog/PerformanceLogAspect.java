package lt.rieske.aolog.perflog;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceLogAspect {

	@Around("execution(* *(..)) && @annotation(performanceLog)")
	public void performanceLog(ProceedingJoinPoint joinPoint, PerformanceLog performanceLog) throws Throwable {
		System.out.println("hijacked method : " + joinPoint.getSignature());
		System.out.println("hijacked arguments : " + Arrays.toString(joinPoint.getArgs()));

		System.out.println("Around before is running!");
		joinPoint.proceed();
		System.out.println("Around after is running!");
	}

}
