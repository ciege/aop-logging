package lt.rieske.aolog.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggerFactoryWrapper {

	public Logger getLogger(String loggerName) {
		return LoggerFactory.getLogger(loggerName);
	}
}
