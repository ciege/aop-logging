package lt.rieske.aolog.logger.wrapper;

import org.slf4j.Logger;

class TraceLoggerWrapper extends LoggerWrapper {

    private final Logger logger;

    public TraceLoggerWrapper(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void log(String format, Object... arguments) {
        logger.trace(format, arguments);
    }
}
