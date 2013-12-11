package lt.rieske.aolog.stubs;

import lt.rieske.aolog.perflog.PerformanceLog;

import org.springframework.stereotype.Component;

@Component
public class ServiceFacadeStub {

	@PerformanceLog
	public void methodReturningVoid() {
		System.out.println("method returning void");
	}
}
