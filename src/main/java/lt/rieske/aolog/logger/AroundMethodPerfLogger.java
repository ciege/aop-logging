package lt.rieske.aolog.logger;

import lt.rieske.aolog.logger.wrapper.LoggerWrapper;

import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.springframework.util.StopWatch;

class AroundMethodPerfLogger extends AroundMethodLoggerBase {

    private Signature signature;
    private StopWatch timer;

    protected AroundMethodPerfLogger(LoggerWrapper loggerWrapper, Logger logger, Signature signature, StopWatch timer) {
        super(loggerWrapper, logger);
        this.signature = signature;
        this.timer = timer;
    }

    @Override
    public void logBefore() {
        timer.start();
        log("Entering {}", signature);
    }

    @Override
    public void logBefore(String arguments) {
        logBefore();
    }

    @Override
    public void logAfter() {
        timer.stop();
        log("Leaving {}, elapsed time: {}ms", signature, timer.getTotalTimeMillis());
    }

    @Override
    public void logAfter(String returnValueString) {
        logAfter();
    }

    @Override
    public void logException(Exception e) {
        timer.stop();
        log("Exception in {}, elapsed time: {}ms", signature, timer.getTotalTimeMillis());
    }

}
