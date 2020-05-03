package com.rrajasek.jlint.lint.engine;

import com.rrajasek.jlint.lint.LintMessage;

public class LintResult {
    private String filePath;
    private LintMessage[] messages;
    private int errorCount;
    private int warningCount;
    private String source;

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
}
