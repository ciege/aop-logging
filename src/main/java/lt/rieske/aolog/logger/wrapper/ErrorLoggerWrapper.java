package lt.rieske.aolog.logger.wrapper;

class ErrorLoggerWrapper extends LoggerWrapper {

    @Override
    public void log(String format, Object... arguments) {
        logger.error(format, arguments);
    }
}
