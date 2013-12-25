package lt.rieske.aolog.logger.wrapper;

class TraceLoggerWrapper extends LoggerWrapper {

    @Override
    public void log(String format, Object... arguments) {
        logger.trace(format, arguments);
    }
}
