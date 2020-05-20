package com.rrajasek.jlint.lint.linter;

public class LintInput {
    String filePath;
    String text;

    public String getFilePath() {
        return filePath;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
