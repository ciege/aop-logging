package lt.rieske.aolog.logger;

import lt.rieske.aolog.annotation.LogAround;

import org.aspectj.lang.Signature;

public interface AspectLoggerFactory {
    AroundMethodLogger getAroundMethodLogger(Signature methodSignature, Object target, LogAround configuration);
}
