package com.rrajasek.jlint.lint.engine;

public class LintMessage {
    private int column;
    private int endColumn;
    private int endLine;
    private boolean fatal;

    private int line;
    private String message;
    private String messageId;
    private String ruleId;
    // TODO: implement severity (enum?)
    // TODO: implement suggestions; not a priority


    public void setMessage(String message) {
        this.message = message;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
