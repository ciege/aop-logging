package lt.rieske.aolog.aspect;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import lt.rieske.aolog.annotation.LogAround;
import lt.rieske.aolog.stubs.MethodAnnotatedServiceFacadeStub;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

@RunWith(MockitoJUnitRunner.class)
public class LogAroundAspectConfigurationTest {

    private MethodAnnotatedServiceFacadeStub targetFacade;

    @Mock
    private LogAroundAspect logAroundAspect;

    @Before
    public void setUp() {
        AspectJProxyFactory factory = new AspectJProxyFactory(new MethodAnnotatedServiceFacadeStub());
        factory.addAspect(logAroundAspect);
        targetFacade = factory.getProxy();
    }

    @Test
    public void shouldInvokePerformanceLogAspect() throws Throwable {
        targetFacade.methodReturningVoid();

        verify(logAroundAspect).logAround(any(ProceedingJoinPoint.class), any(LogAround.class));
        verifyNoMoreInteractions(logAroundAspect);
    }
}
