package lt.rieske.aolog.aspect;

import javax.inject.Inject;

import lt.rieske.aolog.aspect.LogAroundIT.LoggerConfiguration;
import lt.rieske.aolog.config.AspectOrientedLoggingConfiguration;
import lt.rieske.aolog.stubs.ServiceFacadeStub;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AspectOrientedLoggingConfiguration.class, LoggerConfiguration.class })
public class LogAroundIT {

	@Configuration
	public static class LoggerConfiguration {

		private static final String PERF_LOGGER = "perfLogger";

		@Bean
		public Logger logger() {
			return LoggerFactory.getLogger(PERF_LOGGER);
		}
	}

	@Inject
	private ServiceFacadeStub targetFacade;

	@Test
	public void shouldInvokeLoggerBeforeAndAfterMethodExecution() throws Throwable {
		targetFacade.methodReturningVoid();
	}
}
