package lt.rieske.aolog.aspect;

import javax.inject.Inject;

import lt.rieske.aolog.logger.AroundMethodLogger;
import lt.rieske.aolog.logger.AspectLoggerFactory;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAroundAspect {

	private final AspectLoggerFactory loggerFactory;

	private static final String VOID_RETURN = "void ";

	@Inject
	public LogAroundAspect(final AspectLoggerFactory loggerFactory) {
		this.loggerFactory = loggerFactory;
	}

	@Around("execution(* *(..)) && @annotation(configuration)")
	public void logAround(ProceedingJoinPoint joinPoint, LogAround configuration) throws Throwable {
		AroundMethodLogger logger = loggerFactory.getAroundMethodLogger(joinPoint, joinPoint.getTarget(), configuration);

		logger.logBefore();
		try {
			Object returnValue = joinPoint.proceed();
			if (joinPoint.getSignature().toString().startsWith(VOID_RETURN)) {
				logger.logAfter();
			} else {
				logger.logAfter(returnValue == null ? null : returnValue.toString());
			}
		} catch (Throwable t) {
			logger.logException(t);
			throw t;
		}
	}

}
