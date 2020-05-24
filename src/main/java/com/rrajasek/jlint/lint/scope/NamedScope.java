package com.rrajasek.jlint.lint.scope;

public class NamedScope extends Scope {
    private String type;
    private String identifier;

    public NamedScope(String type, String identifier) {
        this.type = type;
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getType() {
        return type;
    }
}
