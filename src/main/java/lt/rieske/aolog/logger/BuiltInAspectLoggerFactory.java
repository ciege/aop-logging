package lt.rieske.aolog.logger;

import lt.rieske.aolog.annotation.LogAround;
import lt.rieske.aolog.logger.wrapper.LoggerWrapper;

import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BuiltInAspectLoggerFactory implements AspectLoggerFactory {

    @Override
    public AroundMethodLogger getAroundMethodLogger(Signature methodSignature, Object target, LogAround configuration) {
        Logger logger = LoggerFactory.getLogger(target.toString());
        LoggerWrapper loggerWrapper = LoggerWrapper.createLoggerWrapper(logger, configuration.logLevel());

        return new AroundMethodStatLogger(loggerWrapper, methodSignature);
    }
}
