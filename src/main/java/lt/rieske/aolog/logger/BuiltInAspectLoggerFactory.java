package lt.rieske.aolog.logger;

import lt.rieske.aolog.logger.wrapper.LoggerWrapper;

import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class BuiltInAspectLoggerFactory extends AspectLoggerFactory {

    @Override
    protected AroundMethodLogger getCustomAroundMethodLogger(String loggerType, LoggerWrapper loggerWrapper, Logger logger, Signature methodSignature) {
        return new AroundMethodStatLogger(loggerWrapper, logger, methodSignature);
    }
}
