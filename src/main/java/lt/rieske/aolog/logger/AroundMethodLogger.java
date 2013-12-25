package lt.rieske.aolog.logger;

public interface AroundMethodLogger {

    void logBefore(String arguments);

    void logAfter();

    void logAfter(String returnValueString);

    void logException(Exception e);
}
