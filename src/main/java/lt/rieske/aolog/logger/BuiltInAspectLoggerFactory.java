package lt.rieske.aolog.logger;

import java.util.HashMap;
import java.util.Map;

import lt.rieske.aolog.annotation.LogAround;
import lt.rieske.aolog.logger.wrapper.LoggerWrapper;

import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BuiltInAspectLoggerFactory implements AspectLoggerFactory {

    private Map<String, AroundMethodLogger> aroundMethodLoggers = new HashMap<>();

    @Override
    public AroundMethodLogger getAroundMethodLogger(Signature methodSignature, Object target, LogAround configuration) {
        AroundMethodLogger aroundMethodLogger = aroundMethodLoggers.get(methodSignature.toString());
        if (aroundMethodLogger == null) {
            Logger logger = LoggerFactory.getLogger(target.toString());
            LoggerWrapper loggerWrapper = LoggerWrapper.createLoggerWrapper(logger, configuration.logLevel());

            aroundMethodLogger = new AroundMethodStatLogger(loggerWrapper, methodSignature);
            aroundMethodLoggers.put(methodSignature.toString(), aroundMethodLogger);
        }
        return aroundMethodLogger;
    }
}
