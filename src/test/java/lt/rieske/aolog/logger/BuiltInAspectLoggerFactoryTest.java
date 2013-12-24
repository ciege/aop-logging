package lt.rieske.aolog.logger;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import lt.rieske.aolog.annotation.LogAround;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BuiltInAspectLoggerFactoryTest {

    @Mock
    private ProceedingJoinPoint joinPoint;

    @Mock
    private Object target;

    private LogAround configuration;

    private AspectLoggerFactory factory;

    @Before
    public void setUp() {
        factory = new BuiltInAspectLoggerFactory();
    }

    @Test
    public void shouldCreateAroundMethodStatLogger() {
        AroundMethodLogger logger = factory.getAroundMethodLogger(joinPoint, target, configuration);

        assertThat(logger, is(not(nullValue())));
    }
}
