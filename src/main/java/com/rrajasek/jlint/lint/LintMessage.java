package com.rrajasek.jlint.lint;

public class LintMessage {
    private int column;
    private int endColumn;
    private int endLine;
    private boolean fatal;

    private int line;
    private String message;
    private String ruleId;
    // TODO: implement severity (enum?)
    // TODO: implement suggestions; not a priority
}
