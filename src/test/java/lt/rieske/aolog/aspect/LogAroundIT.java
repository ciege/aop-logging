package lt.rieske.aolog.aspect;

import javax.inject.Inject;

import lt.rieske.aolog.config.AspectOrientedLoggingConfiguration;
import lt.rieske.aolog.stubs.ServiceFacadeStub;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AspectOrientedLoggingConfiguration.class })
public class LogAroundIT {

    @Inject
    private ServiceFacadeStub targetFacade;

    @Test
    public void shouldInvokeLoggerBeforeAndAfterMethodExecution() throws Throwable {
        targetFacade.methodReturningVoid();
        targetFacade.methodReturningString("testValue");
    }

}
