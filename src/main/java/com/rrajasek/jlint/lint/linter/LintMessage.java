package com.rrajasek.jlint.lint.linter;

public class LintMessage {
    private int column;
    private int endColumn;
    private int endLine;
    private boolean fatal;

    private int line;
    private String message;
    private String ruleId;
    private Severity severity;
    private String nodeType;
    // TODO: implement severity (enum?)
    // TODO: implement suggestions; not a priority

    public LintMessage(String ruleId, Severity severity,
                       String message, Location loc, String nodeType) {
        this.ruleId = ruleId;
        this.severity = severity;
        this.message = message;
        this.line = loc.line;
        this.column = loc.column;

        if (loc.endLine != null && loc.endLine > 0) {
            endLine = loc.endLine;
        }

        if (loc.endColumn != null && loc.endColumn > 0)  {
            endColumn = loc.endColumn;
        }

        this.column = column + 1;
        this.nodeType = nodeType;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public String getMessage() {
        return message;
    }

    public String getRuleId() {
        return ruleId;
    }

    public Severity getSeverity() {
        return severity;
    }
}
