package com.rrajasek.jlint.lint.linter;

import java.util.List;

public class LintResult {
    private String filePath;
    private LintMessage[] messages;
    private int errorCount;
    private int warningCount;
    private String source;

    public LintResult(List<LintMessage> messageList) {
        this.messages = messageList.toArray(new LintMessage[] {});
    }

    public String getFilePath() {
        return filePath;
    }

    public LintMessage[] getMessages() {
        return messages;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public int getWarningCount() {
        return warningCount;
    }

    public String getSource() {
        return source;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
