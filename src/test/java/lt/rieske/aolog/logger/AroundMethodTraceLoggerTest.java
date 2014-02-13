package lt.rieske.aolog.logger;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import lt.rieske.aolog.logger.wrapper.LoggerWrapper;

import org.aspectj.lang.Signature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;

@RunWith(MockitoJUnitRunner.class)
public class AroundMethodTraceLoggerTest {

    @Mock
    private LoggerWrapper loggerWrapper;
    @Mock
    private Logger logger;
    @Mock
    private Signature signature;

    private AroundMethodTraceLogger statLogger;

    @Before
    public void setUp() {
        statLogger = new AroundMethodTraceLogger(loggerWrapper, logger, signature);
    }

    @Test
    public void shouldLogBeforeMethodMessage() {
        statLogger.logBefore();

        verify(loggerWrapper).log(same(logger), anyString(), same(signature));
        verifyNoMoreInteractions(loggerWrapper, logger, signature);
    }

    @Test
    public void shouldLogBeforeMethodMessageWithArguments() {
        final String arguments = "anyArguments";

        statLogger.logBefore(arguments);

        verify(loggerWrapper).log(same(logger), anyString(), same(signature), eq(arguments));
        verifyNoMoreInteractions(loggerWrapper, logger, signature);
    }

    @Test
    public void shouldLogAfterMethodWithVoidReturnMessage() {
        statLogger.logAfter();

        verify(loggerWrapper).log(same(logger), anyString(), same(signature));
        verifyNoMoreInteractions(loggerWrapper, logger, signature);
    }

    @Test
    public void shouldLogAfterMethodWithReturnedValueMessage() {
        final String returnedValue = "anyValue";
        statLogger.logAfter(returnedValue);

        verify(loggerWrapper).log(same(logger), anyString(), same(signature), eq(returnedValue));
        verifyNoMoreInteractions(loggerWrapper, logger, signature);
    }

    @Test
    public void shouldLogMethodExceptionMessage() {
        final String exceptionMessage = "exceptionMessage";
        Exception exception = mock(Exception.class);
        when(exception.getMessage()).thenReturn(exceptionMessage);
        statLogger.logException(exception);

        verify(loggerWrapper).log(same(logger), anyString(), same(signature), eq(exceptionMessage));
        verifyNoMoreInteractions(loggerWrapper, logger, signature);
    }
}
