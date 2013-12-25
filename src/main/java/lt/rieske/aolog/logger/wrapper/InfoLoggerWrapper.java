package lt.rieske.aolog.logger.wrapper;

class InfoLoggerWrapper extends LoggerWrapper {

    @Override
    public void log(String format, Object... arguments) {
        logger.info(format, arguments);
    }
}
