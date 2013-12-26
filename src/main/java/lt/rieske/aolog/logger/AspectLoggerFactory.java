package lt.rieske.aolog.logger;

import lt.rieske.aolog.annotation.LogAround;
import lt.rieske.aolog.logger.wrapper.LoggerWrapper;

import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

public abstract class AspectLoggerFactory {

    public AroundMethodLogger getAroundMethodLogger(Signature methodSignature, Object target, LogAround configuration) {
        LoggerWrapper loggerWrapper = LoggerWrapper.getLoggerWrapper(configuration.logLevel());
        Logger logger = LoggerFactory.getLogger(target.toString());
        String loggerType = configuration.value().toLowerCase();
        switch (loggerType) {
        case "perf":
            return new AroundMethodPerfLogger(loggerWrapper, logger, methodSignature, new StopWatch());
        case "stat":
            return new AroundMethodStatLogger(loggerWrapper, logger, methodSignature);
        default:
            return getCustomAroundMethodLogger(loggerType, loggerWrapper, logger, methodSignature);
        }
    }

    protected abstract AroundMethodLogger getCustomAroundMethodLogger(String loggerType, LoggerWrapper loggerWrapper, Logger logger, Signature methodSignature);
}
