package com.rrajasek.jlint.lint.linter;

import com.rrajasek.jlint.lint.scope.Scope;

public interface LinterTraversalContext {
    void getAncestors();
    void getDeclaredVariables();
    void markVariableAsUsed(String name);
    Scope getScope();
    SourceCode getSourceCode();
}
