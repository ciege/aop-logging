package lt.rieske.aolog.logger;

import lt.rieske.aolog.logger.wrapper.LoggerWrapper;

import org.aspectj.lang.Signature;
import org.slf4j.Logger;

class AroundMethodStatLogger extends AroundMethodLoggerBase {

    private Signature signature;

    public AroundMethodStatLogger(LoggerWrapper loggerWrapper, Logger logger, Signature signature) {
        super(loggerWrapper, logger);
        this.signature = signature;

    }

    @Override
    public void logBefore() {
        log("Entering {}", signature);
    }

    @Override
    public void logBefore(String arguments) {
        log("Entering {} with arguments: {}", signature, arguments);
    }

    @Override
    public void logAfter() {
        log("Leaving {}", signature);
    }

    @Override
    public void logAfter(String returnedString) {
        log("Leaving {} with return value: {}", signature, returnedString);
    }

    @Override
    public void logException(Exception e) {
        log("{} threw exception: {}", signature, e.getMessage());
    }

}
