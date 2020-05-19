package com.rrajasek.jlint.lint;

import com.rrajasek.jlint.lint.engine.SourceCode;

public class LinterTraversalContextBase implements LinterTraversalContext {
    public void getAncestors() {

    }

    public void getDeclaredVariables() {

    }

    @Override
    public void markVariableAsUsed(String name) {

    }

    @Override
    public void getScope() {

    }

    @Override
    public SourceCode getSourceCode() {
        return null;
    }
}
