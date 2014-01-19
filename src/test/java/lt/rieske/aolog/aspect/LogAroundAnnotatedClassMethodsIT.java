package lt.rieske.aolog.aspect;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import javax.annotation.Resource;
import javax.inject.Inject;

import lt.rieske.aolog.config.AspectOrientedLoggingConfiguration;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AspectOrientedLoggingConfiguration.class })
public class LogAroundAnnotatedClassMethodsIT {

    @Mock
    private Logger logger;

    @Mock
    private LoggerFactoryWrapper loggerFactory;

    @Resource
    @InjectMocks
    private AspectLoggerFactory aspectLoggerFactory;

    @Inject
    private ClassAnnotatedServiceFacadeStub classAnnotatedFacade;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(loggerFactory.getLogger(anyString())).thenReturn(logger);
    }

    @Test
    public void shouldInvokeLoggerBeforeAndAfterMethodExecution() {
        classAnnotatedFacade.methodReturningVoid();
        classAnnotatedFacade.methodReturningString("testValue");
    }

    @Test
    public void shouldInvokeDifferentLoggersForOverloadedMethods() {
        classAnnotatedFacade.overloadedMethod();
        classAnnotatedFacade.overloadedMethod("arg");
    }

    @Test
    public void shouldNotLogAroundPrivateAndProtectedMethods() {
        classAnnotatedFacade.methodCallingProtectedAndPrivate();
    }
}
