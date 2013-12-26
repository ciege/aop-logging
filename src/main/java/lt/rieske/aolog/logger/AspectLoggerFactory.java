package lt.rieske.aolog.logger;

import java.util.HashMap;
import java.util.Map;

import lt.rieske.aolog.annotation.LogAround;
import lt.rieske.aolog.logger.wrapper.LoggerWrapper;

import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

public abstract class AspectLoggerFactory {

    private enum LoggerType {
        STAT, PERF
    }

    private static final Map<String, LoggerType> loggerMappings = new HashMap<>();

    static {
        loggerMappings.put("stat", LoggerType.STAT);
        loggerMappings.put("perf", LoggerType.PERF);
    }

    public AroundMethodLogger getAroundMethodLogger(Signature methodSignature, Object target, LogAround configuration) {
        LoggerWrapper loggerWrapper = LoggerWrapper.getLoggerWrapper(configuration.logLevel());
        Logger logger = LoggerFactory.getLogger(target.toString());
        String loggerTypeString = configuration.value().toLowerCase();
        LoggerType loggerType = loggerMappings.get(loggerTypeString);
        AroundMethodLogger aroundMethodLogger = null;
        if (loggerType != null) {
            switch (loggerType) {
            case PERF:
                aroundMethodLogger = new AroundMethodPerfLogger(loggerWrapper, logger, methodSignature, new StopWatch());
                break;
            case STAT:
                aroundMethodLogger = new AroundMethodStatLogger(loggerWrapper, logger, methodSignature);
                break;
            }
        } else {
            aroundMethodLogger = getCustomAroundMethodLogger(loggerTypeString, loggerWrapper, logger, methodSignature);
        }
        return aroundMethodLogger;
    }

    protected abstract AroundMethodLogger getCustomAroundMethodLogger(String loggerType, LoggerWrapper loggerWrapper, Logger logger, Signature methodSignature);
}
