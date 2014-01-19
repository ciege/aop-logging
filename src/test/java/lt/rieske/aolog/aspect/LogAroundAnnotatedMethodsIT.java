package lt.rieske.aolog.aspect;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import javax.annotation.Resource;
import javax.inject.Inject;

import lt.rieske.aolog.config.AspectOrientedLoggingConfiguration;
import lt.rieske.aolog.logger.AspectLoggerFactory;
import lt.rieske.aolog.logger.LoggerFactoryWrapper;
import lt.rieske.aolog.stubs.MethodAnnotatedServiceFacadeStub;

import org.aspectj.lang.Signature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AspectOrientedLoggingConfiguration.class })
public class LogAroundAnnotatedMethodsIT {

	@Mock
	private Logger logger;

	@Mock
	private LoggerFactoryWrapper loggerFactory;

	@Resource
	@InjectMocks
	private AspectLoggerFactory aspectLoggerFactory;

	@Inject
	private MethodAnnotatedServiceFacadeStub methodAnnotatedFacade;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		when(loggerFactory.getLogger(anyString())).thenReturn(logger);
	}

	@Test
	public void shouldInvokeLoggerBeforeAndAfterMethodExecution() {
		methodAnnotatedFacade.methodReturningVoid();
		verify(logger, times(2)).info(anyString(), any(Object[].class));
		verifyNoMoreInteractions(logger);
		methodAnnotatedFacade.methodReturningString("testValue");
	}

	@Test
	public void shouldInvokeDifferentLoggersForOverloadedMethods() {
		methodAnnotatedFacade.overloadedMethod();
		methodAnnotatedFacade.overloadedMethod("arg");
	}
}
