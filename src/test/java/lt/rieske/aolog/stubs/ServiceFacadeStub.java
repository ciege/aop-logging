package lt.rieske.aolog.stubs;

import lt.rieske.aolog.annotation.LogAround;
import lt.rieske.aolog.logger.wrapper.LogLevel;

import org.springframework.stereotype.Component;

@Component
public class ServiceFacadeStub {

    @LogAround(logLevel = LogLevel.INFO)
    public void methodReturningVoid() {
        System.out.println("method returning void");
    }

    @LogAround("perf")
    public String methodReturningString(String value) {
        System.out.println("method returning: " + value);
        return value;
    }

    @LogAround
    public void overloadedMethod() {
        System.out.println("overloadedMethod no args");
    }

    @LogAround(logLevel = LogLevel.INFO)
    public void overloadedMethod(String arg) {
        System.out.println("overloadedMethod with args");
    }
}
