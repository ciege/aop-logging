package lt.rieske.aolog.config;

import lt.rieske.aolog.logger.AspectLoggerFactory;
import lt.rieske.aolog.logger.BuiltInAspectLoggerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("lt.rieske.aolog")
@EnableAspectJAutoProxy
public class AspectOrientedLoggingConfiguration {

    @Bean
    public AspectLoggerFactory aspectLoggerFactory() {
        return new BuiltInAspectLoggerFactory();
    }
}
