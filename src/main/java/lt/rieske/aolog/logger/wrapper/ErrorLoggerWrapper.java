package lt.rieske.aolog.logger.wrapper;

import org.slf4j.Logger;

class ErrorLoggerWrapper extends LoggerWrapper {

    @Override
    public void log(Logger logger, String format, Object... arguments) {
        logger.error(format, arguments);
    }
}
