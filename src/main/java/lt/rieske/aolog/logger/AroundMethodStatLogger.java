package lt.rieske.aolog.logger;

import org.aspectj.lang.Signature;
import org.slf4j.Logger;

public class AroundMethodStatLogger implements AroundMethodLogger {

    private final Logger logger;
    private final Signature signature;
    private final String arguments;

    public AroundMethodStatLogger(Logger logger, Signature signature, String arguments) {
        this.logger = logger;
        this.signature = signature;
        this.arguments = arguments;
    }

    @Override
    public void logBefore() {
        logger.debug("Entering {} with arguments: {}", signature, arguments);
    }

    @Override
    public void logAfter() {
        logger.debug("Leaving {}", signature);
    }

    @Override
    public void logAfter(String returnedString) {
        logger.debug("Leaving {} with return value: {}", signature, returnedString);
    }

    @Override
    public void logException(Throwable t) {
        logger.debug("{} threw exception: {}", signature, t.getMessage());
    }

}
