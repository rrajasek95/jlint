package com.rrajasek.jlint.lint.scope;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scope {
    protected Scope enclosingScope;

    protected List<Scope> definedScopes;

    protected Map<String, NamedScope> symbols;
    protected Map<String, Variable> variables;

    public Scope() {
        definedScopes = new ArrayList<>();
        symbols = new HashMap<>();
        variables = new HashMap<>();
    }

    public Scope getEnclosingScope() {
        return enclosingScope;
    }

    public void define(NamedScope scope) {
        definedScopes.add(scope);
    }

    public void defineVar(Variable variable) {
        symbols.put(variable.getIdentifier(), variable);
        variables.put(variable.getIdentifier(), variable);
    }

    public NamedScope resolve(String name) {
        NamedScope symbol = symbols.get(name);

        if (symbol != null)
            return symbol;
        else if (enclosingScope != null)
            return enclosingScope.resolve(name);
        else
            return null;
    }

    public List<Variable> getVariables() {
        return new ArrayList<>(variables.values());
    }

    public List<Scope> getChildScopes() {
        return definedScopes;
    }
}
