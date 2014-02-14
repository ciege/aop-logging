package lt.rieske.aolog.stubs;

import lt.rieske.aolog.annotation.LogAround;
import lt.rieske.aolog.logger.wrapper.LogLevel;

import org.springframework.stereotype.Component;

@Component
@LogAround(logLevel = LogLevel.INFO)
public class ClassAnnotatedServiceFacadeStub {

    public void methodReturningVoid() {
        System.out.println("method returning void");
    }

    public String methodReturningString(String value) {
        System.out.println("method returning: " + value);
        return value;
    }

    public void overloadedMethod() {
        System.out.println("overloadedMethod no args");
    }

    public void overloadedMethod(String arg) {
        System.out.println("overloadedMethod with args");
    }

    public void methodCallingProtectedAndPrivate() {
        System.out.println("methodCallingProtectedAndPrivate");
        protectedMethod();
        privateMethod();
    }

    protected void protectedMethod() {
        System.out.println("protected method should not have logging around it");
    }

    private void privateMethod() {
        System.out.println("private method should not have logging around it");
    }
}
