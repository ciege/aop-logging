package lt.rieske.aolog.aspect;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import lt.rieske.aolog.annotation.LogAround;
import lt.rieske.aolog.logger.AroundMethodLogger;
import lt.rieske.aolog.logger.factory.AspectLoggerFactory;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LogAroundAspectTest {

    @Mock
    private AspectLoggerFactory loggerFactory;

    @Mock
    private ProceedingJoinPoint joinPoint;

    @Mock
    private Object target;

    @Mock
    private LogAround configuration;

    @Mock
    private AroundMethodLogger logger;

    @InjectMocks
    private LogAroundAspect logAroundAspect;

    @Test
    public void shouldInvokeLoggerBeforeAndAfterMethodExecution() throws Throwable {
        when(loggerFactory.getAroundMethodLogger(joinPoint, target, configuration)).thenReturn(logger);
        when(joinPoint.getTarget()).thenReturn(target);
        when(joinPoint.getSignature()).thenReturn(Mockito.mock(Signature.class));

        logAroundAspect.logAround(joinPoint, configuration);

        verify(loggerFactory).getAroundMethodLogger(same(joinPoint), same(target), same(configuration));
        verify(logger).logBefore();
        verify(logger).logAfter(null);
        verifyNoMoreInteractions(loggerFactory, logger);
    }

    @Test
    public void shouldInvokeLoggerBeforeAndAfterMethodExecutionForVoidMethod() throws Throwable {
        when(loggerFactory.getAroundMethodLogger(joinPoint, target, configuration)).thenReturn(logger);
        Signature methodSignatureMock = Mockito.mock(Signature.class);
        when(methodSignatureMock.toString()).thenReturn("void aaa()");
        when(joinPoint.getSignature()).thenReturn(methodSignatureMock);
        when(joinPoint.getTarget()).thenReturn(target);

        logAroundAspect.logAround(joinPoint, configuration);

        verify(loggerFactory).getAroundMethodLogger(same(joinPoint), same(target), same(configuration));
        verify(logger).logBefore();
        verify(logger).logAfter();
        verifyNoMoreInteractions(loggerFactory, logger);
    }

    @Test
    public void shouldLogExceptionWhenExceptionIsThrown() throws Throwable {
        when(loggerFactory.getAroundMethodLogger(joinPoint, target, configuration)).thenReturn(logger);
        Throwable runtimeException = new RuntimeException();
        when(joinPoint.getTarget()).thenReturn(target);
        when(joinPoint.proceed()).thenThrow(runtimeException);

        try {
            logAroundAspect.logAround(joinPoint, configuration);
            fail("Exception expected to be rethrown");
        } catch (Throwable t) {
            assertTrue(t == runtimeException);
            verify(loggerFactory).getAroundMethodLogger(same(joinPoint), same(target), same(configuration));
            verify(logger).logBefore();
            verify(logger).logException(same(t));
            verifyNoMoreInteractions(loggerFactory, logger);
        }
    }
}
