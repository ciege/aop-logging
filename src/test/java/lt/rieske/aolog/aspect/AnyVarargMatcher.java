package lt.rieske.aolog.aspect;

import org.mockito.ArgumentMatcher;
import org.mockito.internal.matchers.VarargMatcher;

public class AnyVarargMatcher extends ArgumentMatcher<Object[]> implements VarargMatcher {

    private static final long serialVersionUID = -887322781015798344L;

    @Override
    public boolean matches(Object varargArgument) {
        return true;
    }
}