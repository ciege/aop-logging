package lt.rieske.aolog.logger;

import org.aspectj.lang.Signature;

class AroundMethodStatLogger implements AroundMethodLogger {

    private final LoggerWrapper logger;
    private final Signature signature;
    private final String arguments;

    public AroundMethodStatLogger(LoggerWrapper logger, Signature signature, String arguments) {
        this.logger = logger;
        this.signature = signature;
        this.arguments = arguments;
    }

    @Override
    public void logBefore() {
        logger.log(LogLevel.DEBUG, "Entering {} with arguments: {}", signature, arguments);
    }

    @Override
    public void logAfter() {
        logger.log(LogLevel.DEBUG, "Leaving {}", signature);
    }

    @Override
    public void logAfter(String returnedString) {
        logger.log(LogLevel.DEBUG, "Leaving {} with return value: {}", signature, returnedString);
    }

    @Override
    public void logException(Exception e) {
        logger.log(LogLevel.DEBUG, "{} threw exception: {}", signature, e.getMessage());
    }

}
