package lt.rieske.aolog.logger;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import lt.rieske.aolog.logger.wrapper.LoggerWrapper;

import org.aspectj.lang.Signature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.springframework.util.StopWatch;

@RunWith(MockitoJUnitRunner.class)
public class AroundMethodPerfLoggerTest {

    @Mock
    private LoggerWrapper loggerWrapper;
    @Mock
    private Logger logger;
    @Mock
    private Signature signature;
    @Mock
    private StopWatch timer;

    private AroundMethodPerfLogger perfLogger;

    @Before
    public void setUp() {
        perfLogger = new AroundMethodPerfLogger(loggerWrapper, logger, signature, timer);
    }

    @Test
    public void shouldLogNoArgsMethodEntry() {
        perfLogger.logBefore();

        verify(timer).start();
        verify(loggerWrapper).log(same(logger), anyString(), same(signature));
        verifyNoMoreInteractions(loggerWrapper, logger, signature, timer);
    }

    @Test
    public void shouldLogMethodWithArgsEntry() {
        perfLogger.logBefore("arguments");

        verify(timer).start();
        verify(loggerWrapper).log(same(logger), anyString(), same(signature));
        verifyNoMoreInteractions(loggerWrapper, logger, signature, timer);
    }

    @Test
    public void shouldLogMethodExit() {
        perfLogger.logAfter();

        verify(timer).stop();
        verify(timer).getTotalTimeMillis();
        verify(loggerWrapper).log(same(logger), anyString(), same(signature), anyLong());
        verifyNoMoreInteractions(loggerWrapper, logger, signature, timer);
    }

    @Test
    public void shouldLogMethodExitWithReturnValue() {
        perfLogger.logAfter("returnedValue");

        verify(timer).stop();
        verify(timer).getTotalTimeMillis();
        verify(loggerWrapper).log(same(logger), anyString(), same(signature), anyLong());
        verifyNoMoreInteractions(loggerWrapper, logger, signature, timer);
    }

    @Test
    public void shouldLogMethodExitWithException() {
        perfLogger.logException(new RuntimeException("exception"));

        verify(timer).stop();
        verify(timer).getTotalTimeMillis();
        verify(loggerWrapper).log(same(logger), anyString(), same(signature), anyLong());
        verifyNoMoreInteractions(loggerWrapper, logger, signature, timer);
    }
}
