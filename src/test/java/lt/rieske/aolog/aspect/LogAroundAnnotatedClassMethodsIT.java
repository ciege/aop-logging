package lt.rieske.aolog.aspect;

import javax.inject.Inject;

import lt.rieske.aolog.config.AspectOrientedLoggingConfiguration;
import lt.rieske.aolog.stubs.ClassAnnotatedServiceFacadeStub;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AspectOrientedLoggingConfiguration.class })
public class LogAroundAnnotatedClassMethodsIT {

    @Inject
    private ClassAnnotatedServiceFacadeStub classAnnotatedFacade;

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
