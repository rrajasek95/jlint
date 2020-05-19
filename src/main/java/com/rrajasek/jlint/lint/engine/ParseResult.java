package com.rrajasek.jlint.lint.engine;

import org.antlr.v4.runtime.tree.ParseTree;

public class ParseResult {
    private ParseTree ast;

    public ParseResult(ParseTree parseTree) {
        this.ast = parseTree;
    }

    public ParseTree getAst() {
        return ast;
    }
}
