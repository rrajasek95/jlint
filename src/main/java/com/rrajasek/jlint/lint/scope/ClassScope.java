package com.rrajasek.jlint.lint.scope;

public class ClassScope extends NamedScope {

    public ClassScope(String name, Scope enclosingScope) {
        super("class", name);

        this.enclosingScope = enclosingScope;
    }
}
