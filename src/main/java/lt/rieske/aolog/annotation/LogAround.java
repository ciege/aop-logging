package lt.rieske.aolog.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import lt.rieske.aolog.logger.wrapper.LogLevel;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface LogAround {
    String value() default "stat";

    LogLevel logLevel() default LogLevel.DEBUG;
}
