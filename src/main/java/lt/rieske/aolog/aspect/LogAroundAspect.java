package lt.rieske.aolog.aspect;

import java.util.Arrays;

import javax.inject.Inject;

import lt.rieske.aolog.annotation.LogAround;
import lt.rieske.aolog.logger.AroundMethodLogger;
import lt.rieske.aolog.logger.AspectLoggerFactory;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
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
        Signature methodSignature = joinPoint.getSignature();
        AroundMethodLogger logger = loggerFactory.getAroundMethodLogger(methodSignature, joinPoint.getTarget(), configuration);

        Object[] arguments = joinPoint.getArgs();
        if (arguments.length == 0) {
            logger.logBefore();
        } else {
            logger.logBefore(Arrays.toString(arguments));
        }
        try {
            Object returnValue = joinPoint.proceed();
            if (methodSignature.toString().startsWith(VOID_RETURN)) {
                logger.logAfter();
            } else {
                logger.logAfter(returnValue == null ? null : returnValue.toString());
            }
        } catch (Exception e) {
            logger.logException(e);
            throw e;
        }
    }
}
