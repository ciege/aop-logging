package lt.rieske.aolog.logger.wrapper;

import org.slf4j.Logger;

class WarnLoggerWrapper extends LoggerWrapper {

    private final Logger logger;

    public WarnLoggerWrapper(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void log(String format, Object... arguments) {
        logger.warn(format, arguments);
    }

}
