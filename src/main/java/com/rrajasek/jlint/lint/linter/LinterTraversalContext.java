package com.rrajasek.jlint.lint.linter;

public interface LinterTraversalContext {
    void getAncestors();
    void getDeclaredVariables();
    void markVariableAsUsed(String name);
    void getScope();
    SourceCode getSourceCode();
}
