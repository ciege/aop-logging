package lt.rieske.aolog.logger;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import lt.rieske.aolog.annotation.LogAround;
import lt.rieske.aolog.logger.wrapper.LogLevel;

import org.aspectj.lang.Signature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BuiltInAspectLoggerFactoryTest {

	private static final String METHOD_STRING = "methodString";

	@Mock
	private Signature methodSignature;

	@Mock
	private Object target;

	@Mock
	private LogAround configuration;

	@Mock
	private LoggerFactoryWrapper loggerFactory;

	@InjectMocks
	private BuiltInAspectLoggerFactory factory;

	@Before
	public void setUp() {
		when(configuration.value()).thenReturn("default");
		when(configuration.logLevel()).thenReturn(LogLevel.DEBUG);
		when(methodSignature.toString()).thenReturn("a method signature");
		when(target.toString()).thenReturn(METHOD_STRING);
	}

	@Test
	public void shouldCreateAroundMethodStatLogger() {
		when(configuration.value()).thenReturn("stat");

		AroundMethodLogger logger = factory.getAroundMethodLogger(methodSignature, target, configuration);

		verify(loggerFactory).getLogger(eq(METHOD_STRING));
		assertThat(logger, instanceOf(AroundMethodStatLogger.class));
	}

	@Test
	public void shouldCreateAroundMethodPerfLogger() {
		when(configuration.value()).thenReturn("perf");

		AroundMethodLogger logger = factory.getAroundMethodLogger(methodSignature, target, configuration);

		verify(loggerFactory).getLogger(eq(METHOD_STRING));
		assertThat(logger, instanceOf(AroundMethodPerfLogger.class));
	}

	@Test
	public void shouldCreateAroundMethodStatLoggerWhenTypeIsNotRecognized() {
		when(configuration.value()).thenReturn("invalidLoggerType");

		AroundMethodLogger logger = factory.getAroundMethodLogger(methodSignature, target, configuration);

		verify(loggerFactory).getLogger(eq(METHOD_STRING));
		assertThat(logger, instanceOf(AroundMethodStatLogger.class));
	}

	@Test(expected = NullPointerException.class)
	public void shouldThrowNPEForIllegalNullValueInConfiguration() {
		when(configuration.value()).thenReturn(null);

		factory.getAroundMethodLogger(methodSignature, target, configuration);
	}

	@Test
	public void shouldCreateDifferentLoggersForSameMethodInvocations() {
		AroundMethodLogger logger1 = factory.getAroundMethodLogger(methodSignature, target, configuration);
		AroundMethodLogger logger2 = factory.getAroundMethodLogger(methodSignature, target, configuration);

		assertThat(logger1, not(sameInstance(logger2)));
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
