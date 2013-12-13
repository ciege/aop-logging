package lt.rieske.aolog.logger;

import lt.rieske.aolog.aspect.LogAround;

import org.aspectj.lang.ProceedingJoinPoint;

public interface AspectLoggerFactory {
	AroundMethodLogger getAroundMethodLogger(ProceedingJoinPoint joinPoint, LogAround configuration);
}
