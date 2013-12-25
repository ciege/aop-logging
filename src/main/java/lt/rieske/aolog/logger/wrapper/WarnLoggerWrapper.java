package lt.rieske.aolog.logger.wrapper;

class WarnLoggerWrapper extends LoggerWrapper {

    @Override
    public void log(String format, Object... arguments) {
        logger.warn(format, arguments);
    }
}
