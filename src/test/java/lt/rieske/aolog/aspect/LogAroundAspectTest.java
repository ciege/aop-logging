package lt.rieske.aolog.aspect;

import lt.rieske.aolog.aspect.LogAround;
import lt.rieske.aolog.aspect.LogAroundAspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;

@RunWith(MockitoJUnitRunner.class)
public class LogAroundAspectTest {

	@Mock
	private Logger logger;

	@Mock
	private ProceedingJoinPoint joinPoint;

	@Mock
	private LogAround logAroundMarker;

	@InjectMocks
	private LogAroundAspect logAroundAspect;

	@Test
	public void shouldInvokeLoggerBeforeAndAfterMethodExecution() throws Throwable {
		logAroundAspect.logAround(joinPoint, logAroundMarker);
	}
}
