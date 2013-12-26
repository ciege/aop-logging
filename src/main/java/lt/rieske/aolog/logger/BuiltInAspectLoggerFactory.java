package lt.rieske.aolog.logger;

import lt.rieske.aolog.annotation.LogAround;
import lt.rieske.aolog.logger.wrapper.LoggerWrapper;

import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class BuiltInAspectLoggerFactory implements AspectLoggerFactory {

    @Override
    public AroundMethodLogger getAroundMethodLogger(Signature methodSignature, Object target, LogAround configuration) {
        LoggerWrapper loggerWrapper = LoggerWrapper.getLoggerWrapper(configuration.logLevel());
        Logger logger = LoggerFactory.getLogger(target.toString());
        switch (configuration.value()) {
        case "perf":
            return new AroundMethodPerfLogger(loggerWrapper, logger, methodSignature, new StopWatch());
        case "stat":
        default:
            return new AroundMethodStatLogger(loggerWrapper, logger, methodSignature);
        }
    }
}
