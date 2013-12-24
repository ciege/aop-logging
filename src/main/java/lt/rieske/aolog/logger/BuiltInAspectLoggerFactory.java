package lt.rieske.aolog.logger;

import java.util.Arrays;

import lt.rieske.aolog.aspect.LogAround;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BuiltInAspectLoggerFactory implements AspectLoggerFactory {

	@Override
	public AroundMethodLogger getAroundMethodLogger(ProceedingJoinPoint joinPoint, Object target, LogAround configuration) {
		return new AroundMethodStatLogger(LoggerFactory.getLogger(target.toString()),
				joinPoint.getSignature(), Arrays.toString(joinPoint.getArgs()));
	}
}
