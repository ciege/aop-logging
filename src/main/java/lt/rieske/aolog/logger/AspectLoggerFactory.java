package lt.rieske.aolog.logger;

import java.util.HashMap;
import java.util.Map;

import lt.rieske.aolog.annotation.LogAround;
import lt.rieske.aolog.logger.wrapper.LoggerWrapper;

import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

public abstract class AspectLoggerFactory {

    @Autowired
    private LoggerFactoryWrapper loggerFactory;

    private static enum LoggerType {
        TRACE, PERF
    }

    private final Map<String, LoggerType> loggerMappings = new HashMap<>();

    public AspectLoggerFactory() {
        loggerMappings.put("trace", LoggerType.TRACE);
        loggerMappings.put("perf", LoggerType.PERF);
    }

    public AroundMethodLogger getAroundMethodLogger(Signature methodSignature, Object target, LogAround configuration) {
        LoggerWrapper loggerWrapper = LoggerWrapper.getLoggerWrapper(configuration.logLevel());
        Logger logger = loggerFactory.getLogger(target.toString());
        String loggerTypeString = configuration.value().toLowerCase();
        LoggerType loggerType = loggerMappings.get(loggerTypeString);
        if (loggerType != null) {
            return getAroundMethodLogger(loggerType, loggerWrapper, logger, methodSignature);
        } else {
            return getCustomAroundMethodLogger(loggerTypeString, loggerWrapper, logger, methodSignature);
        }
    }

    private AroundMethodLogger getAroundMethodLogger(LoggerType loggerType, LoggerWrapper loggerWrapper, Logger logger, Signature methodSignature) {
        switch (loggerType) {
        case PERF:
            return new AroundMethodPerfLogger(loggerWrapper, logger, methodSignature, new StopWatch());
        case TRACE:
        default:
            return new AroundMethodTraceLogger(loggerWrapper, logger, methodSignature);
        }
    }

    protected abstract AroundMethodLogger getCustomAroundMethodLogger(String loggerType, LoggerWrapper loggerWrapper, Logger logger, Signature methodSignature);
}
