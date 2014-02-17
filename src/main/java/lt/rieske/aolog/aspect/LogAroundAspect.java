package lt.rieske.aolog.aspect;

import java.util.Arrays;

import lt.rieske.aolog.annotation.LogAround;
import lt.rieske.aolog.logger.AroundMethodLogger;
import lt.rieske.aolog.logger.AspectLoggerFactory;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAroundAspect {

    @Autowired
    private AspectLoggerFactory loggerFactory;

    private static final String VOID_RETURN = "void ";

    @Around("execution(public * *(..)) && @within(configuration)")
    public void logAroundAnnotatedClassPublicMethods(ProceedingJoinPoint joinPoint, LogAround configuration) throws Throwable {
        logAround(joinPoint, configuration);
    }

    @Around("execution(* *(..)) && @annotation(configuration)")
    public void logAroundAnnotatedMethods(ProceedingJoinPoint joinPoint, LogAround configuration) throws Throwable {
        logAround(joinPoint, configuration);
    }

    private void logAround(ProceedingJoinPoint joinPoint, LogAround configuration) throws Throwable {
        Signature methodSignature = joinPoint.getSignature();
        AroundMethodLogger logger = loggerFactory.getAroundMethodLogger(methodSignature, joinPoint.getTarget(), configuration);

        logBefore(logger, joinPoint);
        try {
            Object returnValue = joinPoint.proceed();
            logAfter(logger, methodSignature, returnValue);
        } catch (Exception e) {
            logger.logException(e);
            throw e;
        }
    }

    private void logBefore(AroundMethodLogger logger, ProceedingJoinPoint joinPoint) {
        Object[] arguments = joinPoint.getArgs();
        if (arguments.length == 0) {
            logger.logBefore();
        } else {
            logger.logBefore(Arrays.toString(arguments));
        }
    }

    private void logAfter(AroundMethodLogger logger, Signature methodSignature, Object returnValue) {
        if (methodSignature.toString().startsWith(VOID_RETURN)) {
            logger.logAfter();
        } else {
            logger.logAfter(String.valueOf(returnValue));
        }
    }
}
