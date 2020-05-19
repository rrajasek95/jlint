package com.rrajasek.jlint.lint;

import com.rrajasek.jlint.lint.engine.SourceCode;

public interface LinterTraversalContext {
    void getAncestors();
    void getDeclaredVariables();
    void markVariableAsUsed(String name);
    void getScope();
    SourceCode getSourceCode();
}
