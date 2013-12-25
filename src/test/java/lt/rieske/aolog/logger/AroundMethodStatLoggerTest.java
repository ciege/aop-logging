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

@RunWith(MockitoJUnitRunner.class)
public class AroundMethodStatLoggerTest {

    @Mock
    private LoggerWrapper logger;
    @Mock
    private Signature signature;

    private AroundMethodStatLogger statLogger;

    @Before
    public void setUp() {
        statLogger = new AroundMethodStatLogger(logger, signature);
    }

    @Test
    public void shouldLogBeforeMethodMessage() {
        final String arguments = "anyArguments";

        statLogger.logBefore(arguments);

        verify(logger).log(anyString(), same(signature), eq(arguments));
        verifyNoMoreInteractions(logger, signature);
    }

    @Test
    public void shouldLogAfterMethodWithVoidReturnMessage() {
        statLogger.logAfter();

        verify(logger).log(anyString(), same(signature));
        verifyNoMoreInteractions(logger, signature);
    }

    @Test
    public void shouldLogAfterMethodWithReturnedValueMessage() {
        final String returnedValue = "anyValue";
        statLogger.logAfter(returnedValue);

        verify(logger).log(anyString(), same(signature), eq(returnedValue));
        verifyNoMoreInteractions(logger, signature);
    }

    @Test
    public void shouldLogMethodExceptionMessage() {
        final String exceptionMessage = "exceptionMessage";
        Exception exception = mock(Exception.class);
        when(exception.getMessage()).thenReturn(exceptionMessage);
        statLogger.logException(exception);

        verify(logger).log(anyString(), same(signature), eq(exceptionMessage));
        verifyNoMoreInteractions(logger, signature);
    }
}
