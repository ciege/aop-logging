package lt.rieske.aolog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("lt.rieske.aolog")
@EnableAspectJAutoProxy
public class AspectOrientedLoggingConfiguration {

}
