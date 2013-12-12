package lt.rieske.aolog.stubs;

import lt.rieske.aolog.aspect.LogAround;

import org.springframework.stereotype.Component;

@Component
public class ServiceFacadeStub {

	@LogAround
	public void methodReturningVoid() {
		System.out.println("method returning void");
	}
}
