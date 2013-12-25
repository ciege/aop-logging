package lt.rieske.aolog.logger.wrapper;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

public abstract class LoggerWrapper {

    private static Map<LogLevel, LoggerWrapper> loggerWrappers = new HashMap<>();

    static {
        loggerWrappers.put(LogLevel.TRACE, new TraceLoggerWrapper());
        loggerWrappers.put(LogLevel.DEBUG, new DebugLoggerWrapper());
        loggerWrappers.put(LogLevel.INFO, new InfoLoggerWrapper());
        loggerWrappers.put(LogLevel.WARN, new WarnLoggerWrapper());
        loggerWrappers.put(LogLevel.ERROR, new ErrorLoggerWrapper());
    }

    public static LoggerWrapper getLoggerWrapper(LogLevel logLevel) {
        return loggerWrappers.get(logLevel);
    }

    public abstract void log(Logger logger, String format, Object... arguments);
}
