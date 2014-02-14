package lt.rieske.aolog.aspect;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import lt.rieske.aolog.config.AspectLoggingConfiguration;
import lt.rieske.aolog.logger.AspectLoggerFactory;
import lt.rieske.aolog.logger.LoggerFactoryWrapper;
import lt.rieske.aolog.stubs.ClassAnnotatedServiceFacadeStub;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AspectLoggingConfiguration.class })
public class LogAroundAnnotatedClassMethodsIT {

	@Mock
	private Logger logger;

	@Mock
	private LoggerFactoryWrapper loggerFactory;

	@Autowired
	@InjectMocks
	private AspectLoggerFactory aspectLoggerFactory;

	@Autowired
	private ClassAnnotatedServiceFacadeStub classAnnotatedFacade;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		when(loggerFactory.getLogger(anyString())).thenReturn(logger);
	}

	@Test
	public void shouldInvokeLoggerBeforeAndAfterMethodExecution() {
		classAnnotatedFacade.methodReturningVoid();
		verify(logger, times(2)).info(anyString(), any(Object[].class));
        verifyNoMoreInteractions(logger);
	}

	@Test
	public void shouldInvokeDifferentLoggersForOverloadedMethods() {
		classAnnotatedFacade.overloadedMethod();
        verify(logger, times(2)).info(anyString(), argThat(new AnyVarargMatcher()));
        verifyNoMoreInteractions(logger);

		classAnnotatedFacade.overloadedMethod("arg");
		verify(logger, times(4)).info(anyString(), argThat(new AnyVarargMatcher()));
        verifyNoMoreInteractions(logger);
	}

	@Test
	public void shouldNotLogAroundPrivateAndProtectedMethods() {
		classAnnotatedFacade.methodCallingProtectedAndPrivate();
		verify(logger, times(2)).info(anyString(), argThat(new AnyVarargMatcher()));
        verifyNoMoreInteractions(logger);
	}
}
