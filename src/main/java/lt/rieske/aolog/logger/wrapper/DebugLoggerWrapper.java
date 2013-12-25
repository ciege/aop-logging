package lt.rieske.aolog.logger.wrapper;

import org.slf4j.Logger;

class DebugLoggerWrapper extends LoggerWrapper {

    @Override
    public void log(Logger logger, String format, Object... arguments) {
        logger.debug(format, arguments);
    }
}
