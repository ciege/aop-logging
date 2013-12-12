import static ch.qos.logback.classic.Level.DEBUG
import static ch.qos.logback.classic.Level.TRACE
import ch.qos.logback.classic.PatternLayout
import ch.qos.logback.core.ConsoleAppender

appender("STDOUT", ConsoleAppender) {
	layout(PatternLayout) { pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n" }
}
logger("lt.rieske.logger", TRACE)
root(DEBUG, ["STDOUT"])