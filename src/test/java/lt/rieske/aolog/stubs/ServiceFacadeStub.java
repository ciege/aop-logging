package lt.rieske.aolog.stubs;

import lt.rieske.aolog.annotation.LogAround;

import org.springframework.stereotype.Component;

@Component
public class ServiceFacadeStub {

    @LogAround
    public void methodReturningVoid() {
        System.out.println("method returning void");
    }

    @LogAround
    public String methodReturningString(String value) {
        System.out.println("method returning: " + value);
        return value;
    }
}
