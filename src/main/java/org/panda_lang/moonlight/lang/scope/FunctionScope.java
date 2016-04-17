package org.panda_lang.moonlight.lang.scope;

import org.panda_lang.moonlight.core.element.scope.Scope;

public class FunctionScope extends Scope {

    private final String functionName;

    public FunctionScope(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionName() {
        return functionName;
    }

    @Override
    public String getName() {
        return getFunctionName();
    }

}