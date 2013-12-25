package lt.rieske.aolog.logger;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;

@RunWith(MockitoJUnitRunner.class)
public class LoggerWrapperTest {

    @Mock
    private Logger logger;

    @InjectMocks
    private LoggerWrapper wrapper;

    private final String FORMAT = "any format";

    private final Object[] ARGUMENTS = { "arg1", "arg2" };

    @Test
    public void shouldLogTraceMessage() {
        wrapper.log(LogLevel.TRACE, FORMAT, ARGUMENTS);

        verify(logger).trace(FORMAT, ARGUMENTS);
        verifyNoMoreInteractions(logger);
    }

    @Test
    public void shouldLogDebugMessage() {
        wrapper.log(LogLevel.DEBUG, FORMAT, ARGUMENTS);

        verify(logger).debug(FORMAT, ARGUMENTS);
        verifyNoMoreInteractions(logger);
    }

    @Test
    public void shouldLogInfoMessage() {
        wrapper.log(LogLevel.INFO, FORMAT, ARGUMENTS);

        verify(logger).info(FORMAT, ARGUMENTS);
        verifyNoMoreInteractions(logger);
    }

    @Test
    public void shouldLogWarnMessage() {
        wrapper.log(LogLevel.WARN, FORMAT, ARGUMENTS);

        verify(logger).warn(FORMAT, ARGUMENTS);
        verifyNoMoreInteractions(logger);
    }

    @Test
    public void shouldLogErrorMessage() {
        wrapper.log(LogLevel.ERROR, FORMAT, ARGUMENTS);

        verify(logger).error(FORMAT, ARGUMENTS);
        verifyNoMoreInteractions(logger);
    }
}
