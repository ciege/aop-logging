package lt.rieske.aolog.perflog;

import lt.rieske.aolog.stubs.ServiceFacadeStub;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

@RunWith(MockitoJUnitRunner.class)
public class PerformanceLogAspectConfigurationTest {

	private ServiceFacadeStub targetFacade;

	@Mock
	private PerformanceLogAspect perfLogAspect;

	@Before
	public void setUp() {
		AspectJProxyFactory factory = new AspectJProxyFactory(new ServiceFacadeStub());
		factory.addAspect(perfLogAspect);
		targetFacade = factory.getProxy();
	}

	@Test
	public void shouldInvokePerformanceLogAspect() throws Throwable {
		targetFacade.methodReturningVoid();

		Mockito.verify(perfLogAspect).performanceLog(Mockito.any(ProceedingJoinPoint.class), Mockito.any(PerformanceLog.class));
		Mockito.verifyNoMoreInteractions(perfLogAspect);
	}
}
