package lt.rieske.aolog.logger;

import org.slf4j.Logger;

public class LoggerWrapper {

    private final Logger logger;

    public LoggerWrapper(Logger logger) {
        this.logger = logger;
    }

    public void log(LogLevel level, String format, Object... arguments) {
        switch (level) {
        case TRACE:
            logger.trace(format, arguments);
            break;
        case DEBUG:
            logger.debug(format, arguments);
            break;
        case INFO:
            logger.info(format, arguments);
            break;
        case WARN:
            logger.warn(format, arguments);
            break;
        case ERROR:
            logger.error(format, arguments);
            break;
        default:
            logger.error("Unsupported log level");
        }
    }
}
