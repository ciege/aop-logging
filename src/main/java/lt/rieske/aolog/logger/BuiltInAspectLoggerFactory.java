package lt.rieske.aolog.logger;

import java.util.Arrays;

import lt.rieske.aolog.annotation.LogAround;
import lt.rieske.aolog.logger.wrapper.LoggerWrapper;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BuiltInAspectLoggerFactory implements AspectLoggerFactory {

    @Override
    public AroundMethodLogger getAroundMethodLogger(ProceedingJoinPoint joinPoint, Object target, LogAround configuration) {
        Logger logger = LoggerFactory.getLogger(target.toString());
        LoggerWrapper loggerWrapper = LoggerWrapper.createLoggerWrapper(logger, configuration.logLevel());
        Signature methodSignature = joinPoint.getSignature();
        String arguments = Arrays.toString(joinPoint.getArgs());

        return new AroundMethodStatLogger(loggerWrapper, methodSignature, arguments);
    }
}
