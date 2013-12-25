package lt.rieske.aolog.logger.wrapper;

class DebugLoggerWrapper extends LoggerWrapper {

    @Override
    public void log(String format, Object... arguments) {
        logger.debug(format, arguments);
    }
}
