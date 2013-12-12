package lt.rieske.aolog.perflog;

import java.util.Arrays;

import javax.inject.Inject;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceLogAspect {

	private final Logger logger;

	@Inject
	public PerformanceLogAspect(final Logger logger) {
		this.logger = logger;
	}

	@Around("execution(* *(..)) && @annotation(performanceLog)")
	public void performanceLog(ProceedingJoinPoint joinPoint, PerformanceLog performanceLog) throws Throwable {
		System.out.println("hijacked method : " + joinPoint.getSignature());
		System.out.println("hijacked arguments : " + Arrays.toString(joinPoint.getArgs()));

		System.out.println("Around before is running!");
		try {
			joinPoint.proceed();
		} catch (Throwable t) {
			System.out.println("threw up");
			throw t;
		}
		System.out.println("Around after is running!");
	}

}
