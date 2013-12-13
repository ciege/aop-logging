package lt.rieske.aolog.logger;

import lt.rieske.aolog.aspect.LogAround;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BuiltInAspectLoggerFactoryTest {

	@Mock
	private ProceedingJoinPoint joinPoint;

	private LogAround configuration;

	private AspectLoggerFactory factory;

	@Before
	public void setUp() {
		factory = new BuiltInAspectLoggerFactory();
	}

	@Test
	public void shouldCreateAroundMethodStatLogger() {
		AroundMethodLogger logger = factory.getAroundMethodLogger(joinPoint, configuration);

		assertThat(logger, is(not(nullValue())));
	}
}
