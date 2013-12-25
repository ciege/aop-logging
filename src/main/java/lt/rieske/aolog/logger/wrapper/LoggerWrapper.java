package lt.rieske.aolog.logger.wrapper;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

public abstract class LoggerWrapper {

    private static Map<LogLevel, LoggerWrapper> loggerWrappers = new HashMap<>();

    protected Logger logger;

    static {
        loggerWrappers.put(LogLevel.TRACE, new TraceLoggerWrapper());
        loggerWrappers.put(LogLevel.DEBUG, new DebugLoggerWrapper());
        loggerWrappers.put(LogLevel.INFO, new InfoLoggerWrapper());
        loggerWrappers.put(LogLevel.WARN, new WarnLoggerWrapper());
        loggerWrappers.put(LogLevel.ERROR, new ErrorLoggerWrapper());
    }

    public static LoggerWrapper createLoggerWrapper(Logger logger, LogLevel logLevel) {
        return loggerWrappers.get(logLevel).withLogger(logger);
    }

    public abstract void log(String format, Object... arguments);

    private LoggerWrapper withLogger(Logger logger) {
        this.logger = logger;
        return this;
    }
}
