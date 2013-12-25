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
        wrapper = LoggerWrapper.createLoggerWrapper(logger, LogLevel.TRACE);

        wrapper.log(FORMAT, ARGUMENTS);

        verify(logger).trace(FORMAT, ARGUMENTS);
        verifyNoMoreInteractions(logger);
    }

    @Test
    public void shouldLogDebugMessage() {
        wrapper = LoggerWrapper.createLoggerWrapper(logger, LogLevel.DEBUG);

        wrapper.log(FORMAT, ARGUMENTS);

        verify(logger).debug(FORMAT, ARGUMENTS);
        verifyNoMoreInteractions(logger);
    }

    @Test
    public void shouldLogInfoMessage() {
        wrapper = LoggerWrapper.createLoggerWrapper(logger, LogLevel.INFO);

        wrapper.log(FORMAT, ARGUMENTS);

        verify(logger).info(FORMAT, ARGUMENTS);
        verifyNoMoreInteractions(logger);
    }

    @Test
    public void shouldLogWarnMessage() {
        wrapper = LoggerWrapper.createLoggerWrapper(logger, LogLevel.WARN);

        wrapper.log(FORMAT, ARGUMENTS);

        verify(logger).warn(FORMAT, ARGUMENTS);
        verifyNoMoreInteractions(logger);
    }

    @Test
    public void shouldLogErrorMessage() {
        wrapper = LoggerWrapper.createLoggerWrapper(logger, LogLevel.ERROR);

        wrapper.log(FORMAT, ARGUMENTS);

        verify(logger).error(FORMAT, ARGUMENTS);
        verifyNoMoreInteractions(logger);
    }
}
