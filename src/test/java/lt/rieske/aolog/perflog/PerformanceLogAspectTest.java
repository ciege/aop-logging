package lt.rieske.aolog.perflog;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;

@RunWith(MockitoJUnitRunner.class)
public class PerformanceLogAspectTest {

	@Mock
	private Logger logger;

	@Mock
	private ProceedingJoinPoint joinPoint;

	@Mock
	private PerformanceLog performanceLog;

	@InjectMocks
	private PerformanceLogAspect perfLogAspect;

	@Test
	public void shouldInvokeLoggerBeforeAndAfterMethodExecution() throws Throwable {
		perfLogAspect.performanceLog(joinPoint, performanceLog);
	}
}
