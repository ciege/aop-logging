package lt.rieske.aolog.aspect;

import java.util.Arrays;

import javax.inject.Inject;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAroundAspect {

	private final Logger logger;

	@Inject
	public LogAroundAspect(final Logger logger) {
		this.logger = logger;
	}

	@Around("execution(* *(..)) && @annotation(performanceLog)")
	public void logAround(ProceedingJoinPoint joinPoint, LogAround logAroundMarker) throws Throwable {

		logger.debug("hijacked method: " + joinPoint.getSignature());
		logger.debug("hijacked arguments: " + Arrays.toString(joinPoint.getArgs()));

		logger.debug("Around before is running!");
		try {
			joinPoint.proceed();
		} catch (Throwable t) {
			logger.debug("threw up");
			throw t;
		}
		logger.debug("Around after is running!");
	}

}
