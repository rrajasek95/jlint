package com.rrajasek.jlint.lint.scope;

public class MethodScope extends NamedScope {
    public MethodScope(String name, Scope enclosingScope) {
        super("method", name);
        this.enclosingScope = enclosingScope;
    }
}
