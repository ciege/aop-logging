package lt.rieske.aolog.stubs;

import lt.rieske.aolog.annotation.LogAround;
import lt.rieske.aolog.logger.wrapper.LogLevel;

import org.springframework.stereotype.Component;

@Component
public class MethodAnnotatedServiceFacadeStub {

    @LogAround(logLevel = LogLevel.INFO)
    public void methodReturningVoid() {
        System.out.println("method returning void");
    }

    @LogAround("perf")
    public void overloadedMethod() {
        System.out.println("overloadedMethod no args");
    }

    @LogAround(value = "perf", logLevel = LogLevel.INFO)
    public void overloadedMethod(String arg) {
        System.out.println("overloadedMethod with args");
    }
}
