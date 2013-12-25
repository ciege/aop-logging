package lt.rieske.aolog.logger;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
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
    }

    @Test
    public void shouldCreateAroundMethodStatLogger() {
        when(configuration.logLevel()).thenReturn(LogLevel.DEBUG);
        AroundMethodLogger logger = factory.getAroundMethodLogger(methodSignature, target, configuration);

        assertThat(logger, is(not(nullValue())));
    }
}
