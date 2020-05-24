package com.rrajasek.jlint.lint.linter;

import com.rrajasek.jlint.lint.scope.Scope;

public class LinterTraversalContextBase implements LinterTraversalContext {
    private Scope scope;

    public LinterTraversalContextBase(Scope scope) {
        this.scope = scope;
    }
    public void getAncestors() {

    }

    public void getDeclaredVariables() {

    }

    @Override
    public void markVariableAsUsed(String name) {

    }

    @Override
    public Scope getScope() {
        return scope;
    }

    @Override
    public SourceCode getSourceCode() {
        return null;
    }
}
