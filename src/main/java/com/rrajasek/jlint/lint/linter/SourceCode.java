package com.rrajasek.jlint.lint.linter;

import org.antlr.v4.runtime.tree.ParseTree;

public class SourceCode {
    private ParseTree ast;
    private String text;

    public ParseTree getAst() {
        return ast;
    }
    public String getText() { return text; }
    public SourceCode(String text, ParseTree ast) {
        this.text = text;
        this.ast = ast;
    }
}
