package lt.rieske.aolog.logger;

import lt.rieske.aolog.annotation.LogAround;

import org.aspectj.lang.ProceedingJoinPoint;

public interface AspectLoggerFactory {
    AroundMethodLogger getAroundMethodLogger(ProceedingJoinPoint joinPoint, Object target, LogAround configuration);
}
