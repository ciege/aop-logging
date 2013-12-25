package lt.rieske.aolog.logger.wrapper;

import org.slf4j.Logger;

class ErrorLoggerWrapper extends LoggerWrapper {

    private final Logger logger;

    public ErrorLoggerWrapper(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void log(String format, Object... arguments) {
        logger.error(format, arguments);
    }

}
