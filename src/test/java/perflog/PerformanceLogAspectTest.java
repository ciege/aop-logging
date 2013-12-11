package perflog;

import lt.rieske.aolog.config.AspectOrientedLoggingConfiguration;
import lt.rieske.aolog.stubs.ServiceFacadeStub;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AspectOrientedLoggingConfiguration.class })
public class PerformanceLogAspectTest {

	@Autowired
	private ServiceFacadeStub serviceFacade;

	@Test
	public void shouldInvokePerformanceLogAspect() {
		serviceFacade.methodReturningVoid();
	}
}
