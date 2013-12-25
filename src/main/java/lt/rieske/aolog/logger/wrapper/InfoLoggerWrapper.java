package lt.rieske.aolog.logger.wrapper;

import org.slf4j.Logger;

class InfoLoggerWrapper extends LoggerWrapper {

    @Override
    public void log(Logger logger, String format, Object... arguments) {
        logger.info(format, arguments);
    }
}
