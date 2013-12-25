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

    private String getLoggerKey(Signature signature, Object target) {
        return signature.toString() + target.toString();
    }

    @Override
    public AroundMethodLogger getAroundMethodLogger(Signature methodSignature, Object target, LogAround configuration) {
        String loggerKey = getLoggerKey(methodSignature, target);

        AroundMethodLogger aroundMethodLogger = aroundMethodLoggers.get(loggerKey);
        if (aroundMethodLogger == null) {
            Logger logger = LoggerFactory.getLogger(target.toString());
            LoggerWrapper loggerWrapper = LoggerWrapper.createLoggerWrapper(logger, configuration.logLevel());

            aroundMethodLogger = new AroundMethodStatLogger(loggerWrapper, methodSignature);
            aroundMethodLoggers.put(loggerKey, aroundMethodLogger);
        }
        return aroundMethodLogger;
    }
}
