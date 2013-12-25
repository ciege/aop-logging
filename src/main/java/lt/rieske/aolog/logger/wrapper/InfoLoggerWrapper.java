package lt.rieske.aolog.logger.wrapper;

import org.slf4j.Logger;

class InfoLoggerWrapper extends LoggerWrapper {

    private final Logger logger;

    public InfoLoggerWrapper(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void log(String format, Object... arguments) {
        logger.info(format, arguments);
    }
}
