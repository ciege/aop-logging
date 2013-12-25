package lt.rieske.aolog.logger.wrapper;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;

@RunWith(MockitoJUnitRunner.class)
public class LoggerWrapperTest {

    @Mock
    private Logger logger;

    private LoggerWrapper wrapper;

    private final String FORMAT = "any format";

    private final Object[] ARGUMENTS = { "arg1", "arg2" };

    @Test
    public void shouldLogTraceMessage() {
        wrapper = LoggerWrapper.getLoggerWrapper(LogLevel.TRACE);

        wrapper.log(logger, FORMAT, ARGUMENTS);

        verify(logger).trace(FORMAT, ARGUMENTS);
        verifyNoMoreInteractions(logger);
    }

    @Test
    public void shouldLogDebugMessage() {
        wrapper = LoggerWrapper.getLoggerWrapper(LogLevel.DEBUG);

        wrapper.log(logger, FORMAT, ARGUMENTS);

        verify(logger).debug(FORMAT, ARGUMENTS);
        verifyNoMoreInteractions(logger);
    }

    @Test
    public void shouldLogInfoMessage() {
        wrapper = LoggerWrapper.getLoggerWrapper(LogLevel.INFO);

        wrapper.log(logger, FORMAT, ARGUMENTS);

        verify(logger).info(FORMAT, ARGUMENTS);
        verifyNoMoreInteractions(logger);
    }

    @Test
    public void shouldLogWarnMessage() {
        wrapper = LoggerWrapper.getLoggerWrapper(LogLevel.WARN);

        wrapper.log(logger, FORMAT, ARGUMENTS);

        verify(logger).warn(FORMAT, ARGUMENTS);
        verifyNoMoreInteractions(logger);
    }

    @Test
    public void shouldLogErrorMessage() {
        wrapper = LoggerWrapper.getLoggerWrapper(LogLevel.ERROR);

        wrapper.log(logger, FORMAT, ARGUMENTS);

        verify(logger).error(FORMAT, ARGUMENTS);
        verifyNoMoreInteractions(logger);
    }
}
