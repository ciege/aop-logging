package lt.rieske.aolog.logger;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import lt.rieske.aolog.annotation.LogAround;
import lt.rieske.aolog.logger.wrapper.LogLevel;

import org.aspectj.lang.Signature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BuiltInAspectLoggerFactoryTest {

    @Mock
    private Signature methodSignature;

    @Mock
    private Object target;

    @Mock
    private LogAround configuration;

    private AspectLoggerFactory factory;

    @Before
    public void setUp() {
        factory = new BuiltInAspectLoggerFactory();
        when(configuration.logLevel()).thenReturn(LogLevel.DEBUG);
        when(methodSignature.toString()).thenReturn("a method signature");
    }

    @Test
    public void shouldCreateAroundMethodStatLogger() {
        AroundMethodLogger logger = factory.getAroundMethodLogger(methodSignature, target, configuration);

        assertThat(logger, not(nullValue()));
    }

    @Test
    public void shouldReuseTheSameLoggerForSameMethodInvocations() {
        AroundMethodLogger logger1 = factory.getAroundMethodLogger(methodSignature, target, configuration);
        AroundMethodLogger logger2 = factory.getAroundMethodLogger(methodSignature, target, configuration);

        assertThat(logger1, sameInstance(logger2));
    }

    @Test
    public void shouldCreateDifferentLoggersForSameMethodOnDifferentInstances() {
        AroundMethodLogger logger1 = factory.getAroundMethodLogger(methodSignature, target, configuration);
        AroundMethodLogger logger2 = factory.getAroundMethodLogger(methodSignature, mock(Object.class), configuration);

        assertThat(logger1, not(sameInstance(logger2)));
    }

    @Test
    public void shouldCreateDifferentLoggersForDifferentMethodInvocations() {
        Signature differentMethodSignature = mock(Signature.class);
        when(differentMethodSignature.toString()).thenReturn("a different method signature");
        
        AroundMethodLogger logger1 = factory.getAroundMethodLogger(methodSignature, target, configuration);
        AroundMethodLogger logger2 = factory.getAroundMethodLogger(differentMethodSignature, target, configuration);

        assertThat(logger1, not(sameInstance(logger2)));
    }
}
