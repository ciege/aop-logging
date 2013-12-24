package lt.rieske.aolog.logger.factory;

import lt.rieske.aolog.aspect.LogAround;
import lt.rieske.aolog.logger.AroundMethodLogger;

import org.aspectj.lang.ProceedingJoinPoint;

public interface AspectLoggerFactory {
	AroundMethodLogger getAroundMethodLogger(ProceedingJoinPoint joinPoint, Object target, LogAround configuration);
}
