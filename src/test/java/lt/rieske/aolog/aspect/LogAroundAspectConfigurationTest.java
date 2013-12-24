package lt.rieske.aolog.aspect;

import lt.rieske.aolog.annotation.LogAround;
import lt.rieske.aolog.aspect.LogAroundAspect;
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
public class LogAroundAspectConfigurationTest {

    private ServiceFacadeStub targetFacade;

    @Mock
    private LogAroundAspect logAroundAspect;

    @Before
    public void setUp() {
        AspectJProxyFactory factory = new AspectJProxyFactory(new ServiceFacadeStub());
        factory.addAspect(logAroundAspect);
        targetFacade = factory.getProxy();
    }

    @Test
    public void shouldInvokePerformanceLogAspect() throws Throwable {
        targetFacade.methodReturningVoid();

        Mockito.verify(logAroundAspect).logAround(Mockito.any(ProceedingJoinPoint.class), Mockito.any(LogAround.class));
        Mockito.verifyNoMoreInteractions(logAroundAspect);
    }
}
