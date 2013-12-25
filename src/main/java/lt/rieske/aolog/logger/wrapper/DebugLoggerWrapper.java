package lt.rieske.aolog.logger.wrapper;

import org.slf4j.Logger;

class DebugLoggerWrapper extends LoggerWrapper {
    
    private final Logger logger;

    public DebugLoggerWrapper(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void log(String format, Object... arguments) {
        logger.debug(format, arguments);
    }
}
