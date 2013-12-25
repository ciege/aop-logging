package lt.rieske.aolog.aspect;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import lt.rieske.aolog.annotation.LogAround;
import lt.rieske.aolog.logger.AroundMethodLogger;
import lt.rieske.aolog.logger.AspectLoggerFactory;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
    private Signature methodSignature;

    @Mock
    private LogAround configuration;

    @Mock
    private AroundMethodLogger logger;

    private Object[] arguments = new Object[] {};
    private String argumentsString = Arrays.toString(arguments);

    @InjectMocks
    private LogAroundAspect logAroundAspect;

    @Test
    public void shouldInvokeLoggerBeforeAndAfterMethodExecution() throws Throwable {
        when(joinPoint.getSignature()).thenReturn(methodSignature);
        when(joinPoint.getTarget()).thenReturn(target);
        when(loggerFactory.getAroundMethodLogger(methodSignature, target, configuration)).thenReturn(logger);
        when(joinPoint.getArgs()).thenReturn(arguments);

        logAroundAspect.logAround(joinPoint, configuration);

        verify(loggerFactory).getAroundMethodLogger(same(methodSignature), same(target), same(configuration));
        verify(logger).logBefore(argumentsString);
        verify(logger).logAfter(null);
        verifyNoMoreInteractions(loggerFactory, logger);
    }

    @Test
    public void shouldInvokeLoggerBeforeAndAfterMethodExecutionForVoidMethod() throws Throwable {
        when(joinPoint.getSignature()).thenReturn(methodSignature);
        when(joinPoint.getTarget()).thenReturn(target);
        when(loggerFactory.getAroundMethodLogger(methodSignature, target, configuration)).thenReturn(logger);
        when(methodSignature.toString()).thenReturn("void aaa()");
        when(joinPoint.getArgs()).thenReturn(arguments);

        logAroundAspect.logAround(joinPoint, configuration);

        verify(loggerFactory).getAroundMethodLogger(same(methodSignature), same(target), same(configuration));
        verify(logger).logBefore(argumentsString);
        verify(logger).logAfter();
        verifyNoMoreInteractions(loggerFactory, logger);
    }

    @Test
    public void shouldLogExceptionWhenExceptionIsThrown() throws Throwable {
        when(joinPoint.getSignature()).thenReturn(methodSignature);
        when(joinPoint.getTarget()).thenReturn(target);
        when(loggerFactory.getAroundMethodLogger(methodSignature, target, configuration)).thenReturn(logger);
        Throwable runtimeException = new RuntimeException();
        when(joinPoint.proceed()).thenThrow(runtimeException);
        when(joinPoint.getArgs()).thenReturn(arguments);

        try {
            logAroundAspect.logAround(joinPoint, configuration);
            fail("Exception expected to be rethrown");
        } catch (Exception e) {
            assertTrue(e == runtimeException);
            verify(loggerFactory).getAroundMethodLogger(same(methodSignature), same(target), same(configuration));
            verify(logger).logBefore(argumentsString);
            verify(logger).logException(same(e));
            verifyNoMoreInteractions(loggerFactory, logger);
        }
    }
}
