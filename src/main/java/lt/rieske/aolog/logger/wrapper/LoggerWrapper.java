package lt.rieske.aolog.logger.wrapper;

import lt.rieske.aolog.logger.LogLevel;

import org.slf4j.Logger;

public abstract class LoggerWrapper {

    public static LoggerWrapper createLoggerWrapper(Logger logger, LogLevel logLevel) {
        switch (logLevel) {
        case TRACE:
            return new TraceLoggerWrapper(logger);
        case DEBUG:
            return new DebugLoggerWrapper(logger);
        case INFO:
            return new InfoLoggerWrapper(logger);
        case WARN:
            return new WarnLoggerWrapper(logger);
        case ERROR:
            return new ErrorLoggerWrapper(logger);
        default:
            throw new IllegalArgumentException("Unsupported log level: " + logLevel);
        }
    }

    public abstract void log(String format, Object... arguments);
}
