package lt.rieske.aolog.logger;

import lt.rieske.aolog.logger.wrapper.LoggerWrapper;

import org.aspectj.lang.Signature;
import org.slf4j.Logger;

class AroundMethodStatLogger implements AroundMethodLogger {

    private final LoggerWrapper loggerWrapper;
    private final Logger logger;
    private final Signature signature;

    public AroundMethodStatLogger(LoggerWrapper loggerWrapper, Logger logger, Signature signature) {
        this.loggerWrapper = loggerWrapper;
        this.logger = logger;
        this.signature = signature;
    }

    @Override
    public void logBefore() {
        loggerWrapper.log(logger, "Entering {}", signature);
    }

    @Override
    public void logBefore(String arguments) {
        loggerWrapper.log(logger, "Entering {} with arguments: {}", signature, arguments);
    }

    @Override
    public void logAfter() {
        loggerWrapper.log(logger, "Leaving {}", signature);
    }

    @Override
    public void logAfter(String returnedString) {
        loggerWrapper.log(logger, "Leaving {} with return value: {}", signature, returnedString);
    }

    @Override
    public void logException(Exception e) {
        loggerWrapper.log(logger, "{} threw exception: {}", signature, e.getMessage());
    }

}
