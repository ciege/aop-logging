package lt.rieske.aolog.logger;

import lt.rieske.aolog.logger.wrapper.LoggerWrapper;

import org.slf4j.Logger;

abstract class AroundMethodLoggerBase implements AroundMethodLogger {

    private final LoggerWrapper loggerWrapper;
    private final Logger logger;

    protected AroundMethodLoggerBase(LoggerWrapper loggerWrapper, Logger logger) {
        this.loggerWrapper = loggerWrapper;
        this.logger = logger;
    }

    protected void log(String format, Object... arguments) {
        loggerWrapper.log(logger, format, arguments);
    }
}
